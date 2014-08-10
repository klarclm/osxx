/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx.service;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;

/**
 * Service - RSA安全
 * 
 * @author Operate System
 * @version 3.0
 */
public interface RSAService {

	/**
	 * 生成密钥(添加私钥至Session并返回公钥)
	 * 
	 * @param request
	 *            httpServletRequest
	 * @return 公钥
	 */
	RSAPublicKey generateKey(HttpServletRequest request);

	/**
	 * 移除私钥
	 * 
	 * @param request
	 *            httpServletRequest
	 */
	void removePrivateKey(HttpServletRequest request);

	/**
	 * 解密参数
	 * 
	 * @param name
	 *            参数名称
	 * @param request
	 *            httpServletRequest
	 * @return 解密内容
	 */
	String decryptParameter(String name, HttpServletRequest request);

}