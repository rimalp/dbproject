package assignment.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.db.DatabaseManager;

/**
 * Servlet implementation class Account_Settings_Servlet
 */
public class Account_Settings_Servlet extends HttpServlet {
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

		System.out.println("Inside the ACCOUNT SETTINGS init method, the value of sql Statement object: " + sql);

	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account_Settings_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//update user password
		String newPassword = request.getParameter("new");
		System.out.println(newPassword);
		String check = request.getParameter("newConfirm");
		System.out.println(check);
		
		if(!newPassword.equals(check))
		{
			System.out.println("ERROR updating password");
			//return to same page with error message
			//request.getSession().setAttribute("valid", false);
			response.sendRedirect("account_settings.jsp?valid=false");
		}
		else
		{
			//check old password then...
			ResultSet rs = queryDB(String.format("SELECT * FROM users WHERE password='%s' AND email='%s'",request.getParameter("old"), request.getSession().getAttribute("currentEmail")));
			try{
			if (rs.next())
			{
				updateDB(String.format("UPDATE users SET password='%s' WHERE email='%s'",newPassword,request.getSession().getAttribute("currentEmail")));
				System.out.println("UPDATED");
				response.sendRedirect("student_welcome.jsp");
			}
			else
			{
				response.sendRedirect("account_settings.jsp?valid=oldFalse");
			}
			}catch(SQLException e) { System.out.println("SQLERROR: "+ e); }
		}
		
	}
	
	/**
	 * updates the database using the given string as an sql statement
	 */
	private void updateDB(String update)
	{
		try{
			sql.executeUpdate(update);
		}catch(SQLException e){ System.out.println("SQL ERROR: "+ e); }
	}
	
	/**
	 * queries the database with the given string interpreted as an sql statement...
	 * returns a resultset as the result of the query to the database
	 */
	private ResultSet queryDB(String query)
	{
		ResultSet rs = null;
		try {		
			if(sql == null) {
				System.out.println("SQL STATEMENT OBJECT IS EMPTY!");
			} else {
				if(sql.isClosed()) {
					System.err.println("The sql statement object is closed!!!");					
				}
				rs = sql.executeQuery(query);
			}
		} catch(SQLException e){
			System.err.println("Exception generated ----> " + e.toString());
		}
		return rs;
	}

}
