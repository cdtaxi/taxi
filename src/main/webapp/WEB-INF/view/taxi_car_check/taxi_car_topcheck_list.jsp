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
				
				<table id="list_data"></table>
			</div>
		</div>
	</div>
	<!-- 新增和编辑数据 -->
	<div class="dig-wrapper" id="newData-wrapper">
	
	<div id="showPart" data-options="region:'center',border:true">
		<div title="表单示例" >
			<form id="vui_sample" class="easyui-form" >
				
				<table style="table-layout: fixed;" id="table_report" border="1"  class="table table-striped table-bordered table-hover">
		<tr>
			  <td colspan="3"><span
				style="margin-left: 20px;">档案号：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="fileNum" type="text" style="width: 130px;" /></span></td>
			<td colspan="3"><span
				style="margin-left: 20px;">车主姓名：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="ownerName" type="text" style="width: 120px;" /></span></td>
			<td colspan="2"><span
				style="margin-left: 20px;">颜色：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="color" type="text" style="width: 60px;" /></span></td>
			<td colspan="2" style="width: 150px;" rowspan="5">
				<div style="width: 125px; height: 155px; margin: 2px;">
					<img id="uploadPreviewOwnerNamePic" style="width: 125px; height: 155px;" />
					<div style="text-align: center;">
	         			<a href="javascript:;" class="a-upload" style="margin-top: 5px;">
					    	<input id="ownernamepic1" name="ownernamepic1"  type="file"  onchange="loadImageFileOwnerNamePic();"/>上传车主照片
						</a>
	         		</div>
				</div>
			</td>
          </tr>
          <tr>
			 <td colspan="3">
              <span style="margin-left:20px;">营运证号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="opretaCertNum" type="text" style="width: 115px;" /></span>
            </td>
            <td colspan="3">
              <span style="margin-left:20px;">原车主：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="origOwnerName" type="text" style="width: 135px;" /></span>
            </td>
            <td colspan="2">
              <span style="margin-left:20px;">车型：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="sign" type="text" style="width: 60px;" /></span>
            </td>
          </tr>
          <tr>
            <td colspan="4">
              <span style="margin-left:20px;">公司名称：</span>
                    <span style="margin-left:2px;"><input id="corpName" name="corpName" type="text" style="width: 210px;" /></span>
            </td>
            <td colspan="4">
              <span style="margin-left:20px;">联系电话：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="phoneNum" type="text" style="width: 120px;" /></span>
            </td>
          </tr>
          <tr>
            <td colspan="8">
              <span style="margin-left:20px;">变更日期：</span>
                    <span style="margin-left:2px;"><input class="Wdate" name="transferDate"  style="width: 120px;" 
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
              <span style="margin-left:20px;">变更记录：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="ownerChange" type="text" style="width: 120px;" /></span>
              <span style="margin-left:20px;">查封记录：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="sealRecord" type="text" style="width: 125px;" /></span>
            </td>
          </tr>
          <tr>
            <td colspan="8">
              <span style="margin-left:20px;">车牌号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="plateNum" type="text" style="width: 100px;" /></span>
              <span style="margin-left:20px;">原车号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="origPlateNum" type="text" style="width: 100px;" /></span>
              <span style="margin-left:20px;">行驶证初次登记：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="drvLicenseDate" type="text" style="width: 158px;" /></span>
            </td>
          </tr>
          <tr>
			<td colspan="7"><span
				style="margin-left: 20px;">身份证号：</span> <span
				style="margin-left: 2px;"><input class="easyui-textbox" name="iDNumber" type="text" style="width: 450px;" /></span></td>
			<td colspan="3" style="width: 202px;" rowspan="3">
				<div style="width: 185px; height: 110px; margin: 2px;margin-left: 15px;">
						<img id="uploadPreviewVehiclePic" style="width: 185px; height: 110px;" />
					
					<div style="text-align: center;">
	         			<a href="javascript:;" class="a-upload" style="margin-top: 3px;width: 150px;" >
					    	<input id="vehiclepic1" name="vehiclepic1"  type="file"  onchange="loadImageFileVehiclepic();"/>上传车身照片
						</a>
	         		</div>
				</div>
			</td>
          </tr>
          <tr>
            <td colspan="7">
              <span style="margin-left:20px;">家庭住址：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="address" type="text" style="width: 450px;" /></span>
            </td>
          </tr>
          <tr>
          	<td colspan="7">
              <span style="margin-left:20px;">吨（座）位：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="tonsSeat" type="text" style="width: 60px;" /></span>
              <span style="margin-left:20px;">长：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="carLen" style="width:60px;" type="text"></span>
              <span style="margin-left:20px;">宽：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="carWidth" style="width:60px;" type="text"></span>
              <span style="margin-left:20px;">高：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="carHigh" style="width:60px;" type="text"></span>
            </td>
          </tr>
          <tr>
          	<td colspan="5">
              <span style="margin-left:20px;">车架号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="frameNumber" style="width:270px;" type="text"></span>
            </td>
            <td colspan="5">
              <span style="margin-left:20px;">发动机号：</span>
                    <span style="margin-left:2px;"><input class="easyui-textbox" name="engineNumber" style="width:270px;" type="text"></span>
            </td>
          </tr>
          <tr>
          	<td colspan="10">
              <span style="margin-left:20px;">发证日期：</span>
                    <span style="margin-left:2px;"><input  class="Wdate" name="checkDate" style="width: 270px;" 
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
            </td>
          </tr>
          <tr>
          	<td colspan="10">
              <span style="margin-left:20px;">车辆审验记录：</span>
                    <span style="margin-left:2px;">
                    	<input class="easyui-textbox" name="vehicleInspRec" type="text" style="width: 665px;height:60px;"  multiline="true"/>
                    </span>
            </td>
          </tr>
          	</table>
          		</form>
          		<form id="vui_sample1" class="easyui-form" >
			<table  id="table_report1" style="table-layout: fixed;"  border="1" class="table table-striped table-bordered table-hover">
						<input type="hidden" name="id">
						<input type="hidden" name="operatingnum" id="operatingnum1"/>
						<tr>
				        	<td>
				            	<span style="margin-left:20px;">年审日期：</span>
		            		</td>
				        	<td>
				            	<span style="margin-left:10px;">
