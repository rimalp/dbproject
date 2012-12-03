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
 * Servlet implementation class Student_Grades_Servlet
 */
public class Student_Grades_Servlet extends HttpServlet {
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
    public Student_Grades_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String studentEmail=(String)request.getParameter("email");
		String crn=(String)request.getParameter("crn");
		
		//get scores on assignments for this class
		ResultSet scores=queryDB("SELECT grade, name FROM assigned, assignments WHERE assigned.assignmentID=assignments.assignmentID AND assignments.crn='"+crn+"' AND assigned.email='"+studentEmail+"'");
		String[][] listGrades=new String[100][2];
		try{
			int o=0;
			while(scores.next())
			{
				listGrades[o][0]=Double.toString(scores.getDouble(1));
				listGrades[o][1]=scores.getString(2);
				o++;
			}
		}catch(Exception e){ System.out.println("ERROR: "+e); }
		
		
		try{
			//request.setAttribute(""));
			request.setAttribute("grades", listGrades);
			request.getRequestDispatcher("student_grades.jsp").forward(request, response);
		}catch(IOException e) { System.out.println("ioexception: "+e); }
		catch (ServletException e) { System.out.println("servlet exception: "+e); }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

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
