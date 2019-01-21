<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<style>
  .img_link {
  cursor:pointer;
  width: 20px;
  }
</style>
<title>IT 개발 통합관리 서비스</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/JavaScript">
	$(document).ready(function() {
  	$("#group1").show();
  	$("#group2").hide();
  	$("#group3").hide();
  	$("#group4").hide();
  	$("#group5").hide();
  	$("#group6").hide();
  	
  	$("#item_count").val(1);
	});
	
	function items_up() {
	  var count = Number($("#item_count").val()) + 1;
	  
	  if(count<7) {		// count: 1 ~ 6 만 항목가능
	  $("#group"+count).show();
	  
	 		$("#item_count").val(count);
		} else {
		  alert("더이상 항목 수를 늘릴 수 없습니다.");
		  
		  return;
		}
	}
	
	function items_down() {
	  var count = Number($("#item_count").val());
	  if(count > 1)	{		// count: 1 ~ 6 만 항목가능
	  	$("#items"+count).val("");
	  	$("#items_state"+count).val("진행예정").prop("selected", true);
	    $("#group"+count).hide();
	  
			count = count - 1;
	  	$("#item_count").val(count);
	  } else {
	    alert("더이상 항목 수를 줄일 수 없습니다.");
	    
	    return;
	  }
	}
	
	function on_submit() {
		var count = Number($("#item_count").val());
		var items = '';
		var items_state = '';
		var cnt = 1;
		
		for(var index=0; index<count; index++) { // index 0, 1, 2, 3 ,,,, count = 4
		  if(count!=cnt) { 
				items += $("#items"+cnt).val()+",";
				cnt = cnt + 1;
		  } else {
		    items += $("#items"+cnt).val();
		    cnt = 1;
		  }
		}
		
		for(index=0; index<count; index++) { // index 0, 1, 2, 3 ,,,, count = 4
		  if(count!=cnt) { 
		    items_state += $("#items_state"+cnt).val()+",";
				cnt = cnt + 1;
		  } else {
		    items_state += $("#items_state"+cnt).val();
		  }
		}
/* 	var journal_state = $("#journal_state").val();
	console.log("journal_state: " + journal_state); */

		$("#items").val(items);
		$("#items_state").val(items_state);
		
		var title = $("#title").val();
		var items_state1 = $("#items_state1").val();
		var seqno = $("#seqno").val();
		
		if(title.length < 1) {
		  alert("제목을 입력해주세요.");
		  $("#title").focus();
		  
		  return ;
		}
		if(items_state1 == null) {
		  alert("최소 1개의 항목을 입력해주세요.");
		  $("#items_state1").focus();
		  
		  return ;
		}
		if(seqno.length < 1) {
		  alert("순서를 입력해주세요.");
		  $("#seqno").focus();
		  
		  return ;
		} else {
			$("#frm").submit();
		}
	}
</script>

</head> 

<body>
<DIV class='container' style='width: 100%; padding-top: 5%;'>
<jsp:include page="/menu/top.jsp" flush='false' /> 
 <DIV class='title_box'>개발일지 등록 Test v0.1</DIV>
  <DIV style='padding-bottom: 15px;'></DIV>
