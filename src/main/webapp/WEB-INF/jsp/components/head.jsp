<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入 Bootstrap -->
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
	<script src="/resources/js/logout.js"></script>
	<!-- 百度统计 -->
	<script>
		var _hmt = _hmt || [];
		(function () {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?2501d222463e4d9e41c0a36684b37637";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
	<style type="text/css">
		.weather-position {
			position: absolute;
			right: 20px;
			top: 14.3px;
			margin-right: 250px;
		}

		.weather-position_1 {
			position: absolute;
			right: 20px;
			top: 14.3px;
			/* margin-right: 250px; */
			display: none;
		}

		@media (max-width: 1865px) {
			.weather-position {
				display: none;
			}
		}
		@media (min-width: 1430px) and (max-width: 1865px) {
			.weather-position_1 {
				display: block;
			}
		}
	</style>
</head>

<body>
	<nav class="navbar navbar-default" style="border-radius: 0; margin-bottom: 10px;">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
					aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" style="font-weight: 700; font-size: 27px;" href="/">IT Valley</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse header-navbar">
				<form class="navbar-form navbar-left hidden-xs hidden-sm" role="search" action="/search" method="get">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" name="s" value="" style="width: 270px;"
							placeholder="回车搜索">
					</div>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden-xs" id="shouye"><a href="/">首页</a></li>
					<li id="biaoqian"><a href="/tags">标签</a></li>
					<li id="loginli" style="display:none"><a href="/login">登录</a></li>
					<li id="zhuceli" style="display:none"><a href="/register">注册</a></li>
					<li class="hidden-md hidden-lg"><a href="/topic/create">发布文章</a></li>
					<li id="loginuser" style="display:none"><a href="/user/public"><span class="badge"
								id="badge"></span></a></li>
					<li id="shezhili" style="display:none"><a href="/user/settings/profile">设置</a></li>
					<li id="tuichuli" style="display:none"><a
							href="javascript:if(confirm('确定要登出IT Valley吗？'))location.href='/logout'">退出</a></li>
				</ul>
			</div>
			<div id="tp-weather-widget" class="weather-position"></div>
			<div id="tp-weather-widget_1" class="weather-position_1"></div>
		</div>
	</nav>
	<div class="container" style="padding: 0 25px;">
		<form class="hidden-lg hidden-md" style="margin: 0 -10px;" role="search" action="/search" method="get">
			<div class="form-group has-feedback" style="margin-bottom: 10px;">
				<input type="text" class="form-control" name="s" value="" placeholder="回车搜索">
				<!-- <span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true"></span> -->
			</div>
		</form>
		<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
		<script src="/resources/js/jquery.js"></script>
		<!-- 引入 Bootstrap -->
		<script src="/resources/js/bootstrap.min.js"></script>
		<!-- <script src="/resources/js/head.js"></script> -->
		<script type="text/javascript">
			$(function () {
				$.ajax({
					type: "get",
					url: "/session",
					dataType: "json",
					success: function (data) {
						console.log("session:", data.user, data.success)
						console.log(JSON.stringify(data));
						if (data.success != null && data.success == true) {
							$("#loginuser").show();
							// 设置该标签的内容为获取的用户名
							$("#loginuser a").text(data.user);
							// 给用户名标签设置 href 属性， 并设置跳转连接
							$("#loginuser a").attr("href", "/user/" + data.user);
							$("#shezhili").show();
							$("#tuichuli").show();
						}
						if (data.success != null && data.success == false) {
							$("#loginli").show();
							$("#zhuceli").show();
							$("#nologin").show();
						}
					},
					error: function (data) {

					}
				});
			});

			// 天气API
			(function (a, h, g, f, e, d, c, b) {
				b = function () {
					d = h.createElement(g);
					c = h.getElementsByTagName(g)[0];
					d.src = e;
					d.charset = "utf-8";
					d.async = 1;
					c.parentNode.insertBefore(d, c)
				};
				a["SeniverseWeatherWidgetObject"] = f;
				a[f] || (a[f] = function () {
					(a[f].q = a[f].q || []).push(arguments)
				});
				a[f].l = +new Date();
				if (a.attachEvent) {
					a.attachEvent("onload", b)
				} else {
					a.addEventListener("load", b, false)
				}
			}(window, document, "script", "SeniverseWeatherWidget", "//cdn.sencdn.com/widget2/static/js/bundle.js?t=" +
				parseInt((new Date().getTime() / 100000000).toString(), 10)));
			window.SeniverseWeatherWidget('show', {
				flavor: "slim",
				location: "WX4BZD6KEJFY",
				geolocation: true,
				language: "zh-Hans",
				unit: "c",
				theme: "auto",
				token: "d7fee291-95b6-4fea-8ab0-863c6f9babb7",
				hover: "disabled",
				container: "tp-weather-widget_1"
			});
			
			(function (a, h, g, f, e, d, c, b) {
				b = function () {
					d = h.createElement(g);
					c = h.getElementsByTagName(g)[0];
					d.src = e;
					d.charset = "utf-8";
					d.async = 1;
					c.parentNode.insertBefore(d, c)
				};
				a["SeniverseWeatherWidgetObject"] = f;
				a[f] || (a[f] = function () {
					(a[f].q = a[f].q || []).push(arguments)
				});
				a[f].l = +new Date();
				if (a.attachEvent) {
					a.attachEvent("onload", b)
				} else {
					a.addEventListener("load", b, false)
				}
			}(window, document, "script", "SeniverseWeatherWidget", "//cdn.sencdn.com/widget2/static/js/bundle.js?t=" +
				parseInt((new Date().getTime() / 100000000).toString(), 10)));
			window.SeniverseWeatherWidget('show', {
				flavor: "slim",
				location: "WX4BZD6KEJFY",
				geolocation: true,
				language: "zh-Hans",
				unit: "c",
				theme: "auto",
				token: "d7fee291-95b6-4fea-8ab0-863c6f9babb7",
				hover: "enabled",
				container: "tp-weather-widget"
			})
		</script>
</body>

</html>