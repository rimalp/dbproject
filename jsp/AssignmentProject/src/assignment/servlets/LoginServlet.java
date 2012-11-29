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
		String pressed = request.getParameter("id");
		System.out.println("DOGET login servlet: "+pressed);
		if(pressed.equals("logout_link"))
		{
			request.getSession().removeAttribute("currentEmail");
			request.getSession().removeAttribute("name");
			//more?
			System.out.println("DIRECTING TO INDEX");
			response.sendRedirect("index.jsp");
		}
		else if(pressed.equals("student_home_link"))
		{
			//CODE TO DISPLAY HOME PAGE
			displayStudentHome((String)request.getSession().getAttribute("currentEmail"), request, response);
		}
		else if(pressed.equals("professor_home_link"))
		{
			displayProfessorHome((String)request.getSession().getAttribute("currentEmail"), request, response);
		}
		
	}

	/*
	 * diplay the homepage for a professor
	 */
	private void displayProfessorHome(String username, HttpServletRequest request, HttpServletResponse response)
	{
		String numStudentsQuery="SELECT CRN, COUNT(DISTINCT email) AS num FROM takes WHERE CRN IN (SELECT CRN FROM teaches WHERE email='"+username+"') GROUP BY CRN";
		//ResultSet rs = queryDB(numStudents);
		
		//for professor want course, active assignments, number of students, (crns of sections for links)
		String countQuery = "SELECT course, COUNT(DISTINCT assignmentID), num, sections.CRN FROM ("+numStudentsQuery+") studentCount, sections, teaches, takes, assignments WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND assignments.CRN=teaches.CRN AND teaches.email='"+username+"' AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE GROUP BY course, sections.CRN, num";
		ResultSet assignmentCount = queryDB(countQuery);
		String[][] data = addStudentCount(assignmentCount);
		
		System.out.println("TESTSTSTSTSTS");
		//test print for this data
		for(int i=0; i<data.length; i++)
		{
			System.out.println(data[i][0]+" "+data[i][1]+" "+data[i][2]+" "+data[i][3]);
		}
		
		try{
			request.setAttribute("data", data);
			request.getRequestDispatcher("professor_welcome.jsp").forward(request, response);
		}catch(IOException e) { System.out.println("ioexception: "+e); }
		catch (ServletException e) { System.out.println("servlet exception: "+e); }
		
	}
	
	private String[][] addStudentCount(ResultSet rs)
	{
		String[][] r=new String[10][4];
		
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("HERGERGEG: "+count.getString(1));
				r[i][0]=rs.getString(1);
				r[i][1]=Integer.toString(rs.getInt(2));
				r[i][2]=Integer.toString(rs.getInt(3));
				r[i][3]=rs.getString(4);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
	}
	
	/*
	 * method to display the homepage of a student
	 */
	private void displayStudentHome(String username, HttpServletRequest request, HttpServletResponse response)
	{
		String countQuery = "SELECT course, COUNT(DISTINCT assignmentID), sections.CRN FROM sections, takes, assignments WHERE sections.CRN=takes.CRN AND assignments.CRN=takes.CRN AND takes.email='"+username+"' AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE GROUP BY course, sections.CRN";
		ResultSet assignmentCount = queryDB(countQuery);
		String[][] data = addWithCounts(assignmentCount);
		
		//System.out.println("1111111111111111111111111111");
		//result set of all courses that this student takes with 0 active assignments...
		//all courses that this student takes minus those with active assignments
		String allCourses = "SELECT course FROM sections, takes WHERE takes.CRN=sections.CRN AND takes.email='"+username+"'";
		String withActive = "SELECT course FROM sections, takes, assignments WHERE sections.CRN=takes.CRN AND assignments.CRN=takes.CRN AND takes.email='"+username+"' AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE";
		ResultSet rest = queryDB("("+allCourses+") EXCEPT ("+withActive+")");
		data = addWOCounts(data, rest);
		
		//test print data... any classes with no assignments?
		/*for(int i=0; i<data.length; i++)
		{
			for(int j=0; j<data[i].length; j++)
			{
				System.out.print(data[i][j]+": ");
			}
			System.out.println();
		}*/
		//System.out.println("222222222222222222222222222222");
		//assumes no student takes more than 10 sections...
		//could add in checks to grow array if needed or use arraylist
		//String[][] data = studentWelcomeArray(assignmentCount, rest);
		//System.out.println("3333333333333333333333333333333333333333");
		
		try{
			request.setAttribute("data", data);
			request.getRequestDispatcher("student_welcome.jsp").forward(request, response);
		}catch(IOException e) { System.out.println("ioexception: "+e); }
		catch (ServletException e) { System.out.println("servlet exception: "+e); }
		
		//System.out.println("The button pressed for student login: " + request.getParameter("login"));

		//response.sendRedirect("student_welcome.jsp");
		//response.sendRedirect("student_welcome_path");
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

				String name = getUserName(username);
				session.setAttribute("name", name);
				System.out.println("NAME: "+name);

				System.out.println("The button pressed: " + request.getParameter("login"));


				if(isProfessor(username)){
					//get everything to be displayed on prof welcome page
					//and set variables to be used etc... do same for student portion
					//http://stackoverflow.com/questions/3608891/pass-variables-from-servlet-to-jsp\
					
					displayProfessorHome(username, request, response);
					//response.sendRedirect("professor_welcome.jsp");
					//System.out.println("The button pressed for professor login: " + request.getParameter("login"));

				}else {
					/*
					 * NOTES:
					 * not sure if addWOCount works because not sure if there are any classes
					 * without any active assignments... might have to refill database to test this
					 * ...
					 */
					
					
					//System.out.println("0000000000000000000000000000000000");
					//result set of (course, assignmentCount) tuples for course with assignments that this student takes
					//only get active assignments... deadline > currentDate
					//ERROR: deadline is a varchar (why???) convert to date then compare with current_date
					//http://www.postgresql.org/docs/8.1/static/functions-formatting.html
					/*ResultSet t = queryDB("SELECT deadline FROM assignments WHERE assignmentID < 10");
					try{
						while(t.next())
						{
							System.out.println(t.getString("deadline"));

						}
					}catch (SQLException e){ System.out.println("SQL Exception: "+e); }
					*/
					
					
					displayStudentHome(username, request, response);
				}

			}else {//wrong user-name/password
				response.sendRedirect("error.jsp");
			}

		}
		
		
		//Begin test portion//
		else if(request.getParameter("show") != null)
		{
			ResultSet rs = queryDB("SELECT * FROM users LIMIT 5");
			try{
				while(rs.next())
				{
					out.print("<p>"+rs.getString("email")+" *** "+rs.getString("password"));

				}
			}catch (SQLException e){ System.out.println("SQL Exception: "+e); }
			
			out.println("<p>*****************************************************</p>");
			
			rs = queryDB("SELECT * FROM users, professors WHERE users.email=professors.email LIMIT 5");
			try{
				while(rs.next())
				{
					out.print("<p>"+rs.getString("email")+" *** "+rs.getString("password"));

				}
			}catch (SQLException e){ System.out.println("SQL Exception: "+e); }
		}
		/*else if(request.getParameter("change") != null)
		{
			ResultSet rs = queryDB(String.format("SELECT * FROM users WHERE email='%s'",request.getParameter("email")));
			try{
				while(rs.next())
				{
					out.print("<p>"+rs.getString("email")+" *** "+rs.getString("password"));

				}
			}catch (SQLException e){ System.out.println("SQL Exception: "+e); }
			//update db
			try{
				sql.executeUpdate(String.format("UPDATE users SET password='%s' WHERE email='%s'",request.getParameter("newPass"),request.getParameter("email")));
				System.out.println("NEW EMAIL: "+request.getParameter("newEmail"));
				//below not working... why???
				sql.executeUpdate(String.format("UPDATE users SET email='%s' WHERE email='%s'",request.getParameter("newEmail"),request.getParameter("newEmail")));
				rs = queryDB(String.format("SELECT * FROM users WHERE email='%s'",request.getParameter("newEmail")));
				while(rs.next())
				{
					out.print("<p>"+rs.getString("email")+" *** "+rs.getString("password"));

				}
			}catch(SQLException e){ System.out.println("SQLException on update: "+e); }
		}*/
		//End test portion//
		else{
			response.sendRedirect("index.jsp");
		}
	}
	
	/*
	 * takes the result set of courses without active assignments and adds to data array
	 */
	private String[][] addWOCounts(String[][] d, ResultSet a)
	{	
		try{
			int i=0;
			//get correct i
			while(d[i][0] != null){ i++; }
			while(a.next())
			{
				System.out.println("Count=0: "+a.getString(1));
				d[i][0]=a.getString(1);
				d[i][1]="0";
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return d;
	}
	
	/*
	 * takes the result set of courses with active assignment count and converts it
	 * to a 2d array...
	 */
	private String[][] addWithCounts(ResultSet count)
	{
		String[][] r=new String[10][3];
		
		try{
			int i=0;
			while(count.next())
			{
				//System.out.println("HERGERGEG: "+count.getString(1));
				r[i][0]=count.getString(1);
				r[i][1]=Integer.toString(count.getInt(2));
				r[i][2]=count.getString(3);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
	}
	
	private String getUserName(String email)
	{
		String r="";
		ResultSet rs = queryDB(String.format("SELECT first_name, last_name FROM users WHERE email='%s'",email));
		try{
			while(rs.next())
			{
				r+=rs.getString("first_name")+" "+rs.getString("last_name");
			}
		}catch(SQLException e){ System.out.println("SQLException on update: "+e); }
		return r;
	}

	//FOR TESTING
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
