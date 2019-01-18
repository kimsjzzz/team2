<%@ page language="java" contentType="text/html; charset=UTF-8" %>
    
    <%
      String root = request.getContextPath();
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
  <title>일정</title>
  
  <style>
    .date_font {
      color:black;
      margin: 5px;
      font-size: 20px;
    }
    .holiday_font {
      color:red;
      margin: 5px;
      font-size: 20px;
    }
    .saturday_font {
    color:blue;
    margin: 5px;
    font-size: 20px;
    }
    
  </style>
  
  <script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/jquery.cookie.js"></script>
  <script>
  function create_schedule(){
    var frm = $('frm');
    var params = 'year=' + $('#year', frm).val() +
                      '&month=' + $('#month', frm).val() +
                      '&day=' + $('#day', frm).val();
    
    $.ajax({
      url: "./create_schedule.do",
      type: "GET",
      cache: false,
      dataType: "json",
      data: params,
      success: function(data){
        var msg = "";
        
      }
    })
  }
  </script>
  
  
 </head>
 <body>
  <% 
  java.util.Calendar cal=java.util.Calendar.getInstance(); //Calendar객체 cal생성
  int currentYear=cal.get(java.util.Calendar.YEAR); //현재 날짜 기억
  int currentMonth=cal.get(java.util.Calendar.MONTH);
  int currentDate=cal.get(java.util.Calendar.DATE);
  String Year=request.getParameter("year"); //나타내고자 하는 날짜
  String Month=request.getParameter("month");
  int year, month, day;
  if(Year == null && Month == null){ //처음 호출했을 때
  year=currentYear;
  month=currentMonth;
  }
  else { //나타내고자 하는 날짜를 숫자로 변환
   year=Integer.parseInt(Year);
   month=Integer.parseInt(Month);
   if(month<0) { month=11; year=year-1; } //1월부터 12월까지 범위 지정.
   if(month>11) { month=0; year=year+1; }
  }
  %>
  <center>
  <table border=0> <!-- 달력 상단 부분, 더 좋은 방법이 없을까? -->
   <tr>
    <td style="font-size: 20px; color:black;" align=left width=300> <!-- 년 도-->
    <a style = "color:black" href="./calendar.jsp?year=<%out.print(year-1);%>&month=<%out.print(month);%>">◀</a>
    <% out.print(year); %>년
    <a style = "color:black" href="./calendar.jsp?year=<%out.print(year+1);%>&month=<%out.print(month);%>">▶</a>
    </td>
    <td style="font-size: 20px; color:black;" align=center width=450> <!-- 월 -->
    <a style = "color:black" href="./calendar.jsp?year=<%out.print(year);%>&month=<%out.print(month-1);%>">◀</a>
    <% out.print(month+1); %>월
    <a style = "color:black" href="./calendar.jsp?year=<%out.print(year);%>&month=<%out.print(month+1);%>">▶</a>
    </td>
    <td style="font-size: 30px; font-style: italic; color:black;" align=right width=300>오늘 날짜: <% out.print(currentYear + "-" + (currentMonth+1) + "-" + currentDate); %></td>   
   </tr>
   
  </table>
  
  <table border=1 cellspacing=0> <!-- 달력 부분 -->
   <tr>
    <td width=150 class = 'date_font' align=center>일</td> <!-- 일=1 -->
    <td width=150 class = 'date_font' align=center>월</td> <!-- 월=2 -->
    <td width=150 class = 'date_font' align=center>화</td> <!-- 화=3 -->
    <td width=150 class = 'date_font' align=center>수</td> <!-- 수=4 -->
    <td width=150 class = 'date_font' align=center>목</td> <!-- 목=5 -->
    <td width=150 class = 'date_font' align=center>금</td> <!-- 금=6 -->
    <td width=150 class = 'date_font' align=center>토</td> <!-- 토=7 -->
   </tr>
   <tr height=110>
   <%
   cal.set(year, month, 1); //현재 날짜를 현재 월의 1일로 설정
   int startDay=cal.get(java.util.Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
   int end=cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
   int br=0; //7일마다 줄 바꾸기
   for(day=0; day<(startDay-1); day++) { //빈칸출력
    out.println("<td>&nbsp;</td>");
    br++;
    if((br%7)==0) {
     out.println("<br>");
    }
   }
   for(day=1; day<=end; day++) { //날짜출력
     if((br%7) == 0){
       out.println("<td><A  class = 'holiday_font' href ='../calendar/create_schedule_by_date.jsp?year="+year+"&month="+(month+1)+"&day="+day+"'>" + day + "</A></td>");
     }else if((br%7)==6){
     out.println("<td><A class = 'saturday_font' href ='../calendar/create_schedule_by_date.jsp?year="+year+"&month="+(month+1)+"&day="+day+"'>" + day + "</A></td>");
     }else{
         out.println("<td><A class = 'date_font' href ='../calendar/create_schedule_by_date.jsp?year="+year+"&month="+(month+1)+"&day="+day+"'>" + day + "</A></td>");
     }
   
   br++;
    if((br%7)==0 && day!=end) {
     out.println("</tr><tr height=110>");
    }
   }
   while((br++)%7!=0) //말일 이후 빈칸출력
    out.println("<td>&nbsp;</td>");
   %>
   </tr>
     </table>
     <div style = 'text-align: center; margin: 10px'>
     <BUTTON type='button' onclick="location.href='./create_schedule.jsp'">일정 등록</BUTTON>
     <BUTTON type='button' onclick="location.href='./list_all_schedule.do'">일정 목록</BUTTON>
   </div>
  </center>
 </body>
</html>

 

 