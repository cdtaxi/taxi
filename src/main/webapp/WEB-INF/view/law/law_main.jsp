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
							<button class="easyui-linkbutton btnDefault" onclick="openPage(8)">8-行政强制措施审批表</button>
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" onclick="openPage(9)">9-行政强制措施告知书</button>
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" onclick="openPage(10)">10-行政强制措施决定书</button>
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" onclick="openPage(11)">11-延长行政强制措施期限审批表</button>
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" onclick="openPage(12)">12-延长行政强制措施期限通知书</button>
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" onclick="openPage(13)">13-解除行政强制措施决定书</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script language="JavaScript" type="text/javascript">
		function openPage(i) {
// 			window.open("lawNum?num="+i+"&backurl=" + window.location.href);     
				window.location.href = "lawNum?num="+i+"&backurl=" + window.location.href;
		}
	</script>
</body>
</html>
