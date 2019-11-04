<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=divice-width initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<!-- style sheet ���� ������������ css�� �̿��� Ȩ�������� �⺻�� �����η� ��� -->
<title>ȸ������</title>
<link rel="shortcut icon" type="image/x-icon" href="./resources/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
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
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
	function passwordCheckFunction() {
		var password = $('#password').val();
		var password2 = $('#password2').val();
		if (password != password2) {
			$('#passwordCheckMessage').html('��й�ȣ�� ���� ��ġ���� �ʽ��ϴ�.')
		} else {
			$('#passwordCheckMessage').html('')
		}
	}
	function registerCheckFunction() {
		var ID = $('#ID').val();
		$.ajax({
			type : 'POST',
			url : './checkID',
			data : {
				ID : ID
			},
			success : function(result) {
				if (result == 1) {
					$('#checkMessage').html('����� �� �ִ� ���̵��Դϴ�.');
					$('#checkType')
							.attr('class', 'modal-content panel-success');
				} else if (result == 0) {
					$('#checkMessage').html('�̹� �����ϴ� ���̵��Դϴ�.');
					$('#checkType')
							.attr('class', 'modal-content panel-warning');
				} else if (result == -1) {
					$('#checkMessage').html('�����ͺ��̽� �����Դϴ�.');
					$('#checkType')
							.attr('class', 'modal-content panel-warning');
				} else if (result == -2) {
					$('#checkMessage').html('���̵� �Է��ϼ���.');
					$('#checkType')
							.attr('class', 'modal-content panel-warning');
				}
				$('#checkModal').modal("show");
			}
		})
	}
</script>
</head>
<body>
	<!-- �׺���̼� ���� -->
	<div class="wrapper">
		<div class="header no-print">
			<div class="topbar">
				<div class="container">
					<ul class="loginbar pull-right">
						<li><a href="join.jsp">ȸ������</a></li>
						<li class="topbar-devider"></li>
						<li><a href="login.jsp">�α���</a></li>
					</ul>
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
							<li><a href="vote.jsp">��ǥ</a></li>
							<li><a href="bbslist">�Խ���</a></li>
							<li><a href="./recvMemo">����</a>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">ȸ������</h1>
			</div>
		</div>
		<div class="container-fluid content">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<form method="post" action="./registerInput">
					<table class="table table-bordered table-hover"
						style="text-align: center; border: 1px soild #ddddd">
						<thead>
							<tr>
								<th colspan="3" style="background-color: white"><div class="reg-header">
                    <h2>ȸ������</h2>
                    <p>������ �̹� �ִ� ��쿡�� <a href="login.jsp" class="color-green">�α���</a>���ּ���. </p>
                    <p>������ �ϳ��� ���� �� �ֽ��ϴ�.</p>                   
                </div></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 120px;"><h5>���̵�</h5></td>
								<td><input class="form-control" type="text" id="ID"
									name="ID" placeholder="���̵�" maxLength="20"></td>
								<td style="width: 110px;"><button class="btn btn-primary"
										onclick="registerCheckFunction();" type="button">�ߺ�Ȯ��</button>
							</tr>
							<tr>
								<td style="width: 120px;"><h5>��й�ȣ</h5></td>
								<td colspan="2"><input class="form-control" type="password"
									onkeyup="passwordCheckFunction();" id="password"
									name="password" maxLength="20" placeholder="��й�ȣ"></td>
							</tr>
							<tr>
								<td style="width: 120px;"><h5>��й�ȣ Ȯ��</h5></td>
								<td colspan="2"><input class="form-control" type="password"
									onkeyup="passwordCheckFunction();" id="password2"
									name="password2" maxLength="20" placeholder="��й�ȣ ���Է�"></td>
							</tr>
							<tr>
								<td style="width: 120px;"><h5>�̸�</h5></td>
								<td colspan="2"><input class="form-control" type="text"
									id="name" name="name" maxLength="20" placeholder="�̸�"></td>
							</tr>
							<tr>
								<td style="width: 120px;"><h5>�й�</h5></td>
								<td colspan="2"><input class="form-control" type="text"
									id="studentID" name="studentID" maxLength="20" placeholder="�й�"></td>
							</tr>
							<tr>
								<td style="width: 120px;"><h5>�������</h5></td>
								<td colspan="2"><input class="form-control" type="text"
									id="brithday" name="birthday" maxLength="20" placeholder="�������"></td>
							</tr>
							<tr>
								<td style="width: 120px;"><h5>����</h5></td>
								<td colspan="2">
									<div class="form-group"
										style="text-align: center; margin: 0 auto;">
										<div class="btn-group" data-toggle="buttons">
											<label class="btn btn-primary active"> <input
												type="radio" name="gender" autocomplete="off" value="����"
												checked>����
											</label> <label class="btn btn-primary"> <input type="radio"
												name="gender" autocomplete="off" value="����">����
											</label>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td style="width: 120px"><h5>�г�</h5></td>
								<td colspan="3">
									<div class="form-group">
										<select class="form-control" name="grade">
											<option>1�г�</option>
											<option>2�г�</option>
											<option>3�г�</option>
											<option>4�г�</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td style="width: 120px"><h5>�к�</h5></td>
								<td colspan="3">
									<div class="form-group">
										<select class="form-control" name="major">
											<option>���а�</option>
											<option>�ι��к�</option>
											<option>�����а�</option>
											<option>���ǰ�</option>
											<option>��ȸ���к�</option>
											<option>�濵�к�</option>
											<option>�������к�</option>
											<option>Ư��������</option>
											<option>������к�</option>
											<option>���ƽþƾ�ȭ�к�</option>
											<option>�������ȭ�а�</option>
											<option>�����к�</option>
											<option>�ڿ����к�</option>
											<option>����ȯ���к�</option>
											<option>��Ȱ���к�</option>
											<option>��ǻ���������к�</option>
											<option>����������ڰ��к�</option>
											<option>�̵�����������а�</option>
											<option>����������</option>
											<option>�ǰ�����</option>
											<option>��ȣ����</option>
											<option>������</option>
											<option>���д���</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td style="width: 110px;"><h5>Phone Number</h5></td>
								<td colspan="2"><input class="form-control" type="text"
									id="phoneNumber" name="phoneNumber" maxLength="20"
									placeholder="�޴��� ��ȣ"></td>
							</tr>
							<tr>
								<td style="width: 110px;"><h5>E-mail</h5></td>
								<td colspan="2"><input class="form-control" type="email"
									id="mailAddress" name="mailAddress" maxLength="30"
									placeholder="�̸���"></td>
							</tr>
							<tr>
								<td style="text-align: left" colspan="3"><h5
										style="color: red;" id="passwordCheckMessage"></h5> <input
									class="btn btn-primary pull-right" type="submit" value="�����ϱ�"></td>
							</tr>
						</tbody>
					</table>
				</form>
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
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content panel-info">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Ȯ�� �޽���</h4>
					</div>
					<div class="modal-body" id="checkMessage"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Ȯ��</button>
					</div>
				</div>
			</div>
		</div>
	</div>
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