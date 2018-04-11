<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:url var="R" value="/" />

<!DOCTYPE html>
<html>
<head>
    <title>음악 친구 찾기 </title>
    
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${R}res/main.css"/>
    <link type="text/css" rel="stylesheet" href="${R}res/heart.css"/>

	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	
</head>
    <style>

    </style>

<body>

<center>
                <div class="frame">


                    <!-- 1번 프레임 시작  -->
                    <div class="menu" style="textalr">
                    
                      <div class="row">
                            <div class="col-md-2"></div>

                        <div class="col-md-2" style="padding-left: 200px; padding-top:30px;  ">
                          <a href="${R}guest/index"><img src="/pj_music_friend_backend/resources/img/title.PNG" /></a>
                       </div>
                        
                        <center>

                          <ul class="col-md-6" style="padding-top: 50px;">

                                    <li><a href="${R}guest/index">Home</a></li>
                                <li><a href="${R}music/list">My ❤ </a></li>
                                <li><a href="${R}music/friendList">친구 관리</a></li>
                                <li><a href="${R}guest/login">Login</a></li>


                            </ul>

                        </center>
                        </div>
                        <div class="col-md-2">
						                          <table class="table table-bordered" style="width:500px;">
						    <tr>
						        <td>로그인ID</td>
						        <td><sec:authentication property="user.loginId" /></td>
						    </tr>
						    <tr>
						        <td>이름</td>
						        <td><sec:authentication property="user.name" /></td>
						    </tr>
						    <tr>
						        <td>이메일</td>
						        <td><sec:authentication property="user.email" /></td>
						    </tr>
						  </table>
						  
						  <a class="btn btn-default" href="logout_processing">로그아웃</a>
						</div>
						                        
                    </div>

                    <!-- 1번 프레임 끝  -->


                    <!-- 2번 프레임 시작  -->
                    <div class="header" style=" padding-top: 10px;">
                        <center>

                            <div class="title" style="" > <strong> 실시간 차트</strong></div>

                        </center>
                    </div>
                    <!-- 2번 프레임 끝  -->


                    <!-- 3번 프레임 시작  -->

                    <div class="container" style="height: 600px" >

                        <center>



                            <!--4번 프레임 시작-->
                            <div class="content">
                                   <table>
                                            <thead>
                                                <tr>
                                                    <td>순위</td>
                                                    <td>제목 </td>
                                                    <td>가수 </td>
                                                    <td>앨범</td>
                                                    <td>좋아요</td>
                                                </tr>
                                            </thead>
                                             <tbody>
										        <c:forEach var="music" items="${ list }">
										          <tr data-url="edit?id=${music.id}">
										            <td>${ music.id }</td>
										            <td>${ music.title}</td>
										            <td>${ music.singer }</td>
										            <td>${ music.album }</td>
										            
										            <td><input class="toggle-heart" id=${music.id } type="checkbox"  />
                                                <label for=${music.id } class="toggle-heart-label">❤</label></td>
										          </tr>
										        </c:forEach>
										      </tbody>
										    </table>

                            </div>
                            <!--4번 프레임 끝-->


                    </center>

                    </div>

                <!-- 3번 프레임 끝  -->

            </div>
        </center>


</body>
</html>
