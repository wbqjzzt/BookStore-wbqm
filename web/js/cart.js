//购买
$(function () {
    $(".list_r_list_button").click(function () {
        var href = $(this).find("a").attr("href");
        var v_flag = false;
        var info = $(this).next(".mycartinfo");
        info.html("");
        $.ajax({
            type: "GET",
            url: href,
            dataType: 'json',
            success:function (data) {
                if (data.result) {
                    info.html("<a href='./car.do?method=4'>Order success, go to the cart</a>");
                } else if (data.result === false) {
                    info.html("<a href='./user/login.do?method=0'>You were logout, go to login</a>")
                } else {
                    info.html("Order failed");
                }
            }
        });
        return false;
    });
});

