package dev.mvc.paydescript;

import java.io.File;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.employee.EmployeeProcInter;
import dev.mvc.employee.EmployeeVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class PaydescriptCont {
  
  @Autowired
  @Qualifier("dev.mvc.paydescript.PaydescriptProc")
  private PaydescriptProcInter paydescriptProc = null;
  
  public PaydescriptCont() {
    System.out.println("--> PaydescriptCont created.");
  }
  
  @Autowired
  @Qualifier("dev.mvc.employee.EmployeeProc")
  private EmployeeProcInter employeeProc = null;
  
  
  /**
   * 명세서 등록 (GET)
   * http://localhost:9090/intranet/payscript/create.do
   * @return
   */
  @RequestMapping(value="/paydescript/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    /** list 형태로 부서명을 보내주는 코드 시작 */
    List<EmployeeVO> list = employeeProc.team_list();
    mav.addObject("list", list);
    /** list 형태로 부서명을 보내주는 코드 종료 */
    
    mav.setViewName("/paydescript/create"); // /webapp/paydescript/create.jsp

    return mav;
  }
  
  /**
   * 명세서 등록 (POST)
   * http://localhost:9090/intranet/paydescript/create.do
   * @param categoryVO
   * @return
   */
  @RequestMapping(value = "/paydescript/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, PaydescriptVO paydescriptVO) {
    ModelAndView mav = new ModelAndView();
    
    // 사원명을 데이터베이스에서 검색 해 맞는 employeeno를 찾아서 VO에 SET
    String employee_name = paydescriptVO.getEmployee_name();
    int employeeno = paydescriptProc.search_no(employee_name);
    paydescriptVO.setEmployeeno(employeeno);
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/paydescript/storage");
    MultipartFile file1MF = paydescriptVO.getFile1MF();
    String file1 = ""; // 컬럼에 저장할 파일명
    long size1 = file1MF.getSize();
  //  System.out.println("file1MF: " + file1MF);
    if (size1 > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
    }
  //  System.out.println("file1: " + file1);
    paydescriptVO.setFile1(file1);

    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
     // 등록
     int count = paydescriptProc.create(paydescriptVO);
     mav.setViewName("redirect:/paydescript/create_message.jsp?count=" + count); // /webapp/payscript/create_message.jsp

    // mav.setViewName("redirect:/category/list.do"); //
    // /webapp/category/list.jsp

    return mav;
  }
  
  /**
   * 명세서 전체 목록
   * http://localhost:9090/intranet/paydescript/list.do
   * @return
   */
  @RequestMapping(value="/paydescript/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<PaydescriptVO> list = paydescriptProc.list();
    mav.addObject("list", list);
    
    mav.setViewName("/paydescript/list"); // /webapp/category/list.jsp

    return mav;
  }
  
  /**
   * 명세서 조회
   * http://172.16.12.27:9090/intranet/paydescript/read.do?paydescriptno=1
   * @param paydescriptno
   * @return
   */
  @RequestMapping(value = "/paydescript/read.do", method = RequestMethod.GET)
  public ModelAndView read(int paydescriptno) {
    ModelAndView mav = new ModelAndView();

    PaydescriptVO paydescriptVO = paydescriptProc.read(paydescriptno);
    // System.out.println("paydescriptno: " + paydescriptVO.getPaydescriptno());
    String file1 = paydescriptVO.getFile1();
    mav.addObject("paydescriptVO", paydescriptVO);
    
    mav.setViewName("/paydescript/read"); // /webapp/contents/read.jsp
    
    return mav;
  }
  
  /**
   * 명세서 수정 (GET)
   * http://172.16.12.27:9090/intranet/paydescript/update.do?paydescriptno=1
   * @param paydescriptno
   * @return
   */
  @RequestMapping(value = "/paydescript/update.do", method = RequestMethod.GET)
  public ModelAndView update(int paydescriptno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/paydescript/update"); // /webapp/paydescript/update.jsp

    PaydescriptVO paydescriptVO = paydescriptProc.read(paydescriptno);
    mav.addObject("paydescriptVO", paydescriptVO);

    return mav;
  }

  /**
   * 명세서 수정 (POST)
   * http://172.16.12.27:9090/intranet/paydescript/update.do
   * @param paydescriptno
   * @return
   */
  @RequestMapping(value = "/paydescript/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes, PaydescriptVO paydescriptVO,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    ModelAndView mav = new ModelAndView();
    
    // ---------------------------------------------------------------------------
    // 파일 전송
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/paydescript/storage");
    /*
    <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
    ↓
     name='file1MF'에 해당하는 필드를 찾아서 File 객체를 자동으로 할당
    ↓
    BlogVO.java: private MultipartFile file1MF;
    ↓
     파일 객체 사용: MultipartFile file1MF = blogVO.getFile1MF();          
     */
    MultipartFile file1MF = paydescriptVO.getFile1MF();
    String file1 = "";                    // DBMS file1 컬럼의 값
    long size1 = file1MF.getSize(); // 파일 크기
    
    // 기존에 등록된 글 정보 로딩
    PaydescriptVO paydescriptVO_old = paydescriptProc.read(paydescriptVO.getPaydescriptno());
    
    if (size1 > 0) { // 등록된 파일이 있다면
      Tool.deleteFile(upDir, paydescriptVO_old.getFile1());    // 기존 파일 삭제
 
      file1 = Upload.saveFileSpring(file1MF, upDir); // 신규 파일 업로드
      
      if (Tool.isImage(file1)) { // 새로운 파일 이미지인지 검사
        
      } 
    } else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      file1 = paydescriptVO_old.getFile1();
    }
    paydescriptVO.setFile1(file1);

    // ---------------------------------------------------------------------------

    int count = paydescriptProc.update(paydescriptVO);
    
    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("count", count);
    redirectAttributes.addAttribute("paydescriptno", paydescriptVO.getPaydescriptno());
    redirectAttributes.addAttribute("employee_name", paydescriptVO.getEmployee_name());
    redirectAttributes.addAttribute("department_name", paydescriptVO.getDepartment_name());
    redirectAttributes.addAttribute("word", word);
    redirectAttributes.addAttribute("nowPage", nowPage);
    
    mav.setViewName("redirect:/paydescript/update_message.jsp"); // /webapp/paydescript/update_message.jsp

    return mav;
  }
  
  /**
   * 명세서 삭제 (GET)
   * http://172.16.12.27:9090/intranet/paydescript/delete.do?paydescriptno=1
   * @param paydescriptno
   * @return
   */
  @RequestMapping(value = "/paydescript/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int paydescriptno) {
    ModelAndView mav = new ModelAndView();

    PaydescriptVO paydescriptVO = paydescriptProc.read(paydescriptno);
    mav.addObject("paydescriptVO", paydescriptVO);
    
    mav.setViewName("/paydescript/delete"); // /webapp/contents/read.jsp
    
    return mav;
  }  
  
  /**
   * 명세서 삭제 (POST)
   * http://172.16.12.27:9090/intranet/paydescript/delete.do
   * @param paydescriptno
   * @return
   */
  @RequestMapping(value = "/paydescript/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, RedirectAttributes redirectAttributes, int paydescriptno,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    ModelAndView mav = new ModelAndView();

    PaydescriptVO paydescriptVO = paydescriptProc.read(paydescriptno);
      
    // ---------------------------------------------------------------------------
    // 파일 삭제
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/paydescript/storage");

    Tool.deleteFile(upDir, paydescriptVO.getFile1());    // 기존 파일 삭제
      
    // ---------------------------------------------------------------------------
    
    int count = paydescriptProc.delete(paydescriptno);
     
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("month", paydescriptVO.getMonth()); // 1 or 0
    redirectAttributes.addAttribute("employee_name", paydescriptVO.getEmployee_name()); // 1 or 0
    redirectAttributes.addAttribute("department_name", paydescriptVO.getDepartment_name()); // 1 or 0
    redirectAttributes.addAttribute("word", word);
    redirectAttributes.addAttribute("nowPage", nowPage);
    
    mav.setViewName("redirect:/paydescript/delete_message.jsp"); // /webapp/paydescript/delete_message.jsp
    
    return mav;
  }    
  
  /**
   * 검색 목록
   * 
   * @param employeeno
   * @param word
   * @return
   */
  @RequestMapping(value = "/paydescript/list_by_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_search(@RequestParam(value="word", defaultValue="") String word) {

    ModelAndView mav = new ModelAndView();

    // 검색 기능 추가, webapp/paydescript/list_by_search.jsp
    mav.setViewName("/paydescript/list_by_search");

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    hashMap.put("word", word); // #{word}

    // 검색 목록
    List<PaydescriptVO> list = paydescriptProc.list_by_search(hashMap);
    mav.addObject("list", list);

    // 검색된 레코드 갯수
    int search_count = paydescriptProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    return mav;
  }
 
  /**
   * 목록 + 검색 + 페이징 지원
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/paydescript/list_by_search_paging.do", 
                                       method = RequestMethod.GET)
  public ModelAndView list_by_category_search_paging(
      String mon,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 

    ModelAndView mav = new ModelAndView();
    // 검색 기능 추가,  /webapp/paydescript/list_by_search_paging.jsp
    mav.setViewName("/paydescript/list_by_search_paging");   
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", word);                  // #{word}
      hashMap.put("nowPage", nowPage);    

    // 검색 목록
    if(mon==null || mon.equals("all")) {
      List<PaydescriptVO> list = paydescriptProc.list_by_search_paging(hashMap);
      mav.addObject("list", list);
      
      // 검색된 레코드 갯수
      int search_count = paydescriptProc.search_count(hashMap);
      mav.addObject("search_count", search_count);
      
      /*
       * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
       * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
       *
       * @param categoryno 카테고리번호 
       * @param search_count 검색(전체) 레코드수 
       * @param nowPage     현재 페이지
       * @param word 검색어
       * @return 페이징 생성 문자열
       */ 
      String paging = paydescriptProc.paging(search_count, nowPage, word);
      mav.addObject("paging", paging);
      mav.addObject("search_count", search_count);
      mav.addObject("nowPage", nowPage);
    } else if(mon.equals("January")) {
      String month = "1";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("February")) {
      String month = "2";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("March")) {
      String month = "3";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("April")) {
      String month = "4";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("May")) {
      String month = "5";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("June")) {
      String month = "6";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("July")) {
      String month = "7";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("August")) {
      String month = "8";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("September")) {
      String month = "9";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("October")) {
      String month = "10";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("November")) {
      String month = "11";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    } else if(mon.equals("December")) {
      String month = "12";
      hashMap.put("month", month);
      List<PaydescriptVO> list = paydescriptProc.list_by_month(hashMap);
      mav.addObject("list", list);
    }

    return mav;
   }
  
}
