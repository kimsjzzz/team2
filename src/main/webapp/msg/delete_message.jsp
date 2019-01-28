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
<DIV class='container'>
<DIV class='content'>

<DIV class='title_line'>쪽지 삭제</DIV>

<DIV class='content' style='width: 500px; text-align: center;'>
  <FORM name='frm' method='POST' action='./delete.do?msgno=${msgVO.msgno }'>
    삭제하면 복구 할 수 없습니다.<br><br>
    정말로 삭제하시겠습니까?<br><br>         
    <input type='hidden' name='mno' value='${memberVO.mno }'>     
        
    <button type="submit">삭제</button>
    <button type="button" onclick="location.href='./receive_msg.do'">취소(목록)</button>

  </FORM>
</DIV>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html>
 
 