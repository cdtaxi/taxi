<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆业务变更审核列表</title>
<%@ include file="/common/taglibs.jsp"%>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="comp-search-box">
					<div class="screen-top">
							<div class="colRow">
									<input type="text" name="fileNum"  id="fileNum"   class="easyui-textbox" data-options="label:'档案号'"/> 
	 						</div>
 							<div class="colRow">
									<input type="text" name="company"  id="company" value="${sessionUser.departName }"   class="easyui-textbox" data-options="label:'公司名称'"/> 
	 						</div>
 					
						<div class="colRow">
 							<input type="text" class="easyui-textbox" id="PlateNum" data-options="label:'车牌号'" />
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="OpretaCertNum" data-options="label:'营运证号'" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" id="queryData">
								<i class="fa fa-search"></i> 查询
							</button>
						</div>
					</div>
				</div>
				<div class="btnbar-tools">
					<a href="javascript:;" class="add" onclick="BatchRows()"> <i class="fa fa-check"></i> 批量审核
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
						fileNum : $('#fileNum').val(),
						PlateNum : $('#PlateNum').val(),
						company : $('#company').val(),
						OpretaCertNum : $('#OpretaCertNum').val()
					}
				});
			});
		});
		function initGrid() {
			//datagrid初始化 
			$grid.datagrid({
				url : '../taxicarChange/list',
				striped : true,
				nowrap : false,
				rownumbers : true,
				loadMsg : '正在加载中，请稍等... ',
				emptyMsg : '<span>无记录</span>',
				pagination : true,
				//singleSelect : true,
				fitColumns : true,
				idField : 'pkey',
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 100 ],
				columns : getColumns(),
				onLoadSuccess : function() {
					$grid.datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				}
			});
		}
		function getColumns() {
			return [ [ {
				field : 'id',
				checkbox : true
			}, {
				field : 'fileNum',
				title : '档案号'
			}, {
				field : 'plateNum',
				title : '车牌号'
			}, {
				field : 'origPlateNum',
				title : '原车号'
			}, {
				field : 'ownerName',
				title : '车主'
			}, {
				field : 'origOwnerName',
				title : '原车主'
			}, {
				field : 'corpName',
				title : '公司名称'
			}, {
				field : 'transferDate',
				title : '变更日期'
			}, {
				field : 'state',
				title : '变更状态',
				align : 'center',
				formatter : function(value, row) {
					if (value == 0) {
						return '<span style="color:Chocolate">待审核</span>';
					} else if (value == 1) {
						return '<span style="color:DarkGreen">通过</span>';
					} else if (value == 2) {
						return '<span style="color:DimGray">不通过</span>';
					}
				}
			}, {
				field : 'opt',
				title : '操作',
				align : 'center',
				formatter : function(value, row) {
					var s = '<div class ="updateBtn">';
					// 					s += ' <a href="javascript:void(0);" title="查看" onclick="showRow(\'' + row.id + '\')" class="info"><i class="fa fa-eye"></i></a>';
					s += ' <a href="javascript:void(0);" title="审核" onclick="editRow(\'' + row.id + '\')" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
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
					area : [ '1000px', '460px' ], //宽高
					title : [ '编辑信息', false ],
					content : 'goChangeInfo?id=' + id
				});

			}
		}

		//查看
		function showRow(id) {
			if (id) {
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '1000px', '460px' ], //宽高
					title : [ '查看信息', false ],
					content : 'goChangeInfo?type=true&id=' + id
				});
			}
		}

		function BatchRows() {
			// 得到选中的行
			var selRow = $grid.datagrid("getSelections");// 返回选中多行
			if (selRow.length == 0) {
				alert("请至少选择一行数据!");
				return false;
			}
			var ids = [];
			for (var i = 0; i < selRow.length; i++) {
				// 获取自定义table 的中的checkbox值
				var id = selRow[i].id; // id这个是你要在列表中取的单个id
				ids.push(id); // 然后把单个id循环放到ids的数组中
			}
			var param = {
					state:"1", ids : ids
			};
			$.ajax({
				cache : false,
				type : "POST",
				url : "../taxicarChange/updateListByChange",
				data : param,
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
	</script>

</body>

</html>
