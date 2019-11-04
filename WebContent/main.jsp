<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=divice-width initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<!-- style sheet 참조 같은폴더안의 css를 이용해 홈페이지의 기본적 디자인로 사용 -->
<title>가톨릭대학교 투표 웹</title>
<link rel="shortcut icon" type="image/x-icon" href="./resources/favicon.ico" />
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


<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-10874097-3"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag('js', new Date());

	gtag('config', 'UA-10874097-3');
</script>
<script type="text/javascript">

</script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.css" />
<meta name="username" content="">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/revolution-slider/rs-plugin/css/settings.css"
	type="text/css" media="screen">
</head>
<body>
	<%
		String loginedID = null;
		if (session.getAttribute("loginedID") != null) {
			loginedID = (String)session.getAttribute("loginedID");
		}
	%>
	<!-- 네비게이션 구성 -->
	<div class="wrapper">
		<div class="header no-print">
			<div class="topbar">
				<div class="container">
					<%
				if(loginedID==null){
				%>
					<ul class="loginbar pull-right">
						<li><a href="join.jsp">회원가입</a></li>
						<li class="topbar-devider"></li>
						<li><a href="login.jsp">로그인</a></li>
					</ul>
					<%
					}else
					{%>
					<ul class="loginbar pull-right">
						<li><a class="userID" ><%=loginedID%></a></li>
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
							src="./resources/logo.gif" style="width: 233px;
    height: 62px;" alt="Logo" data-retina>
							
						</a>
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
		<div class="tp-banner-container">
			<div class="tp-banner">
				<ul>
					<li class="revolution-mch-1" data-transition="fade"
						data-slotamount="4" data-masterspeed="1000"
						data-title="Catholic Voting System"><img
						src="./resources/2025.jpg"
						alt="" data-bgfit="cover" data-bgposition="center"
						data-bgrepeat="no-repeat" class="rev-slidebg">
						<div class="tp-caption re-title-v2 sft start" data-x="center"
							data-hoffset="0" data-y="center" data-speed="1500"
							data-start="500" data-easing="Back.easeInOut"
							data-endeasing="Power1.easeIn" data-endspeed="300">Catholic Voting System</div></li>
					<li class="revolution-mch-1" data-transition="fade"
						data-slotamount="4" data-masterspeed="1000"
						data-title="Think. Vote. Solve."><img
						src="./resources/man.jpg"
						alt="darkblurbg" data-bgfit="cover" data-bgposition="center"
						data-bgrepeat="no-repeat">
						<div class="tp-caption re-title-v2 sft" data-x="center"
							data-hoffset="0" data-y="center" data-speed="1400"
							data-start="1000" data-easing="easeOutBack" data-endspeed="300"
							data-endeasing="easeInQuad" data-captionhidden="off"
							style="z-index: 6">Think. Vote. Solve.</div></li>
					<li class="" data-transition="fade" data-slotamount="4"
						data-masterspeed="1000" data-title="Veritas Love Volunteer"><img
						src="./resources/veritas.jpg"
						alt="darkblurbg" data-bgfit="cover" data-bgposition="center"
						data-bgrepeat="no-repeat">
						<div class="tp-caption re-title-v2 sft" data-x="center"
							data-hoffset="0" data-y="center" data-speed="1400"
							data-start="1000" data-easing="easeOutBack" data-endspeed="300"
							data-endeasing="easeInQuad" data-captionhidden="off"
							style="z-index: 6">Veritas. Love. Volunteer.</div></li>
				</ul>
				<div class="tp-bannertimer tp-bottom"></div>
			</div>
		</div>
		<div class="container content-sm">
			<div class="text-center margin-bottom-50">
				<h2 class="title-v2 title-center">Catholic Voting System</h2>
				<p class="space-lg-hor">가톨릭대학교 종합 투표 시스템입니다.</p>
			</div>
		</div>
		<div class="parallax-counter-v1 parallaxBg" style="background-position: 50% 73px;">
			<div class="container">
		        <div class="row margin-bottom-10">
		        	<c:forEach var="num" begin="0" end="3">
		        		<c:choose>
		        			<c:when test="${empty voteList[num]}">
		        			</c:when>
		        			<c:otherwise>
		        				<div class="col-sm-3 col-xs-6">
			        				<div class="counters" OnClick="location.href='./voteView?voteKind=${voteList[num].voteKind}'">
				        				<span class="counter">${voteListCount[voteList[num]]}</span>
			    	    				<h4>${voteList[num].voteKind} 투표</h4>
			        				</div>
		        				</div>
		        			</c:otherwise>
		        		</c:choose>
		        	</c:forEach>
        		</div>
   			</div>
		</div>
	</div>
	<%
		//오류메시지가 건너오면
		String messageContent = null;
		String messageType = null;
		if (session.getAttribute("messageContent") != null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		if (session.getAttribute("messageType") != null) {
			messageType = (String) session.getAttribute("messageType");
		}
		if (messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div
					class="modal-content
					<%if (messageType.equals("오류 메시지"))
					out.println("panel-warning");
				else //노랑색 or빨간색
					out.println("panel-success");%>">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="close">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType%>
						</h4>
					</div>
					<div class="modal-body">
						<%=messageContent%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		//만든 messagemodal을 실행할수 있게함
		$('#messageModal').modal("show");
	</script>
	<%
		//한번 메시지가 출력된 다음에는 두세번 나오지 않게함
			session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS-MML_SVG"></script>
	<script type="text/javascript"
		src="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/jquery.parallax.js"></script>
	<script type="text/javascript"
		src="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/counter/waypoints.min.js"></script>
	<script type="text/javascript"
		src="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/counter/jquery.counterup.min.js"></script>
	<script type="text/javascript"
		src="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/revolution-slider/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
	<script type="text/javascript"
		src="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/revolution-slider/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript"
		src="https://ddo7jzca0m2vt.cloudfront.net/unify/js/plugins/revolution-slider.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			RevolutionSlider.initRSfullWidth();
			$('.counter').counterUp({
				delay : 10,
				time : 1000
			});
			$(window).load(function() {
				$('.parallaxBg').parallax("50%", 0.2);
				$('.parallaxBg1').parallax("50%", 0.4);
			});
		});
	</script>
</body>
</html>