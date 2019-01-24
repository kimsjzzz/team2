<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.time.LocalDate" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "nation.web.tool.Tool" %>
<%@ page import = "javax.servlet.http.HttpSession" %>
<%@ page import = "java.util.Date" %>

<%
/* int year = Integer.parseInt(request.getParameter("year"));
int month = Integer.parseInt(request.getParameter("month")); 
int day = Integer.parseInt(request.getParameter("day"));  */

/* SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat stm = new SimpleDateFormat("HH:mm");

Date Basic_Start_Time = Tool.getDate8("00:00");
 */
%>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>일정 등록</title> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/javascript">
  $(function(){
 
  });
</script>

</head> 
<body>
<DIV class='container'>
<DIV class='content'>


<FORM name='frm' method='POST' action='./create_schedule.do'>
  <fieldset class='fieldset_no_line' style='width: 90%; margin: 0px auto;'>
    <ul>
      <li class='li_none'>
        <label for='work' class='label_basic'>일정</label>
        <input type='text' name='work' id='work' required="required"  autofocus="autofocus"> *
      </li>
        
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 시작 날짜</label>*<br>
        <input type='date' style = 'width: 200px' name='work_startdate' id='work_startdate' required="required" value=<%= LocalDate.now() %>>
        <input type='time' style = 'width: 150px' name='start_time' id='start_time' value = '00:00'>
      </li>            
     
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 종료 날짜</label>*<br>
        <input type='date' style = 'width: 200px' name='work_enddate' id='work_enddate' required="required" >
        <input type='time' style = 'width: 150px' name='end_time' id='end_time' value = '00:00'>
     </li>
     
     <li class='li_none'>
        <label for='mname' class='label_basic'>공개 여부</label><br>
          <label for='visible'>공개 여부</label>
       <select name='visible'>
         <option value='Y' >전체공개</option>
         <option value='N' selected="selected">비공개</option>
       </select>
     </li>
               
      <li class='li_right'>
        <button type='submit' >일정 등록</button>
        <button type='button' onclick="location.href='./calendar.jsp'">취소</button>
        
      </li>
    </ul>
  </fieldset>
</FORM>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html> 
 
   