<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>IT 개발 통합관리 서비스</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    
</head> 
<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
    <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./list_by_search_paging.do?nowPage=1'>급여명세서</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='javascript:location.reload();'>수정</A>
  </ASIDE>
  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
<DIV class='title_line'>알림</DIV>
 <DIV style='padding-bottom: 15px;'></DIV>

<DIV class='message' style='background-color: #e6e6e6;'>
  <fieldset class='fieldset_basic' style='background-color: #e6e6e6;'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 1 }">
          <LI class='li_none'>[${param.department_name}] ${param.employee_name} 의 급여명세서를 수정했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>급여명세서 수정에 실패했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <button type='button' class="btn btn-default" style='font-size: 14px;' onclick="location.href='./read.do?paydescriptno=${param.paydescriptno}&word=${param.word}&nowPage=${param.nowPage}' ">수정 확인</button>
        <button type='button' class="btn btn-default" style='font-size: 14px;' onclick="location.href='./list_by_search_paging.do?word=${param.word}&nowPage=${param.nowPage}'">목록</button>
      </LI>
     </UL>
  </fieldset>

</DIV>

</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

   