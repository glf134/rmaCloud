package com.rma.system.service;


import com.ram.common.exception.BaseException;
import com.ram.common.result.Result;
import com.rma.system.vo.SysLog;

/**
 * @author glf
 */
public interface SysLogService {

    /**
     * 新增
     * @param sysLog
     * @return
     */
    Result insert(SysLog sysLog) throws BaseException;


}
