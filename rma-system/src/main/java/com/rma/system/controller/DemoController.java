package com.rma.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ram.common.LogAnnotation;
import com.ram.common.exception.BaseException;
import com.ram.common.result.Result;
import com.rma.system.dao.SysLogDao;
import com.rma.system.feign.RealServiceFeign;
import com.rma.system.utils.MinioUtils;
import com.rma.system.vo.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private SysLogDao sysLogDao;

    @Autowired
    private RealServiceFeign realServiceFeign;
    @Autowired
    private MinioUtils minioUtils;

    @GetMapping("/tenant")
    @ApiOperation(value = "测试租户数据过滤")
    @LogAnnotation(module = "model-parent", recordRequestParam = false)
    public Result selectTenant()  throws BaseException {
        Result result = new Result();
        Page<SysLog> page = new Page<SysLog>(1, 5);
        QueryWrapper<SysLog> wrapper=new QueryWrapper<SysLog>();
        result.setData(sysLogDao.selectPage(page,wrapper));
        return result;
    }
    @GetMapping("/data")
    @ApiOperation(value = "测试数据权限过滤")
    @LogAnnotation(module = "model-parent", recordRequestParam = false)
    public Result selectDataPage()  throws BaseException {
        Result result = new Result();
        Page<SysLog> page = new Page<SysLog>(1, 5);
        result.setData(sysLogDao.selectPageBySysLog(page,null));
        return result;
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
//    @GetMapping("/transDemo")
//    @ApiOperation(value = "测试分布式事务")
//    @LogAnnotation(module = "model-parent", recordRequestParam = false)
////    @Transactional
////    @LcnTransaction
//    @GlobalTransactional
//    public String transDemo()  throws BaseException {
//        SysLog sysLog = new SysLog();
//        sysLog.setId(UUIDUtils.getGUID());
//        sysLog.setCreateTime(new Date(System.currentTimeMillis()));
//        sysLog.setFlag(true);
//        sysLog.setUsername("123");
//        sysLog.setModule("456");
//        sysLogService.insert(sysLog);
//        if(realServiceFeign.updateSysLog(JSON.parseObject(JSON.toJSONString(sysLog)))){
//            return "分布式事务调用成功";
//        };
//        return "测试资源访问";
//    }
}
