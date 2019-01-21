package dev.mvc.employee;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.employee.EmployeeDAO") // DBMS 저장소 접근 
public class EmployeeDAO implements EmployeeDAOInter {
  @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public EmployeeDAO() {
    System.out.println("--> EmployeeDAO created.");
  }

  @Override
  public List<EmployeeVO> team_list() {
    List<EmployeeVO> list = sqlSessionTemplate.selectList("Employee.team_list");
    
    return list;
  }
}
