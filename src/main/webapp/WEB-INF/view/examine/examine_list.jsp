<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考核记录</title>
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
							<input type="text" class="easyui-textbox" id="cyzgCard" data-options="label:'从业资格证号'" />
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
				<table id="list_data"></table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var $grid;
		var curUserComCode;
		$(function() {
			$grid = $("#list_data");
			initGrid();
			$('#queryData').on('click', function() {
				$grid.datagrid({
					queryParams : {
						name : $('#name').val(),
						cyzgCard : $('#cyzgCard').val()
					}
				});
			});
		});
		function initGrid() {
			//datagrid初始化 
			$grid.datagrid({
				url : 'list',
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
			return [ [{
				title : '姓名',
				field : 'name'
			}, {
				title : '身份证号',
				field : 'empId'
			}, {
				title : '从业资格证号',
				field : 'cyzgCard'
			}, {
				title : '扣分条款',
				field : 'ordinal'
			}, {
				title : '扣分分数',
				field : 'scoring',
				align : 'right',
				formatter : function(value, row) {
					 if(row.ordinal.indexOf("6-") != -1 ){
						 return "+"+value; 
					 }else{
						 return "-"+value; 
					 }
					
				}
			}, {
				title : '扣分时间',
				field : 'examineTime'
			},{
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
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '450px', '550px' ], //宽高
					title : [ '编辑信息', false ],
					content : 'goEdit?id=' + id
				});
			
		}
		//删除
		function delRow(id) {
			
				$.messager.confirm('提示', '确定要删除该记录?', function(r) {
					if (r) {
						$.ajax({
							url : "delete?id=" + id,
							success : function(data) {
								if (data) {
									$grid.datagrid('reload');
								} else {
									showError("操作失败");
									msgShow('系统提示', '出现异常');
								}
							}
						});
					}
				});
		
		}
		//导出excel
		function toExcel(){
			 var name = $('#name').val();
			 var cyzgCard = $('#cyzgCard').val();
			    window.location.href='goExcel?name='
						+encodeURI(encodeURI(name))
						+'&cyzgCard='+encodeURI(encodeURI(cyzgCard)); 
			
		}
	</script>
</body>
</html>

