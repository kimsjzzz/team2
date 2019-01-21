<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

</head>

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>     
 <DIV class='title_box'>프로젝트 진행상황 Test v0.1</DIV>
 <DIV style='padding-bottom: 15px;'></DIV>
  <FORM name='frm2' id='frm2' method="GET" action="./list_by_state_paging.do" style='padding-top: 1%;'> 
  <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
      <A href='../projectschedule/list_by_state_paging.do?nowPage=1'>프로젝트일정</A>
    <span style='font-size: 1.2em;'>></span>
      <A href='javascript:location.reload();'>${param.project_name}</A>     
    <span style='font-size: 1.2em;'>></span>
      <A href='javascript:location.reload();'> 진행상황</A>
  </ASIDE>

  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?projectscheduleno=${param.projectscheduleno}&project_name=${param.project_name}'>등록</A>
     <span class='menu_divide' >│</span>
    <A href='javascript:location.reload();'>목록</A>
  </ASIDE> 
</FORM>
  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
<table class="table table-bordered table-hover">
  <colgroup>
    <col style='width: 14%;'/>
    <col style='width: 14%;'/>
    <col style='width: 12%;'/>
    <col style='width: 12%;'/>
    <col style='width: 12%;'/>
    <col style='width: 12%;'/>
    <col style='width: 12%;'/>
    <col style='width: 12%;'/>
  </colgroup>
  <thead>
    <tr>
      <th class="table-info" scope="col" colspan="8" style='text-align: center;'>${param.project_name}</th>
    </tr>
    <tr>
      <th class="table-secondary" scope="col" style='text-align: center; border-bottom: 1px solid #AAAAAA;'>개발요소</th>
      <th class="table-active" scope="col" style='text-align: center;'>항목</th>
      <th class="table-active" scope="col" style='text-align: center;'>개발예정</th>
      <th class="table-active" scope="col" style='text-align: center;'>개발중</th>
      <th class="table-active" scope="col" style='text-align: center;'>디버깅</th>
      <th class="table-active" scope="col" style='text-align: center;'>α-테스트</th>
      <th class="table-active" scope="col" style='text-align: center;'>β-테스트</th>
      <th class="table-active" scope="col" style='text-align: center;'>개발완료</th>
    </tr>
  </thead>
  <tbody>

    <c:forEach var="managementVO" items="${list }" varStatus="n" >
        <tr>
          <th class="table-secondary" scope="row" rowspan='${array_count_items[n.index]}' style='text-align: center; vertical-align: middle; border-bottom: 1px solid #AAAAAA;'><a href="#">${managementVO.title}</a></th>
        </tr>
          <c:set var="items_split" value="${fn:split(managementVO.items,',')}" />
          <c:set var="items_State_split" value="${fn:split(managementVO.items_state,',')}" />
          <c:forEach var="items" items="${items_split }" varStatus="status">
          <tr>
            <td class="table-active" style='text-align: center; font-size: 10px;'>${items}</td>
            <c:choose>
              <c:when test="${items_State_split[status.index] eq '개발예정'}">
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>      
                <td style='border-right: hidden;'></td>          
                <td style='border-right: hidden;'></td>          
                <td style='border-right: hidden;'></td>          
                <td style='border-right: hidden;'></td>          
                <td></td>          
              </c:when>
              <c:when test="${items_State_split[status.index] eq '개발중'}">
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>      
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>           
                <td style='border-right: hidden;'></td>          
                <td style='border-right: hidden;'></td>          
                <td style='border-right: hidden;'></td>          
                <td></td>                         
              </c:when>
              <c:when test="${items_State_split[status.index] eq '디버깅'}">
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>      
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>     
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>         
                <td style='border-right: hidden;'></td>          
                <td style='border-right: hidden;'></td>          
                <td></td>                        
              </c:when>
              <c:when test="${items_State_split[status.index] eq 'a테스트'}">
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>      
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>           
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>            
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>          
                <td style='border-right: hidden;'></td>          
                <td></td>                         
              </c:when>
              <c:when test="${items_State_split[status.index] eq 'b테스트'}">
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>      
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>         
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>            
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>          
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>           
                <td></td>                         
              </c:when>
              <c:when test="${items_State_split[status.index] eq '개발완료'}">
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>      
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>          
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>           
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>          
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>         
                <td class="table-success" style='border-bottom: 1px solid #AAAAAA;'></td>                        
              </c:when>
            </c:choose>
            
          </tr>        
        </c:forEach>
  </c:forEach>
  </tbody>
</table>  

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>