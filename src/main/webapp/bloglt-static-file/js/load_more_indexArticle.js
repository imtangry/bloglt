// 顺带解决载入文章的按钮？
//生成short内容，试验,此版本还没有加入图片功能。
//更新：貌似图片也不用考虑,非连续多图情况下
// 考虑新加载的dom没有执行highlight的渲染
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
        }//解决代码块高亮
        $('.short-data pre code').each(function(i, block) {
            hljs.highlightBlock(block);
        });
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
                //这里有问题，get(index)或者[index]获得的是某一个一级dom节点,后端返回的html代码转为jq对象后还包括空节点。
                //投机取巧认为返回文章数量是长度+1除2后的值,但是为了保险起见还是一个个遍历把。
                // var data_length=$(data)[0].getElementsByClassName("short-data").length;
                // console.log(data_length)
                var jqObject=$(data);
                var data_length=0;
                var origin_data_length=jqObject.length;
                for(var i=0;i<origin_data_length;++i){
                    if(jqObject[i].tagName=='DIV'){
                        ++data_length;
                    }
                }
                console.log(data_length);
                if (data_length == 5) {
                    setTimeout(function () {
                        $("#body").append(data);
                        $(".load-more").text("加载更多...");
                        load_short(100,data_length);
                        flag = true;
                    }, 400)
                } else {
                    setTimeout(function () {
                        $("#body").append(data);
                        $(".load-more").text("没有更多喽...-________-\'\'");
                        load_short(100,data_length);
                    }, 400)
                }
            }
        });
    }
})