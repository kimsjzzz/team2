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

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/JavaScript">

function on_submit() {
  var project_name = $('#project_name').val();
  
  var project_startdate = $('#project_startdate').val();
  var start_year = project_startdate.substring(0,3);
  var start_month = project_startdate.substring(5,7);
  var start_date = project_startdate.substring(8,10);
  
  var project_enddate = $('#project_enddate').val();
  var end_year = project_enddate.substring(0,3);
  var end_month = project_enddate.substring(5,7);
  var end_date = project_enddate.substring(8,10);
  
  //-을 구분자로 연,월,일로 잘라내어 배열로 반환
  var startArray = project_startdate.split('-'); // 2018, 07 , 01
  var endArray = project_enddate.split('-');  // 2018 , 09, 01
  
  //배열에 담겨있는 연,월,일을 사용해서 Date 객체 생성
  var start_date = new Date(startArray[0], startArray[1], startArray[2]);
  var end_date = new Date(endArray[0], endArray[1], endArray[2]);
  
  //날짜를 숫자형태의 날짜 정보로 변환하여 비교한다.
  if(start_date.getTime() > end_date.getTime()) {
      alert("종료날짜보다 시작날짜가 작아야합니다.");
      return;
  }
  if(project_name.length<1) {
    alert("제목은 최소 한글자 이상 적어주세요.");
    $('#project_name').focus();
    return;
  }
  if(project_startdate.length<1) {
    alert("시작일을 선택 해주세요.");
    $('#project_startdate').focus();
    return;
  }
  if(project_enddate.length<1) {
    alert("종료일을 선택해주세요.");
    $('#project_enddate').focus();
    return;
  }
  else {
  $('#frm').submit();
  }
}
</script>

<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('project_content', {
      width :'800px',  // 입력창의 넓이, 넓이는 config.js 에서 % 로 제어
      height : '370px'  // 입력창의 높이 
    }
    );  // <TEXTAREA>태그 id 값
  };
</script>

</head> 

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>     
 <DIV class='title_box'>프로젝트일정 등록 Test v0.1</DIV>
  <DIV style='padding-bottom: 15px;'></DIV>
<DIV class='content'>     

  <ASIDE style='float: left;'>
      <A href='./list_by_state_paging.do?nowPage=1'>프로젝트 일정</A>  
      >
      <A href='./create.do'> 등록</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
     <span class='menu_divide' >│</span>
    <A href='./list_by_state_paging.do?nowPage=1'>목록</A>
  </ASIDE> 

  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' id='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
 <!--  임시값    
  <input type='hidden' name='employeeno' id='employeeno' value='1'>
       -->
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">제목</label>
        <div class="col-md-8">
          <input type='text' class="form-control input" name='project_name' id='project_name' value='통합 도서 관리 시스템' required="required" style='width: 80%; font-size: 14px; '>
        </div>
        <label for="title" class="col-md-2 control-label"></label>
      </div>   
      
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="content" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">내용</label>
        <div class="col-md-8">
          <textarea class="form-control input" name='project_content' id='project_content'  rows='10'>통합 도서 관리 시스템 구축</textarea>
        </div>
        <label for="title" class="col-md-2 control-label"></label>
      </div> 
      
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">작성자</label>
        <div class="col-md-2">
          <input type='text' class="form-control input" name='employee_name' id='employee_name' value='마스터' required="required" style='width: 80%; font-size: 14px;' readonly>
        </div>
        <label for="title" class="col-md-8 control-label"></label>
      </div>   

      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">시작일</label>
        <div class="col-md-2">
          <input type='date' class="form-control input" name='project_startdate' id='project_startdate' value='' required="required" style='width: 100%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-1 control-label" style='text-align: center;'>~</label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">종료일</label>
        <div class="col-md-2">
          <input type='date' class="form-control input" name='project_enddate' id='project_enddate' value='' required="required" style='width: 100%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-4 control-label" style='text-align: left; padding-left: 3%;'></label>
      </div>   

      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">진행상황</label>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='project_state' id='project_state'>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
           </select>
        </div>
        <label for="title" class="col-md-8 control-label"></label>
      </div>  
      
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">순서</label>
        <div class="col-md-1">
          <input type='number' min="1" step="1" class="form-control input" name='seqno' id='seqno' value='1' required="required" style='width: 80%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-9 control-label"></label>
      </div>   

      <DIV style='text-align: center;'>
        <input type='button' class="btn btn-default" style='font-size: 14px;' id='btn_submit' onclick='on_submit();' value='등록' />
        <input type='button' class="btn btn-default" style='font-size: 14px;' onclick="location.href='./list.do'" value='취소'/>
      </DIV>
    </FORM>
  </DIV>
</DIV>

</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

