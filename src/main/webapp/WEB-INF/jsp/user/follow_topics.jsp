<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<head>
	<title>${user.userName}关注的话题</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入 Bootstrap -->
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
	<!-- 引入layui.css -->
	<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
	<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>

<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading"><a href="/">主页</a> / ${user.userName}关注的用户</div>
					<div class="panel-body paginate-bot">
						<c:forEach items="${follows}" var="follow">
							<!-- ${follow.userName}----${follow.signature} -->
							<li data-v-dc1504f6="" class="item">
								<div data-v-dc1504f6="" itemscope="itemscope" itemtype="http://schema.org/Person"
									class="user">
									<a data-v-dc1504f6="" href="/user/${follow.userName} " class="link">
										<div class="media-left">
											<img src="${follow.avatar}" class="avatar img-circle" alt="">
										</div>
										<div data-v-dc1504f6="" class="info-box">
											<div data-v-dc1504f6="" class="username" title="${follow.userId}">
												${follow.userName}</div>
											<div data-v-dc1504f6="" class="detail">${follow.signature}</div>
										</div><button data-v-dc1504f6="" class="follow-btn">已关注</button>
									</a>
								</div>
							</li>
						</c:forEach>
					</div>
					<div class="panel-footer" id="paginate"></div>
				</div>
			</div>
			<div class="col-md-3 hidden-sm hidden-xs">
				<div class="panel panel-default" id="session"></div>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<script src="/resources/js/jquery.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/layui/layui.js"></script>
	<script src="/resources/layui/layui-paginate.js"></script>
	<script src="/resources/js/login_info.js"></script>
	<script type="text/javascript">
		//var url = "/collect/topics?";
		//$(".pagination2").pagination("${page.pageNumber}","${page.totalPage}",10);
		var count = $ {
			page.totalRow
		}; //数据总量
		var limit = $ {
			page.pageSize
		}; //每页显示的条数
		var url = "/collect/topics?p="; //url
		function page() {
			var page = location.search.match(/p=(d+)/);
			return page ? page[1] : 1;
		}
		var p = page(); //当前页数
		//console.log("p:"+p);
		//console.log(count);
		//console.log(url);
		paginate(count, limit, p, url);
	</script>
</body>

</html>