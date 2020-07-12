<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>承德市出租汽车管理系统</title>
<%@ include file="/common/taglibs.jsp"%>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="comp-search-box">
					<div class="screen-top">
						<div class="colRow">
						<input type="text" class="easyui-textbox" id="unitname" data-options="label:'单位名称'" />
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="businessno" data-options="label:'经营许可证号'" />
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="legalrepresentative" data-options="label:'法人代表'" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault"  id="queryData">
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
				<table id="list_data"></table>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	var $grid;
		$(function() {
			$grid = $("#list_data");
			$grid.datagrid({
				url : '../enterprise/list',//url调用Action方法  
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
			//新增数据
			$('#newData').on('click', function(){
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '490px', '500px' ], //宽高
					title : [ '编辑信息', false ],
					content : 'goAdd'
				});
			});
			//查询
			$('#queryData').on('click', function() {
				$grid.datagrid({
					queryParams : {
						unitname : $('#unitname').val(),
						businessno : $('#businessno').val(),
						legalrepresentative : $('#legalrepresentative').val()
					}
				});
			});
		});
		function getColumns() {
			return [ [
					{title : '企业名称',field : 'unitname'},
					{title : '联系电话',field : 'phone'},
					{title : '经营许可证号',field : 'businessno'},
 					{title : '企业代码',field : 'enterprisecode'},
					{title : '法人代表',field : 'legalrepresentative'},
					{field : 'opt',title : '操作',align : 'center',	formatter : function(value, row) {
						var s = '<div class ="updateBtn">';
						s += '<a href="javascript:void(0);" title="删除"  onclick="delRow(\''+row.id+'\')" class="danger delMsg"><i class="fa fa-trash"></i></a>';
						s += ' <a href="javascript:void(0);" title="编辑" onclick="editRow(\''+row.id+'\')" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
						return s;
						}
					} ] ];
		}
		
		function delRow(id) {
			if (confirm("确定要删除吗？")) {
			$.ajax({
				cache : false,
				type : "POST",
				url : "../enterprise/delete",
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
		function editRow(id) {
			//var row = $('#enterpriseList_dg').datagrid('getSelected');
			if (id) {
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '490px', '500px'], //宽高
					title : [ '编辑信息', false ],
					content : 'goEdit?id=' + id
				});
				
			}
		}
		//导出excel
		function toExcel(){
			 var unitname = $('#unitname').val();
			 var businessno = $('#businessno').val();
			 var legalrepresentative = $('#legalrepresentative').val();
			    window.location.href='goExcel?unitname='
						+encodeURI(encodeURI(unitname))
						+'&businessno='+encodeURI(encodeURI(businessno))
						+'&legalrepresentative='+encodeURI(encodeURI(legalrepresentative));  
			
		}
	</script>
</body>
</html>
