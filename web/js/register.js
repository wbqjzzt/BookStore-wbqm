var E = false, R = false, P = false, V = false, N = false;

//注册
// $(function() {
//     $("#btnClientRegister").click(function() {
//         return (E && R && P && V && N);
//     })
// });

//昵称验证
$("#txtNickName").blur(function() {
    var val = $(this).val();
    var info = $("#name\\.info");
    info.html("");
    if ("" === val) {
        info.text("Nickname could not be Null");
        // alert("昵称不能为空");
    } else if (!val.match(/^[a-z0-9\u4e00-\u9fa5]{4,20}$/)) {
        info.text("Nickname format error");
    } else {
        N = true;
        info.html("<img src='../../images/my/ajax_ok.png' />");
    }
});

//密码验证

$("#txtPassword").blur(function() {
   var val = $(this).val();
   var info = $("#password\\.info");
   info.html("");
   if (val === "") {
       info.text("Password can not be Null");
   } else if (!val.match(/^[a-zA-Z0-9]{6,20}$/)) {
       info.text("Password format error");
   } else {
       P = true;
       info.html("<img src='../../images/my/ajax_ok.png' />");
   }
});

//密码再验证

$("#txtRepeatPass").blur(function() {
   var val = $(this).val();
   var info = $("#password1\\.info");
   info.html("");
   if (val === "") {
       info.text("Verify password could not be Null");
   } else if (val !== $("#txtPassword").val()) {
       info.text("Password do not match");
   } else {
       R = true;
       info.html("<img src='../../images/my/ajax_ok.png' />")
   }
});

//邮箱验证
$("#txtEmail").blur(function() {
   var info = $("#email\\.info");
   var email = $(this).val();
   info.html("");
   info.css("color", "red");
   if (email === "") {
       info.text("Email could not be Null");
       // alert("Email为空");
   } else if (!email.match(/^([a-zA-Z0-9]+[_|\_\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/)) {
       info.text("EMail format error");
       // alert("email格式不正确");
   } else {
       // alert("格式正确，进行ajax验证");
       $.post(
           "/validateEmail.do",
           {'email': email},
           function(data) {
               if (!data.result) {
                   info.html("<img src='../../images/my/ajax_ok.png' /> This Email can be use").css("color", "green");
                   E = true;
               } else {
                   info.text("EMail already exists");
               }
           }, 'json'
       );
   }
});

//验证码验证
$("#txtVerifyCode").blur(function() {
   var val = $(this).val();
   var info = $("#number\\.info");
   info.html("");
   if (val === "") {
       info.text("Verify code could not be Null");
   } else {
       $.post(
           "/validateCheckCode.do",
           {"checkCode": val},
           function (data) {
               if (data.result) {
                   info.html("<img src='../../images/my/ajax_ok.png' />");
                   V = true;
               } else {
                   info.text("Verify code error！");
               }
           }, 'json'
       );
   }
});

//刷新验证码
$($("#checkCode").click(function() {
    $("#imgVcode").attr("src", "checkCode.action?dt" + new Date().getTime());
}));




