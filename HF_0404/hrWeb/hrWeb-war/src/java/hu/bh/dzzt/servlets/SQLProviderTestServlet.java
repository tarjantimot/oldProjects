/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.bh.dzzt.servlets;

import hu.bh.dzzt.ejbs.SQLProviderRemote;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SQLProviderTestServlet", urlPatterns = {"/SQLProviderTestServlet"})
public class SQLProviderTestServlet extends HttpServlet {

  @EJB
  private SQLProviderRemote sqlProviderBean;
  
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    System.out.println("Helló!!!!!!!!!!!!!!!");
    System.out.println("list load started");
    request.setAttribute("employeeList", sqlProviderBean.getEmployeeList());
    System.out.println("emloyee list done");
    request.setAttribute("departmentList", sqlProviderBean.getDepartmentList());
    System.out.println("department list done");
    request.setAttribute("jobList", sqlProviderBean.getJobList());
    System.out.println("job list done");
    request.setAttribute("managerList", sqlProviderBean.getManagerList());
    System.out.println("manager list done");
    request.setAttribute("similarEmail", sqlProviderBean.getSimilarEmailList("s"));
    System.out.println("similar email list done");
    System.out.println("list load finished");
    request.getRequestDispatcher("WEB-INF/SQLProviderTestJSP.jsp").forward(request, response);
    System.out.println("redirect finished.");
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
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
