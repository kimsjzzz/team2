package dev.mvc.schedule;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface ScheduleProcInter {
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
   * ���� ���� ��¥�� ���� ���� ��¥�� �ùٸ��� �˻��մϴ�.
   * @param session
   * @return true: ��¥�� ������ �ùٸ�
   */
  public boolean checkDate(HttpSession session);
  
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
