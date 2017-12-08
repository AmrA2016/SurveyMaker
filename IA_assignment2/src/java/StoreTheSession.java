/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import myJavaClasses.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author osman
 */
@WebServlet(urlPatterns = {"/StoreTheSession"})
public class StoreTheSession extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           User user = new User();
           user.name = request.getParameter("name");
           user.email = request.getParameter("email");
           user.mobile = request.getParameter("mobile");
           
           //Creating session
           HttpSession session = request.getSession(true);
           session.setAttribute("name", user.name);
           //put the session details on the session manager hash map which in application object
           HashMap<String,HttpSession> SessionManager = new HashMap<String,HttpSession>();
           SessionManager = (HashMap<String, HttpSession>)request.getServletContext().getAttribute("SessionManager");
           SessionManager.put(session.getId(), session);
           
           //Creating cookie
           Cookie MyCurrentSession = new Cookie("MyCurrentSession", session.getId());
           MyCurrentSession.setMaxAge(180);
           MyCurrentSession.setPath("/");
           response.addCookie(MyCurrentSession);
           
           //Connect to the database
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StoreTheSession.class.getName()).log(Level.SEVERE, null, ex);
            }
           Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IA_Assignment", "root","");
           
           Statement Stmt = Con.createStatement();
           Stmt.executeUpdate("INSERT INTO user (session_id ,email , mobile ) VALUES( '"+session.getId()+"' , '"+user.email+"' , '"+user.mobile+"')");
           out.print("connected");
           
           //session.invalidate();
           response.sendRedirect("intro.jsp");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(StoreTheSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(StoreTheSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
