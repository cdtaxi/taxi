<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>承德市出租汽车管理系统</title>
<%@ include file="/common/taglibs.jsp"%>

<title>Insert title here</title>
<style type="text/css">
body {
	overflow-x: hidden;
}
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

.grid {
	width: 100%;
	border-collapse: collapse;
	font: 12px arial, helvetica, sans-serif;
	border: 1px solid #8DB2E3
}

.grid td {
	height: 20px;
	padding: 2px;
	border: 1px solid #8DB2E3;
	padding-left: 10px
}

.grid th {;
	height: 20px;
	padding: 2px;
	border: 1px solid #8DB2E3;
	background: #E7F3FE;
	text-align: center;
}
</style>
<script type="text/javascript">
	function Dialogclose() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</head>
<body>
	<form class="form-horizontal" id="Form" method="post" enctype="multipart/form-data" style="padding: 0 5px 0 5px">
		<input type="hidden" name="id" id="id" value="${pd.id }" />
		<div style="margin-top: 20px;" id="zhongxin">
			<table id="table_report" class="grid">
				<tr>
					<td><span style="margin-left: 20px;">从业资格证号：</span> <span style="margin-left: 10px;"><input
							id="cyzgCard" name="cyzgCard" value="${pd.cyzgCard }" style="width: 500px;" type="text"></span></td>
					<td style="width: 124px;" rowspan="4">
						<div>
							<div style="width: 120px; height: 172px; margin: 2px;">
								<c:if test="${pd != null && pd.photo != '' && pd.photo != null }">
									<a href="employee/getPhoto.do?id=${pd.id}" target="_blank"> <img id="uploadPreview"
										style="width: 125px; height: 155px;" src="employee/getPhoto.do?id=${pd.id}" /></a>
								</c:if>
								<c:if test="${pd == null || pd.photo == '' || pd.photo == null }">
									<img id="uploadPreview" style="width: 120px; height: 160px;" />
								</c:if>
								<div style="text-align: center;">
									<a href="javascript:;" class="a-upload" style="margin-top: 3px;"> <input id="photo" name="photo"
										type="file" onchange="loadImageFile();" />点击上传照片
									</a>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td><span style="margin-left: 20px;">档案编号：</span> <span style="margin-left: 10px;"> <input
							id="personalId" name="personalId" value="${pd.personalId }" style="width: 175px;" type="text">
					</span> <span style="margin-left: 30px;">许可案卷编号：</span> <span style="margin-left: 10px;"> <input type="text"
							id="permitFilesId" name="permitFilesId" value="${pd.permitFilesId }" " style="width: 200px;" />
					</span></td>
				</tr>
				<tr>
					<td><span style="margin-left: 20px;">姓名：</span> <span style="margin-left: 10px;"><input id="name"
							name="name" value="${pd.name }" style="width: 100px;" type="text"></span> <span style="margin-left: 20px;">性别：</span>
						<span style="margin-left: 10px;"> <select style="width: 80px;" id="sex" name="sex" title="性别">
								<option value="0" <c:if test="${pd.sex == 0 }">selected="selected"</c:if>>男</option>
								<option value="1" <c:if test="${pd.sex == 1 }">selected="selected"</c:if>>女</option>
						</select>
					</span> <span style="margin-left: 30px;">出生年月：</span> <span style="margin-left: 10px;"> <input class="Wdate"
							type="text" onclick="WdatePicker({readOnly:true})" id="borndate" name="borndate" value="${pd.borndate }"
							style="width: 120px;" />
					</span></td>
				</tr>
				<tr>
					<td><span style="margin-left: 20px;">国籍：</span> <span style="margin-left: 10px;"><input
							id="nationality" name="nationality" value="${pd.nationality }" style="width: 60px;" type="text"></span> <span
						style="margin-left: 20px;">身份证号：</span> <span style="margin-left: 10px;"><input id="idcard" name="idcard"
							value="${pd.idcard }" style="width: 150px;" type="text"></span> <span style="margin-left: 20px;">文化程度：</span> <span
						style="margin-left: 10px;"><input id="educated" name="educated" value="${pd.educated }"
							style="width: 80px;" type="text"></span> </span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">联系电话：</span> <span style="margin-left: 10px;"><input
							id="phone" name="phone" value="${pd.phone }" style="width: 120px;" type="text"></span> <span
						style="margin-left: 20px;">住址：</span> <span style="margin-left: 10px;"><input id="address" name="address"
							value="${pd.address }" style="width: 455px;" type="text"></span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">驾驶证号：</span> <span style="margin-left: 10px;"><input
							id="driveCard" name="driveCard" value="${pd.driveCard }" style="width: 150px;" type="text"></span> <span
						style="margin-left: 20px;">驾驶证初领日期：</span> <span style="margin-left: 10px;"> <input
							class="input-small input-mask-date" id="driveStartDate" name="driveStartDate" value="${pd.driveStartDate}"
							style="width: 100px;" type="text">
					</span> <span style="margin-left: 20px;">准驾车型：</span> <span style="margin-left: 10px;"><input id="driveType"
							name="driveType" value="${pd.driveType}" style="width: 145px;" type="text"></span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">注册时间：</span> <span style="margin-left: 10px;"> <input
							class="Wdate" type="text" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" id="registerDate"
							name="registerDate" value="${pd.registerDate }" style="width: 150px;" />
					</span> <span style="margin-left: 20px;">注销时间：</span> <span style="margin-left: 10px;"> <input class="Wdate"
							type="text" onclick="WdatePicker({readOnly:true})" id="cancelDate" name="cancelDate" value="${pd.cancelDate }"
							style="width: 120px;" />
					</span> <span style="margin-left: 20px;">申报材料页数：</span> <span style="margin-left: 10px;"> <input id="infoPages"
							name="infoPages" value="${pd.infoPages }" style="width: 45px;" type="number">
					</span> <span style="margin-left: 20px;">试卷页数：</span> <span style="margin-left: 10px;"> <input id="examPages"
							name="examPages" value="${pd.examPages }" style="width: 45px;" type="number">
					</span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">服务质量监督卡号：</span> <span style="margin-left: 10px;"><input
							id="fwzljdCard" name="fwzljdCard" value="${pd.fwzljdCard}" style="width: 250px;" type="text"></span> <span
						style="margin-left: 20px;">服务质量监督卡补办日期：</span> <span style="margin-left: 10px;"> <input class="Wdate"
							type="text" onclick="WdatePicker({readOnly:true})" id="fwzljdRepairDate" name="fwzljdRepairDate"
							value="${pd.fwzljdRepairDate }" style="width: 163px;" />
					</span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">车号：</span> <span style="margin-left: 10px;"><input
							id="carid" name="carid" value="${pd.carid }" style="width: 95px;" type="text"></span> <span
						style="margin-left: 20px;">车型：</span> <span style="margin-left: 10px;"><input id="cartype" name="cartype"
							value="${pd.cartype }" style="width: 60px;" type="text"></span> <span style="margin-left: 20px;">公司：</span> <span
						style="margin-left: 10px;"> <select class="chzn-select" name="company" id="company"
							data-placeholder="请选择公司" style="vertical-align: top; width: 135px;">
								<option value=""></option>
								<c:forEach items="${enterpriseInfo}" var="enterpriseInfo">
									<option value="${enterpriseInfo.unitname }"
										<c:if test="${enterpriseInfo.unitname == pd.company }">selected</c:if>>${enterpriseInfo.unitname }</option>
								</c:forEach>
						</select>
					</span> <span style="margin-left: 20px;">原属公司：</span> <span style="margin-left: 10px;"> <select class="chzn-select"
							name="oldCompany" id="oldCompany" data-placeholder="请选择原公司" style="vertical-align: top; width: 130px;">
								<c:if test="${pd.oldCompany == '无'}">
									<option value="无">无</option>
								</c:if>
								<option value=""></option>
								<c:if test="${pd.oldCompany != '无'}">
									<option value="无">无</option>
								</c:if>
								<c:forEach items="${enterpriseInfo}" var="enterpriseInfo">
									<option value="${enterpriseInfo.unitname }"
										<c:if test="${enterpriseInfo.unitname == pd.oldCompany }">selected</c:if>>${enterpriseInfo.unitname }</option>
								</c:forEach>
						</select>
					</span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">与经营者关系：</span> <span style="margin-left: 10px;"> <select
							style="width: 70px;" id="engageConn" name="engageConn" title="ENGAGE_CONN">
								<option value="0" <c:if test="${pd.engageConn == 0 }">selected="selected"</c:if>>车主</option>
								<option value="1" <c:if test="${pd.engageConn == 1 }">selected="selected"</c:if>>雇佣</option>
						</select>
					</span> <span style="margin-left: 20px;">经营时间：</span> <span style="margin-left: 10px;"> <select
							style="width: 60px;" id="engageTime" name="engageTime" title="engage_time">
								<option value="0" <c:if test="${pd.engageTime == 0 }">selected="selected"</c:if>>白</option>
								<option value="1" <c:if test="${pd.engageTime == 1 }">selected="selected"</c:if>>夜</option>
								<option value="2" <c:if test="${pd.engageTime == 2 }">selected="selected"</c:if>>白夜</option>
						</select>
					</span> <span style="margin-left: 10px;">承包起始日期：</span> <span style="margin-left: 10px;"> <input class="Wdate"
							type="text" onclick="WdatePicker({readOnly:true})" id="contractSrtCount" name="contractSrtCount"
							value="${pd.contractSrtCount }" style="width: 100px;" />

					</span> <span style="margin-left: 10px;">承包结束日期：</span> <span style="margin-left: 10px;"> <input class="Wdate"
							type="text" onclick="WdatePicker({readOnly:true})" id="contractEndCount" name="contractEndCount"
							value="${pd.contractEndCount }" style="width: 100px;" />
					</span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">注册有效期：</span> <span style="margin-left: 10px;"> <input
							class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" id="expReg" name="expReg" value="${pd.expReg }"
							style="width: 250px;" />
					</span> <span style="margin-left: 20px;">决定（初领）时间：</span> <span style="margin-left: 10px;"> <input class="Wdate"
							type="text" onclick="WdatePicker({readOnly:true})" id="decisionHours" name="decisionHours"
							value="${pd.decisionHours }" style="width: 163px;" />
					</span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">受理时间：</span> <span style="margin-left: 10px;"> <input
							class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" id="approvalHours" name="approvalHours"
							value="${pd.approvalHours }" style="width: 250px;" />
					</span> <span style="margin-left: 40px;">补发从业证日期：</span> <span style="margin-left: 10px;"> <input class="Wdate"
							type="text" onclick="WdatePicker({readOnly:true})" id="reCyzgDate" name="reCyzgDate" value="${pd.reCyzgDate }"
							style="width: 163px;" />
					</span></td>
				</tr>
				<tr>
					<td colspan="2"><span style="margin-left: 20px;">是否换证：</span> <span style="margin-left: 10px;"><input
							class="input-small input-mask-date" id="sfhz" name="sfhz" value="${pd.sfhz}" style="width: 250px;" type="text"></span>
						<span style="margin-left: 20px;">生活状态：</span> <span style="margin-left: 10px;"> <input id="shzt"
							name="shzt" value="${pd.shzt}" style="width: 163px;" type="text">
					</span></td>
				</tr>
				<tr>
					<td style="text-align: center;" colspan="2"><input type="submit" name="" value="保存"
						class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" /> <input type="submit" name=""
						value="取消" class="easyui-linkbutton btnDefault" onclick="Dialogclose()" style="width: 80px" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>