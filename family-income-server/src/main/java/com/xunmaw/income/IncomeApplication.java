package com.xunmaw.income;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 *
 * @author 2023/2/24 18:20
 */
@SpringBootApplication
@MapperScan("com.xunmaw.income.mapper")
public class IncomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomeApplication.class, args);
    }

}
