<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=divice-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/barGraph.css">
<!-- style sheet ���� ������������ css�� �̿��� Ȩ�������� �⺻�� �����η� ��� -->
<title>�Խ���</title>
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
         loginedID = (String) session.getAttribute("loginedID");
      }
   %>
	<!-- �׺���̼� ���� -->
	<div class="wrapper">
		<div class="header no-print">
			<div class="topbar">
				<div class="container">
					<%
            if(loginedID==null){
            %>
					<ul class="loginbar pull-right">
						<li><a href="join.jsp">ȸ������</a></li>
						<li class="topbar-devider"></li>
						<li><a href="login.jsp">�α���</a></li>
					</ul>
					<%
               }else
               {%>
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
							src="./resources/logo.gif" alt="logo" data-retina>
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
	<div class="breadcrumbs">
		<div class="container">
			<h1 class="pull-left">�Խ���</h1>
		</div>
	</div>
	<div class="container content">
		<div class="col-mid-12">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<td width="110px"
							style="font-size: 1.0em; background-color: #fafafa;"><h5>��
								��</h5></td>
						<td colspan="5">${BBS.title}</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="font-size: 1.0em; background-color: #fafafa;">�� ��
							��</td>
						<td>${BBS.writer}</td>
						<td style="width: 110px;">�ۼ� �ð�</td>
						<td style="width: 220px;">${BBS.date}&nbsp; ${BBS.time}</td>
						<td style="width: 80px;">��ȸ��</td>
						<td>${BBS.step}</td>
					</tr>
					<tr>
						<td width="110px"
							style="font-size: 1.0em; background-color: #fafafa;"><h5>��
								��</h5></td>
						<td colspan="5">${BBS.content}</td>
					</tr>
	            	<jsp:useBean id="toDay" class="java.util.Date" />
	            	<c:choose>
		            	<c:when test="${empty bbsVoteInfo}">
		            	</c:when>
	    	        	<c:when test="${isVote eq 1}">
	        	    		<tr>
	            				<td style="font-size: 1.0em; background-color: #fafafa;">��ǥ ���</td>
		            				<td colspan="5" style="height:300px; vertical-align:middle">
		               					<div class="vGraph">
	    	           						<ul>
	        	       							<li>
	            	   								<span class="gTerm">${bbsVoteInfo.candidate1}</span>
	               									<span class="gBar" style="height:<fmt:formatNumber value="${bbsVoteInfo.getVote1/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/>">
	               										<span><fmt:formatNumber value="${bbsVoteInfo.getVote1/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/></span>
		               								</span>
		               							</li>
			               						<li>
		    	           							<span class="gTerm">${bbsVoteInfo.candidate2}</span>
	    	    	       							<span class="gBar" style="height:<fmt:formatNumber value="${bbsVoteInfo.getVote2/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/>">
	        	    	   								<span><fmt:formatNumber value="${bbsVoteInfo.getVote2/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/></span>
	            	   								</span>
	               								</li>
	               							</ul>
	               						</div>
		               				</td>
		               			</tr>
	    	        	</c:when>
	        	    	<c:when test="${toDay > endDate}">
	            			<tr>
	            				<td style="font-size: 1.0em; background-color: #fafafa;">��ǥ ���</td>
		            			<td colspan="5" style="height:300px; vertical-align:middle">
		               					<div class="vGraph">
	    	           							<ul>
	        	       						<li>
	            	   								<span class="gTerm">${bbsVoteInfo.candidate1}</span>
	               									<span class="gBar" style="height:<fmt:formatNumber value="${bbsVoteInfo.getVote1/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/>">
	               										<span><fmt:formatNumber value="${bbsVoteInfo.getVote1/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/></span>
		               								</span>
		               							</li>
			               						<li>
		    	           							<span class="gTerm">${bbsVoteInfo.candidate2}</span>
	    	    	       							<span class="gBar" style="height:<fmt:formatNumber value="${bbsVoteInfo.getVote2/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/>">
	        	    	   								<span><fmt:formatNumber value="${bbsVoteInfo.getVote2/(bbsVoteInfo.getVote1 + bbsVoteInfo.getVote2)}" type="percent"/></span>
	            	   								</span>
	               									</li>
	               							</ul>
		               					</div>
		               				</td>
	    	           			</tr>
	        	    	</c:when>
	            		<c:otherwise>
	            			<tr>
	            				<td width="110px" style="font-size: 1.0em; background-color: #fafafa;"><h5>�� ǥ</h5></td>
	            				<td colspan=5>
	            					<form method="post" action="./bbsVote">
		            					<table class="table table-bordered table-hover" style="text-align: center; border: 1px soild #ddddd">
											<thead>	
												<tr>
													<th style="background-color: #fafafa; color: #000000; width: 150px;"><h5>����</h5></th>
													<th style="background-color: #fafafa; color: #000000; width: auto;"><h5>���� 1</h5></th>
													<th style="background-color: #fafafa; color: #000000; width: auto;"><h5>���� 2</h5></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><h5>�� ǥ</h5></td>
													<td>
															<input type="radio" id="candidateChoice" name="candidateChoice" value="1"/>
														<label for="candidateChoice">${bbsVoteInfo.candidate1}</label>
													</td>
													<td>
														<input type="radio" id="candidateChoice" name="candidateChoice" value="2"/>
														<label for="candidateChoice">${bbsVoteInfo.candidate2}</label>
													</td>
												</tr>
												<tr>
													<td colspan="3">
														<input type="hidden" id="voteKind" name="voteKind" value="${bbsVoteInfo.voteKind}">
														<input type="hidden" id="boardNo" name="boardNo" value="${bbsVoteInfo.seqno}">
														<input type="submit" class="btn btn-primary pull-center" value="��ǥ">
													</td>
												</tr>
											</tbody>
										</table>
									</form>
	    	        			</td>
	        	    		</tr>
	            		</c:otherwise>
		            </c:choose>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${commentCnt eq 0 }">
					<p>�Խõ� ����� �����ϴ�.
					<p>
				</c:when>
				<c:otherwise>
					<c:forEach var="cnt" begin="0" end="${COMMENT_LIST.listSize-1}">
						<div class="panel panel-info">
							<div class="panel-body">
								${COMMENT_LIST.idList[cnt]} &nbsp;&nbsp;&nbsp;
								${COMMENT_LIST.contentList[cnt]} <small><i>
										${COMMENT_LIST.commentDateList[cnt]}
										${COMMENT_LIST.commentTimeList[cnt]}</i></small>
								<c:if test="${COMMENT_LIST.idList[cnt] eq loginedID}">
									<i><a
										href="commentDelete?boardNo=${boardNo}&commentNo=${COMMENT_LIST.commentNoList[cnt]}"
										onclick="return confirm('����� �����Ͻðڽ��ϱ�?')">x</a></i>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${commentCnt eq 0 }">
					<c:if test="${!COMMENT_LIST.lastPage}">
						<A
							href='bbs?boardNo=${boardNo}&LAST_SEQ_NO=${COMMENT_LIST.commentNoList[COMMENT_LIST.listSize-1]}'>����
							������</A>
					</c:if>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${loginedID ne null }">
					<form method="POST"
						action="commentInsert?boardNo=${boardNo}&id=<%=loginedID %>">
						<textarea class="form-control" name="content"
							placeholder="����� �Է����ּ���. (�ִ� 300byte)" /></textarea>
						<input style="background-color: #558ae4; color: white;"
							class="btn pull-right" type="submit" value="���" />
					</form>
				</c:when>
			</c:choose>
			<a href="bbslist" class="btn btn-success pull-left" type="submit">�۸��</a>
		</div>
	</div>

</body>
</html>