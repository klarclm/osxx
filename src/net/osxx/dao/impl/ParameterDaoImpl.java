/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.osxx.dao.ParameterDao;
import net.osxx.entity.Parameter;
import net.osxx.entity.ParameterGroup;

import org.springframework.stereotype.Repository;

/**
 * Dao - 参数
 * 
 * @author Operate System
 * @version 3.0
 */
@Repository("parameterDaoImpl")
public class ParameterDaoImpl extends BaseDaoImpl<Parameter, Long> implements ParameterDao {

	public List<Parameter> findList(ParameterGroup parameterGroup, Set<Parameter> excludes) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Parameter> criteriaQuery = criteriaBuilder.createQuery(Parameter.class);
		Root<Parameter> root = criteriaQuery.from(Parameter.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (parameterGroup != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("parameterGroup"), parameterGroup));
		}
		if (excludes != null && !excludes.isEmpty()) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.not(root.in(excludes)));
		}
		criteriaQuery.where(restrictions);
		return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
	}

}