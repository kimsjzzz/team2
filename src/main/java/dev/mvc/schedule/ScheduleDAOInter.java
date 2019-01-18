package dev.mvc.schedule;

import java.util.List;

public interface ScheduleDAOInter {
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

}
