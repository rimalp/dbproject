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
 * Servlet implementation class Professor_Assignment_Servlet
 */
public class Professor_Assignment_Servlet extends HttpServlet {
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

		System.out.println("PROFESSOR ASSIGNMENT SERVLET INIT...Inside the init method, the value of sql Statement object: " + sql);

	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Professor_Assignment_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//simply view the questions in an assignment
		//option to add question... takes you to another page
		System.out.println("professor assignment servlet do get");
		
		int assignmentID=-1;
		if(request.getParameter("id") !=null)
		{
			System.out.println("do get prof assign servlet.... id in if");
			assignmentID = Integer.parseInt((String)request.getParameter("id"));
		}
		else if(request.getAttribute("id") != null)
		{
			System.out.println("do get prof assign servlet.... id in else");
			assignmentID=(Integer)request.getAttribute("id");
		}
		
		System.out.println("assignment id: "+assignmentID);
		
		//get assignment name and section name and set two parameters 
		//to get information to view assignment page
		String sectionQ="SELECT course FROM assignments, sections WHERE assignmentID='"+assignmentID+"' AND assignments.CRN=sections.CRN";
		String assignmentQ="SELECT name FROM assignments WHERE assignmentID='"+assignmentID+"'";
		
		ResultSet n=queryDB(sectionQ);
		String section="";
		try{
			n.next();
			section=n.getString(1);
		}catch(Exception e){ System.out.println("ERROR: "+e); }
		
		n=queryDB(assignmentQ);
		String assignment="";
		try{
			n.next();
			assignment=n.getString(1);
		}catch(Exception e){ System.out.println("ERROR: "+e); }
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("currentEmail");
		
		if(assignmentID != -1)
		{
			//get all questions
			//String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			ResultSet rs= queryDB(query);
			
			String[][] q = questionArray(rs);
			
			
			try{
				request.setAttribute("section", section);
				request.setAttribute("assignment", assignment);
				request.setAttribute("questions", q);
				request.getRequestDispatcher("professor_view_assignment.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//EDIT BUTTON GOES HERE
		System.out.println("DOPOST prof assignment servlet");
		String edit=request.getParameter("Edit");
		
		int assignmentid=-1;
		if((Integer)request.getAttribute("id") != null){ 
			assignmentid=(Integer)request.getAttribute("id"); 
//			doGet(request, response);
		}
		System.out.println(assignmentid);
		
		if(edit != null)
		{
			//get same info then redirect to an assignment editing page
			String assignmentEditQ="SELECT questions.prompt, content, correct FROM questions, answers WHERE questions.prompt=answers.prompt AND questions.assignmentID=answers.assignmentID AND questions.assignmentID="+assignmentid + " ORDER BY questions.prompt";
			ResultSet editRS = queryDB(assignmentEditQ);
			if(editRS == null) System.err.println("editRS is NULLLLLLLLLLLLLLLLLLLL");
			String[][] result = questionsEditArray(editRS);
					
			//redirect to the edit jsp page
			//get the attributes from db
			String sectionQ="SELECT course FROM assignments, sections WHERE assignmentID='"+assignmentid+"' AND assignments.CRN=sections.CRN";
			String assignmentQ="SELECT name FROM assignments WHERE assignmentID='"+assignmentid+"'";
			ResultSet n=queryDB(sectionQ);
			String section="";
			try{
				n.next();
				section=n.getString(1);
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			
			n=queryDB(assignmentQ);
			String assignment="";
			try{
				n.next();
				assignment=n.getString(1);
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			
			try{
				request.setAttribute("section", section);
				request.setAttribute("assignment", assignment);
				request.setAttribute("questions", result);
				request.setAttribute("ID", assignmentid);
				request.setAttribute("numQs", new String(""+result.length/4));
				request.getRequestDispatcher("edit_assignment.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }			
		}
		else
		{
			request.getRequestDispatcher("professor_welcome.jsp").forward(request, response);
		}

	}

	private String[][] questionsEditArray(ResultSet rs){
		String[][] r = new String[100][3];
		try{
			int i=0;
			while(rs.next())
			{	
				r[i][0]=rs.getString(1);
				r[i][1]=rs.getString(2);
				r[i][2]=String.valueOf(rs.getBoolean(3));
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		return r;
	}

	private String[][] questionArray(ResultSet rs)
	{
		String[][] r=new String[100][4];
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("questionArray: "+r[i][0]);
				String a=rs.getString(1);
				r[i][0]=a;
				r[i][1]=rs.getString(2);
				r[i][2]=String.valueOf(rs.getBoolean(3));
				r[i][3]=Integer.toString(rs.getInt(4));
				i++;
				System.err.println("i="+i+"     "+a+" "+r[i][1]+" "+r[i][2]+" "+r[i][3]);
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
