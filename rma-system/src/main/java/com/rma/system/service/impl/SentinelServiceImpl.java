package com.rma.system.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ram.common.exception.BaseException;
import org.springframework.stereotype.Service;

/**
 * @author glf
 */
@Service
public class SentinelServiceImpl {

    @SentinelResource(value = "doSomeThing1",blockHandler = "exceptionHandler")
    public void doSomeThing1(String str) throws BaseException {
        System.out.println(str);
    }
    // 限流与阻塞处理  该函数的传参必须与资源点的传参一样
    public void exceptionHandler(String str, BlockException ex) {
        System.out.println("限流===处理" + str);
    }

    @SentinelResource(value = "doSomeThing2",fallback  = "fallbackHandler")
    public void doSomeThing2(String str) throws BaseException {
        System.out.println(str);
        System.out.println(1/0);
    }
    // 限流与阻塞处理  该函数的传参必须与资源点的传参一样
    public void fallbackHandler(String str) {
        System.out.println("熔断与降级===处理" + str);
    }
}
