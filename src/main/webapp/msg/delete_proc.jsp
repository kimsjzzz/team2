<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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

<DIV class='title_line'>알림</DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 1 }">
          <LI class='li_none'>쪽지 삭제에 실패했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>쪽지 삭제에 성공했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
      <br>
        <button type='button' onclick="location.href='./list.do'">전체 목록</button>
        <button type='button' onclick="location.href='./receive_msg.do?receiver=${param.receiver }'">목록</button>
      </LI>
     </UL>
  </fieldset>

</DIV>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html>
 
 
