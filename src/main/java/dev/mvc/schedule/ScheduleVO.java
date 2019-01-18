package dev.mvc.schedule;
/*
scheduleno INT NOT NULL PRIMARY KEY,
employeeno INT NOT NULL,
work VARCHAR(100) NOT NULL,
work_startdate VARCHAR(20) NOT NULL,
work_enddate VARCHAR(20) NOT NULL,
start_time VARCHAR(10),
end_time VARCHAR(10),
visible VARCHAR(10),
 */

public class ScheduleVO {
  private int scheduleno;
  private int employeeno;
  private String work;
  private String work_startdate;
  private String work_enddate;
  private String start_time;
  private String end_time;
  private String visible;
  
  public ScheduleVO(){
    
  }

  public int getScheduleno() {
    return scheduleno;
  }

  public void setScheduleno(int scheduleno) {
    this.scheduleno = scheduleno;
  }

  public int getEmployeeno() {
    return employeeno;
  }

  public void setEmployeeno(int employeeno) {
    this.employeeno = employeeno;
  }

  public String getWork() {
    return work;
  }

  public void setWork(String work) {
    this.work = work;
  }

  public String getWork_startdate() {
    return work_startdate;
  }

  public void setWork_startdate(String work_startdate) {
    this.work_startdate = work_startdate;
  }

  public String getWork_enddate() {
    return work_enddate;
  }

  public void setWork_enddate(String work_enddate) {
    this.work_enddate = work_enddate;
  }

  public String getStart_time() {
    return start_time;
  }

  public void setStart_time(String start_time) {
    this.start_time = start_time;
  }

  public String getEnd_time() {
    return end_time;
  }

  public void setEnd_time(String end_time) {
    this.end_time = end_time;
  }

  public String getVisible() {
    return visible;
  }

  public void setVisible(String visible) {
    this.visible = visible;
  }
  
  
}
