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
$(function() {
	
	//添加部分公司列表
	/* $('#parent_id1').combobox({
		url : '../enterprise/queryEnterpriseList',
		valueField : 'unitname',
		textField : 'unitname',
		onSelect : function(data) {
			$("#parent_id1").textbox('setValue', data.unitname);
		}
	}); */
	$('#parent_id1').combobox({
		url : '../enterprise/queryEnterpriseList',
		valueField : 'id',
		textField : 'unitname',
		editable : false,
		cache : false				
	});
	
});
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
					parent.$('#list_data').datagrid('reload');
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
<div class="form1-column" title="表单示例">
			<form id="vui_sample" class="easyui-form" >
				<input type="hidden" name="id" value="${pd.id }">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" id="parent_id1" name="parent_id" value="${pd.parent_id }" style="width: 100%" data-options="label:'企业名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
					 <span>日期：</span>
							<span style="margin-left:96px;"><input class="Wdate" name="date"  value="${pd.date }" style="width: 250px"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>						
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="project" value="${pd.project }" style="width: 100%" data-options="label:'项目:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="reduction" value="${pd.reduction }" style="width: 100%" data-options="label:'减分:'"  validtype="integer">
					</div>
				</div>			
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="plus" value="${pd.plus }" style="width: 100%" data-options="label:'加分:'"  validtype="integer">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="note" value="${pd.note }" style="width: 100%" data-options="label:'备注:'">
					</div>
				</div>				
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()"
						style="width: 80px" /> <input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault"
						onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
</body>
</html>