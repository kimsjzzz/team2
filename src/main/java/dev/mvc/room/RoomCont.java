package dev.mvc.room;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomCont {
  @Autowired
  @Qualifier("dev.mvc.room.RoomProc")
  private RoomProcInter roomProc = null;
 
  public RoomCont() {
    System.out.println("--> RoomCont created.");
  }
  
//http://localhost:9090/team2/room/create_room.do
 @RequestMapping(value = "/room/create_room.do", method = RequestMethod.GET)
 public ModelAndView create_room() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/room/create_room"); // /webapp/room/create_room.jsp

   return mav;
 }
 
//http://localhost:9090/team2_schedule/room/create_room.do
 @ResponseBody
 @RequestMapping(value = "/room/create_room.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
 public ResponseEntity create_room(RoomVO roomVO) {
   HttpHeaders responseHeaders = new HttpHeaders();

   JSONObject json = new JSONObject();
   JSONArray msgs = new JSONArray();

   if (roomProc.create_room(roomVO) == 1) {
     }

   return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
 }

/**
 * 회의실 목록
 */
@ResponseBody
@RequestMapping(value = "/room/list_room.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
public ResponseEntity list_room() {
  HttpHeaders responseHeaders = new HttpHeaders();
  
  List<RoomVO> list = roomProc.list_room();

  JSONArray json = new JSONArray(list);

  return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
}

/**
 * 회의실 수정 폼
 * @param roomno
 * @return
 */
@ResponseBody
@RequestMapping(value = "/room/update_room.do", method = RequestMethod.GET, 
                            produces = "text/plain;charset=UTF-8")
public ResponseEntity update_schedule(int roomno) {
  HttpHeaders responseHeaders = new HttpHeaders();
    
  RoomVO roomVO = roomProc.read_room(roomno);
  JSONObject obj = new JSONObject(roomVO);
  
  obj.put("roomno", roomno);
  obj.put("roomname", roomVO.getRoomname());
  obj.put("locate", roomVO.getLocate());
 
  
  return new ResponseEntity(obj.toString(), responseHeaders, HttpStatus.CREATED);
  // return obj.toString();
}

/**
 * 회의실 수정
 * 
 * @param request
 * @param contentsVO
 * @return
 */
@ResponseBody
@RequestMapping(value = "/room/update_room_json.do", method = RequestMethod.POST, 
                            produces = "text/plain;charset=UTF-8")
public ResponseEntity update_json(RedirectAttributes redirectAttributes, HttpServletRequest request, RoomVO roomVO) {
  HttpHeaders responseHeaders = new HttpHeaders();

  JSONObject json = new JSONObject();
  JSONArray msgs = new JSONArray();

  if (roomProc.update_room(roomVO) == 1) {
    }

  return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
}

/**
 * 회의실 삭제 폼
 * @param scheduleno
 * @return
 */
@ResponseBody
@RequestMapping(value = "/room/delete_room.do", 
                         method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
public String delete_room(int roomno) {
  RoomVO roomVO =roomProc.read_room(roomno);
  JSONObject obj = new JSONObject(roomVO);

  return obj.toString();
}

@ResponseBody
@RequestMapping(value = "/room/delete_room.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
public ResponseEntity delete_room_proc(int roomno) {
  HttpHeaders responseHeaders = new HttpHeaders();

  JSONObject json = new JSONObject();
  JSONArray msgs = new JSONArray();
  
  if(roomProc.delete_room(roomno) == 1) {
  }
  
  return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);

  }

}
