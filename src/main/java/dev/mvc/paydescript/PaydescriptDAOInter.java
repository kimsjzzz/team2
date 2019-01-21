package dev.mvc.paydescript;

import java.util.HashMap;
import java.util.List;


public interface PaydescriptDAOInter {

  /**
   * ���� ���
   * <insert id="create" parameterType="PayscriptVO">
   * @param paydescriptVO
   * @return count 0: ���� / 1: ����
   */
  public abstract int create(PaydescriptVO paydescriptVO);
  
  /**
   * ���� ��ü ���
   * <select id="list" resultType="PaydescriptVO">
   * @return
   */
  public abstract List<PaydescriptVO> list();
  
  /**
   * ���� ��ȸ
   * <select id="read" resultType="PaydescriptVO" parameterType="int">
   * @param paydescriptno ������ȣ
   * @return
   */
  public abstract PaydescriptVO read(int paydescriptno);
  
  /**
   * ���� ����
   * <update id="update" parameterType="PaydescriptVO">
   * @param paydescriptVO
   * @return count 0: ���� / 1: ����
   */
  public abstract int update(PaydescriptVO paydescriptVO);
  
  /**
   * ���� ����
   * <delete id="delete" parameterType="int">
   * @param paydescriptno
   * @return count 0: ���� / 1: ����
   */
  public abstract int delete(int paydescriptno);
  
  /**
   * �˻� ���
   * @param categoryno
   * @return
   */
  public abstract List<PaydescriptVO> list_by_search(HashMap<String, Object> hashMap);
  
  /**
   * �˻��� ���ڵ� ����
   * @return
   */
  public abstract int search_count(HashMap<String, Object> hashMap);
  
  /**
   * �˻� + ����¡
   * @param hashMap
   * @return
   */
  public abstract List<PaydescriptVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * ��������� �����ȣ �˻�
   * <select id="search_no" resultType="int">
   * @param employee_name
   * @return
   */
  public abstract int search_no(String employee_name);
  
  /**
   * �˻��� ���� ��� 
   * @param month
   * @return
   */
  public abstract List<PaydescriptVO> list_by_month(HashMap<String, Object> hashMap);
}
