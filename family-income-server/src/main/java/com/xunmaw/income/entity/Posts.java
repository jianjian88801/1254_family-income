package com.xunmaw.income.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Posts {
    private Integer id;
    private Integer zan;
    private Integer zt;
    private Integer type;
    private String user;
    private String date;
    private String text;
    private String imgs;
    private String p_key;
}
