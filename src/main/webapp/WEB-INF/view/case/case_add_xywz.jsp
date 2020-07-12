<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var $tab_li = $('#tab-wrapper ul li');
		$tab_li.click(function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			var index = $tab_li.index(this);
			$('div.tab_box > div.tabView').eq(index).show().siblings().hide();
		});
	});

	function saveSkip(index) {
		if (true) {
			skip(index);
		}

	}
	function skip(index) {
		$('#tab-wrapper ul li').eq(index).addClass('selected').siblings().removeClass('selected');
		$('div.tab_box > div.tabView').eq(index).show().siblings().hide();
	}
</script>
</head>
<body scroll="no" class="body-pd10" style="width: 1224px;">
	<!-- 新版tabs -->
	<div id="tab-wrapper">
		<ul class="tab_menu">
			<li class="selected" style="width: 110px">主客体依据案由</li>
			<li>案件文本</li>
			<li style="width: 130px">证据处理讨论与送达</li>
			<li style="width: 130px">案卷封面与单位信息</li>
			<li>子表</li>
		</ul>
		<div class="tab_box">
			<div class="tabView">
			  <div class="column"><span class="current">主客体依据案由</span></div>
				<div class="weilan_wrap">
					<div class="car_detail">
						<dl style="width: 100%">
							<dt>案号:</dt>
							<dd>
								<input value="" type="text" style="width: 400px">
								<span style="margin-left: 20px;">记录人：</span>
								<span style="margin-left: 10px;">
									<select class="form-control" id="numbers">
										<option value="1">数字1</option>
										<option value="2" selected>数字2</option>
									</select>
								</span>
							</dd>
						</dl>
						<dl>
							<dt>姓名:</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>性别:</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>年龄:</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>联系电话:</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>

						<dl>
							<dt>邮编:</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>

						<dl>
							<dt>身份证号码:</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>职业 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>与案件关系 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>住址 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>


						<dl style="width: 50%">
							<dt>执法人员1 :</dt>
							<dd>
								<select class="form-control" id="numbers">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
								<input type="text" id="permitFilesId" name="permitFilesId" value="">
							</dd>
						</dl>
						<dl style="width: 50%">
							<dt>执法人员2 :</dt>
							<dd>
								<select class="form-control" id="numbers">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
								<input type="text" id="permitFilesId" name="permitFilesId" value="">
							</dd>
						</dl>
						<dl style="width: 50%">
							<dt>督查人员1 :</dt>
							<dd>
								<select class="form-control" id="numbers">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
								<input value="" type="text">
							</dd>
						</dl>
						<dl style="width: 50%">
							<dt>督查人员2:</dt>
							<dd>
								<select class="form-control" id="numbers">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
								<input value="" type="text">
							</dd>
						</dl>
						<dl style="width: 100%">
							<dt>立案依据:</dt>
							<dd>
								<select class="form-control" id="numbers" style="width: 400px">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
							</dd>
						</dl>
						<dl>
							<dt>立案依据条 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>立案依据款 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>立案依据项 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>车牌号 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>车辆颜色 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>车辆型号 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>乘客人数 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>车费 :</dt>
							<dd>
								<input value="" type="text" style="width: 70px">
								<input value="" type="text" style="width: 100px">
							</dd>
						</dl>
						<dl>
							<dt>发生时间 :</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>
						<dl>
							<dt>地点 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>乘客起点地址 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>乘客目的地址 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl style="width: 100%">
							<dt>案由 :</dt>
							<dd>
								<input value="" type="text">
								<select class="form-control" id="numbers">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
							</dd>
						</dl>
						<dl style="width: 100%">
							<dt>行政处罚 :</dt>
							<dd>
								<input value="" type="text">
								<select class="form-control" id="numbers">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
							</dd>
						</dl>
					</div>
					<div style="text-align: center;">
						<input type="button" class="btnInfo" value="下一步" onclick="skip(1)">
					</div>
				</div>
			  <div class="column"><span class="current">主客体依据案由</span></div>
			</div>
			<div class="tabView hide">
				<div class="weilan_wrap">
					<div class="car_detail">
						<dl>
							<dt>案件来源:</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>受理时间:</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>
						<dl>
							<dt>缴费银行:</dt>
							<dd>
								<input value="${jbxx.JFYH }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>复议单位:</dt>
							<dd>
								<input value="${jbxx.FYDW }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>诉讼单位:</dt>
							<dd>
								<input value="${jbxx.SSDW }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>执法主体:</dt>
							<dd>
								<input value="${jbxx.ZFZT }" type="text">
							</dd>
						</dl>
						<dl style="width: 100%">
							<dt>立案依据:</dt>
							<dd>
								<select class="form-control" id="numbers" style="width: 400px">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
							</dd>
						</dl>
						<dl>
							<dt>立案依据条 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>立案依据款 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>立案依据项 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>

						<dl>
							<dt>询问时间 :</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>

						<dl>
							<dt>询问结束时间 :</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>
						<dl>
							<dt>询问地点 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl style="width: 100%">
							<dt>案件基本情况文本 :</dt>
							<dd>
								<input value="" type="text" style="height: 90px; width: 100%">
							</dd>
						</dl>
						<dl style="width: 50%">
							<dt>违法行为文本 :</dt>
							<dd>
								<input value="" type="text" style="height: 90px; width: 100%">
							</dd>
						</dl>
						<dl style="width: 50%">
							<dt>讨论记录文本 :</dt>
							<dd>
								<input value="" type="text" style="height: 90px; width: 100%">
							</dd>
						</dl>
						<dl style="width: 50%">
							<dt>结论性已经文本 :</dt>
							<dd>
								<input value="" type="text" style="height: 90px; width: 100%">
							</dd>
						</dl>
						<dl style="width: 50%">
							<dt style="width: 160px">调查结论和处理意见文本 :</dt>
							<dd>
								<input value="" type="text" style="height: 90px; width: 100%">
							</dd>
						</dl>
					</div>
					<div style="text-align: center;">
						<input type="button" class="btnInfo" value="保存&下一步" onclick="saveSkip(2)">
					</div>
				</div>

			</div>
			<div class="tabView hide">
				<div class="weilan_wrap">
					<div class="car_detail">
						<dl style="width: 100%">
							<dt>立案依据:</dt>
							<dd>
								<select class="form-control" id="numbers" style="width: 400px">
									<option value="1">数字1</option>
									<option value="2" selected>数字2</option>
								</select>
							</dd>
						</dl>
						<dl>
							<dt>立案依据条 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>立案依据款 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>立案依据项 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>通知联系地址 :</dt>
							<dd>
								<input value="${jbxx.TZLXDZ }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>通知联系邮编 :</dt>
							<dd>
								<input type="text" value="${jbxx.TZLXYB }">
							</dd>
						</dl>
						<dl>
							<dt>通知联系人 :</dt>
							<dd>
								<input value="${jbxx.TZLXR }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>通知联系电话 :</dt>
							<dd>
								<input value="${jbxx.TZLXDH }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>处罚时间起 :</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>
						<dl>
							<dt>处罚时间止 :</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>
						<dl>
							<dt>讨论时间起 :</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>
						<dl>
							<dt>讨论时间止 :</dt>
							<dd>
								<input id="" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" style="height: 22px" />
							</dd>
						</dl>
						<dl>
							<dt>讨论地点 :</dt>
							<dd>
								<input value="${jbxx.TLDD }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>讨论主持人 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>讨论汇报人 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>讨论记录人 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl style="width: 100%">
							<dt>改正要求 :</dt>
							<dd>
								<input value="${jbxx.GZYQ }" type="text" style="width: 60%">
							</dd>
						</dl>
						<dl>
							<dt>证据保存时间 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>证据名称 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
						<dl>
							<dt>证据处理决定 :</dt>
							<dd>
								<input value="${jbxx.ZJCLJD }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>送达单位 :</dt>
							<dd>
								<input value="${jbxx.SDDW }" type="text">
							</dd>
						</dl>
						<dl>
							<dt>送达代收人 :</dt>
							<dd>
								<input value="" type="text">
							</dd>
						</dl>
					</div>
					<div style="text-align: center;">
						<input type="button" class="btnInfo" value="保存&下一步" onclick="saveSkip(3)">
					</div>
				</div>
			</div>
			<div class="tabView hide">
				<div class="car_detail">
					<dl style="width: 25%">
						<dt>案卷处理时间:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 25%">
						<dt>案卷保管期限:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 25%">
						<dt>案卷件数:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 25%">
						<dt>案卷页数:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 25%">
						<dt>案卷全宗号:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 25%">
						<dt>案卷目录号:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 25%">
						<dt>案卷号:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 25%">
						<dt>卷名:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 100%">
						<dt>单位名称:</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>单位法定代表人:</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>单位职务:</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>单位联系电话:</dt>
						<dd></dd>
					</dl>
					<dl style="width: 100%">
						<dt>单位地址:</dt>
						<dd></dd>
					</dl>
				</div>
			</div>
			<div class="tabView hide">
				<div style="text-align: left;">
					<input type="button" class="btnPrimary" value="讨论人员列表">
					<input type="button" class="btnPrimary" value="问答列表">
					<input type="button" class="btnSuccess" value="送达文书列表">
					<input type="button" class="btnSuccess" value="送达下半部">
					<input type="button" class="btnDanger" value="证据材料列表">
					<input type="button" class="btnDanger" value="证据列表">
				</div>
			</div>
		</div>
	</div>
</body>
</html>