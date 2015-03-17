<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>在线考试后台管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/manage/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/manage/main.css" />
<script type="text/javascript"
	src="${ctx }/static/js/manage/modernizr.min.js"></script>
</head>
<body>
	<!-- start head -->
	<%@include file="../manage/common/head.jsp"%>
	<!-- end head -->
	<div class="container clearfix">
		<!-- start menu -->
		<%@include file="../manage/common/menu.jsp"%>
		<!-- end menu -->

		<!--/sidebar-->
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font">&#xe06b;</i><span>欢迎[${sessionScope.manageLoginUser.userName }]登录在线考试后台管理！</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-title">
					<h1>快捷操作</h1>
				</div>
				<div class="result-content">
					<div class="short-wrap">
						<a href="#"><i class="icon-font">&#xe001;</i>新增作品</a> <a href="#"><i
							class="icon-font">&#xe005;</i>新增博文</a> <a href="#"><i
							class="icon-font">&#xe048;</i>新增作品分类</a> <a href="#"><i
							class="icon-font">&#xe041;</i>新增博客分类</a> <a href="#"><i
							class="icon-font">&#xe01e;</i>作品评论</a>
					</div>
				</div>
			</div>

		</div>
		<!--/main-->
	</div>
</body>
</html>