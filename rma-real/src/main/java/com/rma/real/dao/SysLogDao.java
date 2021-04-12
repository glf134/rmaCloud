package com.rma.real.dao;


import com.rma.real.vo.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * 操作日志数据访问接口
 * @author glf
 */
@Mapper
public interface SysLogDao extends RowMapper<SysLog> {

    /**
     * 新增
     * @param sysLog
     * @return
     */
    int insert(SysLog sysLog);

    /**
     * 修改
     * @param sysLog
     * @return
     */
    int updateByPrimaryKey(SysLog sysLog);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from sys_log where id = #{id}")
    int deleteByPrimaryKey(String id);

    /**
     * 删除所有
     * @return
     */
    @Delete("delete from sys_log ")
    int deleteAll();

    /**
     * 获取所有
     * @return
     */
    List<SysLog> selectAll();

}
