<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>短链接</title>
</head>
<body style="position:relative;">
<div style="position:absolute;border:1px solid #FFFF00;top:30%;left:30%;">
    <input type="url" placeholder="请输入长地址" id="longLink" style="width:30em;">
    <button>生成短链接</button>
    <span style="visibility: hidden;color: #FF0000" id="tips"></span>
    <span style="visibility: hidden;" id="s" >短链接：</span>
    <a id="shortLink"></a>
</div>
</body>
<script>
    function createXhr() {
        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        }
        return new ActiveXObject("Microsoft.XMLHTTP");

    }

    function ajax() {
        var xhr = createXhr();
        var url = arguments[0].url || "";
        var data = arguments[0].data || {};
        var async = arguments[0].async || true;
        var type = arguments[0].type.toUpperCase() || "GET";
        var success = arguments[0].success;
        var error = arguments[0].error;
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    success(JSON.parse(xhr.responseText));
                } else {
                    error();
                }
            }
        }
        var params = [];
        for (var key in data) {
            params.push(key + "=" + data[key] + "&");
        }
        var str = params.join("");
        str = str.substring(0, str.length - 1);
        if (type === "POST") {

            xhr.open("POST", url, async);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(str);
        } else {

            xhr.open("GET", url + "?" + str, async);
            xhr.send();
        }

    }

    function show(id) {
        document.getElementById(id).style.visibility = "visible";
    }

    function hide(id) {
        document.getElementById(id).style.visibility = "hidden";
    }

    document.getElementsByTagName("button")[0].addEventListener("click", function () {
        var longLink = document.getElementById("longLink").value;
        var tips = document.getElementById("tips");
        if (longLink.trim() === "") {
            tips.innerText = "长链接不能为空";
            show("tips");
            return;
        }
        if (!(longLink.startsWith("http") || longLink.startsWith("https"))) {
            tips.innerText = "url格式不正确";
            show("tips");
            return;
        }
        ajax({
            type: "post",
            data: {"url": longLink},
            url: "/shortLink/create",
            success: function (res) {
                show("s");
                document.getElementById("shortLink").innerText=res.data;
                document.getElementById("shortLink").href=res.data;
            },
            error: function () {
                console.info("error");
            }
        })


    })
</script>
</html>
