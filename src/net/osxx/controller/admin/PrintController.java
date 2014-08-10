/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.admin;

import javax.annotation.Resource;

import net.osxx.entity.DeliveryCenter;
import net.osxx.entity.DeliveryTemplate;
import net.osxx.service.DeliveryCenterService;
import net.osxx.service.DeliveryTemplateService;
import net.osxx.service.OrderService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller - 打印
 * 
 * @author Operate System
 * @version 3.0
 */
@Controller("adminPrintController")
@RequestMapping("/admin/print")
public class PrintController extends BaseController {

	@Resource(name = "orderServiceImpl")
	private OrderService orderService;
	@Resource(name = "deliveryTemplateServiceImpl")
	private DeliveryTemplateService deliveryTemplateService;
	@Resource(name = "deliveryCenterServiceImpl")
	private DeliveryCenterService deliveryCenterService;

	/**
	 * 订单打印
	 */
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(Long id, ModelMap model) {
		model.addAttribute("order", orderService.find(id));
		return "/admin/print/order";
	}

	/**
	 * 购物单打印
	 */
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String product(Long id, ModelMap model) {
		model.addAttribute("order", orderService.find(id));
		return "/admin/print/product";
	}

	/**
	 * 配送单打印
	 */
	@RequestMapping(value = "/shipping", method = RequestMethod.GET)
	public String shipping(Long id, ModelMap model) {
		model.addAttribute("order", orderService.find(id));
		return "/admin/print/shipping";
	}

	/**
	 * 快递单打印
	 */
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	public String delivery(Long orderId, Long deliveryTemplateId, Long deliveryCenterId, ModelMap model) {
		DeliveryTemplate deliveryTemplate = deliveryTemplateService.find(deliveryTemplateId);
		DeliveryCenter deliveryCenter = deliveryCenterService.find(deliveryCenterId);
		if (deliveryTemplate == null) {
			deliveryTemplate = deliveryTemplateService.findDefault();
		}
		if (deliveryCenter == null) {
			deliveryCenter = deliveryCenterService.findDefault();
		}
		model.addAttribute("deliveryTemplates", deliveryTemplateService.findAll());
		model.addAttribute("deliveryCenters", deliveryCenterService.findAll());
		model.addAttribute("order", orderService.find(orderId));
		model.addAttribute("deliveryTemplate", deliveryTemplate);
		model.addAttribute("deliveryCenter", deliveryCenter);
		return "/admin/print/delivery";
	}

}