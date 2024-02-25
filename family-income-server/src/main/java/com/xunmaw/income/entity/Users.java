package com.xunmaw.income.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer zt;
    private String pwd;
    private String name;
    private String email;
    private Integer auth;
    private String sjh;
    private String pkey;  //家庭key
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rdDate;
}
