<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Free Translator</title>
</head>
<body>
	<h1>Aнглийский язык - не проблема!</h1>
    введите английское слово или фразу до 300 знаков:
	<br>
	<br>
	<form action=http://localhost:8080/Russian4Real/translate2 method=Get> 
		<input type=Text name=symbol> 
		<input type=Submit value="вперед!"> 
	</form>
	<br>

	<% 
	String askedWord = (String) request.getAttribute("translation");
	if (askedWord != null){
		out.println(request.getAttribute("unknownWord"));
	}
	%>
	<br>
	<%
	if (askedWord != null){
		out.println(askedWord);
	}
	%>
	
	<br>
	<h3>Переводите с английского языка - www.english2russian4real.appspot.com - это легко, это весело и это бесплатно!</h3>	
</body>
</html>