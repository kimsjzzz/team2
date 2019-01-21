package dev.mvc.management;

public class ManagementVO {
/*
CREATE TABLE management (
managementno        NUMBER(10)          NOT NULL,
projectscheduleno   NUMBER(10)          NOT NULL,
employeeno          NUMBER(10)          NOT NULL,
employee_name      VARCHAR(30)      NOT NULL ,   -- 작성자 
field                       VARCHAR(30)       NOT NULL , -- 분야
items                     VARCHAR(100)     NOT NULL ,     -- 항목
items_state               VARCHAR(100)     NOT NULL ,     -- 단계
mdate                   DATE                  NOT NULL,         -- 등록일
title                       VARCHAR(30)       NOT NULL ,     -- 업무명
result                    VARCHAR(100)      NOT NULL ,     -- 결과 /산출물
request                   VARCHAR(1000)   NOT NULL ,     -- 요청사항 
startdate                 VARCHAR(50)      NOT    NULL ,  -- 시작일
enddate                 VARCHAR(50)      NOT   NULL ,  -- 종료일
 */
  /** 프로젝트 요소번호 **/
  private int managementno;
  /** 프로젝트 번호 **/
  private int projectscheduleno;
  /** 사원번호 **/
  private int employeeno;
  /** 작성자 **/
  private String employee_name;
  /** 분야 **/
  private String field;
  /** 항목 **/
  private String items;
  /** 항목상태 **/
  private String items_state;
  /** 작성일 **/
  private String mdate;
  /** 프로젝트 요소제목 **/
  private String title;
  /** 결과,산출물 이미지 **/
  private String result;
  /** 요구사항 **/
  private String request;
  /** 시작일 **/
  private String startdate;
  /** 종료일 **/
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
