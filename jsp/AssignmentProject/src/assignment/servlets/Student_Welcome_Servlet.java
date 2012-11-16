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
import javax.servlet.http.HttpSession;

import assignment.db.DatabaseManager;

/**
 * Servlet implementation class Student_Welcome_Servlet
 */
public class Student_Welcome_Servlet extends HttpServlet {
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
		}catch(ClassNotFoundException e){}
		catch(SQLException sqle){}
	
		System.out.println("Inside the init method, the value of sql Statement object: " + sql);

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_Welcome_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//find out what button/link was pressed and do the processing in separate methods in this class.
		String pressed = request.getParameter("action");//make each button with name="action"
		
		//userEmail is the current users email
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("currentEmail");
		
		ResultSet rs = null;
		String query = "";
		
		//if sections is pressed
		if(pressed.equals("sections"))
		{
			//find the CRNs and course names for the sections that the student is in
			query = String.format("SELECT CRN, course FROM takes, sections WHERE email='%s' AND sections.CRN=takes.CRN", userEmail);			
		}
		//if assignments is pressed
		else if(pressed.equals("assignments"))
		{
			//find the assignment name, deadline, description (anything else?) that this student has
			//order by deadline???
			query = String.format("SELECT name, deadline, description FROM assignments, takes WHERE takes.email='%s' AND takes.CRN=assignments.CRN ORDER BY deadline ASC", userEmail);
		}
		//more cases???
		
		
		rs = queryDB(query);
		//now send the resultset rs to the next page so it can be populated correctly
		//HOW DO WE DO THIS??? ... servlet chaining? or something else???

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
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	

}
