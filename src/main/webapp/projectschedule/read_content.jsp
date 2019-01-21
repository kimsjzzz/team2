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
 <DIV class='title_box'>프로젝트 일정 조회 Test v0.1</DIV>
  <DIV style='padding-bottom: 15px;'></DIV>
   <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href="./create.do">등록</A> 
    <span class='menu_divide' >│</span> 
    <A href="list_by_state_paging.do?nowPage=1">목록</A>
     <span class='menu_divide' >│</span> 
     <A href='./update.do?projectscheduleno=${projectscheduleVO.projectscheduleno}'>수정</A>
    <!--  <span class='menu_divide' >│</span> -->
  </ASIDE> 
  <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='list_by_state_paging.do?nowPage=1'>프로젝트일정</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='javascript:location.reload();'>조회</A>
  </ASIDE>
  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
  <FORM name='frm' method="get" action='./update.do'>
  <input type="hidden" name="projectscheduleno" value="${projectscheduleVO.projectscheduleno}">
  
      <div class="form-group">   
        <label for="title" class="col-md-2 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style="text-align: right; background-color: #e6f0ff;">진행상태</label>
        <div class="col-md-7" style='height: 34px;'>
            <c:choose>
              <c:when test="${projectscheduleVO.project_state eq '진행예정'}">
                <span style='background-color: #ffff80; font-weight: bold;'>${projectscheduleVO.project_state}</span>
              </c:when>
              <c:when test="${projectscheduleVO.project_state eq '진행중'}">
                <span style='background-color: #80ff80; font-weight: bold;'>${projectscheduleVO.project_state}</span>
              </c:when>           
              <c:when test="${projectscheduleVO.project_state eq '진행완료'}">
                <span style='background-color: #d9d9d9; font-weight: bold;'>${projectscheduleVO.project_state}</span>
              </c:when>
            </c:choose>
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div>   
        
      <div class="form-group">   
        <label for="title" class="col-md-2 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style="text-align: right; background-color: #e6f0ff;">제목</label>
        <div class="col-md-7" style='height: 34px;'>
            ${projectscheduleVO.project_name}
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div>   
      
      <div class="form-group">   
        <label for="title" class="col-md-2 control-label" style='height: 34px;'></label>
        <label for="content" class="col-md-2 control-label" style="text-align: right; background-color: #e6f0ff;">내용</label>
        <div class="col-md-7" style='height: 34px;'>
            ${projectscheduleVO.project_content}
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div> 
      
      <div class="form-group">   
        <label for="title" class="col-md-2 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style="text-align: right; background-color: #e6f0ff;">작성자</label>
        <div class="col-md-7" style='height: 34px;'>
          ${projectscheduleVO.employee_name}
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div>   

      <div class="form-group">   
        <label for="title" class="col-md-2 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style="text-align: right; background-color: #e6f0ff;">시작일</label>
        <div class="col-md-2" style='height: 34px;'>
          ${projectscheduleVO.project_startdate}
        </div>
        <label for="title" class="col-md-1 control-label" style='text-align: left;'>~</label>
        <label for="title" class="col-md-2 control-label" style="text-align: right; background-color: #e6f0ff;">종료일</label>
        <div class="col-md-2" style='height: 34px;'>
          ${projectscheduleVO.project_enddate}
        </div>
        <label for="title" class="col-md-1 control-label" style='text-align: left; padding-left: 3%;'></label>
      </div>   
  
 
  </FORM>

</DIV> <!-- content END -->
</DIV> <!-- container END -->

</body>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</html>


   