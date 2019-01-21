<%@ page contentType="text/html; charset=UTF-8" %>
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

<script type="text/JavaScript">

</script>

</head> 

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' /> 
 <DIV class='title_box'>개발일지 조회 Test v0.1</DIV>
  <DIV style='padding-bottom: 15px;'></DIV>
<DIV class='content'>     

  <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
      <A href='../projectschedule/list_by_state_paging.do'>프로젝트일정</A>
    <span style='font-size: 1.2em;'>></span>
      <A href='./list.jsp?projectscheduleno=${param.projectscheduleno}&project_name=${param.project_name}'>${param.project_name}</A>     
    <span style='font-size: 1.2em;'>></span>
      <A href='javascript:location.reload();'>조회</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
     <span class='menu_divide' >│</span>
    <A href='./list.jsp?projectscheduleno=${param.projectscheduleno}&project_name=${param.project_name}'>목록</A>
     <span class='menu_divide' >│</span>
    <A href='./update.do'>수정</A>
  </ASIDE> 

   <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
  <FORM name='frm' method="get" action='./update.do'>
  <input type="hidden" name="journalno" value="${journalVO.journalno}">
  
      <div class="form-group">   
        <label for="title" class="col-md-4 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">분야</label>
        <div class="col-md-6" style='height: 34px;'>
          ${journalVO.field}
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div>   
        
      <div class="form-group">   
        <label for="title" class="col-md-4 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">제목</label>
        <div class="col-md-6" style='height: 34px;'>
            ${journalVO.title}
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div>   
      
      <c:forEach var="items_list" items="${items_list}" varStatus="status">
      <div class="form-group">   
        <label for="title" class="col-md-4 control-label" style='height: 34px;'></label>
        <label for="content" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">항목</label>
        <div class="col-md-1" style='height: 34px;'>
            ${items_list} 
        </div>
        <div class="col-md-2" style='height: 34px;'>
            <c:choose>
            <c:when test="${itemsState_list[status.index] eq '진행예정'}">
              <span style='background-color: #ffff80; font-weight: bold;'>${itemsState_list[status.index]}</span>
            </c:when>
            <c:when test="${itemsState_list[status.index] eq '진행중'}">
              <span style='background-color: #80ff80; font-weight: bold;'>${itemsState_list[status.index]}</span>
            </c:when>
            <c:when test="${itemsState_list[status.index] eq '진행완료'}">
              <span style='background-color: #d9d9d9; font-weight: bold;'>${itemsState_list[status.index]}</span>
            </c:when>
            </c:choose>        
        </div>        
      <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
      </div> 
      </c:forEach>
            
      <div class="form-group">   
        <label for="title" class="col-md-4 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">담당자</label>
        <div class="col-md-6" style='height: 34px;'>
          <c:choose>
            <c:when test="${journalVO.employee_name eq '마스터'}">
              미지정
            </c:when>
          </c:choose>
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div>   
      
      <div class="form-group">   
        <label for="title" class="col-md-4 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">작성자</label>
        <div class="col-md-6" style='height: 34px;'>
          ${journalVO.rname}
        </div>
        <label for="title" class="col-md-1 control-label" style='height: 34px;'></label>
      </div>   
  
 
  </FORM>


</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

