package com.rma.system.controller;

import com.alibaba.fastjson.JSON;
import com.ram.common.LogAnnotation;
import com.ram.common.exception.BaseException;
import com.ram.common.utils.UUIDUtils;
import com.rma.system.feign.RealServiceFeign;
import com.rma.system.service.SysLogService;
import com.rma.system.utils.MinioUtils;
import com.rma.system.vo.SysLog;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author glf

/**
 * @author glf
 */
@RestController
@Api(tags = "Demo API")
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private RealServiceFeign realServiceFeign;

    @Autowired
    private MinioUtils minioUtils;

    @GetMapping("/dd")
    @ApiOperation(value = "分页获取映射")
    @LogAnnotation(module = "model-parent", recordRequestParam = false)
    public String selectPage()  throws BaseException {
        return "测试资源访问";
    }

    @PostMapping("/upload")
    @ApiOperation(value = "上传")
    @LogAnnotation(module = "model-parent", recordRequestParam = false)
    @SneakyThrows
    public String upload(@RequestParam(value="file", required = false) MultipartFile file)  throws BaseException {
        minioUtils.putObject("demo1",file.getOriginalFilename(), file.getInputStream(),file.getSize(),file.getContentType());
        return "测试资源访问";
    }
    @PostMapping("/minioApi")
    @ApiOperation(value = "测试minioApi")
    @LogAnnotation(module = "model-parent", recordRequestParam = false)
    @SneakyThrows
    public Object downLoad()  throws BaseException {
       return minioUtils.getObjectUrl("demo1","SysTagPointController.java",100);
    }
    @GetMapping("/transDemo")
    @ApiOperation(value = "测试分布式事务")
    @LogAnnotation(module = "model-parent", recordRequestParam = false)
//    @Transactional
//    @LcnTransaction
    @GlobalTransactional
    public String transDemo()  throws BaseException {
        SysLog sysLog = new SysLog();
        sysLog.setId(UUIDUtils.getGUID());
        sysLog.setCreateTime(new Date(System.currentTimeMillis()));
        sysLog.setFlag(true);
        sysLog.setUsername("123");
        sysLog.setModule("456");
        sysLogService.insert(sysLog);
        if(realServiceFeign.updateSysLog(JSON.parseObject(JSON.toJSONString(sysLog)))){
            return "分布式事务调用成功";
        };
        return "测试资源访问";
    }
}
