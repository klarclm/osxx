/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.admin;

import javax.annotation.Resource;

import net.osxx.Message;
import net.osxx.Pageable;
import net.osxx.entity.Review;
import net.osxx.entity.Review.Type;
import net.osxx.service.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 评论
 * 
 * @author Operate System
 * @version 3.0
 */
@Controller("adminReviewController")
@RequestMapping("/admin/review")
public class ReviewController extends BaseController {

	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("review", reviewService.find(id));
		return "/admin/review/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Long id, @RequestParam(defaultValue = "false") Boolean isShow, RedirectAttributes redirectAttributes) {
		Review review = reviewService.find(id);
		if (review == null) {
			return ERROR_VIEW;
		}
		review.setIsShow(isShow);
		reviewService.update(review);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Type type, Pageable pageable, ModelMap model) {
		model.addAttribute("type", type);
		model.addAttribute("types", Type.values());
		model.addAttribute("page", reviewService.findPage(null, null, type, null, pageable));
		return "/admin/review/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		reviewService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}