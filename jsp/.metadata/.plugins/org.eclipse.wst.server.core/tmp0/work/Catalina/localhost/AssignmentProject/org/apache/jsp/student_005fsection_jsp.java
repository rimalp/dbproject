package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class student_005fsection_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/student_left_links.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>\n");
      out.write("Section 1\n");
      out.write("</title>\n");
      out.write("<style type=\"text/css\">\n");
      out.write("<!--\n");
      out.write(".normalfont {font-size: 11pt; font-family: \"Geneva, Arial, Helvetica, sans-serif\";}\n");
      out.write(".bigfont {font-size: 14pt; font-family: \"Geneva, Arial, Helvetica, sans-serif\";}\n");
      out.write(".bigbigfont {font-size: 16pt; font-family: \"Geneva, Arial, Helvetica, sans-serif\";}\n");
      out.write(".darkbackgroundwithnormalfont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 11pt; font-family: \"Geneva, Arial, Helvetica, sans-serif\"; color: black; }\n");
      out.write(".darkbackgroundwithhugefont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 15pt; font-family: \"Geneva, Arial, Helvetica, sans-serif\"; color: black; }\n");
      out.write(".normalfontwithred {font-size: 11pt; font-family: \"Geneva, Arial, Helvetica, sans-serif\"; color: red;}\n");
      out.write(".centertable {text-align: center; vertical-align: middle;}\n");
      out.write("a {text-decoration: none;}\n");
      out.write(".headerfont {font-size: 18pt; font-family: \"Geneva, Arial, Helvetica, sans-serif\";}\n");
      out.write("button, button:focus, button:active {\n");
      out.write("    /* NOTE Remove all decorations */\n");
      out.write("    border: none;\n");
      out.write("    display: inline;\n");
      out.write("    margin: 0em;\n");
      out.write("    padding: 0em;\n");
      out.write("    outline: none;\n");
      out.write("    outline-offset: 0em;\n");
      out.write("    /* NOTE Look like a link */\n");
      out.write("    background: none;\n");
      out.write("    color: blue;\n");
      out.write("    cursor: pointer;\n");
      out.write("    font: inherit;\n");
      out.write("    text-decoration: none;\n");
      out.write("}\n");
      out.write("-->\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<!-- bgcolor=\"aqua\" -->\n");
      out.write("<body class=\"bodybackground\" style=\"margin-top: 0; margin-left: 0; margin-bottom: 4px; margin-right: 4px\">\n");
      out.write("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"875px\" style=\"height: 100%\">\n");
      out.write("<tr valign=\"top\">\n");
      out.write("<td style=\"width: 190px;\" class=\"marginbackground\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>Insert title here</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"normalfont\" style=\"height: 100%\" >\n");
      out.write("<tr style=\"height: 61px; border-bottom-width: 0px; \" valign=\"top\">\n");
      out.write("<td>\n");
      out.write("\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("<tr style=\"height: 10px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr class=\"darkbackgroundwithnormalfont\" style=\"height: 20px;\">\n");
      out.write("<td align=\"center\">\n");
      out.write("<b>\n");
 String e = (String)request.getSession().getAttribute("name");
