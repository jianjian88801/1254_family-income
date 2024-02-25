package com.xunmaw.income.utils;

import org.springframework.stereotype.Component;
import com.xunmaw.income.entity.ApiR;

@Component
public class ApiRUtil {
    public static ApiR success(Object data){
        ApiR apiR = new ApiR();
        apiR.setCode(200);
        apiR.setMsg("成功");
        apiR.setData(data);
        return apiR;
    }
    public static ApiR error(String msg){
        ApiR apiR = new ApiR();
        apiR.setCode(400);
        apiR.setMsg(msg);
        apiR.setData(null);
        return apiR;
    }
}
