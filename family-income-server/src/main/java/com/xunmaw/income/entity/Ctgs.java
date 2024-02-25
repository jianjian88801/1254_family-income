package com.xunmaw.income.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ctgs {
    private Integer id;
    private String name;
    private Integer status;
}
