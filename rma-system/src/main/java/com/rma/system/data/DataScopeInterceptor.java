package com.rma.system.data;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author glf
 * @since 2020-05-07
 */

/**
 *
 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。
 * 利用拦截器实现Mybatis分页的原理：
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句
 * 是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。在Mybatis中Statement语句是通过RoutingStatementHandler对象的
 * prepare方法生成的。所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用
 * StatementHandler对象的prepare方法，即调用invocation.proceed()。
 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设
 * 置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。
 *
**/
//@Component
@AllArgsConstructor
//@Intercepts({ @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class, Integer.class})})
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {

    private static final Logger LOG = LoggerFactory.getLogger(DataScopeInterceptor.class);

    private DataSource dataSource;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 1.获取执行的RoutingStatementHandler
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        // 2.获取MetaObject
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        this.sqlParser(metaObject);

        // 3.获取MappedStatement, 用于判定方法上的注解
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 4.MappedStatement的ID即为Mapper方法的全路径名
        String methodId = ms.getId();
        LOG.info("mapper method: {}", methodId);
        // 5.获取Mapper的Class名称
        String clazzName = methodId.substring(0, methodId.lastIndexOf("."));
        // 6.获取拦截的方法名
        String methodName = methodId.substring(methodId.lastIndexOf(".") + 1);
        LOG.info("clazzName: {}, methodName: {}", clazzName, methodName);
        // 7.反射获取方法上的注解内容
        Method[] methods = Class.forName(clazzName).getDeclaredMethods();
        DataScope dataScope = null;
        for (Method md : methods) {
            if (methodName.equalsIgnoreCase(md.getName())) {
                dataScope = md.getAnnotation(DataScope.class);
            }
        }
        if (dataScope == null) {
            return invocation.proceed();
        }
        String type = dataScope.type();
        String column = dataScope.column();
        if (StringUtils.isAnyEmpty(type, column)) {
            return invocation.proceed();
        }
        //动态获取用户权限
        List<String> values = new ArrayList<String>();

        values.add("admin");

        // 8.获取原始执行的SQL
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        sql = sql.replaceAll("\\n", "").replaceAll("\\t", "");
        LOG.info("original SQL: {}", sql);
        // 9.根据注解内容修改SQL后执行
        if ("DEPT".equals(type)) {
            String newSql = "SELECT * FROM (" + sql + ") temp_data_scope WHERE temp_data_scope." + column + " in ("+CollectionUtil.join(values, ",","'","'")+")";
            LOG.info("new SQL: {}", newSql);
            metaObject.setValue("delegate.boundSql.sql", newSql);
        }
        return invocation.proceed();
    }
    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 查找参数是否包括DataScope对象
     *
     * @param parameterObj 参数列表
     * @return DataScope
     */
    private DataScope findDataScopeObject(Object parameterObj) {
        if (parameterObj instanceof DataScope) {
            return (DataScope) parameterObj;
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof DataScope) {
                    return (DataScope) val;
                }
            }
        }
        return null;
    }
}
