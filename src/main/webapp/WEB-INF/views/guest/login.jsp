<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url var="R" value="/" />

<!DOCTYPE html>
<html>
<head>
    <title>음악 친구 찾기 </title>
    
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${R}res/login.css"/>
    <link type="text/css" rel="stylesheet" href="${R}res/heart.css"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	
	
	
</head>
    <style>

    </style>

<body>

<center>
                <div class="frame">


                    <!-- 1번 프레임 시작  -->
                    <div class="menu">

                        <center>
                            <ul class="col-md-6" style="padding-top: 50px;">

                                 <li><a href="${R}guest/index">Home</a></li>
                                <li><a href="${R}music/list">My ❤ </a></li>
                                <li><a href="${R}music/friendList">친구 관리</a></li>
                                <li><a href="${R}guest/login">Login</a></li>

                            </ul>
                        </center>
                    </div>

                    <!-- 1번 프레임 끝  -->


                    <!-- 2번 프레임 시작  -->
                    <div class="header">
                        <center>

                            <a href="${R}guest/index"> <div class="title" > Music Friend </div> </a>

                        </center>
                    </div>
                    <!-- 2번 프레임 끝  -->


                    <!-- 3번 프레임 시작  -->

                    <div class="container" >

                        <center>



					                                <h2 style="padding-right: 270px; padding-bottom: 10px;     color: #0c0c0c;
					    font-family: roboto,arial,sans-serif;">로그인</h2>
					                        <center>
					
					                            <!--4번 프레임 시작-->
					
					                                    <div align="center" margin-bottom:10px;>
					                            <input type="text" name="userid" placeholder="아이디 입력" style="width:350px; padding:5px; margin-bottom:5px" />
					                        </div>
					                        <div align="center">
					                            <input type="password" name="password" placeholder="비밀번호 입력" style="width:350px; padding:5px;margin-bottom:5px" />
					                        </div>
					                        <div align="center">
					                      <a href="mypage.html">
					                            <button type="button" id="loginButton" class="btn btn-primary" style="width: 350px; padding:5px" >로그인</button> </a>
					                        </div>
					
					
					                            <hr style="border: solid 1px" align="center" width="400" />
					    <div align="center" style="">
					        <a href="search_password.html">비밀번호 찾기</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a href="${R}guest/sign">회원가입</a>
					        <br>
					        <br>
					    </div>

                            </div>
                            <!--4번 프레임 끝-->


                    </center>

                    </div>

                <!-- 3번 프레임 끝  -->

            </div>
        </center>


</body>
</html>
