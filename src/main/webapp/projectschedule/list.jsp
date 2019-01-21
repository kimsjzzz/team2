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

<script type="text/javascript">
$(function(){

});

function delete_cancel() {
  $('#panel_delete').hide();
}

// 삭제 폼
function deleteOne(projectscheduleno) {
  $('#panel_delete').show();
  
  $.ajax({
    url: "./delete.do", 
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    // data: $('#frm').serialize(),  // 보내는 데이터
    data: 'projectscheduleno='+projectscheduleno,
    success: function(rdata) {
      // alert(rdata);
      var frm_delete = $('#frm_delete');
      $('#projectscheduleno', frm_delete).val(projectscheduleno);
      
      var str = '';
      // 소속된 카테고리 갯수를 출력 예정
      /*
      if (rdata.count_by_categrp > 0) {
        str = '<span style="color: #FF0000;">&apos;'+ rdata.name + '&apos; 카테고리에 [' + rdata.count_by_categrp + '] 건의 데이터가 등록되어있습니다.</span><br>';
        str += '카테고리에 등록된 데이터를 삭제해야 카테고리 그룹 삭제가 가능합니다.<br>';
        str += '<button type="button" onclick="delete_category_by_categrp('+categrpno+')">카테고리 삭제</button>';
        str += '&nbsp;<button type="button" onclick="create_update_cancel();">취소</button>';
      } else {
      */
        str = '<span style="background-color: #ff4d4d; color: white; font-weight: bold;">[' + rdata.project_name + "]</span> 프로젝트 일정을 삭제하시겠습니까?<br>";
        str += '<span style="background-color: #ff4d4d; color: white; font-weight: bold;">삭제하면 복구 할 수 없습니다.</span><br><br>'
        str += '<button type="submit" id="submit">삭제</button>';
        str += '&nbsp;<button type="button" onclick="delete_cancel();">취소</button>'; 
     /* }  */
      $('#msg_delete').html(str);
    },
    error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
      msg += '<strong>status</strong><br>'+status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';

/*         var msg = '알림<br><br>';
      msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
      msg += '예상 종료 시간: 16:00'; */
     
      $('#main_panel').html(msg);
      $('#main_panel').show();
    }
   });
}

function seqnoUp(projectscheduleno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', './seqnoUp.do');
  $('#projectscheduleno', frm_seqno).val(projectscheduleno);
  frm_seqno.submit();
}

function seqnoDown(projectscheduleno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', './seqnoDown.do');
  $('#projectscheduleno', frm_seqno).val(projectscheduleno);
  frm_seqno.submit();
}
</script>

<script type="text/javascript">
</script>
</head>

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>     
 <DIV class='title_box'>프로젝트일정 목록 Test v0.1</DIV>
  <DIV style='padding-bottom: 15px;'></DIV>
<FORM name='frm2' id='frm2' method="GET" action="./list_by_state.do"> 
  <ASIDE style='float: right;'>
   <!-- selectBox -->
   <SELECT name='project_state' id='project_state'>
    <OPTION value='all' selected="selected">전체</OPTION>
    <OPTION value='Developing'>진행중</OPTION>
    <OPTION value='Expect'>진행예정</OPTION>
    <OPTION value='Complete'>진행완료</OPTION>
   </SELECT>
   <button type='submit'>검색</button>

   <!-- selectBox End -->
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href="./create.do">등록</A> 
    <span class='menu_divide' >│</span> 
    <A href="./list.do">목록</A>
    <!--  <span class='menu_divide' >│</span> -->
  </ASIDE> 
</FORM>
     
     <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./list.do'>프로젝트일정</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./list.do'>목록</A>
  </ASIDE>
  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
  
  <!-- 우선 순위 증가 감소 폼 -->
  <FORM name='frm_seqno' id='frm_seqno' method='post' action=''>
    <input type='hidden' name='projectscheduleno' id='projectscheduleno' value=''>
  </FORM>
    
   <!-- 삭제 폼 -->
  <DIV id='panel_delete' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_delete' id='frm_delete' method='POST' 
                action='./delete.do'> 
      <input type='hidden' name='projectscheduleno' id='projectscheduleno' value=''>
      <DIV id='msg_delete' style='margin: 20px auto; width:50%;'></DIV>
      <br>
    </FORM>
  </DIV>
  
  <div style='width: 90%; padding-left: 10%;'>
    <table class="table table-striped table-hover" style='width: 100%; margin: 0px auto;'>
       <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
      </colgroup> 
      
      <%-- table 컬럼 --%>
      <thead class="thead-light" style='background-color: #bfbfbf'>
        <tr>
          <th scope="col" style='text-align: center;'>NO</th>
          <th scope="col" style='text-align: center;'>상태</th>
          <th scope="col" style='text-align: center;'>제목</th>
          <th scope="col" style='text-align: center;'>기간</th>
          <th scope="col" style='text-align: center;'>작성자</th>
          <th scope="col" style='text-align: center;'>기타</th>
        </tr>
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="projectscheduleVO" items="${list }">
          <tr> 
            <th scope="row" style='vertical-align: middle; text-align: center;'>${projectscheduleVO.seqno}</th>
            <td style='vertical-align: middle; text-align: center;'>
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
            </td>
            <td style='vertical-align: middle; text-align: center;'>
              <a href="./read.do?projectscheduleno=${projectscheduleVO.projectscheduleno}">${projectscheduleVO.project_name}</a> 
            </td> 
            <td style='vertical-align: middle; text-align: center;'>${projectscheduleVO.project_startdate} ~ ${projectscheduleVO.project_enddate}</td>
            <td style='vertical-align: middle; text-align: center;'>${projectscheduleVO.employee_name}</td>
            <td style='vertical-align: middle; text-align: center;'>
              <A href="javascript:seqnoUp(${projectscheduleVO.projectscheduleno})"><img src="./images/up.jpg" title="높힘" border='0' style='width: 16px;'/></a>
              <A href="javascript:seqnoDown(${projectscheduleVO.projectscheduleno})"><img src="./images/down.jpg" title="낮춤" border='0' style='width: 16px;'/></a>
              <a href="./update.do?projectscheduleno=${projectscheduleVO.projectscheduleno}"><img src="./images/update.png" title="수정" border='0' style='width: 16px;'/></a>
              <A href="javascript:deleteOne(${projectscheduleVO.projectscheduleno})"><img src="./images/delete.png" title="삭제"  border='0' style='width: 16px;'/></a>
              <a href="./read_content.do?projectscheduleno=${projectscheduleVO.projectscheduleno}"><img src="./images/glass.png" title="자세히보기"  border='0' style='width: 16px;'/></a>
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
    

