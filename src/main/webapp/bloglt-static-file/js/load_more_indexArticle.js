// 顺带解决载入文章的按钮？
//生成short内容，试验,此版本还没有加入图片功能。
$(function(){
    load_short(100,5);
})
function load_short(wordCounts, replaceCounts) {
    var shortdata = document.getElementsByClassName("short-data").length;
    // console.log(shortdata);
    // console.log(replaceCounts);
    var start = shortdata - 1;
    var end = start - replaceCounts;
    for (start; start > end; --start) {
        // console.log(start)
        //此处已经获得full-data块下的所有子节点了。
        //此处逻辑版本：1;
        //依次拿出子元素，判断总内容长度;遇到pre直接return;长度大于200return;
        var full_child = $(".full-data").eq(start).children();
        var full_child_length = full_child.length;
        var char_length = 0;
        //获得对应short-data块
        var short_child = $(".short-data").eq(start);
        for (var i = 0; i < full_child_length; ++i) {
            var child = full_child.eq(i);
            var tagName = child[0].tagName;
            if (tagName == 'P') {
                char_length += full_child.eq(i).text().length;
                // console.log('p标签里的:' + char_length+'\r\n'+child);
                if (char_length > wordCounts) {
                    short_child.append(child);
                    short_child.append('......');
                    break;
                }
                short_child.append(child);
            } else if (tagName == 'PRE') {
                char_length += full_child.eq(i).text().length;
                // console.log('pre标签里的:' + char_length+'\r\n'+child);
                short_child.append(child);
                short_child.append('......');
                break;
            } else {
                char_length += full_child.eq(i).text().length;
                // console.log("else里的:" + char_length+'\r\n'+child);
                if (char_length > wordCounts) {
                    short_child.append(child);
                    short_child.append('......');
                    break;
                }
                short_child.append(child);
            }
        }

    }
}


//防止按钮被点击多次，重复加载数据
var flag = true;
//点击加载更多
$(".load-more").click(function () {
    if (flag) {
        flag = false;
        $(".load-more").text("( *・ω・)✄╰ひ╯,呀吼！正在加载哩...");
        var href = $(".article_more").last().attr("href");
        var param = 'articleId=' + href.match(/articleId=(\S*)(&)?/)[1];
        $.ajax({
            type: "GET",
            url: "/loadmore",
            data: param,
            success: function (data) {
                var data_length=$(data)[0].getElementsByClassName("short-data").length;
                // console.log(data_length)
                if (data_length < 5) {
                    setTimeout(function () {
                        $("#body").append(data);
                        $(".load-more").text("没有更多喽...-________-\'\'");
                        load_short(100,data_length)
                    }, 400)
                } else {
                    setTimeout(function () {
                        $("#body").append(data);
                        $(".load-more").text("加载更多...");
                        load_short(100,data_length)
                        flag = true;
                    }, 400)

                }
            }
        });
    }
})