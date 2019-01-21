package dev.mvc.employee;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.employee.EmployeeDAO") // DBMS ����� ���� 
public class EmployeeDAO implements EmployeeDAOInter {
  @Autowired  // ���� �������� ���� �ڵ� �Ҵ�, �����ڴ� new ��� ����.
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
