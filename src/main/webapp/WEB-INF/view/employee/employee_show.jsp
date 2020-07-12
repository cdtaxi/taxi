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

</head>
<body>
	<table style="table-layout: fixed;"  class="table table-striped table-bordered table-hover">
			<tr>
				<td colspan="8"><span style="margin-left: 20px;">从业资格证号：</span> <span style="margin-left: 2px;">${pd.cyzgCard }</span></td>
				<td colspan="2" style="width: 102px;" rowspan="4">
					<div style="width: 98px; height: 130px; margin: 2px;">
						<c:if test="${pd != null && pd.photo != '' && pd.photo != null }">
							<a href="../employee/getPhoto?id=${pd.id}" target="_blank"> <img id="uploadPreview"
								style="width: 98px; height: 130px;" src="../employee/getPhoto?id=${pd.id}" /></a>
						</c:if>
						<c:if test="${pd == null || pd.photo == '' || pd.photo == null }">
							<img id="uploadPreview" style="width: 98px; height: 130px;" />
						</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<c:if test="${flag == '0' }">
					<td colspan="4"><span style="margin-left: 20px;">档案编号：</span> <span style="margin-left: 2px;">${pd.personalId }</span></td>
					<td colspan="4"><span style="margin-left: 10px;">许可案卷编号：</span> <span style="margin-left: 2px;">${pd.permitFilesId }</span></td>
				</c:if>
				<c:if test="${flag != '0' }">
					<td colspan="8"><span style="margin-left: 20px;">档案编号：</span> <span style="margin-left: 2px;">${pd.personalId}</span></td>
				</c:if>
			</tr>
			<tr>
				<td colspan="2"><span style="margin-left: 20px;">姓名：</span> <span style="margin-left: 2px;">${pd.name }</span></td>
				<td colspan="2"><span style="margin-left: 10px;">性别：</span> <span style="margin-left: 2px;"> <c:if
							test="${pd.sex == 0 }">男</c:if> <c:if test="${pd.sex == 1 }">女</c:if>
				</span></td>
				<td colspan="4"><span style="margin-left: 10px;">出生年月：</span> <span style="margin-left: 2px;">${pd.borndate }</span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="margin-left: 20px;">国籍：</span> <span style="margin-left: 2px;">${pd.nationality }</span></td>
				<td colspan="4"><span style="margin-left: 10px;">身份证号：</span> <span style="margin-left: 2px;">${pd.idcard }</span></td>
				<td colspan="2"><span style="margin-left: 10px;">文化程度：</span> <span style="margin-left: 2px;">${pd.educated }</span>
				</td>
			</tr>
			<tr>
				<td colspan="3"><span style="margin-left: 20px;">联系电话：</span> <span style="margin-left: 2px;">${pd.phone }</span></td>
				<td colspan="7"><span style="margin-left: 10px;">住址：</span> <span style="margin-left: 2px;">${pd.address }</span></td>
			</tr>
			<tr>
				<td colspan="3"><span style="margin-left: 20px;">驾驶证号：</span> <span style="margin-left: 2px;">${pd.driveCard }</span>
				</td>
				<td colspan="4"><span style="margin-left: 10px;">驾驶证初领日期：</span> <span style="margin-left: 2px;">${pd.driveStartDate }</span>
				</td>
				<td colspan="3"><span style="margin-left: 10px;">准驾车型：</span> <span style="margin-left: 2px;">${pd.driveType }</span>
				</td>
			</tr>
			<tr>
				<td colspan="3"><span style="margin-left: 20px;">注册时间：</span> <span style="margin-left: 2px;">${pd.registerDate }</span>
				</td>
				<td colspan="3"><span style="margin-left: 10px;">注销时间：</span> <span style="margin-left: 2px;">${pd.cancelDate }</span>
				</td>
				<td colspan="2"><span style="margin-left: 10px;">申报资料记录：</span> <span style="margin-left: 2px;">${pd.infoPages }</span>
				</td>
				<td colspan="2"><span style="margin-left: 10px;">试卷页数：</span> <span style="margin-left: 2px;">${pd.examPages }</span>
				</td>
			</tr>
			<tr>
				<td colspan="5"><span style="margin-left: 20px;">服务质量监督卡号：</span> <span style="margin-left: 2px;">${pd.fwzljdCard }</span>
				</td>
				<td colspan="5"><span style="margin-left: 10px;">服务质量监督卡补办日期：</span> <span style="margin-left: 2px;">${pd.fwzljdRepairDate }</span>
				</td>
			</tr>
			<tr>
				<td colspan="2"><span style="margin-left: 20px;">车号：</span> <span style="margin-left: 2px;">${pd.carid }</span>
				</td>
				<td colspan="2"><span style="margin-left: 10px;">车型：</span> <span style="margin-left: 2px;">${pd.cartype }</span>
				</td>
				<c:if test="${flag == '0' }">
					<td colspan="3"><span style="margin-left: 10px;">公司：</span> <span style="margin-left: 2px;">${pd.company }</span>
					</td>
					<td colspan="3"><span style="margin-left: 10px;">原公司：</span> <span style="margin-left: 2px;">${pd.oldCompany }</span>
					</td>
				</c:if>
				<c:if test="${flag != '0' }">
					<td colspan="6"><span style="margin-left: 10px;">公司：</span> <span style="margin-left: 2px;">${pd.company }</span>
					</td>
				</c:if>
			</tr>
			<tr>
				<td colspan="2"><span style="margin-left: 20px;">与经营者关系：</span> <span style="margin-left: 2px;"> <c:if
							test="${pd.engageConn == 0 }">车主</c:if> <c:if test="${pd.engageConn == 1 }">雇佣</c:if> <c:if
							test="${pd.engageConn == 2 }">去世</c:if>
				</span></td>
				<td colspan="2"><span style="margin-left: 10px;">经营时间：</span> <span style="margin-left: 2px;"> <c:if
							test="${pd.engageTime == 0 }">白</c:if> <c:if test="${pd.engageTime == 1 }">夜</c:if> <c:if
							test="${pd.engageTime == 2 }">白夜</c:if> <c:if test="${pd.engageTime == 3 }">大包</c:if>
				</span></td>
				<td colspan="3"><span style="margin-left: 10px;">承包起始日期：</span> <span style="margin-left: 2px;">${pd.contractSrtCount }</span>
				</td>
				<td colspan="3"><span style="margin-left: 10px;">承包结束日期：</span> <span style="margin-left: 2px;">${pd.contractEndCount }</span>
				</td>
			</tr>

			<tr>
				<td colspan="5"><span style="margin-left: 20px;">注册有效期：</span> <span style="margin-left: 2px;">${pd.expReg }</span>
				</td>
				<td colspan="5"><span style="margin-left: 10px;">决定（初领）日期：</span> <span style="margin-left: 2px;">${pd.decisionHours }</span>
				</td>
			</tr>
			<tr>
				<td colspan="5"><span style="margin-left: 20px;">受理日期：</span> <span style="margin-left: 2px;">${pd.approvalHours }</span>
				</td>
				<td colspan="5"><span style="margin-left: 10px;">补发从业证日期：</span> <span style="margin-left: 2px;">${pd.reCyzgDate }</span>
				</td>
			</tr>
			<tr>
				<td colspan="3"><span style="margin-left: 20px;">是否换证：</span> <span style="margin-left: 2px;">${pd.sfhz }</span>
				</td>
				<td colspan="2"><span style="margin-left: 10px;">生活状态：</span> <span style="margin-left: 2px;">${pd.shzt }</span>
				</td>
				<td colspan="5"><span style="margin-left: 10px;">人员状态：</span> <span style="margin-left: 2px;"> <c:if
							test="${pd.status == 0 }">报名成功</c:if> <c:if test="${pd.status == 1 }">考试通过</c:if> <c:if test="${pd.status == 2 }">考试未通过</c:if>
						<c:if test="${pd.status == 3 }">出证完成</c:if>
				</span> <span style="margin-left: 20px;">添加时间：</span> <span style="margin-left: 2px;">${pd.addtime }</span></td>
			</tr>
		</table>

		<c:if test="${flag == '0' }">
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th style="width: 30%;" class="center">年份等级</th>
					<th style="width: 60%;" class="center">等级</th>
					<th style="width: 10%;" class="center">操作</th>
				</tr>
				<!-- 开始循环 -->
				<c:choose>
					<c:when test="${not empty rank}">
						<c:forEach items="${rank}" var="var" varStatus="vs">
							<tr>
								<td class="center">${var.year }年度</td>
								<td class="center">${var.rank }级</td>
								<td class="center">
									<div class='btn-group'>
										<a class='btn btn-mini btn-info' title="编辑" onclick="editRank('${var.ID }','${var.YEAR }','${var.RANK }');"><i
											class='icon-edit'></i></a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th style="width: 30%;" class="center">延续时间</th>
				</tr>
				<!-- 开始循环 -->
				<c:choose>
					<c:when test="${not empty continueRegister}">
						<c:forEach items="${continueRegister}" var="var" varStatus="vs">
							<tr>
<%-- 								<td class="center">${var.r_reg_date }</td> --%>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</table>
		</c:if>
</body>
</html>