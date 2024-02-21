<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>


<body>
	<p>
		home.do에 의해서 호출되는 페이지
	</p>
	<fieldset>
		<legend>session유지여부</legend>
		<ul>
			<li>사용자 정보: ${loginVo }</li>
			<li>페이지 정보:${row}</li>
		</ul>
	</fieldset>
	<button onclick="location.href='./loginForm.do'">로그인 화면 이동</button>
</body>
</html>