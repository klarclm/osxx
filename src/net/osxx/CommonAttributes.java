/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx;

/**
 * 公共参数
 * 
 * @author Operate System
 * @version 3.0
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** osxx.xml文件路径 */
	public static final String SHOPXX_XML_PATH = "/osxx.xml";

	/** osxx.properties文件路径 */
	public static final String SHOPXX_PROPERTIES_PATH = "/osxx.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}