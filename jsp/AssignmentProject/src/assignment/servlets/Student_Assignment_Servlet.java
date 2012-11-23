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
 * Servlet implementation class Student_Assignment_Servlet
 */
public class Student_Assignment_Servlet extends HttpServlet {
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

		System.out.println("STUDENT ASSIGNMENT SERVLET INIT...Inside the init method, the value of sql Statement object: " + sql);

	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_Assignment_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("student assignment servlet do get");
		
		String assignmentID = (String)request.getParameter("id");
		System.out.println("assignment id: "+assignmentID);
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("currentEmail");
		
		if(assignmentID != null)
		{
			//get all questions
			//String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			ResultSet rs= queryDB(query);
			
			String[][] q = questionArray(rs);
			
			/*for(int w=0; w<q.length; w++)
			{
				System.out.println(w+": "+q[w][0]);
				if(w==20){ w=q.length; }
			}*/
			//get previous student answers
			//query="SELECT questions.prompt, student.answerID FROM students, questions, answers WHERE email='"+email+"' AND answers.prompt=questions.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" AND student.answerID=answer.answerID ORDER BY questions.prompt ";
			
			
			try{
				request.setAttribute("questions", q);
				request.getRequestDispatcher("student_single_assignment.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
			
		}
	}

	private String[][] questionArray(ResultSet rs)
	{
		String[][] r=new String[100][4];
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("questionArray: "+r[i][0]);
				r[i][0]=rs.getString(1);
				r[i][1]=rs.getString(2);
				r[i][2]=String.valueOf(rs.getBoolean(3));
				r[i][3]=Integer.toString(rs.getInt(4));
				i++;
			}
		}catch(SQLException e) { System.out.println("SQLEXCEPTION: "+e); }
		return r;
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
