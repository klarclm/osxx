/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.shop;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.osxx.Message;
import net.osxx.Pageable;
import net.osxx.ResourceNotFoundException;
import net.osxx.entity.ArticleCategory;
import net.osxx.entity.Product;
import net.osxx.entity.Store;
import net.osxx.service.ArticleCategoryService;
import net.osxx.service.ArticleService;
import net.osxx.service.SearchService;
import net.osxx.service.StoreService;



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

	@Resource(name = "storeServiceImpl")
	private StoreService storeService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Store store, ModelMap model) {

		return "/shop/store/add";
	}
	
	@RequestMapping(value = "/store_item_add")
	public @ResponseBody
	Map<String, Object> storeItemAdd(Store store) {
		Map<String, Object> data = new HashMap<String, Object>();
        storeService.save(store);
		data.put("message", SUCCESS_MESSAGE);
		return data;
	}

}