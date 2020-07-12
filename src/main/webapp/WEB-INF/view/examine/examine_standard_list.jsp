<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考核标准</title>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var $grid;
	$(function() {
		$grid = $('#listTree');
		initGrid();
		//新增数据
		$('#newData').on('click', function(){
			layer.open({
				type : 2,
				skin : 'layui-layer-rim', //加上边框
				hade : [ 0.5, '#000', false ],
				area : [ '490px', '450px' ], //宽高
				title : [ '编辑信息', false ],
				content : 'goAdd'
			});
		});
	});
	function initGrid() {
		$grid.treegrid({
		 			url : 'listTree',
		 			idField : 'id', //定义标识树节点的键名字段
		 			treeField : 'ordinal', //定义树节点的字段
		 			pagination : false,// 显示分页
		 			fitColumns : true, //设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
		 			columns : [ [{
		 				field : 'ordinal',
		 				title : '序号'
		 			}, {
		 				field : 'standard',
		 				title : '考核标准'
		 			}, {
		 				field : 'scoring',
		 				title : '计分标准'
		 			}, 
					{
		 				field : 'opt',
		 				title : '操作',
		 				width : 100,
		 				align : 'center',
						formatter : function(value, row) {
							var s = '<div class ="updateBtn">',s3='</div>';
							s1= ' <a href="javascript:void(0);" title="编辑" onclick="editRow('+row.id+')" class="info"><i class="fa fa-pencil-square-o"></i></a>';
							if (row.parent_id == "0") {
								return s+s1+s3;
							}
							s2= '<a href="javascript:void(0);" title="删除"  onclick="delRow('+row.id+')" class="danger delMsg"><i class="fa fa-trash"></i></a>';
							return s+s1+s2+s3;
						}
					}  ] ]
		 		});
	} 
	
	
	function delRow(id) {
		$.ajax({
			cache : false,
			type : "POST",
			url : "delete",
			data : {
				id : id
			},
			async : false,
			success : function(data) {
				if (data) {
					$('#listTree').treegrid('reload');// 刷新datagrid
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
			area : [ '490px', '450px' ], //宽高
			title : [ '编辑信息', false ],
			content : 'goEdit?id=' + id
		});			
	}
</script>
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
				<table id="listTree"></table> 
				
			</div>
		</div>
	</div>
	
</body>
</html>