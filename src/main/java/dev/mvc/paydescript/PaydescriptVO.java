package dev.mvc.paydescript;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

public class PaydescriptVO {
/*  paydescriptno           NUMBER(10)            NOT NULL,
  employeeno            NUMBER(10)            NOT NULL,
  employee_name     VARCHAR(10)         NOT NULL,
  department_name   VARCHAR(10)         NOT NULL,
  pay                       NUMBER(10)            NOT NULL,
  insentive               NUMBER(10)            NOT NULL,
  team_pay                NUMBER(10)            NOT NULL,
  perfect_pay             NUMBER(10)            NOT NULL,
  total                     NUMBER(10)            NOT NULL,
  insurance               NUMBER(10)            NOT NULL,
  incometax               NUMBER(10)            NOT NULL,
  real_pay                  NUMBER(10)            NOT NULL,
  month                   VARCHAR(10)         NOT NULL,
  payment_day           VARCHAR(10)         NOT NULL,
*/
  /** 명세서번호 */
  private int paydescriptno;
  /** 사원번호 */
  private int employeeno;
  /** 사원이름 */
  private String employee_name;
  /** 부서이름 */
  private String department_name;
  /** 기본급 */
  private int pay;
  /** 상여금 */
  private int insentive;
  /** 팀수당 */
  private int team_pay;
  /** 만근비 */
  private int perfect_pay;
  /** 총금액 */
  private int total;
  /** 4대보험(4%) */
  private int insurance;
  /** 종합소득세(10%) */
  private int incometax;
  /** 실수령액 */
  private int real_pay;
  /** 월 */
  private String month;
  /** 실지급일 */
  private String payment_day;
  /** 이미지 */
  private String file1= "";
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
*/  
private MultipartFile file1MF;
  
  public int getPaydescriptno() {
    return paydescriptno;
  }
  public void setPaydescriptno(int paydescriptno) {
    this.paydescriptno = paydescriptno;
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
  public String getDepartment_name() {
    return department_name;
  }
  public void setDepartment_name(String department_name) {
    this.department_name = department_name;
  }
  public int getPay() {
    return pay;
  }
  public void setPay(int pay) {
    this.pay = pay;
  }
  public int getInsentive() {
    return insentive;
  }
  public void setInsentive(int insentive) {
    this.insentive = insentive;
  }
  public int getTeam_pay() {
    return team_pay;
  }
  public void setTeam_pay(int team_pay) {
    this.team_pay = team_pay;
  }
  public int getPerfect_pay() {
    return perfect_pay;
  }
  public void setPerfect_pay(int perfect_pay) {
    this.perfect_pay = perfect_pay;
  }
  public int getTotal() {
    return total;
  }
  public void setTotal(int total) {
    this.total = total;
  }
  public int getInsurance() {
    return insurance;
  }
  public void setInsurance(int insurance) {
    this.insurance = insurance;
  }
  public int getIncometax() {
    return incometax;
  }
  public void setIncometax(int incometax) {
    this.incometax = incometax;
  }
  public int getReal_pay() {
    return real_pay;
  }
  public void setReal_pay(int real_pay) {
    this.real_pay = real_pay;
  }
  public String getMonth() {
    return month;
  }
  public void setMonth(String month) {
    this.month = month;
  }
  public String getPayment_day() {
    return payment_day;
  }
  public void setPayment_day(String payment_day) {
    this.payment_day = payment_day;
  }
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }



 
  
}
