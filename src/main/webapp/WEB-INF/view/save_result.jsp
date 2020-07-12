<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保存结果</title>
<base href="<%=basePath%>">
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script type="text/javascript" src="assets/js/jquery2.1.1.js"></script>

</head>
<body>
	<script type="text/javascript">
		var msg = "${msg}";
		if (msg == "success" || msg == "") {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			$('#userList_dg').datagrid('reload');// 刷新datagrid
		} else {
			msgShow('系统提示', '出现异常');
		}
	</script>
</body>
</html>