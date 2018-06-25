<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript">
	function del(id){
		location.href="/customer/delCustomer?id="+id;
	}

	function delOrder(orderId,id,pageNo) {
        $.post("/order/delOrder",{orderId:orderId},function () {
            showPage(id,parseInt(pageNo));
        })
    }

    //显示页面实现代码
    function showPage(id, pageNo) {
        pageNo = parseInt(pageNo);
        //全局变量,用于分页
        var showResultNo = 5;	//每页显示结果数,默认值5

        //重置表格数据,跟分页栏
        $("#page").html("");
        $("#orders").html("<tr class='info'><td>订单编号</td><td>订单地址</td><td>订单价格</td><td>客户名称</td><td>操作</td></tr>");

        var html="";		//分页栏拼接
        var tab=""			//商品信息拼接

        //上翻页按钮
        if (pageNo==1) {
            html += "<ul class='pagination'><li class='disabled'><a href='javascript:void(0)' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
        } else {
            html += "<ul class='pagination'><li><a href='javascript:void(0)' aria-label='Previous' onclick=showPage("+id+","+(pageNo-1)+")><span aria-hidden='true'>&laquo;</span></a></li>";
        }

        //向服务器发送请求
        $.post("/order/findOrderByCustomerId",{id:id,pageNo:pageNo,showResultNo:showResultNo},function(data){
            //解析数据,显示商品信息
            var jsonObj = eval(data)
            $(jsonObj.model.content).each(function(i){
                tab += "<tr><td>"+this.orderNum+"</td><td>"+this.receiverInfo+"</td><td>"+this.price+"</td><td>"+this.customer.cusName+"</td><td><a href='javascript:void(0)' onclick='delOrder("+this.orderNum+","+id+","+pageNo+")'>删除</a><br /></td></tr>"

            });

            $("#orders").append($(tab));

            //解析数据,拼接分页栏
            if (jsonObj.model.totalPageNo <= 10) {// 总页数小于等于10
                for(var i = 1; i <= jsonObj.model.totalPageNo; i++){
                    if (pageNo == i) {
                        html += "<li class='active'><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                    } else {
                        html += "<li><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                    }
                };
            } else {//总页数大于10
                if (pageNo <= 5) {//当前页小于等于5
                    for(var i = 1; i <= 10; i++){
                        if (pageNo == i) {
                            html += "<li class='active'><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                        } else {
                            html += "<li><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                        }
                    };
                } else if(pageNo <= (jsonObj.model.totalPageNo-4)) {//当前页大于5,小于总页数减4
                    for(var i = (pageNo-5); i <= (pageNo+4); i++){
                        if (pageNo == i) {
                            html += "<li class='active'><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                        } else {
                            html += "<li><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                        }
                    };
                } else {//当前页是最后四页
                    for(var i = (jsonObj.model.totalPageNo-10); i <= jsonObj.model.totalPageNo; i++){
                        if (pageNo == i) {
                            html += "<li class='active'><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                        } else {
                            html += "<li><a href='javascript:void(0)' onclick=showPage("+id+",this.innerHTML)>"+i+"</a></li>"
                        }
                    };
                }
            }
            //下翻页按钮
            if (pageNo==jsonObj.model.totalPageNo) {
                html += "<li class='disabled'><a href='javascript:void(0)' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li></ul>";
            } else {
                html += "<li><a href='javascript:void(0)' aria-label='Next' onclick=showPage("+id+","+(pageNo+1)+")><span aria-hidden='true'>&raquo;</span></a></li></ul>";
            }

            //将html拼接到ul中
            $("#page").append($(html));
        },"json");
    }

</script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix">
				  <div class="container">
				    <a href="${pageContext.request.contextPath}/addCustomer.jsp" class="btn btn-primary btn-lg" role="button">add Customer</a>
				  </div>
				</nav>
			</div>
			<div class="col-sm-8">
				<table class="table table-hover table-bordered text-center">
					<tr class="info">
						<td>编号</td>
						<td>头像</td>
						<td>姓名</td>
						<td>联系方式</td>
						<td>操作</td>
					</tr>
				  	<s:iterator value="cusList" var="c" status="st">
				  		<tr>
							<td><s:property value="#st.index+1"/></td>
							<td><img class="img-circle" src="<s:property value="#c.cusImgSrc"/>" style="width: 100px; width: 100px"></td>
							<td><s:property value="#c.cusName"/></td>
							<td><s:property value="#c.cusPhone"/></td>
                            <td>
                                <a href="javascript:void(0)" onclick="del(<s:property value="#c.id"/>)">删除</a><br />
                                <a href="javascript:void(0)" data-toggle="modal" data-target=".bs-example-modal-lg" onclick="showPage(<s:property value="#c.id"/>,1)">订单</a><br />
                            </td>
						</tr>
				  	</s:iterator>
				</table>
			</div>
		</div>
	</div>

    <!--模态框-->
    <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">订单信息</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-hover table-bordered" id="orders">


                    </table>
                </div>
                <div class="modal-footer">
                    <nav id="page">

                    </nav>
                </div>
            </div>
        </div>
    </div>


</body>
</html>