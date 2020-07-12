<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试成绩录入</title>
<%@ include file="/common/taglibs.jsp"%>
</head>
<body class="padding10">
	<form action="../employee/goExamResult" method="post" name="employeeForm" id="employeeForm">
		<table class="table table-bordered">
			<tr>
				<th>
					<input class="easyui-textbox" name="idcard" style="width: 200px">
					<input type="button" value="查询" class="easyui-linkbutton btnPrimary" style="width: 100px" onclick="search();"/>
				</th>
			</tr>
			<tr>
				<th>
					<c:choose>
						<c:when test="${not empty employee}">
							<p>
								<span style="width: 100px;">姓名:</span>
								<span style="margin-left: 10px;">${employee.name }</span>
							</p>
							<p>
								<span style="width: 100px;">性别:</span>
								<span style="margin-left: 10px;">
									<c:if test="${employee.sex == 0}">男</c:if>
									<c:if test="${employee.sex == 1}">女</c:if>
								</span>
							</p>
							<p>
								<span style="width: 100px;">公司:</span>
								<span style="margin-left: 10px;">${employee.company }</span>
							</p>
							<p>
								<span style="width: 100px;">身份证号:</span>
								<span style="margin-left: 10px;">${employee.idcard }</span>
							</p>
						</c:when>
						<c:otherwise>
							<p>查无此人，请确认身份证号码是否正确！</p>
						</c:otherwise>
					</c:choose>
				</th>
			</tr>
			<tr>
				<th>
					<input type="button" name="" value="已通过考试" class="easyui-linkbutton btnPrimary" style="width: 100px" onclick="examStatus('${employee.id}','1','${employee.status }');" />
					<input type="button" name="" value="未通过考试" class="easyui-linkbutton btnDefault" style="width: 100px" onclick="examStatus('${employee.id}','2','${employee.status }');" />
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		//检索
		function search() {
			$("#employeeForm").submit();
		}

		function examStatus(id, status, thisStatus) {
			if (thisStatus != 1) {
				$.ajax({
					type : "POST",
					url : '../employee/changeStatus',
					data : {
						id : id,
						status : status
					},
					cache : false,
					success : function(data) {
						if (data == "ok") {
							alert("录入成功");
						} else {
							alert("录入失败");
						}
						window.location.reload();
					}
				});
			} else {
				alert("已录入！");
			}

		}
	</script>
</body>
</html>

