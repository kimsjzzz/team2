package dev.mvc.management;

public class ManagementVO {
/*
CREATE TABLE management (
managementno        NUMBER(10)          NOT NULL,
projectscheduleno   NUMBER(10)          NOT NULL,
employeeno          NUMBER(10)          NOT NULL,
employee_name      VARCHAR(30)      NOT NULL ,   -- �ۼ��� 
field                       VARCHAR(30)       NOT NULL , -- �о�
items                     VARCHAR(100)     NOT NULL ,     -- �׸�
items_state               VARCHAR(100)     NOT NULL ,     -- �ܰ�
mdate                   DATE                  NOT NULL,         -- �����
title                       VARCHAR(30)       NOT NULL ,     -- ������
result                    VARCHAR(100)      NOT NULL ,     -- ��� /���⹰
request                   VARCHAR(1000)   NOT NULL ,     -- ��û���� 
startdate                 VARCHAR(50)      NOT    NULL ,  -- ������
enddate                 VARCHAR(50)      NOT   NULL ,  -- ������
 */
  /** ������Ʈ ��ҹ�ȣ **/
  private int managementno;
  /** ������Ʈ ��ȣ **/
  private int projectscheduleno;
  /** �����ȣ **/
  private int employeeno;
  /** �ۼ��� **/
  private String employee_name;
  /** �о� **/
  private String field;
  /** �׸� **/
  private String items;
  /** �׸���� **/
  private String items_state;
  /** �ۼ��� **/
  private String mdate;
  /** ������Ʈ ������� **/
  private String title;
  /** ���,���⹰ �̹��� **/
  private String result;
  /** �䱸���� **/
  private String request;
  /** ������ **/
  private String startdate;
  /** ������ **/
  private String enddate;
  
  public int getManagementno() {
    return managementno;
  }
  public void setManagementno(int managementno) {
    this.managementno = managementno;
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
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getResult() {
    return result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public String getRequest() {
    return request;
  }
  public void setRequest(String request) {
    this.request = request;
  }
  public String getStartdate() {
    return startdate;
  }
  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }
  public String getEnddate() {
    return enddate;
  }
  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }
  
  
}
