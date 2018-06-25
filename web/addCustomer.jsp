<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.bg {
	max-width: 530px;
	padding: 15px;
	margin: 0 auto;
}
</style>
<title>添加客户信息</title>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-md-offset-2">
				<form class="form-horizontal" action="${pageContext.request.contextPath}/customer/addCustomer" method="post" enctype="multipart/form-data">
				  <div class="form-group">
				    <label class="col-sm-2 control-label">姓名</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="cusName" placeholder="姓名">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-2 control-label">手机号</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="cusPhone" placeholder="手机号">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-2 control-label">头像</label>
				    <div class="col-sm-10">
				      <input type="file" class="form-control" name="cusImg" placeholder="头像">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-default">add Customer</button>
				    </div>
				  </div>
				</form>
			</div>
		</div>
	</div>






</body>
</html>