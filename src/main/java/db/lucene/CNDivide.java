package db.lucene;


import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class CNDivide {

    public static void main(String[] argv) throws IOException, InterruptedException {
        String text = "今天是一个教大家分词的好日子";

        var it= new IKSegmenter(new StringReader(text), false);
        Lexeme token = null;
        while((token = it.next()) != null) {
            System.out.println(token.getLexemeText());
        }
    }
}
