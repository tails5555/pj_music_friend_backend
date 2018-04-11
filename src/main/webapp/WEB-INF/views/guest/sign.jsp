<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

                        <div class="container" style="width: 600px;" >
                    	<h2 style="padding-right: 270px; padding-bottom: 10px;     color: #0c0c0c; font-family: roboto,arial,sans-serif; text-align: left;"><strong>회원 가입</strong></h2>

                		<hr style="border: solid 1px black;" />
                        <center>

                            <!--4번 프레임 시작-->


            
					                <form:form method="post" modelAttribute="userForm">
					                      <div class="application" style="text-align: left;" >
					
					
					                          <form name="sentMessage" id="contactForm" novalidate>
					                            <div class="control-group">
					                              <div class="form-group floating-label-form-group controls">
					                                <label>이름: </label>
					                                <form:input path="name" class="form-control" placeholder="이름 입력" />
					                                <p class="help-block text-danger"></p>
					                              </div>
					                            </div>
					
					                            <br/>
					
					
					                            <div class="control-group">
					                              <div class="form-group floating-label-form-group controls">
					                                <label>아이디: </label>
					                                <form:input path="userId" class="form-control" placeholder="아이디 입력" />
					                                <p class="help-block text-danger"></p>
					                              </div>
					                            </div>
					
					                             <br/>
					
					
					
					                            <div class="control-group">
					                              <div class="form-group floating-label-form-group controls">
					                                <label>비밀번호:  </label>
					                                <form:input path="password1" class="form-control" placeholder="비밀번호 입력" />
					                                <p class="help-block text-danger"></p>
					                              </div>
					                            </div>
					
					
					                             <br/>
					
					                            <div class="control-group">
					                              <div class="form-group floating-label-form-group controls">
					                                <label>비밀번호 확인:  </label>
					                                <form:input path="password2" class="form-control" placeholder="비밀번호 확인" />
					                                <p class="help-block text-danger"></p>
					                              </div>
					                            </div>
					
					
					                             <br/>
					
					                       <hr>
					
					
					
					
					
					      <div style="margin-top: 40px">
					        <p><h3>회원가입을 신청하시려면 밑에 신청 버튼을 눌러 주세요.</h3></p>
					
					      <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"> 신청</i></button>
					      <a href="${R}guest/index"><button type="button" class="btn btn-primary"><i class="glyphicon glyphicon-remove"> 취소</i></button></a>
					      </div>
					
				
					



                    </div>
				</form:form>
                <!-- 3번 프레임 끝  -->

            </div>
        </center>


</body>
</html>
