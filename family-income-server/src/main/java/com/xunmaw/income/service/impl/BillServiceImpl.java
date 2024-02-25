package com.xunmaw.income.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunmaw.income.mapper.UsersMapper;
import com.xunmaw.income.service.BillService;
import com.xunmaw.income.service.CtgsService;
import com.xunmaw.income.service.CtgzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xunmaw.income.entity.Bill;
import com.xunmaw.income.entity.Ctgs;
import com.xunmaw.income.entity.Ctgz;
import com.xunmaw.income.entity.Users;
import com.xunmaw.income.mapper.BillMapper;

import java.util.*;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
	CtgsService ctgsService;
    @Autowired
	CtgzService ctgzService;
    @Autowired
    BillMapper billMapper;
    @Autowired
	UsersMapper usersMapper;

    @Override
    public Map<Integer, Map<String, Object>> showBills(String name, String phone,String password, String date){
        //检验用户
        QueryWrapper<Users> userQ = new QueryWrapper<>();
        userQ.eq("sjh", phone);
        userQ.eq("pwd", password);
        Users user = usersMapper.selectOne(userQ);
        if(user == null){
            return null;
        }
        Calendar instance = Calendar.getInstance();
        int cmonth = instance.get(Calendar.MONTH);
        int cyear = instance.get(Calendar.YEAR);
        String year = date.split("-")[0];
        Map<Integer, Map<String, Object>> rst = new HashMap<>();
        List<Bill> bills = billMapper.selectList(new QueryWrapper<Bill>().eq("user", name)
        .like("time", year));
        if(Integer.parseInt(year) < cyear){
            cmonth = 11;
        }
        for (int i=1; i<=cmonth+1; i++){
            Map<String, Object> map2 = new HashMap<>();
            float earn = 0;
            float cost = 0;
           for (Bill bill :bills){
               if (bill.getTime().getMonth() + 1 == i){
                   float price = bill.getPrice();
                   if (bill.getType() == 1){
                        //earn
                        earn += price;
                    }else {
                        cost += price;
                    }
               }
           }
            map2.put("earn", earn);
            map2.put("cost", cost);
            map2.put("date", year+"-"+String.format("%02d", i));
            rst.put(i, map2);
        }
        return rst;
    }

    @Override
    public Integer addBill(Bill bill) {
        if (bill == null) {
            return 0;
        } else {
            String ctgId = bill.getCtg();
            if(bill.getType() == 1){
                List<Ctgs> ctgsList = ctgsService.getAll();
                for (Ctgs ctgs : ctgsList) {
                    if (String.valueOf(ctgs.getId()).equals(ctgId)) {
                        bill.setCtg(ctgs.getName());
                    }
                }
            }else{
                List<Ctgz> ctgzList = ctgzService.getAll();
                for (Ctgz ctgz : ctgzList) {
                    if (String.valueOf(ctgz.getId()).equals(ctgId)) {
                        bill.setCtg(ctgz.getName());
                    }
                }
            }
            // 可以重构

            return billMapper.insert(bill);
        }
    }

    @Override
    public List<Bill> getBills(String name, Integer type) {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", name).eq("type", type);
        return billMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Bill> getBillsByPage(String name, String phone, Integer type, Integer current, Integer yearMonth, String date, String password) {
        //检验用户
        QueryWrapper<Users> userQ = new QueryWrapper<>();
        userQ.eq("sjh", phone);
        userQ.eq("pwd", password);
        Users user = usersMapper.selectOne(userQ);
        if(user == null){
            return null;
        }
        Page<Bill> page = new Page<>(current, 10);
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", name);
        queryWrapper.eq("type", type);
        queryWrapper.orderByDesc("time");
        if(yearMonth == 1){
            queryWrapper.like("time", date.split("-")[0]);
        }else{
            queryWrapper.like("time", date);
        }

        return billMapper.selectPage(page, queryWrapper);
    }

}
