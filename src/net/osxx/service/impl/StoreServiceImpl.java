/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.osxx.Filter;
import net.osxx.Order;
import net.osxx.Page;
import net.osxx.Pageable;
import net.osxx.dao.ArticleDao;
import net.osxx.dao.StoreDao;
import net.osxx.entity.Article;
import net.osxx.entity.ArticleCategory;
import net.osxx.entity.Store;
import net.osxx.entity.Tag;
import net.osxx.service.ArticleService;
import net.osxx.service.StaticService;
import net.osxx.service.StoreService;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service - 文章
 * 
 * @author Operate System
 * @version 3.0
 */
@Service("storeServiceImpl")
public class StoreServiceImpl extends BaseServiceImpl<Store, Long> implements StoreService, DisposableBean {

	@Resource(name = "ehCacheManager")
	private CacheManager cacheManager;
	@Resource(name = "storeDaoImpl")
	private StoreDao storeDao;


	@Resource(name = "storeDaoImpl")
	public void setBaseDao(StoreDao storeDao) {
		super.setBaseDao(storeDao);
	}
	
	public void destroy() throws Exception {
	}

	@Override
	@Transactional
	@CacheEvict(value = { "store" }, allEntries = true)
	public void save(Store store) {
		Assert.notNull(store);
		super.save(store);
		storeDao.flush();
	}

	@Override
	@Transactional
	@CacheEvict(value = { "store" }, allEntries = true)
	public Store update(Store store) {
		Assert.notNull(store);
		Store pStore = super.update(store);
		storeDao.flush();
		return pStore;
	}

	@Override
	@Transactional
	@CacheEvict(value = { "store" }, allEntries = true)
	public Store update(Store store, String... ignoreProperties) {
		return super.update(store, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "store" }, allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "store" }, allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "store" }, allEntries = true)
	public void delete(Store store) {
		super.delete(store);
	}

}