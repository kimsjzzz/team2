package dev.mvc.room;

import java.util.List;

public interface RoomProcInter {
  /**
   * 회의실 등록
   * @param roomVO
   */
  public abstract int create_room(RoomVO roomVO);
  
  /**
   * 회의실 목록
   */
  public List<RoomVO> list_room();
  
  /**
   * 회의실 조회
   */
  public RoomVO read_room(int roomno);
  
  /**
   * 회의실 수정
   */
  public int update_room(RoomVO roomVO);
  
  /**
   * 회의실 삭제
   * @param roomno
   * @return
   */
  public int delete_room(int roomno);

}
