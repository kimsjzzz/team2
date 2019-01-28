package dev.mvc.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.room.RoomProc")
public class RoomProc implements RoomProcInter {
  @Autowired
  @Qualifier("dev.mvc.room.RoomDAO")
  private RoomDAOInter roomDAO = null;
  
  public RoomProc() {
    System.out.println("--> RoomProc created.");
  }

  @Override
  public int create_room(RoomVO roomVO) {
    return roomDAO.create_room(roomVO);
  }

  @Override
  public List<RoomVO> list_room() {
    return roomDAO.list_room();
  }

  @Override
  public RoomVO read_room(int roomno) {
    return roomDAO.read_room(roomno);
  }

  @Override
  public int update_room(RoomVO roomVO) {
    return roomDAO.update_room(roomVO);
  }

  @Override
  public int delete_room(int roomno) {
    return roomDAO.delete_room(roomno);
  }

}
