<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>承德市出租汽车管理系统</title>
<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/My97DatePicker/skin/WdatePicker.css">
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="comp-search-box">
					<div class="screen-top">
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="operatingnum" data-options="label:'营运证号'" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" id="queryData">
								<i class="fa fa-search"></i>
								查询
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
					<a href="javascript:;" class="add" id="newData">
						<i class="fa fa-plus-square success"></i>
						添加
					</a>
				</div>
				<table id="list_data"></table>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var $grid;
		$(function() {
			$grid = $("#list_data");
			initGrid();
			
			//新增数据
			$('#newData').on('click', function() {
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '1000px', '460px' ], //宽高
					title : [ '编辑信息', false ],
					content : 'goAdd'
				});
				
			});
			$('#queryData').on('click', function() {
				$grid.datagrid({
					queryParams : {
						operatingnum : $('#operatingnum').val()
					}
				});
			});
		});
		function initGrid() {
			//datagrid初始化 
			$grid.datagrid({
				url : '../taxicarCheck/checkList',
				striped : true,
				nowrap : false,
				rownumbers : true,
				loadMsg : '正在加载中，请稍等... ',
				emptyMsg : '<span>无记录</span>',
				pagination : true,
				singleSelect : true,
				fitColumns : true,
				idField : 'pkey',
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 100 ],
				columns : getColumns()
			});
		}
		function getColumns() {
			return [ [ 
			           		{								
								field : 'operatingnum',
								title : '营运证号'
							}, {								
								field : 'annualdate',
								title : '年审日期'
							}, {								
								field : 'nextannualdate',
								title : '终止日期'
							},  {
								field : 'status',
								title : '状态',
								align : 'center',
								formatter : function(value, row) {
										if(value==0){
											return '<span style="color:DarkGreen">年审完成</span>';
										}
										else if(value==1){
											return '<span style="color:Chocolate">待上级审核</span>';
										}
										else if(value==2){
											return '<span style="color:red">上级审核完成</span>';
										}
										else if(value==3){
											return '<span style="color:DimGray">上级审核失败</span>';
										}
										
									}
								}, {
							field : 'opt',
							title : '操作',
							align : 'center',
							formatter : function(value, row) {
								var s = '<div class ="updateBtn">';
								s += '<a href="javascript:void(0);" title="删除"  onclick="delRow(\''+row.id+'\')" class="danger delMsg"><i class="fa fa-trash"></i></a>';
								s += ' <a href="javascript:void(0);" title="编辑" onclick="editRow(\''+row.id+'\')" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
								return s;
							}
						} ] ];
		}
		
		//修改
		function editRow(id) {
			if (id) {
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '1000px', '460px'], //宽高
					title : [ '编辑信息', false ],
					content : 'goEdit?id=' + id
				});
				
			}
		}
		
		//删除
		function delRow(id) {
			if (confirm("确定要删除吗？")) {
				$.ajax({
					cache : false,
					type : "POST",
					url : "../taxicarCheck/deleteCheck",
					data : {
						id : id
					},
					async : false,
					success : function(data) {
						if (data) {
							$grid.datagrid('reload');// 刷新datagrid
						} else {
							msgShow('系统提示', '出现异常');
						}
					}
				});
				}
		}
		//导出excel
		function toExcel(){
			 var operatingnum=$('#operatingnum').val();
			    window.location.href='goExcel?operatingnum='
						+encodeURI(encodeURI(operatingnum));  
			
		}
	</script>

</body>
</html>
