package dev.mvc.msg;

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
public class MsgVO {
  
  /** �޽��� ��ȣ */
  private int msgno;
  /** �޽��� ���� */
  private String content;
  /** �޽��� ������¥ */
  private String rdate;
  /** �޽��� ��ȸ��¥ */
  private String vdate;
  /** �޽��� ��ȸ���� */
  private String vcnt;
  /** �޽��� ������� */
  private int sender;
  /** �޽��� �޴»�� */
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
