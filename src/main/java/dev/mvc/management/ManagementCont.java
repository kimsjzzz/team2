package dev.mvc.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import dev.mvc.projectschedule.ProjectscheduleProcInter;

@Controller
public class ManagementCont {
  @Autowired
  @Qualifier("dev.mvc.management.ManagementProc")
  private ManagementProcInter managementProc = null;

  @Autowired
  @Qualifier("dev.mvc.projectschedule.ProjectscheduleProc")
  private ProjectscheduleProcInter projectscheduleProc = null;
  
  public ManagementCont() {
    System.out.println("--> ManagementCont created.");
  }
}
