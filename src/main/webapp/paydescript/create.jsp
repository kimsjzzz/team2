<%@page import="java.text.DecimalFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>IT 개발 통합관리 서비스</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript"
        src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/JavaScript">
function calc() {
  // console.log("calc() 실행됨");

  var pay = Number(uncomma($('#pay').val()));	// id가 pay인 값을 comma를 땐 String타입을 받아 Number로 형변환
  var insentive = Number(uncomma($('#insentive').val()));
  var team_pay = Number(uncomma($('#team_pay').val()));
  var perfect_pay = Number(uncomma($('#perfect_pay').val()));

  var total = pay + insentive + team_pay + perfect_pay;
  var insurance = total * 0.04;
  var incometax = total * 0.1;
  var real_pay = total - (insurance+incometax);

  total =total.toLocaleString('en');		// 단위에 콤마 붙이기
  insurance = insurance.toLocaleString('en');		// 단위에 콤마 붙이기
  incometax = incometax.toLocaleString('en');		// 단위에 콤마 붙이기
  real_pay = real_pay.toLocaleString('en');		// 단위에 콤마 붙이기
  
   $('#total').val(total);
   $('#insurance').val(insurance);
   $('#incometax').val(incometax);
   $('#real_pay').val(real_pay);
}

// 콤마찍기 정규식
function comma(str) {
   str = String(str);

  return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'); 
}

// 콤마풀기 정규식
function uncomma(str) {
  str = String(str);

  return str.replace(/[^\d]+/g, '');
}

function onblur_event(obj) {
   obj.value = comma(uncomma(obj.value));
}

function on_submit() {
  // console.log("submit() 호출");
  var employee_name = $('#employee_name').val();
  var department_name =$('#department_name').val();
  
  var pay = Number(uncomma($('#pay').val()));	// id가 pay인 값을 comma를 땐 String타입을 받아 Number로 형변환
  var insentive = Number(uncomma($('#insentive').val()));
  var team_pay = Number(uncomma($('#team_pay').val()));
  var perfect_pay = Number(uncomma($('#perfect_pay').val()));
  var total = Number(uncomma($('#total').val()));
  var insurance = Number(uncomma($('#insurance').val()));
  var incometax = Number(uncomma($('#incometax').val()));
  var real_pay = Number(uncomma($('#real_pay').val()));
  
  var month = $('#month').val();
  var payment_day = $('#payment_day').val();
  
  if(employee_name.length==0) {
    alert("사원명을 입력해주세요.");
    $('#employee_name').focus();
    return;
  }

  if(department_name.length==0) {
    alert("부서명을 입력해주세요.");
    $('#department_name').focus();
    return;
  }
  if(pay==0) { 
    alert("기본급을 입력해주세요.");
    $('#pay').focus();
    return;
  }
  if(insentive==0) {
    alert("상여금을 입력해주세요.");
    $('#insentive').focus();
    return;
  }
  if(team_pay==0){
    alert("팀수당을 입력해주세요.");
    $('#team_pay').focus();
    return;
  }
  if(total==0) {
    alert("자동계산 버튼을 눌러주세요.")
    return; 
  }
  if(month.length==0){
    alert("월 을 입력해주세요.");
    $('#month').focus();
    return
  }
  if(payment_day.length==0) {
    console.log("payment_day.length: " + payment_day.length);
    alert("실지급일을 입력해주세요.");
    $('#payment_day').focus();
    return;
  }
  else {
  
  $('#pay').val(pay);	// 콤마를 땐 Number타입 전송
  $('#insentive').val(insentive);
  $('#team_pay').val(team_pay);
  $('#perfect_pay').val(perfect_pay);
  $('#total').val(total);
  $('#insurance').val(insurance);
  $('#incometax').val(incometax);
  $('#real_pay').val(real_pay);
  
/*   var file1MF = $('#file1MF').val();
  console.log("file1MF: " + file1MF); */
  
  $('#frm').submit();
  }
}
</script>

</head> 

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 <DIV class='title_box'>급여 명세서 등록 Test v0.1</DIV>
  <DIV style='padding-bottom: 15px;'></DIV>
   <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>┃
    <A href="./create.do">등록</A> |
    <A href="./list_by_search_paging.do?nowPage=1">목록</A> ┃
    <!--  <span class='menu_divide' >│</span> -->
  </ASIDE> 
    <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./list_by_search_paging.do?nowPage=1'>급여명세서</A>
    <span style='font-size: 1.2em;'>></span>
    <A href='./create.do'>등록</A>
  </ASIDE>
  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
 
 <DIV style='margin: 0px auto;'>
 <FORM name='frm' id='frm' method='POST' action='./create.do'
    enctype="multipart/form-data">
 <!-- 임시 데이터 employeeno -->
