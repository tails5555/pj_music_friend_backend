<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
    <title>나의 플레이 리스트 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${R}res/main.css"/>
    <link type="text/css" rel="stylesheet" href="${R}res/heart.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="http://visjs.org/dist/vis-network.min.css" rel="stylesheet" type="text/css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${R}js/userMain.js"></script>
    <script type="text/javascript" src="http://visjs.org/dist/vis.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
    	#mynetwork {
	      width: 100%;
	      height: 100%;
	      border: 1px solid lightgray;
	    }
    </style>
</head>
<body onload="draw()">
<div class="frame">
<!-- 1번 프레임 시작  -->
	<div class="menu" style="textalr">
    	<div class="row">
			<div class="col-md-2" style="padding-left: 200px; padding-top:30px;  ">
            	<a href="${R}user/index"><img src="/pj_music_friend_backend/resources/img/title.PNG" /></a>
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
    <div class="header">
    	<div class="title text-center" > <Strong>My ❤ </Strong> </div>
    	<hr/>
    </div>
    <!-- 2번 프레임 끝  -->
    <!-- 3번 프레임 시작  -->
    <div class="container text-center">
    <!--4번 프레임 시작-->
    	<div class="content">
            <table class="table table-responsive">
            	<thead>
                <tr>
                    <td>상대순위</td>
                    <td>변화</td>
                    <td>제목 </td>
                    <td>가수 </td>
                    <td>앨범</td>
                    <td>좋아요</td>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="music" items="${ list }" varStatus="status">
		          		<tr data-url="like?mId=${ music.id }&work=${ music.liked ? 'delete' : 'insert'}">
				            <td>${ status.count }</td>
				            <td>${ music.rate }</td>
				            <td>${ music.title }</td>
				            <td>${ music.singer }</td>
				            <td>${ music.album }</td>
				            <td>
				            	<input class="toggle-heart" id=${ music.id } type="checkbox" ${ music.liked ? 'checked' : '' }  />
			                    <label for=${ music.id } class="toggle-heart-label">❤</label>
			                </td>
				        </tr>
			    	</c:forEach>
                </tbody>
            </table>
		</div>
	<!--4번 프레임 끝-->
    </div>
    <hr/>
    <div class="header">
    	<div class="title text-center" > <Strong>너와 나의 음악고리</Strong> </div>
    	<hr/>
    </div>
    <div id="mynetwork" class="text-center">
    </div>
    <script type="text/javascript">
	    var nodes = null;
	    var edges = null;
	    var network = null;
	
	    function draw() {
	      // create people.
	      // value corresponds with the age of the person
	      nodes = ${ nodeList };
	
	      // create connections between people
	      // value corresponds with the amount of contact between two people
	      edges = ${ edgeList };
	
	      // Instantiate our network object.
	      var container = document.getElementById('mynetwork');
	      var data = {
	        nodes: nodes,
	        edges: edges
	      };
	      var options = {
	        nodes: {
	          shape: 'dot',
	          scaling:{
	            label: {
	              min:8,
	              max:20
	            }
	          }
	        }
	      };
	      network = new vis.Network(container, data, options);
	    }
  </script>
  <hr/>
<!-- 3번 프레임 끝  -->
</div>
</body>
</html>
