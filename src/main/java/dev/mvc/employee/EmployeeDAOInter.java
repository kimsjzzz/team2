package dev.mvc.employee;

import java.util.List;

public interface EmployeeDAOInter {

  /**
   * SELECT BOX를 위한 부서명 가져오기
   * <select id="team_list" resultType="EmployeeVO">
   * @return
   */
  public abstract List<EmployeeVO> team_list();
}
