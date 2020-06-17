package job.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import job.bean.User;
import job.service.UserService;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		if("regist".equals(action)){			
			regist(request, response);
		}else if("login".equals(action)){			
			login(request, response);
		}
		else if("logout".equals(action))
		{
			logout(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	public void regist(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		UserService us = new UserService();
		User user = new User();		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setNickname(request.getParameter("nickname"));
		user.setEmail(request.getParameter("email"));
		us.addUserService(user);
		response.sendRedirect(request.getContextPath() + "/m3.jsp");	
			
	}
	public void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		UserService us = new UserService();
		User user = new User();	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user = us.findUserDao(username, password);
		if(user == null){			
			request.getRequestDispatcher("/login.jsp").forward(request, response);	
		}
		else {	
			request.getSession().invalidate();
			request.getSession().setAttribute("user", user);		
			response.sendRedirect(request.getContextPath() + "/m3.jsp");	
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().invalidate(); // 销毁session

		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");

		response.addCookie(cookie);

		response.sendRedirect(request.getContextPath() + "/m3.jsp");

	}

}
