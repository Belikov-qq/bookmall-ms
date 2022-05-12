$(function () {
    $('#bookAddBtn').bind("click").on("click", function (){submit('bookadd', 'bookAddBtn')});
});

$(function () {
    $('#BookModifyBtn').bind("click").on("click", function (){submit('modify', 'bookModifyBtn')});
});

function submit(url, id){
    var ids = ['id', 'name', 'author', 'price', 'cover', 'description', 'left'];
    var book_info = {};
    for (var i = 0; i < ids.length; i++) {
        book_info[ids[i]] = $('#' + ids[i]).val();
    }
    book_info['type'] = $('input[name="bookType"]:checked').val();
    $.ajax({
        url: url,
        type: "POST",
        data: book_info,
        beforeSend: function () {
            $('#'+id).attr('disabled', true);
            for (var i = 0; i < book_info.length; i++) {
                if (book_info[i] == '') {
                    Message('warning', '请填写完整信息');
                    $('#'+id).attr('disabled', false);
                    return false;
                }
            }
        },
        success: function (data){
            var j = JSON.parse(data);
            if (j['error'] == 0){
                Message('success', j['message']);
            }
        },
        complete: function () {
            $('#'+id).attr('disabled', false);
        },
    });
}