<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处罚文书模板</title>
<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/My97DatePicker/skin/WdatePicker.css">
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="comp-search-box">
					<div class="screen-top">
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" onclick="openPag111e(8)">返回</button>
						</div>
					</div>
					<div>行政强制措施审批表</div>
				</div>
			</div>
		</div>
	</div>
	<script language="JavaScript" type="text/javascript">
		function openPage(i) {
			switch (i) {
			case 8:
				window.location.href = "http://www.dollare.com.cn/login.PHP?backurl=" + window.location.href;
				break;
			case 9:
				break;
			}
		}
	</script>
</body>
</html>
