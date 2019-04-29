/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.bh.servlets;

import hu.bh.dzzt.dtos.DepartmentDTO;
import hu.bh.dzzt.dtos.EmployeeDTO;
import hu.bh.dzzt.dtos.UserDTO;
import hu.bh.dzzt.ejbs.SQLProviderRemote;
import hu.bh.utils.EmployeeData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zolta
 */
public class SalaryChangeServlet extends HttpServlet {

  @EJB
  private SQLProviderRemote sqlProviderBean;
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("userbean");
		if(null!=user && user.getRole()>=0){
    //TODO, jogosultság vizsgálat
			updatePage(request, response, request.getParameter("sort"), null, null);
		} else
			response.sendRedirect("login");
  }
    
  private void updatePage(HttpServletRequest request, HttpServletResponse response, String sortType, Long id, Long newSalary) 
          throws ServletException, IOException {
    if(sortType == null || sortType.isEmpty())
      sortType = request.getParameter("sort");
    if(sortType == null || sortType.isEmpty())
      sortType = "idinc";
    List<EmployeeDTO> employees = sqlProviderBean.getEmployeeList();
    switch(sortType){
      case "idinc": sortById(employees, true); break;
      case "iddec": sortById(employees, false); break;
      case "nameinc": sortByName(employees, true); break;
      case "namedec": sortByName(employees, false); break;
      case "depinc": sortByDepartment(employees, true); break;
      case "depdec": sortByDepartment(employees, false); break;
      case "maninc": sortByManager(employees, true); break;
      case "mandec": sortByManager(employees, false); break;
      case "jobinc": sortByJob(employees, true); break;
      case "jobdec": sortByJob(employees, false); break;
      default:
        sortById(employees, true); break;
    }
    //prepare list for jsp
    List<DepartmentDTO> departments = sqlProviderBean.getDepartmentList();
    departments.add(new DepartmentDTO(-1L, "has no department", null));
    long[] sumOfSalary = new long[departments.size()];
    for (int i = 0; i < departments.size(); i++) {
      sumOfSalary[i] = 0;
    }
    //calculate salary sums for departments
    for (int i = 0; i < sumOfSalary.length; i++) {
      for (EmployeeDTO employee : employees) {
        if((i == sumOfSalary.length - 1 && employee.getDepartment() == null) ||
            employee.getDepartment() != null && employee.getDepartment().getDepartmentId() == departments.get(i).getDepartmentId()){
          sumOfSalary[i] += employee.getSalary();
          employee.setDepartment(departments.get(i)); //link the employees to the DTO-s in the department list
        }
      }
    }
    //build user list for the jsp
    ArrayList<EmployeeData> employeeDataList = new ArrayList<>();
    for (int i = 0; i < employees.size(); i++) {
      EmployeeDTO employee = employees.get(i);
      EmployeeData employeeData = new EmployeeData();
      employeeData.setId(employee.getEmployeeId());
      employeeData.setName(employee.getLastName() + ", " + employee.getFirstName());
      employeeData.setDepartment(employee.getDepartment().getDepartmentName());
      employeeData.setManager(employee.getManager() == null ? "has no manager" : employee.getManager().getLastName() + ", " + employee.getManager().getFirstName());
      employeeData.setJob(employee.getJob().getJobTitle());
      if(id != null && id == employee.getEmployeeId()){
        employeeData.setSalary(newSalary);
        employeeData.setSalaryOk(false);
      }
      else{
        employeeData.setSalary(employee.getSalary());
        employeeData.setSalaryOk(true);
      }
      int j = departments.indexOf(employee.getDepartment());
      double ds = 0.05 * employee.getSalary();
      if(j >= 0){
        ds = Math.min(0.03 * sumOfSalary[j], ds);
      }
      employeeData.setMinSalary((long)(employee.getSalary() - ds));
      employeeData.setMaxSalary((long)(employee.getSalary() + ds));
      employeeDataList.add(employeeData);
    }
    request.setAttribute("employeeList", employeeDataList);
    request.setAttribute("sort", sortType);
    request.getRequestDispatcher("WEB-INF/SalaryChage.jsp").forward(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String event = request.getParameter("event");
    String id = request.getParameter("id");
    String salary = request.getParameter("salary");
    if("update".equals(event) && id != null && !id.isEmpty() && salary!= null && !salary.isEmpty()){
      try{
        int employeeId = Integer.parseInt(id);
        long newSalary = Long.parseLong(salary);
        EmployeeDTO employee = sqlProviderBean.getEmployeeById(employeeId);
        long salarySum = sqlProviderBean.getDepartmentWageCost(employee.getDepartment());
        double diff = Math.min(employee.getSalary() * 0.05,  salarySum * 0.03);
        String sort = request.getParameter("sort");
        if (newSalary > employee.getSalary() - diff && newSalary < employee.getSalary() + diff){
          sqlProviderBean.updateSalary(employeeId, newSalary);
          response.sendRedirect("SalaryChange?sort=" + (sort == null ? "" : sort));
        }
        else{
          updatePage(request, response, sort, new Long(employeeId), newSalary);
        }
      }
      catch(NumberFormatException ex){
        ;
      }
    }
  }

  private void sortById(List<EmployeeDTO> employees, boolean inc){
    if (inc){
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          int result = 1;
          if(e1.getEmployeeId() < e2.getEmployeeId()){
              result = -1;
          }
          else if(e1.getEmployeeId() == e2.getEmployeeId()) {
            result = 0;
          }
          return result;
        }
      });
    }
    else{
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          int result = 1;
          if(e1.getEmployeeId() > e2.getEmployeeId()){
              result = -1;
          }
          else if(e1.getEmployeeId() == e2.getEmployeeId()) {
            result = 0;
          }
          return result;
        }
      });
    }
  }

  private void sortByName(List<EmployeeDTO> employees, boolean inc){
    if (inc){
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          return (e1.getLastName()+", " + e1.getFirstName()).compareTo(e2.getLastName()+", " + e2.getFirstName());
        }
      });
    }
    else{
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          return -(e1.getLastName()+", " + e1.getFirstName()).compareTo(e2.getLastName()+", " + e2.getFirstName());
        }
      });
    }  
  }
  
  private void sortByDepartment(List<EmployeeDTO> employees, boolean inc){
    if (inc){
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          String dep1 = e1.getDepartment() == null ? "zzzz" : e1.getDepartment().getDepartmentName();
          String dep2 = e2.getDepartment() == null ? "zzzz" : e2.getDepartment().getDepartmentName();
          return (dep1.compareTo(dep2));
        }
      });
    }
    else{
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          String dep1 = e1.getDepartment() == null ? "zzzz" : e1.getDepartment().getDepartmentName();
          String dep2 = e2.getDepartment() == null ? "zzzz" : e2.getDepartment().getDepartmentName();
          return -(dep1.compareTo(dep2));
        }
      });
    }      
  }
  
  private void sortByManager(List<EmployeeDTO> employees, boolean inc){
    if (inc){
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          String man1 = e1.getManager() == null ? "zzzz" : e1.getManager().getLastName() + ", " + e1.getManager().getFirstName();
          String man2 = e2.getManager() == null ? "zzzz" : e2.getManager().getLastName() + ", " + e2.getManager().getFirstName();
          return (man1.compareTo(man2));
        }
      });
    }
    else{
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          String man1 = e1.getManager() == null ? "zzzz" : e1.getManager().getLastName() + ", " + e1.getManager().getFirstName();
          String man2 = e2.getManager() == null ? "zzzz" : e2.getManager().getLastName() + ", " + e2.getManager().getFirstName();
          return -(man1.compareTo(man2));
        }
      });
    }   
  }
    
  private void sortByJob(List<EmployeeDTO> employees, boolean inc){
    if (inc){
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          return (e1.getJob().getJobTitle()).compareTo(e2.getJob().getJobTitle());
        }
      });
    }
    else{
      Collections.sort(employees, new Comparator<EmployeeDTO>() {
        @Override
        public int compare(EmployeeDTO e1, EmployeeDTO e2) {
          return -(e1.getJob().getJobTitle()).compareTo(e2.getJob().getJobTitle());
        }
      });
    }      
  }
  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
