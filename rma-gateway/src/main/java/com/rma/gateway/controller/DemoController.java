package com.rma.gateway.controller;

import com.ram.common.LogAnnotation;
import com.ram.common.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/dd")
    @ApiOperation(value = "分页获取映射")
    @LogAnnotation(module = "rma-real", recordRequestParam = false)
    public String selectPage()  throws BaseException {
        return file;
    }
}
