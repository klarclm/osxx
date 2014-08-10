/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.service;

import net.osxx.Page;
import net.osxx.Pageable;
import net.osxx.entity.Deposit;
import net.osxx.entity.Member;

/**
 * Service - 预存款
 * 
 * @author Operate System
 * @version 3.0
 */
public interface DepositService extends BaseService<Deposit, Long> {

	/**
	 * 查找预存款分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 预存款分页
	 */
	Page<Deposit> findPage(Member member, Pageable pageable);

}