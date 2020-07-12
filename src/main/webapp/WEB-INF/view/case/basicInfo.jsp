<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基本信息</title>
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
	$(function() {

	});

	function clearForm() {//重置表单
		$('#vui_sample').form('clear');
	}
</script>
</head>
<body>
	 
	<form id="vui_sample" class="easyui-form" method="post">
		<table style="table-layout: fixed;width: 1024px;margin-left: 20px;" id="table_report" border="1" class="table table-striped table-bordered table-hover">
			<tr>
				<td>
					<span style="margin-left: 20px;">缴款银行：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="jfyh" value="${jbxx.JFYH }" type="text" style="width: 400px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">复议单位：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="FYDW" value="${jbxx.FYDW }" type="text" style="width: 400px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">诉讼单位：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="SSDW" value="${jbxx.SSDW }" type="text" style="width: 400px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">执法主体：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="ZFZT" value="${jbxx.ZFZT }" type="text" style="width: 400px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">通知联系地址：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="fileNum" value="${jbxx.TZLXDZ }" type="text" style="width: 370px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">通知联系人：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="TZLXR" value="${jbxx.TZLXR }" type="text" style="width: 380px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">通知联系邮编：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="TZLXYB" value="${jbxx.TZLXYB }" type="text" style="width: 370px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">通知联系电话：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="TZLXDH" value="${jbxx.TZLXDH }" type="text" style="width: 370px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">讨论地点：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="TLDD" value="${jbxx.TLDD }" type="text" style="width: 400px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">改正要求：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="GZYQ" value="${jbxx.GZYQ }" type="text" style="width: 400px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">证据处理决定：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="ZJCLJD" value="${jbxx.ZJCLJD }" type="text" style="width: 370px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">	送达单位：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="SDDW" value="${jbxx.SDDW }" type="text" style="width: 400px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">案卷名称：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="AJMC" value="${jbxx.AJMC }" type="text" style="width: 400px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">案卷保管期限：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="ajbgqx" value="${jbxx.AJBGQX }" type="text" style="width:370px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="margin-left: 20px;">案卷件数：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="ajjs" value="${jbxx.AJJS }" type="text" style="width: 370px;" data-options="required:true" />
					</span>
				</td>
				<td>
					<span style="margin-left: 20px;">案卷全宗号：</span>
					<span style="margin-left: 2px;">
						<input class="easyui-textbox" name="ajqzh" value="${jbxx.AJQZH }" type="text" style="width: 370px;" data-options="required:true"/>
					</span>
				</td>
			</tr>
			<tr>
			<td style="text-align: center;" colspan="2">
				<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" />
					<input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
			</td>
		  </tr>
		</table>

	</form>
</body>
</html>