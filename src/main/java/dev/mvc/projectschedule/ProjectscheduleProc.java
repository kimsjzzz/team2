package dev.mvc.projectschedule;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.paydescript.Paydescript;
import dev.mvc.paydescript.PaydescriptVO;

@Component("dev.mvc.projectschedule.ProjectscheduleProc")
public class ProjectscheduleProc implements ProjectscheduleProcInter {
  @Autowired
  @Qualifier("dev.mvc.projectschedule.ProjectscheduleDAO")
  private ProjectscheduleDAOInter projectscheduleDAO = null;
  
  public ProjectscheduleProc() {
    System.out.println("--> ProjectscheduleProc created.");
  }

  @Override
  public int create(ProjectscheduleVO projectscheduleVO) {
    int count = this.projectscheduleDAO.create(projectscheduleVO);
    
    return count;
  }

  @Override
  public List<ProjectscheduleVO> list() {
    List<ProjectscheduleVO> list = this.projectscheduleDAO.list();
    
    return list;
  }

  @Override
  public ProjectscheduleVO read_content(int projectscheduleno) {
    ProjectscheduleVO projectscheduleVO = this.projectscheduleDAO.read_content(projectscheduleno);
    
    return projectscheduleVO;
  }
  
  @Override
  public int search_no(String employee_name) {
    int employeeno = this.projectscheduleDAO.search_no(employee_name);
    
    return employeeno;
  }

  @Override
  public int update(ProjectscheduleVO projectscheduleVO) {
    int count = this.projectscheduleDAO.update(projectscheduleVO);
    
    return count;
  }

  @Override
  public int delete(int projectscheduleno) {
    int count = this.projectscheduleDAO.delete(projectscheduleno);
    
    return count;
  }

  @Override
  public int seqnoUp(int projectscheduleno) {
    int count = this.projectscheduleDAO.seqnoUp(projectscheduleno);
    
    return count;
  }

  @Override
  public int seqnoDown(int projectscheduleno) {
    int count = this.projectscheduleDAO.seqnoDown(projectscheduleno);
    
    return count;
  }

  @Override
  public List<ProjectscheduleVO> list_by_state(String project_state) {
    List<ProjectscheduleVO> list = this.projectscheduleDAO.list_by_state(project_state);
    
    return list;
  }

  @Override
  public List<ProjectscheduleVO> list_by_state_paging(HashMap<String, Object> hashMap) {
    /* 
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Projectschedule.RECORD_PER_PAGE;
   
    // 시작 rownum, 1 페이지: 1 / 2 페이지: 11 / 3 페이지: 21 
   int startNum = beginOfPage + 1; 
   //  종료 rownum, 1 페이지: 10 / 2 페이지: 20 / 3 페이지: 30
   int endNum = beginOfPage + Projectschedule.RECORD_PER_PAGE;   
   /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
    
    List<ProjectscheduleVO> list = this.projectscheduleDAO.list_by_state_paging(hashMap);
    
    return list;
  }
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  @Override
  public String paging(int search_count, int nowPage, String project_state){ 
    int totalPage = (int)(Math.ceil((double)search_count/Paydescript.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Paydescript.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Paydescript.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Paydescript.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Paydescript.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 

    // 이전 10개 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 20
    int _nowPage = (nowGrp-1) * Paydescript.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_by_state_paging.do?project_state="+project_state+"&nowPage="+_nowPage+"'>이전</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='./list_by_state_paging.do?project_state="+project_state+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 

    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 21
    _nowPage = (nowGrp * Paydescript.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_by_state_paging.do?project_state="+project_state+"&nowPage="+_nowPage+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int count = this.projectscheduleDAO.search_count(hashMap);
    
    return count;
  }
  
}
