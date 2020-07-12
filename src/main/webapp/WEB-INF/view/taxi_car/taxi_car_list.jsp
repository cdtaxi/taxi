<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							<input type="text" class="easyui-textbox" id="FileNum" data-options="label:'档案号'" />
						</div>
						<div class="colRow">
							<input type="hidden" name="company" id="company" value="${sessionUser.departName }" /> <input type="text"
								class="easyui-textbox" id="PlateNum" data-options="label:'车牌号'" />
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="OpretaCertNum" data-options="label:'营运证号'" />
						</div>
						<div class="colRow">
							<input type="text" class="easyui-textbox" id="OwnerName" data-options="label:'车主姓名'" />
						</div>
						<div class="colRow">
							<select class="easyui-combobox" id="Area" data-options="label:'所属地区'" value="${pd.area }">
								<option value=''></option>
								<option value=''>全部</option>
								<option value='河北省承德市双桥区'>河北省承德市双桥区</option>
								<option value='河北省承德市双滦区'>河北省承德市双滦区</option>
							</select>
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" id="queryData">
								<i class="fa fa-search"></i> 查询
							</button>
						</div>
						<div class="colRow">
								<button class="easyui-linkbutton btnDefault" onclick="toExcel()">
									<i class="fa fa-download"></i> 导出
								</button>
							</div>
						<c:if test="${flag == '0' }">
							<div class="colRow">
								<button class="easyui-linkbutton btnDefault" onclick="fromExcel()">
									<i class="fa fa-upload"></i> 导入
								</button>
							</div>
						</c:if>
					</div>
				</div>
 
				<c:if test="${flag == '0' }">
					<div class="btnbar-tools">
						<a href="javascript:;" class="add" id="newData"> <i class="fa fa-plus-square success"></i> 添加
						</a> <a href="javascript:;" class="add" onclick="delRows()"> <i class="fa fa-close"></i> 批量删除
						</a>
					</div>
				</c:if>
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
						PlateNum : $('#PlateNum').val(),
						OpretaCertNum : $('#OpretaCertNum').val(),
						OwnerName : $('#OwnerName').val(),
						Area : $('#Area').val(),
						FileNum : $('#FileNum').val()
					}
				});
			});
		});
		function initGrid() {
			//datagrid初始化 
			$grid.datagrid({
				url : '../taxicar/list',
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
				field : 'ownerName',
				title : '车主'
			}, {
				title : '车主性别',
				field : 'ownerSex',
				formatter : function(value) {
					if (value == "1") {
						return "女";
					} else {
						return "男";
					}
				}
			}, {
				field : 'opretaCertNum',
				title : '营运证号'
			}, {
				field : 'licenseKey',
				title : '许可证号'
			}, {
				field : 'corpName',
				title : '公司名称'
			}, {
				field : 'manageNature',
				title : '经营性质'
			}, {
				field : 'opt',
				title : '操作',
				align : 'center',
				formatter : function(value, row) {
					if (($('#company').val() == "超级管理员") || ($('#company').val() == "")) {
						var s = '<div class ="updateBtn">';
						s += ' <a href="javascript:void(0);" title="查看" onclick="showRow(\'' + row.id + '\')" class="info"><i class="fa fa-eye"></i></a>';
						s += ' <a href="javascript:void(0);" title="编辑" onclick="editRow(\'' + row.id + '\')" class="info"><i class="fa fa-pencil-square-o"></i></a></div>';
						return s;
					} else {
						var s = '<div class ="updateBtn">';
						// 						s += '<a href="javascript:void(0);" title="删除"  onclick="delRow(\''+row.id+'\')" class="danger delMsg"><i class="fa fa-trash"></i></a>';
						s += ' <a href="javascript:void(0);" title="变更" onclick="rankRow(\'' + row.id + '\')" class="info"><i class="fa fa-pencil-square-o"></i></a>';
						s += ' <a href="javascript:void(0);" title="查看" onclick="showRow(\'' + row.id + '\')" class="info"><i class="fa fa-eye"></i></a></div>';
						return s;
					}
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
					content : 'goEdit?urlFun=save&id=' + id
				});
			}
		}
		function rankRow(id) {
			if (id) {
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '1000px', '460px' ], //宽高
					title : [ '编辑信息', false ],
					content : 'goEdit?urlFun=rank&id=' + id
				});
			}
		}
		

		//删除
		function delRow(id) {
			if (confirm("确定要删除吗？")) {
				$.ajax({
					cache : false,
					type : "POST",
					url : "../taxicar/delete",
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
		//查看
		function showRow(id) {
			if (id) {
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '1000px', '460px' ], //宽高
					title : [ '查看信息', false ],
					content : 'goShow?id=' + id
				});

			}
		}
		//删除
		function delRows() {
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
				ids : ids
			};
			if (confirm("确定要删除吗？")) {
				$.ajax({
					cache : false,
					type : "POST",
					url : "../taxicar/deletes",
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
		}
		//导出excel
		function toExcel() {
			var PlateNum = $('#PlateNum').val();
			var OpretaCertNum = $('#OpretaCertNum').val();
			var OwnerName = $('#OwnerName').val();
			var Area = $('#Area').val();
			window.location.href = 'goExcel?PlateNum=' + encodeURI(encodeURI(PlateNum)) + '&OpretaCertNum=' + encodeURI(encodeURI(OpretaCertNum)) + '&OwnerName='
					+ encodeURI(encodeURI(OwnerName)) + '&Area=' + encodeURI(encodeURI(Area));

		}
		function fromExcel() {

			layer.open({
				type : 2,
				skin : 'layui-layer-rim', //加上边框
				hade : [ 0.5, '#000', false ],
				area : [ '450px', '200px' ], //宽高
				title : [ '导入excel', false ],
				content : 'goUploadExcel'
			});

		}
	</script>

</body>

</html>
