/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.admin;

import java.util.Iterator;

import javax.annotation.Resource;

import net.osxx.Message;
import net.osxx.Pageable;
import net.osxx.entity.Parameter;
import net.osxx.entity.ParameterGroup;
import net.osxx.service.ParameterGroupService;
import net.osxx.service.ProductCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 参数
 * 
 * @author Operate System
 * @version 3.0
 */
@Controller("adminParameterGroupController")
@RequestMapping("/admin/parameter_group")
public class ParameterGroupController extends BaseController {

	@Resource(name = "parameterGroupServiceImpl")
	private ParameterGroupService parameterGroupService;
	@Resource(name = "productCategoryServiceImpl")
	private ProductCategoryService productCategoryService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("productCategoryTree", productCategoryService.findTree());
		return "/admin/parameter_group/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ParameterGroup parameterGroup, Long productCategoryId, RedirectAttributes redirectAttributes) {
		for (Iterator<Parameter> iterator = parameterGroup.getParameters().iterator(); iterator.hasNext();) {
			Parameter parameter = iterator.next();
			if (parameter == null || parameter.getName() == null) {
				iterator.remove();
			} else {
				parameter.setParameterGroup(parameterGroup);
			}
		}
		parameterGroup.setProductCategory(productCategoryService.find(productCategoryId));
		if (!isValid(parameterGroup)) {
			return ERROR_VIEW;
		}
		parameterGroupService.save(parameterGroup);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("parameterGroup", parameterGroupService.find(id));
		model.addAttribute("productCategoryTree", productCategoryService.findTree());
		return "/admin/parameter_group/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ParameterGroup parameterGroup, Long productCategoryId, RedirectAttributes redirectAttributes) {
		for (Iterator<Parameter> iterator = parameterGroup.getParameters().iterator(); iterator.hasNext();) {
			Parameter parameter = iterator.next();
			if (parameter == null || parameter.getName() == null) {
				iterator.remove();
			} else {
				parameter.setParameterGroup(parameterGroup);
			}
		}
		parameterGroup.setProductCategory(productCategoryService.find(productCategoryId));
		if (!isValid(parameterGroup)) {
			return ERROR_VIEW;
		}
		parameterGroupService.update(parameterGroup);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", parameterGroupService.findPage(pageable));
		return "/admin/parameter_group/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		parameterGroupService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}