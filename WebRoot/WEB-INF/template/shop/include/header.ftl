<script type="text/javascript">
$().ready(function() {

	var $headerLogin = $("#headerLogin");
	var $headerRegister = $("#headerRegister");
	var $headerLogout = $("#headerLogout");
	var $storename = $("#storename");
	var $productSearchForm = $("#productSearchForm");
	var $keyword = $("#productSearchForm input");
	var defaultKeyword = "${message("shop.header.keyword")}";
	var $headerUsername = $("#headerUsername");
	var $storeRegister = $("#storeRegister")
	var $admincommonmainpage = $("#admincommonmainpage")
	
	var username = getCookie("username");
	if (username != null) {
		$headerUsername.text("${message("shop.header.welcome")}, " + username).show();
		$headerLogout.show();
	} else {
		$headerLogin.show();
		$headerRegister.show();
	}
	
	$.ajax({
	url: ${base}/member/getMemberState.jhtml,
	type: "POST",
	data: {},
	dataType: "json",
	cache: false,
	success: function(data) {
		if (data.isAuthenticated == true) {
			$headerUsername.text("${message("shop.header.welcome")}, " + data.user.username).show();
			$headerLogout.show();
			if(data.isStoreOwner == true){
				$storeRegister.hide();
				$admincommonmainpage.show();
			}
		}
		else{
			$headerUsername.hide();
			$headerLogin.show();
			$headerRegister.show();
		}
	}
	});

	$keyword.focus(function() {
		if ($keyword.val() == defaultKeyword) {
			$keyword.val("");
		}
	});
	
	$keyword.blur(function() {
		if ($keyword.val() == "") {
			$keyword.val(defaultKeyword);
		}
	});
	
	$productSearchForm.submit(function() {
		if ($.trim($keyword.val()) == "" || $keyword.val() == defaultKeyword) {
			return false;
		}
	});

});
</script>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${base}/">
				<img src="${setting.logo}" alt="${setting.siteName}" />
			</a>
		</div>
	</div>
	<div class="span9">
		[@ad_position id = 1 /]
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>

				<li id="headerLogin" class="headerLogin" style="display:none">
					<a href="${base}/login/index.jhtml">${message("shop.header.login")}</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display:none">
					<a href="${base}/register.jhtml">${message("shop.header.register")}</a>|
				</li>
				<li id="admincommonmainpage" class="admincommonmainpage" style="display:none">
					<a href="${base}/admin/common/main.jhtml">管理页面</a>|
				</li>
				<li id="headerUsername" class="headerUsername"  style="display:none"></li>
				<li id="headerLogout" class="headerLogout"  style="display:none">
					<a href="${base}/logout.jhtml">[${message("shop.header.logout")}]</a>|
				</li>
				<li id="storeRegister" class="storeRegister"  style="display:none">
				<a href="${base}/store/add.jhtml">添加商铺</a>|
				</li>
				
				[@navigation_list position = "top"]
					[#list navigations as navigation]
						<li>
							<a href="${navigation.url}"[#if navigation.isBlankTarget] target="_blank"[/#if]>${navigation.name}</a>
							[#if navigation_has_next]|[/#if]
						</li>
					[/#list]
				[/@navigation_list]
			</ul>
		</div>
		<div class="cart">
			<a href="${base}/cart/list.jhtml">${message("shop.header.cart")}</a>
		</div>
		[#if setting.phone??]
			<div class="phone">
				${message("shop.header.phone")}:
				<strong>${setting.phone}</strong>
			</div>
		[/#if]
	</div>
	<div class="span24">
		<ul class="mainNav">
			[@navigation_list position = "middle"]
				[#list navigations as navigation]
					<li[#if navigation.url = url] class="current"[/#if]>
						<a href="${navigation.url}"[#if navigation.isBlankTarget] target="_blank"[/#if]>${navigation.name}</a>
						[#if navigation_has_next]|[/#if]
					</li>
				[/#list]
			[/@navigation_list]
		</ul>
	</div>
	<div class="span24">
		<div class="tagWrap">
			<ul class="tag">
				[@tag_list type="product" count = 3]
					[#list tags as tag]
						<li[#if tag.icon??] class="icon" style="background: url(${tag.icon}) right no-repeat;"[/#if]>
							<a href="${base}/product/list.jhtml?tagIds=${tag.id}">${tag.name}</a>
						</li>
					[/#list]
				[/@tag_list]
			</ul>
			<div class="hotSearch">
				[#if setting.hotSearches?has_content]
					${message("shop.header.hotSearch")}:
					[#list setting.hotSearches as hotSearch]
						<a href="${base}/product/search.jhtml?keyword=${hotSearch?url}">${hotSearch}</a>
					[/#list]
				[/#if]
			</div>
			<div class="search">
				<form id="productSearchForm" action="${base}/product/search.jhtml" method="get">
					<input name="keyword" class="keyword" value="${productKeyword!message("shop.header.keyword")}" maxlength="30" />
					<button type="submit">${message("shop.header.search")}</button>
				</form>
			</div>
		</div>
	</div>
</div>