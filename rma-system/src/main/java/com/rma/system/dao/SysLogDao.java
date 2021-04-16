package com.rma.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rma.system.data.DataScope;
import com.rma.system.vo.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 * 操作日志数据访问接口
 * @author glf
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLog> {

//    @SqlParser(filter = true)
    @DataScope(type = "DEPT", column = "username")
    IPage<SysLog> selectPageBySysLog(IPage<SysLog> page, @Param("m") Map<String, Object> queryMap);

}
