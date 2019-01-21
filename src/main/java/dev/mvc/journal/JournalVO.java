package dev.mvc.journal;

public class JournalVO {
  /*
  journalno             NUMBER(10)        NOT NULL,
  seqno                 NUMBER(10)        NOT NULL,
  projectscheduleno NUMBER(10)        NOT NULL,
  employeeno          NUMBER(10)  ,
  employee_name   VARCHAR(10) , 
  field                     VARCHAR(30)     NOT NULL,
  title                     VARCHAR(100)    NOT NULL,
  Items                   VARCHAR(400)    NOT NULL,
  items_state           VARCHAR(400)    NOT NULL,
  state                   VARCHAR(10)     NOT NULL,
  rname                 VARCHAR(10)     NOT NULL,
  */
  /** 개발일지 번호 */
  private int journalno;
  /** 우선순위 */
  private int seqno;
  /** 프로젝트일정 번호 */
  private int projectscheduleno;
  /** 담당사원 번호 */
  private int employeeno;
  /** 담당사원 이름 */
  private String employee_name;
  /** 개발분야 */
  private String field;
  /** 제목 */
  private String title;
  /** 세부항목 */
  private String items;
  /** 세부항목 진행상태 */
  private String items_state;
  /** 전체 진행상태 */
  private String journal_state;
  /** 작성자 */
  private String rname;
  
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
  public int getProjectscheduleno() {
    return projectscheduleno;
  }
  public void setProjectscheduleno(int projectscheduleno) {
    this.projectscheduleno = projectscheduleno;
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
  public String getRname() {
    return rname;
  }
  public void setRname(String rname) {
    this.rname = rname;
  }
  public String getJournal_state() {
    return journal_state;
  }
  public void setJournal_state(String journal_state) {
    this.journal_state = journal_state;
  }


  
  
}
