package com.rma.system.tenant;

import org.springframework.stereotype.Component;

@Component
public class PreTenantContextHolder {

	public static final String DEFAULT_TENANT = "default";
	private static final InheritableThreadLocal<String> DATASOURCE_HOLDER = new InheritableThreadLocal<String>(){
		@Override
		protected String initialValue() {
			return DEFAULT_TENANT;
		}
	};

	//获取当前数据源配置租户标识
	public static String getCurrentTenant() {
		return DATASOURCE_HOLDER.get();
	}
	//设置当前数据源配置租户标识
	public static void setCurrentTanent(String tenant) {
		DATASOURCE_HOLDER.set(tenant);
	}
	public static void clear() {
		DATASOURCE_HOLDER.remove();
	}
}