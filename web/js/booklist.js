function getInfo(page) {
    $.ajax({
        url: '/bookmall_ms/list',
        type: 'POST',
        data: {
            "page": page,
        },
        cookie: true,
        success: function (data) {
            var j = typeof(data) == 'object' ? data : JSON.parse(data);
            if (j["error"] == -2){
                Message("warning", "请先登录");
                setTimeout(function () {
                    window.location.href = "/bookmall_ms/login.html";
                }, 2000);
                return;
            }
            console.log(data);
            showInfo(j, page)
        },
        error: function (e) {
            Message("error", "获取数据失败！");
            console.log(e);
        }
    });
}

function showInfo(data, page) {
    var html = '';
    var pagetext = '';
    for (var i = 0; i < data['result'].length; i++) {
        html += '<tr>';
        html += '<td>' + data['result'][i]['id'] + '</td>';
        html += '<td>' + data['result'][i]['name'] + '</td>';
        html += '<td>' + data['result'][i]['author'] + '</td>';
        html += '<td>￥' + data['result'][i]['price'] + '</td>';
        html += '<td>' + data['result'][i]['stock'] + '</td>';
        html += '<td>' + data['result'][i]['desc'] + '</td>';
        html += '<td>' + data['result'][i]['type'] + '</td>';
        html += '<td>';
        html += '<button class="button is-small" onclick="modifyBook(\'' + data['result'][i]['id']+'\')">修改</button>';
        html += '<button class="button is-small" onclick="deleteBook(\'' + data['result'][i]['id']+'\')">删除</button>';
        html += '</td>';
        html += '</tr>';
    }
    var toalpage = data['total'];
    pagetext = (page + 1) + '/' + (toalpage + 1);
    $('tbody').html(html);
    $('#page').text(pagetext);
}

function getPage() {
    var a = $('#page');
    if (a.length > 0) {
        var p = a[0].innerText.split('/');
        return [parseInt(p[0]) - 1, parseInt(p[1]) - 1];
    } else {
        return 1;
    }
}

function prevPage() {
    var page = getPage();
    if (page[0] >= 1) {
        getInfo(page[0] - 1);
    }else {
        Message('error', '已经是第一页了');
    }
}

function nextPage() {
    var page = getPage();
    if (page[0] < page[1]) {
        getInfo(page[0] + 1);
    }else {
        Message('error', '已经是最后一页了');
    }
}

function deleteBook(id) {
    $.ajax({
        url: '/bookmall_ms/delete',
        type: 'POST',
        data: {
            "bookId": id,
        },
        cookie: true,
        success: function (data) {
            var j = typeof(data) == 'object' ? data : JSON.parse(data);
            if (j["error"] == -2){
                Message("warning", "请先登录");
                setTimeout(function () {
                    window.location.href = "/bookmall_ms/login.html";
                }, 2000);
                return;
            }
            if (j['error'] == 0) {
                Message('success', '删除成功');
                getInfo(getPage()[0]);
            }else {
                Message('error', '删除失败');
            }
        },
        error: function () {
            Message('error', '服务器错误，删除失败');
        },
    });
}

function firstPage() {
    getInfo(0);
}

function lastPage() {
    getInfo(getPage()[1]);
}

function modifyBook(id){
    window.location = 'book-modify.html?id=' + id;
}

