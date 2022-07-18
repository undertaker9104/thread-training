package springtest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class TestApp {


    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }

    @PostMapping("/test")
    public String hello(@RequestParam Map<String, String> m) {
        System.out.println(m);
        return "Hello";

    }
}