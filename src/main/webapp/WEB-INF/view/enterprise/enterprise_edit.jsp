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
				<input type="hidden" name="id"  value="${pd.id }">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="unitname" value="${pd.unitname }" style="width: 100%" data-options="label:'单位名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="phone" value="${pd.phone }" style="width: 100%" data-options="label:'联系电话:',required:true"  validtype="phone">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="unitaddr" value="${pd.unitaddr }" style="width: 100%" data-options="label:'单位地址:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="businessscope" value="${pd.businessscope }" style="width: 100%" data-options="label:'经营范围:'">
					</div>
				</div>			
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="businessno" value="${pd.businessno }" style="width: 100%" data-options="label:'经营许可证号:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="enterprisecode" value="${pd.enterprisecode }" style="width: 100%" data-options="label:'企业代码:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="regcapital" value="${pd.regcapital }" style="width: 100%" data-options="label:'注册资本:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="economytype" value="${pd.economytype }" style="width: 100%" data-options="label:'经济类型:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="legalrepresentative" value="${pd.legalrepresentative }" style="width: 100%" data-options="label:'法人代表:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="vehiclesnum" value="${pd.vehiclesnum }" style="width: 100%" data-options="label:'车辆总数:'"  validtype="integer">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="employeescount" value="${pd.employeescount }" style="width: 100%" data-options="label:'从业人员总数:'"  validtype="integer">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="manageersonnel" value="${pd.manageersonnel }" style="width: 100%" data-options="label:'安全管理人员总数:'"  validtype="integer">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="carnum" value="${pd.carnum }" style="width: 100%" data-options="label:'企业自有车数:'"  validtype="integer">
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