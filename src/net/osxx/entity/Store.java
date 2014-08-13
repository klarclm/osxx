/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 店铺
 * 
 * @author Operate System
 * @version 3.0
 */
@Entity
@Table(name = "xx_store")
public class Store extends BaseEntity  {

	private static final long serialVersionUID = -6950317719006603385L;
	
	// Fields
	/** 店铺名字*//** 店主id*//** 店铺logo*//** 店铺等级*//** 店铺页面展示内容*//** 店铺能提供的产品服务范畴1-5*//** 店铺所在地*/
	/** 服务评价*//** 质量评级*//** 速度物流评价*/
	private String name;
	private Long owneridXxmember;
	private String logo;
	private Long rank;
	private String pageshowcontent;
	private Long productcategoryid1Xxproductcategory;
	private Long productcategoryid2Xxproductcategory;
	private Long productcategoryid3Xxproductcategory;
	private Long productcategoryid4Xxproductcategory;
	private Long productcategoryid5Xxproductcategory;
	private Long areaidXxarea;
	private Long reviewservice;
	private Long reviewquality;
	private Long reviewspeed;
	private String modifycolumnname;


	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ownerid_xxmember", nullable = false)
	public Long getOwneridXxmember() {
		return this.owneridXxmember;
	}

	public void setOwneridXxmember(Long owneridXxmember) {
		this.owneridXxmember = owneridXxmember;
	}

	@Column(name = "logo")
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "rank")
	public Long getRank() {
		return this.rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	@Column(name = "pageshowcontent")
	@Basic(fetch = FetchType.LAZY)
	public String getPageshowcontent() {
		return this.pageshowcontent;
	}

	public void setPageshowcontent(String pageshowcontent) {
		this.pageshowcontent = pageshowcontent;
	}

	@Column(name = "productcategoryid1_xxproductcategory")
	public Long getProductcategoryid1Xxproductcategory() {
		return this.productcategoryid1Xxproductcategory;
	}

	public void setProductcategoryid1Xxproductcategory(
			Long productcategoryid1Xxproductcategory) {
		this.productcategoryid1Xxproductcategory = productcategoryid1Xxproductcategory;
	}

	@Column(name = "productcategoryid2_xxproductcategory")
	public Long getProductcategoryid2Xxproductcategory() {
		return this.productcategoryid2Xxproductcategory;
	}

	public void setProductcategoryid2Xxproductcategory(
			Long productcategoryid2Xxproductcategory) {
		this.productcategoryid2Xxproductcategory = productcategoryid2Xxproductcategory;
	}

	@Column(name = "productcategoryid3_xxproductcategory")
	public Long getProductcategoryid3Xxproductcategory() {
		return this.productcategoryid3Xxproductcategory;
	}

	public void setProductcategoryid3Xxproductcategory(
			Long productcategoryid3Xxproductcategory) {
		this.productcategoryid3Xxproductcategory = productcategoryid3Xxproductcategory;
	}

	@Column(name = "productcategoryid4_xxproductcategory")
	public Long getProductcategoryid4Xxproductcategory() {
		return this.productcategoryid4Xxproductcategory;
	}

	public void setProductcategoryid4Xxproductcategory(
			Long productcategoryid4Xxproductcategory) {
		this.productcategoryid4Xxproductcategory = productcategoryid4Xxproductcategory;
	}

	@Column(name = "productcategoryid5_xxproductcategory")
	public Long getProductcategoryid5Xxproductcategory() {
		return this.productcategoryid5Xxproductcategory;
	}

	public void setProductcategoryid5Xxproductcategory(
			Long productcategoryid5Xxproductcategory) {
		this.productcategoryid5Xxproductcategory = productcategoryid5Xxproductcategory;
	}

	@Column(name = "areaid_xxarea")
	public Long getAreaidXxarea() {
		return this.areaidXxarea;
	}

	public void setAreaidXxarea(Long areaidXxarea) {
		this.areaidXxarea = areaidXxarea;
	}

	@Column(name = "reviewservice")
	public Long getReviewservice() {
		return this.reviewservice;
	}

	public void setReviewservice(Long reviewservice) {
		this.reviewservice = reviewservice;
	}

	@Column(name = "reviewquality")
	public Long getReviewquality() {
		return this.reviewquality;
	}

	public void setReviewquality(Long reviewquality) {
		this.reviewquality = reviewquality;
	}

	@Column(name = "reviewspeed")
	public Long getReviewspeed() {
		return this.reviewspeed;
	}

	public void setReviewspeed(Long reviewspeed) {
		this.reviewspeed = reviewspeed;
	}


	@Column(name = "modifycolumnname")
	public String getModifycolumnname() {
		return this.modifycolumnname;
	}

	public void setModifycolumnname(String modifycolumnname) {
		this.modifycolumnname = modifycolumnname;
	}

}
