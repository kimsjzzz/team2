<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.time.LocalDate" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "nation.web.tool.Tool" %>
<%@ page import = "javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import= "dev.mvc.schedule.ScheduleProc" %>
<%@ page import= "dev.mvc.schedule.ScheduleVO" %>

<%
int scheduleno = Integer.parseInt(request.getParameter("scheduleno"));
ScheduleProc scheduleProc = new ScheduleProc();
ScheduleVO scheduleVO = scheduleProc.read(scheduleno);

%> --%>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>일정 등록</title> 


<!-- <script type="text/javascript">
  window.onload = function(){
    find('work_startdate').valueAsDate = new Date(); // 현재 날짜 객체를 생성하여 date 태그에 적용
    find('work_enddate').valueAsDate = new Date();  // 현재 날짜 객체를 생성하여 date 태그에 적용
    find('start_time').valueAsDate = new Date(); // 현재 날짜 객체를 생성하여 date 태그에 적용
    find('end_time').valueAsDate = new Date();  // 현재 날짜 객체를 생성하여 date 태그에 적용
  }
  </script> -->

</head> 
<body>
<DIV class='container'>
<DIV class='content'>


<FORM name='frm' method='POST' action='./update_schedule.do'
 enctype="multipart/form-data" class="form-horizontal">
<input type='hidden' name='scheduleno' id='scheduleno' value='${param.scheduleno }'>
  
  <fieldset class='fieldset_no_line' style='width: 90%; margin: 0px auto;'>
    <ul>
      <li class='li_none'>
        <label for='work' class='label_basic'>일정</label>
        <input type='text' name='work' id='work' value='${param.work }'> *
      </li>
        
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 시작 날짜</label>*<br>
        <input type='date' style = 'width: 200px' name='work_startdate' id='work_startdate' value='${param.work_startdate }'>
        <input type='time' style = 'width: 150px' name='start_time' id='start_time' value = '${param.start_time }'>
      </li>            
     
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 종료 날짜</label>*<br>
        <input type='date' style   = 'width: 200px' name='work_enddate' id='work_enddate' value= '${parma.work_enddate }'>
        <input type='time' style = 'width: 150px' name='end_time' id='end_time' value = '${param.end_time }'> 
             </li>
     
     <li class='li_none'>
        <label for='mname' class='label_basic'>공개 여부</label><br>
          <%-- <c:when test = "${scheduleVO.getVisible == 'Y' }"> --%>
            <label>
              <input type='radio' name='visible' id='visible' value='Y' checked="checked"> 전체 공개
            </label>
            <label>
              <input type='radio' name='visible' id='visible' value='N'> 비공개
            </label>
          <%-- </c:when>
          <c:otherwise>
          <label>
              <input type='radio' name='visible' id='visible' value='Y' > 전체 공개
            </label>
            <label>
              <input type='radio' name='visible' id='visible' value='N' checked="checked"> 비공개
            </label>
          </c:otherwise> --%>
     </li>
               
      <li class='li_right'>
        <button type='submit' >일정 수정</button>
        <button type='button' onclick="location.href='./calendar.jsp'">취소</button>
        
      </li>
    </ul>
  </fieldset>
</FORM>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html> 
 
   