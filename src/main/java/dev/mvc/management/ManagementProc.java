package dev.mvc.management;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("dev.mvc.management.ManagementProc")
public class ManagementProc implements ManagementProcInter {
  @Autowired
  @Qualifier("dev.mvc.management.ManagementDAO")
  private ManagementDAOInter managementDAO = null;
  
  public ManagementProc() {
    System.out.println("--> ManagementProc created.");
  }

  @Override
  public ManagementVO read(int managementno) {
    ManagementVO managementVO = this.managementDAO.read(managementno);
    
    return managementVO;
  }

  @Override
  public int count_no(int projectscheduleno) {
    int count = this.managementDAO.count_no(projectscheduleno);
    
    return count;
  }

  @Override
  public List<ManagementVO> list(int projectscheduleno) {
    List<ManagementVO> list = this.managementDAO.list(projectscheduleno);
    
    return list;
  }
 
  @Override
  public ArrayList<String> devide_String(String str) {
    ArrayList<String> list = new ArrayList<String>();
    StringTokenizer token = new StringTokenizer(str, ",");  // " , "�� �������� token ����

    while(token.hasMoreTokens()) {  // ��ū�� ������ �� ����
      list.add(token.nextToken());    // ��ū�� ArrayList�� add
    }
    
    return list; 
  }

}
