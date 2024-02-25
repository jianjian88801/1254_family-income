package com.xunmaw.income.utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Music {

    private String hotTop = "https://music.163.com/#/discover/toplist?id=3778678";

    private void getMusicList(){
        try {
            Document document = Jsoup.connect(hotTop).get();
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
