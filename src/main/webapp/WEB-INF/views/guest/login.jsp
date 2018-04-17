<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
    <title>음악 친구 Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${R}res/main.css"/>
    <link type="text/css" rel="stylesheet" href="${R}res/heart.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="frame">
<!-- 1번 프레임 시작  -->
	<div class="menu" style="textalr">            
    	<div class="row">
            <div class="col-md-2" style="padding-left: 50px; padding-top:30px;  ">
            	<a href="${R}guest/index"><img src="/pj_music_friend_backend/resources/img/title.PNG" /></a>
            </div>
            <div class="col-md-6">
                <ul class="text-center" style="padding-top: 50px;">
                      <li><a href="${R}guest/index">Home</a></li>
                      <li><a href="${R}music/list">My ❤ </a></li>
                      <li><a href="${R}music/friendList">친구 관리</a></li>
                      <li><a href="${R}guest/login">Login</a></li>
                </ul>
			</div>
			<div class="col-md-4"></div>
         </div>
     </div>
     <!-- 1번 프레임 끝  -->


     <!-- 2번 프레임 시작  -->
     <div class="header" style="padding-top: 10px;">
     	<div class="title text-center"> <strong>로그인</strong></div>
     	<hr/>
     </div>
     <!-- 2번 프레임 끝  -->
     <!-- 3번 프레임 시작  -->
     <div class="container text-center" >
	 	 <form method="post" action="login_processing">
		 	<h3 style="padding-bottom: 10px; color: #0c0c0c; font-family: roboto,arial,sans-serif;">Login</h3>
			<!--4번 프레임 시작-->
			<div class="text-center" style="margin-bottom:10px;">
				<input type="text" name="loginId" placeholder="아이디 입력" style="width:350px; padding:5px; margin-bottom:5px" />
			</div>
			<div class="text-center">
				<input type="password" name="passwd" placeholder="비밀번호 입력" style="width:350px; padding:5px; margin-bottom:5px" />
			</div>
			<div class="text-center">
				<button type="submit" id="loginButton" class="btn btn-success" style="width: 350px; padding:5px" >로그인</button>
			</div>
			<hr style="border: 1px solid;"/>
			<div class="text-center">
				<a href="${R}guest/create"><button type="button" class="btn btn-info" style="width: 350px; padding:5px">회원가입</button></a>
			</div>
		</form>
	</div>
	<!-- 3번 프레임 끝  -->
</div>
</body>
</html>
