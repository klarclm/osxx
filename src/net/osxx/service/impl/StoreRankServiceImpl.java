/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import net.osxx.dao.MemberRankDao;
import net.osxx.dao.StoreRankDao;
import net.osxx.entity.MemberRank;
import net.osxx.entity.StoreRank;
import net.osxx.service.MemberRankService;
import net.osxx.service.StoreRankService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 会员等级
 * 
 * @author Operate System
 * @version 3.0
 */
@Service("storeRankServiceImpl")
public class StoreRankServiceImpl extends BaseServiceImpl<StoreRank, Long> implements StoreRankService {

	@Resource(name = "storeRankDaoImpl")
	private StoreRankDao storeRankDao;

	@Resource(name = "storeRankDaoImpl")
	public void setBaseDao(StoreRankDao storeRankDao) {
		super.setBaseDao(storeRankDao);
	}

	@Transactional(readOnly = true)
	public StoreRank findDefault() {
		return storeRankDao.findDefault();
	}


}