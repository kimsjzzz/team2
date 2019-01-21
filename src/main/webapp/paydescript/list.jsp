<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
$(function(){

});
</script>

<script type="text/javascript">
</script>
</head>

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>     
 <DIV class='title_box'>급여 명세서 목록 Test v0.1</DIV>
 <DIV style='padding-bottom: 15px;'></DIV>
 
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href="./create.do">등록</A> 
    <span class='menu_divide' >│</span> 
    <A href="./list.do">목록</A>
    <!--  <span class='menu_divide' >│</span> -->
  </ASIDE> 
     <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./list.do'>급여명세서</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./list.do'>목록</A>
  </ASIDE>
  <DIV class='menu_line' style='clear: both;'></DIV>
  
  <div style='width: 90%; padding-left: 10%;'>
    <table class="table table-striped table-hover" style='width: 100%; margin: 0px auto;'>
       <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 20%;"></col>
        
      </colgroup> 
      
      <%-- table 컬럼 --%>
      <thead class="thead-light" style='background-color: #bfbfbf'>
        <tr>
          <th scope="col" style='text-align: center;'>NO</th>
          <th scope="col" style='text-align: center;'>월</th>
          <th scope="col" style='text-align: center;'>사원명</th>
          <th scope="col" style='text-align: center;'>소속부서</th>
          <th scope="col" style='text-align: center;'>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="paydescriptVO" items="${list }">
          <tr> 
            <th scope="row" style='vertical-align: middle; text-align: center;'>${paydescriptVO.paydescriptno}</th>
            <td style='vertical-align: middle; text-align: center;'>${paydescriptVO.month}</td>
            <td style='vertical-align: middle; text-align: center;'>
              <a href="./read.do?paydescriptno=${paydescriptVO.paydescriptno}">${paydescriptVO.employee_name}</a> 
            </td> 
            <td style='vertical-align: middle; text-align: center;'>${paydescriptVO.department_name}</td>
            <td style='vertical-align: middle; text-align: center;'>
              <a href="./update.do?paydescriptno=${paydescriptVO.paydescriptno}"><img src="./images/update.png" title="수정" border='0'/></a> /
              <a href="./delete.do?paydescriptno=${paydescriptVO.paydescriptno}"><img src="./images/delete.png" title="삭제"  border='0'/></a>
 
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>
  
  


</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
    

