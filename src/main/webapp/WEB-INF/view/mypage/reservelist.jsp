<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예매내역</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/mypage/reservelist.css">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<img id="header_ci" alt="브랜드 로고" src="<%=request.getContextPath()%>/resources/images/ci.png">
		<div>
			<a href="#">로그인</a>
			<a href="#">회원가입</a>
			<a href="#">바로예매</a>
		</div>

	</header>
	
	<nav>
		<ul>
			<li class="nav"><a href="#">영화</a></li>
			<li class="nav"><a href="#">예매</a></li>
			<li class="nav"><a href="#">극장</a></li>
			<li class="nav"><a href="#">이벤트</a></li>
			<li class="nav"><a href="#">고객센터</a></li>
			<li id="mypagebtn"><a href="#"><i class="far fa-user"></i></a></li>
		</ul>
	</nav>
	
	<section>
		<div id="container">
			<div id="mypage-menubar">
			
				<ul>
					<li><a href="#"><h2>My Page</h2></a></li>
					<li><a href="#">예매내역</a></li>
					<li><a href="#">한줄평 내역</a></li>
					<li><a href="#">1대1 문의 내역</a></li>
					<li><a href="#">개인정보 수정</a></li>
					<li><a href="#">회원탈퇴</a></li>
				</ul>
			</div>
			
			<div id="reserve-wrap">
				<div id="reserve-history">
					<h2>예매내역</h2>
					<ul id="reserve-list">
						<li>예매 내역이 없습니다.</li>
					</ul>
				</div>
				
				<div id="desc">
					<h3>예매 유의사항</h3>
					<p>01. 영화 상영 시간표는 영화관 사정에 의해 변경될 수 있습니다.</p>
					<p>02. 온라인 예매 및 취소는 영화 상영시작 시간 20분 전까지 가능합니다.</p>
				</div>
			</div>
		</div>
	</section>
	
	<footer>
		<div id="content">
			<img id="footer_ci" alt="브랜드 로고" src="<%=request.getContextPath()%>/resources/images/ci.png">
			<div id="textarea">
				<p>COPYRIGHT © BoxMovie, Inc. All rights reserved</p>
				<p>대구광역시 서구 서대구로 7길2 (내당동 245-4번지 2층) ARS 053-555-1333</p>
			</div>
		</div>
	</footer>

</body>
</html>