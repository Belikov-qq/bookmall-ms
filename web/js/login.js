$(function () {
    $('#loginBtn').bind("click").on("click", function () {
        var username = $('#username').val();
        var password = $('#password').val();
        console.log(username, password);
        $.ajax({
            url: "/bookmall_ms/login",
            type: "POST",
            data: {
                username: username,
                password: password,
            },
            beforeSend: function () {
                $('#loginBtn').attr('disabled', true);
                if (username == '' || password == '' || captcha == '') {
                    Message('warning', '请填写完整信息');
                    $('#loginBtn').attr('disabled', false);
                    return false;
                }
            },
            success: function (data) {
                var j = JSON.parse(data);
                if (j["status"] == 1) {
                    Message('success', '登录成功');
                    setTimeout(function () {
                        window.location.href = '/bookmall_ms/book-add.html';
                    }, 1000);
                } else {
                    Message('warning', j["msg"]);
                    $('#loginBtn').attr('disabled', false);
                }
            },
            error: function (data) {
                Message('warning', '服务器错误');
            },
            complete: function () {
                $('#loginBtn').attr('disabled', false);
            },
        });
    });
});
