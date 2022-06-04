function getUrlPara(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return (r[2]); return null;
}

function initInfo(){
    var id = parseInt(getUrlPara('id'));
    $.ajax({
        url: '/bookmall_ms/query',
        type: 'POST',
        data: {
            'id': id
        },
        cookie: true,
        beforeSend: function () {
            $('#id')[0].value = id;
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
            $('#name')[0].value = j['result'].hasOwnProperty('name') ? j['result']['name'] : '';
            $('#author')[0].value = j['result'].hasOwnProperty('author') ? j['result']['author'] : '';
            $('#price')[0].value = j['result'].hasOwnProperty('price') ? j['result']['price'] : '';
            $('#description')[0].value = j['result'].hasOwnProperty('desc') ? j['result']['desc'] : '';
            $('input[name="bookType"]')[(j['result'].hasOwnProperty('type') ? (j['result']['type'] == "原创" ? 0 : 1 ) : 1) ].checked = true;
        },
        error: function (data) {
            Message("error", "服务器错误, 未能获取图书信息");
        }
    })
}

