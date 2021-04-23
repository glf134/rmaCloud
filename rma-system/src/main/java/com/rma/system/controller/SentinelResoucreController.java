package com.rma.system.controller;

import com.rma.system.service.impl.SentinelServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glf
 */
@RestController
@Api(tags = "Demo API")
@RequestMapping("/demo/sentinel")
public class SentinelResoucreController {

    @Autowired
    private SentinelServiceImpl sentinelService;

    @GetMapping("/testXl")
    public void testXl() {
        sentinelService.doSomeThing1("testXl");
    }
    @GetMapping("/testRd")
    public void testRd() {
        sentinelService.doSomeThing2("testRd");
    }

}
