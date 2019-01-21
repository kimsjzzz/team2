package dev.mvc.journal;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.management.ManagementProcInter;
import dev.mvc.management.ManagementVO;
import dev.mvc.projectschedule.ProjectscheduleProc;
import dev.mvc.projectschedule.ProjectscheduleProcInter;
import dev.mvc.projectschedule.ProjectscheduleVO;


@Controller
public class JournalCont {
  @Autowired
  @Qualifier("dev.mvc.journal.JournalProc")
  private JournalProcInter journalProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.projectschedule.ProjectscheduleProc")
  private ProjectscheduleProcInter projectscheduleProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.management.ManagementProc")
  private ManagementProcInter managementProc = null;
  
  public JournalCont() {
    System.out.println("--> JournalCont created.");
  }
  
  /**
   * �������� ��� (GET)
   * http://localhost:9090/intranet/journal/create.do
   * @return
   */
  @RequestMapping(value="/journal/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/journal/create"); // /webapp/journal/create.jsp

    return mav;
  }
  
  /**
   * �������� ��� (POST)
   * http://localhost:9090/intranet/journal/create.do
   * @param categoryVO
   * @return
   */
  @RequestMapping(value = "/journal/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, RedirectAttributes redirectAttributes, JournalVO journalVO) {
    ModelAndView mav = new ModelAndView();
    
    // ������� �����ͺ��̽����� �˻� �� �´� employeeno�� ã�Ƽ� VO�� SET
    String employee_name = journalVO.getRname();
    int employeeno = journalProc.search_no(employee_name);
    journalVO.setEmployeeno(employeeno);
    
    // ������Ʈ�� ����
    int projectscheduleno = journalVO.getProjectscheduleno();
    ProjectscheduleVO projectscheduleVO = projectscheduleProc.read_content(projectscheduleno);
    String project_name = projectscheduleVO.getProject_name();

    // journal_state�� SET
    journalVO.setJournal_state("��������");
    
    // projectscheduleno ����
    projectscheduleno = journalVO.getProjectscheduleno();
    redirectAttributes.addAttribute("projectscheduleno", projectscheduleno);
    redirectAttributes.addAttribute("project_name", project_name);
    
     // ���
     int count = journalProc.create(journalVO);
     mav.setViewName("redirect:/journal/create_message.jsp?count=" + count); // /webapp/journal/create_message.jsp

    return mav;
  }
  
  /**
   * ������Ʈ���� �׷캰 JSON ��� ��ü ���
   * http://localhost:9090/intranet/journal/list_by_schedule_json.do
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/journal/list_by_schedule_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_by_schedule_json(int projectscheduleno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    List<Projectschedule_JournalVO> list = journalProc.list_by_schedule(projectscheduleno);

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * �������� ��ȸ
   * http://172.16.12.27:9090/intranet/projectschedule/read.do?int journalno=1
   * @param int journalno
   * @return
   */
  @RequestMapping(value = "/journal/read.do", method = RequestMethod.GET)
  public ModelAndView read(int journalno) {
    ModelAndView mav = new ModelAndView();

    JournalVO journalVO = journalProc.read(journalno);
    
    ArrayList<String> items_list = journalProc.devide_String(journalVO.getItems());  // items �ڸ���
    ArrayList<String> itemsState_list = journalProc.devide_String(journalVO.getItems_state());  // items_state �ڸ���

    // System.out.println("Items: " + items);
    
    mav.addObject("items_list", items_list);  // �ڸ� items ����
    mav.addObject("itemsState_list", itemsState_list);  // �ڸ� itemsState_list ����
    mav.addObject("journalVO", journalVO);
    
    mav.setViewName("/journal/read"); // /webapp/journal/read.jsp
    
    return mav;
  }
  
  /**
   * �������� ���� (GET)
   * http://localhost:9090/intranet/journal/update.do
   * @return
   */
  @RequestMapping(value="/journal/update.do", method=RequestMethod.GET)
  public ModelAndView update(int journalno) {
    ModelAndView mav = new ModelAndView();
    
    JournalVO journalVO = journalProc.read(journalno);
/*    ArrayList<String> item_list = journalProc.devide_String(journalVO.getItems());
    ArrayList<String> itemState_list = journalProc.devide_String(journalVO.getItems_state());
    int item_count = item_list.size();
    */
    String items = journalVO.getItems();
    String items_state = journalVO.getItems_state();

    mav.addObject("journalVO", journalVO);
    mav.addObject("items", items);
    mav.addObject("items_state", items_state);
    mav.setViewName("/journal/update"); // /webapp/journal/update.jsp

    return mav;
  }
  
  /**
   * �������� ��ȸ
   * http://172.16.12.27:9090/intranet/projectschedule/read.do?int journalno=1
   * @param int journalno
   * @return
   */
  @RequestMapping(value = "/journal/list_table.do", method = RequestMethod.GET)
  public ModelAndView list_table(int projectscheduleno) {
    ModelAndView mav = new ModelAndView();

    List<ManagementVO> list = managementProc.list(projectscheduleno); // ������Ʈ ��ȣ
    ArrayList<String> items_list = new ArrayList<String>();
    ArrayList<String> items_state_list = new ArrayList<String>();
    
    int count_no = managementProc.count_no(projectscheduleno);  // 3 ��ȯ
    int[] array_count_items = new int[count_no];     // count_no ũ���� �迭 ���� 4 , 3 , 2 , 2

    for(int i=0; i<count_no; i++) {
      ManagementVO managementVO = managementProc.read(i+1);   // List �����ŭ VO �ݺ�
      
      String VO_items = managementVO.getItems();    // VO���� items get
      String VO_items_State = managementVO.getItems_state();    // VO���� items_state get
      
      items_list = managementProc.devide_String(VO_items);  // "," �� �������� ArrayList<String>�� input
      items_state_list = managementProc.devide_String(VO_items_State);  // "," �� �������� ArrayList<String>�� input
      
      int count_items = items_list.size();
      array_count_items[i] = count_items+1;   // �о�� ������ items�� ũ����� �迭�� ����
    }
    
    mav.addObject("array_count_items", array_count_items);
    mav.addObject("list", list);
    // mav.addObject("managementVO", managementVO);
    
    return mav;
  }
  
}
