package dev.mvc.projectschedule;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.projectschedule.ProjectscheduleDAO") // DBMS 저장소 접근 
public class ProjectscheduleDAO implements ProjectscheduleDAOInter {
  @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ProjectscheduleDAO() {
    System.out.println("--> ProjectscheduleDAO created.");
  }

  @Override
  public int create(ProjectscheduleVO projectscheduleVO) {
    int count = sqlSessionTemplate.insert("Projectschedule.create", projectscheduleVO);
  
    return count;
  }

  @Override
  public List<ProjectscheduleVO> list() {
    List<ProjectscheduleVO> list = sqlSessionTemplate.selectList("Projectschedule.list");
    
    return list;
  }

  @Override
  public ProjectscheduleVO read_content(int projectscheduleno) {
    ProjectscheduleVO projectscheduleVO = sqlSessionTemplate.selectOne("Projectschedule.read_content", projectscheduleno);
    
    return projectscheduleVO;
  }

  @Override
  public int search_no(String employee_name) {
    int employeeno = sqlSessionTemplate.selectOne("Projectschedule.search_no", employee_name);
    
    return employeeno;
  }

  @Override
  public int update(ProjectscheduleVO projectscheduleVO) {
    int count = sqlSessionTemplate.update("Projectschedule.update", projectscheduleVO);
    
    return count;
  }

  @Override
  public int delete(int projectscheduleno) {
    int count = sqlSessionTemplate.delete("Projectschedule.delete", projectscheduleno);
    
    return count;
  }

  @Override
  public int seqnoUp(int projectscheduleno) {
    int count = sqlSessionTemplate.update("Projectschedule.seqnoUp", projectscheduleno);
    
    return count;
  }

  @Override
  public int seqnoDown(int projectscheduleno) {
    int count = sqlSessionTemplate.update("Projectschedule.seqnoDown", projectscheduleno);
    
    return count;
  }

  @Override
  public List<ProjectscheduleVO> list_by_state(String project_state) {
    List<ProjectscheduleVO> list = sqlSessionTemplate.selectList("Projectschedule.list_by_state", project_state);
    
    return list;
  }

  @Override
  public List<ProjectscheduleVO> list_by_state_paging(HashMap<String, Object> hashMap) {
    List<ProjectscheduleVO> list = sqlSessionTemplate.selectList("Projectschedule.list_by_state_paging", hashMap);
    
    return list;
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int count = sqlSessionTemplate.selectOne("Projectschedule.search_count", hashMap);
    
    return count;
  }
}
