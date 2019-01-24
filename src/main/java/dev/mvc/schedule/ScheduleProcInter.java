package dev.mvc.schedule;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface ScheduleProcInter {
  /**
   * 일정 등록
   * @param scheduleVO
   */
  public abstract int create(ScheduleVO scheduleVO);

  /**
   * 일정 목록
   */
  public List<ScheduleVO> list_all_schedule();
  
  /**
   * 일정 조회
   */
  public ScheduleVO read_schedule(int scheduleno);

  /**
   * 일정 수정
   */
  public int update_schedule(ScheduleVO scheduleVO);
  
  /**
   * 일정 삭제
   */
  public int delete_schedule(int scheduleno);
  
  /**
   * 일정 시작 날짜와 일정 종료 날짜가 올바른지 검사합니다.
   * @param session
   * @return true: 날짜의 순서가 올바름
   */
  public int check_date(ScheduleVO scheduleVO);
  

}
