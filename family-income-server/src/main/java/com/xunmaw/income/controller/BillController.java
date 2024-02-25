package com.xunmaw.income.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunmaw.income.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xunmaw.income.entity.ApiR;
import com.xunmaw.income.entity.Bill;
import com.xunmaw.income.utils.ApiRUtil;

import java.util.List;
import java.util.Map;


@RestController
public class BillController {

    @Autowired
	BillService billService;

    /**
     * 得到各月收支情况
     * @param name 名字
     * @return
     */
    @PostMapping("/showBills")
    public ApiR showBills(String name, String phone, String password, String date){
        Map<Integer, Map<String, Object>> mapList = billService.showBills(name, phone, password, date);
        return ApiRUtil.success(mapList);
    }
    /**
     * 账单添加
     *
     * @param bill
     * @return
     */
    @PostMapping("/bill")
    public ApiR addBill(Bill bill) {
        if(billService.addBill(bill) == 1){
            return ApiRUtil.success("");
        }else{
            //不上传文件，并且报错
            return ApiRUtil.error("失败");
        }
    }

    /**
     * 得到所有数据
     * @param name
     * @param type
     * @return
     */
    @GetMapping("/bill")
    public ApiR getBill(String name, Integer type){
        List<Bill> bills = billService.getBills(name, type);
        if(bills == null){
            return ApiRUtil.error("失败");
        }else {
            return ApiRUtil.success(bills);
        }
    }
    @GetMapping("/getPageBills")
    public ApiR getPageBills(String name, String phone, Integer type, Integer current, Integer yearMonth, String date, String password){
        Page<Bill> billsByPage = billService.getBillsByPage(name, phone, type, current, yearMonth, date, password);
        if(billsByPage == null){
            return ApiRUtil.error("获取分页数据出错");
        }
        return ApiRUtil.success(billsByPage);
    }

}
