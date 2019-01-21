package dev.mvc.paydescript;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.paydescript.PaydescriptDAO") // DBMS 저장소 접근 
public class PaydescriptDAO implements PaydescriptDAOInter {
  @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public PaydescriptDAO() {
    System.out.println("--> PaydescriptDAO created.");
  }
  
  @Override
  public int create(PaydescriptVO paydescriptVO) {
    int count = sqlSessionTemplate.insert("Paydescript.create", paydescriptVO);
    
    return count;
  }

  @Override
  public List<PaydescriptVO> list() {
    List<PaydescriptVO> list = sqlSessionTemplate.selectList("Paydescript.list");
    
    return list;
  }

  @Override
  public PaydescriptVO read(int paydescriptno) {
    PaydescriptVO paydesciptVO = sqlSessionTemplate.selectOne("Paydescript.read", paydescriptno);
    
    return paydesciptVO;
  }

  @Override
  public int update(PaydescriptVO paydescriptVO) {
    int count = sqlSessionTemplate.update("Paydescript.update", paydescriptVO);
    
    return count;
  }

  @Override
  public int delete(int paydescriptno) {
    int count = sqlSessionTemplate.delete("Paydescript.delete", paydescriptno);
    
    return count;
  }

  @Override
  public List<PaydescriptVO> list_by_search(HashMap<String, Object> hashMap) {
    List<PaydescriptVO> list = sqlSessionTemplate.selectList("Paydescript.list_by_search", hashMap);
    
    return list;
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int count = sqlSessionTemplate.selectOne("Paydescript.search_count", hashMap);
  
    return count;
  }

  @Override
  public List<PaydescriptVO> list_by_search_paging(HashMap<String, Object> hashMap) {
    List<PaydescriptVO> list = sqlSessionTemplate.selectList("Paydescript.list_by_search_paging", hashMap);

    return list;
  }

  @Override
  public int search_no(String employee_name) {
    int employeeno = sqlSessionTemplate.selectOne("Paydescript.search_no", employee_name);
    
    return employeeno;
  }

  @Override
  public List<PaydescriptVO> list_by_month(HashMap<String, Object> hashMap) {
    List<PaydescriptVO> list = sqlSessionTemplate.selectList("Paydescript.list_by_month", hashMap);
    
    return list;
  }

}
