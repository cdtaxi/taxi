<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>车辆信息-查看</title>
<%@ include file="/common/taglibs.jsp"%>

<style type="text/css">
.a-upload {
	padding: 4px 10px; height: 20px; width: 128px; line-height: 6px;
	position: relative; cursor: pointer; color: #888; background: #fafafa;
	border: 1px solid #ddd; border-radius: 4px; overflow: hidden;
	display: inline-block; *display: inline; *zoom: 1
}

.a-upload  input {
	position: absolute; font-size: 100px; right: 0; top: 0; opacity: 0;
	filter: alpha(opacity = 0); cursor: pointer
}

.a-upload:hover {
	color: #444; background: #eee; border-color: #ccc; text-decoration: none
}
</style>

</head>
<body>
	<input type="hidden" name="id" id="id" value="${pd.id }" />
	<table style="table-layout: fixed;" id="table_report" border="1" class="table table-striped table-bordered table-hover">
		<tr>
			<td colspan="3"><span style="margin-left: 20px;">档案号：</span> <span style="margin-left: 2px;">${pd.fileNum }</span></td>
			<td colspan="3"><span style="margin-left: 20px;">车主姓名：</span> <span style="margin-left: 2px;">${pd.ownerName }</span></td>
			<td colspan="2"><span style="margin-left: 20px;">性别：</span> <span style="margin-left: 2px;">
					${pd.ownerSex} </span></td>
			<td colspan="3" style="width: 150px;" rowspan="5">
				<div style="width: 125px; height: 155px; margin: 2px;">
					<c:if test="${pd != null && pd.ownerNamePic != '' && pd.ownerNamePic != null}">
						<img id="uploadPreviewOwnerNamePic" style="width: 125px; height: 155px;"
							src="../taxicar/getOwnernamepic?id=${pd.id}" />
					</c:if>
					<c:if test="${pd == null || pd.ownerNamePic == '' || pd.ownerNamePic == null}">
						<img id="uploadPreviewOwnerNamePic" style="width: 125px; height: 155px;" />
					</c:if>

				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3"><span style="margin-left: 20px;">营运证号：</span> <span style="margin-left: 2px;">${pd.opretaCertNum }</span>
			</td>
			<td colspan="3"><span style="margin-left: 20px;">原车主：</span> <span style="margin-left: 2px;">${pd.origOwnerName }</span>
			</td>
			<td colspan="2"><span style="margin-left: 20px;">车型：</span> <span style="margin-left: 2px;">${pd.sign }</span></td>
		</tr>
		<tr>
			<td colspan="3"><span style="margin-left: 4px;">公司名称：</span> <span style="margin-left: 2px;">${pd.corpName }</span>
			</td>
			<td colspan="3"><span style="margin-left: 20px;">联系电话：</span> <span style="margin-left: 2px;">${pd.phoneNum }</span>
			</td>
			<td colspan="2"><span style="margin-left: 20px;">车身颜色：</span> <span style="margin-left: 2px;">${pd.color }</span>
			</td>
		</tr>
		<tr>
			<td colspan="8"><span style="margin-left: 20px;">变更日期：</span> <span style="margin-left: 2px;">${pd.transferDate }</span>
				<span style="margin-left: 20px;">变更记录：</span> <span style="margin-left: 2px;">${pd.ownerChange }</span> <span
				style="margin-left: 20px;">查封记录：</span> <span style="margin-left: 2px;">${pd.sealRecord }</span></td>
		</tr>
		<tr>
			<td colspan="8"><span style="margin-left: 20px;">车牌号：</span> <span style="margin-left: 2px;">${pd.plateNum }</span>
				<span style="margin-left: 20px;">原车号：</span> <span style="margin-left: 2px;">${pd.origPlateNum }</span> <span
				style="margin-left: 20px;">行驶证初次登记日期：</span> <span style="margin-left: 2px;">${pd.drvLicenseDate }</span></td>
		</tr>
		<tr>
			<td colspan="4"><span style="margin-left: 20px;">身份证号：</span> <span style="margin-left: 2px;">${pd.iDNumber }</span></td>
			<td colspan="3"><span style="margin-left: 20px;">车辆所属地区：</span> <span style="margin-left: 2px;">
					${pd.area} </span></td>
			<td colspan="4" style="width: 202px;" rowspan="4">
				<div style="width: 200px; height: 150px; margin: 2px; margin-left: 15px;">
					<c:if test="${pd != null && pd.vehiclePic != '' && pd.vehiclePic != null}">
						<img id="uploadPreviewVehiclePic" style="width: 210px; height: 145px;" src="../taxicar/getVehiclepic?id=${pd.id}" />
					</c:if>
					<c:if test="${pd == null || pd.vehiclePic == '' || pd.vehiclePic == null}">
						<img id="uploadPreviewVehiclePic" style="width: 210px; height: 145px;" />
					</c:if>

				</div>
			</td>
		</tr>
		<tr>
			<td colspan="7"><span style="margin-left: 20px;">家庭住址：</span> <span style="margin-left: 2px;">${pd.address }</span>
			</td>
		</tr>
		<tr>
			<td colspan="7"><span style="margin-left: 20px;">吨（座）位：</span> <span style="margin-left: 2px;">${pd.tonsSeat }</span>
				<span style="margin-left: 20px;">长：</span> <span style="margin-left: 2px;">${pd.carLen }</span> <span
				style="margin-left: 20px;">宽：</span> <span style="margin-left: 2px;">${pd.carWidth }</span> <span
				style="margin-left: 20px;">高：</span> <span style="margin-left: 2px;">${pd.carHigh }</span></td>
		</tr>
		<tr>
			<td colspan="4"><span style="margin-left: 20px;">车架号：</span> <span style="margin-left: 2px;">${pd.frameNumber }</span>
			</td>
			<td colspan="3"><span style="margin-left: 20px;">发动机号：</span> <span style="margin-left: 2px;">${pd.engineNumber }</span>
			</td>
		</tr>
		<tr>
			<td colspan="5"><span style="margin-left: 20px;">许可证号：</span> <span style="margin-left: 2px;">${pd.licenseKey }</span>
			</td>
			<td colspan="6"><span style="margin-left: 20px;">发证日期：</span> <span style="margin-left: 2px;">${pd.checkDate }</span>
			</td>
		</tr>
		<tr>
			<td colspan="11"><span style="margin-left: 20px;">座位数：</span> <span style="margin-left: 2px;">${pd.seating }</span>

				<span style="margin-left: 20px;">经营性质：</span> <span style="margin-left: 2px;"> ${pd.manageNature} </span> <span
				style="margin-left: 20px;">燃料类型：</span> <span style="margin-left: 2px;"> ${pd.fuelType} </span></td>
		</tr>
		<tr>
			<td colspan="11"><span style="margin-left: 20px;">车辆审验记录：</span> <span style="margin-left: 2px;">
					${pd.vehicleInspRec } </span></td>
		</tr>
		<c:if test="${flag == '0' }">
			<tr>
				<td style="text-align: center;" colspan="11"><input type="button" name="" value="打印汽车证"
					class="easyui-linkbutton btnPrimary" onclick="print()" style="width: 100px" />
					<span style="margin-left: 10px;">申请事项：</span> <span style="margin-left: 2px;"> <select
						 	class="easyui-combobox" style="width: 200px;" id="mattercombox">
							<option value='1'>车辆更新</option>
							<option value='2'>变更业务名称</option>
							<option value='3'>丢失补办</option>
					</select>
				</span><input type="button" name="" value="打印申请表"
					class="easyui-linkbutton btnPrimary" onclick="printApply()" style="width: 100px" /></td>
			</tr>
		</c:if>
	</table>
	<script type="text/javascript">
		function print() {
			var id = $("#id").val();
			var url = "TradingCards.do?id=" + id;
			window.open(url);
		}
		function printApply() {
			var id = $("#id").val();
			var mattercombox=$('#mattercombox').combobox('getValue');
			var url = "applyPage.do?id=" + id+"&matter="+mattercombox;
			window.open(url);
		}
	</script>


</body>
</html>