<%@ page contentType="text/html; charset=UTF-8" %>
 <!DOCTYPE html>
<html lang="ko">
 <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>IT 개발 통합관리 서비스</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/full-slider.css" rel="stylesheet">

  </head>

  <body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
       <a class="navbar-brand" href="../index.jsp" style="font-size: 19px; padding-top: 10px;">IT INTRANET</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto" style='font-size: 19px;'>
            <li class="nav-item active">
              <a class="nav-link" href="../index.jsp" style='font-size: 16px;'>Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" style='font-size: 16px;'>About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" style='font-size: 16px;'>Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" style='font-size: 16px;'>Contact</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"  style='font-size: 16px;'>Menu</a>
              <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px);">
                <a class="dropdown-item" href="../paydescript/list_by_search_paging.do?nowPage=1">급여명세서</a>
                <a class="dropdown-item" href="../projectschedule/list_by_state_paging.do?nowPage=1">프로젝트 일정</a>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
           </li>
          </ul>
        </div>
      </div>
    </nav>
    
    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
