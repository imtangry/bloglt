//初始化，ajax根据初始条件返回数据，初始化
var init = function (a) {
    var b = {
        currentPage: 1,
        pageSize: 10
    }
    var c ;
    ajax(b.currentPage, b.pageSize,this);
    // var totalData=c.totalData,totalPages=c.totalPages;
    // console.log(totalData);
    // console.log(totalPages);
    console.log(c);
}

//ajax
function ajax(num, size,c) {
    $.ajax({
        type: 'GET',
        url: '/articlelist',
        cache: 'false',
        data: {
            currentPage: num,
            pageSize: size
        },
        dataType: 'json',
        success: function (data) {
            c.c=data;
        }
    })
}

//回调