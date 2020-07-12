<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript">
	//检索
	function search() {
		$("#employeeRegisterForm").submit();
	}
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
		//$('#vui_sample').form('clear');
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				
			<div class="comp-search-box">
			<form action="../employeeContinueRegister/goAdd" method="post" name="employeeRegisterForm" id="employeeRegisterForm">
				<div class="screen-top">
					<div class="colRow">
						<input class="easyui-textbox" name="idcard" style="width: 300px">
			<input type="button" value="查询" class="easyui-linkbutton btnPrimary" style="width: 100px" onclick="search();" />
				</div>
			</div>
			</form>
			<form method="post" id="vui_sample">
			<input type="hidden" name="id" id="id" value="${pd.id }" />
			<c:if test="${msg == 'save'}">
			<input type="hidden" name="status" id="status" value="0" />
			</c:if>
			<div class="screen-term" style="display: block;">
			<c:choose>
			<c:when test="${not empty employee}">
				<div class="form2-column" style="margin: 0px 0px 0px 10px;">
					<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="name" data-options="label:'姓名:'" value="${employee.name }" editable="false">
						</div>
						<div class="form-column-left fm-left">
							<select  class="easyui-combobox" style="width: 80px;" name="sex"  data-options="label:'性别:'" editable="false">
								<option value='0' <c:if test="${employee.sex == '0' }">selected</c:if>>男</option>
								<option value='1' <c:if test="${employee.sex == '1' }">selected</c:if>>女</option>
							</select>
						</div>
						<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="cyzg_card" data-options="label:'从业资格证号:'" value="${employee.cyzgCard }" editable="false">
						</div>										
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="idcard" data-options="label:'身份证号:'" value="${employee.idcard }" editable="false">
						</div>
						</div>
						<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="borndate" data-options="label:'出生年月:'" value="${employee.borndate }" editable="false">
						</div>										
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="phone" data-options="label:'联系电话:'" value="${employee.phone }" editable="false">
						</div>
						</div>
						<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="address" data-options="label:'住址:'" value="${employee.address }" editable="false">
						</div>
						
					</div>
					<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="drive_card" data-options="label:'驾驶证号:'" value="${employee.driveCard }" editable="false">
						</div>
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="drive_start_date" data-options="label:'驾驶证初领日期:'" value="${employee.driveStartDate}" editable="false">
						</div>
					</div>
					<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="carid" data-options="label:'车号:'" value="${employee.carid }" editable="false" >
						</div>
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="cartype" data-options="label:'车型:'" value="${employee.cartype}" editable="false">
						</div>
					</div>
					<div class="form-column2">
						<div class="form-column-left">
						<%-- <input class="easyui-textbox" style="width: 100%" name="engage_conn" data-options="label:'与经营者关系:'" value="${employee.engageConn}" editable="false"> --%>
						 <select  class="easyui-combobox" style="width: 80px;" name="engage_conn"  data-options="label:'与经营者关系:'" value="${employee.engageConn }" editable="false" >
								<option value='0' <c:if test="${employee.engageConn == 0 }">selected</c:if>>车主</option>
								<option value='1' <c:if test="${employee.engageConn == 1 }">selected</c:if>>雇佣</option>
						</select> 
							
						</div>
						<div class="form-column-left fm-left">
						<%-- <input class="easyui-textbox" style="width: 100%" name="engage_time" data-options="label:'经营时间:'" value="${employee.engageTime}" editable="false"> --%>
						<select  class="easyui-combobox" style="width: 80px;" name="engage_time"  data-options="label:'经营时间:'" value="${employee.engageTime }" editable="false" >
								<option value='0' <c:if test="${employee.engageTime == 0 }">selected</c:if>>白</option>
								<option value='1' <c:if test="${employee.engageTime == 1 }">selected</c:if>>夜</option>
								<option value='2' <c:if test="${employee.engageTime == 2 }">selected</c:if>>白夜</option>
							</select> 
							
						</div>
					</div>
					<div class="form-column2">
						<div class="form-column-left">
						<span>承包起始时间：</span>
					<span style="margin-left:45px;"><input class="Wdate" name="contract_strcount"  value="${pd.contract_strcount }" style="width: 270px"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>												
						</div>
						<div class="form-column-left fm-left">
						<span>承包结束时间：</span>
					<span style="margin-left:45px;"><input class="Wdate" name="contract_endcount"  value="${pd.contract_endcount }" style="width: 270px"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></span>
							
						</div>
					</div>					
						<div  class="form-btnBar pl75" style="text-align: center;">
							<input type="submit" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()"
							style="width: 80px" /> 
							<input type="submit" value="取消" class="easyui-linkbutton btnDefault"
							onclick="clearForm()" style="width: 80px" />
						</div>
					</div>
				</div>
			</c:when>
			</c:choose>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>