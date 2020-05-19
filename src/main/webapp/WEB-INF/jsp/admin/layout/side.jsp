<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- 侧边栏 -->
<aside class="main-sidebar" style="position: fixed">
  <section class="sidebar">
    <ul class="sidebar-menu">
      <div class="user-panel">
        <div class="pull-left image">
          <img src="/resources/images/default-avatar.jpg" class="img-circle" alt="User Image" id="user-avatar">
        </div>
        <div class="pull-left info">
          <p id="user-name">admin</p>
          <a href="#"><i class="fa fa-circle text-success"></i>管理员</a>
        </div>
      </div>
      <li class="header">MAIN NAVIGATION</li>
      <shiro:hasPermission name="index:index">
        <li>
          <a href="/admin/index">
            <i class="fa fa-dashboard"></i>
            <span>首页</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="topic:list">
        <li>
          <a href="/admin/topic/list">
            <i class="fa fa-list"></i>
            <span>文章管理</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="reply:list">
        <li>
          <a href="/admin/reply/list">
            <i class="fa fa-comment"></i>
            <span>评论管理</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="user:list">
        <li>
          <a href="/admin/user/list">
            <i class="fa fa-user"></i>
            <span>用户管理</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="system:edit">
        <li>
          <a href="/admin/system/edit?pid=4&index=2">
            <i class="fa fa-circle-o"></i>积分设置
          </a>
        </li>
      </shiro:hasPermission>
      <li>
        <a href="/admin/logout">
          <i class="fa fa-sign-out"></i>
          <span>登出</span>
        </a>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
  <script type="text/javascript">
    $(function () {
      $.ajax({
        url: "/admin/user/info",
        type: "get",
        dataType: "json",
        success: function (data) {
          if (data.success === true) {
            $("#user-name").text(data.data.username);
            $("#user-avatar").attr("src", data.data.avatar);
          }
        },
        error: function (data) {

        }
      });
    })
  </script>
</aside>