<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单管理</title>
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
					<a href="javascript:;" class="add" id="newData">
						<i class="fa fa-plus-square success"></i>
						添加
					</a>
				</div>
				<table id="menuList_dg"></table>
			</div>
		</div>
	</div>
	
	<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
	<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="../assets/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="../assets/js/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
		var $grid;
		$(function() {
			$grid = $('#menuList_dg');
			initGrid();
			//新增数据
			$('#newData').on('click', function(){
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area: ['490px', '450px'], //宽高
					title : [ '编辑信息', false ],
					content : 'goAdd',
					zIndex:1000
				});
			});
		});
		function initGrid() {
			$grid.treegrid({
				url : '../menu/listMenuTree',
				loadMsg : '数据加载中....',
				nowrap : true, //false:折行
				striped : true, //隔行变色
				checkOnSelect : true,
				idField : 'id',
				treeField : 'name',
				lines : true,
				//rownumbers: true,
				animate : true,
				columns : [ [ 
					{field : 'name',title : '资源名称',width : 180}, 
					{field : 'url',title : '资源路径'}, 
					{field : 'icon',title : '图标'}, 
					{field : 'sort',title : '排序'}, 
					{field : 'opt',title : '操作',width : 100,align : 'center',
						formatter : function(value, row) {
							var s = '<div class ="updateBtn">';
							s += '<a href="javascript:void(0);" title="删除"  onclick="delRow('+row.id+')" class="danger delMsg"><i class="fa fa-trash"></i></a>';
							s += ' <a href="javascript:void(0);" title="编辑" onclick="editRow('+row.id+')" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
							if (row.parent_id == "0") {
								return "";
							}
							return s;
						}
					} ] ]
			});
		}
		
		
		function delRow(id) {
			$.ajax({
				cache : false,
				type : "POST",
				url : "../menu/delete",
				data : {
					id : id
				},
				async : false,
				success : function(data) {
					if (data) {
						$('#menuList_dg').treegrid('reload');// 刷新datagrid
					} else {
						msgShow('系统提示', '出现异常');
					}
				}
			});
		}
		
		function editRow(id) {
			
			layer.open({
				type : 2,
				skin : 'layui-layer-rim', //加上边框
				hade : [ 0.5, '#000', false ],
				area : [ '490px', '450px'], //宽高
				title : [ '编辑信息', false ],
				content : 'goEdit?id=' + id
			});
		}
	</script>
</body>
</html>