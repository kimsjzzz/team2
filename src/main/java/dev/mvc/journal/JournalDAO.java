package dev.mvc.journal;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.journal.JournalDAO") // DBMS 저장소 접근
public class JournalDAO implements JournalDAOInter {
  @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public JournalDAO() {
    System.out.println("--> JournalDAO created.");
  }

  @Override
  public int create(JournalVO journalVO) {
    int count = sqlSessionTemplate.insert("Journal.create", journalVO);
    
    return count;
  }
  
  @Override
  public int search_no(String employee_name) {
    int employeeno = sqlSessionTemplate.selectOne("Journal.search_no", employee_name);
    
    return employeeno;
  }

  @Override
  public List<Projectschedule_JournalVO> list_by_schedule(int projectscheduleno) {
    List<Projectschedule_JournalVO> list = sqlSessionTemplate.selectList("Journal.list_by_schedule", projectscheduleno);
    
    return list;
  }

  @Override
  public JournalVO read(int journalno) {
    JournalVO journalVO = sqlSessionTemplate.selectOne("Journal.read", journalno);
    
    return journalVO;
  }

  @Override
  public int update(JournalVO journalVO) {
    int count = sqlSessionTemplate.update("Journal.update", journalVO);
    
    return count;
  }
  
}
