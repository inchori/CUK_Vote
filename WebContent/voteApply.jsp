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
<!-- style sheet ���� ������������ css�� �̿��� Ȩ�������� �⺻�� �����η� ��� -->
<title>��ǥ ��û</title>
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
				style="text-align: center; border: 1px solid #dddddd;">
				<thead>
					<tr>
						<th
							style="background-color: #fafafa; color: #000000; width: auto;"><h5>��ǥ
								����</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 150px;"><h5>����
								��</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 150px;"><h5>����
								��</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 100px;"><h5>�ۼ���</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 100px;"
							colspan="2"><h5>���� ����</h5></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty voteApplyList}">
							<tr>
								<td colspan="5" style="vertical: middle;">�Խõ� ���� �����ϴ�.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="voteApplyL" items="${voteApplyList}">
								<TR>
									<TD style="vertical-align: middle;"><a
										href="./voteApplyBBS?voteKind=${voteApplyL.voteKind}">${voteApplyL.voteKind}</a></TD>
									<TD style="vertical-align: middle;">${voteApplyL.startDate}&nbsp;${voteApplyL.startTime}</TD>
									<TD style="vertical-align: middle;">${voteApplyL.endDate}&nbsp;${voteApplyL.endTime}</TD>
									<td style="vertical-align: middle;">${voteApplyL.writer}</td>
									<TD style="width: 50px; vertical-align: middle;">
										<button type="button" class="btn btn-primary"
											onclick="window.location.href='./applyVote?voteKind=${voteApplyL.voteKind}'">����</button>
									</TD>
									<td style="width: 50px; vertical: middle;"><button type="button"
											class="btn btn-warning"
											onclick="window.location.href='./deleteVoteApply?voteKind=${voteApplyL.voteKind}'">����</button></td>
								</TR>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<button type="button" class="btn btn-primary pull-right"
				onclick="window.location.href='./voteKindApply.jsp'">��û�ϱ�</button>
			<div class="col-md-12">
				<div class="text-center">
					<ul class="pagination">
						<c:if test="${pageNumber ne '1'}">
							<li><a href="./voteApplyView?pageNumber=${pageNumber-1}">����</a></li>
						</c:if>
						<c:if test="${nextPage}">
							<li><a href="./voteApplyView?pageNumber=${pageNumber+1}">����</a></li>
						</c:if>
					</ul>
				</div>
			</div>
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