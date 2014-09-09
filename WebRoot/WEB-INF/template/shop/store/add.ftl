<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.register.title")}[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="Operate System" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/register.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $registerForm = $("#registerForm");
	var $name = $("#name");
	var $email = $("#email");
	var $areaId = $("#areaId");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $submit = $(":submit");
	var $agreement = $("#agreement");
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/common/area.jhtml"
	});
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 注册协议
	$agreement.hover(function() {
		$(this).height(200);
	});
	
	// 表单验证
	$registerForm.validate({
		rules: {
			name: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: 1
			},
			email: {
				required: true,
				email: true
				[#if !setting.isDuplicateEmail]
					,remote: {
						url: "${base}/register/check_email.jhtml",
						cache: false
					}
				[/#if]
			},
			captcha: "required"
			[@member_attribute_list]
				[#list memberAttributes as memberAttribute]
					[#if memberAttribute.isRequired]
						,{memberAttribute.id}: {
							required: true
						}
					[/#if]
				[/#list]
			[/@member_attribute_list]
		},
		submitHandler: function(form) {
					$.ajax({
						url: $registerForm.attr("action"),
						type: "POST",
						data: {
							name: $name.val(),
								captchaId: "${captchaId}",
								captcha: $captcha.val()
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							if (message.type == "success") {
								setTimeout(function() {
									$submit.prop("disabled", false);
									location.href = "${base}/";
								}, 3000);
							} else {
								$submit.prop("disabled", false);
								[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
									$captcha.val("");
									$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
								[/#if]
							}
						}
					});
		}
	});

});
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>店铺注册</strong>Store REGISTER
					   
					</div>
					<form id="registerForm" action="store_item_add.jhtml" method="post">
						<table>
							<tr>
								<th>
									<span class="requiredField">*</span>店铺名称:
								</th>
								<td>
									<input type="text" id="name" name="name" class="text"  />
								</td>
							</tr>

									<tr>
										<th>
											地区:
										</th>
										<td>
												<span class="fieldSet">
													<input type="hidden" id="areaId" name="123" />
												</span>
										</td>
									</tr>
																		<tr>
										<th>
											服务类型:
										</th>
										<td>
													<input type="text" id="productcategoryname1Xxproductcategory" name="productcategoryname1Xxproductcategory" />
<input type="text" id="productcategoryname2Xxproductcategory" name="productcategoryname2Xxproductcategory" />
<input type="text" id="productcategoryname3Xxproductcategory" name="productcategoryname3Xxproductcategory" />
													</td>
									</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>${message("shop.captcha.name")}:
									</th>
									<td>
										<span class="fieldSet">
										<input type="hidden" id="captchaId" name="captchaId" class="text captcha"  />
											<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" /><img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" />
										</span>
									</td>
								</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="submit" value="${message("shop.register.submit")}" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									${message("shop.register.agreement")}
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<div id="agreement" class="agreement">
										${setting.registerAgreement}
									</div>
								</td>
							</tr>
						</table>
						<div class="login">
							[@ad_position id = 8 /]
							<dl>
								<dt>${message("shop.register.hasAccount")}</dt>
								<dd>
									${message("shop.register.tips")}
									<a href="${base}/login.jhtml">${message("shop.register.login")}</a>
								</dd>
							</dl>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>