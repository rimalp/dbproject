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
			//query="SELECT course, num, COUNT(DISTINCT assignmentID), teaches.CRN FROM ("+numStudents+") studentCount, sections, assignments, teaches WHERE studentCount.CRN=sections.CRN AND sections.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND teaches.email='"+email+"' GROUP BY course, teaches.CRN, num ORDER BY course";
			rs=queryDB(query);
			
			String[][] sectionData = fillData(rs);
			
			
			
			ResultSet old=queryDB("SELECT course, num, COUNT(DISTINCT assignmentID), MIN(to_date(deadline, 'YYYY-MM-DD'))::TEXT, teaches.CRN FROM ("+numStudents+") studentCount, sections, assignments, teaches WHERE studentCount.CRN=sections.CRN AND sections.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND teaches.email='"+email+"' AND to_date(deadline, 'YYYY-MM-DD') <= CURRENT_DATE GROUP BY course, teaches.CRN, num ORDER BY course");
			fillMoreData(old, sectionData);
			
			/*ResultSet due=queryDB("SELECT teaches.CRN, MIN(to_date(deadline, 'YYYY-MM-DD'))::TEXT, course FROM ("+numStudents+") studentCount, assignments, teaches, sections WHERE to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE AND sections.CRN=teaches.CRN AND teaches.email='"+email+"' AND studentCount.CRN=teaches.CRN AND assignments.CRN=teaches.CRN GROUP BY course, teaches.CRN, num ORDER BY course");
			String[] min=new String[10];
			try{
				int w=0;
				while(due.next())
				{
					min[w]=due.getString(2);
					w++;
				}
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			*/
			
			//get sections with no assignments
			ResultSet noAssignments=queryDB("SELECT course, num, teaches.CRN FROM ("+numStudents+") studentCount, sections, teaches WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND teaches.email='"+email+"' AND NOT EXISTS (SELECT assignmentID FROM assignments WHERE assignments.CRN=teaches.CRN) GROUP BY course, teaches.CRN, num ORDER BY course");
			String[][] empty=fillEmpty(noAssignments);
			
			
			try{
				request.setAttribute("empty", empty);
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
			ResultSet a=queryDB(countTotal);
			try{
				while(a.next())
				{
					System.out.println("count total: "+a.getString(1)+" "+a.getInt(2));
				}
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			
			//get number of students who have completed the assignment from the table assigned
			String countCompleted="SELECT teaches.CRN, COUNT(DISTINCT assigned.email) AS completed FROM assigned, teaches, assignments WHERE assignments.assignmentID=assigned.assignmentID AND teaches.CRN=assignments.CRN AND teaches.email='"+email+"' GROUP BY teaches.CRN";
			ResultSet b=queryDB(countCompleted);
			try{
				while(b.next())
				{
					System.out.println("count completed: "+b.getString(1)+" "+b.getInt(2));
				}
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			
			//display course names, description, deadline, num completed / total in section, assignmentid for links
			//current assignments
			query="SELECT name, description, to_date(deadline, 'YYYY-MM-DD'), completed, num, assignments.assignmentID FROM ("+countTotal+") studentTotal, ("+countCompleted+") studentCompleted, assignments, teaches WHERE assignments.assignmentID IN (SELECT DISTINCT assignmentID FROM questions) AND teaches.email='"+email+"' AND studentCompleted.CRN=teaches.CRN AND studentTotal.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND to_date(deadline, 'YYYY-MM-DD') >= CURRENT_DATE ORDER BY deadline ASC";
			rs=queryDB(query);
			String[][] activeAssignmentData = fillAssignmentData(rs);
			
			for(int i=0; i<activeAssignmentData.length && activeAssignmentData[i][0]!=null; i++)
			{
				System.out.println("ACTIVE: "+activeAssignmentData[i][0]);
			}
			
			//old assignments
			query="SELECT name, description, to_date(deadline, 'YYYY-MM-DD'), completed, num, assignments.assignmentID FROM ("+countTotal+") studentTotal, ("+countCompleted+") studentCompleted, assignments, teaches WHERE assignments.assignmentID IN (SELECT DISTINCT assignmentID FROM questions) AND teaches.email='"+email+"' AND studentCompleted.CRN=teaches.CRN AND studentTotal.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND to_date(deadline, 'YYYY-MM-DD') < CURRENT_DATE ORDER BY deadline ASC";
			rs=queryDB(query);
			String[][] oldAssignmentData = fillAssignmentData(rs);
			
			for(int i=0; i<oldAssignmentData.length && oldAssignmentData[i][0]!=null; i++)
			{
				System.out.println("ACTIVE: "+oldAssignmentData[i][0]);
			}
			
			//put assignments completed by noone into appropriate array
			ResultSet f=queryDB("SELECT name, description, to_date(deadline, 'YYYY-MM-DD'), num, assignments.assignmentID FROM ("+countTotal+") studentTotal, assignments, teaches WHERE NOT EXISTS (SELECT * FROM ("+countCompleted+") studentCompleted WHERE studentCompleted.CRN=teaches.CRN AND teaches.email='"+email+"') AND assignments.assignmentID IN (SELECT DISTINCT assignmentID FROM questions) AND teaches.email='"+email+"' AND studentTotal.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND to_date(deadline, 'YYYY-MM-DD') >= CURRENT_DATE ORDER BY deadline ASC");
			String[][] activeEmpty=fillEmptyAssignments(f, activeAssignmentData);
			
			ResultSet g=queryDB("SELECT name, description, to_date(deadline, 'YYYY-MM-DD'), num, assignments.assignmentID FROM ("+countTotal+") studentTotal, assignments, teaches WHERE NOT EXISTS (SELECT * FROM ("+countCompleted+") studentCompleted WHERE studentCompleted.CRN=teaches.CRN AND teaches.email='"+email+"') AND assignments.assignmentID IN (SELECT DISTINCT assignmentID FROM questions) AND teaches.email='"+email+"' AND studentTotal.CRN=teaches.CRN AND teaches.CRN=assignments.CRN AND to_date(deadline, 'YYYY-MM-DD') < CURRENT_DATE ORDER BY deadline ASC");
			String[][] oldEmpty=fillEmptyAssignments(g, oldAssignmentData);
			
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

	private String[][] fillMoreData(ResultSet rs, String[][] a)
	{
		//get next index
		int j=0;
		while(a[j][0]!=null){ j++; }
		try{
			int i=j+1;
			while(rs.next())
			{
				String crn=rs.getString(5);
				if(!duplicatesExist2(a, crn))
				{
					//System.out.println("HERGERGEG: "+count.getString(1));
					a[i][0]=rs.getString(1);
					a[i][1]=Integer.toString(rs.getInt(2));
					a[i][2]=Integer.toString(rs.getInt(3));
					a[i][3]=rs.getString(4);
					a[i][4]=crn;
					i++;
				}
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		return a;
	}
	
	private boolean duplicatesExist2(String[][] a, String crn)
	{
		for(int i=0; i<a.length && a[i][0]!=null; i++)
		{
			if(a[i][4].equals(crn))
			{
				return true;
			}
		}
		return false;
	}
	private String[][] fillEmptyAssignments(ResultSet rs, String[][] a)
	{
		//et next empty spot in a
		int j=0;
		while(a[j][0]!=null){ j++; }
		try{
			int i=j+1;
			while(rs.next())
			{
				//System.out.println("fillempty: "+rs.getString(1));
				a[i][0]=rs.getString(1);
				a[i][1]=rs.getString(2);
				a[i][2]="0";
				a[i][3]=rs.getString(3);
				a[i][4]=Integer.toString(rs.getInt(4));
				a[i][5]=Integer.toString(rs.getInt(5));
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return a;
	}
	
	private String[][] fillEmpty(ResultSet rs)
	{
		String[][] r=new String[50][3];
		try{
			int i=0;
			while(rs.next())
			{
				System.out.println("fillempty: "+rs.getString(1));
				r[i][0]=rs.getString(1);
				r[i][1]=Integer.toString(rs.getInt(2));
				r[i][2]=rs.getString(3);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
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
	
	private boolean duplicatesExist(String[][] a, String crn)
	{
		for(int i=0; i<a.length && a[i][0]!=null; i++)
		{
			if(a[i][4].equals(crn))
			{
				return true;
			}
		}
		return false;
	}
	/*
	 * fills the section data for professors appropriately
	 */
	private String[][] fillData(ResultSet rs)
	{
		//display course names, num students, active assignment count, next due date, section crn for links
		String[][] r=new String[10][6];
		
		try{
			int i=0;
			while(rs.next())
			{
				String crn=rs.getString(5);
				//if(!duplicatesExist(r, crn))
				//{
					//System.out.println("HERGERGEG: "+count.getString(1));
					r[i][0]=rs.getString(1);
					r[i][1]=Integer.toString(rs.getInt(2));
					r[i][2]=Integer.toString(rs.getInt(3));
					r[i][4]=rs.getString(4);//date
					r[i][5]=crn;
					i++;
				//}
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
	}
	
	/**
	 * queries the database with the given string interpreted as an sql statement...
	 * returns a resultset rs.getString(5)as the result of the query to the database
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
