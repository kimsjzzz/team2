package dev.mvc.journal;

public class Projectschedule_JournalVO {

  /******* ProjectscheduleVO ********/
  
  /** ������Ʈ ���� ��ȣ */
  private int projectscheduleno;
  /** ������Ʈ�� */
  private String project_name;
  
  /******* Journal VO ******/
  
  /** �������� ��ȣ */
  private int journalno;
  /** �켱���� */
  private int seqno;
  /** ����� ��ȣ */
  private int employeeno;
  /** ����� �̸� */
  private String employee_name;
  /** ���ߺо� */
  private String field;
  /** ���� */
  private String title;
  /** �����׸� */
  private String items;
  /** �����׸� ������� */
  private String items_state;
  /** ��ü ������� */
  private String journal_state;
  /** �ۼ��� */
  private String rname;
  
  
  public int getProjectscheduleno() {
    return projectscheduleno;
  }
  public void setProjectscheduleno(int projectscheduleno) {
    this.projectscheduleno = projectscheduleno;
  }
  public String getProject_name() {
    return project_name;
  }
  public void setProject_name(String project_name) {
    this.project_name = project_name;
  }
  public int getJournalno() {
    return journalno;
  }
  public void setJournalno(int journalno) {
    this.journalno = journalno;
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
  public String getField() {
    return field;
  }
  public void setField(String field) {
    this.field = field;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getItems() {
    return items;
  }
  public void setItems(String items) {
    this.items = items;
  }
  public String getItems_state() {
    return items_state;
  }
  public void setItems_state(String items_state) {
    this.items_state = items_state;
  }
  public String getJournal_state() {
    return journal_state;
  }
  public void setJournal_state(String journal_state) {
    this.journal_state = journal_state;
  }
  public String getRname() {
    return rname;
  }
  public void setRname(String rname) {
    this.rname = rname;
  }
  
}
