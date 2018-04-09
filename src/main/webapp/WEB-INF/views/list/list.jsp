<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="R" value="/" />

<!DOCTYPE html>
<html>
<head>
    <title>나의 플레이 리스트 </title>
    
    
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

                            <div class="title" > <Strong>My ❤ </Strong> </div>

                        </center>
                    </div>
                    <!-- 2번 프레임 끝  -->


                    <!-- 3번 프레임 시작  -->

                    <div class="container" >

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
                                                <tr>
                                                    <td>1</td>
                                                    <td>바코드</td>
                                                    <td>김하온 , 이병재</td>
                                                    <td>고등래퍼2 팀대항전 Part 3</td>
                                                    <td><input class="toggle-heart" id="1" type="checkbox" checked="checked" />
                                                <label for="1" class="toggle-heart-label">❤</label></td>


                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>소나기</td>
                                                    <td>용준형</td>
                                                    <td>소나기 (Feat. 10cm)</td>

                                                <td><input class="toggle-heart" id="2" type="checkbox" checked="checked"/>
                                                <label for="2" class="toggle-heart-label">❤</label></td>
                                                </tr>
                                                <tr>
                                                    <td>3</td>
                                                    <td>꽃길</td>
                                                    <td>BIGBANG</td>
                                                    <td>꽃길</td>
                                                        <td><input class="toggle-heart" id="3" type="checkbox" checked="checked"/>
                                                <label for="3" class="toggle-heart-label" >❤</label></td>
                                                </tr>
                                            
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
