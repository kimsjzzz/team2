<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회의실 목록</title>

<style>
    .list_center {
      text-align: center;
    }    
    
    .list_left {
      text-align: left;
    }    
    
     /* 팝업 윈도우 */
  .popup1 {
    line-height: 40px;
    margin-top: 20px;
    margin-bottom: 20px;
    padding: 10px;
    position: absolute;
    top: 25%;
    left: 35%;
    width: 36%;
    background-color: #cc99ff;
    opacity: 1.0;
    text-align: center;
    color: #FFFFFF;
  }
  </style>

<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script type="text/javascript">
  $(function() {
    action_cancel();
    $('#panel_create').hide();
    $('#panel_delete').hide();
    $('#panel_update').hide();
    
    
    list_room();

  });
  
  // 회의실 목록
  function list_room() {
    $.ajax({
      url: "./list_room.do", // 요청을 보낼주소
      type: "GET",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      // data: "categrpno=" + categrpno, 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
 
        for(index=0; index < rdata.length; index++) {
          panel += "<TR>";
          panel += "<TD class = 'list_center'>"+rdata[index].roomno+"</TD>";
          panel += "<TD class = 'list_center'>"+rdata[index].roomname+"</TD>";
          panel += "<TD class = 'list_left'>"+rdata[index].locate+"</TD>";
          panel += "<TD class = 'list_center'>";
          panel += "  <A href=\"javascript:update_room("+rdata[index].roomno+")\">수정</A>";
          panel += "  <A href=\"javascript:delete_room("+rdata[index].roomno+")\">삭제</A>";
          panel += "</TD>";
          panel += "</TR>";
        }
        // alert(panel);
        // return;
        $('#tbody_panel').empty();
        $('#tbody_panel').append(panel);
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
      }
    });
  }
  

 // 회의실 등록 처리
 function create_submit() {
   $.ajax({
     url: "./create_room.do", // 요청을 보낼주소
     type: "POST",  // or get
     cache: false,
     dataType: "json", // 응답 데이터 형식, or json
     data: $('#frm_create').serialize(), 
     // Ajax 통신 성공, JSP 정상 처리
     success: function(rdata) { // callback 함수
       alert("회의실 정보가 등록되었습니다.")
               
       action_cancel();
       
       list_room();  // 전체 카테고리 목록
       
       $('#main_panel').show();
       
     },
     // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
     error: function(request, status, error) { // callback 함수
       var panel = '';
       panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
       panel += '  ERROR<br><br>';
       panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
       panel += '  <strong>error</strong><br>'+error + '<hr>';
       panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
       panel += "</DIV>";
       
       $('#main_panel').html(panel);
       $('#main_panel').show();

     }
   });
 }
  
  // 일정 수정
   function update_room(roomno) {
    // alert('update_schedule('+scheduleno+')');
    // return;
     $('#panel_update').show();
     $('#panel_delete').hide();
     $('#panel_create').hide();
     
    
    $.ajax({
      url: "./update_room.do", // 요청을 보낼주소
      type: "GET",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: 'roomno=' + roomno,
      success: function(rdata) { // callback 함수
        var frm_update = $('#frm_update');
        // $('#categrpno', frm_update).val(rdata.categrpno).prop("selected", true);
        // SELECT tag
        $('#roomno', frm_update).val(rdata.roomno);
        $('#roomname', frm_update).val(rdata.roomname);
        $('#locate', frm_update).val(rdata.locate);
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 350px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();

      }
    });
  } 

  // 수정 처리
  function update_submit() {
    $.ajax({
      url: "./update_room_json.do", // 요청을 보낼주소
      type: "POST",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_update').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        alert("회의실 정보가 수정되었습니다.")
                
        action_cancel();
        
        list_room();  // 전체 카테고리 목록
        
        $('#main_panel').show();
        
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
 
      }
    });
  }

  function action_cancel() {
    $('#panel_update').hide();
    $('#panel_delete').hide();
    $('#panel_create').hide();

  }
  
  function delete_room(roomno) {
    $('#panel_update').hide();
    $('#panel_create').hide();
    $('#panel_delete').show();
    
    $.ajax({
      url: "./delete_room.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: 'roomno=' +roomno,  // $('#frm').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var frm_delete = $('#frm_delete');
        $('#roomno', frm_delete).val(rdata.roomno);
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 350px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();

      }
    });
  }

  // 삭제 처리
  function delete_room_submit() {
    $.ajax({
      url: "./delete_room.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_delete').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        alert("회의실이 삭제되었습니다.")
        
        action_cancel();
        
        list_room();  // 전체 카테고리 목록
        
        $('#main_panel').show();
        
        // $('#frm_create')[0].reset(); // id가 frm_create인 첫번째폼을 reset
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
 
      }
    });
  }
  
  function create_room() {
    $('#frm_create')[0].reset();
    
    $('#panel_update').hide();
    $('#panel_delete').hide();
    $('#panel_create').show();

  }
  
  
