package com.rma.system.service.impl;


import com.ram.common.result.Result;
import com.ram.common.utils.UUIDUtils;
import com.rma.system.dao.SysLogDao;
import com.rma.system.service.SysLogService;
import com.rma.system.vo.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author glf
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public Result insert(SysLog sysLog)  {
        Result result = new Result();
        sysLog.setId(UUIDUtils.getGUID32());
        sysLog.setCreateTime(new Date());
        int num = sysLogDao.insert(sysLog);
        if(num > 0) {
            result.setCode("200");
        }
        return result;
    }


}
