package dev.mvc.projectschedule;

public class ProjectscheduleVO {
  /*
  projectscheduleno   NUMBER(10)            NOT NULL,
  seqno                     NUMBER(10)            NOT NULL,
  employeeno            NUMBER(10)          NOT NULL,
  employee_name     VARCHAR(10)         NOT NULL,
  project_name          VARCHAR(50)         NOT NULL,
  project_content       VARCHAR(1000)     NOT NULL,
  project_startdate     VARCHAR(30)         NOT NULL,
  project_enddate       VARCHAR(30)         NOT NULL,
  project_state           VARCHAR(20)         NOT NULL,
  */
  /** ������Ʈ ���� ��ȣ */
  private int projectscheduleno;
  /** �켱���� */
  private int seqno;
  /** �����ȣ */
  private int employeeno;
  /** ����� */
  private String employee_name;
  /** ������Ʈ�� */
  private String project_name;
  /** ������Ʈ ���� */
  private String project_content;
  /** ������ */
  private String project_startdate;
  /** ������ */
  private String project_enddate;
  /** ������� */
  private String project_state;
  
  public int getProjectscheduleno() {
    return projectscheduleno;
  }
  public void setProjectscheduleno(int projectscheduleno) {
    this.projectscheduleno = projectscheduleno;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public int getEmployeeno() {
    return employeeno;
  }
  public void setEmployeeno(int employeeno) {
    this.employeeno = employeeno;
  }
  public String getEmployee_name() {
    return employee_name;
  }
  public void setEmployee_name(String employee_name) {
    this.employee_name = employee_name;
  }
  public String getProject_name() {
    return project_name;
  }
  public void setProject_name(String project_name) {
    this.project_name = project_name;
  }
  public String getProject_content() {
    return project_content;
  }
  public void setProject_content(String project_content) {
    this.project_content = project_content;
  }
  public String getProject_startdate() {
    return project_startdate;
  }
  public void setProject_startdate(String project_startdate) {
    this.project_startdate = project_startdate;
  }
  public String getProject_enddate() {
    return project_enddate;
  }
  public void setProject_enddate(String project_enddate) {
    this.project_enddate = project_enddate;
  }
  public String getProject_state() {
    return project_state;
  }
  public void setProject_state(String project_state) {
    this.project_state = project_state;
  }
  
}
