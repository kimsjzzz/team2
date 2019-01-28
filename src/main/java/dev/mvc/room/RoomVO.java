package dev.mvc.room;
/*
  roomno INT NOT NULL,
  roomname VARCHAR(30) NOT NULL,
  locate VARCHAR(50)
 */

public class RoomVO {
  private int roomno;
  private String roomname;
  private String locate;

  public RoomVO() {
    
  }

  public int getRoomno() {
    return roomno;
  }

  public void setRoomno(int roomno) {
    this.roomno = roomno;
  }

  public String getRoomname() {
    return roomname;
  }

  public void setRoomname(String roomname) {
    this.roomname = roomname;
  }

  public String getLocate() {
    return locate;
  }

  public void setLocate(String locate) {
    this.locate = locate;
  }

}
