<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>일정 목록</title>

<style>
    .list_center {
      text-align: center;
    }    
    
    .list_left {
      text-align: left;
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
    $('#panel_update').hide();
    $('#panel_delete').hide();
    
    list();

  });
  
  // 일정 목록
  function list() {
    $.ajax({
      url: "./list_all_schedule_json.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      // data: "categrpno=" + categrpno, 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
 
        for(index=0; index < rdata.length; index++) {
          panel += "<TR>";
          panel += "<TD class = 'list_center'>"+(index+1)+"</TD>";
          panel += "<TD class = 'list_center'>"+rdata[index].scheduleno+"</TD>";
          panel += "<TD class = 'list_center'>"+rdata[index].employeeno+"</TD>";
          panel += "<TD class = 'list_left'>"+rdata[index].work+"</TD>";
          panel += "<TD  class = 'list_center'>"+rdata[index].work_startdate+ " " + rdata[index].start_time +"</TD>";
          panel += "<TD  class = 'list_center'>"+rdata[index].work_enddate+ " " + rdata[index].end_time +"</TD>";
          if(rdata[index].visible == 'Y'){
            panel += "<TD class = 'list_center'>전체공개</TD>";
          } else {
            panel += "<TD class = 'list_center'>비공개</TD>";
          }
          /* panel += "<TD class = 'list_center'>"+rdata[index].visible+"</TD>"; */
          panel += "<TD class = 'list_center'>";
          panel += "  <A href=\"javascript:update_schedule("+rdata[index].scheduleno+")\">수정</A>";  
          panel += "  <A href=\"javascript:delete_schedule("+rdata[index].scheduleno+")\">삭제</A>";
          /* panel += "  <A href=\"javascript:seqnoUp("+rdata[index].categoryno+")\"><IMG src='./images/up.png' title='우선 순위 높임' style='width: 20px;'></A>";
          panel += "  <A href=\"javascript:seqnoDown("+rdata[index].categoryno+")\"><IMG src='./images/down.png' title='우선 순위 감소' style='width: 20px;'></A>";  */
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
  
  // 일정 수정
   function update_schedule(scheduleno) {
    $('#panel_update').show();
    
    $.ajax({
      url: "./update_schedule.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: 'scheduleno=' + scheduleno,
      success: function(rdata) { // callback 함수
        var frm_update = $('#frm_update');
        // $('#categrpno', frm_update).val(rdata.categrpno).prop("selected", true);
        // SELECT tag
        $('#scheduleno', frm_update).val(rdata.scheduleno);
        $('#employeeno', frm_update).val(rdata.employeeno);
        $('#work', frm_update).val(rdata.work);
        $('#work_startdate', frm_update).val(rdata.work_startdate);
        $('#work_enddate', frm_update).val(rdata.work_enddate);
        $('#start_time', frm_update).val(rdata.start_time);
        $('#end_time', frm_update).val(rdata.end_time);
        $('#visible', frm_update).val(rdata.visible);        
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
      url: "./update_schedule_json.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_update').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 250px;'>";
        panel += '  알림<br>';
        for(index=0; index < rdata.msgs.length; index++) {
          panel += rdata.msgs[index]+'<br>';
        }
        panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'>닫기</button>";
        panel += "</DIV>";
        
        action_cancel();
        
        list();  // 전체 카테고리 목록
        
        $('#main_panel').html(panel);
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

  }
  
  function delete_schedule(scheduleno) {
    $('#panel_update').hide();
    $('#panel_delete').show();
    
    $.ajax({
      url: "./delete_schedule.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: 'scheduleno=' +scheduleno,  // $('#frm').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var frm_delete = $('#frm_delete');
        $('#scheduleno', frm_delete).val(rdata.scheduleno);        
        $('#work').html(rdata.work); // 카테고리 이름
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
  function delete_schedule_submit() {
    $.ajax({
      url: "./delete_schedule.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_delete').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 250px;'>";
        panel += '  알림<br>';
        for(index=0; index < rdata.msgs.length; index++) {
          panel += rdata.msgs[index]+'<br>';
        }
        panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'>닫기</button>";
        panel += "</DIV>";
        
        action_cancel();
        
        list();  // 전체 카테고리 목록
        
        $('#main_panel').html(panel);
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

  
  
</script>
</head>

<body>
  <DIV class='container' style='width: 90%;'>
    <DIV class='content'>

      <ASIDE style='float: left;'>
        <A href='./calendar.jsp'>캘린더</A>
      </ASIDE>
      
      <!-- 삭제 폼 -->
      <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #FFAAAA; width: 100%; text-align: center;'>
    <FORM name='frm_delete' id='frm_delete'>
      <input type='hidden' name='scheduleno' id='scheduleno' value=''>
      
      <span id='category_title'></span> 삭제하시겠습니까?
      삭제하면 복구 할 수 없습니다.
      <button type="button" id='submit' onclick="delete_schedule_submit()">삭제</button>
      <button type="button" onclick="action_cancel()">취소</button>
    </FORM>
  </DIV>
      
      
<!-- 일정 수정 폼 -->
  <DIV id='panel_update' style='padding: 10px 0px 10px 0px; background-color: #DDDDDD; width: 100%; text-align: left;'>  
    <FORM name='frm_update' id='frm_update' method='POST' action='./update_schedule.do'>
      <input type='hidden' name='scheduleno' id='scheduleno' value=''> 
 
      <fieldset class='fieldset_no_line' style='width: 90%; margin: 0px auto;'>
      <ul>
        <li class='li_none'>
          <label for='work' class='label_basic'>일정</label>
          <input type='text' name='work' id='work' required="required"> *
        </li>
        
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 시작 날짜</label>*<br>
        <input type='date' style = 'width: 200px' name='work_startdate' id='work_startdate' required="required">
        <input type='time' style = 'width: 150px' name='start_time' id='start_time' required="required">
      </li>            
      
      <li class='li_none'>
        <label for='mname' class='label_basic'>일정 종료 날짜</label>*<br>
        <input type='date' style   = 'width: 200px' name='work_enddate' id='work_enddate' required="required">
        <input type='time' style = 'width: 150px' name='end_time' id='end_time' required="required"> 
      </li>
     
      <li class='li_none'>
        <label for='mname' class='label_basic'>공개 여부</label><br>
        <%-- <c:when test = "${scheduleVO.getVisible == 'Y' }"> --%>
          <label>
            <input type='radio' name='visible' id='visible' value='Y' checked="checked"> 전체 공개
          </label>
          <label>
            <input type='radio' name='visible' id='visible' value='N'> 비공개
          </label>
          <%-- </c:when>
          <c:otherwise>
          <label>
              <input type='radio' name='visible' id='visible' value='Y' > 전체 공개
            </label>
            <label>
              <input type='radio' name='visible' id='visible' value='N' checked="checked"> 비공개
            </label>
          </c:otherwise> --%>
      </li>
               
      <li class='li_right'>
        <button type="button"  id='submit' onclick="update_submit();">저장</button>
        <button type="button" onclick="action_cancel()">취소</button>
      </li>
    </ul>
  </fieldset>
  </FORM>
</DIV>

      <div class='menu_line'></div>
      <table class="table table-striped" style='width: 100%;'>
        <colgroup>
          <col style='width: 5%;' />
          <col style='width: 5%;' />
          <col style='width: 5%;' />
          <col style='width: 15%;' />
          <col style='width: 20%;' />
          <col style='width: 20%;' />
          <col style='width: 10%;' />
          <col style='width: 10%;' />
        </colgroup>
        <TR>
          <TH class='list_center'>순서</TH>
          <TH class='list_center'>일정 번호</TH>
          <TH class='list_center'>회원 번호</TH>
          <TH class='list_center'>일정</TH>
          <TH class='list_center'>일정 시작 시간</TH>
          <TH class='list_center'>일정 종료 시간</TH>
          <TH class='list_center'>공개여부</TH>
          <TH class='list_center'>기타</TH>
        </TR>
        <%-- <tbody>
          <c:forEach var="scheduleVO" items="${list_all_schedule }">
            <c:set var="scheduleno" value ="${scheduleVO.scheduleno }" /> 
            <TR>
              <TD class = 'list_center'>${scheduleVO.scheduleno}</TD>
              <TD class = 'list_center'>${scheduleVO.employeeno}</TD>
              <TD class = 'list_left'>${scheduleVO.work}</TD>
              <TD class = 'list_center'>${scheduleVO.work_startdate} ${scheduleVO.start_time}</TD>
              <TD class = 'list_center'>${scheduleVO.work_enddate} ${scheduleVO.end_time}</TD>
              <TD class = 'list_center'>
              <c:choose>
                <c:when test ="${scheduleVO.visible == 'Y'}">
                  전체공개
                </c:when>
                <c:otherwise>
                  비공개
                </c:otherwise>
              </c:choose>
              </TD>
              <TD class = 'list_center'>
                <A href = "../calendar/update_schedule.jsp?scheduleno=${scheduleVO.scheduleno }">수정</A>
              </TD>

            </TR>
          </c:forEach>
        </tbody> --%>
        <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
        </tbody>
      </TABLE>

      <DIV class='bottom_menu'>
        <button type='button'
          onclick="location.href='./create_schedule.do'">일정 등록</button>
        <button type='button' onclick="location.href='./calendar.jsp'">캘린더</button>
      </DIV>

    </DIV>
    <!-- content END -->
  </DIV>
  <!-- container END -->
</body>

</html>
