$(function () {
    var options = {
        strings: ["在线书城后台管理系统-欢迎您登录"],
        typeSpeed: 30,
        startDelay: 0,
        backSpeed: 0,
        loop: false,
        // loopCount: Infinity,
    }
    var type = new Typed(".title", options);
    $('#loginBtn').bind("click").on("click", function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var salt = randomString(10, '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        var hash = sha256_digest(window.btoa(username) + ':' + password);
        // console.log(salt, hash, window.btoa(username));
        hash = sha256_digest(hash + salt);
        // console.log(hash)
        $.ajax({
            url: "/bookmall_ms/login",
            type: "POST",
            data: {
                username: username,
                password: hash,
                salt: salt,
            },
            beforeSend: function () {
                var btn = $('#loginBtn')
                btn.attr('disabled', true);
                if (username === '') {
                    Message('warning', '请填写用户名');
                    $("#username").each((i, e) => {
                        infoIsWrong(e)
                    });
                    btn.attr('disabled', false);
                    return false;
                } else {
                    if (password === "") {
                        Message('warning', '请填写密码');
                        $("#password").each((i, e) => {
                            infoIsWrong(e)
                        });
                        btn.attr('disabled', false);
                        return false;
                    }
                }
            },
            success: function (data) {
                var j = typeof (data) == 'object' ? data : JSON.parse(data);
                if (j["status"] == 1) {
                    Message('success', '登录成功');
                    setTimeout(function () {
                        window.location.href = '/bookmall_ms/book-add.html';
                    }, 1000);
                } else {
                    Message('warning', j["msg"]);
                    $("input").each((i, e) => {
                        infoIsWrong(e);
                    });
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

randomString = (length, chars) => {
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
    return result;
}

infoIsWrong = (element) => {
    element.classList.add("is-danger");
    element.onchange = (e) => {
        reset(e);
    }
}

reset = (e) => {
    e.target.classList = ["input"];
    e.target.onchange = null;
}

