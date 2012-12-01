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
import java.util.regex.Pattern;

/**
 * Servlet implementation class Make_Assignment_Servlet
 */
public class Make_Assignment_Servlet extends HttpServlet {
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

		System.out.println("make ASSIGNMENT SERVLET INIT...Inside the init method, the value of sql Statement object: " + sql);

	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Make_Assignment_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MAKE ASSIGNMENT DOPOST....");
		
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("currentEmail");
		
		if(request.getParameter("edit") != null)
		{
			//editing existing assignment
			//from professor view assignment page
			String crn=request.getParameter("edit");
		}
		else if(request.getParameter("id") != null)
		{
			//new assignment
			//from new assignment page
			String crn=request.getParameter("id");
			System.out.println("CRNNNCNGFN: "+crn);
			String course="";
			ResultSet rs=queryDB("SELECT course FROM sections WHERE CRN='"+crn+"'");
			try{
				while(rs.next())
				{
					course=rs.getString(1);
				}
			}catch(Exception e) { System.out.println("ERROR: "+e); }
			
			String name=request.getParameter("name");
			String date=request.getParameter("deadline");//ADD CHECK HERE
			
			System.out.println("DATADATADATATEEEEDATEDATEDATEADAT: "+date);
			
			String numQs=request.getParameter("numQuestions");//and here
			String descr=request.getParameter("description");
			
			//if deadline not a date... send reload page with message
			//same if numquestions not a number
			//anything if deadline has already passed?.... for now same as first
			//implement later....
			System.out.println("before checks");
			if(!checkDeadline(date))
			{
				System.out.println("in if for checkdeadline");
				//send to new_assignment.jsp but with error message at top
				try{
					request.setAttribute("CRN", crn);
					request.setAttribute("section", course);
					request.setAttribute("error", "The deadline you entered was not properly formatted (MM/DD/YYYY). Please try again.");
					request.getRequestDispatcher("new_assignment.jsp").forward(request, response);
				}catch(IOException e) { System.out.println("ioexception: "+e); }
				catch (ServletException e) { System.out.println("servlet exception: "+e); }
			}
			else if(!Pattern.matches("[0-9]*", numQs))
			{
				//send to new_assignment.jsp with error message
				try{
					request.setAttribute("CRN", crn);
					request.setAttribute("section", course);
					request.setAttribute("error", "The value you entered for number of questions was not an integer. Please try again.");
					request.getRequestDispatcher("new_assignment.jsp").forward(request, response);
				}catch(IOException e) { System.out.println("ioexception: "+e); }
				catch (ServletException e) { System.out.println("servlet exception: "+e); }
			}
			else {
            //get last assignment id
			//System.out.println("before max");
			rs=queryDB("SELECT MAX(assignmentID) FROM assignments");
			//System.out.println("after max");
			int aID=-1;
			try{
				while(rs.next())
				{
					aID=rs.getInt(1)+1;
				}
			}catch(Exception e) { System.out.println("ERROR: "+e); }
			
			//add new assignment to the database
            try{
            	System.out.println("DATE: "+date);
            	sql.executeUpdate("INSERT INTO assignments VALUES("+aID+", '"+name+"', '"+date+"', '"+descr+"', '"+email+"', '"+crn+"')");
            }catch(SQLException e){ System.out.println("ERROR: "+e); }
            //System.out.println("after insert");
			
            //redirect to a page where questions are added
			try{
				request.setAttribute("section", course);
				request.setAttribute("numQs", numQs);
				request.setAttribute("ID", aID);
				request.getRequestDispatcher("add_questions.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
			}
		}
		else if(request.getParameter("new") != null)
		{
			//from professor section page
			String crn=request.getParameter("new");
			System.out.println("NEW: "+crn);
			//get course name
			String course="";
			ResultSet rs=queryDB("SELECT course FROM sections WHERE CRN='"+crn+"'");
			try{
				while(rs.next())
				{
					course=rs.getString(1);
				}
			}catch(Exception e) { System.out.println("ERROR: "+e); }
			
			
			try{
				request.setAttribute("section", course);
				request.setAttribute("CRN", crn);
				request.getRequestDispatcher("new_assignment.jsp").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
		}
		else if(request.getParameter("questions") != null)
		{
			//on button click on the add questions page...
			//add the questions to the database
			System.out.println("QUESTIONS");
			
			//get last answerid
			int lastAnswerID=-1;
			ResultSet rs=queryDB("SELECT MAX(answerID) FROM answers");
			try{
				while(rs.next())
				{
					lastAnswerID=rs.getInt(1);
				}
			}catch(Exception e) { System.out.println("ERROR: "+e); }
			
			System.out.println("1111111111111111111111111111111111111111111111111");
			
			String qs=request.getParameter("questions");
			int assignmentid=Integer.parseInt(request.getParameter("ID"));
			System.out.println(qs+"     "+assignmentid);
			//add the questions to the database
			System.out.println("22222222222222222222222222222222222222222222222222222");
			
			for(int i=0; i<Integer.parseInt(qs); i++)
			{
				System.out.println("Question: "+(String)request.getParameter("question"+i));
				
				
				String prompt=(String)request.getParameter("question"+i);
				try{
	            	sql.executeUpdate("INSERT INTO questions VALUES("+assignmentid+", '"+prompt+"')");
	            }catch(SQLException e){ System.out.println("ERROR inserting into questions: "+e); }
				
				char[] answ={'a','b','c','d'};
				String[] correct=(String[])request.getParameterValues("correct");
				for(int j=0; j<answ.length; j++)
				{
					System.out.println("answer: "+(String)request.getParameter("answer"+i+answ[j]));
					
					
					boolean c=false;
					if(correct != null){
					for(int x=0; x<correct.length && c==false; x++)
					{
						c=(Integer.parseInt(correct[x]) == j) ? true : false;
					}
					}
					
					try{
		            	sql.executeUpdate("INSERT INTO answers VALUES("+lastAnswerID+", '"+(String)request.getParameter("answer"+i+answ[j])+"', "+assignmentid+", '"+prompt+"', "+c+")");
		            }catch(SQLException e){ System.out.println("ERROR: "+e); }
				}
			}
			
			System.out.println("333333333333333333333333333333333333333333333");
			
			//redirect to 
			try{
				request.setAttribute("id", assignmentid);
				request.getRequestDispatcher("professor_assignment_servlet_path").forward(request, response);
			}catch(IOException e) { System.out.println("ioexception: "+e); }
			catch (ServletException e) { System.out.println("servlet exception: "+e); }
			
		}
		
	}
	
	
	/*
	 * checks that the given deadline is a date
	 */
	private boolean checkDeadline(String d)
	{
		//yyyy/mm/dd
		return Pattern.matches("[0-9][0-9][0-9][0-9]/[0-1][0-9]/[0-3][0-9]", d);
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
