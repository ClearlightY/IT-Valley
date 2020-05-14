<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>IT Valley-${user.userName}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
    <link rel="shortcut icon" href="/resources/images/favicon.ico">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <style type="text/css">
        .links-of-author-item a:before {
            display: inline-block;
            vertical-align: middle;
            margin-right: 3px;
            content: " ";
            width: 4px;
            height: 4px;
            border-radius: 50%;
            background: #b66cff;
        }

        .links-of-author-item {
            margin-left: 11px;
            font-size: 13px;
        }

        .links-of-author-item_1 {
            margin-left: 8px;
            font-size: 14px;
        }

        .person_url {
            margin-top: 16px;
        }

        .user_detail {
            margin-top: 13px;
        }
    </style>
</head>

<body>
    <div class="wrapper">
        <jsp:include page="../components/head.jsp"></jsp:include>
        <div class="row">

            <!-- 小屏幕显示 -->
            <div class="col-md-9">
                <div class="box">
                    <div class="cell">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tbody>
                                <tr>
                                    <td width="73" valign="top" align="center">
                                        <img src="${user.avatar}" border="0" align="default" style="border-radius: 4px;"
                                            width="73" height="73px" />
                                        <div class="sep10"></div>
                                    </td>
                                    <td width="10"></td>
                                    <td width="auto" valign="top" align="left">
                                        <c:if test="${user2 != null && user2.userId != user.userId}">
                                            <div class="fr">
                                                <button class="btn btn-follow" onclick="save()" id="follow">加入特别关注
                                                </button>
                                                <!-- <div class="sep10"></div>
                                <button class="btn btn-warning">Block</button> -->
                                            </div>
                                        </c:if>
                                        <h1 title="${user.userId}" id="user_id" class="user_id">${user.userName}</h1>
                                        <span class="gray" style="font-size: 14px;">IT Valley 第 ${user.userId} 号会员，加入于
                                            <fmt:formatDate type="both" value="${user.createDate}" />
                                            <div class="sep5"></div>
                                        </span>
                                        <div class="user_detail">
                                            <span class="links-of-author-item_1">文章 <span
                                                    class="gray">${countTopic}</span></span>
                                            <span class="links-of-author-item_1">收藏 <span
                                                    class="gray">${countCollect}</span></span>
                                            <span class="links-of-author-item_1">评论 <span
                                                    class="gray">${countReply}</span></span>
                                            <span class="links-of-author-item_1">获赞 <span
                                                    class="gray">${countTopicClick}</span></span>
                                            <span class="links-of-author-item_1">积分 <span
                                                    class="gray">${countScore}</span></span>
                                            <span class="links-of-author-item_1">访问 <span
                                                    class="gray">${countVisit}</span></span>
                                            <span class="links-of-author-item_1">排名 <span class="gray">2</span></span>
                                        </div>
                                        <div class="person_url">
                                            <c:if test="${user.url !=null && user.url != ''}">
                                                <span class="links-of-author-item"><a href="http://${user.url}"
                                                        target="_blank" title="website"><i
                                                            class="fa fa-globe fa-fw"></i>Website</a> </span>
                                            </c:if>
                                            <c:if test="${user.email != null && user.email != ''}">
                                                <span class="links-of-author-item"><a href="mailto:${user.email}"
                                                        target="_blank" title="E-Mail"><i
                                                            class="fa fa-fw fa-envelope"></i>E-Mail</a> </span>
                                            </c:if>
                                            <c:if test="${user.thirdId != null && user.thirdId != ''}">
                                                <span class="links-of-author-item gray"><a id="a_github" target="_blank"
                                                        href="https://${user.thirdId}" title="GitHub"><i
                                                            class="fa fa-fw fa-github"></i>GitHub</a> </span>
                                            </c:if>
                                        </div>
                                        <br />

                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- <div class="sep5"></div> -->
                    </div>
                </div>
                <div class="sep20"></div>
                <div class="panel panel-default">
                    <%-- <div class="panel-heading">${user.userName}创建的文章</div> --%>
                    <div class="cell_tabs">
                        <div class="fl"><img src="${user.avatar}" width="24"
                                style="border-radius: 24px; margin-top: -2px;" border="0"></div>
                        <!-- <a href="javascript:void(0);" onclick="activitiesList()" class="cell_tab_current">动态</a> -->
                        <a href="javascript:void(0);" onclick="topicList()" class="cell_tab_current">文章</a>
                        <a href="javascript:void(0);" onclick="replyList()" class="cell_tab">评论</a>
                        <a href="javascript:void(0);" onclick="collectList()" class="cell_tab">收藏</a>
                        <a href="javascript:void(0);" onclick="followList()" class="cell_tab">关注</a>
                        <a href="javascript:void(0);" onclick="fansList()" class="cell_tab">粉丝</a>
                        <!-- <a href="javascript:void(0);" onclick="topicQnaList()" class="cell_tab">提问</a> -->
                    </div>
                    <div class="itemList"></div>
                </div>
                <button id="toggleBigImageBtn" data-toggle="modal" class="hidden"
                    data-target="#showBigImageModal"></button>
                <div class="modal fade" tabindex="-1" role="dialog" id="showBigImageModal">
                    <div class="modal-dialog" style="width: 98%" role="document">
                        <div class="modal-content">
                            <div class="modal-body text-center">
                                <img src="" id="bigImage" style="max-width: 100%;" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="back2Top" class="backTop___6Q-ki" style="display:none">
        <div class="line___F1WY0"></div>
        <div class="arrow___3UCwo"></div>
    </div>
    </div>
    <jsp:include page="../components/foot.jsp"></jsp:include>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/goTop.js"></script>
    <script src="/resources/layui/layui.js"></script>
    <script src="/resources/layui/layui-paginate.js"></script>
    <script src="/resources/js/user/detail.js"></script>
    <script src="/resources/js/formatDate.js"></script>
    <script>
        // var url = $("#a_github").text();
        // console.log("github.url",url)
        // $('#a_github').attr('href',url);
    </script>
</body>

</html>