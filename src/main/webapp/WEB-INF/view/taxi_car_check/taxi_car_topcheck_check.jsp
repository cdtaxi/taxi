<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试报名</title>
<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript">
	$(function() {
	$('#corpName').combobox({
		url : '../enterprise/queryEnterpriseList',
		valueField : 'unitname',
		textField : 'unitname',
		onSelect : function(data) {
			$("#corpName").textbox('setValue', data.unitname);
		}
	});
	});
	
	function pass(id,status){//修改状态
		
		$.ajax({
			type : "post",
			dataType : "json",
			url : '../taxicarCheck/editCheckStatus',
			data:{id:id,status:status},
			async : true,
			success : function(result) {
				if (result) {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.$('#list_data').datagrid('reload');
				} else {
					showError("审核失败！");
				}
			}
		})
	}
	
	
</script>
</head>
<body>

	<c:choose>
			<c:when test="${not empty taxicar}">
	<div id="showPart" data-options="region:'center',border:true">
		<div title="表单示例" >
			<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true">
			
			
				<table style="table-layout: fixed;" id="table_report" border="1"  class="table table-striped table-bordered table-hover">
		<tr>
			  <td colspan="3"><span
				style="margin-left: 20px;">档案号：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="fileNum" value="${taxicar.fileNum }" type="text" style="width: 130px;" /></span></td>
			<td colspan="3"><span
				style="margin-left: 20px;">车主姓名：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="ownerName" value="${taxicar.ownerName }" type="text" style="width: 120px;" /></span></td>
			<td colspan="2">
				<span style="margin-left: 20px;">性别：</span>
					<span style="margin-left: 2px;">
						<select  class="easyui-combobox" style="width: 80px;" id="ownerSex" name="ownerSex" title="性别">
							<option value='0' <c:if test="${taxicar.ownerSex == '0' }">selected</c:if>>男</option>
							<option value='1' <c:if test="${taxicar.ownerSex == '1' }">selected</c:if>>女</option>
						</select>
					</span></td>
			<td colspan="3" style="width: 150px;" rowspan="5">
				<div style="width: 125px; height: 155px; margin: 2px;">
					<img id="uploadPreviewOwnerNamePic" style="width: 125px; height: 155px;" src="../taxicar/getOwnernamepic?id=${taxicar.id}"/>
					<div style="text-align: center;">
	         			<a href="javascript:;" class="a-upload" style="margin-top: 5px;">
					    	<input id="ownernamepic1" name="ownernamepic1"  type="file"  onchange="loadImageFileOwnerNamePic();"/>上传车主照片
						</a>
	         		</div>
				</div>
			</td>
          </tr>
          <tr>
			 <td colspan="3">
              <span style="margin-left:20px;">营运证号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="opretaCertNum" value="${taxicar.opretaCertNum }" type="text" style="width: 115px;" /></span>
            </td>
            <td colspan="3">
              <span style="margin-left:20px;">原车主：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="origOwnerName" value="${taxicar.origOwnerName }" type="text" style="width: 135px;" /></span>
            </td>
            <td colspan="2">
            <span style="margin-left:20px;">车型：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="sign" value="${taxicar.sign }" type="text" style="width: 80px;" /></span>
                   
            </td>
          </tr>
          <tr>
            <td colspan="3">
              <span style="margin-left:4px;">公司名称：</span>
                    <span style="margin-left:2px;"><input id="corpName" name="corpName" value="${taxicar.corpName }" type="text" style="width: 180px;" /></span>
            </td>
            <td colspan="3">
              <span style="margin-left:20px;">联系电话：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="phoneNum" value="${taxicar.phoneNum }" type="text" style="width: 120px;" /></span>
            </td>
            <td colspan="2">
              <span
				style="margin-left: 20px;">车身颜色：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="color" value="${taxicar.color }" type="text" style="width: 60px;" /></span>
            </td>
          </tr>
          <tr>
            <td colspan="8">
              <span style="margin-left:20px;">变更日期：</span>
                    <span style="margin-left:2px;"><input class="Wdate" name="transferDate" value="${taxicar.transferDate }"  style="width: 120px;" 
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
              <span style="margin-left:20px;">变更记录：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="ownerChange" value="${taxicar.ownerChange }" type="text" style="width: 180px;" /></span>
              <span style="margin-left:20px;">查封记录：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="sealRecord" value="${taxicar.sealRecord }" type="text" style="width: 180px;" /></span>
            </td>
          </tr>
          <tr>
            <td colspan="8">
              <span style="margin-left:20px;">车牌号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="plateNum" value="${taxicar.plateNum }" type="text" style="width: 100px;" /></span>
              <span style="margin-left:20px;">原车号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="origPlateNum" value="${taxicar.origPlateNum }" type="text" style="width: 100px;" /></span>
              <span style="margin-left:20px;">行驶证初次登记：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="drvLicenseDate" value="${taxicar.drvLicenseDate }" type="text" style="width: 180px;" /></span>
            </td>
          </tr>
          <tr>
			<td colspan="4"><span
				style="margin-left: 20px;">身份证号：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="iDNumber" value="${taxicar.iDNumber }" type="text" style="width: 250px;" /></span></td>
			<td colspan="3"> <span style="margin-left: 20px;">车辆所属地区：</span>
					<span style="margin-left: 2px;">
						<select  class="easyui-combobox" style="width: 120px;" id="area" name="area">
							<option value=''></option>
							<option value='市区' <c:if test="${taxicar.area == '0' }">selected</c:if>>市区</option>
							<option value='双滦' <c:if test="${taxicar.area == '0' }">selected</c:if>>双滦</option>
							<option value='双滦代管' <c:if test="${taxicar.area == '0' }">selected</c:if>>双滦代管</option>
						</select>
					</span></td>
			<td colspan="4" style="width: 202px;" rowspan="4">
				<div style="width: 185px; height: 110px; margin: 2px;margin-left: 15px;">
						<img id="uploadPreviewVehiclePic" style="width: 185px; height: 110px;"  src="../taxicar/getVehiclepic?id=${taxicar.id}"/>
					
					<div style="text-align: center;">
	         			<a href="javascript:;" class="a-upload" style="margin-top: 3px;width: 150px;" >
					    	<input id="vehiclepic1" name="vehiclepic1"  type="file"  onchange="loadImageFileVehiclepic();"/>上传车身照片
						</a>
	         		</div>
				</div>
			</td>
          </tr>
          <tr>
            <td colspan="7">
              <span style="margin-left:20px;">家庭住址：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="address" value="${taxicar.address }" type="text" style="width: 450px;" /></span>
            </td>
          </tr>
          <tr>
          	<td colspan="7">
              <span style="margin-left:20px;">吨（座）位：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="tonsSeat" value="${taxicar.tonsSeat }" type="text" style="width: 60px;" /></span>
              <span style="margin-left:20px;">长：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="carLen" value="${taxicar.carLen }" style="width:60px;" type="text"></span>
              <span style="margin-left:20px;">宽：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="carWidth" value="${taxicar.carWidth }" style="width:60px;" type="text"></span>
              <span style="margin-left:20px;">高：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="carHigh" value="${taxicar.carHigh }" style="width:60px;" type="text"></span>
            </td>
          </tr>
          <tr>
          	<td colspan="7">
              <span style="margin-left:20px;">车架号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="frameNumber" value="${taxicar.frameNumber }" style="width:270px;" type="text"></span>
            </td>
            
          </tr>
          <tr>
          <td colspan="6">
              <span style="margin-left:20px;">发动机号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="engineNumber" value="${taxicar.engineNumber }" style="width:270px;" type="text"></span>
            </td>
          	<td colspan="5">
              <span style="margin-left:20px;">发证日期：</span>
                    <span style="margin-left:2px;"><input  class="Wdate" name="checkDate" value="${taxicar.checkDate }" style="width: 200px;" 
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
            </td>
          </tr>
          <tr>
          	<td colspan="11">
              <span style="margin-left:20px;">车辆审验记录：</span>
                    <span style="margin-left:2px;">
                    	<input class="easyui-textbox" name="vehicleInspRec"  value="${taxicar.vehicleInspRec }"type="text" style="width: 665px;height:60px;"  multiline="true"/>
                    </span>
            </td>
          </tr>
    

		</table>
				
			</form>
		<form id="vui_sample1" class="easyui-form" >
			<table  id="table_report1" style="table-layout: fixed;"  border="1" class="table table-striped table-bordered table-hover">
						<input type="hidden" name="id" value="${pd.id }">
						<input type="hidden" name="operatingnum"  value="${taxicar.opretaCertNum }"/>
						<tr>
				        	<td>
				            	<span style="margin-left:20px;">年审日期：</span>
		            		</td>
				        	<td>
				            	<span style="margin-left:10px;">
