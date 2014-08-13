/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import net.osxx.dao.StoreRankDao;
import net.osxx.dao.StoreRankDao;
import net.osxx.entity.StoreRank;
import net.osxx.entity.Product;
import net.osxx.entity.StoreRank;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Dao - 会员等级
 * 
 * @author Operate System
 * @version 3.0
 */
@Repository("storeRankDaoImpl")
public class StoreRankDaoImpl extends BaseDaoImpl<StoreRank, Long> implements StoreRankDao {


	public StoreRank findDefault() {
		try {
			String jpql = "select Rank from StoreRank storeRank where storeRank.isDefault = true";
			return entityManager.createQuery(jpql, StoreRank.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	/**
	 * 处理默认并保存
	 * 
	 * @param storeRank
	 *            会员等级
	 */
	@Override
	public void persist(StoreRank storeRank) {
		Assert.notNull(storeRank);
		if (storeRank.getIsDefault()) {
			String jpql = "update StoreRank storeRank set storeRank.isDefault = false where storeRank.isDefault = true";
			entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
		}
		super.persist(storeRank);
	}

	/**
	 * 处理默认并更新
	 * 
	 * @param storeRank
	 *            会员等级
	 * @return 会员等级
	 */
	@Override
	public StoreRank merge(StoreRank storeRank) {
		Assert.notNull(storeRank);
		if (storeRank.getIsDefault()) {
			String jpql = "update StoreRank storeRank set storeRank.isDefault = false where storeRank.isDefault = true and storeRank != :storeRank";
			entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("storeRank", storeRank).executeUpdate();
		}
		return super.merge(storeRank);
	}

	/**
	 * 忽略默认、清除会员价并删除
	 * 
	 * @param storeRank
	 *            会员等级
	 */
	@Override
	public void remove(StoreRank storeRank) {
		if (storeRank != null && !storeRank.getIsDefault()) {
			String jpql = "select product from Product product join product.memberPrice memberPrice where index(memberPrice) = :storeRank";
			List<Product> products = entityManager.createQuery(jpql, Product.class).setFlushMode(FlushModeType.COMMIT).setParameter("storeRank", storeRank).getResultList();
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				product.getMemberPrice().remove(storeRank);
				if (i % 20 == 0) {
					super.flush();
					super.clear();
				}
			}
			super.remove(super.merge(storeRank));
		}
	}

}