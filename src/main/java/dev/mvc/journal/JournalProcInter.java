package dev.mvc.journal;

import java.util.ArrayList;
import java.util.List;

public interface JournalProcInter {

  /**
   * �������� ���
   * <insert id="create" parameterType="JournalVO">
   * @param journalVO
   * @return
   */
  public abstract int create(JournalVO journalVO);
  
  /**
   * ��������� �����ȣ �˻�
   * <select id="search_no" resultType="int">
   * @param employee_name
   * @return
   */
  public abstract int search_no(String employee_name);
  
  /**
   * <select id="list_by_schedule" resultType="Projectschedule_JournalVO" parameterType="int">
   * ������Ʈ���� �� �������� ���
   * @param projectscheduleno
   * @return
   */
  public abstract List<Projectschedule_JournalVO> list_by_schedule(int projectscheduleno);
  
  /**
   * �������� ��ȸ
   * <select id="read" resultType="JournalVO" parameterType="int">
   * @param journalno
   * @return
   */
  public abstract JournalVO read(int journalno);
  
  /**
   * �������� ����
   * <update id="update" parameterType="JournalVO">
   * @param journalVO
   * @return
   */
  public abstract int update(JournalVO journalVO);
  
  /**
   * String��  " , " �� �߶� ArrayList<String>���� ��ȯ
   * @param str
   * @return
   */
  public ArrayList<String> devide_String(String str);
  

}
