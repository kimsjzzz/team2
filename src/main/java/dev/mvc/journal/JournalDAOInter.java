package dev.mvc.journal;

import java.util.List;

public interface JournalDAOInter {

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
   * ������Ʈ���� �� �������� ���
   * <select id="list_by_schedule" resultType="Projectschedule_JournalVO" parameterType="int">
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

}
