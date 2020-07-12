<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglibs.jsp"%>
</head>
<body>
	<div style="margin-top: 20px;" id="zhongxin">
		<table style="table-layout: fixed;" id="table_report" border="1"
			class="table table-striped table-bordered table-hover">
					<tr>
						<td colspan="4"><span style="margin-left: 20px;">从业资格证号：</span>
							<span style="margin-left: 2px;">${varList.cyzg_card }</span></td>
						<td colspan="4"><span style="margin-left: 10px;">身份证号码：</span>
							<span style="margin-left: 2px;">${varList.idcard }</span></td>
					</tr>
					<tr>
						<td colspan="2"><span style="margin-left: 20px;">姓名：</span> <span
							style="margin-left: 2px;">${varList.name }</span></td>
						<td colspan="2"><span style="margin-left: 10px;">性别：</span> <span
							style="margin-left: 2px;"> <c:if
									test="${varList.sex == 0 }">男</c:if> <c:if
									test="${varList.sex == 1 }">女</c:if>
						</span></td>
						<td colspan="4"><span style="margin-left: 10px;">出生年月：</span>
							<span style="margin-left: 2px;">${varList.borndate }</span></td>
					</tr>
					<tr>
						<td colspan="3"><span style="margin-left: 20px;">联系电话：</span>
							<span style="margin-left: 2px;">${varList.phone }</span></td>
						<td colspan="5"><span style="margin-left: 10px;">住址：</span> <span
							style="margin-left: 2px;">${varList.address }</span></td>
					</tr>
					<tr>
						<td colspan="4"><span style="margin-left: 20px;">驾驶证号：</span>
							<span style="margin-left: 2px;">${varList.drive_card }</span>
						</td>
						<td colspan="4"><span style="margin-left: 10px;">驾驶证初领日期：</span>
							<span style="margin-left: 2px;">${varList.drive_start_date }</span>
						</td>
					</tr>
					<tr>
						<td colspan="4"><span style="margin-left: 20px;">车号：</span>
							<span style="margin-left: 2px;">${varList.carid }</span>
						</td>
						<td colspan="4"><span style="margin-left: 10px;">注册时间：</span>
							<span style="margin-left: 2px;">${varList.registerDate }</span>
						</td>
					</tr>
				<!-- 	<tr>
						<td class="center" colspan="8">
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">关闭</a>
						</td>
					</tr> -->
		</table>

	</div>
	
	
</body>
</html>