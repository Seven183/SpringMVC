<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增用户用户</title>
<!-- 加载Query文件-->
<script src="https://code.jquery.com/jquery-3.2.0.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
    $("#submit").click(function() {
        var age = $("#age").val();
        var name = $("#name").val();
        if ($.trim(age)=='') {
            alert("用户名不能为空！");
            return;
        }
        var params = {
            age : age,
            name : name
        };
        $.post({
            url : "/insertEmp",
            // 此处需要告知传递参数类型为JSON，不能缺少
            contentType : "application/json",
            // 将JSON转化为字符串传递
            data : JSON.stringify(params),
            // 成功后的方法
            success : function(result) {
                // if (result == null || result.id == null) {
                //     alert("插入失败");
                //     return;
                // }
                alert("插入成功");
            }
        });
    });
});
</script>
</head>
<body>
    <div style="margin: 20px 0;"></div>
    <form id="insertForm">
        <table>
            <tr>
                <td>年龄：</td>
                <td><input id="age" name="age"></td>
            </tr>
            <tr>
                <td>名称</td>
                <td><input id="name" name="name"></td>
            </tr>
            <tr>
                <td></td>
                <td align="right"><input id="submit" type="button" value="提交" /></td>
            </tr>
        </table>
    </form>
</body>