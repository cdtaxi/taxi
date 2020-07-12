<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试报名</title>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	

	function submitForm() {
		var validate = $("#vui_sample").form('validate');
		if (!validate) {
			return validate;
		}
		//var formData = new FormData(document.getElementById("vui_sample"));
		var data =$("#vui_sample").serializeArray();
		$.ajax({
			url : "save",
			type : "POST",
			data : data,
			async : false,
			cache : false,
			success : function(res) {
				if (res) {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.$('#listTree').treegrid('reload');
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
			<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true">
			<input type="hidden" name="id" id="id" value="${pd.id }" />
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="standard"  value="${pd.standard }" style="width: 100%" data-options="label:'考核内容',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-combotree" id="parent_id" name="parent_id" value="${pd.parent_id }" style="width: 100%" data-options="label:'上级标准:',url :'getCombotree',lines : true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="scoring" value="${pd.scoring }" style="width: 100%" data-options="label:'计分标准:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="ordinal" value="${pd.ordinal }" style="width: 100%" data-options="label:'序号:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<select class="easyui-combobox" name="stype" data-options="label:'标准等级:',required:true" labelPosition="top" style="width: 100%;">
							<option value="0" <c:if test="${pd.stype == '0' }">selected</c:if>>一级标准</option>
							<option value="1"  <c:if test="${pd.stype == '1' }">selected</c:if> >二级标准</option>
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