<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>easyui 的window 控件的使用</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
     <!-- 先引Jquery的js -->
     <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
     <!-- 引入easyui的js -->
      <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
       <!-- 引入国际化的js -->
      <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
       <!-- 引入默认样式的css -->    
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css"/>
      <!-- 引入Icon图标的css -->   
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css"/>
      
  </head>
  
  <body>
     <div id="myWindow" class="easyui-window" data-options="title:'自定义窗口',modal:true,closed:true" style="width:220px;height:120px" >你好</div>
     <input type="button" value="打开窗口" onclick="$('#myWindow').window('open')"/>
  </body>
</html>
