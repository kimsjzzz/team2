package dev.mvc.schedule;

import java.util.List;

public interface ScheduleDAOInter {
   /**
   * ���� ���
   * @param scheduleVO
   */
  public abstract int create(ScheduleVO scheduleVO);
  
  /**
   * ���� ���
   */
  public List<ScheduleVO> list_all_schedule();
  
  /**
   * ���� ��ȸ
   */
  public ScheduleVO read_schedule(int scheduleno);
  
  /**
   * ���� ����
   */
  public int update_schedule(ScheduleVO scheduleVO);
  
  /**
   * ���� ����
   */
  public int delete_schedule(int scheduleno);

}
