package com.xunmaw.income.service;


import com.xunmaw.income.config.CloudStorageConfig;

import java.io.FileInputStream;

/**
 * @program: Springboot Qiniu
 * @description:
 * @author: CodeDuck
 * @create: 2020-07-30 19:48
 **/
public abstract class UploadImageService {

    protected CloudStorageConfig config;

    public abstract String uploadQNImg(FileInputStream file, String path);
}
