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
<script type="text/javascript">
	//检索
	function search() {
		$("#employeeForm").submit();
	}
	
</script>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<form action="../examine/listOne" method="post" name="employeeForm" id="employeeForm">
					<div class="comp-search-box">
						<div class="screen-top">
							<div class="colRow">
								<input class="easyui-textbox" name="idcard" style="width: 300px">
								<input type="button" value="查询" class="easyui-linkbutton btnPrimary" style="width: 100px" onclick="search();" />
							</div>
						</div>
						<div class="screen-term" style="display: block;">
							<c:choose>
								<c:when test="${not empty employee}">
									<div class="form2-column" style="margin: 0px 0px 0px 10px;">
										<div class="form-column2">
											<div class="form-column-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'姓名:'" value="${employee.name }" editable="false">
											</div>
											<div class="form-column-left fm-left">
												<input class="easyui-textbox" style="width: 100%" id="idcard" data-options="label:'身份证号:'" value="${employee.idcard }" editable="false">
											</div>
										</div>
										<div class="form-column2">
											<div class="form-column-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'从业资格证号:'" value="${employee.cyzgCard }" editable="false">
											</div>
											<div class="form-column-left fm-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'注册车牌号:'" value="${employee.carid }" editable="false">
											</div>
										</div>
										<div class="form-column2">
											<div class="form-column-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'所属公司:'" value="${employee.company }" editable="false">
											</div>
											<div class="form-column-left fm-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'剩余分数:'" value="${syFraction}" editable="false">
											</div>
										</div>
									
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
					<c:choose>
						<c:when test="${not empty employee}">
							<table class="easyui-datagrid" title="考核记录">
								<thead>
									<tr>
										<th field=ordinal width="45%" align="left">扣分条款</th>
										<th field="scoring" width="10%" align="center">扣分分数</th>
										<th field="examineTime" width="10%" align="center">扣分时间</th>
										<th field="remark" width="20%" align="left">备注</th>
									<!-- 	<th field="opt" width="150px" align="left"></th> -->
									</tr>
								</thead>
								<tbody>
									<!-- 开始循环 -->
									<c:choose>
										<c:when test="${not empty staList}">
											<c:forEach items="${staList}" var="var" varStatus="vs">
												<tr>
													<td align="left">${var.ordinal }</td>
													<td>${var.scoring }</td>
													<td>${var.examineTime }</td>
													<td align="left">${var.remark }</td>
													<!-- <td></td> -->
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="100" class="center">没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</c:when>
					</c:choose>
				</form>
			</div>
		</div>
	</div>

</body>
</html>