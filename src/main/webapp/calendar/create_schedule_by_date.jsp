<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.time.LocalDate" %>

<%
int year = Integer.parseInt(request.getParameter("year"));
int month = Integer.parseInt(request.getParameter("month")); 
int day = Integer.parseInt(request.getParameter("day"));
String start_date_value = "";

if(month < 10){
  start_date_value = (String)(year+"-0"+month);
} else{
  start_date_value = (String)(year+"-"+month);
}

if(day < 10) {
  start_date_value = (String)(start_date_value + "-0" + day);
}else {
  start_date_value = (String)(start_date_value + "-" + day);
}

System.out.println("선택 날짜: "+ start_date_value + "/ 실제 날짜"+ LocalDate.now());

%>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>일정 등록</title> 


</head> 
<body>
<DIV class='container'>
<DIV class='content'>


<FORM name='frm' method='POST' action='./create_schedule.do'>
  <fieldset class='fieldset_no_line' style='width: 90%; margin: 0px auto;'>
    <ul>
      <li class='li_none'>
        <label for='work' class='label_basic'>일정</label>
        <input type='text' name='work' id='work' > *
      </li>
        
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 시작 날짜</label>*<br>
        <input type='date' style = 'width: 200px' name='work_startdate' id='work_startdate' value=<%=start_date_value %>>
        <input type='time' style = 'width: 150px' name='start_time' id='start_time' value = '00:00'>
      </li>            
     
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 종료 날짜</label>*<br>
        <input type='date' style = 'width: 200px' name='work_enddate' id='work_enddate' >
        <input type='time' style = 'width: 150px' name='end_time' id='end_time' value = '00:00'>
     </li>
     
     <li class='li_none'>
        <label for='visible'>공개 여부</label>
       <select name='visible'>
         <option value='Y' >전체공개</option>
         <option value='N' selected="selected">비공개</option>
       </select>
     </li>
     
      <li class='li_right'>
        <button type='submit'>일정 등록</button>
        <button type='button' onclick="location.href='./calendar.jsp'">취소</button>
        
      </li>
    </ul>
  </fieldset>
</FORM>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html> 
   