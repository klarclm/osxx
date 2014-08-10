/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.dao.impl;

import net.osxx.dao.AdPositionDao;
import net.osxx.entity.AdPosition;

import org.springframework.stereotype.Repository;

/**
 * Dao - 广告位
 * 
 * @author Operate System
 * @version 3.0
 */
@Repository("adPositionDaoImpl")
public class AdPositionDaoImpl extends BaseDaoImpl<AdPosition, Long> implements AdPositionDao {

}