package dev.mvc.projectschedule;

import java.util.HashMap;
import java.util.List;

public interface ProjectscheduleProcInter {

  /**
   * 프로젝트 일정 등록
   * <insert id="create" parameterType="ProjectscheduleVO">
   * @param projectscheduleVO
   * @return
   */
  public abstract int create(ProjectscheduleVO projectscheduleVO);
  
  /**
   * 프로젝트 일정 목록
   * <select id="list" resultType="ProjectscheduleVO">
   * @return
   */
  public abstract List<ProjectscheduleVO> list();
  
  /**
   * 프로젝트 일정 내용 조회
   * <select id="read_content" parameterType="int" resultType="ProjectscheduleVO" >
   * @param projectscheduleno
   * @return
   */
  public abstract ProjectscheduleVO read_content(int projectscheduleno);
  
  /**
   * 사원명으로 사원번호 검색
   * <select id="search_no" resultType="int">
   * @param employee_name
   * @return
   */
  public abstract int search_no(String employee_name);
  
  /**
   * 프로젝트 일정 수정
   * <update id="update" parameterType="ProjectscheduleVO">
   * @param projectschedule
   * @return
   */
  public abstract int update(ProjectscheduleVO projectscheduleVO);
  
  /**
   * 프로젝트 일정 삭제
   * <delete id="delete" parameterType="int">
   * @param projectscheduleno
   * @return
   */
  public abstract int delete(int projectscheduleno);
  
  /**
   * 우선순위 높힘
   * @param projectscheduleno
   * @return
   */
  public abstract int seqnoUp(int projectscheduleno);
  
  /**
   * 우선순위 낮춤
   * @param projectscheduleno
   * @return
   */
  public abstract int seqnoDown(int projectscheduleno);
  
  /**
   * 프로젝트 상태 기반 목록
   * @param state
   * @return
   */
  public abstract List<ProjectscheduleVO> list_by_state(String project_state);
  
  /**
   * 검색된 레코드 갯수
   * @return
   */
  public abstract int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 상태 + 페이징
   * <select id="list_by_state_paging" parameterType="HashMap" resultType="ProjectscheduleVO">
   * @param hashMap
   * @return
   */
  public abstract List<ProjectscheduleVO> list_by_state_paging(HashMap<String, Object> hashMap);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String project_state);
}
