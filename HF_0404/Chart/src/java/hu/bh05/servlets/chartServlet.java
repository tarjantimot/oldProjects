
package hu.bh05.servlets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author karty
 */
@WebServlet(name = "chartServlet", urlPatterns = {"/chartServlet"})
public class chartServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("image/png");

		OutputStream outputStream = response.getOutputStream();

		JFreeChart chart = getChart();
		int width = 800;
		int height = 550;
		ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);

	}

	public JFreeChart getChart() {
            
             DefaultCategoryDataset averageSalary = new DefaultCategoryDataset();
             
                averageSalary.addValue(3300, "1","1");
                averageSalary.addValue(7300, "2","2");
                averageSalary.addValue(6300, "3","3");
                averageSalary.addValue(5300, "4","4");
                
         
//
                JFreeChart chart = ChartFactory.createBarChart("Average Deparmentsalary", "Department", "Average salary", averageSalary);
		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);
		return chart;
	}

}
