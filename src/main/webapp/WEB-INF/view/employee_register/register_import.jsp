<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
	
	</head> 

<body onkeydown="keyLogin();">
		
		
<div class="container-fluid" id="main-container">

		

<div id="page-content" class="clearfix">
						
  <div class="row-fluid">
	<div class="row-fluid">
		<!-- 检索  -->
		<form action="employeeRegister/goImportReg.do" method="post" name="employeeRegForm" id="employeeRegForm">
		<table style="table-layout: fixed;" class="table center">
			<tr>
				<td colspan="8" class="center" style="vertical-align:top;">
					<span style="margin-top: 100px;" class="input-icon">
						<input autocomplete="off" id="nav-search-input" style="margin-top: 10px;width: 200px;" type="text" name="id" placeholder="请扫描或输入条形码" />
						<i id="nav-search-icon"  style="margin-top: 10px;" class="icon-search"></i>
						<button class="btn btn-mini btn-light" id="tijiao" onclick="search();" ><i id="nav-search-icon" class="icon-search"></i></button>
					</span>
				</td>
			</tr>
			
			<c:choose>
				<c:when test="${not empty varList}">
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
						<td colspan="4"><span style="margin-left: 20px;">联系电话：</span>
							<span style="margin-left: 2px;">${varList.phone }</span></td>
						<td colspan="4"><span style="margin-left: 10px;">住址：</span> <span
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
						<td colspan="8" class="center" style="vertical-align:top;">
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-yellow" onclick="Resignuptable('${pd.id }');">打印注册表</a>
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-success" onclick="registerImport('${varList.id}');">确认注册</a>
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-info" onclick="PrintSupervision('${pd.id }');">打印监督卡</a>
						</td>
					</tr>
					<tr>
						<td colspan="8" class="center" style="vertical-align:top;">
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-success" onclick="PrintRegExec('${varList.id}','1');">注册记录一</a>
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-success" onclick="PrintRegExec('${varList.id}','2');">注册记录二</a>
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-success" onclick="PrintRegExec('${varList.id}','3');">注册记录三</a>
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-success" onclick="PrintRegExec('${varList.id}','4');">注册记录四</a>
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-success" onclick="PrintRegExec('${varList.id}','5');">注册记录五</a>
							<a style="margin-top: 10px;width: 100px;" class="btn btn-small btn-success" onclick="PrintRegExec('${varList.id}','6');">注册记录六</a>
						</td>
					</tr>
				</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8" class="center">查无此人，请确认条形码扫描或输入正确！</td>
				</tr>
			</c:otherwise>
			</c:choose>
		</table>
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 单选框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		
		<script type="text/javascript">
		
		$(top.hangge());
		
		window.onload = function()
		{
		    document.getElementById("nav-search-input").focus();
		}

		//检索
		function search(){
			$("#employeeRegForm").submit();
		}
		
		function registerImport(id){
			$(top.jzts());
			var url = "<%=basePath%>employeeRegister/registerImport.do?id="+id+"&tm="+new Date().getTime();
			$.get(url,function(data){
				$(top.hangge());
				if(data=="error"){
					alert("录入失败");
					window.location.reload();
				}else if(data=="success"){
					alert("录入成功");
					top.Dialog.close();
					window.location.reload();
				}else if(data=="useReg"){
					alert("该从业人员已录入！");
					window.location.reload();
				}
			});
			
		}
		
		function keyLogin(){
			 if (event.keyCode==13)  //回车键的键值为13
			   document.getElementById("tijiao").click(); //调用登录按钮的登录事件
			}
		

		function Resignuptable(id){
			var url = "<%=basePath%>employeeRegister/ReSignUpTable.do?E_IDS="+id;
			window.open(url);   
			window.location.reload();
		}
		
		function PrintRegExec(id,position){
			var url = "<%=basePath%>employeeRegister/printEmployeeCard02.do?E_IDS="+id+"&position="+position;
			window.open(url);  
			window.location.reload();
		}
		
		function PrintSupervision(id){
			var url = "<%=basePath%>employeeRegister/printAllSupervisionCard.do?E_IDS="+id;
			window.open(url);  
			window.location.reload();
		}
		
		</script>
		
	</body>
</html>

