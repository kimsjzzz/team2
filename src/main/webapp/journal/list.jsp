<%@ page contentType="text/html; charset=UTF-8" %>

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
$(function() {
  list();  // 모든 카테고리 목록
});

	function on_button(journalno) {
	  var journalno = journalno;
	
	  
	};

// http://localhost:9090/intranet/journal/list.jsp?projectscheduleno=1
// 목록
 function list() {
	$.ajax({
	  url: "./list_by_schedule_json.do",
	  type: "get",
	  cache: false,
	  dataType:"json",
	  data: "projectscheduleno="+${param.projectscheduleno},
	  // Ajax 통신 성공, JSP 정상 처리
	  success: function(rdata) {	// callback 함수
	    var panel = '';
      for(index=0; index < rdata.length; index++) {
        if(rdata[index].employee_name==null) {
        panel += "<TR>";
        // panel += "<TD style='text-align: center ;'>"+rdata[index].categoryno+"</TD>";
        panel += "<TH scope='row' style='text-align: center ;'>"+rdata[index].seqno+"</TH>";
        panel += "<TH scope='row' style='text-align: center ;'>"+rdata[index].field+"</TH>";
        panel += "<td style='vertical-align: middle; text-align: center;'><A href='./read.do?projectscheduleno="+rdata[index].projectscheduleno+"&project_name="+rdata[index].project_name+"&journalno="+rdata[index].journalno+"'>"+rdata[index].title+"</A></TD>";
        panel += "<td style='vertical-align: middle; text-align: center;'><input type='button' class='btn btn-default' onclick='on_button("+rdata[index].journalno+");' id='btn_button1' value='담당하기' style='font-size: 12px;'/></TD>";
        panel += "<td style='vertical-align: middle; text-align: center;'>"+rdata[index].rname+"</TD>";
        panel += "<td style='vertical-align: middle; text-align: center;'>"; 
        panel += "  <A href=\"javascript:seqnoUp("+rdata[index].journalno+")\"><IMG src='./images/up.jpg' title='우선 순위 높임' style='width: 16px;'></A>";
        panel += "  <A href=\"javascript:seqnoDown("+rdata[index].journalno+")\"><IMG src='./images/down.jpg' title='우선 순위 감소' style='width: 16px;'></A>"; 
        panel += "  <A href=./update.do?journalno="+rdata[index].journalno+"&project_name="+rdata[index].project_name+"><IMG src='./images/update.png' title='수정'></A>";  
        panel += "  <A href=\"javascript:deleteOne("+rdata[index].journalno+")\"><IMG src='./images/delete.png' title='삭제'></A>";
        panel += "</TD>";
        panel += "</TR>";
        } else {
          panel += "<TH scope='row' style='text-align: center ;'>"+rdata[index].seqno+"</TH>";
          panel += "<TH scope='row' style='text-align: center ;'>"+rdata[index].field+"</TH>";
          panel += "<td style='vertical-align: middle; text-align: center;'>"+rdata[index].title+"</TD>";
          panel += "<td style='vertical-align: middle; text-align: center;'>"+rdata[index].employee_name+"</TD>";
          panel += "<td style='vertical-align: middle; text-align: center;'>"+rdata[index].rname+"</TD>";
          panel += "<td style='vertical-align: middle; text-align: center;'>"; 
          panel += "  <A href=\"javascript:seqnoUp("+rdata[index].journalno+")\"><IMG src='./images/up.jpg' title='우선 순위 높임' style='width: 16px;'></A>";
          panel += "  <A href=\"javascript:seqnoDown("+rdata[index].journalno+")\"><IMG src='./images/down.jpg' title='우선 순위 감소' style='width: 16px;'></A>"; 
          panel += "  <A href=\"javascript:update("+rdata[index].journalno+")\"><IMG src='./images/update.png' title='수정'></A>";  
          panel += "  <A href=\"javascript:deleteOne("+rdata[index].journalno+")\"><IMG src='./images/delete.png' title='삭제'></A>";
          panel += "</TD>";
          panel += "</TR>";          
        }
        // alert(panel);
        // return;
        $('#tbody_panel').empty();
        $('#tbody_panel').append(panel);
      }
	  }
	  });
}
</script>

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
  
  
  <!-- ****************개발일지 관련 테이블 시작********************* -->
  
  <TABLE class="table table-striped table-hover" style='width: 100%; margin: 0px auto;'>
  <colgroup>
<%--     <col style='width: 10%;'/>
    <col style='width: 10%;'/> --%>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 30%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 20%;'/>
 
  </colgroup>
  <thead class="thead-light" style='background-color: #bfbfbf'>  
  <TR>
<!--     <TH style='text-align: center ;'>categrpno</TH>
    <TH style='text-align: center ;'>seqno</TH> -->
    <TH style='text-align: center ;'>번호</TH>
    <TH style='text-align: center ;'>분야</TH>
    <TH style='text-align: center ;'>제목</TH>
    <TH style='text-align: center ;'>담당자명</TH>
    <TH style='text-align: center ;'>작성자</TH>
    <TH style='text-align: center ;'>기타</TH>
    
  </TR>
  </thead>
 
  <tbody id='tbody_panel'>
  </tbody>
  
</TABLE>

  <!-- ****************개발일지 관련 테이블 종료********************* -->
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

