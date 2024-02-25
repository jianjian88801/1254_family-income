package com.xunmaw.income.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiR {
    private Integer code;
    private String msg;
    private Object data;
}
