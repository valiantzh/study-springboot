package com.study.common.db;

import com.study.common.util.StringUtil;

/**
 * 多数据源枚举
 * @author valiantzh
 * @version 1.0
 */
public enum DataSourceEnum {

	// 主库
	MASTER("masterDataSource", true),
	// 从库
	SLAVE("slaveDataSource", false),;

	// 数据源名称
	private String name;
	// 是否是默认数据源
	private boolean master;

	DataSourceEnum(String name, boolean master) {
		this.name = name;
		this.master = master;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public String getDefault() {
		String defaultDataSource = "";
		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
			if (StringUtil.isNotBlank(defaultDataSource)) {//!"".equals(defaultDataSource)
				break;
			}
			if (dataSourceEnum.master) {
				defaultDataSource = dataSourceEnum.getName();
			}
		}
		return defaultDataSource;
	}

}
