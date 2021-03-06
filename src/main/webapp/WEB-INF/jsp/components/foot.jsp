<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<head>
	<style type="text/css">
		@media (max-width: 922px) {
			.footpoi {
				margin-left: 90px
			}
		}
	</style>
</head>

<body>
	<div class="container">
		<br>
		<p style="color: #778087;">
			<strong class="footpoi">
				<a href="/about" style="color: gray;">关于</a>
				&nbsp;
				<span class="snow" style="color: #e2e2e2;">·</span>
				&nbsp;
				<a href="/faq" style="color: gray;">FAQ</a>
				&nbsp;
				<span class="snow" style="color: #e2e2e2;">·</span>
				<%--				<a href="/api" style="color: gray;">API</a>--%>
				<%--				<span class="snow" style="color: #e2e2e2;">·</span>--%>
				&nbsp;
				<a href="/mission" style="color: gray;">愿景</a>
				&nbsp;
				<span class="snow" style="color: #e2e2e2;">·</span>
				&nbsp;
				<a href="/feedback" style="color: gray;">建议反馈</a>
			</strong>
			<span class="pull-right">
				<a href="/admin/login" target="_blank">登录后台</a>
			</span>
		</p>

		<div class="footpoi">
			<p style="color: #778087;"><a data-v-2b9fe4cd="">©2020 IT Valley</a></p>
			<p style="color: #ccc;">
				<font color="red">♥</font> 「Stay Hungry, Stay Foolish.」<font color="red">♥</font>
			</p>
			<p><a href="http://beian.miit.gov.cn/publish/query/indexFirst.action" target="_blank"
					rel="nofollow noopener">冀ICP备19019307号</a></p>
		</div>
		<link rel="stylesheet" href="/resources/css/APlayer.min.css">
		<div id="aplayer"></div>
		<script type="text/javascript" src="/resources/js/APlayer.min.js"></script>
		<script type="text/javascript" src="/resources/js/music.js"></script>
	</div>

</body>

</html>