package com.xunmaw.income.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunmaw.income.entity.Bill;

import java.util.List;
import java.util.Map;

public interface BillService {
    Map<Integer, Map<String, Object>> showBills(String name, String phone,String password, String date);
    Integer addBill(Bill bill);
    List<Bill> getBills(String name, Integer type);
    Page<Bill> getBillsByPage(String name, String phone, Integer type, Integer current
            , Integer yearMonth, String date, String password);
}
