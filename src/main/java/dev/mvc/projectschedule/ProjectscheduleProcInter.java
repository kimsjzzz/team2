package dev.mvc.projectschedule;

import java.util.HashMap;
import java.util.List;

public interface ProjectscheduleProcInter {

  /**
   * ������Ʈ ���� ���
   * <insert id="create" parameterType="ProjectscheduleVO">
   * @param projectscheduleVO
   * @return
   */
  public abstract int create(ProjectscheduleVO projectscheduleVO);
  
  /**
   * ������Ʈ ���� ���
   * <select id="list" resultType="ProjectscheduleVO">
   * @return
   */
  public abstract List<ProjectscheduleVO> list();
  
  /**
   * ������Ʈ ���� ���� ��ȸ
   * <select id="read_content" parameterType="int" resultType="ProjectscheduleVO" >
   * @param projectscheduleno
   * @return
   */
  public abstract ProjectscheduleVO read_content(int projectscheduleno);
  
  /**
   * ��������� �����ȣ �˻�
   * <select id="search_no" resultType="int">
   * @param employee_name
   * @return
   */
  public abstract int search_no(String employee_name);
  
  /**
   * ������Ʈ ���� ����
   * <update id="update" parameterType="ProjectscheduleVO">
   * @param projectschedule
   * @return
   */
  public abstract int update(ProjectscheduleVO projectscheduleVO);
  
  /**
   * ������Ʈ ���� ����
   * <delete id="delete" parameterType="int">
   * @param projectscheduleno
   * @return
   */
  public abstract int delete(int projectscheduleno);
  
  /**
   * �켱���� ����
   * @param projectscheduleno
   * @return
   */
  public abstract int seqnoUp(int projectscheduleno);
  
  /**
   * �켱���� ����
   * @param projectscheduleno
   * @return
   */
  public abstract int seqnoDown(int projectscheduleno);
  
  /**
   * ������Ʈ ���� ��� ���
   * @param state
   * @return
   */
  public abstract List<ProjectscheduleVO> list_by_state(String project_state);
  
  /**
   * �˻��� ���ڵ� ����
   * @return
   */
  public abstract int search_count(HashMap<String, Object> hashMap);
  
  /**
   * ���� + ����¡
   * <select id="list_by_state_paging" parameterType="HashMap" resultType="ProjectscheduleVO">
   * @param hashMap
   * @return
   */
  public abstract List<ProjectscheduleVO> list_by_state_paging(HashMap<String, Object> hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String project_state);
}
