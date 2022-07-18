package jvm;

import javassist.*;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyClassLoader {



    private static byte[] genClass() throws CannotCompileException, IOException, NotFoundException {

        // javassist
        var pool = ClassPool.getDefault();

        var ctClass = pool.getOrNull("greetings.Go");
        if(ctClass != null) {
            ctClass.defrost();
        }
        ctClass = pool.makeClass("greetings.Go");
        var method = new CtMethod(CtClass.voidType, "greetings", new CtClass[]{}, ctClass);
        method.setModifiers(Modifier.PUBLIC);
        method.setBody("{ System.out.println(\"Hi Greetings!!\"); }");
        ctClass.addMethod(method);
        return ctClass.toBytecode();
    }

    class BinLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if (name == "greetings.Go") {
                try {
                    var bytes = genClass();
                    return defineClass("greetings.Go", genClass(), 0, bytes.length);
                } catch (CannotCompileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            return super.findClass(name);
        }
    }

    class NetLoader extends ClassLoader {
        byte[] bytes;

        public NetLoader() throws IOException {
            this.connect();
        }

        private void connect() throws IOException {
            try(var socket = new Socket("localhost", 8000)) {
                bytes = socket.getInputStream().readAllBytes();
            }
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if(name == "greetings.Go") {
                return defineClass("greetings.Go", bytes, 0, bytes.length);
            }
            return super.findClass(name);
        }
    }

    @Test
    public void server() throws IOException, CannotCompileException, NotFoundException {
        var serverSocket = new ServerSocket(8000);
        var bytes = genClass();
        while(true) {
            try(var clientSocket = serverSocket.accept()) {
                System.out.println("receve request...");
                var out = clientSocket.getOutputStream();
                out.write(bytes);
                out.flush();
            }
        }
    }

    @Test
    public void test_gen() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var loader = new BinLoader();
        var goclazz = loader.loadClass("greetings.Go");
        var go = goclazz.getConstructor().newInstance();
        go.getClass().getMethod("greetings").invoke(go);
    }
    @Test
    public void test_net() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        var loader = new NetLoader();
        var goclazz = loader.loadClass("greetings.Go");
        var go = goclazz.getConstructor().newInstance();
        go.getClass().getMethod("greetings").invoke(go);
    }






}
