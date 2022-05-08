$(function () {
    $('#infoSubmitBtn').bind("click").on("click", function () {
            var ids = ['id', 'name', 'author', 'price', 'cover', 'description', 'left'];
            var book_info = {};
            for (var i = 0; i < ids.length; i++) {
                book_info[ids[i]] = $('#' + ids[i]).val();
            }
            book_info['type'] = $('input[name="bookType"]:checked').val();
            book_info = JSON.stringify(book_info);
            $.ajax({
                url: "",
                type: "POST",
                data: book_info,
                beforeSend: function () {
                    $('#infoSubmitBtn').attr('disabled', true);
                    for (var i = 0; i < book_info.length; i++) {
                        if (book_info[i] == '') {
                            Message('warning', '请填写完整信息');
                            $('#infoSubmitBtn').attr('disabled', false);
                            return false;
                        }
                    }
                },
                complete: function () {
                    $('#infoSubmitBtn').attr('disabled', false);
                },
            });
        }
    );
});