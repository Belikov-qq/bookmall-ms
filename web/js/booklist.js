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

function getCurrentPage() {
    var a = $('#page');
    if (a.length > 0) {
        return parseInt(a[0].innerText.split('/')[0]);
    } else {
        return 1;
    }
}

function prevPage() {
    var page = getCurrentPage();
    if (page > 1) {
        getInfo(page - 1);
    }else {
        Message('error', '已经是第一页了');
    }
}

function nextPage() {
    var page = getCurrentPage();
    getInfo(page + 1);
}

function deleteBook(id) {
    $.ajax({
        url: 'http://localhost:5000/delete',
        type: 'POST',
        data: {
            "id": id,
        },
        cookie: true,
        success: function (data) {
            var j = JSON.parse(data);
            if (j['status'] == 'success') {
                Message('success', '删除成功');
            }
        }
    });
}

function firstPage() {
    getInfo(1);
}

function lastPage() {
    getInfo(-1);
}