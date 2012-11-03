package assignment.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assignment.db.*;

/**
 * Servlet implementation class login
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private DatabaseManager manager;
	private static Statement sql;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try{
			manager = new DatabaseManager();
			sql = DatabaseManager.getSql();
		}catch(ClassNotFoundException e){
			
		}catch(SQLException sqle){
			
		}
	
		System.out.println("Inside the init method, the value of sql Statement object: " + sql);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("The SQL object: " + sql);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		
		HttpSession session = request.getSession();
		
		
		if(request.getParameter("login") != null){
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(this.validateUser(username, password)){
				//save the user to the session eobject
				session.setAttribute("currentEmail", username);		
				
				System.out.println("The button pressed: " + request.getParameter("login"));

				
				if(isProfessor(username)){
					response.sendRedirect("professor_welcome.jsp");
					System.out.println("The button pressed for professor login: " + request.getParameter("login"));

				}else {
					System.out.println("The button pressed for student login: " + request.getParameter("login"));

					response.sendRedirect("student_welcome.jsp");
				}
				
			}else {//wrong user-name/password
					response.sendRedirect("error.jsp");
			}
			
		}else{
			response.sendRedirect("index.jsp");
		}
	}


	private boolean validateUser(String username, String password){
		String query = String.format("SELECT email, password FROM users WHERE email='%s' AND password='%s'",username, password);

		ResultSet rs = null;
			
		System.out.println("inside the validateUser() method, rs value initially "  + rs);
		
		try {
						
			if(sql == null) {
				System.out.println("SQL STATEMENT OBJECT IS EMPTY!");
			} else {
				if(sql.isClosed()) {
					System.err.println("The sql statement object is closed!!!");					
				}
				rs = sql.executeQuery(query);
				while(rs.next()){
					System.out.println(rs.getString("email"));
					System.out.println(rs.getString("password"));
					return true;
				}
				//save the user to the session object
			}
		} catch(SQLException e){
			
			System.err.println("Exception generated ----> " + e.toString());
		}
		return false;
	}
	
	private boolean isProfessor(String email){
		
		String query = String.format("SELECT email FROM professors WHERE email='%s'", email);

		ResultSet rs = null;

		try {
			sql = DatabaseManager.getSql();
			rs = sql.executeQuery(query);

			while(rs.next()){
				return true;
			}
		} catch(SQLException e){}
		catch (ClassNotFoundException ce){}
		return false;
	}
}
