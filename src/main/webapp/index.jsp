<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>文件上传下载</title>
    <script src="jquery/jquery-3.3.1.js"></script>
</head>
<body>
<style>
    #progressbar {
        width: 200px;
        border: 1px solid darkgray;
        height: 15px;
        border-radius: 1rem;
        margin-top: -10px;
        display: none;
    }
    #fill {
        height: 15px;
        text-align: center;
        line-height: 15px;
        border-radius: 1rem;
        background-color: mediumturquoise;
    }
</style>


<div id="progressbar">
    <div id="fill"></div>
</div>

<form action="#" id="form">
    <input type="file" name="file"/>
    <input type="button" value="上传" onclick="upload()"/>
</form>
</body>
</html>
<script type="text/javascript">
    var interval;
    function upload() {
        var formData = new FormData($("form")[0]);
        interval = setInterval(getProgress, 100);//开启定时器（间歇调用）
        $.ajax({
            url: "/upload",
            type: "POST",
            data: formData,
            cache: false,
            processData: false,//此处需要设置为false
            contentType: false,//此处需要设置为false
            success: function (data) {
                console.log(data);
            }
        });
    }
    //轮询获取文件上传进度的方法
    function getProgress() {
        $("#progressbar").show()
        $.ajax({
            url: "getInfo",
            type: "get",
            success: function (progressdata) {
                if (progressdata == "stop") {
                    clearInterval(interval);
                }
                $("#fill").css("width", progressdata+"%");
                $("#fill").text(progressdata+"%");
                console.log(progressdata);
            }
        });
    }
</script>
</html>