<input  class="Wdate" name="annualdate" style="width: 150px;" 
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
		            		</td>
				        	<td>
				            	<span style="margin-left:20px;">下一年审日期：</span>
		            		</td>
				        	<td>
				            	<span style="margin-left:10px;"><input  class="Wdate" name="nextannualdate" style="width: 150px;" 
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/></span>
		            		</td>
		        		</tr>
		        		<tr>
				        	<td>
				            	<span style="margin-left:20px;">车辆级别：</span>
		            		</td>
				        	<td colspan="3">
				            	<span style="margin-left:10px;">
				            		<select style="width:150px;" class="easyui-combobox"  name="vehicle" title="车辆级别">
										<option value="1">一级车</option>
										<option value="2">二级车</option>
									</select>
								</span>
		            		</td>
		        		</tr>
						<tr>
							<td style="text-align: center;"  colspan="4">
								<a href="javascript:void(0);"
					class="easyui-linkbutton" style="margin: 5 0 0 5"
					data-options="iconCls:'icon-ok',plain:true" onclick="pass('2');">通过</a>
								<a href="javascript:void(0);"
					class="easyui-linkbutton" style="margin: 5 0 0 5"
					data-options="iconCls:'icon-no',plain:true" onclick="pass('3');">拒绝</a>
							</td>
						</tr>
					</table>
         </form>
				
		
		</div>
		</div>
	</div>
	<script type="text/javascript">
		var $grid;
		var pkey;
		$(function() {
			$grid = $("#list_data");
			initGrid();
			
			
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
				url : '../taxicarCheck/topcheckList',
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
			return [ [ {
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
					if(value==1){
						return '<span style="color:Chocolate">待审核</span>';
					}
					else if(value==2){
						return '<span style="color:red">通过审核</span>';
					}
					else if(value==3){
						return '<span style="color:DimGray">审核失败</span>';
					}
					
				}
			} , {
				field : 'opt',
				title : '操作',
				align : 'center',
				formatter : function(value, row) {
					var s = "";
					s += "<a href=\"javascript:void(0)\" onclick=\"topCheck('" + row.id + "');\"><i class=\"fa fa-pencil \"></i>审核</a>";
					return s;
				}
			} ] ];
				}
	
		//修改
		function topCheck(id) {
			pkey=id;
			if (id) {
				layer.open({
					type : 2,
					skin : 'layui-layer-rim', //加上边框
					hade : [ 0.5, '#000', false ],
					area : [ '1000px', '460px'], //宽高
					title : [ '编辑信息', false ],
					content : 'goCheck?id=' + id
				});
			
			}
		}
		//导出excel
		function toExcel(){
			 var operatingnum=$('#operatingnum').val();
			 var status="true";
			    window.location.href='goExcel?operatingnum='
						+encodeURI(encodeURI(operatingnum))
						+'&status='+encodeURI(encodeURI(status));  
			
		}
	</script>

</body>
</html>
