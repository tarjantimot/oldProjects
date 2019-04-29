
package hu.bh.servlets;

import hu.bh.dzzt.dtos.DepartmentDTO;
import hu.bh.dzzt.dtos.EmployeeDTO;
import hu.bh.dzzt.dtos.JobDTO;
import hu.bh.dzzt.dtos.ManagerDTO;
import hu.bh.dzzt.dtos.UserDTO;
import hu.bh.dzzt.ejbs.SQLProviderRemote;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dottya
 */
public class NewEmployeeServlet extends HttpServlet {

  @EJB
  private SQLProviderRemote sqlProviderBean;
  

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
		HttpSession session = request.getSession();
			UserDTO user = (UserDTO) session.getAttribute("userbean");
		if(null!=user && user.getRole()>=0){
    List<DepartmentDTO> departmentList = sqlProviderBean.getDepartmentList();
    List<ManagerDTO> managerList = sqlProviderBean.getManagerList();
    List<JobDTO> jobList = sqlProviderBean.getJobList();
    request.setAttribute("departmentList", departmentList);
    request.setAttribute("managerList", managerList);
    request.setAttribute("jobList", jobList);
    request.getRequestDispatcher("WEB-INF/newEmployee.jsp").forward(request, response);
		}
		else
			response.sendRedirect("login");
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
		if(request.getParameter("submit").equals("Submit")){
			String firstName = request.getParameter("firstName");

			String lastName = request.getParameter("lastName");

			String phone = request.getParameter("phone");

			String department = request.getParameter("department");

			long departmentId = Long.parseLong(department);
			String jobTitle = request.getParameter("jobtitle");

			String manager = request.getParameter("manager");

			long managerId=Long.parseLong(manager);
			ManagerDTO managerdto = new ManagerDTO(managerId, "", "", 0l);
			JobDTO job = new JobDTO(jobTitle, "", 0l, 0l);
			DepartmentDTO departmentDto = new DepartmentDTO(departmentId, "", managerdto);
			String eMail = validateEmail((firstName+lastName.charAt(0)).toUpperCase());
			Long salary = Long.parseLong(request.getParameter("salary"));
			EmployeeDTO employee = new EmployeeDTO(0l, firstName, lastName, eMail, phone, job ,salary , 0f, managerdto, departmentDto);
			sqlProviderBean.saveNewEmployee(employee);
			response.sendRedirect("login");
//			request.getRequestDispatcher("").forward(request, response);
			}
  }


  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

	
	private String validateEmail(String preferredEMail){
    if (preferredEMail == null)
      throw new NullPointerException();
    String eMail = null;
    String preferredEMailUpperCase = preferredEMail.toUpperCase();
    if(preferredEMail.length() > 2){
      List<String> eMailList = sqlProviderBean.getSimilarEmailList(preferredEMail);
      boolean ok = false;
      int num = 2;
      eMail = preferredEMailUpperCase;
      while(!ok){
        ok = true;
        for (String em : eMailList) {
          if (eMail.toUpperCase().equals(em.toUpperCase())){
            ok = false;
          }
        }
        if (!ok){
          eMail = preferredEMailUpperCase + num;
          num++;
        }
      }
    }
    return eMail;
  }
}
