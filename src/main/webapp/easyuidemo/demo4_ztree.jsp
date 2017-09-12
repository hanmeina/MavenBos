<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Jquery Ztree的使用</title>
    
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
      <!-- 引入ztree --> 
       <script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
      <!-- 引入ztreeStyle -->   
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"/>
    
     <script type="text/javascript">
        $(function(){//页面加载完毕后执行js函数
            //设置树参数
            var setting = {};
            //数据
            var zNodes = [
               {"name":"菜单一"},//每个大括号{}，就是一个节点
                  //通过chilldren属性添加子节点
                  
               {"name":"菜单二","children":[
                     {"name":"菜单11","children":[
                          {"name":"菜单111"},
                          {"name":"菜单222"}
                     
                     
                     ]},
                     {"name":"菜单22"}     
               
               ]}
            
            ];
            //初始化树
          $.fn.zTree.init($("#basicTree"), setting, zNodes);
            
            //设置树参数
            var setting1 = {
	            data: {
		          simpleData: {
			      enable: true
			
		               }
	                }
                 };
            
        //数据
        
        var zNodes1 = [
             //id代表本节点编号，pId父节点编号
            {"id":"1","pId":"0","name":"菜单一","url":"https://www.baidu.com","icon":"${pageContext.request.contextPath}/js/ztree/img/diy/3.png"},
            {"id":"2","pId":"0","name":"菜单二"},
            {"id":"3","pId":"1","name":"菜单1-1"},
            {"id":"4","pId":"1","name":"菜单1-2"}
        ];
        
            //初始化树
            $.fn.zTree.init($("#simpleTree"), setting1, zNodes1);
      
        
        });
     
    
    </script>
   
   
  </head>
  
  <body class="easyui-layout">
  <!-- easyui 在标签data-options 配置属性 -->
    <!-- 只有center区域是必须的， -->
       <div data-options="region:'north', title:'北部面板'" style="height:100px;">北部</div>
       <div data-options="region:'south', title:'南部面板'" style="height:100px;">南部</div>
       <div data-options="region:'west' , title:'西部面板'" style="width:200px;">
       
           <!-- 折叠面板 -->
           <!-- fit属性，使当前面板占满父容器 -->
          <div class="easyui-accordion" data-options="fit:true">
             <!-- 通过iconCls 来设置图标，找icon.css中类定义 -->
            <div data-options="title:'基本功能',iconCls:'icon-mini-add'" >
                  <!-- （显示树 ,标准数据）-->
                 <ul id="basicTree" class="ztree"></ul> 
 

            </div>  <!-- 这里的每个div就是一个面板 -->
            <div data-options="title:'管理员功能',iconCls:'icon-mini-add'">
                   <!-- （显示树 ,简单数据）-->
                   <ul id="simpleTree" class="ztree"></ul>
            
            </div> 
            <div data-options="title:'高级功能',iconCls:'icon-mini-add'">面板三</div>   
          </div>  
       
       </div>
       <div data-options="region:'center'">
             <!-- 选项卡面板 -->
           <div class="easyui-tabs" data-options="fit:true">
             <!-- closable属性    可关闭 -->
            <div data-options="title:'选项卡一',closable:'true'" >内容一</div>  <!-- 这里的每个div就是一个选项卡 -->
            <div data-options="title:'选项卡二',closable:'true'">内容二</div> 
            <div data-options="title:'选项卡三',closable:'true'">内容三</div>   
          </div>  
         
       
       
       </div>
       <div data-options="region:'east', title:'东部面板'" style="width:200px;">东部</div>
       
        
    
    <br>
  </body>
</html>
