package dev.mvc.employee;

import java.util.List;

public interface EmployeeDAOInter {

  /**
   * SELECT BOX�� ���� �μ��� ��������
   * <select id="team_list" resultType="EmployeeVO">
   * @return
   */
  public abstract List<EmployeeVO> team_list();
}
