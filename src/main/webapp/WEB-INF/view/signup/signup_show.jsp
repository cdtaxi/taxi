<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<base href="<%=basePath%>">

<meta charset="utf-8" />
<title></title>

<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/css/chosen.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<link rel="stylesheet" href="static/css/datepicker.css" />
<!-- 日期框 -->

<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<style type="text/css">
body{
	overflow-x: hidden;
}
</style>
<style>
/*a  upload */
.a-upload {
	padding: 4px 10px;
	height: 6px;
	width: 98px;
	line-height: 6px;
	position: relative;
	cursor: pointer;
	color: #888;
	background: #fafafa;
	border: 1px solid #ddd;
	border-radius: 4px;
	overflow: hidden;
	display: inline-block;
	*display: inline;
	*zoom: 1
}

.a-upload  input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer
}

.a-upload:hover {
	color: #444;
	background: #eee;
	border-color: #ccc;
	text-decoration: none
}
</style>

</head>
<body>
	<input type="hidden" name="id" id="id" value="${pd.ID }" />
	<div style="margin-top: 20px;" id="zhongxin">
		<table style="table-layout: fixed;" id="table_report" border="1"
			class="table table-striped table-bordered table-hover">
			<tr>
				<td colspan="2"><span style="margin-left: 20px;">姓名：</span> <span
					style="margin-left: 2px;">${pd.NAME }</span></td>
				<td colspan="2"><span style="margin-left: 10px;">性别：</span> <span
					style="margin-left: 2px;"> <c:if test="${pd.SEX == 0 }">男</c:if>
						<c:if test="${pd.SEX == 1 }">女</c:if>
				</span></td>
				<td colspan="4"><span style="margin-left: 10px;">出生年月：</span>
					<span style="margin-left: 2px;">${pd.BORNDATE }</span></td>
				
				<td colspan="2" style="width: 102px;" rowspan="4">
					<div style="width: 98px; height: 130px; margin: 2px;">
						<c:if test="${pd != null && pd.PHOTO != '' && pd.PHOTO != null }">
							<a href="employee/getPhoto.do?id=${pd.ID}"
								target="_blank"> <img id="uploadPreview"
								style="width: 98px; height: 130px;"
								src="employee/getPhoto.do?id=${pd.ID}" /></a>
						</c:if>
						<c:if test="${pd == null || pd.PHOTO == '' || pd.PHOTO == null }">
							<img id="uploadPreview" style="width: 98px; height: 130px;" />
						</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><span style="margin-left: 20px;">国籍：</span> <span
					style="margin-left: 2px;">${pd.NATIONALITY }</span></td>
				<td colspan="4"><span style="margin-left: 10px;">身份证号：</span> <span
					style="margin-left: 2px;">${pd.IDCARD }</span></td>
				<td colspan="2"><span style="margin-left: 10px;">文化程度：</span> 
				<span style="margin-left: 2px;">${pd.EDUCATED }
				
				<%-- 	<c:if test="${pd.EDUCATED == 0 }">初中</c:if>
					<c:if test="${pd.EDUCATED == 1 }">专科</c:if>
					<c:if test="${pd.EDUCATED == 2 }">本科</c:if>
					<c:if test="${pd.EDUCATED == 3 }">研究生</c:if> --%>
				</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<span style="margin-left: 20px;">联系电话：</span> <span
						style="margin-left: 2px;">${pd.PHONE }</span></td>
				<td colspan="4">
					<span style="margin-left: 20px;">申报材料页数：</span> <span
						style="margin-left: 2px;">${pd.INFO_PAGES }</span></td>
			</tr>
			<tr>
			 <td colspan="4">
              <span style="margin-left:20px;">驾驶证号：</span>
                    <span style="margin-left:2px;">${pd.DRIVE_CARD }</span>
            </td>
            <td colspan="4">
              <span style="margin-left:10px;">驾驶证初领日期：</span>
                    <span style="margin-left:2px;">${pd.DRIVE_START_DATE }</span>
            </td>
          </tr>
          <tr>
         	 <td colspan="10"><span style="margin-left: 20px;">住址：</span> <span
					style="margin-left: 2px;">${pd.ADDRESS }</span></td>
          </tr>
          <tr>
			<td colspan="5">
              <span style="margin-left:20px;">准驾车型：</span>
                    <span style="margin-left:2px;">${pd.DRIVE_TYPE }</span>
            </td>
            <td colspan="5">
              <span style="margin-left:10px;">原公司：</span>
                    <span style="margin-left:2px;">${pd.OLD_COMPANY }</span>
            </td>
          </tr>
          <tr>
			<td class="center" colspan="10">
				<a class="btn btn-mini btn-yellow" onclick="signuptable('${pd.ID }');">打印</a>
				<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">关闭</a>
			</td>
		</tr>
		</table>

	</div>
	<div id="zhongxin2" class="center" style="display: none">
		<br /> <br /> <br /> <br /> <br /> <img
			src="static/images/jiazai.gif" /><br />
		<h4 class="lighter block green">提交中...</h4>
	</div>

	<!-- 引入 -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
	</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 单选框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript"
		src="static/js/jquery.maskedinput.min.js"></script>

	<script type="text/javascript">
		$(top.hangge());

		function signuptable(id){
			var url = "<%=basePath%>employee/SignUpTable.do?id="+id;
			window.open(url);   
		}
		
		
	</script>

</body>
</html>