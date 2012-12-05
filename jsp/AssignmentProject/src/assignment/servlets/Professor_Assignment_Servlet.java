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
			assignmentID = Integer.parseInt(request.getParameter("id"));
		}
		else if(request.getAttribute("id") != null)
		{
			System.out.println("do get prof assign servlet.... id in else");
			assignmentID=(Integer)request.getAttribute("id");
		}
		/*else if(request.getParameter("ID") != null)
		{
			System.out.println("do get with ID");
			assignmentID=Integer.parseInt((String)request.getParameter("ID"));
		}*/
		else if(request.getAttribute("editID") !=null)
		{
			System.out.println("prof assignment servlet doget... editid: "+request.getAttribute("editID"));
			assignmentID=(Integer)request.getAttribute("editID");
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
		
		//System.out.println("professor assignment servlet... befoer if aid=-1");
		
		if(assignmentID != -2)//-1 is the assignment id of our test class cs499
		{
			//get all questions
			//String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			ResultSet rs= queryDB(query);
			
			String[][] q = questionArray(rs);
			
			//System.out.println("professor assignment servlet.... before forward");
			//System.out.println("section: "+section+" assignment: "+assignment+" questions(first): "+q[0][0]+" aid: "+assignmentID);
			try{
				request.setAttribute("section", section);
				//System.out.println("1111111111111111");
				request.setAttribute("assignment", assignment);
				//System.out.println("22222222222222222222222222");
				request.setAttribute("questions", q);
				//System.out.println("33333333333333333333");
				request.setAttribute("assignmentID", assignmentID);
				//System.out.println("444444444444444444");
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
		
		if(request.getParameter("ID") != null)
		{
			System.out.println(request.getParameter("ID")+"=ID after edit... send to do get and populate view assignment page");
			
			doGet(request, response);
		}
		else if((Integer)request.getAttribute("editID") != null)
		{
			//assignmentid=(Integer)request.getAttribute("editID");
			System.out.println("editID in prof assignment servlet... go to doget..."+(Integer)request.getAttribute("editID"));
			doGet(request,response);
		}
		
		int assignmentid=-1;
		if((Integer)request.getAttribute("id") != null){ 
			assignmentid=(Integer)request.getAttribute("id"); 
			System.out.println("go to doget????"+assignmentid);
			//if(edit == null){ doGet(request, response); }
		}
		
		if(request.getParameter("assignmentID") != null)
		{
			assignmentid=Integer.parseInt(request.getParameter("assignmentID"));
		}
		
		System.out.println("assignment id after get parameter: "+assignmentid);
		
		System.out.println(assignmentid);
		
		if(edit != null && assignmentid !=-1 && request.getParameter("DELETE") == null)
		{
			System.out.println("edit not null and assignmentid not -1");
			//get same info then redirect to an assignment editing page
			String assignmentEditQ="SELECT questions.prompt, content, correct FROM questions, answers WHERE questions.prompt=answers.prompt AND questions.assignmentID=answers.assignmentID AND questions.assignmentID="+assignmentid + " ORDER BY questions.prompt";
			ResultSet editRS = queryDB(assignmentEditQ);
			//if(editRS == null) System.err.println("editRS is NULLLLLLLLLLLLLLLLLLLL");
			String[][] result = questionsEditArray(editRS);
			
			
			//TESTSETSETSETSET
			//print result array
			for(int i=0; i<result.length && result[i][0]!=null; i++)
			{
				System.out.println("q: "+result[i][0]+" a: "+result[i][1]+" correct: "+result[i][2]+" i: "+i);
			}
			
			
			
			//redirect to the edit jsp page
			//get the attributes from db
			String sectionQ="SELECT course FROM assignments, sections WHERE assignmentID='"+assignmentid+"' AND assignments.CRN=sections.CRN";
			String assignmentQ="SELECT name FROM assignments WHERE assignmentID='"+assignmentid+"'";
			ResultSet n=queryDB(sectionQ);
			String section="";
			try{
				if(n.next()){
				section=n.getString(1);}
				else{System.out.println("section query null?"); }
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			
			n=queryDB(assignmentQ);
			String assignment="";
			try{
				if(n.next()){
				assignment=n.getString(1);}
				else{System.out.println("assignment query null???"); }
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			
			try{
				request.setAttribute("section", section);
				request.setAttribute("assignment", assignment);
				request.setAttribute("questions", result);
				request.setAttribute("ID", assignmentid);
				//request.setAttribute("numQs", ""+result.length/4);
				request.getRequestDispatcher("edit_assignment.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }			
		}
		else if(request.getParameter("DELETE") != null)
		{
			System.out.println("DLETETETETETETE ing else if");
			try{
				System.out.println(sql.executeUpdate("DELETE FROM assignments WHERE assignmentID="+assignmentid));
			}catch(SQLException e){ System.out.println("ERROR deleting assignment: "+e); }
			
			//go to assignments page
			try{
//				request.getRequestDispatcher("loginpath?id=professor_home_link").forward(request, response);
				response.sendRedirect("loginpath?id=professor_home_link");
			}catch(IOException e) { System.out.println("ioexception: "+e); }
		
		}
		else
		{
			System.out.println("ELSE IN PROFESSOR ASSIGNMENT SERVLET DOPOST");
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
				//System.err.println("i="+i+"     "+a+" "+r[i][1]+" "+r[i][2]+" "+r[i][3]);
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
