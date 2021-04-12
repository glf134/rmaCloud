package com.rma.system.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "rma-real")
public interface RealServiceFeign {

    @RequestMapping(value = "/demo/updateSysLog", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public boolean updateSysLog(JSONObject jsonObject);

}