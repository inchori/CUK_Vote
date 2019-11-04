<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=divice-width initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/barGraph.css">
<!-- style sheet ���� ������������ css�� �̿��� Ȩ�������� �⺻�� �����η� ��� -->
<title>��ǥ ���</title>
<link rel="shortcut icon" type="image/x-icon"
	href="./resources/favicon.ico" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- �ִϸ��̼� ����ϴ� �ڹٽ�ũ��Ʈ ���� -->
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
<script type="text/javascript">
	
</script>

<script src="css/barGraph.css"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.css" />
<meta name="username" content="">
<link rel="stylesheet"
	href="https://ddo7jzca0m2vt.cloudfront.net/unify/plugins/revolution-slider/rs-plugin/css/settings.css"
	type="text/css" media="screen">
<body>
	<%
		String loginedID = null;
		if (session.getAttribute("loginedID") != null) {
			loginedID = (String) session.getAttribute("loginedID");
		}
	%>
	<!-- �׺���̼� ���� -->
	<div class="wrapper">
		<div class="header no-print">
			<div class="topbar">
				<div class="container">
					<%
						if (loginedID == null) {
					%>
					<ul class="loginbar pull-right">
						<li><a href="join.jsp">ȸ������</a></li>
						<li class="topbar-devider"></li>
						<li><a href="login.jsp">�α���</a></li>
					</ul>
					<%
						} else {
					%>
					<ul class="loginbar pull-right">
						<li><a class="userID"><%=loginedID%></a></li>
						<li class="topbar-devider"></li>
						<li><a href="./loadProfile" class="username">����</a></li>
						<li class="topbar-devider"></li>
						<li><a href="./logout">�α׾ƿ�</a></li>
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
							alt="Logo" data-retina>

						</a>
					</div>
					<div class="collapse navbar-collapse navbar-responsive-collapse">
						<ul class="nav navbar-nav">
							<li><a href="./voteKindView">��ǥ</a></li>
							<li><a href="bbslist">�Խ���</a></li>
							<li><a href="./recvMemo">����</a>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="breadcrumbs">
		<div class="container">
			<h1 class="pull-left">��ǥ</h1>
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
					<li><a href="./voteKindView">��ǥ ��</a></li>
					<li><a href="./endVoteView">��ǥ ���</a></li>
					<li><a href="./voteApplyView">��ǥ ��û</a>
				</ul>
			</div>
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<td width="110px"
							style="font-size: 1.0em; background-color: #fafafa;"><h5>��ǥ
								����</h5></td>
						<td colspan="3">${endVote.voteKind}</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;">���� �ð�</td>
						<td style="width: 220px;">${endVote.endDate}&nbsp;
							${endVote.endTime}</td>
						<td style="width: 80px;">�� ��ǥ ��</td>
						<td>${endVote.getVote}</td>
					</tr>
					<tr>
						<td width="110px"
							style="font-size: 1.0em; background-color: #fafafa;"><h5>��
								��</h5></td>
						<c:choose>
							<c:when test="${empty candidateList}">
								<td colspan="3">�Խõ� ���� �����ϴ�.</td>
							</c:when>
							<c:when test="${candidateList.size() eq 1 }">
								<c:choose>
									<c:when test="${endVote.getVote ne 0 }">
										<td colspan="3" style="height: 300px; vertical-align: middle">
											<div class="vGraph">
												<ul>
													<li><span class="gTerm">����</span> <span class="gBar"
														style="height:<fmt:formatNumber value="${candidateList[0].getVote/endVote.getVote}" type="percent"/>">
															<span><fmt:formatNumber
																	value="${candidateList[0].getVote/endVote.getVote}"
																		type="percent" /></span>
													</span></li>
													<li><span class="gTerm">�ݴ�</span> <span class="gBar"
														style="height:<fmt:formatNumber value="${(endVote.getVote - candidateList[0].getVote)/endVote.getVote}" type="percent"/>">
														<span><fmt:formatNumber
																	value="${(endVote.getVote -candidateList[0].getVote)/endVote.getVote}"
																	type="percent" /></span>
													</span></li>
												</ul>
											</div>
											</td>
									</c:when>
									<c:otherwise>
										<td colspan="3" style="height: 300px; vertical-align: middle">
											<div class="vGraph">
												<ul>
													<li><span class="gTerm">����</span> <span class="gBar"
														style="height: 0">
															<span>0%</span>
														</span></li>
													<li><span class="gTerm">�ݴ�</span> <span class="gBar"
														style="height:0">
														<span>0%</span>
													</span></li>
												</ul>
											</div>
										</td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${endVote.getVote ne 0 }">
										<td colspan="3" style="height: 300px; vertical-align: middle">
											<div class="vGraph">
												<ul>
													<c:forEach var="cnt" begin="0"
														end="${candidateList.size()-1}">
														<li><span class="gTerm">��ȣ${candidateList[cnt].no}</span>
															<span class="gBar"
															style="height:<fmt:formatNumber value="${candidateList[cnt].getVote/endVote.getVote}" type="percent"/>">
																<span>span><fmt:formatNumber
																		value="${candidateList[cnt].getVote/endVote.getVote}"
																		type="percent" /></span>
														</span></li>
													</c:forEach>
												</ul>
											</div>
										</td>
									</c:when>
										<c:otherwise>
										<td colspan="3" style="height: 300px; vertical-align: middle">
											<div class="vGraph">
												<ul>
													<c:forEach var="cnt" begin="0"
														end="${candidateList.size()-1}">
															<li><span class="gTerm">��ȣ${candidateList[cnt].no}</span>
															<span class="gBar"
															style="height:0">
																	<span>0%</span>
														</span></li>
													</c:forEach>
												</ul>
											</div>
										</td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>
			<button type="button" class="btn btn-primary"
				onclick="window.location.href='./endVoteView'">���</button>
		</div>
	</div>
</body>
</html>