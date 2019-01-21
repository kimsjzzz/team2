package dev.mvc.paydescript;

import java.util.HashMap;
import java.util.List;

public interface PaydescriptProcInter {

  /**
   * 등록
   * <insert id="create" parameterType="PayscriptVO">
   * @param paydescriptVO
   * @return count 0: 실패 / 1: 성공
   */
  public abstract int create(PaydescriptVO paydescriptVO);
  
  /**
   * 명세서 전체 목록
   * <select id="list" resultType="PaydescriptVO">
   * @return
   */
  public abstract List<PaydescriptVO> list();
  
  /**
   * 명세서 조회
   * <select id="read" resultType="PaydescriptVO" parameterType="int">
   * @param paydescriptno 명세서번호
   * @return
   */
  public abstract PaydescriptVO read(int paydescriptno);
  
  /**
   * 명세서 수정
   * <update id="update" parameterType="PaydescriptVO">
   * @param paydescriptno
   * @return count 0: 실패 / 1: 성공
   */
  public abstract int update(PaydescriptVO paydescriptVO);
  
  /**
   * 명세서 삭제
   * <delete id="delete" parameterType="int">
   * @param paydescriptno
   * @return count 0: 실패 / 1: 성공
   */
  public abstract int delete(int paydescriptno);
  
  /**
   * 검색 목록
   * @param categoryno
   * @return
   */
  public List<PaydescriptVO> list_by_search(HashMap<String, Object> hashMap);
  
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 검색 + 페이징
   * @param hashMap
   * @return
   */
  public abstract List<PaydescriptVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
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
  public String paging(int search_count, int nowPage, String word);
  
  /**
   * 사원명으로 사원번호 검색
   * <select id="search_no" resultType="int">
   * @param employee_name
   * @return
   */
  public abstract int search_no(String employee_name);
  
  /**
   * 검색한 월별 목록 
   * @param month
   * @return
   */
  public abstract List<PaydescriptVO> list_by_month(HashMap<String, Object> hashMap);
}
