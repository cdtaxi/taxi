<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var $grid;
	
	//检索
	function search() {
		$("#employeeForm").submit();
	}
	$(function() {
		$grid = $("#list_data");
		if($("#idcard").val()!=null){
		initGrid();
		}
		$("#click_event").click(function() {
			layer.open({
				type : 2,
				skin : 'layui-layer-rim', //加上边框
				hade : [ 0.5, '#000', false ],
				area : [ '450px', '550px' ], //宽高
				title : [ '编辑信息', false ],
				content : 'goAdd?empId=' + $('#idcard').val()
			});
		});
	});
	function initGrid() {
		//datagrid初始化 
		$grid.datagrid({
			url : 'listByIdCard',
			queryParams: {  		
				idcard: $("#idcard").val()  		
				  },  
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
			title : '备注',
			field : 'remark'
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
</script>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<form action="../examine/examineAdd" method="post" name="employeeForm" id="employeeForm">
					<div class="comp-search-box">
						<div class="screen-top">
							<div class="colRow">
								<input class="easyui-textbox" name="idcard" style="width: 300px">
								<input type="button" value="查询" class="easyui-linkbutton btnPrimary" style="width: 100px" onclick="search();" />
							</div>
						</div>
						<div class="screen-term" style="display: block;">
							<c:choose>
								<c:when test="${not empty employee}">
									<div class="form2-column" style="margin: 0px 0px 0px 10px;">
										<div class="form-column2">
											<div class="form-column-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'姓名:'" value="${employee.name }" editable="false">
											</div>
											<div class="form-column-left fm-left">
												<input class="easyui-textbox" style="width: 100%" id="idcard" data-options="label:'身份证号:'" value="${employee.idcard }" editable="false">
											</div>
										</div>
										<div class="form-column2">
											<div class="form-column-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'从业资格证号:'" value="${employee.cyzgCard }" editable="false">
											</div>
											<div class="form-column-left fm-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'注册车牌号:'" value="${employee.carid }" editable="false">
											</div>
										</div>
										<div class="form-column2">
											<div class="form-column-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'所属公司:'" value="${employee.company }" editable="false">
											</div>
											<div class="form-column-left fm-left">
												<input class="easyui-textbox" style="width: 100%" data-options="label:'剩余分数:'" value="${syFraction}" editable="false">
											</div>
										</div>
										<div class="form-column2">
											<div class="form-column-left">
												<input id="click_event" type="button" value="扣分登记" class="easyui-linkbutton btnPrimary" style="width: 100px" />
											</div>
										</div>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
					<table id="list_data"></table>
					<%-- <c:choose>
						<c:when test="${not empty employee}">
							<table class="easyui-datagrid" title="考核记录">
								<thead>
									<tr>
										<th field=ordinal width="45%" align="left">扣分条款</th>
										<th field="scoring" width="10%" align="center">扣分分数</th>
										<th field="examineTime" width="10%" align="center">扣分时间</th>
										<th field="remark" width="20%" align="left">备注</th>
										<th field="opt" width="15%" align="left">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 开始循环 -->
									<c:choose>
										<c:when test="${not empty staList}">
											<c:forEach items="${staList}" var="var" varStatus="vs">
												<tr>
													<td align="left">${var.ordinal }</td>
													<td>${var.scoring }</td>
													<td>${var.examineTime }</td>
													<td align="left">${var.remark }</td>
													<td><div class ="updateBtn"><a href="javascript:void(0);" title="删除"  onclick="delRow(${var.id })" class="danger delMsg"><i class="fa fa-trash"></i></a>
													<a href="javascript:void(0);" title="编辑" onclick="editRow(${var.id })" class="info"><i class="fa fa-pencil-square-o"></i></a></div></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="100" class="center">没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</c:when>
					</c:choose> --%>
				</form>
			</div>
		</div>
	</div>

</body>
</html>