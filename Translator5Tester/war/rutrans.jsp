<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <!-- make the viewport responsive -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>Free Translator</title>
        <meta name="description" content="Translate for free from English to Russian" />
        <meta name="author" content="B4R brothers4real.com" />
        <link rel="shortcut icon" href="favicon.ico">
		
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<h1 id="big-header">Aнглийский язык? Не проблема!</h1>
	<div class="container">
			<div class="wrapper">
				<div class="output">Введите английское слово:</div>
				
				<form action=http://english2russian4real.appspot.com/translate method=Get class="form-wrapper cf" autocomplete="off"> 
					<input type=Text name="symbol" id="text-input" placeholder="Введите слово..."> 
					<button type=Submit id="submit">вперед!</button>
				</form>
				<br>
				<div class="output">
					<div class="word">
					
					<% 
					String askedWord = (String) request.getAttribute("translation");
					if (askedWord != null){
						out.println(request.getAttribute("unknownWord"));
					}
					%>
					</div>
					<div class="translation">
					<%
					if (askedWord != null){
						out.println(askedWord);
					}
					%>
					
					</div>
				</div>
			</div><!-- /wrapper -->
		<br>
		<footer>Переводите с английского языка!<br>www.english2russian4real.appspot.com<br>Ето легко, это весело и это бесплатно!</footer>	
	</div><!-- /container -->
	<!-- include JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/jquery.fittext.js"></script>
	<script type="text/javascript">
		$("#big-header").fitText(0.8, { minFontSize: '28px', maxFontSize: '48px' });
	</script>
</body>
</html>