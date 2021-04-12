package com.rma.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rma.system.vo.SysLog;
import org.apache.ibatis.annotations.Mapper;


/**
 * 操作日志数据访问接口
 * @author glf
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLog> {



}
