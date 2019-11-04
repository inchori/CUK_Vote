<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=divice-width initial-scale=1">
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<!-- style sheet 참조 같은폴더안의 css를 이용해 홈페이지의 기본적 디자인로 사용 -->
<title>투표 신청</title>
<link rel="shortcut icon" type="image/x-icon"
	href="./resources/favicon.ico" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 애니메이션 담당하는 자바스크립트 참조 -->
<script src="js/bootstrap.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/unify/css/style.css?version=20180524-2">
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700|Open+Sans:300,300i,400,400i,700,700i|Source+Code+Pro&amp;subset=korean"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/css/connect.css">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/css/result.css?version=20180524-2">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/unify/css/custom.css?version=20180524-2">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/unify/css/theme-colors/blue.css?version=20151221-3">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/css/pace.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.css" />
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/revolution-slider/rs-plugin/css/settings.css"
	type="text/css" media="screen">
</head>
<body>
	<%
		String loginedID = null;
		if (session.getAttribute("loginedID") != null) {
			loginedID = (String) session.getAttribute("loginedID");
		}
	%>
	<!-- 네비게이션 구성 -->
	<div class="wrapper">
		<div class="header no-print">
			<div class="topbar">
				<div class="container">
					<%
						if (loginedID == null) {
					%>
					<ul class="loginbar pull-right">
						<li><a href="join.jsp">회원가입</a></li>
						<li class="topbar-devider"></li>
						<li><a href="login.jsp">로그인</a></li>
					</ul>
					<%
						} else {
					%>
					<ul class="loginbar pull-right">
						<li><a class="userID"><%=loginedID%></a></li>
						<li class="topbar-devider"></li>
						<li><a href="./loadProfile" class="username">설정</a></li>
						<li class="topbar-devider"></li>
						<li><a href="./logout">로그아웃</a></li>
					</ul>
					<%
						}
					%>
				</div>
			</div>
			<div class="navbar navbar-default mega-menu" role="navgation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target=".navbar-responsive-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="fa fa-bars"></span>
						</button>
						<a class="navbar-brand" href="main"> <img id="logo-header"
							src="./resources/logo.gif" style="width: 233px; height: 62px;"
							alt="Logo" data-retina></a>
					</div>
					<div class="collapse navbar-collapse navbar-responsive-collapse">
						<ul class="nav navbar-nav">
							<li><a href="./voteKindView">투표</a></li>
							<li><a href="bbslist">게시판</a></li>
							<li><a href="./recvMemo">쪽지</a>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="breadcrumbs">
		<div class="container">
			<h1 class="pull-left">투표</h1>
		</div>
	</div>
	<div class="container content">
		<div class="col-md-12">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-responsive-collapse1">
				<span class="sr-only">Toggle navigation</span> <span
					class="fa fa-bars"></span>
			</button>
			<div class="collapse navbar-collapse navbar-responsive-collapse1">
				<ul class="nav navbar-nav">
					<li><a href="./voteKindView">투표 중</a></li>
					<li><a href="./endVoteView">투표 결과</a></li>
					<li><a href="./voteApplyView">투표 신청</a>
				</ul>
			</div>
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px soild #ddddd">
				<thead>
					<tr>
						<th width="110px"
							style="font-size: 1.0em; background-color: #fafafa;"><h5>제
								목</h5></th>
						<th colspan="5" style="vertical-align:middle;">${voteApplyInfo.voteKind}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="font-size: 1.0em; background-color: #fafafa;">작 성
							자</td>
						<td>${voteApplyInfo.writer}</td>
						<td style="width: 110px;">시작 시간</td>
						<td style="width: 220px;">${voteApplyInfo.startDate}&nbsp;
							${voteApplyInfo.startTime}</td>
						<td style="width: 110px;">종료 시간</td>
						<td style="width: 220px;">${voteApplyInfo.endDate}&nbsp;
							${voteApplyInfo.endTime}</td>
					</tr>
					<tr>
						<td colspan="2"><h5>이 름</h5></td>
						<td colspan="2"><h5>약 력</h5></td>
						<td colspan="2"><h5>공 약</h5></td>
					</tr>
					<c:choose>
						<c:when test="${empty candidateList}">
							<tr>
								<td colspan="6">후보가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="candidateL" items="${candidateList}">
								<tr>
									<td colspan="2" style="vertical-align: middle; text-align: left;">
										기호.${candidateL.no}<a
										href="./sentToCandiMemo?voteKind=${voteApplyInfo.voteKind}&seqNo=${candidateL.no}">${candidateInfoList[candidateL].name}</a>
										<c:choose>
											<c:when test="${empty subCandidateList[candidateL]}">
											</c:when>
											<c:otherwise>
												&nbsp;<a
													href="./sentToSubCandiMemo?voteKind=${voteApplyInfo.voteKind}&seqNo=${candidateL.no}">${subCandidateInfoList[subCandidateList[candidateL]].name}</a>
											</c:otherwise>
										</c:choose>
									</td>
									<td colspan="2" style="text-align: left;">후보자 약력<br>${candidateL.carreer} <c:choose>
											<c:when test="${empty subCandidateList[candidateL]}">
											</c:when>
											<c:otherwise>
												<br>부후보자 약력<br>${subCandidateList[candidateL].carreer}
											</c:otherwise>
										</c:choose>
									</td>
									<td colspan="2" style="text-align: left;">${candidateL.commitment}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<button type="button" class="btn btn-primary"
				onclick="window.location.href='./voteApplyView'">목록</button>
		</div>
	</div>
</body>
</html>