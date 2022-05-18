$(function () {
    $('#bookAddBtn').bind("click").on("click", function (){submit('/bookmall_ms/BookAddServlet', 'bookAddBtn')});
});

$(function () {
    $('#bookModifyBtn').bind("click").on("click", function (){submit('/bookmall_ms/BookModifyServlet', 'bookModifyBtn')});
});

function submit(url, id){
    var ids = ['id', 'name', 'author', 'price', 'cover', 'description'];
    var names = ['bookId', 'name', 'author', 'price', 'cover', 'desc'];
    var book_info = {};
    for (var i = 0; i < names.length; i++) {
        book_info[names[i]] = $('#' + ids[i]).val();
    }
    book_info['type'] = $('input[name="bookType"]:checked').val() == '1' ? "原创" : "翻译";
    $.ajax({
        url: url,
        type: "POST",
        data: book_info,
        beforeSend: function () {
            $('#'+id).attr('disabled', true);
            if (book_info["bookId"] == '') {
                Message('warning', '请填写完整信息');
                $('#'+id).attr('disabled', false);
                return false;
            }
        },
        success: function (data){
            var j = JSON.parse(data);
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
