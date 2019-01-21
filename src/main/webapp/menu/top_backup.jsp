<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ page import="java.util.ArrayList" %>
<%@ page import="nation.web.tool.Tool" %>


<%
String root = request.getContextPath();
// out.println("root: " + root);
%>
 
<!-- 화면 상단 메뉴 --> 
<DIV class='top_menu'
        style="background-image: url('<%=root %>/menu/images/top3.png')" >
  <DIV class='top_menu_label'></DIV>
  <NAV class='top_menu_list'>
    <A class='menu_link' href='<%=root %>/index.jsp' style='padding-left: 10px;'>HOME</A><span class='top_menu1'> | </span>
    <A class='menu_link' href='<%=root %>/paydescript/list_by_search_paging.do?nowPage=1'>급여명세서</A><span class='top_menu1'> | </span>
    <A class='menu_link' href='<%=root %>/projectschedule/list_by_state_paging.do?nowPage=1' style='padding-left: 10px;'>프로젝트일정</A><span class='top_menu1'> | </span>
    
 
  </NAV>
</DIV>