package dev.mvc.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.employee.EmployeeProc")
public class EmployeeProc implements EmployeeProcInter {
  @Autowired
  @Qualifier("dev.mvc.employee.EmployeeDAO")
  private EmployeeDAOInter employeeDAO = null;
  
  public EmployeeProc() {
    System.out.println("--> EmployeeProc created.");
  }

  @Override
  public List<EmployeeVO> team_list() {
    List<EmployeeVO> list = this.employeeDAO.team_list();
    
    return list;
  }
  
}
