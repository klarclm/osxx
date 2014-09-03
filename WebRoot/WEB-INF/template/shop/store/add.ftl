<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.register.title")}[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="Operate System" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/register.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="../../../../resources/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="../../../../resources/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="../../../../resources/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<link rel="stylesheet"
	href="../../../../resources/jquery-easyui-1.4/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../../../../resources/jquery-easyui-1.4/themes/icon.css"
	type="text/css"></link>
</head>
<script type="text/javascript">
	function submitForm(){
		$('#store_add_panel').form({
		url : 'update.jhtml',
			success : function(data) {
				if(data.message.type == "success"){
				$.message(data.message);
				}
			}
		});

	};
</script>
<body>
aaaaaaaaaaa
bbbbbbbbbbbb
	[#include "/shop/include/header.ftl" /]
	<div class="container register">
	<div id="store_add_panel" class="easyui-panel" title="My Panel"
		style="width:500px;height:150px;padding:10px;background:#fafafa;"
		data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:true,maximizable:true,border:false">
              
		<form id="store_add_addform" method="post">
		<table>
			<tr>
				<th>店铺名字</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'店铺名字'" />
				</td>
			</tr>
			<tr>
				<th>店铺能提供的产品服务范畴</th>
				<td><input  name="productcategoryid1Xxproductcategory" class="easyui-validatebox" data-options="required:true,missingMessage:'必填'" />
				</td><td><input  name="productcategoryid2Xxproductcategory" class="easyui-validatebox" />
				</td><td><input  name="productcategoryid3Xxproductcategory" class="easyui-validatebox" />
				</td>
			</tr>
				<tr>
				<th>店铺所在地</th>
				<td><input name="areaidXxarea" class="easyui-validatebox" data-options="required:true,missingMessage:'店铺所在地'" />
				</td>
			</tr>

		</table>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
		</form>
	</div>
	[#include "/shop/include/footer.ftl" /]

</body>

</html>
