package com.xunmaw.income.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ctgz {
    private Integer id;
    private String name;
    private Integer status;
}
