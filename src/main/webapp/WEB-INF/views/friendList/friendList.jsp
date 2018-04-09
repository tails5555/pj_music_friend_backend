<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="R" value="/" />

<!DOCTYPE html>
<html>
<head>
    <title>음악 친구 관리 </title>
    
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${R}res/friendList.css"/>
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
                  <div class="menu" style="">

                        <div class="col-md-2" style="padding-left: 200px; padding-top:30px;  ">
                           <a href="${R}music/main"><img src="${R}img/title.PNG"/></a>
                       </div>
                        
                        <center>

                          <ul class="col-md-6" style="padding-top: 50px;">

                                 <li><a href="${R}music/main">Home</a></li>
                                <li><a href="${R}music/list">My ❤ </a></li>
                                <li><a href="${R}music/friendList">친구 관리</a></li>
                                <li><a href="${R}music/login">Login</a></li>

                            </ul>

                        </center>
                    </div>
                    <!-- 1번 프레임 끝  -->


                    <!-- 2번 프레임 시작  -->
                    <div class="header">
                        <center>

                            <div class="title" ><Strong>친구 관리</Strong> </div>

                        </center>
                    </div>
                    <!-- 2번 프레임 끝  -->


                    <!-- 3번 프레임 시작  -->

                    <div class="container" >

                        <center>


                            <!--4번 프레임 시작-->
                            <div class="content">
                            
                                <h3 style="text-align: left; ">내 프로필</h3>
                                    <hr/>
                                    <table style="right: 90px;">
                                            <thead>
                                                <tr>
                                                    <td>이름</td>
                                                    <td>상태 메세지</td>
                                                    <td>자주 듣는 노래 </td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <td>장승훈</td>
                                                <td>잘 부탁 드립니다.</td>
                                                <td>고등래퍼 바코드 , 봄이 좋냐 </td>

                                            </tbody>
                                        </table>

                                        <br/>
                                        <br/>

                             <h3 style="text-align: left;">친구 목록</h3>
                                    <hr/>
                                    <table>
                                            <thead>
                                                <tr>
                                                    <td>이름</td>
                                                    <td>상태 메세지</td>
                                                    <td>자주 듣는 노래 </td>
                                                    <td>즐겨 찾기</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <td>강인성</td>
                                                <td>강DB</td>
                                                <td>도끼, 박재범  </td>
                                                        <td><input class="toggle-heart" id="userId1" type="checkbox" checked="checked" />
                                                <label for="userId1" class="toggle-heart-label">❤</label></td>


                                            </tbody>
                                        </table>


                                    <br/>
                                    <br/>

                                <h3 style="text-align: left;">추천 친구</h3>
                                    <hr/>
                                    <table>
                                            <thead>
                                                <tr>
                                                    <td>이름</td>
                                                    <td>상태 메세지</td>
                                                    <td>자주 듣는 노래 </td>
                                                    <td>즐겨 찾기</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <td>서종현</td>
                                                <td>너 이름이 뭐야 </td>
                                                <td>고등래퍼 바코드</td>
                                                    <td><input class="toggle-heart" id="userId2" type="checkbox" />
                                                <label for="userId2" class="toggle-heart-label">❤</label></td>


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
