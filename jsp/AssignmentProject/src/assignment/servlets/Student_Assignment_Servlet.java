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
		
		//get assignment name and section name and set two parameters 
		//to get information to single assignment page
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
		
		if(assignmentID != null)
		{
			//get all questions
			//String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			ResultSet rs= queryDB(query);
			
			String[][] q = questionArray(rs);
			
			//get last student submission if it exists
			/*query="SELECT prompt, answerID FROM last_submission WHERE assignmentID='"+assignmentID+"' AND email='"+email+"' ORDER BY prompt";
			
			rs=queryDB(query);
			String[][] answersGiven=fillAnswers(rs);
			*/
			try{
				request.setAttribute("assignmentID", assignmentID);
				request.setAttribute("section", section);
				request.setAttribute("assignment", assignment);
				request.setAttribute("questions", q);
				//request.setAttribute("answersGiven", answersGiven);
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
	
	private String[][] fillAnswers(ResultSet rs)
	{
		String[][] r=new String[100][2];
		try{
			int i=0;
			while(rs.next())
			{
				//System.out.println("questionArray: "+r[i][0]);
				r[i][0]=rs.getString(1);
				r[i][1]=Integer.toString(rs.getInt(2));
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
		System.out.println("dopost assignment servlet");
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("currentEmail");
		
		if(request.getParameter("Save") != null)
		{
			System.out.println("No Save button");
			/*int i=0;
			while(request.getParameter("answer"+i) != null)
			{
				try{
					//HERHEHRERHEHREHR
					//where do we put student answers in the db?
					sql.executeUpdate("UPDATE students SET");
				}catch(Exception e) { System.out.println("ERROR: "+e); }
				i++;
			}
			//save checked answers
			//show same page with with "SAVED" written at top
			//same as doget??
			*/
		}
		else if(request.getParameter("Submit") != null)
		{
			//save answers.... later, just grade
			//show grade with same page and correct answers
			System.out.println("student assignment servlet do post submit");
			
			int assignmentID=Integer.parseInt(request.getParameter("assignmentID"));
			System.out.println("assignment id= "+assignmentID);
			
			//get a list of answers in the same order as they are presented
			String query="SELECT questions.prompt, content, correct, answerID FROM questions, answers WHERE correct=true AND questions.prompt=answers.prompt AND answers.assignmentID=questions.assignmentID AND answers.assignmentID="+assignmentID+" ORDER BY questions.prompt";
			ResultSet rs= queryDB(query);
			
			String[][] q = questionArray(rs);
			int numQuestions;
			for(numQuestions=0; numQuestions<q.length && q[numQuestions][0]!=null; numQuestions++){}
			
			//boolean that states whether or not a last submission exists
			//HERGERGERGERGERGGGGGGGGGGGGg
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			/*boolean lastSubmissionExists=false;
			rs=queryDB("SELECT email FROM last_submission WHERE assignmentID="+assignmentID+" AND email='"+email+"'");
			try{
				if(rs.next())
				{
					lastSubmissionExists=true;
				}
				else
				{
					System.out.println("last submission does not exist: "+lastSubmissionExists);
				}
			}catch(Exception e){ System.out.println("ERROR: "+e); }*/
			
			
			//get student response in order
			//save to last_submission table regardless
			int n=0;//selected answer to question n
			int correct=0;
			while((String)request.getParameter("answer"+n) != null)
			{
				String answer=(String)request.getParameter("answer"+n);
				System.out.println("given: "+answer+" current: "+q[n][1]+" correct: "+q[n][2]);
				
				//save to last_submission table
				/*try{
					if(lastSubmissionExists)
					{
						sql.executeUpdate("UPDATE last_submission SET answerID="+q[n][3]+" WHERE email='"+email+"' AND assignmentID="+assignmentID+" AND prompt='"+q[n][0]+"'");
					}
					else
					{
						sql.executeUpdate("INSERT INTO last_submission VALUES('"+email+"', "+assignmentID+", '"+q[n][0]+"', "+q[n][3]+")");
					}
				}catch(Exception e){ System.out.println("ERROR: "+e); }
				*/
				
				if(answer.equals(q[n][2]) && q[n][2].equals("true"))
				{
					correct++;
				}
				n++;
			}
			System.out.println("CORRECT: "+correct+" TOTAL: "+numQuestions);
			
			//check if deadline passed...
			//get deadline
			boolean saveAnswers=false;
			ResultSet deadline=queryDB("SELECT deadline FROM assignments WHERE assignmentID="+assignmentID+" AND to_date(deadline, 'YYYY-MM-DD') > CURRENT_DATE");
			try{
				if(deadline.next())
				{
					saveAnswers=true;
				}
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			
			//add score to assigned table
			//check if already submitted a score	
			String pastDeadline="";
			if(saveAnswers)
			{
				ResultSet lastScore=queryDB("SELECT grade FROM assigned WHERE assignmentID='"+assignmentID+"' AND email='"+email+"'");
				try{
					if(lastScore.next()){
						lastScore.getDouble(1);
						System.out.println(sql.executeUpdate("UPDATE assigned SET submit_time=CURRENT_TIMESTAMP, grade="+(correct/numQuestions)+" WHERE assignmentID='"+assignmentID+"' AND email='"+email+"'"));
					}
					else{
						System.out.println("inserted....  "+sql.executeUpdate("INSERT INTO assigned VALUES("+assignmentID+", '"+email+"', "+(correct/numQuestions)+", CURRENT_TIMESTAMP)"));
					}
				}catch(Exception e){ System.out.println("ERROR: "+e); }
			}
			else
			{
				pastDeadline="The deadline for this assignment has passed.";
			}
			
			//what page to go to? then display ratio correct/numQuestions and save to assigned table
			//get assignment name
			ResultSet assignment=queryDB("SELECT name FROM assignments WHERE assignmentID="+assignmentID);
			String assignmentName="";
			try{
				assignment.next();
				assignmentName=assignment.getString(1);
			}catch(Exception e){ System.out.println("ERROR: "+e); }
			try{
				request.setAttribute("assignment", assignmentName);
				request.setAttribute("total", numQuestions);
				request.setAttribute("correct", correct);
				request.setAttribute("deadline", pastDeadline);
				request.getRequestDispatcher("show_score.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
			
		}
		else
		{
			
		}
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
