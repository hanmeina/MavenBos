<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>easyui 的datagrid 控件的使用1</title>
    
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
   <h1>将Datagrid 应用 HTML 数据上 </h1>
<!-- 对table元素 添加  class="easyui-datagrid"  -->
<!-- 使用 thead tbody标记， 对每个标题列，设置field属性 -->
<table class="easyui-datagrid"  data-options="singleSelect:true,rownumbers:true,pagination:true">
	<thead>
		<tr>
			<th data-options="field:'code'">商品编号</th>
			<th data-options="field:'name',width:200">商品名称</th>
			<th data-options="field:'price'">商品价格</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>001</td>
			<td>冰箱</td>
			<td>3000</td>
		</tr>
		<tr>
			<td>002</td>
			<td>洗机器</td>
			<td>2000</td>
		</tr>
	</tbody>	
</table>
<h1>加载远程数据</h1>
<table class="easyui-datagrid" data-options="singleSelect:true,rownumbers:true,pagination:true,url:'easyuidemo/data.json'">
	<thead>
		<tr>
			<th data-options="field:'code'">商品编号</th>
			<th data-options="field:'name',width:200">商品名称</th>
			<th data-options="field:'number'">商品数量</th>
		</tr>
	</thead>	
</table>
  </body>
</html>

