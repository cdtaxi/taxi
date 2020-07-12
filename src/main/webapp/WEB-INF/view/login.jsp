<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>承德市出租车管理系统</title>
<link rel="Bookmark" href="assets/images/logoIco.ico" />
<link rel="Shortcut Icon" href="assets/images/logoIco.ico" />
<link type="text/css" rel="stylesheet" href="assets/default/login/css/login.css">
<script src="assets/js/jquery2.1.1.js" type="text/javascript"></script>
<script type="text/javascript">
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
	//处理记住用户名
	function changeCheckRembername() {
		var rembername = document.getElementById("rembername").value;
		if (rembername == 'true') {//选中，置为不选中
			document.getElementById("rembername").value = 'false';
			$('#checked').attr("class", "normal");
		} else {
			document.getElementById("rembername").value = 'true';
			$('#checked').attr("class", "checked");
		}
	}

	//回车事件
	$(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				severCheck();//处理事件
			}
		}
	});
	//客户端校验
	function check() {
		if ($("#loginname").val() == "") {
			$("#EE").text("用户名不得为空");
			$("#loginname").focus();
			return false;
		} else {
			$("#loginname").val(jQuery.trim($('#loginname').val()));
		}
		if ($("#password").val() == "") {
			$("#EE").text("密码不得为空");
			$("#password").focus();
			return false;
		}
		return true;
	}
	//服务器校验
	function severCheck() {
		if(check()){
			var loginname = $("#loginname").val();
			var password = $("#password").val();
			var code = loginname + "," + password + "," + $("#verification").val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : 'login_login',
				data : {
					KEYDATA : code,
					tm : new Date().getTime()
				},
				async : true,
				cache : false,
				success : function(data) {
					if ("success" == data.result) {
						window.location.href = "index";
					} else if ("usererror" == data.result) {
						$("#EE").text("户名或密码有误");
						$("#loginname").focus();
					} else if ("codeerror" == data.result) {
						$("#EE").text("验证码输入有误");
						$("#verification").focus();
					} else {
						$("#EE").text("缺少参数");
						$("#loginname").focus();
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<!--登录start-->
	<div class="ordinaryLogin">
		<div class="login_ad">
			<div class="bg_ordLogin">
				<img src="assets/default/login/images/icon-banner.png" />
			</div>
			<div class="loginCont_dk post05" style="right: -17px;">
				<div class="loginCont">
					<div class="login_th">
						<h4 class="lgCurr bd_r">用户登录</h4>
					</div>
					<div class="login_text">
						<!-- error start -->
						<div class="login-error">
							<label id="EE" class="login_error_tips1"></label>
						</div>
						<!-- end -->
						<div class="user_parent">
							<div class="login_input user_bg unm">
								<input name="username" id="loginname" tabindex="1" value="" type="text" class="usernameSty">
							</div>
						</div>

						<div class="user_paswd">
							<div class="login_input user_bg pwd" name="pwdParent" id="pwdParent">
								<div id="newPwd" class="keyboards-box">
									<input id="password" value="" type="password" name="password" tabindex="2" class="styTextinput w162px">
								</div>
							</div>
						</div>

						<div class="user_code" style="display: none;">
							<div class="login_input user_bg ">
								<input name="verification" id="verification" tabindex="3" value="验证码" type="text" class="usernameSty">
								<div id="v_container" class="codeimg">
									<img style="height: 25px;" id="codeImg" alt="点击更换" title="点击更换" src="" />
								</div>
							</div>
						</div>
						<div style="height: 10px"></div>
<!-- 						<div class="remeber_name"> -->
<!-- 							<b id="checked" class="normal" onclick="changeCheckRembername();"></b> -->
<!-- 							<label id="login_save" style="float: left;">记住用户名</label> -->
<!-- 							<div class="login-wjpw"> -->
								<!-- 									<a class="blue" href="javascript:;">忘记用户名？</a>&nbsp;|&nbsp;&nbsp; <a href="javascript:;"><span class="blue">忘记密码？</span></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<div class="login_ck">
						<input name="submitBtn" id="submitBtn" type="submit" tabindex="5" class="login_btn" value="登录" onclick="severCheck();">
					</div>
				</div>
			</div>
			<div class="filter box_shadow"></div>
			<!--用户名密码出错弹出层start-->
			<div class="login_error_w" style="top: 152px;">
				<div class="login_error">
					<div class="login_error_th">
						<h4>用户名或密码错误</h4>
					</div>
				</div>
			</div>
			<!--用户名密码出错弹出层end-->
			<!--验证码出错弹出层start-->
			<div class="login_error_w" style="top: 232px;">
				<div class="login_error">
					<div class="login_error_th">
						<h4>验证码错误</h4>
					</div>
				</div>
			</div>
			<!--验证码出错弹出层end-->
		</div>
	</div>
	<!--登录end-->
	<!--底部start-->
	<div style="clear: both"></div>
	<div class="footBorder_t01">
		<div class="ce_about_foot">
			<p class="ce_about_copyright">
				<span class="" style="">Copyright © 2016-2019 All Rights Reserved 版权所有 承德市城市出租汽车管理处</span>
		</div>
	</div>
	<!--底部end-->
</body>
</html>