<!--   <input type='hidden' name='employeeno' id='employeeno' value='2'>  -->

       <div class="form-group" style='width: 100%;'> 
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="filesMF" class="col-md-2 control-label" style='height: 34px; text-align: right; font-size: 16px;'>사진</label>
        <div class="col-md-3">
          <input type="file" class="form-control input" name='file1MF' id='file1MF' size='30' style='font-size: 14px;'>
        </div>
        <label for="title" class="col-md-4 control-label" style='height: 34px;'></label>
      </div>   
      
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>사원명</label>
        <div class="col-md-2" style='text-align: center; height: 34px;' >
          <input type='text' class="form-control input" name='employee_name' id='employee_name' value='왕눈이' required="required" style='width: 100%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>   

      <div class="form-group" style='width: 100%;'> 
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>부서명</label>
        <div class="col-md-2" style='text-align: center; height: 34px;'>
          <!-- <input type='text' class="form-control input" name='department_name' id='department_name' value='개발팀' required="required" style='width: 100%; font-size: 14px;'> -->
          <SELECT class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='department_name' id='department_name'>
            <!-- 반복문 -->
            <c:forEach var="employeeVO" items="${list }">
              <option>${employeeVO.department_name}</option>
            </c:forEach>
          </SELECT>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>   
      
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>기본급</label>
        <div class="col-md-2" style='text-align: center; height: 34px;'>
          <input type='text' class="form-control input" name='pay' id='pay' value=2100000 onkeyup="onblur_event(this)" onkeypress="if(event.keyCode==13) {onblur_event(this);}" required="required" style='width: 100%; font-size: 14px; '>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>   
      
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>상여금</label>
        <div class="col-md-2" style='text-align: center; height: 34px;'>
          <input type='text' class="form-control input" name='insentive' id='insentive' value=700000 onkeyup="onblur_event(this)" onkeypress="if(event.keyCode==13) {onblur_event(this);}" required="required" style='width: 100%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>   
      
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>팀수당</label>
        <div class="col-md-2" style='text-align: center; height: 34px'>
          <input type='text' class="form-control input" name='team_pay' id='team_pay' value=500000 onkeyup="onblur_event(this)" onkeypress="if(event.keyCode==13) {onblur_event(this);}" required="required" style='width: 100%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>   
      
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>만근비</label>
        <div class="col-md-2" style='text-align: center; height: 34px; '>
          <input type='text' class="form-control input" name='perfect_pay' id='perfect_pay' value=200000 onkeyup="onblur_event(this)" onkeypress="if(event.keyCode==13) {onblur_event(this);}" required="required" style='width: 100%; font-size: 14px;'>
        </div> 
        <label for="title" class="col-md-5 control-label" style='text-align: left; height: 34px;'>
        <button type='button' class="btn btn-default" id='btn_calc' onclick='calc()' style='font-size: 14px;'>자동계산</button>
        </label>
        </div> 
  
 
      <div class="form-group" style='width: 100%;' >   
        <label for="title" class="col-md-3 control-label" style='height: 34px;' ></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>총금액</label>
        <div class="col-md-2" style='text-align: center; height: 34px;'>
          <div>
          <input type='text' class="form-control input" name='total' id='total' required="required" style='width: 100%; font-size: 14px;' readonly>
          </div>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>   

      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>4대보험</label>
        <div class="col-md-2" style='text-align: center; height: 34px;'>
          <input type='text' class="form-control input" name='insurance' id='insurance' required="required" style='width: 100%; font-size: 14px;' readonly>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>  
       
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>종합소득세</label>
        <div class="col-md-2" style='text-align: center; height: 34px;'>
          <input type='text' class="form-control input" name='incometax' id='incometax' required="required" style='width: 100%; font-size: 14px;' readonly>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>        
      
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>실수령액</label>
        <div class="col-md-2" style='text-align: center; height: 34px;'>
          <input type='text' class="form-control input" name='real_pay' id='real_pay' required="required" style='width: 100%; font-size: 14px;' readonly>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>        
      
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>월</label>
        <div class="col-md-2" style='text-align: center; height: 34px; '>
          <input type='number' min="1" max="12" class="form-control input" name='month' id='month' value='' required="required" style='width: 100%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>      
        
      <div class="form-group" style='width: 100%;'>   
        <label for="title" class="col-md-3 control-label" style='height: 34px;'></label>
        <label for="title" class="col-md-2 control-label" style='text-align: right; height: 34px;'>실지급일</label>
        <div class="col-md-2" style='text-align: center; height: 34px; '>
          <input type='date' class="form-control input" name='payment_day' id='payment_day' value='' required="required" style='width: 100%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-5 control-label" style='height: 34px;'></label>
      </div>    

       <div style='text-align: center; width: 100%;'>
        <input type='button' class="btn btn-default" id='btn_submit' onclick='on_submit();' value='등록' style='font-size: 14px;'/>
      </div>

      </FORM>
   </DIV>

</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