out.println(e); 
      out.write("\n");
      out.write("</b>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("<tr style=\"height: 10px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr style=\"height: 35px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;\n");
      out.write("<a href=\"loginpath?id=student_home_link\" style=\"color: darkblue;\">\n");
      out.write("Home Page\n");
      out.write("</a>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr style=\"height: 35px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;\n");
      out.write("<a href=\"student_welcome_path?id=sections\" style=\"color: darkblue;\">\n");
      out.write("Sections\n");
      out.write("</a>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr style=\"height: 35px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;\n");
      out.write("<a href=\"student_welcome_path?id=assignments\" style=\"color: darkblue;\">\n");
      out.write("Assignments\n");
      out.write("</a>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr style=\"height: 35px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;\n");
      out.write("<a href=\"account_settings.jsp\" style=\"color: darkblue;\">\n");
      out.write("Account Settings\n");
      out.write("</a>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr style=\"height: 35px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;\n");
      out.write("<a href=\"loginpath?id=logout_link\" style=\"color: darkblue;\">\n");
      out.write("Log Out\n");
      out.write("</a>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<!-- more links here? -->\n");
      out.write("\n");
      out.write("<tr style=\"height: 10px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td>\n");
      out.write("&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr style=\"height: 35px;\">\n");
      out.write("<td>\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
      out.write("<a href=\"\" style=\"color: darkblue;\">\n");
      out.write("Help\n");
      out.write("</a>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</td>\n");
      out.write("<td style=\"width: 3px; background-color: black;\">&nbsp;</td>\n");
      out.write("<td valign=\"top\" class=\"lightbackgroundwithnormalfont\" style=\"width: 686px;\">\n");
      out.write("<CENTER><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" class=\"smallfont\" style=\"color: black;\">\n");
      out.write("<tr class=\"darkbackgroundwithhugefont\" style=\"height: 35px\">\n");
      out.write("<td colspan=\"3\" valign=\"middle\" align=\"center\">\n");
      out.write("<b>\n");
      out.write("Database Project\n");
      out.write("</b>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("<tr style=\"height: 5px\">\n");
      out.write("<td colspan=\"3\">\n");
      out.write("&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("</table></CENTER>\n");
      out.write("<hr/>\n");
      out.write("\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
      out.write("<b class=\"headerfont\">Section 1</b><br/><br/>\n");
      out.write("\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
      out.write("<b class=\"bigbigfont\">Assignments</b> (Sort by <button name=\"byType\" type=\"button\">Assignment Type</button>, <button name=\"byDeadline\" type=\"button\">Deadline</button>, <button name=\"other\" type=\"button\">More Sorts</button>):<br/><!-- options for sorting by practice/uploaded, deadline(default), others? each is a button-->\n");
      out.write("\n");
      out.write("<table border=\"0\" cellpadding=\"0\" cellspacing=\"9\" width=\"100%\" class=\"normalfont\">\n");
      out.write("<tr>\n");
      out.write("<td class=\"bigfont\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Name</b></td>\n");
      out.write("<td class=\"centertable bigfont\"><b>Description</b></td>\n");
      out.write("<td class=\"centertable bigfont\"><b>Deadline</b></td>\n");
      out.write("</tr>\n");
      out.write("\n");
 String[][] assignments = (String[][])request.getAttribute("assignments");
String[] info = (String[])request.getAttribute("info");
for(int i=0; i<assignments.length; i++){
if(assignments[i][0] != null){
      out.write("\n");
      out.write("<tr>\n");
      out.write("<td class=\"normalfont\">\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
 out.println("<a href=\"student_assignment_servlet_path?id="+assignments[i][3]+"\">"+assignments[i][0]+"</a>"); 
      out.write("</td>\n");
      out.write("<td class=\"centertable normalfont\">");
 out.println(assignments[i][1]); 
      out.write("</td>\n");
      out.write("<td class=\"centertable normalfont\">");
 out.println(assignments[i][2]); 
      out.write("</td>\n");
      out.write("</tr>\n");
 }
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<br/>\n");
      out.write("\n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
      out.write("<b class=\"bigbigfont\">Section Information:</b><br/><!-- course title, time, day, room -->\n");
      out.write("<table border=\"0\" cellpadding=\"0\" cellspacing=\"9\" width=\"100%\" class=\"normalfont\">\n");
      out.write("<tr>\n");
      out.write("<td class=\"bigfont\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Course Title</b></td>\n");
      out.write("<td class=\"centertable bigfont\"><b>Location</b></td>\n");
      out.write("<td class=\"centertable bigfont\"><b>Days</b></td>\n");
      out.write("<td class=\"centertable bigfont\"><b>Time</b></td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td class=\"normalfont\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
 out.println(info[0]); 
      out.write("</td>\n");
      out.write("<td class=\"centertable normalfont\">Room ");
 out.println(info[1]); 
      out.write("</td>\n");
      out.write("<td class=\"centertable normalfont\">");
 out.println(info[2]); 
      out.write("</td>\n");
      out.write("<td class=\"centertable normalfont\">");
 out.println(info[3]); 
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<table border=\"0\" cellspacing=\"0\" cellpadding=\"2\" width=\"100%\">\n");
      out.write("<tr style=\"height: 5px\">\n");
      out.write("<td colspan=\"3\">\n");
      out.write("&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("</td>\n");
      out.write("<td style=\"width: 3px; background-color: black;\">&nbsp;</td>\n");
      out.write("<td style=\"width: 190px\" class=\"marginbackground\">\n");
      out.write("&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
