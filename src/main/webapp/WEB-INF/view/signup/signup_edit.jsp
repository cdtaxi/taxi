<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试报名</title>
<%@ include file="/common/taglibs.jsp"%>
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
	oFReader = new FileReader(),
			rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

	oFReader.onload = function(oFREvent) {
		document.getElementById("uploadPreview").src = oFREvent.target.result;
	};
	$(function() {
		$('#oldCompany').combobox({
			url : '../enterprise/queryEnterpriseList',
			valueField : 'unitname',
			textField : 'unitname',
			onSelect : function(data) {
				$("#oldCompany").textbox('setValue', data.unitname);
			}
		});
	})
	function loadImageFile() {
		if (document.getElementById("photo").files.length === 0) {
			return;
		}
		var oFile = document.getElementById("photo").files[0];
		if (!rFilter.test(oFile.type)) {
			alert("上传图片类型不符!");
			return;
		}
		oFReader.readAsDataURL(oFile);
	}

	function submitForm() {
		var validate = $("#vui_sample").form('validate');
		if (!validate) {
			return validate;
		}
		var formData = new FormData(document.getElementById("vui_sample"));
		$.ajax({
			url : "../employee/save",
			type : "post",
			data : formData,
			processData : false,
			contentType : false,
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
		$('#vui_sample').form('clear');
	}
</script>
</head>
<body>
	<form class="form-horizontal" name="Form" id="vui_sample" method="post" enctype="multipart/form-data" onsubmit="return submitForm();">
		<input type="hidden" name="id" id="id" value="${pd.id }" />
		<input type="hidden" name="company" id="company" value="${pd.company }" />
		<%-- <c:if test="${msg == 'save'}"> --%>
			<input type="hidden" name="status" id="status" value="0" />
		<%-- </c:if> --%>
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<td>
					<span style="margin-left: 20px;">姓名：</span>
					<span style="margin-left: 10px;">
						<input id="name" name="name" value="${pd.name }" style="width: 120px;" type="text" class="easyui-textbox" data-options="required:true" >
					</span>
					<span style="margin-left: 20px;">性别：</span>
					<span style="margin-left: 10px;">
						<select  class="easyui-combobox" style="width: 80px;" id="sex" name="sex" title="性别">
							<option value='0' <c:if test="${pd.sex == '0' }">selected</c:if>>男</option>
							<option value='1' <c:if test="${pd.sex == '1' }">selected</c:if>>女</option>
						</select>
					</span>
					<span style="margin-left: 30px;">出生年月：</span>
					<span style="margin-left: 10px;">
						<input class="Wdate" id="borndate" name="borndate" value="${pd.borndate}" 
						style="width: 120px;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</span>
				</td>
				<td style="width: 124px;" rowspan="4">
					<div style="width: 120px; height: 172px; margin: 2px;">
						<c:if test="${pd != null && pd.photo != '' && pd.photo != null}">
							<a href="../employee/getPhoto.do?id=${pd.id}" target="_blank">
								<img id="uploadPreview" style="width: 125px; height: 155px;" src="../employee/getPhoto?id=${pd.id}" />
							</a>
						</c:if>
						<c:if test="${pd == null || pd.photo == '' || pd.photo == null}">
							<img id="uploadPreview" style="width: 120px; height: 160px;" />
						</c:if>
						<div style="text-align: center;">
							<a href="javascript:;" class="a-upload" style="margin-top: 3px;">
								<input id="photo" name="photo" type="file" onchange="loadImageFile();" />
								点击上传照片
							</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">国籍：</span>
					<span style="margin-left: 10px;">
						<input id="nationality" name="nationality" value="${pd.nationality }" style="width: 90px;" type="text" class="easyui-textbox" validtype="CHS">
					</span>
					<span style="margin-left: 20px;">身份证号：</span>
					<span style="margin-left: 10px;">
						<input id="idcard" name="idcard" value="${pd.idcard }" style="width: 150px;" type="text" class="easyui-textbox" validtype="idcard" data-options="required:true">
					</span>
					<span style="margin-left: 20px;">文化程度：</span>
					<span style="margin-left: 10px;">
						<input id="educated" name="educated" value="${pd.educated }" style="width: 80px;" type="text" class="easyui-textbox" validtype="CHS">
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">联系电话：</span>
					<span style="margin-left: 10px;">
						<input id="phone" name="phone" value="${pd.phone }" style="width: 180px;" type="text" class="easyui-textbox" validtype="mobile" data-options="required:true">
					</span>
					<span style="margin-left: 20px;">申报材料页数：</span>
					<span style="margin-left: 10px;">
						<input id="infoPages" name="infoPages" value="${pd.infoPages }" style="width: 195px;" type="text" class="easyui-numberspinner">
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">驾驶证号：</span>
					<span style="margin-left: 10px;">
						<input id="driveCard" name="driveCard" value="${pd.driveCard }" style="width: 150px;" type="text" class="easyui-textbox" >
					</span>
					<span style="margin-left: 20px;">驾驶证初领日期：</span>
					<span style="margin-left: 10px;">
						<input class="Wdate" id="driveStartDate" name="driveStartDate" value="${pd.driveStartDate }" 
						style="width: 215px;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<span style="margin-left: 20px;">住址：</span>
					<span style="margin-left: 10px;">
						<input id="address" name="address" style="width: 690px;" type="text" value="${pd.address }" class="easyui-textbox" >
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<span style="margin-left: 20px;">准驾车型：</span>
					<span style="margin-left: 10px;">
						<input id="driveType" name="driveType" style="width: 145px;" type="text" value="${pd.driveType }" class="easyui-textbox" >
					</span>
					<span style="margin-left: 20px;">原属公司：</span>
					<span style="margin-left: 10px;">
					<input class="easyui-textbox" id="oldCompany" name="oldCompany" style="width: 170px" value="${pd.oldCompany }" >
					
					</span>
				</td>
			</tr>
			<tr>
				<td class="center" colspan="2" style="text-align: center;">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" style="width: 80px" />
					<input type="button" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>