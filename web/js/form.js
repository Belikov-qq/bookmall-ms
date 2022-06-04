$(function () {
    $('#bookAddBtn').bind("click").on("click", function (){submit('/bookmall_ms/add', 'bookAddBtn')});
});

$(function () {
    $('#bookModifyBtn').bind("click").on("click", function (){submit('/bookmall_ms/modify', 'bookModifyBtn')});
});

function submit(url, id){
    var ids = ['id', 'name', 'author', 'price', 'stock', 'description'];
    var names = ['bookId', 'name', 'author', 'price', 'stock', 'desc'];
    var book_info = {};
    for (var i = 0; i < names.length; i++) {
        book_info[names[i]] = $('#' + ids[i]).val();
    }
    book_info['type'] = $('input[name="bookType"]:checked').val() == '1' ? "原创" : "翻译";
    $.ajax({
        url: url,
        type: "POST",
        data: book_info,
        cookie: true,
        beforeSend: function () {
            $('#'+id).attr('disabled', true);
            if (book_info["bookId"] == '') {
                Message('warning', '请填写完整信息');
                $('#'+id).attr('disabled', false);
                return false;
            }
        },
        success: function (data){
            var j = typeof(data) == 'object' ? data : JSON.parse(data);
            if (j["error"] == -2){
                Message("warning", "请先登录");
                setTimeout(function () {
                    window.location.href = "/bookmall_ms/login.html";
                }, 2000);
                return;
            }
            if (j['error'] == 0){
                Message('success', j['msg']);
            } else {
                Message('warning', j['msg']);
            }
        },
        error: function (data) {
            Message('warning', '服务器错误');
        },
        complete: function () {
            $('#'+id).attr('disabled', false);
        },
    });
}
