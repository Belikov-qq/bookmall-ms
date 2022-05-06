$(function () {
    $('#loginBtn').bind("click").on("click", function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var captcha = $('#captcha').val();
        console.log(username, password);
        $.ajax({
            url: "",
            type: "POST",
            data: {
                username: username,
                password: password,
                captcha: captcha
            },
            beforeSend: function () {
                $('#loginBtn').attr('disabled', true);
                if (username == '' || password == '' || captcha == '') {
                    Message('warning','请填写完整信息');
                    $('#loginBtn').attr('disabled', false);
                    return false;
                }
            },
            complete: function () {
                $('#loginBtn').attr('disabled', false);
            },
        });
    });
});