// 顺带解决载入文章的按钮？
//生成short内容，试验中
function load_short(){
    var fulldiv=$(".full-data");
    var shortdata=$(".short-data")
}
//防止按钮被点击多次，重复加载数据
var flag=true;
//点击加载更多
$(".load-more").click(function () {
if(flag){
    flag=false;
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
                setTimeout(function(){
                    $(".load-more").text("没有更多喽...-________-\'\'");
                    flag=true;
                },400)
            }else{
                setTimeout(function(){
                    $("#body").append(data);
                    $(".load-more").text("加载更多...");
                    flag=true;
                },400)

            }
        }
    });
}

})