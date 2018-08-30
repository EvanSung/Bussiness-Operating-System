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
	function doAdd(){
		$('#addFilesSetupWindow').window("open");
	}
	
	function doView(){
		$('#searchFilesSetupWindow').window("open");
	}
	
	function doDelete(){
		alert("删除。。。");
	}
	
	function doSubset(){
		alert("子集操作。。。");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-more',
		text : '子集操作',
		iconCls : 'icon-more',
		handler : doSubset
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		title : '基础档案编号',
		width : 120,
		align : 'center'
	},{
		field : 'name',
		title : '基础档案名称',
		width : 120,
		align : 'center'
	}, {
		field : 'hasclassification',
		title : '档案是否分级',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "是";
			}else{
				return "否";
			}
		}
	}, {
		field : 'remark',
		title : '备注',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageSize:10,
			pageList: [10,30,50,100],
			pagination : true, //显示分页条
			toolbar : toolbar,
			url : "",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addFilesSetupWindow').window({
	        title: '添加基础档案',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改取派员窗口
		$('#editFilesSetupWindow').window({
	        title: '修改基础档案',
	        width: 400,
	        modal: true,//遮罩效果
	        shadow: true,//阴影效果
	        closed: true,//关闭
	        height: 400,
	        resizable:false
	    });
		
		//搜索取派员窗口
		$('#searchFilesSetupWindow').window({
			title: '搜索基础档案',
	        width: 400,
	        modal: true,//遮罩效果
	        shadow: true,//阴影效果
	        closed: true,//关闭
	        height: 300,
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
		
		//为搜索按钮绑定事件
		$("#search").click(function(){
			//将指定的form表单中所有的输入项转为json数据{key:value,key:value}
			var p = $("#searchFilesSetupForm").serializeJson();
			console.info(p);
			//调用数据表格的load方法，重新发送一次ajax请求，并且提交参数
			$("#grid").datagrid("load",p);
			//关闭查询窗口
			$("#searchFilesSetupWindow").window("close");
		});
		
		//为清空按钮绑定事件
		$("#empty").click(function(){
			$('#searchFilesSetupForm')[0].reset();
		});
		
	});

	//数据表格绑定的双击行事件对应的函数
	function doDblClickRow(rowIndex, rowData){
		//打开修改取派员窗口
		$('#searchFilesSetupWindow').window("open");
		//使用form表单对象的load方法回显数据
		$("#searchFilesSetupForm").form("load",rowData);
	}
	
	$(function(){
		//为保存按钮绑定事件
		$("#save").click(function(){
			//表单校验，如果通过，提交表单
			var v = $("#addFilesSetupForm").form("validate");
			if(v){
				//校验通过，提交表单
				$("#addFilesSetupForm").submit();
			}
		});
		
		//为修改按钮绑定事件
		$("#edit").click(function(){
			//表单校验，如果通过，提交表单
			var v = $("#editFilesSetupForm").form("validate");
			if(v){
				$("#editFilesSetupForm").submit();
			}
		});
		
	});
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<!-- 添加基础档案窗口 Start -->
	<div class="easyui-window" title="基础档案添加" id="addFilesSetupWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addFilesSetupForm" action="" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">基础档案信息信息</td>
					</tr>
					<tr>
						<td>基础档案编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>基础档案名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="hasclassification" value="1" />
						档案是否分级</td>
					</tr>
					<tr>
						<td>备注</td>
						<td>
							<input type="text" name="remark" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 添加基础档案窗口 End -->
	
	<!-- 修改基础档案窗口 Strat -->
	<div class="easyui-window" title="基础档案修改" id="editFilesSetupWindow" collapsible="false" 
		minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-edit" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editFilesSetupForm" action="" method="post">
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">基础档案信息信息</td>
					</tr>
					<tr>
						<td>基础档案编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true" onfocus=this.blur(); /></td>
					</tr>
					<tr>
						<td>基础档案名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="hasclassification" value="1" />
						档案是否分级</td>
					</tr>
					<tr>
						<td>备注</td>
						<td>
							<input type="text" name="remark" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 修改基础档案窗口 End -->
	
	<!-- 查询基础档案窗口 Start -->
	<div class="easyui-window" title="基础档案搜索" id="searchFilesSetupWindow" collapsible="false" 
		minimizable="false" maximizable="false" style="top:20px;left:200px">
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="searchFilesSetupForm">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">基础档案搜索条件</td>
						</tr>
						<tr>
							<td>基础档案编号</td>
							<td><input type="text" name="id" class="easyui-validatebox" /></td>
						</tr>
						<tr>
							<td>基础档案名称</td>
							<td><input type="text" name="name" class="easyui-validatebox" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<a id="search" icon="icon-search" href="#" class="easyui-linkbutton">查询</a> 
								<a id="empty" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
							</td>
						</tr>
					</table>
			</form>
		</div>
	</div>
	<!-- 查询基础档案窗口 End -->
</body>
</html>	