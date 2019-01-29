package dev.mvc.msg;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.employee.EmployeeVO;

/**
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 2��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : dev.mvc.msg
 * ���ϸ�           : MsgDAO.java 2019. 1. 14.
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

@Repository("dev.mvc.msg.MsgDAO")
public class MsgDAO implements MsgDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public MsgDAO() {
    System.out.println("--> MsgDAO created.");
  }
  
  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#create()
   */
  @Override
  public int create(MsgVO msgVO) {
    return sqlSessionTemplate.insert("msg.create", msgVO);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#receive_msg()
   */
  @Override
  public List<MsgVO> receive_msg(int sender) {
    return sqlSessionTemplate.selectList("msg.receive_msg", sender);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#read_msg()
   */
  @Override
  public MsgVO read_msg(int mailno) {
    return sqlSessionTemplate.selectOne("msg.read_msg", mailno);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#send_msg()
   */
  @Override
  public List<MsgVO> send_msg(int employeeno) {
    return sqlSessionTemplate.selectList("msg.send_msg", employeeno);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#employee_select()
   */
  @Override
  public List<EmployeeVO> employee_select() {
    return sqlSessionTemplate.selectList("msg.employee_select");
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#update_vcnt(dev.mvc.msg.MsgVO)
   */
  @Override
  public int update_vcnt(MsgVO msgVO) {
    return sqlSessionTemplate.update("msg.update_vcnt", msgVO);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#delete(int)
   */
  @Override
  public int delete(int msgno) {
    // TODO Auto-generated method stub
    return sqlSessionTemplate.delete("msg.delete", msgno);
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#receive_msg()
   */
  @Override
  public List<MsgVO> list() {
    // TODO Auto-generated method stub
    return sqlSessionTemplate.selectList("msg.list");
  }

  /* (non-Javadoc)
   * @see dev.mvc.msg.MsgDAOInter#update(dev.mvc.msg.MsgVO)
   */
  @Override
  public int update(MsgVO msgVO) {
    return sqlSessionTemplate.update("msg.update", msgVO);
  }

  @Override
  public int login(MsgVO msgVO) {
    return 0;
  }

  @Override
  public int receive_count(int receiver) {
    return sqlSessionTemplate.selectOne("msg.receive_count", receiver);
  }

  @Override
  public int send_count(int sender) {
    return sqlSessionTemplate.selectOne("msg.send_count", sender);
  }

}
