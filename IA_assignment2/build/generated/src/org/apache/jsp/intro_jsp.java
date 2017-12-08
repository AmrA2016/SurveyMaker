package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public final class intro_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Intro jsp</title>\n");
      out.write("        <style>\n");
      out.write("       \n");
      out.write("        \n");
      out.write("        div{\n");
      out.write("            position:fixed;\n");
      out.write("            top: 25%;\n");
      out.write("            left: 50%;\n");
      out.write("            width:30em;\n");
      out.write("            text-align: center;\n");
      out.write("            \n");
      out.write("            margin-left: -15em; /*set to a negative number 1/2 of your width*/\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            background-color: #f3f3f3;\n");
      out.write("            padding: 100px 0px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .input{\n");
      out.write("            border-radius: 3px;\n");
      out.write("            padding: 5px 0px;\n");
      out.write("            border: 1px solid black;\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 20px;\n");
      out.write("           \n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        form{\n");
      out.write("            font-size:22px;\n");
      out.write("            display: inline-grid;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .submit-btn{\n");
      out.write("            padding: 10px;\n");
      out.write("            font-size: 20px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        ");

            boolean flag = false ;
            Cookie[] AllCookies = request.getCookies();
            if(AllCookies !=null){
                for (Cookie cookie : AllCookies) {
                    if (cookie.getName().equals("MyCurrentSession")) {
                      flag = true;
                      if(application.getAttribute("SessionManager") != null){//searching for session manager object  
                          String cookie_session_id = cookie.getValue();
                          HashMap<String,HttpSession> SessionManager = new HashMap<String,HttpSession>();
                          SessionManager = (HashMap)application.getAttribute("SessionManager");
                          
                          if(SessionManager.get(cookie_session_id) != null){// if session object exist in session manager
                              
      out.write("\n");
      out.write("                              <p> ");
 out.println("Name : " + session.getAttribute("name")); 
      out.write(" </p>\n");
      out.write("                              ");

                              
                              Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IA_Assignment", "root","");
                              
                              Statement Stmt = Con.createStatement();
                              ResultSet RS = Stmt.executeQuery("SELECT email, mobile FROM user WHERE session_id = '"+session.getId()+"' ");
                              
                              RS.next();
                              
      out.write("\n");
      out.write("                              <p> ");
 out.println("Email : " + RS.getString(1)); 
      out.write(" </p>\n");
      out.write("                              \n");
      out.write("                              <p> ");
 out.println("Phone Number : " + RS.getString(2)); 
      out.write(" </p>\n");
      out.write("                              \n");
      out.write("                              <p> ");
 out.println("Number of active sessions : " + SessionManager.size()); 
      out.write(" </p>\n");
      out.write("                              \n");
      out.write("                              <a href=\"logout\">logout</a>\n");
      out.write("                              ");


                              
                          }
                          
                          else{ // if session object does not exist 
                            cookie.setValue(null);
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                          }
                          
                      }
                      
                      else{ //if session manager object does not exist
                          
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                      }
                      
                     }
                   }
               
                if(!flag){
                    if(application.getAttribute("SessionManager") == null){
                        HashMap<String,HttpSession> SessionManager = new HashMap<String,HttpSession>();
                        application.setAttribute("SessionManager", SessionManager);
                        
                        
                    }
                    else{
                        
      out.write("\n");
      out.write("                            <div>\n");
      out.write("                                <form action=\"StoreTheSession\" method=\"post\">\n");
      out.write("                                    Name <input type=\"text\" name=\"name\" class='input' />\n");
      out.write("                                    Email <input type=\"text\" name=\"email\" class='input' />\n");
      out.write("                                    Phone Number <input type=\"text\" name=\"mobile\" class='input' />\n");
      out.write("                                    <br>\n");
      out.write("                                    <input type='submit' class='submit-btn'/>\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                        ");

                    }
                }
            }
            
        
      out.write("\n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
