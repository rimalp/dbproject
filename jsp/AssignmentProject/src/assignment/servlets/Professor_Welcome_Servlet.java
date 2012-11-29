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
 * Servlet implementation class Professor_Welcome_Servlet
 */
public class Professor_Welcome_Servlet extends HttpServlet {
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
    public Professor_Welcome_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("professor welcome do get ##################################");
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("currentEmail");
		
		String pressed = request.getParameter("id");
		
		ResultSet rs = null;
		String query = "";
		
		if(pressed.equals("sections"))
		{
			System.out.println("Professor sections pressed");
			
			//display course names, num students, active assignment count, next due date, section crn for links
			
			//count of students in each course
			String numStudents="SELECT CRN, COUNT(DISTINCT email) AS num FROM takes WHERE CRN IN (SELECT CRN FROM teaches WHERE email='"+email+"') GROUP BY CRN";
			
			query="SELECT course, num, COUNT(DISTINCT assignmentID), MIN(to_date(deadline, 'YYYY-MM-DD'))::TEXT, teaches.CRN FROM ("+numStudents+") studentCount, sections, assignments, teaches WHERE studentCount.CRN=sections.CRN AND sections.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND teaches.email='"+email+"' AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE GROUP BY course, teaches.CRN, num ORDER BY course";
			rs=queryDB(query);
			
			String[][] sectionData = fillData(rs);
			
			try{
				request.setAttribute("sectionData", sectionData);
				request.getRequestDispatcher("professor_sections.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
		}
		else if(pressed.equals("assignments"))
		{
			//SEPARATE INTO OLD AND CURRENT ASSIGNMENTS
			System.out.println("professors assignments pressed");
			
			/*String test="SELECT assignmentID, COUNT(DISTINCT email), COUNT(grade) FROM assigned GROUP BY assignmentID";
			ResultSet t=queryDB(test);
			try{
				int i=0;
				while(t.next() && i<50)
				{
					System.out.println("HERGERGEG: "+t.getInt(1)+" *** "+t.getInt(2)+" *** "+t.getInt(3));
					i++;
				}
			}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
			*/
			
			
			String countTotal="SELECT CRN, COUNT(DISTINCT email) AS num FROM takes WHERE CRN IN (SELECT CRN FROM teaches WHERE email='"+email+"') GROUP BY CRN";
			
			//get number of students who have completed the assignment from the table assigned
			String countCompleted="SELECT teaches.CRN, COUNT(DISTINCT assigned.email) AS completed FROM assigned, teaches, assignments WHERE assignments.assignmentID=assigned.assignmentID AND teaches.CRN=assignments.CRN AND teaches.email='"+email+"' GROUP BY teaches.CRN";
			
			//display course names, description, deadline, num completed / total in section, assignmentid for links
			//current assignments
			query="SELECT name, description, to_date(deadline, 'YYYY-MM-DD'), completed, num, assignments.assignmentID FROM ("+countTotal+") studentTotal, ("+countCompleted+") studentCompleted, assignments, teaches WHERE teaches.email='"+email+"' AND studentCompleted.CRN=teaches.CRN AND studentTotal.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND to_date(deadline, 'YYYY-MM-DD') >= CURRENT_DATE ORDER BY deadline ASC";
			rs=queryDB(query);
			String[][] activeAssignmentData = fillAssignmentData(rs);
			
			//old assignments
			query="SELECT name, description, to_date(deadline, 'YYYY-MM-DD'), completed, num, assignments.assignmentID FROM ("+countTotal+") studentTotal, ("+countCompleted+") studentCompleted, assignments, teaches WHERE teaches.email='"+email+"' AND studentCompleted.CRN=teaches.CRN AND studentTotal.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND to_date(deadline, 'YYYY-MM-DD') < CURRENT_DATE ORDER BY deadline ASC";
			rs=queryDB(query);
			String[][] oldAssignmentData = fillAssignmentData(rs);
			
			try{
				request.setAttribute("assignmentCurrentData", activeAssignmentData);
				request.setAttribute("assignmentOldData", oldAssignmentData);
				request.getRequestDispatcher("professor_assignments.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
		}
		else
		{
			
		}
		
	}

	/*
	 * fills the assignment data for professors appropriately
	 */
	private String[][] fillAssignmentData(ResultSet rs)
	{
		//display course names, description, deadline, num completed, total in section, assignmentid for links

		String[][] r=new String[50][6];
		
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("HERGERGEG: "+count.getString(1));
				r[i][0]=rs.getString(1);
				r[i][1]=rs.getString(2);
				r[i][2]=rs.getString(3);
				r[i][3]=Integer.toString(rs.getInt(4));
				r[i][4]=Integer.toString(rs.getInt(5));
				r[i][5]=Integer.toString(rs.getInt(6));
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
	}
	
	/*
	 * fills the section data for professors appropriately
	 */
	private String[][] fillData(ResultSet rs)
	{
		//display course names, num students, active assignment count, next due date, section crn for links
		String[][] r=new String[10][5];
		
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("HERGERGEG: "+count.getString(1));
				r[i][0]=rs.getString(1);
				r[i][1]=Integer.toString(rs.getInt(2));
				r[i][2]=Integer.toString(rs.getInt(3));
				r[i][3]=rs.getString(4);
				r[i][4]=rs.getString(5);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
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
