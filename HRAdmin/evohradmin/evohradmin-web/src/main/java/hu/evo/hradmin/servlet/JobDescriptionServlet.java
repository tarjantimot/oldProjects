/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.servlet;

import hu.evo.hradmin.bl.impl.JobBlImpl;
import hu.evo.hradmin.controller.JobDescriptionUploadController;
import hu.evo.hradmin.dto.JobDto;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author szotyi
 */
public class JobDescriptionServlet extends HttpServlet {

    @EJB
    private JobBlImpl jobBlImpl;
    
    @Inject
    private JobDescriptionUploadController uploadController;
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
        response.setContentType("text/html;charset=UTF-8");
//        System.out.println("");
        Integer id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        JobDto job = jobBlImpl.getJob(id);
        uploadController.setJob(job);
        System.out.println(job.getJobTitle());
        response.sendRedirect("/view/job/JobDescription.xhtml");
        
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
//        processRequest(request, response);
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
