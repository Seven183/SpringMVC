<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css"
	href="../../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../../easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../easyui/demo/demo.css">
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	// 定义事件方法
	function onSearch() {
		// 指定请求路径
		var opts = $("#dg").datagrid("options");
		opts.url = "./list";
		// 获取查询参数
		var age = $("#age").val();
		var name = $("#name").val();
		// 组织参数
		var params = {};
		if (age != null && age.trim() != '') {
			params.age = age;
		}
		if (name != null && name.trim() != '') {
			params.name = name;
		}
		// 重新载入表格数据
		$("#dg").datagrid('load', params);
	}
</script>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-layout" style="width: 100%; height: 350px;">
		<div data-options="region:'north'" style="height: 50px">
			<form id="searchForm" method="post">
				<table>
					<tr>
						<td>年龄：</td>
						<td><input id="age" name="age"
							class="easyui-textbox" data-options="prompt:'输入用户名称...'"
							style="width: 100%; height: 32px"></td>
						<td>名称</td>
						<td><input id="name" name="name" class="easyui-textbox"
							data-options="prompt:'输入备注...'" style="width: 100%; height: 32px">
						</td>
						<td><a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" style="width: 80px"
							onclick="onSearch()">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',title:'用户列表',iconCls:'icon-ok'">
			<table id="dg" class="easyui-datagrid", data-options="border:false,singleSelect:true,fit:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'id'" width="80">编号</th>
						<th data-options="field:'age'" width="100">年龄</th>
						<th data-options="field:'name'" width="80">名称</th>
					</tr>
				</thead>
				<tbody>
					<!--使用forEache渲染数据模型-->
					<c:forEach items="${empList}" var="emp">
						<tr>
							<td>${emp.id}</td>
							<td>${emp.age}</td>
							<td>${emp.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>