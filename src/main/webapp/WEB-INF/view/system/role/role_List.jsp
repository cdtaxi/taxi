<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色管理</title>
<link href="../assets/css/reset.css" rel="stylesheet" type="text/css" />
<link href="../assets/js/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/layout.css" rel="stylesheet" type="text/css" />
</head>
<body scroll="no" class="body-pd10">

	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="btnbar-tools">
					<a href="javascript:;" class="add" id="newData"><i class="fa fa-plus-square success"></i>添加</a>
				</div>
				<table id="roleList_dg"></table>
			</div>
		</div>
	</div>
	<!-- 新增和编辑数据 -->
	<div class="dig-wrapper" id="newData-wrapper" style="text-align: center;">
		<form id="vui_sample" class="easyui-form">
			<input type="hidden" name="id">
			<div class="form-column1">
				<div class="form-column-left">
					<input class="easyui-textbox" id="rolenameText" name="name" style="width: 350px" data-options="required:true">
				</div>
			</div>
			<div class="form-btnBar ">
				<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()"
					style="width: 80px" />
			</div>
		</form>
	</div>
	<div class="dig-wrapper" id="roleData-wrapper" style="text-align: left: ;">
		<input id="roleId" type="hidden" />
		 <div class="btnbar-tools">
			<a href="javascript:;" class="add" onclick="roleModule()">
				<i class="fa fa-plus-square success"></i>
				确定
			</a>
		</div>
		<ul id="reslist" style="margin-top: 15px"></ul>
	</div>
	<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
	<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="../assets/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="../assets/js/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$('#roleList_dg').datagrid({
				url : '../role/list',//url调用Action方法  
				loadMsg : '数据装载中......',
				singleSelect : true,//为true时只能选择单行  
				fitColumns : true,//允许表格自动缩放，以适应父容器  
				remoteSort : false,
				rownumbers : true,//行数  
				nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
				striped : true,//设置为true将交替显示行背景。 
				columns : getColumns()
			});
			//新增数据
			$('#newData').on('click', function(){
				layer.open({
					type: 1,
					title:"添加角色",
					skin: 'layui-layer-rim', //加上边框
					area: ['400px', '160px'], //宽高
					content:$('#newData-wrapper'),
					zIndex:1000
				});
			});
		});
		function getColumns() {
			return [ [
					{title : '角色',field : 'name',width: 200},
// 					{title : '增',field : 'phone',align : 'center',width: 10},
// 					{title : '删',field : 'del_opt',align : 'center',width: 10},
// 					{title : '改',field : 'edit_opt',align : 'center',width: 10},
// 					{title : '查',field : 'cha_opt',align : 'center',width: 10},
					{field : 'opt',title : '操作',align : 'center',width: 50,formatter : function(value, row, index) {
							var s = '<div class ="updateBtn">';
							s += '<a href="javascript:;" title="删除"  onclick="delRole('+row.id+')" class="danger delMsg"><i class="fa fa-trash"></i></a>';
							s += ' <a href="javascript:void(0);;" title="编辑" onclick="editRow('+index+')" class="info"><i class="fa fa-pencil-square-o"></i></a>';
							s += ' <a href="javascript:void(0);;" title="权限" onclick="qxRow('+index+')" class="info"><i class="fa fa-check-circle yellow"></i>权限</a></div>';
							return s;
						}
					} ] ];
			}
		
		
		function submitForm() {
			var data = {
				name : $("#rolenameText").val()
			}
			$.ajax({
				cache : false,
				type : "POST",
				url : "../role/add",
				data : data,
				async : false,
				success : function(data) {
					if (data) {
						$('#roleList_dg').datagrid('reload');// 刷新datagrid
						layer.close(layer.index); 
					} else {
						msgShow('系统提示', '出现异常');
					}
				}
			});
		}
		
		function delRole(id) {
			$.ajax({
				cache : false,
				type : "POST",
				url : "../role/delete",
				data : {
					id : id
				},
				async : false,
				success : function(data) {
					if (data) {
						$('#roleList_dg').datagrid('reload');// 刷新datagrid
					} else {
						msgShow('系统提示', '出现异常');
					}
				}
			});
		}
		
		function qxRow(index) {
			var row = $('#roleList_dg').datagrid('getData').rows[index];
			$("#roleId").val(row.id);
			if (row) {
				layer.open({
					type : 1,
					title : "角色信息",
					skin : 'layui-layer-rim', //加上边框
					area : [ '300px', '420px' ], //宽高
					content : $('#roleData-wrapper'),
					zIndex : 1000
				});
				$('#reslist').tree({
					url : '../menu/role2Module?roleId=' + row.id,
					loadMsg : '数据加载中....',
					lines : true,
					checkbox : true
				});
			}
		}
		function editRow(index) {
			var row = $('#roleList_dg').datagrid('getData').rows[index];
			//var row = $('#roleList_dg').datagrid('getSelected');
			if (row) {
				layer.open({
					type : 1,
					title : "角色信息",
					skin : 'layui-layer-rim', //加上边框
					area : [ '490px', '160px' ], //宽高
					content : $('#newData-wrapper'),
					zIndex : 1000
				});
				$("#vui_sample").form("load", row);
			}
		}
		/* 保存权限设置 */
		function roleModule() {
			var nodes = $('#reslist').tree('getChecked', [ 'checked', 'indeterminate' ]);
			var ids = [];
			for (var i = 0; i < nodes.length; i++) {
				ids.push(nodes[i].id);
			}
			if (ids.length > 0) {
				var param = {
					roleid : $("#roleId").val(),
					ids : ids
				};
				$.ajax({
					url : "../role/saveRoleRes",
					type : "POST",
					data : param,
					async : false,
					dataType : "json",
					cache : false,
					success : function(data) {
						if (data) {
							$.messager.show({
								msg : '设置成功！'
							});
							layer.close(layer.index); 
						} else {
							$.messager.show({
								title : 'Error',
								msg : '不好意思，出错了！'
							});
						}
					}
				});
			} else {
				alert("请选择分配资源!");
			}
		}
	</script>
</body>
</html>