<DIV class='content'>     

  <ASIDE style='float: left;'>
    <A href='../index.jsp'>HOME</A>
    <span style='font-size: 1.2em;'>></span>
      <A href='../projectschedule/list_by_state_paging.do'>프로젝트일정</A>
    <span style='font-size: 1.2em;'>></span>
      <A href='./list.jsp?projectscheduleno=${param.projectscheduleno}&project_name=${param.project_name}'>${param.project_name}</A>     
    <span style='font-size: 1.2em;'>></span>
      <A href='./create.do'>등록</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='javascript:location.reload();'>등록</A>
     <span class='menu_divide' >│</span>
    <A href='./list.jsp?projectscheduleno=${param.projectscheduleno}&project_name=${param.project_name}'>목록</A>
  </ASIDE> 

  <DIV class='menu_line' style='clear: both; padding-top: 5px;'></DIV>
  <DIV style='padding-bottom: 10px;'></DIV>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' id='frm' method='POST' action='./create.do' class="form-horizontal">
    <input type='hidden' name='items' id='items' value=''>
    <input type='hidden' name='items_state' id='items_state' value=''>
    <input type='hidden' name='item_count' id='item_count' value='1'>
    <input type='hidden' name='projectscheduleno' id='projectscheduleno' value='${param.projectscheduleno}'> <!-- 임시값 -->
 <!--  임시값    
  <input type='hidden' name='employeeno' id='employeeno' value='1'>
       -->
       
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">분야</label>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='field' id='field'>
            <option>개발</option>
            <option>설계</option>
            <option>디자인</option>
            <option>보안</option>
           </select>
        </div> 
        <label for="title" class="col-md-8 control-label"></label>
      </div>           
       
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">제목</label>
        <div class="col-md-8">
          <input type='text' class="form-control input" name='title' id='title' value='공지사항 개발' required="required" style='width: 80%; font-size: 13px; '>
        </div>
        <label for="title" class="col-md-2 control-label"></label>
      </div>   
      
      <div class="form-group" id="group1">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">항목 1</label>
        <div class="col-md-3">
          <input type='text' class="form-control input" name='items1' id='items1' value='' required="required" style='width: 100%; font-size: 14px; '>
        </div>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='items_state1' id='items_state1' disabled>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
           </select>
        </div> 
        <label for="title" class="col-md-5 control-label"></label>
      </div>     

      <div class="form-group" id="group2">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">항목 2</label>
        <div class="col-md-3">
          <input type='text' class="form-control input" name='items2' id='items2' value='' required="required" style='width: 100%; font-size: 14px; '>
        </div>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='items_state2' id='items_state2' disabled>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
           </select>
        </div>        
        <label for="title" class="col-md-5 control-label"></label>
      </div>     
      
      <div class="form-group" id="group3">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">항목 3</label>
        <div class="col-md-3">
          <input type='text' class="form-control input" name='items3' id='items3' value='' required="required" style='width: 100%; font-size: 14px; '>
        </div>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='items_state3' id='items_state3' disabled>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
           </select>
        </div>        
        <label for="title" class="col-md-5 control-label"></label>
      </div>     
      
      <div class="form-group" id="group4">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">항목 4</label>
        <div class="col-md-3">
          <input type='text' class="form-control input" name='items4' id='items4' value='' required="required" style='width: 100%; font-size: 14px; '>
        </div>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='items_state4' id='items_state4' disabled>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
           </select>
        </div>        
        <label for="title" class="col-md-5 control-label"></label>
      </div>         
      
      <div class="form-group" id="group5">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">항목 5</label>
        <div class="col-md-3">
          <input type='text' class="form-control input" name='items5' id='items5' value='' required="required" style='width: 100%; font-size: 14px; '>
        </div>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='items_state5' id='items_state5' disabled>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
           </select>
        </div>        
        <label for="title" class="col-md-5 control-label"></label>
      </div>      
                                    
      <div class="form-group" id="group6">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">항목 6</label>
        <div class="col-md-3">
          <input type='text' class="form-control input" name='items6' id='items6' value='' required="required" style='width: 100%; font-size: 14px; '>
        </div>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='items_state6' id='items_state6' disabled>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
           </select>
        </div>        
        <label for="title" class="col-md-5 control-label"></label>
      </div>     
  
      <div class="form-group">   
        <label for="title" class="col-md-2 control-label"></label>
        <div class="col-md-1">
           <img src="./images/up.jpg" class="img_link" title="항목늘리기"  onclick="items_up();"/>
           <img src="./images/down.jpg" class="img_link" title="항목없애기" onclick="items_down();"/>    
        </div>
        <label for="title" class="col-md-9 control-label"></label>
      </div>   
<!--        
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">전체상황</label>
        <div class="col-md-2">
          <select class="form-control" style='width: 70%; font-size: 14px; height: 34px;' name='journal_state' id='journal_state' disabled>
            <option>담당미지정</option>
            <option>진행예정</option>
            <option>진행중</option>
            <option>진행완료</option>
            <option>버그픽스중</option>
           </select>
        </div>
        <label for="title" class="col-md-8 control-label"></label>
      </div> 
        -->
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">순서</label>
        <div class="col-md-1">
          <input type='number' min="1" step="1" class="form-control input" name='seqno' id='seqno' value='1' required="required" style='width: 80%; font-size: 14px;'>
        </div>
        <label for="title" class="col-md-9 control-label"></label>
      </div>   

      <div class="form-group">   
        <label for="title" class="col-md-1 control-label"></label>
        <label for="title" class="col-md-1 control-label" style="text-align: right; background-color: #e6f0ff;">작성자</label>
        <div class="col-md-2">
          <input type='text' class="form-control input" name='rname' id='rname' value='마스터' required="required" style='width: 100%; font-size: 14px;' readonly>
        </div>
        <label for="title" class="col-md-8 control-label"></label>
      </div>     
      
      <DIV style='text-align: center;'>
        <input type='button' class="btn btn-default" style='font-size: 14px;' onclick='on_submit();' value='등록' />
        <input type='button' class="btn btn-default" style='font-size: 14px;' onclick="location.href='./list.jsp?projectscheduleno=${param.projectscheduleno}&project_name=${param.project_name}'" value='취소'/>
      </DIV>
    </FORM>
  </DIV>


</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