</script>
</head>

<body>
  <DIV class='container' style='width: 90%;'>
    <DIV class='content'>
      
<!-- 일정 삭제 폼 -->
      <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #FFAAAA; width: 100%; text-align: center;'>
    <FORM name='frm_delete' id='frm_delete'>
      <input type='hidden' name='roomno' id='roomno' value=''>
      
      <span id='category_title'></span> 삭제하시겠습니까?
      삭제하면 복구 할 수 없습니다.
      <button type="button" id='submit' onclick="delete_room_submit()">삭제</button>
      <button type="button" onclick="action_cancel()">취소</button>
    </FORM>
  </DIV>
      
      
<!-- 회의실 수정 폼 -->
  <DIV id='panel_update' style='padding: 10px 0px 10px 0px; background-color: #BBBB; width: 100%; text-align: left;'>  
    <FORM name='frm_update' id='frm_update' method='POST' action='./update_room.do'>
      <input type='hidden' name='roomno' id='roomno' value=''>
 
      <fieldset class='fieldset_no_line' style='width: 90%; margin: 0px auto;'>
      <ul>
        <li class='li_none'>
        <label for='roomname' class='label_basic'>회의실 이름</label>
        <input type='text' name='roomname' id='roomname' required="required"  autofocus="autofocus"> *
      </li>
      
      <li class='li_none'>
        <label for='locate' class='label_basic'>회의실 위치</label>
        <input type='text' name='locate' id='locate' required="required"> *
      </li>
               
      <li class='li_right'>
        <button type="button"  id='submit' onclick="update_submit();">저장</button>
        <button type="button" onclick="action_cancel()">취소</button>
      </li>
    </ul>
  </fieldset>
  </FORM>
</DIV>

<!-- 회의실 등록 폼 -->
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #BBBB; width: 100%; text-align: left;'>  
    <FORM name='frm_create' id='frm_create' method='POST' action='./create_room.do'>
 
      <fieldset class='fieldset_no_line' style='width: 90%; margin: 0px auto;'>
      <ul>
        <li class='li_none'>
        <label for='roomname' class='label_basic'>회의실 이름</label>
        <input type='text' name='roomname' id='roomname' required="required"  autofocus="autofocus"> *
      </li>
      
      <li class='li_none'>
        <label for='locate' class='label_basic'>회의실 위치</label>
        <input type='text' name='locate' id='locate' required="required"> *
      </li>
               
      <li class='li_right'>
        <button type="button"  id='submit' onclick="create_submit();">회의실 등록</button>
        <button type="button" onclick="action_cancel()">취소</button>
      </li>
    </ul>
  </fieldset>
  </FORM>
</DIV>

      <div class='menu_line'></div>
      <table class="table table-striped" style='width: 100%;'>
        <colgroup>
          <col style='width: 10%;' />
          <col style='width: 35%;' />
          <col style='width: 40%;' />
          <col style='width: 15%;' />
          
        </colgroup>
        <TR>
          <TH class='list_center'>회의실 번호</TH>
          <TH class='list_center'>회의실 이름</TH>
          <TH class='list_center'>위치</TH>
          <TH class='list_center'>기타</TH>
          
        </TR>
        
        <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
        </tbody>
      </TABLE>

      <DIV>
        <button type='button'
          onclick="javascript:create_room()">회의실 등록</button>
        <!-- <button type='button' onclick="location.href='./calendar.jsp'">캘린더</button> -->
      </DIV>

    </DIV>
    <!-- content END -->
  </DIV>
  <!-- container END -->
</body>

</html>
