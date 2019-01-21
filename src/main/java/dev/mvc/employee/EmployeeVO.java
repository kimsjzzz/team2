package dev.mvc.employee;

public class EmployeeVO {
/* 
  employeeno            NUMBER(10)        NOT NULL,
  employee_name     VARCHAR(10)       NOT NULL,
  department_name   VARCHAR(10)       NOT NULL, 
 */
 private String department_name;

public String getDepartment_name() {
  return department_name;
}

public void setDepartment_name(String department_name) {
  this.department_name = department_name;
}
 
 
  
}
