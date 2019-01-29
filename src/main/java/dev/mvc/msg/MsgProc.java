/**
 * 
 */
package dev.mvc.msg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.employee.EmployeeVO;

/**
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : dev.mvc.msg
 * 파일명           : MsgProc.java 2019. 1. 14.
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

@Component("dev.mvc.msg.MsgProc")
public class MsgProc implements MsgProcInter {
  @Autowired
  @Qualifier("dev.mvc.msg.MsgDAO")
  private MsgDAOInter msgDAO = null;
  
  public MsgProc() {
    System.out.println("--> MsgProc created");
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#create(dev.mvc.msg.MsgVO)
   */
  @Override
  public int create(MsgVO msgVO) {
    // TODO Auto-generated method stub
    return msgDAO.create(msgVO);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#receive_msg(int)
   */
  @Override
  public List<MsgVO> receive_msg(int sender) {
    // TODO Auto-generated method stub
    return msgDAO.receive_msg(sender);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#read_msg(int)
   */
  @Override
  public MsgVO read_msg(int mailno) {
    // TODO Auto-generated method stub
    return msgDAO.read_msg(mailno);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#send_msg(int)
   */
  @Override
  public List<MsgVO> send_msg(int employeeno) {
    // TODO Auto-generated method stub
    return msgDAO.send_msg(employeeno);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#employee_select()
   */
  @Override
  public List<EmployeeVO> employee_select() {
    // TODO Auto-generated method stub
    return msgDAO.employee_select();
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#update_vcnt(dev.mvc.msg.MsgVO)
   */
  @Override
  public int update_vcnt(MsgVO msgVO) {
    // TODO Auto-generated method stub
    return msgDAO.update_vcnt(msgVO);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#delete(int)
   */
  @Override
  public int delete(int msgno) {
    // TODO Auto-generated method stub
    return msgDAO.delete(msgno);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#receive_msg()
   */
  @Override
  public List<MsgVO> list() {
    // TODO Auto-generated method stub
    return msgDAO.list();
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgProcInter#update(dev.mvc.msg.MsgVO)
   */
  @Override
  public int update(MsgVO msgVO) {
    // TODO Auto-generated method stub
    return msgDAO.update(msgVO);
  }

  @Override
  public int receive_count(int receiver) {
    // TODO Auto-generated method stub
    return msgDAO.receive_count(receiver);
  }

  @Override
  public int send_count(int sender) {
    // TODO Auto-generated method stub
    return msgDAO.send_count(sender);
  }

}
