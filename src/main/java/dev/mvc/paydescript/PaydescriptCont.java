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
   * ���� ��� (GET)
   * http://localhost:9090/intranet/payscript/create.do
   * @return
   */
  @RequestMapping(value="/paydescript/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    /** list ���·� �μ����� �����ִ� �ڵ� ���� */
    List<EmployeeVO> list = employeeProc.team_list();
    mav.addObject("list", list);
    /** list ���·� �μ����� �����ִ� �ڵ� ���� */
    
    mav.setViewName("/paydescript/create"); // /webapp/paydescript/create.jsp

    return mav;
  }
  
  /**
   * ���� ��� (POST)
   * http://localhost:9090/intranet/paydescript/create.do
   * @param categoryVO
   * @return
   */
  @RequestMapping(value = "/paydescript/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, PaydescriptVO paydescriptVO) {
    ModelAndView mav = new ModelAndView();
    
    // ������� �����ͺ��̽����� �˻� �� �´� employeeno�� ã�Ƽ� VO�� SET
    String employee_name = paydescriptVO.getEmployee_name();
    int employeeno = paydescriptProc.search_no(employee_name);
    paydescriptVO.setEmployeeno(employeeno);
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/paydescript/storage");
    MultipartFile file1MF = paydescriptVO.getFile1MF();
    String file1 = ""; // �÷��� ������ ���ϸ�
    long size1 = file1MF.getSize();
  //  System.out.println("file1MF: " + file1MF);
    if (size1 > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
    }
  //  System.out.println("file1: " + file1);
    paydescriptVO.setFile1(file1);

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    
     // ���
     int count = paydescriptProc.create(paydescriptVO);
     mav.setViewName("redirect:/paydescript/create_message.jsp?count=" + count); // /webapp/payscript/create_message.jsp

    // mav.setViewName("redirect:/category/list.do"); //
    // /webapp/category/list.jsp

    return mav;
  }
  
  /**
   * ���� ��ü ���
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
   * ���� ��ȸ
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
   * ���� ���� (GET)
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
   * ���� ���� (POST)
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
    // ���� ����
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/paydescript/storage");
    /*
    <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
    ��
     name='file1MF'�� �ش��ϴ� �ʵ带 ã�Ƽ� File ��ü�� �ڵ����� �Ҵ�
    ��
    BlogVO.java: private MultipartFile file1MF;
    ��
     ���� ��ü ���: MultipartFile file1MF = blogVO.getFile1MF();          
     */
    MultipartFile file1MF = paydescriptVO.getFile1MF();
    String file1 = "";                    // DBMS file1 �÷��� ��
    long size1 = file1MF.getSize(); // ���� ũ��
    
    // ������ ��ϵ� �� ���� �ε�
    PaydescriptVO paydescriptVO_old = paydescriptProc.read(paydescriptVO.getPaydescriptno());
    
    if (size1 > 0) { // ��ϵ� ������ �ִٸ�
      Tool.deleteFile(upDir, paydescriptVO_old.getFile1());    // ���� ���� ����
 
      file1 = Upload.saveFileSpring(file1MF, upDir); // �ű� ���� ���ε�
      
      if (Tool.isImage(file1)) { // ���ο� ���� �̹������� �˻�
        
      } 
    } else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
      file1 = paydescriptVO_old.getFile1();
    }
    paydescriptVO.setFile1(file1);

    // ---------------------------------------------------------------------------

    int count = paydescriptProc.update(paydescriptVO);
    
    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
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
   * ���� ���� (GET)
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
   * ���� ���� (POST)
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
    // ���� ����
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/paydescript/storage");

    Tool.deleteFile(upDir, paydescriptVO.getFile1());    // ���� ���� ����
      
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
   * �˻� ���
   * 
   * @param employeeno
   * @param word
   * @return
   */
  @RequestMapping(value = "/paydescript/list_by_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_search(@RequestParam(value="word", defaultValue="") String word) {

    ModelAndView mav = new ModelAndView();

    // �˻� ��� �߰�, webapp/paydescript/list_by_search.jsp
    mav.setViewName("/paydescript/list_by_search");

    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    hashMap.put("word", word); // #{word}

    // �˻� ���
    List<PaydescriptVO> list = paydescriptProc.list_by_search(hashMap);
    mav.addObject("list", list);

    // �˻��� ���ڵ� ����
    int search_count = paydescriptProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    return mav;
  }
 
  /**
   * ��� + �˻� + ����¡ ����
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
    // �˻� ��� �߰�,  /webapp/paydescript/list_by_search_paging.jsp
    mav.setViewName("/paydescript/list_by_search_paging");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", word);                  // #{word}
      hashMap.put("nowPage", nowPage);    

    // �˻� ���
    if(mon==null || mon.equals("all")) {
      List<PaydescriptVO> list = paydescriptProc.list_by_search_paging(hashMap);
      mav.addObject("list", list);
      
      // �˻��� ���ڵ� ����
      int search_count = paydescriptProc.search_count(hashMap);
      mav.addObject("search_count", search_count);
      
      /*
       * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
       * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
       *
       * @param categoryno ī�װ���ȣ 
       * @param search_count �˻�(��ü) ���ڵ�� 
       * @param nowPage     ���� ������
       * @param word �˻���
       * @return ����¡ ���� ���ڿ�
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
