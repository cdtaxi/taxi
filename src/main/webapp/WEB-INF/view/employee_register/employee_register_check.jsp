<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<style type="text/css">
		body{
			overflow-x: hidden;
		}
		</style>
	</head>
<body>
	<div style="margin-top: 20px;" id="zhongxin">
		<table style="table-layout: fixed;" id="table_report" border="1"
			class="table table-striped table-bordered table-hover">
					<tr>
						<td colspan="4"><span style="margin-left: 20px;">从业资格证号：</span>
							<span style="margin-left: 2px;">${varList.cyzgCard }</span></td>
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
							<span style="margin-left: 2px;">${varList.driveCard }</span>
						</td>
						<td colspan="4"><span style="margin-left: 10px;">驾驶证初领日期：</span>
							<span style="margin-left: 2px;">${varList.driveStartDate }</span>
						</td>
					</tr>
					<tr>
						<td colspan="4"><span style="margin-left: 20px;">车号：</span>
							<span style="margin-left: 2px;">${varList.carid }</span>
						</td>
						<td colspan="4"><span style="margin-left: 10px;">车型：</span>
							<span style="margin-left: 2px;">${varList.cartype }</span>
						</td>
					</tr>
					<tr>
						<td colspan="4"><span style="margin-left: 20px;">与经营者关系：</span>
							<span style="margin-left: 2px;"> 
								<c:if test="${varList.engageConn == 0 }">车主</c:if> 
								<c:if test="${varList.engageConn == 1 }">雇佣</c:if>
							</span>
						</td>
						<td colspan="4"><span style="margin-left: 10px;">经营时间：</span>
							<span style="margin-left: 2px;"> 
								<c:if test="${varList.engageTime == 0 }">白</c:if> 
								<c:if test="${varList.engageTime == 1 }">夜</c:if>
								<c:if test="${varList.engageTime == 2 }">白夜</c:if>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4"><span style="margin-left: 20px;">承包起始时间：</span>
							<span style="margin-left: 2px;">${varList.contractStrcount }</span>
						</td>
						<td colspan="4"><span style="margin-left: 10px;">承包结束时间：</span>
							<span style="margin-left: 2px;">${varList.contractEndcount }</span>
						</td>
					</tr>
					<tr>
						<td class="center" colspan="8">
							<%-- <a class="btn btn-mini btn-primary" onclick="checkRegister('${varList.id}','${varList.idcard }','2');">审核通过</a>
							<a class="btn btn-mini btn-info" onclick="checkRegister('${varList.id}','${varList.idcard }','1');">审核未通过</a> --%>
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">关闭</a>
						</td>
					</tr>
		</table>

	</div>
	<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
	
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 单选框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery.maskedinput.min.js"></script>

		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			$('.input-mask-date').mask('9999-99-99');
		});
		
		<%-- function checkRegister(id,idcard,status){
			var url = "<%=basePath%>employeeRegister/checkRegister.do?id="+id+"&idcard="+idcard+"&status="+status+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data=="error"){
					alert("审核失败，请联系管理员");
				}else if(data=="success"){
					alert("审核成功");
					top.Dialog.close();
				}else if(data=="useReg"){
					alert("该从业人员已注册！");
				}
			});
		} --%>
		
		</script>
	
</body>
</html>