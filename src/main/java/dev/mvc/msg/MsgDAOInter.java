/**
 * 
 */
package dev.mvc.msg;

import java.util.List;

import dev.mvc.employee.EmployeeVO;

/**
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 2��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : dev.mvc.msg
 * ���ϸ�           : MsgVO.java 2019. 1. 14.
 * �ۼ���           : jlyn7437
 * �ۼ��� email   : jlyn7437@naver.com
 * ��������
 * ------------------------------------------------------------------
 * ���� �̷�
 * ------------------------------------------------------------------ 
 * ������        ������  ����ó               ���� ����
 * ------------------------------------------------------------------ 
 * 2016-05-01 �Ʒι�  mail@mail.com  ȸ�� ��� ����
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
