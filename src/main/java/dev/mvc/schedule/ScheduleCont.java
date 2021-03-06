package dev.mvc.schedule;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nation.web.tool.Tool;


@Controller
public class ScheduleCont {
  @Autowired
  @Qualifier("dev.mvc.schedule.ScheduleProc")
  private ScheduleProcInter scheduleProc = null;

  public ScheduleCont() {
    System.out.println("--> ScheduleCont created.");
  }

  // http://localhost:9090/team2/calendar/create_schedule.do
  @RequestMapping(value = "/calendar/create_schedule.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/calendar/create_schedule"); // /webapp/calendar/create_schedule.jsp

    return mav;
  }

  // http://localhost:9090/team2/calendar/create_schedule.do
  @RequestMapping(value = "/calendar/create_schedule.do", method = RequestMethod.POST)
  public ModelAndView create(ScheduleVO scheduleVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    int count = 0;
    
     if(scheduleProc.check_date(scheduleVO) == 0){
       count = scheduleProc.create(scheduleVO);
       mav.setViewName("redirect:/calendar/create_schedule_message.jsp?count=" + count);
       
      
    } else {
      count = 2;
      mav.setViewName("redirect:/calendar/create_schedule_message.jsp?count=" + count);
    }
     /*count = scheduleProc.create(scheduleVO);
     mav.setViewName("redirect:/calendar/create_schedule_message.jsp?count=" + count);*/
    
    
    return mav;
  }
/**
 * 일정 목록
 * @return
 */
  @RequestMapping(value = "/calendar/list_all_schedule.do", method = RequestMethod.GET)
  public ModelAndView list_all_schedule() {
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    
     
      List<ScheduleVO> list_all_schedule = scheduleProc.list_all_schedule();
      mav.addObject("list_all_schedule", list_all_schedule);

      mav.setViewName("/calendar/list_all_schedule"); // webapp/calendar/list_all_schedule.jsp
      
    return mav;
  }
  
  /**
   * json 기반 일정 목록
   * @param scheduleno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/calendar/list_all_schedule_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_all_schedule_json() {
    HttpHeaders responseHeaders = new HttpHeaders();
    
    List<ScheduleVO> list = scheduleProc.list_all_schedule();

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
 
  /**
   * 일정 조회
   * @param scheduleno
   * @return
   *//*
  @RequestMapping(value="/calendar/read_schedule.do", method=RequestMethod.GET)
  public ModelAndView read(int scheduleno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/calendar/read"); // webapp/member/read.jsp
    
    ScheduleVO scheduleVO = scheduleProc.read_schedule(scheduleno);
    mav.addObject("scheduleVO", scheduleVO);
    
    return mav;
  }  
*/
  /**
   * 일정 수정 폼
   * @param scheduleno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/calendar/update_schedule.do", method = RequestMethod.GET, 
                              produces = "text/plain;charset=UTF-8")
  public ResponseEntity update_schedule(int scheduleno) {
    HttpHeaders responseHeaders = new HttpHeaders();
      
    ScheduleVO scheduleVO = scheduleProc.read_schedule(scheduleno);
    JSONObject obj = new JSONObject(scheduleVO);
    
    obj.put("scheduleno", scheduleno);
    obj.put("employeeno", scheduleVO.getEmployeeno());
    obj.put("work", scheduleVO.getWork());
    /*obj.put("work_startdate", Tool.getDate9(scheduleVO.getWork_startdate()));
    obj.put("work_enddate", Tool.getDate9(scheduleVO.getWork_enddate()));
    obj.put("start_time", Tool.getDate10(scheduleVO.getStart_time()));
    obj.put("end_time", Tool.getDate10(scheduleVO.getEnd_time()));*/
    obj.put("work_startdate",scheduleVO.getWork_startdate());
    obj.put("work_enddate",scheduleVO.getWork_enddate());
    obj.put("start_time",scheduleVO.getStart_time());
    obj.put("end_time",scheduleVO.getEnd_time());
    obj.put("visible", scheduleVO.getVisible());
    
    return new ResponseEntity(obj.toString(), responseHeaders, HttpStatus.CREATED);
    // return obj.toString();
  }
  
  /**
   * 일정 수정
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/calendar/update_schedule_json.do", method = RequestMethod.POST, 
                              produces = "text/plain;charset=UTF-8")
  public ResponseEntity update_json(RedirectAttributes redirectAttributes, HttpServletRequest request, ScheduleVO scheduleVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (scheduleProc.update_schedule(scheduleVO) == 1) {
      }

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * 일정 삭제 폼
   * @param scheduleno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/calendar/delete_schedule.do", 
                           method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String delete_schedule(int scheduleno) {
    ScheduleVO scheduleVO = scheduleProc.read_schedule(scheduleno);
    JSONObject obj = new JSONObject(scheduleVO);

    return obj.toString();
  }
  
  @ResponseBody
  @RequestMapping(value = "/calendar/delete_schedule.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity delete_schedule_proc(int scheduleno) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();
    
    String work = scheduleProc.read_schedule(scheduleno).getWork();
    
    if (scheduleProc.delete_schedule(scheduleno) == 1) {
    }

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
}
