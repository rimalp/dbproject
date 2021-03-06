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
 * Servlet implementation class Student_Section_Servlet
 */
public class Student_Section_Servlet extends HttpServlet {
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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_Section_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("DOGET in student section servlet");
		
		String crn = request.getParameter("id");
		//now get data for this crn and send to student_section.jsp (single section page)
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("currentEmail");
		
		System.out.println("CRN: "+crn);
		if(crn != null)
		{
			ResultSet rs = null;
			
			//name descr, deadline
			//assignments.assignmentID IN (SELECT DISTINCT assignmentID FROM questions)..... for only assignments with questions
			//AND to_date(deadline, 'YYYY-MM-DD') >= CURRENT_DATE for only active assignments
			String assignments = "SELECT name, description, to_date(deadline, 'YYYY-MM-DD')::TEXT, assignmentID FROM assignments, takes WHERE assignments.assignmentID IN (SELECT DISTINCT assignmentID FROM questions) AND assignments.CRN=takes.CRN AND takes.CRN='"+crn+"' AND takes.email='"+email+"' ORDER BY deadline ASC";
			rs = queryDB(assignments);
			String[][] a = fillA(rs);
			
			//title, room, days, time
			String sectionInfo = "SELECT course, room, day, time FROM sections, takes WHERE sections.CRN='"+crn+"' AND takes.CRN=sections.CRN AND takes.email='"+email+"'";
			rs=queryDB(sectionInfo);
			String[] info = fillInfo(rs);
			
			//get the grades for this course
			String courseGradeQ = "SELECT assignments.name, grade FROM assignments, assigned WHERE assigned.assignmentid=assignments.assignmentid AND assignments.crn='"+crn+"' AND assigned.email='"+email+"'";
			ResultSet gradeRS = queryDB(courseGradeQ);
			String[][] grades = getGrades(gradeRS);
			System.out.println("Gradessssssssssssss-------- " + grades[0][0] + "  " + grades[0][1] );
			try{
				request.setAttribute("assignments_grades", grades);
				request.setAttribute("assignments", a);
				request.setAttribute("info", info);
				request.getRequestDispatcher("student_section.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
			
		}
		//also sort link options delt with here
	}
	
	//read and return assignment name and grades from result set and return it in an array
	private String[][] getGrades(ResultSet rs){
		String[][] result = new String[100][2];
		
		try {
			int i = 0;
			while(rs.next()){
				result[i][0] = (String)rs.getString(1);
				result[i++][1] = "" +(Double)rs.getDouble(2);
			}
		}catch(Exception e){ System.out.println("ERROR: " + e); }
		
		return result;
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private String[][] fillA(ResultSet rs)
	{
		String[][] r=new String[50][4];
		try{
			int i=0;
			while(rs.next())
			{
				r[i][0]=rs.getString(1);
				r[i][1]=rs.getString(2);
				r[i][2]=rs.getString(3);
				r[i][3]=Integer.toString(rs.getInt(4));
				
				//System.out.println("i= "+i+" "+r[i][0]+" "+r[i][1]+" date: "+r[i][2]+" "+r[i][3]);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
	}
	
	private String[] fillInfo(ResultSet rs)
	{
		String[] r=new String[4];
		try{
			int i=0;
			while(rs.next())
			{
				r[0]=rs.getString(1);
				r[1]=rs.getString(2);
				r[2]=rs.getString(3);
				r[3]=rs.getString(4);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		return r;
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
