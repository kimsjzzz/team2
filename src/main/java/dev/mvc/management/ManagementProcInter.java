package dev.mvc.management;

import java.util.ArrayList;
import java.util.List;

public interface ManagementProcInter {

  /**
   * ������Ʈ ��� ��ȸ
   * <select id="read" resultType="ManagementVO" parameterType="int">
   * @param managementno
   * @return
   */
  public abstract ManagementVO read(int managementno);
  
  /**
   * managementno ���� ����
   * <select id="count_no">
   * @return
   */
   public abstract int count_no(int projectscheduleno);
   
   /**
    * ������Ʈ ��� ���
    * <select id="list" resultType="ManagementVO">
    * @return
    */
   public abstract List<ManagementVO> list(int projectscheduleno);
   
   /**
    * String��  " , " �� �߶� ArrayList<String>���� ��ȯ
    * @param str
    * @return
    */
   public ArrayList<String> devide_String(String str);
}
