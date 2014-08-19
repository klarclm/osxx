<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <script type="text/javascript" src="../../../../resources/jquery-easyui-1.4/jquery.easyui.min.js"></script><script type="text/javascript" src="../../../../resources/jquery-easyui-1.4/jquery.min.js"></script><script type="text/javascript" src="../../../../resources/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script><link rel="stylesheet" href="../../../../resources/jquery-easyui-1.4/themes/color.css" type="text/css"></link><link rel="stylesheet" href="../../../../resources/jquery-easyui-1.4/themes/default/easyui.css" type="text/css"></link></head>
  
  <body>
  <div id="store_add_panel" class="easyui-panel" title="My Panel" 
        style="width:500px;height:150px;padding:10px;background:#fafafa;"
        data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:true,maximizable:true,border:false">
</div>
  </body>
</html>
