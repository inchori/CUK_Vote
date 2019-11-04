<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.PrintWriter"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>받은 쪽지함</title>
<link rel="shortcut icon" type="image/x-icon"
	href="./resources/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
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
<script src="https://www.googletagmanager.com/gtag/js?id=UA-10874097-3"></script>
<link rel="stylesheet"
	href="data:text/css;charset=utf-8;base64,Y2xvdWRmbGFyZS1hcHBbYXBwPSJjZi1hdXRoLWJhciJdIHsKICBkaXNwbGF5OiBibG9jazsKICBkaXNwbGF5OiAtd2Via2l0LWJveDsKICBkaXNwbGF5OiAtbXMtZmxleGJveDsKICBkaXNwbGF5OiBmbGV4OwogIGZvbnQtd2VpZ2h0OiA3MDA7CiAgbGV0dGVyLXNwYWNpbmc6IC4wOGVtOwogIHBvaW50ZXItZXZlbnRzOiBub25lOwogIHRleHQtdHJhbnNmb3JtOiB1cHBlcmNhc2U7CiAgd2hpdGUtc3BhY2U6IG5vd3JhcDsKICBwb3NpdGlvbjogZml4ZWQ7CiAgdG9wOiAwOwogIGxlZnQ6IDA7CiAgcmlnaHQ6IDA7Cn0KCmNsb3VkZmxhcmUtYXBwW2FwcD0iY2YtYXV0aC1iYXIiXVtkYXRhLWhvcml6b250YWwtYWxpZ249ImxlZnQiXSB7CiAgLXdlYmtpdC1ib3gtcGFjazogc3RhcnQ7CiAgICAgIC1tcy1mbGV4LXBhY2s6IHN0YXJ0OwogICAgICAgICAganVzdGlmeS1jb250ZW50OiBmbGV4LXN0YXJ0Owp9CmNsb3VkZmxhcmUtYXBwW2FwcD0iY2YtYXV0aC1iYXIiXVtkYXRhLWhvcml6b250YWwtYWxpZ249ImxlZnQiXSBjZi1jb250ZW50IHsKICBib3JkZXItcmFkaXVzOiAwIDAgLjNlbSAwOwp9CgpjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl1bZGF0YS1ob3Jpem9udGFsLWFsaWduPSJjZW50ZXIiXSB7CiAgLXdlYmtpdC1ib3gtcGFjazogY2VudGVyOwogICAgICAtbXMtZmxleC1wYWNrOiBjZW50ZXI7CiAgICAgICAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjsKfQpjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl1bZGF0YS1ob3Jpem9udGFsLWFsaWduPSJjZW50ZXIiXSBjZi1jb250ZW50IHsKICBib3JkZXItcmFkaXVzOiAwIDAgLjNlbSAuM2VtOwp9CgpjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl1bZGF0YS1ob3Jpem9udGFsLWFsaWduPSJyaWdodCJdIHsKICAtd2Via2l0LWJveC1wYWNrOiBlbmQ7CiAgICAgIC1tcy1mbGV4LXBhY2s6IGVuZDsKICAgICAgICAgIGp1c3RpZnktY29udGVudDogZmxleC1lbmQ7Cn0KY2xvdWRmbGFyZS1hcHBbYXBwPSJjZi1hdXRoLWJhciJdW2RhdGEtaG9yaXpvbnRhbC1hbGlnbj0icmlnaHQiXSBjZi1jb250ZW50IHsKICBib3JkZXItcmFkaXVzOiAwIDAgMCAuM2VtOwp9CgpjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl0gY2YtY29udGVudCB7CiAgZGlzcGxheTogLXdlYmtpdC1ib3g7CiAgZGlzcGxheTogLW1zLWZsZXhib3g7CiAgZGlzcGxheTogZmxleDsKICAtbXMtZmxleC1saW5lLXBhY2s6IGNlbnRlcjsKICAgICAgYWxpZ24tY29udGVudDogY2VudGVyOwogIC13ZWJraXQtYm94LXBhY2s6IGNlbnRlcjsKICAgICAgLW1zLWZsZXgtcGFjazogY2VudGVyOwogICAgICAgICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7CiAgLXdlYmtpdC1ib3gtb3JpZW50OiBob3Jpem9udGFsOwogIC13ZWJraXQtYm94LWRpcmVjdGlvbjogbm9ybWFsOwogICAgICAtbXMtZmxleC1mbG93OiByb3cgd3JhcDsKICAgICAgICAgIGZsZXgtZmxvdzogcm93IHdyYXA7CiAgcGFkZGluZzogMCAxZW07CiAgbGluZS1oZWlnaHQ6IDIuNTsKICBwb2ludGVyLWV2ZW50czogYWxsOwp9CgpjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl0gY2YtZW50cmllcyB7CiAgZGlzcGxheTogLXdlYmtpdC1ib3g7CiAgZGlzcGxheTogLW1zLWZsZXhib3g7CiAgZGlzcGxheTogZmxleDsKfQoKY2xvdWRmbGFyZS1hcHBbYXBwPSJjZi1hdXRoLWJhciJdIGNmLWVudHJ5IHsKICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7CiAgLXdlYmtpdC1ib3gtZmxleDogMDsKICAgICAgLW1zLWZsZXg6IDAgMCBhdXRvOwogICAgICAgICAgZmxleDogMCAwIGF1dG87Cn0KCmNsb3VkZmxhcmUtYXBwW2FwcD0iY2YtYXV0aC1iYXIiXSBjZi1lbnRyeSArIGNmLWVudHJ5IHsKICBtYXJnaW4tbGVmdDogMWVtOwp9CgpjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl0gY2YtYWN0aW9ucyB7CiAgZGlzcGxheTogLXdlYmtpdC1ib3g7CiAgZGlzcGxheTogLW1zLWZsZXhib3g7CiAgZGlzcGxheTogZmxleDsKfQoKY2xvdWRmbGFyZS1hcHBbYXBwPSJjZi1hdXRoLWJhciJdIGEgewogIHRleHQtZGVjb3JhdGlvbjogbm9uZTsKfQoKQG1lZGlhIChtaW4td2lkdGg6IDc2OHB4KSB7CiAgY2xvdWRmbGFyZS1hcHBbYXBwPSJjZi1hdXRoLWJhciJdIGNmLWVudHJpZXMgKyBjZi1hY3Rpb25zIHsKICAgIG1hcmdpbi1sZWZ0OiAxZW07CiAgfQp9CgpAbWVkaWEgKG1heC13aWR0aDogNzY4cHgpIHsKICBjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl0gewogICAgZm9udC1zaXplOiAuOWVtICFpbXBvcnRhbnQ7CiAgfQoKICBjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl0gY2YtY29udGVudCB7CiAgICAtd2Via2l0LWJveC1vcmllbnQ6IHZlcnRpY2FsOwogICAgLXdlYmtpdC1ib3gtZGlyZWN0aW9uOiBub3JtYWw7CiAgICAgICAgLW1zLWZsZXgtZmxvdzogY29sdW1uOwogICAgICAgICAgICBmbGV4LWZsb3c6IGNvbHVtbjsKICAgIGJvcmRlci1yYWRpdXM6IDAgIWltcG9ydGFudDsKICAgIHdpZHRoOiAxMDAlICFpbXBvcnRhbnQ7CiAgfQoKICBjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl0gY2YtZW50cmllcyB7CiAgICAtd2Via2l0LWJveC1wYWNrOiBjZW50ZXI7CiAgICAgICAgLW1zLWZsZXgtcGFjazogY2VudGVyOwogICAgICAgICAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjsKICAgIC13ZWJraXQtYm94LW9yaWVudDogaG9yaXpvbnRhbDsKICAgIC13ZWJraXQtYm94LWRpcmVjdGlvbjogbm9ybWFsOwogICAgICAgIC1tcy1mbGV4LWZsb3c6IHJvdyB3cmFwOwogICAgICAgICAgICBmbGV4LWZsb3c6IHJvdyB3cmFwOwogIH0KCiAgY2xvdWRmbGFyZS1hcHBbYXBwPSJjZi1hdXRoLWJhciJdIGNmLWFjdGlvbnMgewogICAgLXdlYmtpdC1ib3gtcGFjazogY2VudGVyOwogICAgICAgIC1tcy1mbGV4LXBhY2s6IGNlbnRlcjsKICAgICAgICAgICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7CiAgfQoKICBjbG91ZGZsYXJlLWFwcFthcHA9ImNmLWF1dGgtYmFyIl0gY2YtYWN0aW9ucyBhIHsKICAgIGRpc3BsYXk6IGJsb2NrOwogICAgLXdlYmtpdC1ib3gtZmxleDogMTsKICAgICAgICAtbXMtZmxleDogMSAwIGF1dG87CiAgICAgICAgICAgIGZsZXg6IDEgMCBhdXRvOwogICAgdGV4dC1hbGlnbjogY2VudGVyOwogIH0KfQoK">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.css">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/unify/css/pages/page_log_reg_v1.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css">

