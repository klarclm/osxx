/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.service.impl;

import javax.annotation.Resource;

import net.osxx.dao.PluginConfigDao;
import net.osxx.entity.PluginConfig;
import net.osxx.service.PluginConfigService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 插件配置
 * 
 * @author Operate System
 * @version 3.0
 */
@Service("pluginConfigServiceImpl")
public class PluginConfigServiceImpl extends BaseServiceImpl<PluginConfig, Long> implements PluginConfigService {

	@Resource(name = "pluginConfigDaoImpl")
	private PluginConfigDao pluginConfigDao;

	@Resource(name = "pluginConfigDaoImpl")
	public void setBaseDao(PluginConfigDao pluginConfigDao) {
		super.setBaseDao(pluginConfigDao);
	}

	@Transactional(readOnly = true)
	public boolean pluginIdExists(String pluginId) {
		return pluginConfigDao.pluginIdExists(pluginId);
	}

	@Transactional(readOnly = true)
	public PluginConfig findByPluginId(String pluginId) {
		return pluginConfigDao.findByPluginId(pluginId);
	}

}