package com.xunmaw.income.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.income.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xunmaw.income.entity.Auth;
import com.xunmaw.income.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
	AuthMapper authMapper;
    @Override
    public Auth getAuth(Integer id) {
        return authMapper.selectOne(new QueryWrapper<Auth>().eq("id", id));
    }
}
