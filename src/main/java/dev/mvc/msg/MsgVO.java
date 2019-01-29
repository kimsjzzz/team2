package dev.mvc.msg;

/**
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 2조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : dev.mvc.msg
 * 파일명           : MsgVO.java 2019. 1. 14.
 * 작성자           : jlyn7437
 * 작성자 email   : jlyn7437@naver.com
 * 수정내용
 * ------------------------------------------------------------------
 * 수정 이력
 * ------------------------------------------------------------------ 
 * 수정일        수정자  연락처               수정 내용
 * ------------------------------------------------------------------ 
 * 2016-05-01 아로미  mail@mail.com  회원 등록 변경
 * ------------------------------------------------------------------
 * 
 *</pre>
 */
public class MsgVO {
  
  /** 메시지 번호 */
  private int msgno;
  /** 메시지 내용 */
  private String content;
  /** 메시지 보낸날짜 */
  private String rdate;
  /** 메시지 조회날짜 */
  private String vdate;
  /** 메시지 조회여부 */
  private String vcnt;
  /** 메시지 보낸사람 */
  private int sender;
  /** 메시지 받는사람 */
  private int receiver;
  private String name;
  
  public int getMsgno() {
    return msgno;
  }
  public void setMsgno(int msgno) {
    this.msgno = msgno;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getVdate() {
    return vdate;
  }
  public void setVdate(String vdate) {
    this.vdate = vdate;
  }
  public String getVcnt() {
    return vcnt;
  }
  public void setVcnt(String vcnt) {
    this.vcnt = vcnt;
  }
  public int getSender() {
    return sender;
  }
  public void setSender(int sender) {
    this.sender = sender;
  }
  public int getReceiver() {
    return receiver;
  }
  public void setReceiver(int receiver) {
    this.receiver = receiver;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  

}
