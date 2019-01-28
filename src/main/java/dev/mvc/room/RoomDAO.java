package dev.mvc.room;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.room.RoomDAO")
public class RoomDAO implements RoomDAOInter{
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public RoomDAO() {
    System.out.println("--> RoomDAO created.");
  }

  @Override
  public int create_room(RoomVO roomVO) {
    return sqlSessionTemplate.insert("room.create_room", roomVO);
  }

  @Override
  public List<RoomVO> list_room() {
    return sqlSessionTemplate.selectList("room.list_room");
  }

  @Override
  public RoomVO read_room(int roomno) {
    return sqlSessionTemplate.selectOne("room.read_room", roomno);
  }

  @Override
  public int update_room(RoomVO roomVO) {
    return sqlSessionTemplate.update("room.update_room", roomVO);
  }

  @Override
  public int delete_room(int roomno) {
    return sqlSessionTemplate.delete("room.delete_room", roomno);
  }

}
