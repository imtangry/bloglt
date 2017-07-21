function load(text){
    // console.log(text);
    console.log("执行了");
    CKEDITOR.instances.ckeditor.insertHtml(text);
}
console.log("这是外面");
// CKEDITOR.document.getById( 'ckeditor' ).setHtml(text2);
$(function(){
    console.log("这是函数里面");
    setTimeout('load(srctext)',500);
})