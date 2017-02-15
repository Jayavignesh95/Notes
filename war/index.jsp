<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notes</title>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<h1 style="text-align: center">Notes</h1>
	<div>
		<form action="add">
			<label for="name">Name</label> <input type="text" name="uname"><br>
			<label for="notes">Notes </label>
			<textarea rows="5" cols="15" name="notes"></textarea>
			<br> <input type="submit" value="Add notes">
</form>
</div>
</body>
</html>