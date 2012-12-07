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
			System.out.println("class not found: "+e);
		}catch(SQLException sqle){
			System.out.println("sql exception: "+sqle);
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
		
		
		//for professor want course, active assignments, number of students, (crns of sections for links)
		String countQuery = "SELECT course, COUNT(DISTINCT assignmentID), num, sections.CRN FROM ("+numStudentsQuery+") studentCount, sections, teaches, takes, assignments WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND assignments.CRN=teaches.CRN AND teaches.email='"+username+"' AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE GROUP BY course, sections.CRN, num";
		//String countQuery = "SELECT course, num, sections.CRN FROM ("+numStudentsQuery+") studentCount, sections, teaches, takes, assignments WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND assignments.CRN=teaches.CRN AND teaches.email='"+username+"' GROUP BY course, sections.CRN, num";
		ResultSet assignmentCount = queryDB(countQuery);

		String[][] data = addStudentCount(assignmentCount);
		
		ResultSet t=queryDB("SELECT course, COUNT(DISTINCT assignmentID), num, sections.CRN FROM ("+numStudentsQuery+") studentCount, sections, teaches, takes, assignments WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND assignments.CRN=teaches.CRN AND teaches.email='"+username+"' AND to_date(deadline, 'YYYY-MM-DD') <= CURRENT_DATE GROUP BY course, sections.CRN, num");
		addMore(t, data);
		
		//ResultSet due=queryDB("SELECT MIN(to_date(deadline, 'YYYY-MM-DD'))::TEXT FROM ("+numStudentsQuery+") studentCount, assignments, teaches WHERE to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE AND teaches.email='"+username+"' AND studentCount.CRN=teaches.CRN AND assignments.CRN=teaches.CRN");
		//ResultSet due=queryDB("SELECT COUNT(DISTINCT assignmentID), sections.CRN, course FROM ("+numStudentsQuery+") studentCount, sections, teaches, takes, assignments WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND assignments.CRN=teaches.CRN AND teaches.email='"+username+"' AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE GROUP BY course, sections.CRN, num");
		//ResultSet due=queryDB("SELECT course, COUNT(DISTINCT assignmentID), num, sections.CRN FROM ("+numStudentsQuery+") studentCount, sections, teaches, takes, assignments WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND assignments.CRN=teaches.CRN AND teaches.email='"+username+"' AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE GROUP BY course, sections.CRN, num");
		ResultSet due=queryDB("SELECT COUNT(DISTINCT assignmentID) FROM assignments, teaches WHERE to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE AND teaches.email='"+username+"' AND teaches.CRN=assignments.CRN");
		String[] min=new String[10];
		try{
			int w=0;
			while(due.next())
			{
				min[w]=due.getString(1);
				System.out.println("MIN: "+min[w]+" :"+due.getString(1));
				w++;
			}
		}catch(Exception e){ System.out.println("ERROR: "+e); }
		/*System.out.println("TESTSTSTSTSTS");
		//test print for this data
		for(int i=0; i<data.length && data[0][0] != null; i++)
		{
			System.out.println(data[i][0]+" "+data[i][1]+" "+data[i][2]+" "+data[i][3]);
		}*/
		
		
		ResultSet emptyAssignments=queryDB("SELECT course, num, sections.CRN FROM ("+numStudentsQuery+") studentCount, sections, teaches, takes WHERE studentCount.CRN=teaches.CRN AND sections.CRN=teaches.CRN AND NOT EXISTS (SELECT assignmentID FROM assignments WHERE assignments.CRN=teaches.CRN) AND teaches.email='"+username+"' GROUP BY course, sections.CRN, num");
		String[][] empty = fillEmpty(emptyAssignments);
		
		try{
			request.setAttribute("min", min);
			request.setAttribute("empty", empty);
			request.setAttribute("data", data);
			request.getRequestDispatcher("professor_welcome.jsp").forward(request, response);
		}catch(IOException e) { System.out.println("ioexception: "+e); }
		catch (ServletException e) { System.out.println("servlet exception: "+e); }
		
	}
	
	private boolean duplicateExists(String[][] a, String crn)
	{
		for(int i=0; i<a.length && a[i][0]!=null; i++)
		{
			if(a[i][3].equals(crn))
			{
				return true;
			}
		}
		return false;
	}
	
	private String[][] addMore(ResultSet rs, String[][] a)
	{
		//get next index
		int j=0;
		while(a[j][0]!=null){ j++; }
		try{
			int i=j+1;
			while(rs.next())
			{
				String crn=rs.getString(4);
				if(!duplicateExists(a, crn))
				{
					//System.out.println("HERGERGEG: "+count.getString(1));
					a[i][0]=rs.getString(1);
					a[i][1]=Integer.toString(rs.getInt(2));
					a[i][2]=Integer.toString(rs.getInt(3));
					a[i][3]=crn;
					i++;
				}
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return a;
	}
	
	private String[][] fillEmpty(ResultSet rs)
	{
		String[][] r=new String[10][3];
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("HERGERGEG: "+count.getString(1));
				r[i][0]=rs.getString(1);
				r[i][1]=Integer.toString(rs.getInt(2));
				r[i][2]=rs.getString(3);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
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
		
		//get crns of sections with no assignments
		ResultSet noAssignments=queryDB("SELECT course, sections.CRN FROM sections, takes WHERE takes.email='"+username+"' AND takes.CRN=sections.CRN AND NOT EXISTS (SELECT assignmentID FROM assignments WHERE assignments.CRN=takes.CRN) GROUP BY course, sections.CRN");
		String[][] empty=fillEmptyStudent(noAssignments);
		for(int i=0; i<data.length && data[i][0] != null; i++)
		{
			if(data[i][2] == null)
			{
				for(int j=0; j<empty.length && empty[j][0] != null; j++)
				{
					if(empty[j][0].equals(data[i][0]))
					{
						data[i][2]=empty[j][1];
					}
				}
			}
		}
		
		//test print data... any classes with no assignments?
		for(int i=0; i<data.length; i++)
		{
			for(int j=0; j<data[i].length; j++)
			{
				System.out.print(data[i][j]+": ");
			}
			System.out.println();
		}
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
	}
	
	private String[][] fillEmptyStudent(ResultSet rs)
	{
		String[][] r=new String[10][2];
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("HERGERGEG: "+count.getString(1));
				r[i][0]=rs.getString(1);
				r[i][1]=rs.getString(2);
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		
		return r;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		///////////////////////////////////////////////////
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
		else if(request.getParameter("insert") != null)
		{
			//run this once to add fake data for demo purposes
			//WARNING... this data already inserted once...
			boolean inserted=true;
			if(!inserted){
				try{
					System.out.println("inserting demo values into database");
					int check=-1;
					
					//create users
					System.out.println("inserting users");
					sql.executeUpdate("INSERT INTO users VALUES('xia.ge@lafayette.edu', 'Ge', 'Xia', 'xia', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('taylor.matthew@lafayette.edu', 'Matthew', 'Taylor', 'taylor', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('liew.chun@lafayette.edu', 'Chun', 'Liew', 'liew', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('li.xiaoyan@lafayette.edu', 'Xiaoyan', 'Li', 'li', 'Lafayette')");
					
					sql.executeUpdate("INSERT INTO users VALUES('james.wilson@lafayette.edu', 'Wilson', 'James', 'james', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('reid.john@lafayette.edu', 'John', 'Reid', 'reid', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('hunter.robert@lafayette.edu', 'Robert', 'Hunter', 'hunter', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('lewis.mary@lafayette.edu', 'Mary', 'Lewis', 'lewis', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('anderson.laura@lafayette.edu', 'Laura', 'Anderson', 'anderson', 'Lafayette')");
					sql.executeUpdate("INSERT INTO users VALUES('hall.brian@lafayette.edu', 'Brian', 'Hall', 'hall', 'Lafayette')");
					
					//add users as students or professors
					//add professors
					System.out.println("inserting professors");
					sql.executeUpdate("INSERT INTO professors VALUES('xia.ge@lafayette.edu', '1:00-5:00', 'AEC 512')");
					sql.executeUpdate("INSERT INTO professors VALUES('taylor.matthew@lafayette.edu', '11:00-5:30', 'AEC 509')");
					sql.executeUpdate("INSERT INTO professors VALUES('liew.chun@lafayette.edu', '9:30-4:45', 'AEC 517')");
					sql.executeUpdate("INSERT INTO professors VALUES('li.xiaoyan@lafayette.edu', '8:00-12:30', 'AEC 510')");
					
					//add students
					System.out.println("inserting students");
					sql.executeUpdate("INSERT INTO students VALUES('james.wilson@lafayette.edu', 2014, 'CS', 1)");
					sql.executeUpdate("INSERT INTO students VALUES('reid.john@lafayette.edu', 2013, 'ECON', 1)");
					sql.executeUpdate("INSERT INTO students VALUES('hunter.robert@lafayette.edu', 2014, 'MATH', 1)");
					sql.executeUpdate("INSERT INTO students VALUES('lewis.mary@lafayette.edu', 2015, 'CS', 1)");
					sql.executeUpdate("INSERT INTO students VALUES('anderson.laura@lafayette.edu', 2016, 'CS', 1)");
					sql.executeUpdate("INSERT INTO students VALUES('hall.brian@lafayette.edu', 2016, 'ART', 1)");
					
					//create courses(sections) for demo
					System.out.println("inserting sections");
					sql.executeUpdate("INSERT INTO sections VALUES('CS2151', 'CS215', '1:10-2:00', 'MWF', 'AEC 500')");
					sql.executeUpdate("INSERT INTO sections VALUES('CS2152', 'CS215', '2:10-3:00', 'MWF', 'AEC 500')");
					sql.executeUpdate("INSERT INTO sections VALUES('CS2153', 'CS215', '8:00-9:30', 'TR', 'AEC 515')");
					
					sql.executeUpdate("INSERT INTO sections VALUES('CS3011', 'CS301', '8:00-9:30', 'TR', 'AEC 517')");
					sql.executeUpdate("INSERT INTO sections VALUES('CS3012', 'CS301', '11:00-12:15', 'TR', 'AEC 510')");
					
					sql.executeUpdate("INSERT INTO sections VALUES('MATH1871', 'MATH187', '11:00-12:15', 'TR', 'PARDEE 210')");
					
					sql.executeUpdate("INSERT INTO sections VALUES('MATH2921', 'MATH292', '9:00-9:50', 'MWF', 'PARDEE 201')");
					sql.executeUpdate("INSERT INTO sections VALUES('MATH2922', 'MATH292', '1:15-2:45', 'TR', 'PARDEE 221')");
					
					sql.executeUpdate("INSERT INTO sections VALUES('ECON1501', 'ECON150', '10:00-11:15', 'MW', 'SIMON 115')");
					
					sql.executeUpdate("INSERT INTO sections VALUES('ART1011', 'ART101', '11:00-12:15', 'TR', 'WILLIAMS 118')");
					sql.executeUpdate("INSERT INTO sections VALUES('ART1012', 'ART101', '3:10-4:00', 'MWF', 'WILLIAMS 117')");
					
					//assign professors to teach each section
					System.out.println("inserting into teaches");
					sql.executeUpdate("INSERT INTO teaches VALUES('xia.ge@lafayette.edu', 'CS2151')");
					sql.executeUpdate("INSERT INTO teaches VALUES('xia.ge@lafayette.edu', 'CS2152')");
					sql.executeUpdate("INSERT INTO teaches VALUES('taylor.matthew@lafayette.edu', 'CS2153')");
					
					sql.executeUpdate("INSERT INTO teaches VALUES('liew.chun@lafayette.edu', 'CS3011')");
					sql.executeUpdate("INSERT INTO teaches VALUES('xia.ge@lafayette.edu', 'CS3012')");
					
					sql.executeUpdate("INSERT INTO teaches VALUES('li.xiaoyan@lafayette.edu', 'MATH1871')");
					
					sql.executeUpdate("INSERT INTO teaches VALUES('liew.chun@lafayette.edu', 'MATH2921')");
					sql.executeUpdate("INSERT INTO teaches VALUES('taylor.matthew@lafayette.edu', 'MATH2922')");
					
					sql.executeUpdate("INSERT INTO teaches VALUES('li.xiaoyan@lafayette.edu', 'ECON1501')");
					
					sql.executeUpdate("INSERT INTO teaches VALUES('liew.chun@lafayette.edu', 'ART1011')");
					sql.executeUpdate("INSERT INTO teaches VALUES('xia.ge@lafayette.edu', 'ART1012')");
					
					//assign students to take each section
					System.out.println("inserting into takes");
					sql.executeUpdate("INSERT INTO takes VALUES('james.wilson@lafayette.edu', 'CS2151')");
					sql.executeUpdate("INSERT INTO takes VALUES('reid.john@lafayette.edu', 'CS2151')");
					sql.executeUpdate("INSERT INTO takes VALUES('anderson.laura@lafayette.edu', 'CS2151')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('hunter.robert@lafayette.edu', 'CS2152')");
					sql.executeUpdate("INSERT INTO takes VALUES('lewis.mary@lafayette.edu', 'CS2152')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('hall.brian@lafayette.edu', 'CS2153')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('james.wilson@lafayette.edu', 'CS3011')");
					sql.executeUpdate("INSERT INTO takes VALUES('anderson.laura@lafayette.edu', 'CS3011')");
					sql.executeUpdate("INSERT INTO takes VALUES('lewis.mary@lafayette.edu', 'CS3011')");
					sql.executeUpdate("INSERT INTO takes VALUES('hall.brian@lafayette.edu', 'CS3011')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('hunter.robert@lafayette.edu', 'CS3012')");
					sql.executeUpdate("INSERT INTO takes VALUES('reid.john@lafayette.edu', 'CS3012')");
					sql.executeUpdate("INSERT INTO takes VALUES('james.wilson@lafayette.edu', 'CS3012')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('hunter.robert@lafayette.edu', 'MATH1871')");
					sql.executeUpdate("INSERT INTO takes VALUES('hall.brian@lafayette.edu', 'MATH1871')");
					sql.executeUpdate("INSERT INTO takes VALUES('james.ge@lafayette.edu', 'MATH1871')");
					sql.executeUpdate("INSERT INTO takes VALUES('lewis.mary@lafayette.edu', 'MATH1871')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('anderson.laura@lafayette.edu', 'MATH2921')");
					sql.executeUpdate("INSERT INTO takes VALUES('reid.john@lafayette.edu', 'MATH2921')");
					sql.executeUpdate("INSERT INTO takes VALUES('james.wilson@lafayette.edu', 'MATH2921')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('lewis.mary@lafayette.edu', 'MATH2922')");
					sql.executeUpdate("INSERT INTO takes VALUES('hunter.robert@lafayette.edu', 'MATH2922')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('anderson.laura@lafayette.edu', 'ECON1501')");
					sql.executeUpdate("INSERT INTO takes VALUES('hunter.robert@lafayette.edu', 'ECON1501')");
					sql.executeUpdate("INSERT INTO takes VALUES('lewis.mary@lafayette.edu', 'ECON1501')");
					sql.executeUpdate("INSERT INTO takes VALUES('reid.john@lafayette.edu', 'ECON1501')");
					sql.executeUpdate("INSERT INTO takes VALUES('james.wilson@lafayette.edu', 'ECON1501')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('hunter.robert@lafayette.edu', 'ART1011')");
					sql.executeUpdate("INSERT INTO takes VALUES('hall.brian@lafayette.edu', 'ART1011')");
					sql.executeUpdate("INSERT INTO takes VALUES('lewis.mary@lafayette.edu', 'ART1011')");
					
					sql.executeUpdate("INSERT INTO takes VALUES('anderson.laura@lafayette.edu', 'ART1012')");
					sql.executeUpdate("INSERT INTO takes VALUES('james.wilson@lafayette.edu', 'ART1012')");
					
					//assignments can then be created by the professors
					//so the tables assignments, answers, questions, last_submission
					//can all be filled from within the site
					
				}catch(Exception e){ System.out.println("ERROR: "+e); }
			}
			else{ System.out.println("Already inserted..."); }
		}
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
