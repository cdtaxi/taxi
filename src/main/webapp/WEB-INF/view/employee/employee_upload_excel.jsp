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
	
	function submitForm() {
		/* var validate = $("#vui_sample").form('validate');
		if (!validate) {
			return validate;
		} */
		if($("#excel").val()=="" || document.getElementById("excel").files[0] =='请选择xls格式的文件'){
			
			alert("请选择文件！");
			return false;
		}
		var formData = new FormData(document.getElementById("vui_sample"));
		//var data =$("#vui_sample").serializeArray();
		
		$.ajax({
			type : "POST",
			url : "../employee/readExcel",
			data : formData,
			async : false,
			cache : false,
			contentType: false,
            processData: false,
			success : function(data) {
				if (data>0) {
					alert("成功导入"+data+"条！");
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.$('#list_data').datagrid('reload');
				} else {
					//msgShow('系统提示', '出现异常');
					alert("导入失败！");
				}
			}
		});

		return false;
	}
	function fileType(obj){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(fileType != '.xls'){
	    	alert("请上传xls格式的文件!");
	    	$("#excel").val('');
	    	document.getElementById("excel").files[0] = '请选择xls格式的文件';
	    }
	}
	function closeForm() {//重置表单
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</head>
<body>
	
	<div class="form1-column" title="表单示例">
			<form id="vui_sample" class="easyui-form" >		
				
				<div class="form-column1">
					<div class="form-column-left">
						<input type="file" id="excel" name="excel" style="width:200px;" onchange="fileType(this)" />
					</div>
				</div>
				<div style="text-align: center;">
					<input type="submit" name="" value="导入" class="easyui-linkbutton btnPrimary" onclick="submitForm()"
						style="width: 80px" /> <input type="submit" name="" value="取消" class="easyui-linkbutton btnDefault"
						onclick="closeForm()" style="width: 80px" /> <input value="下载模板" class="easyui-linkbutton btnSuccess" onclick="window.location.href='downExcel'"
						style="width: 120px" />
				</div>
			</form>
		</div>
</body>
</html>