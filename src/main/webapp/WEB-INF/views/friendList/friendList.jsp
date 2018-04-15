<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />

<!DOCTYPE html>
<html>
<head>
    <title>나의 친구 정보</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${R}res/main.css"/>
    <link type="text/css" rel="stylesheet" href="${R}res/heart.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${R}js/userMain.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="frame">
	<!-- 1번 프레임 시작  -->
	<div class="menu" style="textalr">
    	<div class="row">
			<div class="col-md-2" style="padding-left: 200px; padding-top:30px;  ">
            	<a href="${R}guest/index"><img src="/pj_music_friend_backend/resources/img/title.PNG" /></a>
            </div>
            <div class="col-md-8">
                <ul class="text-center" style="padding-top: 50px;">
                    <li><a href="${R}user/index">Home</a></li>
                    <li><a href="${R}music/list">My ❤ </a></li>
                    <li><a href="${R}music/friendList">친구 관리</a></li>
                    <li><a href="${R}user/logout_processing">Logout</a></li>
                    <li><sec:authentication property="user.name" />님<br/>환영합니다.</li>
                </ul>
            </div>
            <div class="col-md-2">
            </div>
        </div>
    </div>
    <!-- 1번 프레임 끝  -->
	<!-- 2번 프레임 시작  -->
    <div class="header" style="padding-top: 10px;">
    	<div class="title text-center"><strong>나의 친구 정보</strong></div>
    	<hr/>
    </div>
	<!-- 2번 프레임 끝  -->
    <!-- 3번 프레임 시작  -->
    <div class="header" style="padding-top: 10px;">
    	<div class="title text-center"><strong>나를 따르는 친구 목록</strong></div>
    	<hr/>
    </div>
	<div class="container text-center">
	<!--4번 프레임 시작-->
	    <div class="content">
		    <table class="table table-responsive">
		    	<thead>
	            <tr>
	                <td>이름 </td>
	                <td>추가 날짜 </td>
	                <td>음악 목록</td>
	                <td>Heart</td>
	            </tr>
	            </thead>
	            <tbody>
		    	<c:forEach var="folrFriend" items="${ followers }">
	          		<tr>
			            <td>${ folrFriend.name }</td>
			            <td>${ folrFriend.followerDate }</td>
			            <td>
			            <c:forEach var="music" items="${ folrFriend.playList }">
			            	<p>${ music.title } - ${ music.singer }</p>
			            </c:forEach>
			            </td>
			            <td>
				            <input class="toggle-heart" id=${ folrFriend.id } type="checkbox" checked />
					        <label for=${ folrFriend.id } class="toggle-heart-label">❤</label>
				        </td>
			        </tr>
		    	</c:forEach>
		    	</tbody>
	    	</table>
	    </div>
    </div>
    <div class="header" style="padding-top: 10px;">
    	<div class="title text-center"><strong>내가 따르는 친구 목록</strong></div>
    	<hr/>
    </div>
    <div class="container text-center">
	    <div class="content">
		    <table class="table table-responsive">
		    	<thead>
	            <tr>
	                <td>이름 </td>
	                <td>추가 날짜 </td>
	                <td>음악 목록</td>
	                <td>Heart</td>
	            </tr>
	            </thead>
	            <tbody>
		    	<c:forEach var="folingFriend" items="${ followings }">
	          		<tr data-url="unfollow?subUserId=${ folingFriend.id }">
			            <td>${ folingFriend.name }</td>
			            <td>${ folingFriend.followingDate }</td>
			            <td>
			            <c:forEach var="music" items="${ folingFriend.playList }">
			            	<p>${ music.title } - ${ music.singer }</p>
			            </c:forEach>
			            </td>
			            <td>
				            <input class="toggle-heart" id=${ folingFriend.id } type="checkbox" checked />
					        <label for=${ folingFriend.id } class="toggle-heart-label">❤</label>
				        </td>
			        </tr>
		    	</c:forEach>
		    	</tbody>
	    	</table>
	    </div>
    </div>
    <div class="header" style="padding-top: 10px;">
    	<div class="title text-center"><strong>친구를 추천합니다.</strong></div>
    	<hr/>
    </div>
    <div class="container text-center">
	    <div class="content">
	    	<table class="table table-responsive">
		    	<thead>
	            <tr>
	                <td>이름 </td>
	                <td>Heart</td>
	            </tr>
	            </thead>
	            <tbody>
		    	<c:forEach var="recFriend" items="${ recommends }">
	          		<tr data-url="follow?subUserId=${ recFriend.id }" class="${ recFriend.followed ? 'info' : '' }">
			            <td>${ recFriend.name }</td>
			            <td>
				            <input class="toggle-heart" id=${ recFriend.id } type="checkbox" />
					        <label for=${ recFriend.id } class="toggle-heart-label">❤</label>
				        </td>
			        </tr>
		    	</c:forEach>
		    	</tbody>
	    	</table>
	    </div>
	</div>
    <!--4번 프레임 끝-->
    <hr/>
</div>
<!-- 3번 프레임 끝  -->
</body>
</html>