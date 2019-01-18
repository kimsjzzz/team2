package dev.mvc.schedule;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.schedule.ScheduleDAO")
public class ScheduleDAO implements ScheduleDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public ScheduleDAO() {
    System.out.println("--> ScheduleDAO created.");
  }

  @Override
  public int create(ScheduleVO scheduleVO) {
    return sqlSessionTemplate.insert("schedule.create", scheduleVO);
  }

  @Override
  public List<ScheduleVO> list_all_schedule() {
    return sqlSessionTemplate.selectList("schedule.list_all_schedule");
  }

  @Override
  public ScheduleVO read_schedule(int scheduleno) {
   return sqlSessionTemplate.selectOne("schedule.read_schedule", scheduleno);
  }

  @Override
  public int update_schedule(ScheduleVO scheduleVO) {
    return sqlSessionTemplate.update("schedule.update", scheduleVO);
  }

  @Override
  public int delete_schedule(int scheduleno) {
    return sqlSessionTemplate.delete("schedule.delete_schedule", scheduleno);
  }
  
  
  
  

}
