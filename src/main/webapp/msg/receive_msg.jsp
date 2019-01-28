<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<DIV class='container' style="width: 100%">
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style="padding-top: 10%;">
  <ASIDE style='float: left'>
    <span class='' >${sessionScope.mname }님의 쪽지함</span>
    <!-- <span class=''>님의 쪽지함</span> -->
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="./list.do">전체쪽지함</A>
    <span class='menu_divide' >│</span> 
    <A href="./receive_msg.do">받은쪽지함 (${receive_count }건)</A>
    <%-- <A href="./receive_msg.do?receiver=${employeeVO.employeeno }">받은쪽지함</A> --%>
    <span class='menu_divide' >│</span> 
    <A href="./send_msg.do">보낸쪽지함 (${send_count }건)</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>보내기</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>  
         
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 50%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
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
          <th>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="msgVO" items="${receive_msg }">
          <tr> 
            <td style='vertical-align: middle;'>${msgVO.msgno}</td>
            <td style='vertical-align: middle;'>
              <a href="./read.do?msgno=${msgVO.msgno}&receiver=${msgVO.receiver}">${msgVO.content}</a> 
            </td> 
            <td style='vertical-align: middle;'>${msgVO.sender}</td> <!-- ${msgVO.sender} -->
            <td style='vertical-align: middle;'>${msgVO.receiver}</td><!-- ${msgVO.receiver} -->
            <td style='vertical-align: middle;'>${msgVO.rdate.substring(0, 10)}</td>
            <td style='vertical-align: middle;'>
              <%-- <a href="./update.do?msgno=${msgVO.msgno}"><img src="../css/images/update.png" title="수정" border='0'/></a> --%>
              <a href="./delete.do?msgno=${msgVO.msgno}"><img src="../css/images/delete.png" title="삭제"  border='0'/></a>
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
 
 
