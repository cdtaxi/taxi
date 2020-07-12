<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/My97DatePicker/skin/WdatePicker.css">
<style type="text/css">
.a-upload {
	padding: 4px 10px;
	height: 20px;
	width: 128px;
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

<script type="text/javascript">
	//检索
	function search() {
		$("#employeeRegisterForm").submit();
	}
	function submitForm() {
		var validate = $("#vui_sample").form('validate');
		if (!validate) {
			return validate;
		}
		var formData = new FormData(document.getElementById("vui_sample"));
		//var data =$("#vui_sample").serializeArray();
		$.ajax({
			url : "save",
			type : "POST",
			data : formData,
			async : false,
			cache : false,
			contentType: false,
            processData: false,
			success : function(res) {
				if (res) {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.$('#list_data').datagrid('reload');
				} else {
					msgShow('系统提示', '出现异常');
				}
			}
		});

		return false;
	}
	function clearForm() {//重置表单
		//$('#vui_sample').form('clear');
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				
			<div class="comp-search-box">
			<form action="../cancelReg/goAdd" method="post" name="employeeRegisterForm" id="employeeRegisterForm">
				<div class="screen-top">
					<div class="colRow">
						<input class="easyui-textbox" name="idcard" style="width: 300px">
			<input type="button" value="查询" class="easyui-linkbutton btnPrimary" style="width: 100px" onclick="search();" />
				</div>
			</div>
			</form>
			<form method="post" id="vui_sample">
			<input type="hidden" name="id" id="id" value="${pd.id }" />
			<c:if test="${msg == 'save'}">
			<input type="hidden" name="status" id="status" value="0" />
			</c:if>
			<div class="screen-term" style="display: block;">
			<c:choose>
			<c:when test="${not empty employee}">
				<div class="form2-column" style="margin: 0px 0px 0px 10px;">
					<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="name" data-options="label:'姓名:'" value="${employee.name }" editable="false">
						</div>
						<div class="form-column-left fm-left">
							<select  class="easyui-combobox" style="width: 80px;" name="sex"  data-options="label:'性别:'" editable="false">
								<option value='0' <c:if test="${employee.sex == '0' }">selected</c:if>>男</option>
								<option value='1' <c:if test="${employee.sex == '1' }">selected</c:if>>女</option>
							</select>
						</div>
						<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="cyzg_card" data-options="label:'从业资格证号:'" value="${employee.cyzgCard }" editable="false">
						</div>										
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="idcard" data-options="label:'身份证号:'" value="${employee.idcard }" editable="false">
						</div>
						</div>
						<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="borndate" data-options="label:'出生年月:'" value="${employee.borndate }" editable="false">
						</div>										
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="phone" data-options="label:'联系电话:'" value="${employee.phone }" editable="false">
						</div>
						</div>
						<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="address" data-options="label:'住址:'" value="${employee.address }" editable="false">
						</div>
						
					</div>
					<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="drive_card" data-options="label:'驾驶证号:'" value="${employee.driveCard }" editable="false">
						</div>
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="drive_start_date" data-options="label:'驾驶证初领日期:'" value="${employee.driveStartDate}" editable="false">
						</div>
					</div>
					<div class="form-column2">
						<div class="form-column-left">
							<input class="easyui-textbox" style="width: 100%" name="carid" data-options="label:'车号:'" value="${employee.carid}" editable="false" >
						</div>
						<div class="form-column-left fm-left">
							<input class="easyui-textbox" style="width: 100%" name="cartype" data-options="label:'注册时间:'" value="${employee.registerDate}" editable="false">
						</div>
					</div>
						
						<div  style="text-align: center;">
						<div  style="text-align: center;">
						<img id="cancelRegPic" style="width: 255px; height: 155px;"/>
						<!-- <img id="cancelRegPic0" style="width: 125px; height: 155px;"/>
						<img id="cancelRegPic1" style="width: 125px; height: 155px;"/>
						<img id="cancelRegPic2" style="width: 125px; height: 155px;"/>
						<img id="cancelRegPic3" style="width: 125px; height: 155px;"/>
						 <img id="cancelRegPic4" style="width: 125px; height: 155px;"/>  -->
						</div>
						<a href="javascript:;" class="a-upload" style="margin-top: 5px;">
							<!-- <input id="cancelRegfile" name="cancelRegfile"  type="file" multiple="multiple"
							  onchange="loadImageFileVehiclepic();"/>上传报废单 -->
							  <input id="cancelRegfile" name="cancelRegfile"  type="file" 
							  onchange="loadImageFileVehiclepic();"/>上传报废单
						</a>
						</div>			
						<div  class="form-btnBar pl75" style="text-align: center;">
							<input type="submit" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()"
							style="width: 80px" /> 
							<input type="submit" value="取消" class="easyui-linkbutton btnDefault"
							onclick="clearForm()" style="width: 80px" />
						</div>
					</div>
				</div>
			</c:when>
			</c:choose>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
			rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
			//var j=0;
			
			function loadImageFileVehiclepic() {
				var cancelFile=document.getElementById("cancelRegfile").files;				
			  if (cancelFile.length == 0 ){return; }
			 // for(var i=0;i<cancelFile.length;i++){
				  var oFile = cancelFile[0];
				  if (!rFilter.test(oFile.type)) { alert("上传图片类型不符!"); return; }
				  $("#cancelRegPic").attr("src",URL.createObjectURL(oFile));
				  //$("#cancelRegPic"+j).attr("src",URL.createObjectURL(oFile));
			//	  j++;
			//  }
			 
			}
			
		</script>
</body>
</html>