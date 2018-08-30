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
		$("body").css({visibility:"visible"});
		
		//性别
		var genderValue = "${editUser.gender}";
		var genderOption = $("#gender").find("option");
		for(var i = 1; i < genderOption.length; i++){
			if($(genderOption[i]).val() == genderValue){
				$("#gender").combobox("select", genderValue);
			}
		}
		
		//单位
		var stationValue = "${editUser.station}";
		var stationOption = $("#station").find("option");
		for(var i = 1; i < stationOption.length; i++){
			if($(stationOption[i]).val() == stationValue){
				$("#station").combobox("select", stationValue);
			}
		}
		
		//保存按钮
		$('#edit_save').click(function(){
			$('#editUserForm').submit();
		});
		
		//返回按钮
		$('#back').click(function(){
			location.href='${pageContext.request.contextPath}/page_admin_userlist.action';
		});
		
		//页面加载完成后，发送ajax请求，获取所有的角色数据
		$.post('${pageContext.request.contextPath }/roleAction_listajax.action',function(data){
			//在ajax回调函数中，解析json数据，展示为checkbox
			for(var i=0;i<data.length;i++){
				var id = data[i].id;
				var name = data[i].name;
				$("#roleTD").append('<input id="'+id+'" type="checkbox" name="roleIds" value="'+id+'"><label for="'+id+'">'+name+'</label>');
			}
		});
		
	});
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="back" icon="icon-back" href="#" class="easyui-linkbutton" plain="true" >返回</a>
			<a id="edit_save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
		</div>
	</div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
       <form id="editUserForm" action="${pageContext.request.contextPath }/userAction_edit.action" method="post" >
           <input type="hidden" name="id" value="${editUser.id }"/>
           <table class="table-edit"  width="95%" align="center">
           		<tr class="title"><td colspan="4">基本信息</td></tr>
	           	<tr><td>用户名:</td><td><input type="text" name="username" id="username" class="easyui-validatebox" required="true" value="${editUser.username }" /></td>
					<td>口令:</td><td><input type="password" name="password" id="password" class="easyui-validatebox" required="true" validType="minLength[5]" value="${editUser.password }" onfocus="this.select();" /></td></tr>
				<tr class="title"><td colspan="4">其他信息</td></tr>
	           	<tr><td>工资:</td><td><input type="text" name="salary" id="salary" class="easyui-numberbox" value="${editUser.salary }" /></td>
					<td>生日:</td><td><input type="text" name="birthday" id="birthday" class="easyui-datebox" value="${editUser.birthday }" /></td></tr>
	           	<tr><td>性别:</td><td>
	           		<select name="gender" id="gender" class="easyui-combobox" style="width: 150px;">
	           			<option value="">请选择</option>
	           			<option value="男">男</option>
	           			<option value="女">女</option>
	           		</select>
	           	</td>
					<td>单位:</td><td>
					<select name="station" id="station" class="easyui-combobox" style="width: 150px;">
	           			<option value="">请选择</option>
	           			<option value="总公司">总公司</option>
	           			<option value="分公司">分公司</option>
	           			<option value="厅点">厅点</option>
	           			<option value="基地运转中心">基地运转中心</option>
	           			<option value="营业所">营业所</option>
	           		</select>
				</td></tr>
				<tr>
					<td>联系电话</td>
					<td colspan="3">
						<input type="text" name="telephone" id="telephone" class="easyui-validatebox" required="true" value="${editUser.telephone }"/>
					</td>
				</tr>
	           	<tr><td>备注:</td><td colspan="3"><textarea name="remark" id="remark" style="width:80%" >${editUser.remark }</textarea></td></tr>
	           	<tr>
	           		<td>选择角色:</td>
	           		<td colspan="3" id="roleTD"></td>
	           	</tr>
           </table>
       </form>
	</div>
</body>
</html>