<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
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
<FORM name='frm' method='POST' action='./create.do'>
    <input type='hidden' name='sender' id='sender' value='${sessionScope.employeeno }'>
    받는 사람 : 
    <select name='receiver' id='receiver''>
<!--  <option value='1'>1 아로미</option>
        <option value='2'>2 왕눈이</option>
        <option value='3'>3 투투</option> -->
      <c:forEach var="employeeVO"  items="${list }">
        <option value='${employeeVO.employeeno }'>${employeeVO.employeeno } | ${employeeVO.name_employee }</option>
      </c:forEach>
    </select>
    <div class="form-group">   
      <label for="content" class="col-md-1 control-label">내용</label>
      <div class="col-md-11">
        <textarea class="form-control input-lg" name='content' id='content'  rows='10'>내용</textarea>
      </div>
    </div>
    <DIV style='text-align: right;'>
      <button type="submit">보내기</button>
      <button type="button" onclick="location.href='./list.do">취소[목록]</button>
    </DIV>
</FORM>


<!-- employee_select 구현 -->
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html>
 
 
