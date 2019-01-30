<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<DIV class='container' style="width: 100%">
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style="padding-top: 10%;">

<ASIDE style='float: right;'>
  <A href='./receive_msg.do'>받은쪽지함</A>
  <span class='menu_divide' >│</span> 
  <A href='./create.do'>답변</A>
  <span class='menu_divide' >│</span> 
  <A href='./delete.do?msgno=${msgVO.msgno }'>삭제</A>
</ASIDE> 
<DIV class='menu_line' style='clear: both;'></DIV> 

${msgVO.content}
  
<DIV class='menu_line' style='clear: both;'></DIV> 

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
</html>
 
 
