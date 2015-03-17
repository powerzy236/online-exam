<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE>用户登录</TITLE>
<LINK href="${ctx }/static/css/manage/Default.css" type=text/css
	rel=stylesheet>
<LINK href="${ctx }/static/css/manage/xtree.css" type=text/css
	rel=stylesheet>
<LINK href="${ctx }/static/css/manage/User_Login.css" type=text/css
	rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
</HEAD>
<BODY id=userlogin_body>
	<DIV></DIV>
	<DIV id=user_login>
		<DL>
			<DD id=user_top>
				<UL>
					<LI class=user_top_l></LI>
					<LI class=user_top_c></LI>
					<LI class=user_top_r></LI>
				</UL>
			<DD id=user_main>
				<UL>
					<LI class=user_main_l></LI>
					<LI class=user_main_c>
						<DIV class=user_main_box>
							<UL>
								<LI class=user_main_text>用户名：</LI>
								<LI class=user_main_input><INPUT class=TxtUserNameCssClass
									id=userName maxLength=20 name=userName></LI>
							</UL>
							<UL>
								<LI class=user_main_text>密 码：</LI>
								<LI class=user_main_input><INPUT class=TxtPasswordCssClass
									id=password type=password name=password></LI>
							</UL>
							<UL>
								<LI class=user_main_meg></LI>
							</UL>
						</DIV>
					</LI>
					<LI class=user_main_r>
					<INPUT class="IbtnEnterCssClass"	id="IbtnEnter" style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
						 onclick= "onclickSub();" type=image	 src="${ctx }/static/image/manage/user_botton.gif"></LI>
				</UL>
			<DD id=user_bottom>
				<!-- <UL>
					<LI class=user_bottom_l></LI>
					<LI class=user_bottom_c><SPAN style="MARGIN-TOP: 40px">如果您想获得更多后台模板，请点此
							<A href="http://www.mycodes.net">源码之家</A> 。
					</SPAN></LI>
					<LI class=user_bottom_r></LI>
				</UL> -->
			</DD>
		</DL>
	</DIV>
	<DIV></DIV>
<script type="text/javascript" src="${ctx }/static/js/common/jquery.js"></script>
<script type="text/javascript">
	function onclickSub(){
		var _userName = $("#userName").val();
		var _password = $("#password").val();
		var reg_user = /^[a-zA-Z0-9_]{3,18}$/;
		var reg_pwd = /^[a-zA-Z0-9]\w{5,17}$/;
		var $_user_main_meg = $(".user_main_meg");
		if(_userName == null || '' == _userName ){
			$_user_main_meg.html("用户名不能为空！");
		}else if(!reg_user.test(_userName)){
			$_user_main_meg.html("用户名格式错误！");
		}else if(_password == null || '' == _password){
			$_user_main_meg.html("密码不能为空！");
		}else if(!reg_pwd.test(_password)){
			$_user_main_meg.html("密码格式错误!");
		}else{
			$_user_main_meg.html("");
			  $.post("${ctx}/manage/login",
			  {
			    userName:_userName,
			    password:_password
			  },
			  function(data,status){
				  if(status){
					  location.href="${ctx}/manage/view/index";
				  }else{
					  $_user_main_meg.html(data);
				  }
			  });
		}
	};
$(document).ready(function(){
});
</script>
</BODY>
</HTML>
