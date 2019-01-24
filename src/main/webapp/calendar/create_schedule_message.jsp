<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

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
          <LI>새로운 일정을 등록했습니다.</LI>
        </c:when>
        <c:when test="${param.count == 2 }">
          <LI>등록하고자 하시는 날짜에 문제가 발생하여 </LI>
          <LI>일정을 등록하지 못하였습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI>새로운 일정 등록에 실패했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI >
        <br>
        <button type='button' onclick="location.href='./create_schedule.do'"> 일정 등록</button>
        <BUTTON type='button' onclick="location.href='./list_all_schedule.do'">일정 목록</BUTTON>
        <button type='button' onclick="location.href='./calendar.jsp'">캘린더</button>
      </LI>
     </UL>
  </fieldset>

</DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>

</html> 