<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>easyui 的layout 控件的使用</title>
    
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
  
  <body class="easyui-layout">
  <!-- easyui 在标签data-options 配置属性 -->
    <!-- 只有center区域是必须的， -->
       <div data-options="region:'north', title:'北部面板'" style="height:100px;">北部</div>
       <div data-options="region:'south', title:'南部面板'" style="height:100px;">南部</div>
       <div data-options="region:'west' , title:'西部面板'" style="width:200px;">西部</div>
       <div data-options="region:'center', title:'中部面板'">中部</div>
       <!--  <div data-options="region:'east', title:'东部面板'" style="width:200px;">东部</div>-->
    <br>
  </body>
</html>
