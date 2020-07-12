<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执法人员信息</title>
<%@ include file="/common/taglibs.jsp"%>
<script>
	var $grid;
	var curUserComCode;
	$(function() {
		$grid = $("#list_data");
		initGrid();
	});
	function initGrid() {
		//datagrid初始化 
		$grid.datagrid({
			url : '../case/zfryListData',
			striped : true,
			nowrap : false,
			rownumbers : true,
			loadMsg : '正在加载中，请稍等... ',
			emptyMsg : '<span>无记录</span>',
			pagination : true,
			singleSelect : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 100 ],
			columns : [ [ {
				field : 'zfrxm',
				title : '姓名',
				width : 150
			}, {
				field : 'zfzh',
				title : '执法证号',
 				width : 150
 			}, {
				field : 'zw',
				title : '职务',
				width : 150
			},{
				field : 'opt',
				title : '操作',
				align : 'center',
				formatter : function(value, row) {
					var s = '<div class ="updateBtn">';
					s += '<a href="javascript:void(0);" title="查看" onclick="show(\''+row.id+'\')" class="danger delMsg"><i class="fa fa-eye"></i>查看</a></div>';
					//s += '<a href="javascript:void(0);" title="删除" onclick="delRow()" class="danger delMsg"><i class="fa fa-trash"></i></a>';
					//s += '<a href="javascript:void(0);" title="编辑" onclick="editRow()" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
					return s;
				}
			} ] ],
			onLoadSuccess : function(data) {
				if (data && data.rows && data.rows.length > 0) {
					$grid.datagrid("clearSelections");
				} else {
					$grid.datagrid("clearSelections");
				}
			}
		});
	}
	//修改
	function edit(id) {
		layer.open({
			type : 2,
			skin : 'layui-layer-rim', //加上边框
			hade : [ 0.5, '#000', false ],
			area : [ '860px', '540px' ],
			title : [ '添加或修改用信息', false ],
			content : 'goEdit?id=' + id
		});
	}
	//详情
	function show(id) {
		layer.open({
			type : 2,
			skin : 'layui-layer-rim', //加上边框
			hade : [ 0.5, '#000', false ],
			area : [ '860px', '600px' ], //宽高
			content : '../goShow?id=' + id
		});
	}
	//删除
	function del(id) {
		$.messager.confirm('提示', '确定要删除该记录?', function(r) {
			if (r) {
				$.ajax({
					url : "delete?id=" + id,
					success : function(data) {
						if (data) {
							$grid.datagrid('reload');
						} else {
							showError("操作失败");
						}
					}
				});
			}
		});
	}
	//查询
	function query() {
		$grid.datagrid({
			queryParams : {
				name : $('#name').val(),
				status : $('#status').val(),
				company : $('#company').val()
			}
		});
	}
	//导出excel
	function toExcel(){
		
		 var name=$('#name').val();
		var status=$('#status').val();
		var company=$('#company').val();
		window.location.href='../goExcel?name='
				+encodeURI(encodeURI(name))+'&status='+encodeURI(encodeURI(status))
				+'&company='+encodeURI(encodeURI(company)); 
	}
	//导入
	function fromExcel() {
		
		layer.open({
			type : 2,
			skin : 'layui-layer-rim', //加上边框
			hade : [ 0.5, '#000', false ],
			area : [ '450px', '200px'], //宽高
			title : [ '导入excel', false ],
			content : '../goUploadExcel'
		});
		
	
}
</script>
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
							<select class="easyui-combobox" name="status" id="status" data-options="label:'状态'" style="vertical-align: top; width: 120px;">
								<option value=""></option>
								<option value="">全部</option>
								<option value="0">报名成功</option>
								<option value="1">考试通过</option>
								<option value="2">考试未通过</option>
								<option value="3">出证完成</option>
							</select>
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="company" data-options="label:'公司名称'" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" id="queryData" onclick="query()">
								<i class="fa fa-search"></i>
								查询
							</button>
						</div>
						<div class="colRow">
						<button class="easyui-linkbutton btnDefault"  onclick="toExcel()">
								<i class="fa fa-download"></i>
								导出
							</button>						
						</div>
						<div class="colRow">
						<button class="easyui-linkbutton btnDefault" onclick="fromExcel()">
								<i class="fa fa-upload"></i>
								导入
							</button>						
						</div>
					</div>
				</div>
				<table id="list_data"></table>
			</div>
		</div>
	</div>
</body>
</html>
