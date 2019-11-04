<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=divice-width initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<!-- style sheet ���� ������������ css�� �̿��� Ȩ�������� �⺻�� �����η� ��� -->
<title>��ǥ ��</title>
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
			<form method="post" action="./vote">
				<h4>${voteKind}�ĺ�</h4>
				<c:choose>
					<c:when test="${empty candidateList}">
						<table class="table table-bordered table-hover"
							style="text-align: center; vertical-align: middle; border: 1px soild #ddddd">
							<thead>
								<tr>
									<th
										style="background-color: #fafafa; color: #000000; width: auto;">�ĺ�
										����</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>�ĺ��� �����ϴ�.</td>
								</tr>
							</tbody>
						</table>
					</c:when>
					<c:when test="${candidateList.size() eq 1}">
						<table class="table table-bordered table-hover"
							style="text-align: center; vertical-align: middle; border: 1px soild #ddddd">
							<thead>
								<tr>
									<th
										style="background-color: #fafafa; color: #000000; width: 150px;"><h5>����</h5></th>
									<th
										style="background-color: #fafafa; color: #000000; width: auto;"
										colspan="2"><h5>��ȣ 1</h5></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><h5>�� ��</h5></td>
									<td colspan="2" style="text-align: left"><a
										href="./sentToCandiMemo?voteKind=${voteKind}&seqNo=${1}">${candidateInfoList[candidateList[0]].name}</a>
										<c:choose>
											<c:when test="${empty subCandidateList}">
											</c:when>
											<c:otherwise>
											&nbsp;<a
													href="./sentToSubCandiMemo?voteKind=${voteKind}&seqNo=${1}">${subCandidateInfoList[subCandidate[0]].name}</a>
											</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<td><h5>�� ��</h5></td>
									<td colspan="2" style="text-align: left;">�ĺ��� ���<br>${candidateList[0].carreer}
										<c:choose>
											<c:when test="${empty subCandidateList[candidateList[0]]}">
											</c:when>
											<c:otherwise>
												<br>���ĺ��� ���<br>${subCandidateList[candidateList[0]].carreer}
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td><h5>�� ��</h5></td>
									<td colspan="2" style="text-align: left;">
										${candidateList[0].commitment}</td>
								</tr>
								<tr>
									<td><h5>�� ǥ</h5></td>
									<td><input type="radio" id="candidateChoice"
										name="candidateChoice" value="${candidateList[0].no}" /> <label
										for="candidateChoice">����</label></td>
									<td><input type="radio" id="candidateChoice"
										name="candidateChoice" value="0" /> <label
										for="candidateChoice">�ݴ�</label></td>
								</tr>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<table class="table table-bordered table-hover"
							style="text-align: center; border: 1px soild #ddddd">
							<thead>
								<tr>
									<th
										style="background-color: #fafafa; color: #000000; width: 150px;"><h5>����</h5></th>
									<c:forEach var="candidateL" items="${candidateList}">
										<th
											style="background-color: #fafafa; color: #000000; width: ${100/candidateList.size()}%;"><h5>��ȣ
												${candidateL.no}</h5></th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><h5>�� ��</h5></td>
									<c:forEach var="candidateL" items="${candidateList}">
										<td style="text-align: left; vertical-align: middle;"><a
											href="./sentToCandiMemo?voteKind=${voteKind}&seqNo=${candidateL.no}">${candidateInfoList[candidateL].name}</a>
											<c:choose>
												<c:when test="${empty subCandidateList[candidateL]}">
												</c:when>
												<c:otherwise>
												&nbsp;<a
														href="./sentToSubCandiMemo?voteKind=${voteKind}&seqNo=${candidateL.no}">${subCandidateInfoList[subCandidateList[candidateL]].name}</a>
												</c:otherwise>
											</c:choose></td>
									</c:forEach>
								</tr>
								<tr>
									<td><h5>�� ��</h5></td>
									<c:forEach var="candidateL" items="${candidateList}">
										<td style="text-align: left; vertical-align: middle;">�ĺ��� ���<br>${candidateL.carreer}
											<c:choose>
												<c:when test="${empty subCandidateList[candidateL]}">
												</c:when>
												<c:otherwise>
													<br>���ĺ��� ���<br>${subCandidateList[candidateL].carreer}
											</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<td><h5>�� ��</h5></td>
									<c:forEach var="candidateL" items="${candidateList}">
										<td style="text-align: left; vertical-align: middle;">${candidateL.commitment}</td>
									</c:forEach>
								</tr>
								<tr>
									<td><h5>�� ǥ</h5></td>
									<c:forEach var="candidateL" items="${candidateList}">
										<td><input type="radio" id="candidateChoice"
											name="candidateChoice" value="${candidateL.no}" /> <label
											for="candidateChoice">��ȣ ${candidateL.no}</label></td>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="container" align="center">
					<input type="hidden" id="voteKind" name="voteKind"
						value="${voteKind}"> <input type="submit" value="��ǥ"
						width="150" height="48">
				</div>
			</form>
		</div>
	</div>
	<%
		//�����޽����� �ǳʿ���
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
					<%if (messageType.equals("���� �޽���"))
					out.println("panel-warning");
				else //����� or������
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
						<button type="button" class="btn btn-primary" data-dismiss="modal">Ȯ��</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		//���� messagemodal�� �����Ҽ� �ְ���
		$('#messageModal').modal("show");
	</script>
	<%
		//�ѹ� �޽����� ��µ� �������� �μ��� ������ �ʰ���
			session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>
</body>
</html>