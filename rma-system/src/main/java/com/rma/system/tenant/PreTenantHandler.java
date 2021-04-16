package com.rma.system.tenant;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PreTenantHandler implements TenantHandler {

  @Autowired
  private PreTenantConfigProperties configProperties;

  /**
   * 租户Id
   *
   * @return
   */
  @Override
  public Expression getTenantId(boolean where) {
    //可以通过过滤器从请求中获取对应租户id 
    String tenantId = PreTenantContextHolder.getCurrentTenant();
    System.out.println(tenantId);
    if (tenantId == null) {
      return new NullValue();
    }
    return new LongValue(tenantId);
  }
  /**
   * 租户字段名
   *
   * @return
   */
  @Override
  public String getTenantIdColumn() {
    return configProperties.getColumn();
  }

  /**
   * 根据表名判断是否进行过滤
   * 忽略掉一些表：如租户表（sys_tenant）本身不需要执行这样的处理
   *
   * @param tableName
   * @return
   */
  @Override
  public boolean doTableFilter(String tableName) {

    String tenantId = PreTenantContextHolder.getCurrentTenant();
    // 租户中ID 为空，查询全部，不进行过滤
    if (tenantId == null) {
      return Boolean.TRUE;
    }

    return !configProperties.getTables().contains(tableName);

  }
}