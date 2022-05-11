function getUrlPara(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return (r[2]); return null;
}

function initInfo(){
    var id = parseInt(getUrlPara('id'));
    $.ajax({
        url: 'http://localhost:5000/getInfoById',
        type: 'POST',
        data: {
            'id': id
        },
        cookie: true,
        success: function (data){
            var j = JSON.parse(data);
            $('#id')[0].value = id;
            $('#name')[0].value = j['result'].hasOwnProperty('name') ? j['result']['name'] : '';
            $('#author')[0].value = j['result'].hasOwnProperty('author') ? j['result']['author'] : '';
            $('#price')[0].value = j['result'].hasOwnProperty('price') ? j['result']['price'] : '';
            $('#left')[0].value = j['result'].hasOwnProperty('left') ? j['result']['left'] : '';
            $('#description')[0].value = j['result'].hasOwnProperty('desc') ? j['result']['desc'] : '';
            $('input[name="bookType"]')[(j['result'].hasOwnProperty('type') ? j['result']['type'] : 1) - 1].checked = true;
        }
    })
}

