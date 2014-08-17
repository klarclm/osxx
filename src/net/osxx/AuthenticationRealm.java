/*
 * Copyright 2005-2013 klarclm.com. All rights reserved.
 * Support: http://www.klarclm.com
 * License: http://www.klarclm.com/license
 */
package net.osxx;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.osxx.Setting.AccountLockType;
import net.osxx.Setting.CaptchaType;
import net.osxx.entity.Admin;
import net.osxx.entity.Member;
import net.osxx.service.AdminService;
import net.osxx.service.CaptchaService;
import net.osxx.service.MemberService;
import net.osxx.util.SettingUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 权限认证
 * 
 * @author Operate System
 * @version 3.0
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	// 将member纳入到shiro框架
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	/**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String captchaId = authenticationToken.getCaptchaId();
		String captcha = authenticationToken.getCaptcha();
		String ip = authenticationToken.getHost();
		if (!captchaService.isValid(CaptchaType.adminLogin, captchaId, captcha)) {
			throw new UnsupportedTokenException();
		}
		if (username != null && password != null) {
			Admin admin = adminService.findByUsername(username);
			Member member = memberService.findByUsername(username);
			if (admin == null && member == null) {
					throw new UnknownAccountException();
			}
			if(admin != null){
				if (!admin.getIsEnabled()) {
					throw new DisabledAccountException();
				}
				Setting setting = SettingUtils.get();
				if (admin.getIsLocked()) {
					if (ArrayUtils.contains(setting.getAccountLockTypes(),
							AccountLockType.admin)) {
						int loginFailureLockTime = setting.getAccountLockTime();
						if (loginFailureLockTime == 0) {
							throw new LockedAccountException();
						}
						Date lockedDate = admin.getLockedDate();
						Date unlockDate = DateUtils.addMinutes(lockedDate,
								loginFailureLockTime);
						if (new Date().after(unlockDate)) {
							admin.setLoginFailureCount(0);
							admin.setIsLocked(false);
							admin.setLockedDate(null);
							adminService.update(admin);
						} else {
							throw new LockedAccountException();
						}
					} else {
						admin.setLoginFailureCount(0);
						admin.setIsLocked(false);
						admin.setLockedDate(null);
						adminService.update(admin);
					}
				}
				if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
					int loginFailureCount = admin.getLoginFailureCount() + 1;
					if (loginFailureCount >= setting.getAccountLockCount()) {
						admin.setIsLocked(true);
						admin.setLockedDate(new Date());
					}
					admin.setLoginFailureCount(loginFailureCount);
					adminService.update(admin);
					throw new IncorrectCredentialsException();
				}
				admin.setLoginIp(ip);
				admin.setLoginDate(new Date());
				admin.setLoginFailureCount(0);
				adminService.update(admin);
				return new SimpleAuthenticationInfo(new Principal(admin.getId(),
						username), password, getName());
			}else{
				if (!member.getIsEnabled()) {
					throw new DisabledAccountException();
				}
				Setting setting = SettingUtils.get();
				if (member.getIsLocked()) {
					if (ArrayUtils.contains(setting.getAccountLockTypes(),
							AccountLockType.member)) {
						int loginFailureLockTime = setting.getAccountLockTime();
						if (loginFailureLockTime == 0) {
							throw new LockedAccountException();
						}
						Date lockedDate = member.getLockedDate();
						Date unlockDate = DateUtils.addMinutes(lockedDate,
								loginFailureLockTime);
						if (new Date().after(unlockDate)) {
							member.setLoginFailureCount(0);
							member.setIsLocked(false);
							member.setLockedDate(null);
							memberService.update(member);
						} else {
							throw new LockedAccountException();
						}
					} else {
						member.setLoginFailureCount(0);
						member.setIsLocked(false);
						member.setLockedDate(null);
						memberService.update(member);
					}
				}
				if (!DigestUtils.md5Hex(password).equals(member.getPassword())) {
					int loginFailureCount = member.getLoginFailureCount() + 1;
					if (loginFailureCount >= setting.getAccountLockCount()) {
						member.setIsLocked(true);
						member.setLockedDate(new Date());
					}
					member.setLoginFailureCount(loginFailureCount);
					memberService.update(member);
					throw new IncorrectCredentialsException();
				}
				member.setLoginIp(ip);
				member.setLoginDate(new Date());
				member.setLoginFailureCount(0);
				memberService.update(member);
				

				
				return new SimpleAuthenticationInfo(new Principal(member.getId(),username), password, getName());
			}
	
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName())
				.iterator().next();
		if (principal != null) {
			List<String> authorities = adminService.findAuthorities(principal
					.getId());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}