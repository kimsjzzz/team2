package dev.mvc.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeCont {
  
  @Autowired
  @Qualifier("dev.mvc.employee.EmployeeProc")
  private EmployeeProcInter employeeProc = null;
  
  public EmployeeCont() {
    System.out.println("--> EmployeeCont created.");
  }
  
  
}
