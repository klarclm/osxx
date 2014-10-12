/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.controller.shop;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.osxx.Setting;
import net.osxx.entity.Area;
import net.osxx.entity.Member;
import net.osxx.entity.Role;
import net.osxx.service.AreaService;
import net.osxx.service.CaptchaService;
import net.osxx.service.MemberService;
import net.osxx.service.RSAService;
import net.osxx.service.impl.MemberServiceImpl;
import net.osxx.util.SettingUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 共用
 * 
 * @author Operate System
 * @version 3.0
 */
@Controller("shopCommonController")
@RequestMapping("/common")
public class CommonController {

	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	/**
	 * 网站关闭
	 */
	@RequestMapping("/site_close")
	public String siteClose() {
		Setting setting = SettingUtils.get();
		if (setting.getIsSiteEnabled()) {
			return "redirect:/";
		} else {
			return "/shop/common/site_close";
		}
	}

	/**
	 * 公钥
	 */
	@RequestMapping(value = "/public_key", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> publicKey(HttpServletRequest request) {
		RSAPublicKey publicKey = rsaService.generateKey(request);
		Map<String, String> data = new HashMap<String, String>();
		data.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
		data.put("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
		return data;
	}

	/**
	 * 地区
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody
	Map<Long, String> area(Long parentId) {
		List<Area> areas = new ArrayList<Area>();
		Area parent = areaService.find(parentId);
		if (parent != null) {
			areas = new ArrayList<Area>(parent.getChildren());
		} else {
			areas = areaService.findRoots();
		}
		Map<Long, String> options = new HashMap<Long, String>();
		for (Area area : areas) {
			options.put(area.getId(), area.getName());
		}
		return options;
	}

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(captchaId)) {
			captchaId = request.getSession().getId();
		}
		String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
		String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
		response.addHeader(pragma, value);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			BufferedImage bufferedImage = captchaService.buildImage(captchaId);
			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(servletOutputStream);
		}
	}

	/**
	 * 错误提示
	 */
	@RequestMapping("/error")
	public String error() {
		return "/shop/common/error";
	}

	/**
	 * 资源不存在
	 */
	@RequestMapping("/resource_not_found")
	public String resourceNotFound() {
		return "/shop/common/resource_not_found";
	}
	
	@RequestMapping(value = "/getMemberState", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getMemberState() {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			Member user = memberService.getCurrent();
			if(user != null){
				data.put("isAuthenticated", true);
				data.put("memberusername", user.getUsername());
				Subject userSubject = SecurityUtils.getSubject();
				if(userSubject.hasRole(Role.ROLE_NORMALSTOREMANAGER) || userSubject.hasRole(Role.ROLE_SUPERSTOREMANAGER)){
					data.put("isStoreOwner",true);
				}else{
					data.put("isStoreOwner", false);
				}
			}else{
				data.put("isAuthenticated", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}