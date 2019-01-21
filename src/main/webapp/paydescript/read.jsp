<%@page import="dev.mvc.paydescript.PaydescriptVO"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- JSTL의 포맷기능 제공 el 표현식에 콤마 넣어 출력가능 -->

<%
  DecimalFormat df = new DecimalFormat("#,###,###");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>IT 개발 통합관리 서비스</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>
</head>

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>   
 <DIV class='title_box'>급여 명세서 조회 Test v0.1</DIV>
  <DIV style='padding-bottom: 15px;'></DIV>
  
   <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href="./create.do">등록</A> 
    <span class='menu_divide' >│</span> 
    <A href="./list_by_search_paging.do?&word=${param.word}&nowPage=${param.nowPage}">목록</A>
     <span class='menu_divide' >│</span> 
     <A href='./update.do?paydescriptno=${paydescriptVO.paydescriptno}&word=${param.word}&nowPage=${param.nowPage}'>수정</A>
      <span class='menu_divide' >│</span> 
      <A href='./delete.do?paydescriptno=${paydescriptVO.paydescriptno }&word=${param.word}&nowPage=${param.nowPage}'>삭제</A>
    <!--  <span class='menu_divide' >│</span> -->
  </ASIDE> 
  <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./list_by_search_paging.do?nowPage=1'>급여명세서</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='javascript:location.reload();'>조회</A>
  </ASIDE>
  
  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
  <FORM name='frm' method="get" action='./update.do'>
  <input type="hidden" name="paydescriptno" value="${paydescriptVO.paydescriptno}">


  <div style='text-align: center;'>
  <c:set var='file' value="${paydescriptVO.file1}" />
  <c:choose>
    <c:when test="${paydescriptVO.file1.length() > 0}">
    <IMG src='./storage/${paydescriptVO.file1}' style='width: 15%; height: 10%;'>
    </c:when>
    <c:when test="${paydescriptVO.file1.length() <= 0}">
    <IMG src='./images/null_image.png' style='width: 15%; height: 10%;'><br>
     [등록된 사진 없음]
    </c:when>
  </c:choose>
  </div><br>
  
 <table class="table table-bordered" style='width: 50%; margin: 0px auto;'>
        <colgroup>
        <col style="width: 20%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 30%;"></col>
      </colgroup> 
  <thead>
    <tr>
      <th colspan="4" style='background-color: #AAAAAA; color: white; text-align: center;'>${paydescriptVO.month} 월 급여명세서</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>사원명</th>
      <td style='text-align: center;'>${paydescriptVO.employee_name}</td>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>소속</th>
      <td style='text-align: center;'>${paydescriptVO.department_name}</td>
    </tr>
    <tr>
      <th colspan="2" style='background-color: #AAAAAA; color: white; text-align: center;'>급여내역</th>
      <th colspan="2" style='background-color: #AAAAAA; color: white; text-align: center;'>공제내역</th>
    </tr>
    <tr>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>기본급</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.pay }" pattern="#,###" /> 원</td>
            <th scope="row" style='background-color: #EEFFFF; text-align: center;'>4대보험</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.insurance}" pattern="#,###" /> 원</td>
    </tr>
    <tr>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>팀수당</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.team_pay}" pattern="#,###" /> 원</td>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>종합소득세</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.incometax}" pattern="#,###" /> 원</td>
    </tr>
    <tr>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>상여금</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.insentive}" pattern="#,###" /> 원</td>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'></th>
      <td style='text-align: center;'><fmt:formatNumber value="" pattern="#,###" /> </td>
    </tr>
    <tr>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>만근비</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.perfect_pay}" pattern="#,###" /> 원</td>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'></th>
      <td style='text-align: center;'><fmt:formatNumber value="" pattern="#,###" /> </td>
    </tr>
    <tr>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>급여합계</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.total}" pattern="#,###" /> 원</td>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>공제합계</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.insurance + paydescriptVO.incometax}" pattern="#,###" /> 원</td>
    </tr>
    <tr>
      <th style='background-color: #EEFFFF; text-align: center;'>실수령액</th>
      <td style='text-align: center;'><fmt:formatNumber value="${paydescriptVO.real_pay}" pattern="#,###" /> 원</td>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'></th>
      <td style='text-align: center;'><fmt:formatNumber value="" pattern="#,###" /> </td>
    </tr>    
    <tr>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>해당월</th>
      <td style='text-align: center;'>${paydescriptVO.month} 월</td>
      <th scope="row" style='background-color: #EEFFFF; text-align: center;'>실지급일</th>
      <td style='text-align: center;'>${paydescriptVO.payment_day}</td>
    </tr>    
  </tbody>
</table>

  </FORM>


</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>


   