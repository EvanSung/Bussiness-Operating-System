<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'search',
					text : '查询',
					iconCls : 'icon-search',
					handler : function(){
						alert("查询。。。");
					}
				},{
					id : 'add',
					text : '添加',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_function_add.action';
					}
				},{
					id : 'edit',
					text : '修改',
					iconCls : 'icon-edit',
					handler : function(){
						//获取选中行的数据
						var rowData = $("#grid").datagrid("getSelected");
						//取得所有选中行的 itemid
						var rows = $("#grid").datagrid("getSelections");
						
						if(rowData == null){
							//没有选中的记录，弹出提示
							$.messager.alert("提示信息","请选择需要修改的用户！","info");
						} else if(rows.length > 1){
							//选中多条记录，弹出提示
							$.messager.alert("提示信息","一次只能修改一条数据！","info");
						} else {
							location.href = "${pageContext.request.contextPath }/functionAction_callBack.action?functionId="+rows[0].id;
						}
					}
				},{
					id : 'delete',
					text : '删除',
					iconCls : 'icon-cancel',
					handler : function(){
						//获得所有选中的行
						var rows = $("#grid").datagrid("getSelections");
						if(rows.length == 0){
							//没有选中的记录，弹出提示
							$.messager.alert("提示信息","请选择需要删除的区域！","info");
						}else {
							//弹出确认框
							$.messager.confirm("提示信息","你确认删除当前选中的区域吗？",function(r){
								if(r){
									var ids = "";
									var array = new Array();
									//用户点击确认按钮，删除区域
									//获取当前选中记录的id
									for(var i=0;i<rows.length;i++){
										var id = rows[i].id;//区域id
										array.push(id);
									}
									ids = array.join(",");
									//发送请求，将ids提交到Action
									location.href = "${pageContext.request.contextPath }/functionAction_delete.action?functionId="+ids;
								}
							});
						}
					}
				}
			],
			pagination : true,
			url : '${pageContext.request.contextPath }/functionAction_pageQuery.action',
			fit : true,
			striped : true,
			columns : [[
			  {
				  field : 'id',
				  checkbox : true,
		      },
			  {
				  field : 'code',
				  title : '关键字',
				  width : 200
			  },
			  {
				  field : 'name',
				  title : '名称',
				  width : 200
			  },  
			  {
				  field : 'description',
				  title : '描述',
				  width : 200
			  },  
			  {
				  field : 'generatemenu',
				  title : '是否生成菜单',
				  width : 150,
				  formatter : function(data,row, index){
					  if(data=="1"){
						  return "生成";
					  }else{
						  return "不生成";
					  }
				  }
			  },  
			  {
				  field : 'zindex',
				  title : '优先级',
				  width : 100
			  },  
			  {
				  field : 'page',
				  title : '路径',
				  width : 300
			  },  
			  {
				  field : 'type',
				  title : '菜单类型',
				  width : 100,
				  formatter : function(data,row, index){
					  if(data=="1"){
						  return "基本功能";
					  }else{
						  return "系统管理";
					  }
				  }
			  }
			]]
		});
	});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="grid"></table>
	</div>
</body>
</html>