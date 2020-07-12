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
		$('#ordinalId').combotree({
			url:'../examineStandard/getCombotree',
			lines : true,
			onSelect:function(node) {
			  //alert(node.attributes.score);  
			$('#scoring').textbox('setValue',node.attributes.score);
			  }
			})
	})
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
			<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true">
			<input type="hidden" name="id" id="id" value="${pd.id }" />
			<c:if test="${msg == 'add'}">	
			<input type="hidden" name="empId" id="empId" value="${empId }" />
			</c:if>
			<c:if test="${msg == 'edit'}">					
				<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="name" value="${employee.name }" style="width: 300px" data-options="label:'姓名:'"  readonly>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="idcard" value="${employee.idcard }" style="width: 300px" data-options="label:'身份证号:'" readonly>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="cyzgCard" value="${employee.cyzgCard }" style="width: 300px" data-options="label:'从业资格证号:'" readonly>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="carid" value="${employee.carid }" style="width: 300px" data-options="label:'注册车牌号:'" readonly>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="company" value="${employee.company }" style="width: 300px" data-options="label:'所属公司:'" readonly>
						</div>
					</div>
					</c:if>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-combotree" id="ordinalId" name="ordinalId"  
							value="${pd.ordinalId }" style="width: 300px"
							data-options="label:'审核标准:',required : true">
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" id="scoring" name="scoring"  value="${pd.scoring }" style="width: 300px" data-options="label:'扣分分数:',required:true">
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
						 <span>扣分时间：</span>
							<span style="margin-left:70px;"><input class="Wdate" name="examineTime"  value="${pd.examineTime }" style="width: 250px"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>
						</div>
					</div>
					<div class="form-column1">
						<div class="form-column-left">
							<input class="easyui-textbox" name="remark"  value="${pd.remark }" style="width: 300px; height: 60px" data-options="label:'备注:',multiline:true">
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