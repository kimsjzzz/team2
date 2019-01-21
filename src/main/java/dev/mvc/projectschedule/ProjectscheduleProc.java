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
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Projectschedule.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Projectschedule.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
    
    List<ProjectscheduleVO> list = this.projectscheduleDAO.list_by_state_paging(hashMap);
    
    return list;
  }
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String paging(int search_count, int nowPage, String project_state){ 
    int totalPage = (int)(Math.ceil((double)search_count/Paydescript.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Paydescript.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Paydescript.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Paydescript.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Paydescript.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

    // ���� 10�� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� 10
    // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� 20
    int _nowPage = (nowGrp-1) * Paydescript.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_by_state_paging.do?project_state="+project_state+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list_by_state_paging.do?project_state="+project_state+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * Paydescript.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_by_state_paging.do?project_state="+project_state+"&nowPage="+_nowPage+"'>����</A></span>"); 
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
