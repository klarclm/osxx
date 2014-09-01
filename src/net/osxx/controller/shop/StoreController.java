/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.shop;

import javax.annotation.Resource;

import net.osxx.Pageable;
import net.osxx.ResourceNotFoundException;
import net.osxx.entity.ArticleCategory;
import net.osxx.entity.Store;
import net.osxx.service.ArticleCategoryService;
import net.osxx.service.ArticleService;
import net.osxx.service.SearchService;
<<<<<<< HEAD
=======
import net.osxx.service.StoreService;
>>>>>>> origin/master

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 文章
 * 
 * @author Operate System
 * @version 3.0
 */
@Controller("shopStoreController")
@RequestMapping("/store")
public class StoreController extends BaseController {

<<<<<<< HEAD
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Store store,  ModelMap model) {
		
=======
	/** 每页记录数 */
	private static final int PAGE_SIZE = 20;
	
	@Resource(name = "storeServiceImpl")
	private StoreService storeService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Store store, ModelMap model) {
		ArticleCategory articleCategory = articleCategoryService.find(id);
		if (articleCategory == null) {
			throw new ResourceNotFoundException();
		}
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("articleCategory", articleCategory);
		model.addAttribute("page", articleService.findPage(articleCategory, null, pageable));
>>>>>>> origin/master
		return "/shop/store/add";
	}

}