package com.xunmaw.income.controller;

import cn.hutool.core.util.RandomUtil;
import com.xunmaw.income.service.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.xunmaw.income.utils.UpException;
import com.xunmaw.income.utils.UpYun;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MainConroller {
    @Autowired
    UpYun upYun;
    @Autowired
	UploadImageService uploadImageService;

    @PostMapping("/uploadImage")
    public List<String> uploadImage(String name, float price, MultipartFile[] files){
        String date = new SimpleDateFormat("yyyy-MM-dd hh").format(new Date());
        List<String> fileList = new ArrayList<>();
        int j = 0;
        int random = RandomUtil.randomInt(1, 9999);
        for (MultipartFile file : files){
            String fn = file.getOriginalFilename();
            int i = fn.lastIndexOf(".");
            String suf = fn.substring(i);
            String filename = "sz-" + name + "-" + price + "-" + date + j + random + suf;
            fileList.add(filename);
            try {
//                uploadImageService.uploadQNImg(fileInputStream, filename);
                upYun.writeFile(filename, file.getBytes(), false);
                //上传到又拍云
                j++;
            } catch (IOException | UpException e) {
                e.printStackTrace();
            }
        }
        return fileList;
    }
}
