package com.rma.real.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ram.common.LogAnnotation;
import com.ram.common.exception.BaseException;
import com.rma.real.dao.SysLogDao;
import com.rma.real.vo.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author glf

/**
 * @author glf
 */
@RestController
@Api(tags = "Demo API")
@RequestMapping("/demo")
public class DemoController {

    @Value("${demo.file}")
    private String file;

    @Autowired
    private SysLogDao sysLogDao;

    @GetMapping("/dd")
    @ApiOperation(value = "分页获取映射")
    @LogAnnotation(module = "rma-real", recordRequestParam = false)
    public String selectPage()  throws BaseException {
        return "测试资源访问====Real"+file;
    }

    @PostMapping("/updateSysLog")
    @ApiOperation(value = "测试")
    @LogAnnotation(module = "rma-real", recordRequestParam = false)
    @Transactional
    public boolean updateSysLog(@RequestBody JSONObject jsonObject)  throws BaseException {
        int nun = sysLogDao.updateByPrimaryKey(JSON.toJavaObject(jsonObject, SysLog.class));
        System.out.println(1/0);
        return true;
    }
}
