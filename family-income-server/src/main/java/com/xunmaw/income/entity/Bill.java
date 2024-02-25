package com.xunmaw.income.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Mapper
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private float price;
    private String content;
    private String imgurl;
    private String user;
    private Integer type;
    private String ctg;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date time;

}
