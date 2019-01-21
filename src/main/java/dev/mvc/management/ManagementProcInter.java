package dev.mvc.management;

import java.util.ArrayList;
import java.util.List;

public interface ManagementProcInter {

  /**
   * 프로젝트 요소 조회
   * <select id="read" resultType="ManagementVO" parameterType="int">
   * @param managementno
   * @return
   */
  public abstract ManagementVO read(int managementno);
  
  /**
   * managementno 갯수 리턴
   * <select id="count_no">
   * @return
   */
   public abstract int count_no(int projectscheduleno);
   
   /**
    * 프로젝트 요소 목록
    * <select id="list" resultType="ManagementVO">
    * @return
    */
   public abstract List<ManagementVO> list(int projectscheduleno);
   
   /**
    * String을  " , " 로 잘라 ArrayList<String>으로 반환
    * @param str
    * @return
    */
   public ArrayList<String> devide_String(String str);
}
