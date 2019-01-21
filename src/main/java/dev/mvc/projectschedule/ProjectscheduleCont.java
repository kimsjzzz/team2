package dev.mvc.projectschedule;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.paydescript.PaydescriptVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;


@Controller
public class ProjectscheduleCont {
  @Autowired
  @Qualifier("dev.mvc.projectschedule.ProjectscheduleProc")
  private ProjectscheduleProcInter projectscheduleProc = null;
  
  public ProjectscheduleCont() {
    System.out.println("--> ProjectscheduleCont created.");
  }
  
  /**
   * ������Ʈ ���� ��� (GET)
   * http://localhost:9090/intranet/projectschedule/create.do
   * @return
   */
  @RequestMapping(value="/projectschedule/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/projectschedule/create"); // /webapp/proejctschedule/create.jsp

    return mav;
  }
  
  /**
   * ������Ʈ ���� ��� (POST)
   * http://localhost:9090/intranet/projectschedule/create.do
   * @param projectscheduleVO
   * @return
   */
  @RequestMapping(value = "/projectschedule/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ProjectscheduleVO projectscheduleVO) {
    ModelAndView mav = new ModelAndView();
    
    // ������� �����ͺ��̽����� �˻� �� �´� employeeno�� ã�Ƽ� VO�� SET
    String employee_name = projectscheduleVO.getEmployee_name();
    int employeeno = projectscheduleProc.search_no(employee_name);
    projectscheduleVO.setEmployeeno(employeeno);
    
    // ���
    int count = projectscheduleProc.create(projectscheduleVO);
    mav.setViewName("redirect:/projectschedule/create_message.jsp?count=" + count); // /webapp/projectschedule/create_message.jsp

   return mav;
 }
  
  /**
   * ������Ʈ ���� ��ü ���
   * http://localhost:9090/intranet/projectschedule/list.do
   * @return
   */
  @RequestMapping(value="/projectschedule/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<ProjectscheduleVO> list = projectscheduleProc.list();
    mav.addObject("list", list);
    
    mav.setViewName("/projectschedule/list"); // /webapp/projectschedule/list.jsp

    return mav;
  }
  
  /**
   * ������Ʈ ���� ��ȸ
   * http://172.16.12.27:9090/intranet/projectschedule/read_content.do?projectscheduleno=1
   * @param projectscheduleno
   * @return
   */
  @RequestMapping(value = "/projectschedule/read_content.do", method = RequestMethod.GET)
  public ModelAndView read(int projectscheduleno) {
    ModelAndView mav = new ModelAndView();

    ProjectscheduleVO projectscheduleVO = projectscheduleProc.read_content(projectscheduleno);
    mav.addObject("projectscheduleVO", projectscheduleVO);
    
    mav.setViewName("/projectschedule/read_content"); // /webapp/projectschedule/read_content.jsp
    
    return mav;
  }
  
  /**
   * ���� ���� (GET)
   * http://172.16.12.27:9090/intranet/projectschedule/update.do?projectscheduleno=1
   * @param paydescriptno
   * @return
   */
  @RequestMapping(value = "/projectschedule/update.do", method = RequestMethod.GET)
  public ModelAndView update(int projectscheduleno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/projectschedule/update"); // /webapp/paydescript/update.jsp

    ProjectscheduleVO projectscheduleVO = projectscheduleProc.read_content(projectscheduleno);
    mav.addObject("projectscheduleVO", projectscheduleVO);

    return mav;
  }

  /**
   * ���� ���� (POST)
   * http://172.16.12.27:9090/intranet/projectschedule/update.do
   * @param paydescriptno
   * @return
   */
  @RequestMapping(value = "/projectschedule/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes,
      ProjectscheduleVO projectscheduleVO){
/*      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage) {*/
    ModelAndView mav = new ModelAndView();
    

    int count = projectscheduleProc.update(projectscheduleVO);
    
    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("count", count);
   redirectAttributes.addAttribute("projectscheduleno", projectscheduleVO.getProjectscheduleno());
   /* 
    redirectAttributes.addAttribute("employee_name", paydescriptVO.getEmployee_name());
    redirectAttributes.addAttribute("department_name", paydescriptVO.getDepartment_name());
    redirectAttributes.addAttribute("word", word);
    redirectAttributes.addAttribute("nowPage", nowPage);
    */
    
    mav.setViewName("redirect:/projectschedule/update_message.jsp"); // /webapp/paydescript/update_message.jsp

    return mav;
  }
  
  /**
   * ������Ʈ ���� ���� (GET)
   * http://172.16.12.27:9090/intranet/projectschedule/delete.do?projectscheduleno=1
   * @param projectschedule
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/projectschedule/delete.do",
                            method = RequestMethod.GET,
                            produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(int projectscheduleno) {
    HttpHeaders responseHeaders = new HttpHeaders();

    ProjectscheduleVO projectscheduleVO = projectscheduleProc.read_content(projectscheduleno);
    
    JSONObject json = new JSONObject();
    json.put("project_name", projectscheduleVO.getProject_name());
    
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }  
  
  /**
   * ������Ʈ ���� ���� (POST)
   * http://172.16.12.27:9090/intranet/projectschedule/delete.do
   * @param projectschedule
   * @return
   */

  @RequestMapping(value = "/projectschedule/delete.do", 
                          method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, RedirectAttributes redirectAttributes,
      int projectscheduleno)
