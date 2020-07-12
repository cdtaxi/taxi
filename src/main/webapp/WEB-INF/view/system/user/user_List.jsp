<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<%@ include file="/common/taglibs.jsp"%>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="comp-search-box">
					<div class="screen-top">
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="name" data-options="label:'姓名'" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" id="queryData">
								<i class="fa fa-search"></i>查询
							</button>
						</div>
						<div class="colRow">
						<button class="easyui-linkbutton btnDefault" onclick="toExcel()">
								<i class="fa fa-download"></i>
								导出
							</button>						
						</div>
					</div>
				</div>
				<div class="btnbar-tools">
					<a href="javascript:;" class="add" id="newData"><i class="fa fa-plus-square success"></i>添加</a>
				</div>
				<table id="userList_dg"></table>
			</div>
		</div>
	</div>
	<!-- 新增和编辑数据 -->
	<div class="dig-wrapper" id="newData-wrapper">
		<div class="form1-column" title="表单示例">
			<form id="vui_sample" class="easyui-form" >
				<input type="hidden" name="id">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="username" style="width: 100%" data-options="label:'用户名称',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="realname" style="width: 100%" data-options="label:'姓名:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="phone" style="width: 100%" data-options="label:'联系电话:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input id="unitnameCombox" class="easyui-combobox" name="departName"
							data-options="
									label:'单位',
									url: '../enterprise/queryEnterpriseList',
									valueField:'unitname',
									textField:'unitname'"
							style="width: 100%; height: 32px">
					</div>
				</div>	<div class="form-column1">
					<div class="form-column-left">
						<input id="unitnameCombox" class="easyui-combobox" name="roleId"
							data-options="
									label:'角色',
									url: '../role/list',
									required:true,
									valueField:'id',
									textField:'name'"
							style="width: 100%; height: 32px">
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()"
						style="width: 80px" /> <input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault"
						onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#userList_dg').datagrid({
				url : '../user/list',//url调用Action方法  
				loadMsg : '数据装载中......',
				singleSelect : true,//为true时只能选择单行  
				fitColumns : true,//允许表格自动缩放，以适应父容器  
				remoteSort : false,
				pagination : true,//分页  
				rownumbers : true,//行数  
				pageSize : 10,//默认选择的分页是每页5行数据  
				pageList : [ 10, 15, 20 ],//可以选择的分页集合  
				nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
				striped : true,//设置为true将交替显示行背景。 
				columns : getColumns()
			});
			//查询数据
			$('#queryData').on('click', function() {
				$('#userList_dg').datagrid({
					queryParams : {
						name : $('#name').val()
					}
				});
			});
			//新增数据
			$('#newData').on('click', function(){
				layer.open({
					type: 1,
					title:"用户信息",
					skin: 'layui-layer-rim', //加上边框
					area: ['490px', '350px'], //宽高
					content:$('#newData-wrapper'),
					zIndex:1000
				});
			});
		});
		function getColumns() {
			return [ [
					{title : '状态',field : 'status',formatter : function(value, rec) { return value == 'enabled' ? '启用' : '<span style="color:red">禁用</span>'; } },
					{title : '用户名',field : 'username'	},
					{title : '姓名',field : 'realname'},
					{title : '电话',field : 'phone'},
// 					{title : '性别',field : 'sex',formatter : function(value) {if (value == "1") {return "男";} else {return "女";}	}},
					{title : '单位',field : 'departName'},
					{field : 'opt',title : '操作',align : 'center',	formatter : function(value, row,index) {
						var s = '<div class ="updateBtn">';
						s += '<a href="javascript:void(0);" title="删除"  onclick="delRow('+row.id+')" class="danger delMsg"><i class="fa fa-trash"></i></a>';
						s += ' <a href="javascript:void(0);" title="编辑" onclick="editRow('+index+')" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
						return s;
						}
					} ] ];
		}
		function submitForm(){//保存提交
			//校验
			var validate = $("#vui_sample").form('validate');
			if (!validate) {
				return validate;
			}
			var data =$("#vui_sample").serializeArray();
			$.ajax({
				cache : false,
				type : "POST",
				url : "../user/add",
				data : data,
				async : false,
				success : function(data) {
					if (data) {
						$('#userList_dg').datagrid('reload');// 刷新datagrid
						layer.close(layer.index); 
						clearForm();
					} else {
						msgShow('系统提示', '出现异常');
					}
				}
			});
		}
		function clearForm(){//重置表单
			$('#vui_sample').form('clear');
		}
		function delRow(id) {
			$.ajax({
				cache : false,
				type : "POST",
				url : "../user/delete",
				data : {
					id : id
				},
				async : false,
				success : function(data) {
					if (data) {
						$('#userList_dg').datagrid('reload');// 刷新datagrid
					} else {
						msgShow('系统提示', '出现异常');
					}
				}
			});
		}
		function editRow(index) {
			var row = $('#userList_dg').datagrid('getData').rows[index];
			//var row = $('#userList_dg').datagrid('getSelected');
			if (row) {
				layer.open({
					type : 1,
					title : "用户信息",
					skin : 'layui-layer-rim', //加上边框
					area : [ '490px', '350px' ], //宽高
					content : $('#newData-wrapper'),
					zIndex : 1000
				});
				$("#vui_sample").form("load", row);
			}
		}
		//导出excel
		function toExcel(){
			 var name = $('#name').val();
			    window.location.href='goExcel?name='
						+encodeURI(encodeURI(name));  
			
		}
	</script>
</body>
</html>