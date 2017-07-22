// 顺带解决载入文章的按钮？
$(".load-more").click(function () {
    $(".load-more").text("( *・ω・)✄╰ひ╯,呀吼！正在加载哩...");
    var href = $(".article_more").last().attr("href");
    var param = 'articleId='+href.match(/articleId=(\S*)(&)?/)[1];
    $.ajax({
        type:"GET",
        url:"/loadmore",
        data:param,
        success:function(data){
            console.log(data);
            if(data.length==0){
                setTimeout('$(".load-more\").text(\"没有更多喽...-________-\'\'")',400)
            }else{
                setTimeout(function(){
                    $("#body").append(data);
                    $(".load-more").text("加载更多...");
                },400)

            }
        }
    });
})