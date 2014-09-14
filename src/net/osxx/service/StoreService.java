/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.service;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;

import net.osxx.Filter;
import net.osxx.Order;
import net.osxx.Page;
import net.osxx.Pageable;
import net.osxx.entity.Article;
import net.osxx.entity.ArticleCategory;
import net.osxx.entity.Store;
import net.osxx.entity.Tag;

/**
 * Service - 文章
 * 
 * @author Operate System
 * @version 3.0
 */
public interface StoreService extends BaseService<Store, Long> {
	public void save(Store store) ;

}