package dev.mvc.management;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.management.ManagementDAO") // DBMS ����� ����
public class ManagementDAO implements ManagementDAOInter {
  @Autowired  // ���� �������� ���� �ڵ� �Ҵ�, �����ڴ� new ��� ����.
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ManagementDAO() {
    System.out.println("--> ManagementDAO created.");
  }

  @Override
  public ManagementVO read(int managementno) {
    ManagementVO managementVO = sqlSessionTemplate.selectOne("management.read", managementno);
    
    return managementVO;
  }

  @Override
  public int count_no(int projectscheduleno) {
    int count = sqlSessionTemplate.selectOne("management.count_no", projectscheduleno);
    
    return count;
  }

  @Override
  public List<ManagementVO> list(int projectscheduleno) {
    List<ManagementVO> list = sqlSessionTemplate.selectList("management.list", projectscheduleno);
    
    return list;
  }
}
