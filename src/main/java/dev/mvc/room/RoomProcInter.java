package dev.mvc.room;

import java.util.List;

public interface RoomProcInter {
  /**
   * ȸ�ǽ� ���
   * @param roomVO
   */
  public abstract int create_room(RoomVO roomVO);
  
  /**
   * ȸ�ǽ� ���
   */
  public List<RoomVO> list_room();
  
  /**
   * ȸ�ǽ� ��ȸ
   */
  public RoomVO read_room(int roomno);
  
  /**
   * ȸ�ǽ� ����
   */
  public int update_room(RoomVO roomVO);
  
  /**
   * ȸ�ǽ� ����
   * @param roomno
   * @return
   */
  public int delete_room(int roomno);

}
