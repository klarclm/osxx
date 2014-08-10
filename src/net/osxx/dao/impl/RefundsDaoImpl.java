/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.dao.impl;

import net.osxx.dao.RefundsDao;
import net.osxx.entity.Refunds;

import org.springframework.stereotype.Repository;

/**
 * Dao - 退款单
 * 
 * @author Operate System
 * @version 3.0
 */
@Repository("refundsDaoImpl")
public class RefundsDaoImpl extends BaseDaoImpl<Refunds, Long> implements RefundsDao {

}