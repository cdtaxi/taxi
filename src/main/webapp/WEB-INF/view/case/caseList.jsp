<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>案件查询</title>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var $grid;
	$(function() {
		$grid = $("#list_data");
		initGrid();
	});
	function initGrid() {
		//datagrid初始化 
		$grid.datagrid({
			url : '../case/listData',
			striped : true,
			nowrap : false,
			rownumbers : true,
			loadMsg : '正在加载中，请稍等... ',
			emptyMsg : '<span>无记录</span>',
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 100 ],
			columns : [ [  {
				field : 'anhao',
				title : '案号',
				width:80
			}, {
				field : 'grxm',
				title : '个人姓名',
				width:80
			},{
				field : 'ay',
				title : '案由',
				width:260
			},  {
				field : 'grlxdh',
				title : '个人联系电话',
				width:100
			}, {
				field : 'grsfzh',
				title : '个人身份证件号',
				width:150
			}, {
				field : 'gryajgx',
				title : '个人与案件关系'
			}, {
				field : 'cphm',
				title : '车牌号码'
			}, {
				field : 'ajly',
				title : '案件来源'
			}, {
				field : 'sasj',
				title : '受案时间'
			}, {
				field : 'opt',
				title : '操作',
				align : 'center',
				formatter : function(value, rec) {
					var s = '<div class ="updateBtn">';
					s += '<a href="javascript:void(0);" title="查看" onclick="openWin()" class="danger delMsg"><i class="fa fa-eye"></i></a>';
					s += '<a href="javascript:void(0);" title="删除" onclick="delRow()" class="danger delMsg"><i class="fa fa-trash"></i></a>';
					s += '<a href="javascript:void(0);" title="编辑" onclick="editRow()" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
					return s;
				}
			} ] ]
		});
	}
	function openWin(){
		window.open("//www.100sucai.com");    
	}
	//查询
	function query() {
		$grid.datagrid({
			queryParams : {
				anhao : $('#anhao').val(),
				grxm : $('#grxm').val(),
				ay : $('#ay').val()
			}
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
							<input type="text" class="easyui-textbox" id="anhao" data-options="label:'案号'" />
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="grxm" data-options="label:'个人姓名'" />
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="ay" data-options="label:'案由'" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" onclick="query()">
								<i class="fa fa-search"></i>
								查询
							</button>
						</div>
					</div>
				</div>
				<!-- <div class="btnbar-tools">
					<a href="javascript:;" class="add" id="newData">
						<i class="fa fa-plus-square success"></i>
						添加
					</a>
				</div> -->
				<table id="list_data"></table>
			</div>
		</div>
	</div>
</body>
</html>