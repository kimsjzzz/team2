package dev.mvc.journal;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.journal.JournalProc")
public class JournalProc implements JournalProcInter {
  @Autowired
  @Qualifier("dev.mvc.journal.JournalDAO")
  private JournalDAOInter journalDAO = null;
  
  public JournalProc() {
    System.out.println("--> JournalProc created.");
  }

  @Override
  public int create(JournalVO journalVO) {
    int count = this.journalDAO.create(journalVO);
    
    return count;
  }
  
  @Override
  public int search_no(String employee_name) {
    int employeeno = this.journalDAO.search_no(employee_name);
    
    return employeeno;
  }

  @Override
  public List<Projectschedule_JournalVO> list_by_schedule(int projectscheduleno) {
    List<Projectschedule_JournalVO> list = this.journalDAO.list_by_schedule(projectscheduleno);
    
    return list;
  }

  @Override
  public JournalVO read(int journalno) {
    JournalVO journalVO = this.journalDAO.read(journalno);
    
    return journalVO;
  }
  
  @Override
  public int update(JournalVO journalVO) {
    int count = this.journalDAO.update(journalVO);
    
    return count;
  }
  
  @Override
  public ArrayList<String> devide_String(String str) {
    ArrayList<String> list = new ArrayList<String>();
    StringTokenizer token = new StringTokenizer(str, ",");  // " , "�� �������� token ����

    while(token.hasMoreTokens()) {  // ��ū�� ������ �� ����
      list.add(token.nextToken());    // ��ū�� ArrayList�� add
    }
    
    return list; 
  }


}
