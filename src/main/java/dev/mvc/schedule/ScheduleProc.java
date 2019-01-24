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
  public int check_date(ScheduleVO scheduleVO) {
    return scheduleDAO.check_date(scheduleVO);
  }

   
}
