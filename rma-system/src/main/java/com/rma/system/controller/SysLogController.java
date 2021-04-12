package com.rma.system.controller;


import com.ram.common.LogAnnotation;
import com.ram.common.exception.BaseException;
import com.ram.common.result.Result;
import com.rma.system.service.SysLogService;
import com.rma.system.vo.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author glf
 */
@RestController
@Api(tags = "SysLog API")
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增日志")
    @LogAnnotation(module = "model-parent", recordRequestParam = true)
    public Result insert(@RequestBody SysLog sysLog) throws BaseException {
        return sysLogService.insert(sysLog);
    }

}
