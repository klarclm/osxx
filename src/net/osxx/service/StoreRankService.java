/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.service;

import java.math.BigDecimal;

import net.osxx.entity.MemberRank;
import net.osxx.entity.StoreRank;

/**
 * Service - 会员等级
 * 
 * @author Operate System
 * @version 3.0
 */
public interface StoreRankService extends BaseService<StoreRank, Long> {

	/**
	 * 查找默认会员等级
	 * 
	 * @return 默认会员等级，若不存在则返回null
	 */
	StoreRank findDefault();

}