package hu.bh.servlets;

import hu.bh.dzzt.dtos.DepartmentDTO;
import hu.bh.dzzt.dtos.UserDTO;
import hu.bh.dzzt.ejbs.SQLProviderRemote;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartServlet extends HttpServlet {

     private static final long serialVersionUID = 1L;

     @EJB
     private SQLProviderRemote sqlProviderBean;
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
			HttpSession session = request.getSession();
			UserDTO user = (UserDTO) session.getAttribute("userbean");
			if(null!=user && user.getRole()>=0){
//      try {
//            Context context = new InitialContext();
//
//            sqlProviderBean = (SQLProviderRemote) context.lookup("java:global/project4-ejb/SQLProviderBean!hu.bh.dzzt.ejbs.SQLProviderRemote");
//        } catch (NamingException ex) {
//            Logger.getLogger(ChartServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//
//        
    response.setContentType("image/png");

		OutputStream outputStream = response.getOutputStream();

		JFreeChart chart = getChart();
		int width = 1200;
		int height = 550;
		ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
		} else
				response.sendRedirect("login");
	}

	public JFreeChart getChart() {
    List<DepartmentDTO> departmentList = sqlProviderBean.getDepartmentList();
    DefaultCategoryDataset averageSalary = new DefaultCategoryDataset();
    departmentList.add(null);
    for (DepartmentDTO departmentDTO : departmentList) {
      long sumOfSalary = sqlProviderBean.getDepartmentWageCost(departmentDTO);
      long countOfEmployee = sqlProviderBean.getDepartmentEmployeeCount(departmentDTO);
      if(countOfEmployee > 0){
        if(departmentDTO != null)
          averageSalary.addValue(1.0*sumOfSalary/countOfEmployee, departmentDTO.getDepartmentName() + "("+ departmentDTO.getDepartmentId() + ")", departmentDTO.getDepartmentName());
        else
          averageSalary.addValue(1.0*sumOfSalary/countOfEmployee, "Has no department", "Has no department");
      }
    }
    JFreeChart chart = ChartFactory.createBarChart("Average Salary by Departments", "Department", "Average salary", averageSalary, PlotOrientation.VERTICAL, true, true, false );
		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);
		return chart;
	}

}
