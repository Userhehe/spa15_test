<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/login.js"></script>
	<style type="text/css" >
		.container{
			width: 500px;
			margin:200px auto;
		}
		
		.form-horizontal{
			border: 1px solid #ccc;
			border-radius:20px;
			padding: 10px;
		}
	</style>
</head>

<body>
<div class="container">
   <form class="form-horizontal">
      <div class="form-group">
         <label class="control-label col-sm-2" for="id">ID:</label>
         <div class="col-sm-10">
            <input type="text" class="form-control" id="id" name="id"
               placeholder="Enter ID">
         </div>
      </div>
      <div class="form-group">
         <label class="control-label col-sm-2" for="pwd">Password:</label>
         <div class="col-sm-10">
            <input type="password" class="form-control" id="pwd" name="password"
               placeholder="Enter password">
         </div>
      </div>
            <div class="form-group">
         <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">로그인</button>
         </div>
      </div>
      <a href="./signup.do">회원가입</a>
   </form>
   
   <script type="text/javascript" src="./js/ObjectJS.js"></script>
   </div>
</body>
</html>