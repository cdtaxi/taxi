<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试报名</title>
<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript">
	
	function submitForm() {
		var validate = $("#vui_sample").form('validate');
		if (!validate) {
			return validate;
		}
		//var formData = new FormData(document.getElementById("vui_sample"));
		var data =$("#vui_sample").serializeArray();
		$.ajax({
			url : "../menu/add",
			type : "POST",
			data : data,
			async : false,
			cache : false,
			success : function(res) {
				if (res) {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.$('#menuList_dg').treegrid('reload');
				} else {
					msgShow('系统提示', '出现异常');
				}
			}
		});

		return false;
	}
	function clearForm() {//重置表单
		$('#vui_sample').form('clear');
	}
</script>
</head>
<body>
<div class="form1-column">
			<form id="vui_sample" class="easyui-form">
				<input type="hidden" name="id" value="${pd.id }">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="name" value="${pd.name }" style="width: 100%" data-options="label:'菜单名称',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-combotree" id="parent_id" name="parent_id" value="${pd.parent_id }" style="width: 100%" data-options="label:'上级菜单:',url :'../menu/getCombotree',lines : true,required : true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="url" value="${pd.url }" style="width: 100%" data-options="label:'资源路径:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="icon" value="${pd.icon }" style="width: 100%" data-options="label:'图标:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="sort" value="${pd.sort }" style="width: 100%" data-options="label:'顺序:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<select class="easyui-combobox" name="priority" data-options="label:'菜单类型:',required:true" labelPosition="top" style="width: 100%;">
							<option value="1" <c:if test="${pd.priority == 1 }">selected</c:if>>一级菜单</option>
							<option value="2" <c:if test="${pd.priority == 2 }">selected</c:if>>二级菜单</option>
							<option value="3" <c:if test="${pd.priority == 3 }">selected</c:if>>菜单按钮</option>
							<option value="4" <c:if test="${pd.priority == 4 }">selected</c:if>>行按钮</option>
						</select>
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" />
					<input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
</body>
</html>