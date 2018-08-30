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
	// 工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查看',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '新增',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}];

	// 定义标题栏
	var columns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2
	}, {
		field : 'username',
		title : '名称',
		width : 100,
		rowspan : 2,
		align : 'center'
	},{
		field : 'gender',
		title : '性别',
		width : 60,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'birthdayString',
		title : '生日',
		width : 120,
		rowspan : 2,
		align : 'center'
	}, {
		title : '其他信息',
		colspan : 2
	}, {
		field : 'roleNames',
		title : '对应角色',
		width : 600,
		rowspan : 2,
		align : 'center'
	} ], [ {
		field : 'station',
		title : '单位',
		width : 80,
		align : 'center'
	}, {
		field : 'salary',
		title : '工资',
		width : 80,
		align : 'center'
	} ] ];
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			pagination : true,
			border : false,
			rownumbers : true,
			striped : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/userAction_pageQuery.action",
			idField : 'id', 
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		$("body").css({visibility:"visible"});
		
	});
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		doEdit();
	}
	
	function doAdd() {
		location.href="${pageContext.request.contextPath}/page_admin_userinfo.action";
	}

	function doView() {
		alert("查询。。。");
	}
	
	function doEdit(){
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
			location.href = "${pageContext.request.contextPath }/userAction_callBack.action?userId="+rows[0].id;
		}
	}

	function doDelete() {
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
					location.href = "${pageContext.request.contextPath }/userAction_delete.action?userId="+ids;
				}
			});
		}
	}
	
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>