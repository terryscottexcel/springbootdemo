<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome You Login</h1>
<form action="login" method="post">

<table>
	<tr>
		<td>UserName</td>
		<td><input type="text" id="uname" name="username" placeholder="Input User Name"/></td>
	</tr>
	<tr>
		<td>UserPassword</td>
		<td><input type="password" id="upasswd" name="userpassword" placeholder="Input User Password"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="submit" id="submit" name="submit" placeholder="Input User Password" value="Login"/></td>
	</tr>
</table>
</form>
</body>
</html>