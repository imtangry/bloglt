//阻止事件冒泡
var EventUtil = {
    stopbub: function(e) {
        e = e || window.event;
        e.stopPropagation = e.stopPropagation() ? e.stopPropagation() : e.cancelBubble = true;
    }
}
// 用于后面显示分类条件块中的文字信息以及作为查询条件
var search_title, search_tags;
//防止ajax多次加载，分页插件的多次初始化
var isFirstLoad = true;
//头部查询的onclicl事件对应函数，实现标题查询文章
var reload = function () {
    console.log($.trim($('.search_input').val()))
    if ($.trim($('.search_input').val()) == '') {
        alert("请输入查询标题");
    } else {
        search_title = $.trim($('.search_input').val());
        isFirstLoad = true;
        ajax(1, 10);
    }
}
var reloadByTag = function (a) {
    if (a == 1) {
        console.log(a)
        search_title = '';
        search_tags = undefined;
        isFirstLoad = true;
        ajax(1, 10);
    } else if (a == 2) {
    }
    else if (a == 3) {
    }
}
//ajax获取数据生成分页信息,生成分页页码
var ajax = function (a, b) {
    $.ajax(
        {
            type: 'GET',
            url: '/articlelist',
            data: {
                currentPage: a,
                pageSize: b,
                startRow: b * (a - 1),
                title: (search_title == undefined || search_title == '') ? undefined : '%' + search_title + '%',
                tags: search_tags
            },
            dataType: 'json',
            success: function (data) {
                if (data.totalData == 0) {
                    $('.page_content').html('');
                    $('.page_content').append(
                        '<div class="search_condition">没有找到你想要的东西哦...</div>')
                    return;
                }
                console.log("success")
                if (isFirstLoad) {
                    console.log('OKKKK第一次');
                    $('.pages').jqPaginator({
                        totalPages: data.totalPages,
                        visiblePages: b,
                        currentPage: a,
                        first: '<li class="first"><a href="javascript:void(0);">首页</a></li>',
                        last: '<li class="last"><a href="javascript:void(0);">末页</a></li>',
                        page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
                        onPageChange: function (num) {
                            if (!isFirstLoad) {
                                console.log(num)
                                ajax(num, b);
                                return;
                            }
                            if (search_title != undefined && search_title != '') {
                                makeContent(data, search_title);
                            } else if (search_tags != undefined && search_tags != '') {
                                makeContent(data, search_tags);
                            } else {
                                makeContent(data);
                            }
                            isFirstLoad = false;
                        }
                    });
                    return;
                } else {
                    console.log('已经加载过了');
                    if (search_title != undefined && search_title != '') {
                        makeContent(data, search_title);
                    } else if (search_tags != undefined && search_tags != '') {
                        makeContent(data, search_tags);
                    } else {
                        makeContent(data)
                    }
                }
            }
        }
    )
}

//这里文章列表渲染应该加个过度动画,人性化
function makeContent(a, b) {
    $('.page_content').html('');
    $('.page_content').append(
        '<div class="search_condition">正在卖命查找，请稍等...</div>')
    setTimeout(function () {
        $('.page_content').html('');
        if (b == null) {
            $('.page_content').append(
                '<div class="search_condition">全部文章</div>'
            )
        } else {
            $('.page_content').append(
                '<div class="search_condition">' + b + '</div>')
        }
        var o = a.dataList;
        var length = o.length;
        for (var i = 0; i < length; ++i) {
            var tmp = o[i];
            var date = getMyDate(tmp.articleDate);
            $('.page_content').append(
                '<div class="pages_article">' +
                '<h2 class="pages_article_title"><a href="/readarticle?articleId=' + tmp.articleId +
                '" target="_blank">' + tmp.articleTitle + '</a></h2>' +
                '<div class="pages_article_content short-data">' + tmp.contentShort + '</div>' +
                '<div class="pages_article_date"><span class="fa fa-calendar-o"></span>' + date +
                '</div>' +
                //                    '<a class="article_more" href="/readarticle?articleId=' + tmp.articleId + '" target="_blank">阅读全文>></a>' +
                //                    '<div style="clear:both"></div>' +
                '</div>'
            )
        }
    }, 400);
}

// 格式化后台传来的日期
function getMyDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
//                oHour = oDate.getHours(),
//                oMin = oDate.getMinutes(),
//                oSen = oDate.getSeconds(),
        oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);
//                        + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);最后拼接时间
    return oTime;
};

// 加零操作
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}

//非文章详情则进行分页初始化
$(function () {
    if ($('.article').length > 0) {
        return;
    } else {
        ajax(1, 10);
    }
});

// 头部分类按钮,实现点击外部关闭选项
document.getElementsByClassName("search_tags")[0].onclick=function(e){
    EventUtil.stopbub(e);
    if ($('.tags').css('display') == 'none') {
        $('.tags').css('display', 'block');
        $(".search_tags").css('background-color', '#021c53')
    } else {
        $('.tags').css('display', 'none')
        $(".search_tags").css('background-color', '#1d92c9')
    }
}
// function tagList(e) {
//     EventUtil.stopbub(e);
//     if ($('.tags').css('display') == 'none') {
//         $('.tags').css('display', 'block');
//         $(".search_tags").css('background-color', '#021c53')
//     } else {
//         $('.tags').css('display', 'none')
//         $(".search_tags").css('background-color', '#1d92c9')
//     }
// }

// 实现点击任意处关闭li
document.onclick = function () {
    $('.tags').css('display', 'none')
    $(".search_tags").css('background-color', '#1d92c9')
}