function getInfo(page) {
    $.ajax({
        url: 'http://localhost:5000/list',
        type: 'POST',
        data: {
            "page": page,
        },
        cookie: true,
        success: function (data) {
            console.log(data);
            var j = JSON.parse(data);
            showInfo(j)
        }
    });
}
function showInfo(data) {
    var html = '';
    for (var i = 0; i < data['result'].length; i++) {
        html += '<tr>';
        html += '<td>' + data['result'][i]['id'] + '</td>';
        html += '<td><img src="' + data['result'][i]['cover'] + '" alt=""></td>';
        html += '<td>' + data['result'][i]['title'] + '</td>';
        html += '<td>' + data['result'][i]['author'] + '</td>';
        html += '<td>￥' + data['result'][i]['price'] + '</td>';
        html += '<td>' + data['result'][i]['left'] + '</td>';
        html += '<td>' + data['result'][i]['desc'] + '</td>';
        html += '<td>' + ((data['result'][i]['type'] == 1) ? "原创" : "翻译" ) + '</td>';
        html += '<td>';
        html += '<button class="button is-small" onclick="modifyBook(\'' + data['result'][i]['id']+'\')">修改</button>';
        html += '<button class="button is-small" onclick="deleteBook(\'' + data['result'][i]['id']+'\')">删除</button>';
        html += '</td>';
        html += '</tr>';
    }
    $('tbody').html(html);
}