/**
 * 
 */
package dev.mvc.msg;

import java.util.List;

import dev.mvc.employee.EmployeeVO;

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
public interface MsgDAOInter {
  
  public int login(MsgVO msgVO);
  
  public int create(MsgVO msgVO);
  
  public List<MsgVO> list();
  
  public List<MsgVO> receive_msg(int sender);
  
  public int receive_count(int receiver);
  
  public MsgVO read_msg(int mailno);
  
  public List<MsgVO> send_msg(int employeeno);
  
  public int send_count(int sender);
  
  public List<EmployeeVO> employee_select();
  
  public int update_vcnt(MsgVO msgVO);
  
  public int update(MsgVO msgVO);
  
  public int delete(int msgno);
  
  
  
}