<input  class="Wdate" name="annualdate" style="width: 150px;"   value="${pd.annualdate }"
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
		            		</td>
				        	<td>
				            	<span style="margin-left:20px;">下一年审日期：</span>
		            		</td>
				        	<td>
				            	<span style="margin-left:10px;"><input  class="Wdate" name="nextannualdate" 
				            	style="width: 150px;"   value="${pd.nextannualdate }"
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
		            		</td>
		        		</tr>
		        		<tr>
				        	<td>
				            	<span style="margin-left:20px;">车辆级别：</span>
		            		</td>
				        	<td colspan="3">
				            	<span style="margin-left:10px;">
				            		<select style="width:150px;" class="easyui-combobox"  name="vehicle" title="车辆级别">
										<option value="1" <c:if test="${pd.vehicle == '1' }">selected</c:if>>一级车</option>
										<option value="2" <c:if test="${pd.vehicle == '2' }">selected</c:if>>二级车</option>
									</select>
								</span>
		            		</td>
		        		</tr>
						<tr>
							<td style="text-align: center;"  colspan="4">
								<a href="javascript:void(0);"
					class="easyui-linkbutton" style="margin: 5 0 0 5"
					data-options="iconCls:'icon-ok',plain:true" onclick="pass('${pd.id }','2');">通过</a>
								<a href="javascript:void(0);"
					class="easyui-linkbutton" style="margin: 5 0 0 5"
					data-options="iconCls:'icon-no',plain:true" onclick="pass('${pd.id }','3');">拒绝</a>
							</td>
						</tr>
					</table>
						
         </form>
         </div>
		</div>
		</c:when>
			</c:choose>
		<script type="text/javascript">
			 oFReaderOwnerNamePic = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
			oFReaderVehiclepic = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
			
			oFReaderOwnerNamePic.onload = function (oFREvent) {
			  document.getElementById("uploadPreviewOwnerNamePic").src = oFREvent.target.result;
			};
			oFReaderVehiclepic.onload = function (oFREvent) {
			  document.getElementById("uploadPreviewVehiclePic").src = oFREvent.target.result;
			}; 
			
			function loadImageFileOwnerNamePic() {
			  if (document.getElementById("ownernamepic1").files.length == 0 ){return; }
			  var oFile = document.getElementById("ownernamepic1").files[0];
			  if (!rFilter.test(oFile.type)) { alert("上传图片类型不符!"); return; }
			  oFReaderOwnerNamePic.readAsDataURL(oFile);
			}
			function loadImageFileVehiclepic() {
			  if (document.getElementById("vehiclepic1").files.length == 0 ){return; }
			  var oFile1 = document.getElementById("vehiclepic1").files[0];
			  if (!rFilter.test(oFile1.type)) { alert("上传图片类型不符!"); return; }
			  oFReaderVehiclepic.readAsDataURL(oFile1);
			}
			function closeImageFileVehiclepic(){
				oFReaderVehiclepic.close();
			}
		</script>
</body>
</html>