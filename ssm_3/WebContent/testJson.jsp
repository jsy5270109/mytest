<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试json交互</title>
<script type="text/javascript"	src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	function requestKV() {
		$.ajax({
			type : "post",
			url : '${pageContext.request.contextPath }/requestKV.do',
			//输入是key/value时，默认就指定好了contentType了，不需要再指定了
			//contentType:'application/json;charset=utf-8',
			//data为key/value形式
			data : 'name=json测试&price=999',
			success : function(data) {
				alert(data.name);
			}
		});
	}

	function requestJSON() {
		$.ajax({
			type : "post",
			url : '${pageContext.request.contextPath }/requestJSON.do',
			//输入是json是 ，需要指定contentType为application/json
			contentType : 'application/json;charset=utf-8',
			data : '{"name":"json测试","price":999}',
			success : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<input type="button" value="请求KV" onclick="requestKV();" />
	<input type="button" value="请求JSON" onclick="requestJSON();" />
</body>
</html>