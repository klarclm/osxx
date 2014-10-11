/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.shop.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.osxx.controller.shop.BaseController;
import net.osxx.entity.Member;
import net.osxx.entity.Role;
import net.osxx.entity.Store;
import net.osxx.service.ConsultationService;
import net.osxx.service.CouponCodeService;
import net.osxx.service.MemberService;
import net.osxx.service.MessageService;
import net.osxx.service.OrderService;
import net.osxx.service.ProductNotifyService;
import net.osxx.service.ProductService;
import net.osxx.service.ReviewService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Controller - 会员中心
 * 
 * @author Operate System
 * @version 3.0
 */
@Controller("shopMemberController")
@RequestMapping("/member")
public class MemberController extends BaseController {

	/** 最新订单数 */
	private static final int NEW_ORDER_COUNT = 6;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "orderServiceImpl")
	private OrderService orderService;
	@Resource(name = "couponCodeServiceImpl")
	private CouponCodeService couponCodeService;
	@Resource(name = "messageServiceImpl")
	private MessageService messageService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;
	@Resource(name = "productNotifyServiceImpl")
	private ProductNotifyService productNotifyService;
	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;
	@Resource(name = "consultationServiceImpl")
	private ConsultationService consultationService;

	/**
	 * 首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Integer pageNumber, ModelMap model) {
		Member member = memberService.getCurrent();
		model.addAttribute("waitingPaymentOrderCount", orderService.waitingPaymentCount(member));
		model.addAttribute("waitingShippingOrderCount", orderService.waitingShippingCount(member));
		model.addAttribute("messageCount", messageService.count(member, false));
		model.addAttribute("couponCodeCount", couponCodeService.count(null, member, null, false, false));
		model.addAttribute("favoriteCount", productService.count(member, null, null, null, null, null, null));
		model.addAttribute("productNotifyCount", productNotifyService.count(member, null, null, null));
		model.addAttribute("reviewCount", reviewService.count(member, null, null, null));
		model.addAttribute("consultationCount", consultationService.count(member, null, null));
		model.addAttribute("newOrders", orderService.findList(member, NEW_ORDER_COUNT, null, null));
		return "shop/member/index";
	}


}