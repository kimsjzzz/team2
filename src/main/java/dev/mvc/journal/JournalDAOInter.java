package dev.mvc.journal;

import java.util.List;

public interface JournalDAOInter {

  /**
   * 개발일지 등록
   * <insert id="create" parameterType="JournalVO">
   * @param journalVO
   * @return
   */
  public abstract int create(JournalVO journalVO);
  
  /**
   * 사원명으로 사원번호 검색
   * <select id="search_no" resultType="int">
   * @param employee_name
   * @return
   */
  public abstract int search_no(String employee_name);
  
  /**
   * 프로젝트일정 별 개발일지 목록
   * <select id="list_by_schedule" resultType="Projectschedule_JournalVO" parameterType="int">
   * @param projectscheduleno
   * @return
   */
  public abstract List<Projectschedule_JournalVO> list_by_schedule(int projectscheduleno);
  
  /**
   * 개발일지 조회
   * <select id="read" resultType="JournalVO" parameterType="int">
   * @param journalno
   * @return
   */
  public abstract JournalVO read(int journalno);
  
  /**
   * 개발일지 수정
   * <update id="update" parameterType="JournalVO">
   * @param journalVO
   * @return
   */
  public abstract int update(JournalVO journalVO);

}
