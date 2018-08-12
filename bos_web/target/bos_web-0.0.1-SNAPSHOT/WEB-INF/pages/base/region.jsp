<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域设置</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript">
	function doAdd(){
		$('#addRegionWindow').window("open");
	}
	
	function doView(){
		$('#searchRegionWindow').window("open");
	}
	
	function doEdit(){
		//获取选中行的数据
		var rowData = $("#grid").datagrid("getSelected");
		//取得所有选中行的 itemid
		var rows = $("#grid").datagrid("getSelections");
		
		if(rowData == null){
			//没有选中的记录，弹出提示
			$.messager.alert("提示信息","请选择需要修改的区域！","info");
		} else if(rows.length > 1){
			//选中多条记录，弹出提示
			$.messager.alert("提示信息","一次只能修改一条数据！","info");
		} else {
			//打开修改区域窗口
			$('#editRegionWindow').window("open");
			//使用form表单对象的load方法回显数据
			$("#editRegionForm").form("load",rowData);
		}
	}
	
	function doDelete(){
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
					location.href = "${pageContext.request.contextPath }/regionAction_delete.action?ids="+ids;
				}
			});
		}
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	},{
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
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
	}, {
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo'
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center'
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center'
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center'
	}, {
		field : 'postcode',
		title : '邮编',
		width : 120,
		align : 'center'
	}, {
		field : 'shortcode',
		title : '简码',
		width : 120,
		align : 'center'
	}, {
		field : 'citycode',
		title : '城市编码',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageSize:30,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath }/regionAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		//页面加载完成后，调用OCUpload插件的方法
		$("#button-import").upload({
			action:'${pageContext.request.contextPath }/regionAction_importXls.action',
			name:'regionFile'
		});
		
		// 添加区域窗口
		$('#addRegionWindow').window({
	        title: '添加区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改区域窗口
		$('#editRegionWindow').window({
	        title: '修改区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询区域窗口
		$('#searchRegionWindow').window({
	        title: '查询区域',
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
		
		//为搜索按钮绑定事件
		$("#search").click(function(){
			//将指定的form表单中所有的输入项转为json数据{key:value,key:value}
			var p = $("#searchRegionForm").serializeJson();
			console.info(p);
			//调用数据表格的load方法，重新发送一次ajax请求，并且提交参数
			$("#grid").datagrid("load",p);
			//关闭查询窗口
			$("#searchRegionWindow").window("close");
		});
		
		//为清空按钮绑定事件
		$("#empty").click(function(){
			$('#searchRegionForm')[0].reset();
		});
		
	});
	
	//添加区域保存按钮
	$(function(){
		$("#add_save").click(function(){
			//表单校验，如果校验通过再提交表单
			var v = $("#addRegionForm").form("validate");
			if(v){
				$("#addRegionForm").submit();
			}
		});
	})

	//修改区域保存按钮
	$(function(){
		$("#edit_save").click(function(){
			//表单校验，如果校验通过再提交表单
			var v = $("#editRegionForm").form("validate");
			if(v){
				$("#editRegionForm").submit();
			}
		});
	})
	
	//数据表格绑定的双击行事件对应的函数
	function doDblClickRow(rowIndex, rowData){
		//打开修改区域窗口
		$('#editRegionWindow').window("open");
		//使用form表单对象的load方法回显数据
		$("#editRegionForm").form("load",rowData);
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<!-- 区域添加 start -->
	<div class="easyui-window" title="区域添加" id="addRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="add_save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addRegionForm" action="${pageContext.request.contextPath }/regionAction_add.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>ID</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	<!-- 区域添加 end -->
	
	<!-- 区域修改 start -->
	<div class="easyui-window" title="区域修改" id="editRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit_save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editRegionForm" action="${pageContext.request.contextPath }/regionAction_edit.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>ID</td>
						<td><input type="text" name="id" class="easyui-validatebox" onfocus=this.blur() /></td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 修改区域 End -->
	
	<!-- 查询区域 Start -->
	<div class="easyui-window" title="区域查询" id="searchRegionWindow" collapsible="false" 
		minimizable="false" maximizable="false" style="top:20px;left:200px">
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="searchRegionForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息查询条件</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode"/></td>
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
	<!-- 查询区域 End -->
</body>
</html>