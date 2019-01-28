<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/javascript" src="../js/jquery.cookie.js"></script>

<title>Insert title here</title>
</head>
<body>
<DIV class='container' style="width: 100%">
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style="padding-top: 10%;">
  <ASIDE style='float: right;'>
    <A href="./list.do">전체쪽지함</A>
    <span class='menu_divide' >│</span> 
    <A href="./receive_msg.do?receiver=1">받은쪽지함</A>
    <%-- <A href="./receive_msg.do?receiver=${employeeVO.employeeno }">받은쪽지함</A> --%>
    <span class='menu_divide' >│</span> 
    <A href="./send_msg.do?sender=1">보낸쪽지함</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?sender=1'>보내기</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>  
         
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 35%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 5%;"></col>
        <col style="width: 5%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>번호</th>
          <th>내용</th>
          <th>보낸사람</th>
          <th>받는사람</th>
          <th>보낸날짜</th>
          <th>받은날짜</th>
          <th>기타</th>
          <th>열람</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="msgVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle;'>${msgVO.msgno}</td>
            <td style='vertical-align: middle;'>
              <a href="./read.do?msgno=${msgVO.msgno}&receiver=${msgVO.receiver}">${msgVO.content}</a> 
            </td> 
            <td style='vertical-align: middle;'>${msgVO.sender}</td>
            <td style='vertical-align: middle;'>${msgVO.receiver}</td>
            <td style='vertical-align: middle;'>${msgVO.rdate.substring(0, 10)}</td>
            <td style='vertical-align: middle;'>${msgVO.vdate.substring(0, 10)}</td>
            <td style='vertical-align: middle;'>
              <a href="./update.do?msgno=${msgVO.msgno}"><img src="../css/images/update.png" title="수정" border='0'/></a>
              <a href="./delete.do?msgno=${msgVO.msgno}"><img src="../css/images/delete.png" title="삭제"  border='0'/></a>
            </td>
            <td style='vertical-align: middle;'>${msgVO.vcnt}</td>
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
 
 
