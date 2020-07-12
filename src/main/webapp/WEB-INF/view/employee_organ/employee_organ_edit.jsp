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
	
	<div class="form1-column">
			<form id="vui_sample" class="easyui-form" method="post"> <!-- data-options="novalidate:true"> -->
			<input type="hidden" name="id" id="id" value="${pd.id }" />							
				<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="parent_id" value="${pd.parent_id }" style="width: 300px" data-options="label:'从业资格证编号:',required:true">
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="type" value="${pd.type }" style="width: 300px" data-options="label:'从业资格证类型:',required:true">
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
						 <span>初次领证日期：</span>
							<span style="margin-left:45px;"><input class="Wdate" name="first_date"  value="${pd.first_date }" style="width: 250px"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
						 <span>有效起始日期：</span>
							<span style="margin-left:45px;"><input class="Wdate" name="valid_startdate"  value="${pd.valid_startdate }" style="width: 250px"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
						 <span>有效终止日期：</span>
							<span style="margin-left:45px;"><input class="Wdate" name="valid_enddate"  value="${pd.valid_enddate }" style="width: 250px"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>
						</div>
					</div>
				
					<div class="form-column1">
						<div class="form-column-left">
						 <span>补办日期：</span>
							<span style="margin-left:70px;"><input class="Wdate" name="buban_date"  value="${pd.buban_date }" style="width: 250px"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
						 <span>换证日期：</span>
							<span style="margin-left:70px;"><input class="Wdate" name="huanzheng_date"  value="${pd.huanzheng_date }" style="width: 250px"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>
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