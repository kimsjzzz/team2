package dev.mvc.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import nation.web.tool.Tool;

@Component("dev.mvc.schedule.ScheduleProc")
public class ScheduleProc implements ScheduleProcInter {
  @Autowired
  @Qualifier("dev.mvc.schedule.ScheduleDAO")
  private ScheduleDAOInter scheduleDAO = null;

  public ScheduleProc() {
    System.out.println("--> ScheduleProc created.");
  }

  @Override
  public int create(ScheduleVO scheduleVO) {
    return scheduleDAO.create(scheduleVO);
  }

  @Override
  public List<ScheduleVO> list_all_schedule() {
    return scheduleDAO.list_all_schedule();
  }
  
  @Override
  public ScheduleVO read_schedule(int scheduleno) {
    return scheduleDAO.read_schedule(scheduleno);
  }

  @Override
  public int update_schedule(ScheduleVO scheduleVO) {
    return scheduleDAO.update_schedule(scheduleVO);
  }
  
  @Override
  public int delete_schedule(int scheduleno) {
    return scheduleDAO.delete_schedule(scheduleno);
  }

  @Override
  public boolean checkDate(HttpSession session) {
    boolean cd = false;
    SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat stm = new SimpleDateFormat("HH:mm");
    
    Date work_startdate = null;
    Date work_enddate = null;
    Date start_time = null;
    Date end_time = null;
    
    try {
      work_startdate = sfm.parse((String)session.getAttribute("work_startdate"));
      work_enddate =  sfm.parse((String)session.getAttribute("work_enddate"));
      start_time = stm.parse((String)session.getAttribute("start_time"));
      end_time = stm.parse((String)session.getAttribute("end_time"));
      
      System.out.println(work_startdate);
      System.out.println(work_enddate);
      System.out.println(start_time);
      System.out.println(end_time);
    
    } catch (ParseException e) {
      e.printStackTrace();
    }    
    
    /*int compareDate = work_startdate.compareTo(work_enddate);
    int compareTime = start_time.compareTo(end_time);
    
    if(compareDate < 0){
      cd = true;
      
    } else if (compareDate == 0 && compareTime < 0){
        cd = true;
    } else{
      cd = false;
    }*/
    
    String start_date_time = work_startdate + " " + start_time;
    String end_date_time = work_enddate + " " + end_time;
    
    Date date = Tool.getDate8(start_date_time);
    System.out.println(date.toLocaleString());
    long start = date.getTime();
    
    date = Tool.getDate8(end_date_time);
    System.out.println(date.toLocaleString());
    long end = date.getTime();
    
    if(start - end < 0){
      cd = true;
    } else {
      cd = false;
    }
    
    return cd;
  }

   
}