<link rel="stylesheet" type="text/css" href="./css/note_main.140206.css">
<script type="text/javascript" charset="utf-8"
	src="https://platform.twitter.com/js/button.7db838345f9545c91ea4497f64abb814.js"></script>
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
						<a class="navbar-brand" href="main.jsp"> <img id="logo-header"
							src="./resources/logo.gif" alt="logo" data-retina>
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
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">쪽지</h1>
			</div>
		</div>
		<div class="container content">
			<div class="col-md-12">
				<div class="navbar-default" role="navgation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target=".navbar-responsive-collapse1">
							<span class="sr-only">Toggle navigation</span> <span
								class="fa fa-bars"></span>
						</button>
					</div>
					<div
						class="list_menu collapse navbar-collapse navbar-responsive-collapse1">
						<ul class="nav navbar-nav pull-right">
							<li><a href="writeMemo.jsp" class="bu3">쪽지 쓰기</a></li>
							<li><a href="./recvMemo" class="bu2">받은 쪽지함</a></li>
							<li><a href="./sentMemo" class="bu4">보낸 쪽지함</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="page-header" style="margin: 0">
					<h3>${memo.title}</h3>
				</div>
			</div>
			<div class="col-md-12 comment">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<strong>보낸이ID:</strong> ${memo.sent_id} &nbsp; <strong>보낸날짜:</strong>
							${memo.sentDate} &nbsp; <strong>보낸시간:</strong> ${memo.sentTime}
						</h4>
					</div>
					<div class="panel-body">
						<div class="content post" style="line-height: 30px;">
							<p>${memo.content}</p>
						</div>
					</div>
					<input class="btn btn-success pull-right" type="button"
						value="삭제하기"
						onclick="location.href='./deleteRecvMemo?recv_id=${memo.recv_id}&seqno=${memo.seqno}'">
					<input class="btn btn-primary pull-right" type="button"
						value="답장하기"
						onclick="location.href='./replyMemo?sent_id=${memo.sent_id}&recv_id=${memo.recv_id}&sentDate=${memo.sentDate}&sentTime=${memo.sentTime}&title=${memo.title}'">
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
</body>
</html>