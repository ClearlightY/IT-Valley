$(function () {
  $("#loginli").addClass("active");
  $("#form").on("click", "#btn", function () {
    var username = $("#username").val();
    var password = $("#password").val();
    if (username.length === 0) {
      alert('用户名不能为空');
      return false;
    }
    if (password.length === 0) {
      alert('密码不能为空');
      return false;
    }
    $.ajax({
      // 请求方式post
      type: "post",
      // 请求地址 /login
      url: "/login",
      // 返回的数据类型格式 json
      dataType: "json",
      // 发送请求时的参数
      data: {
        // 用户输入的内容
        username: $("#username").val(),
        password: $("#password").val(),
      },
      // 返回成功 其中参数data为服务器响应的内容
      success: function (data) {
        // 如果返回的数据为空或者返回false，则提示错误
        if (data.success != null && data.success == false) {
          alert(data.error);
          return false;
        } else {
          // 登录成功，跳转到首页
          location.href = "/";
        }
      },
      error: function (data) {
      }
    });
  });
});