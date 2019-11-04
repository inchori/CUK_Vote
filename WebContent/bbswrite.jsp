<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
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
<title>글쓰기</title>
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
   	<script async src="//www.google-analytics.com/analytics.js"></script>
	<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
	<script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
	<link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
	<script type="text/javascript">
    	$(function () {
    		$('#enddatepicker').datetimepicker({
       	 		format: 'YYYY-MM-DD'
    		});
		});
     </script>
    <script type="text/javascript">
    	$(function () {
        	$('#endtimepicker').datetimepicker({
            	format: 'HH:mm:ss'
        	});
    	});
     </script>
     <script> 
		function myShowFunction() {
    		document.getElementById("vote").style.display = "table";
    		document.getElementById("voteButton").style.display = "none";
    		document.getElementById("voteCheck").checked = true;
		}
		function myHiddenFunction() {
    		document.getElementById("vote").style.display = "none";
    		document.getElementById("voteButton").style.display = "inline-block";
    		document.getElementById("voteCheck").checked = false;
		}
	</script>
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
            if(loginedID==null){
               session.setAttribute("messageType", "오류메시지");
               session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
               response.sendRedirect("bbslist");
               return;
               %><%
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
      <div class="container content">
	      <form method="post" action="bbsWrite?id=<%=loginedID %>">
	      <table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
	         <thead>
	            <tr>
	               <th colspan="3"><h5><b>게시물 작성</b></h5></th>
	            </tr>
	         </thead>
	         <tbody>
	            <tr>
	               <td width="110px"><h5>제목</h5></td>
	               <td colspan="2"><input class="form-control" type="text" id="title" name="title" maxlength="50" placeholder="제목을 입력하세요."></td>
	            </tr>
	            <tr>
	               <td width="110px"><h5>내용</h5></td>
	               <td colspan="2">
	               		<textarea class="form-control" rows="10" id="content" name="content" maxlength="2000" placeholder="글 내용을 입력하세요. (최대 2000byte)"></textarea>
	               </td>
	            </tr>
	            <tr>
	            	<td width="110px"><h5>투표</h5><input type="checkbox" id="voteCheck" name="voteCheck" hidden /></td>
	            	<td colspan="2">
	            		<p class="btn btn-primary pull-center" onclick="myShowFunction()" id="voteButton">투표 추가</p>
	            		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd; display: none;" id="vote">
    	     				<tbody>
	            				<tr>
					               <td width="110px"><h5>투표 종류</h5></td>
					               <td colspan="2"><input class="form-control" type="text" id="voteKind" name="voteKind" maxlength="50" placeholder="투표 종류를 입력하세요."></td>
				        	    </tr>
				        	    <tr>
        	    					<td width="110px"><h5>종료 날짜</h5></td>
			        	    		<td>
        	    						<div class='input-group date' id='enddatepicker'>
                    						<input type='text' class="form-control" name="endDate" id="endDate"/>
                    						<span class="input-group-addon">
                        						<span class="glyphicon glyphicon-calendar"></span>
                    						</span>
                						</div>
        	    					</td>
        	    				</tr>
        	    				<tr>
        	    					<td width="110px"><h5>종료 시간</h5></td>
        	    					<td>
        								<div class='input-group date' id='endtimepicker' >
                    						<input type='text' class="form-control" name="endTime" id="endTime" />
                    						<span class="input-group-addon">
                        						<span class="glyphicon glyphicon-time"></span>
                    						</span>
                						</div>
   									</td>
        	    				</tr>
        	    				<tr>
        	    					<td width="110px"><h5>선택 1</h5></td>
        	    					<td><input type="text" class="form-control" id="candidate1" name="candidate1" placeholder="이름을 입력해주세요."></td>
        	    				</tr>
        	    				<tr>
        	    					<td width="110px"><h5>선택 2</h5></td>
    	           					<td><input type="text" class="form-control" id="candidate2" name="candidate2" placeholder="이름을 입력해주세요."></td>
        	    				</tr>
        	    				<tr>
        	    					<td colspan="2"><p class="btn btn-primary pull-center" onclick="myHiddenFunction()">투표 제거</p></td>
        	    				</tr>
	         				</tbody>		
	      				</table>
	            	</td>
	            </tr>
	            <tr>
	               <td style="text-align:left;" colspan="3"><h5 style="color: red;"></h5><input class="btn btn-primary pull-right" type="submit" value="등록"></td>
	            </tr>
	         </tbody>		
	      </table>
	      </form>
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