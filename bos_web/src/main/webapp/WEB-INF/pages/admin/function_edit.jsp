<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		// 点击保存
		$('#edit_save').click(function(){
			//校验表单
			var v = $("#functionEditForm").form("validate");
			if(v){
				//校验通过提交表单
				$("#functionEditForm").submit();
			}
		});
		
		//返回按钮
		$('#back').click(function(){
			location.href='${pageContext.request.contextPath}/page_admin_function.action';
		});
		
		//是否生成菜单
		var generatemenuValue = "${editFunction.generatemenu}";
		var generatemenuOption = $("#generatemenu").find("option");
		for(var i = 0; i < generatemenuOption.length; i++){
			if($(generatemenuOption[i]).val() == generatemenuValue){
				$("#generatemenu").combobox("select", generatemenuValue);
			}
		}
		
		//菜单类型
		var typeValue = "${editFunction.type}";
		var typeOption = $("#type").find("option");
		for(var i = 0; i < typeOption.length; i++){
			if($(typeOption[i]).val() == typeValue){
				$("#type").combobox("select", typeValue);
			}
		}
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'north'">
	<div class="datagrid-toolbar">
		<a id="back" icon="icon-back" href="#" class="easyui-linkbutton" plain="true" >返回</a>
		<a id="edit_save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
	</div>
</div>
<div data-options="region:'center'">
	<form id="functionEditForm" action="${pageContext.request.contextPath }/functionAction_edit.action" method="post">
				<input type="hidden" name="id" value="${editFunction.id }"/>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">功能权限信息</td>
					</tr>
					<tr>
						<td width="200">关键字</td>
						<td>
							<input type="text" name="code" class="easyui-validatebox" data-options="required:true" value="${editFunction.code }" />						
						</td>
					</tr>
					<tr>
						<td>名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" value="${editFunction.name }" /></td>
					</tr>
					<tr>
						<td>访问路径</td>
						<td><input type="text" name="page" value="${editFunction.page }" /></td>
					</tr>
					<tr>
						<td>是否生成菜单</td>
						<td>
							<select name="generatemenu" id="generatemenu" class="easyui-combobox" style="width:140px;">
								<option value="0">不生成</option>
								<option value="1">生成</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>菜单类型</td>
						<td>
							<select name="type" id="type" class="easyui-combobox" style="width:140px;">
								<option value="0">系统管理</option>
								<option value="1">基本功能</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>优先级</td>
						<td>
							<input type="text" name="zindex" class="easyui-numberbox" data-options="required:true" value="${editFunction.zindex }" />
						</td>
					</tr>
					<tr>
						<td>父功能点</td>
						<td>
							<input name="parentFunction.id" class="easyui-combobox" data-options="valueField:'id',textField:'name',url:'${pageContext.request.contextPath }/functionAction_listajaxParentNode.action'"/>
						</td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60">${editFunction.description }</textarea>
						</td>
					</tr>
					</table>
			</form>
</div>
</body>
</html>