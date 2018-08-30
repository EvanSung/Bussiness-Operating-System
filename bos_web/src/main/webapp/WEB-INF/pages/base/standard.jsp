<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>取派标准</title>
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
	function doAdd(){
		$('#addStandardWindow').window("open");
	}
	
	function doEdit(){
		alert("修改。。。");
	}
	
	function doDelete(){
		alert("删除。。。");
	}
	
	function doSearch(){
		$('#searchStandardWindow').window("open");
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
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
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'name',
		title : '标准名称',
		width : 120,
		align : 'center'
	},{
		field : 'minweight',
		title : '最小重量',
		width : 120,
		align : 'center'
	}, {
		field : 'maxweight',
		title : '最大重量',
		width : 120,
		align : 'center'
	}, {
		field : 'minlength',
		title : '最小长度',
		width : 120,
		align : 'center'
	}, {
		field : 'maxlength',
		title : '最大长度',
		width : 120,
		align : 'center'
	}, {
		field : 'operator',
		title : '操作人',
		width : 120,
		align : 'center'
	}, {
		field : 'operationtime',
		title : '操作时间',
		width : 120,
		align : 'center'
	} , {
		field : 'operationunit',
		title : '操作单位',
		width : 120,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageSize:10,
			pageList: [10,30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加分区
		$('#addStandardWindow').window({
	        title: '添加取派标准',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改分区
		$('#editStandardWindow').window({
	        title: '修改取派标准',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询分区
		$('#searchStandardWindow').window({
	        title: '查询取派标准',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		//定义一个工具方法，用于将指定的form表单中所有的输入项转为json数据{key:value,key:value}
		$.fn.serializeJson=function(){  
            var serializeObj={};  
            var array=this.serializeArray();
            $(array).each(function(){  
                if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                }  
            });  
            return serializeObj;  
        }; 
		
		$("#btn").click(function(){
			//将指定的form表单中所有的输入项转为json数据{key:value,key:value}
			var p = $("#searchStandardForm").serializeJson();
			console.info(p);
			//调用数据表格的load方法，重新发送一次ajax请求，并且提交参数
			$("#grid").datagrid("load",p);
			//关闭查询窗口
			$("#searchStandardWindow").window("close");
		});
		
		//为清空按钮绑定事件
		$("#empty").click(function(){
			$('#searchStandardForm')[0].reset();
		});
		
	});

	//数据表格绑定的双击行事件对应的函数
	function doDblClickRow(rowIndex, rowData){
		//打开修改区域窗口
		$('#editStandardWindow').window("open");
		//使用form表单对象的load方法回显数据
		$("#editStandardForm").form("load",rowData);
	}
	
	$(function(){
		$("#add_save").click(function(){
			//表单校验，如果校验通过再提交表单
			var v = $("#addStandardForm").form("validate");
			if(v){
				$("#addStandardForm").submit();
			}
		});
		
		$("#edit_save").click(function(){
			//表单校验，如果校验通过再提交表单
			var v = $("#editStandardForm").form("validate");
			if(v){
				$("#editStandardForm").submit();
			}
		});
	})
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<!-- 添加取派标准 Start -->
	<div class="easyui-window" title="取派标准添加" id="addStandardWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="add_save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="addStandardForm" action="" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">取派标准信息</td>
					</tr>
					<tr>
						<td>标准名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最小重量</td>
						<td><input type="text" name="minweight" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最大重量</td>
						<td><input type="text" name="maxweight" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最小长度</td>
						<td><input type="text" name="minlength" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最大长度</td>
						<td><input type="text" name="maxlength" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>操作人</td>
						<td><input type="text" name="operator" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>操作时间</td>
						<td><input type="text" name="operationtime" class="easyui-datebox" required="true"/></td>
					</tr>
					<tr>
						<td>操作单位</td>
						<td><input type="text" name="operationunit" class="easyui-validatebox" required="true"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 添加取派标准End -->
	
	<!-- 修改取派标准Start -->
	<div class="easyui-window" title="取派标准修改" id="editStandardWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit_save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="editStandardForm" action="" method="post">
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">取派标准信息</td>
					</tr>
					<tr>
						<td>标准名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最小重量</td>
						<td><input type="text" name="minweight" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最大重量</td>
						<td><input type="text" name="maxweight" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最小长度</td>
						<td><input type="text" name="minlength" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>最大长度</td>
						<td><input type="text" name="maxlength" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>操作人</td>
						<td><input type="text" name="operator" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>操作时间</td>
						<td><input type="text" name="operationtime" class="easyui-datebox" required="true"/></td>
					</tr>
					<tr>
						<td>操作单位</td>
						<td><input type="text" name="operationunit" class="easyui-validatebox" required="true"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 修改取派标准End -->
	
	<!-- 查询取派标准 Start -->
	<div class="easyui-window" title="查询取派标准窗口" id="searchStandardWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="searchStandardForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">取派标准信息</td>
					</tr>
					<tr>
						<td>标准名称</td>
						<td><input type="text" name="name" class="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
							<a id="empty" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 查询取派标准 End -->
</body>
</html>