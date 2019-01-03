var A = false, R = false, P = false;

$(function () {
    $("#addressId").val(-1);
    var addressOption = $("#address");
    $.ajax({
        type: "POST",
        url: "order.do?method=2",
        dataType: "json",
        async:false,
        success: function (data) {
            var addrs = data.result;
            appendOption(addrs);
            addressOption.change(function () {
                fillForm(addrs, $(this).val());
            });
        }
    });

    function appendOption(addrs) {
        for (var i = 0; i < addrs.length; i ++) {
            addressOption.append("<option value='" + i + "'>" + i +
                addrs[i].fullAddress.substr(0, 6) + "...</option>");

        }
    }

    function fillForm(addrs, index) {
        if (index === -1) {
            $("#receiveName").val('');
            $("#fullAddress").val('');
            $("#postalCode").val('');
            $("#phone").val('');
            $("#mobile").val('');
            $("#addressId").val('');
            return;
        }
        A = true; R = true; P = true;

        // $("#nameValidMsg").html('');
        // $("#addressValidMsg").html('');
        $("#receiveName").val(addrs[index].receiveName);
        $("#fullAddress").val(addrs[index].fullAddress);
        $("#postalCode").val(addrs[index].postalCode);
        $("#phone").val(addrs[index].phone);
        $("#mobile").val(addrs[index].mobile);
        $("#addressId").val(addrs[index].id);

    }

    $("#postalCode").blur(function () {
        if (!$("#postalCode").val()) {
            P = false;
            $("#codeValidMsg").css("color", "red")
        } else {
            P = true;
            $("#codeValidMsg").css("color", "black")
        }
    });

    $("#fullAddress").blur(function () {
        if (!$("#fullAddress").val()) {
            A = false;
            $("#addressValidMsg").css("color", "red")
        } else {
            A = true;
            $("#addressValidMsg").css("color", "black")
        }
    });

    $("#receiveName").blur(function () {
        // alert($("#receiveName").val());
        // var receiveName = $("#receiveName").val();
        if ($("#receiveName").val()) {
            R = true;
            $("#nameValidMsg").css("color", "black")

        } else {
            R = false;
            $("#nameValidMsg").css("color", "red")
        }
    });
});

$(function() {
    $("#btnClientRegister").click(function() {
        return (R && P && A);
    })
});

