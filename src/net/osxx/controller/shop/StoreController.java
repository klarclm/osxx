/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.shop;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.osxx.CommonAttributes;
import net.osxx.Message;
import net.osxx.Pageable;
import net.osxx.Principal;
import net.osxx.ResourceNotFoundException;
import net.osxx.Setting;
import net.osxx.Setting.CaptchaType;
import net.osxx.entity.Area;
import net.osxx.entity.ArticleCategory;
import net.osxx.entity.Attribute;
import net.osxx.entity.Brand;
import net.osxx.entity.Cart;
import net.osxx.entity.Member;
import net.osxx.entity.MemberAttribute;
import net.osxx.entity.Product;
import net.osxx.entity.Promotion;
import net.osxx.entity.Role;
import net.osxx.entity.Store;
import net.osxx.entity.Tag;
import net.osxx.entity.BaseEntity.Save;
import net.osxx.entity.Member.Gender;
import net.osxx.entity.MemberAttribute.Type;
import net.osxx.service.ArticleCategoryService;
import net.osxx.service.ArticleService;
import net.osxx.service.CaptchaService;
import net.osxx.service.MemberService;
import net.osxx.service.SearchService;
import net.osxx.service.StoreService;
import net.osxx.util.SettingUtils;
import net.osxx.util.WebUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.omg.CORBA.DATA_CONVERSION;
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

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Store store, ModelMap model) {
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/shop/store/add";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(ModelMap model) {
		
		Member user = memberService.getCurrent();
		Long userId = user.getId();
		Store userStore = storeService.find(userId);
		if (userStore != null) {
			model.addAttribute("user", user);
			model.addAttribute("userStore", userStore);
			return "/shop/store/show";
		}else {
			return "redirect:/store_item_add";
		}
		
		
	}

	@RequestMapping(value = "/store_item_add", method = RequestMethod.POST)
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> storeItemAdd(Store store, String captchaId, String captcha) {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			Member user = memberService.getCurrent();
			store.setOwneridXxmember(user.getId());
			store.setCreateDate(new Date());
			storeService.save(store);

			user.setStoreid_xxstore(store.getId());
			user.setRoleId(Role.ROLE_NORMALSTOREMANAGER);
			memberService.update(user);

			data.put("sn", "111");
			data.put("message", SUCCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}


}