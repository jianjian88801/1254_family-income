package com.xunmaw.income;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FabApplicationTests {
    private String hotTop = "https://music.163.com/#/discover/toplist?id=3778678";
    @Test
    void contextLoads() {
        try {
            Document document = Jsoup.connect(hotTop).get();
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
