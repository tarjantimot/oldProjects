//Tarján Timót
package hu.bh.servlets;


import hu.bh.dzzt.dtos.EmployeeDTO;
import hu.bh.dzzt.dtos.UserDTO;
import hu.bh.dzzt.ejbs.SQLProviderRemote;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

	@EJB
  private SQLProviderRemote sqlProviderBean;

	
	 @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    UserDTO user = (UserDTO) session.getAttribute("userbean");
		if(user!=null){
    request.setAttribute("accessLevel", user.getRole());
		request.setAttribute("userName", user.getUserName());
		}
    request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
    
  }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		HttpSession session = request.getSession();
			UserDTO user = (UserDTO) session.getAttribute("userbean");
		if(request.getParameter("logInOut").equals("Login")){
//			user = null;
			String userName = request.getParameter("username");
			System.out.println(userName);
			String password = request.getParameter("password");
			System.out.println(password);
			user = sqlProviderBean.validateLogin(userName, password);
			System.out.println(user.getUserName());
			System.out.println(user.getRole());
//			request.setAttribute("accessLevel", user.getRole());
//			request.setAttribute("userName", user.getUserName());
			session.setAttribute("userbean", user);
			response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
//			request.getRequestDispatcher("user.jsp").forward(request, response);
		
		} else if(request.getParameter("logInOut").equals("logout")){
//			user = (UserDTO) session.getAttribute("userbean");
//			user.setRole(-1);
//			user.setUserName("notLoggedIn");
//			session.setAttribute("userbean", user);
			session.invalidate();
			response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
//			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
		request.getRequestDispatcher("WEB-INF/user.jsp");
	}


	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
	
}
