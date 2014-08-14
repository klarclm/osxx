/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

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

import org.springframework.stereotype.Repository;

/**
 * Dao - 文章
 * 
 * @author Operate System
 * @version 3.0
 */
@Repository("storeDaoImpl")
public class StoreDaoImpl extends BaseDaoImpl<Store, Long> implements StoreDao {



}