/*      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage)*/ {
    ModelAndView mav = new ModelAndView();

    ProjectscheduleVO projectscheduletVO = projectscheduleProc.read_content(projectscheduleno);
      
    int count = projectscheduleProc.delete(projectscheduleno);
     
    /*redirectAttributes.addAttribute("count", count); // 1 or 0
*/    /*
    redirectAttributes.addAttribute("month", paydescriptVO.getMonth()); // 1 or 0
    redirectAttributes.addAttribute("employee_name", paydescriptVO.getEmployee_name()); // 1 or 0
    redirectAttributes.addAttribute("department_name", paydescriptVO.getDepartment_name()); // 1 or 0
    redirectAttributes.addAttribute("word", word);
    redirectAttributes.addAttribute("nowPage", nowPage);
    */
    
    mav.setViewName("redirect:/projectschedule/list_by_state_paging.do"); // /webapp/projectschedule/list.jsp
    
    return mav;
  }    
  
  @RequestMapping(value="/projectschedule/seqnoUp.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_up(int projectscheduleno) {
    ModelAndView mav = new ModelAndView();
    
    projectscheduleProc.seqnoUp(projectscheduleno);
    mav.setViewName("redirect:/projectschedule/list_by_state_paging.do"); // /webapp/projectschedule/list.jsp
    
    return mav;
  }

  @RequestMapping(value="/projectschedule/seqnoDown.do", method=RequestMethod.POST)
  public ModelAndView seqnoDown(int projectscheduleno) {
    ModelAndView mav = new ModelAndView();
    
    projectscheduleProc.seqnoDown(projectscheduleno);
    mav.setViewName("redirect:/projectschedule/list_by_state_paging.do"); // /webapp/projectschedule/list.jsp
    
    return mav;
  }
  
  /**
   * ������Ʈ ���� ���� ��� ���
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/projectschedule/list_by_state.do", 
                                       method = RequestMethod.GET)
  public ModelAndView list_by_state(
      String project_state
/*      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage*/
      ) { 

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/projectschedule/list");   

    // �˻� ���
    if(project_state==null || project_state.equals("all")) {
      List<ProjectscheduleVO> list = projectscheduleProc.list();
      mav.addObject("list", list);
    } else if(project_state.equals("Developing")) {
      project_state = "������";
      List<ProjectscheduleVO> list = projectscheduleProc.list_by_state(project_state);
      mav.addObject("list", list);
    } else if(project_state.equals("Expect")) {
      project_state = "���࿹��";
      List<ProjectscheduleVO> list = projectscheduleProc.list_by_state(project_state);
      mav.addObject("list", list);
    } else if(project_state.equals("Complete")) {
      project_state = "����Ϸ�";
      List<ProjectscheduleVO> list = projectscheduleProc.list_by_state(project_state);
      mav.addObject("list", list);
    }

    return mav;
   }
  
  /**
   * ������Ʈ ���� ���� ��� ���
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/projectschedule/list_by_state_paging.do", 
                                       method = RequestMethod.GET)
  public ModelAndView list_by_state_paging(
      String project_state,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/projectschedule/list_by_state_paging"); 
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("project_state", project_state);    
    hashMap.put("nowPage", nowPage);    

    // �˻��� ���ڵ� ����
    int search_count = projectscheduleProc.search_count(hashMap);
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
    String paging = projectscheduleProc.paging(search_count, nowPage, project_state);
    mav.addObject("paging", paging);
    mav.addObject("search_count", search_count);
    mav.addObject("nowPage", nowPage);
    
    // �˻� ���
    if(project_state==null || project_state.equals("all")) {
      List<ProjectscheduleVO> list = projectscheduleProc.list();
      mav.addObject("list", list);
    } else if(project_state.equals("Developing")) {
      project_state = "������";
      List<ProjectscheduleVO> list = projectscheduleProc.list_by_state(project_state);
      mav.addObject("list", list);
    } else if(project_state.equals("Expect")) {
      project_state = "���࿹��";
      List<ProjectscheduleVO> list = projectscheduleProc.list_by_state(project_state);
      mav.addObject("list", list);
    } else if(project_state.equals("Complete")) {
      project_state = "����Ϸ�";
      List<ProjectscheduleVO> list = projectscheduleProc.list_by_state(project_state);
      mav.addObject("list", list);
    }

    return mav;
   }
}
