var base_url = '/blog';

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});

function save_write_blog(title,content,items,layer) {
    $.post(base_url,
        {
            title:title,
            content:content,
            items:items
        },
        function (data) {
            data = JSON.parse(data);
            if(data.code == 200) {
                console.log("上传成功" + data.code);
                layer.msg("上传成功");
            }
            else {
                console.log("上传失败" + data.code);
                layer.msg("上传失败");
            }
        })
}

function delete_blog(id,layer,obj){
    $.ajax({
        url: base_url+"/"+id,
        type: 'DELETE',
        success: function(data) {
            data = JSON.parse(data);
            console.log(data);
            if(data.code == 200){
                obj.del()
                layer.msg("删除成功");
            }else layer.msg("删除失败");
        }
    });
}