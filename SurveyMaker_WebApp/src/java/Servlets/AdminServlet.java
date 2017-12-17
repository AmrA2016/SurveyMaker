/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.User;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Manar Ashraf
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/Make_Admin", "/Suspend_User", "/Unsuspend_User" , "/GetUsers"})
public class AdminServlet extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
         String path = request.getServletPath();
       if(path.equals("/Make_Admin")) {
            makeAdmin(request, response);
        }
       else if(path.equals("/Suspend_User")){
            suspendUser(request,response);
        }else if (path.equals("/Unsuspend_User")) {
            unSuspendUser(request, response);
        }
        else if(path.equals("/GetUsers")){
            getUsers(request, response);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void makeAdmin(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
    {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        UserModel user = new UserModel();
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                int id = Integer.parseInt(request.getParameter("user_id"));
                user.setAdmin(id);
                response.sendRedirect(request.getContextPath() + "/GetUsers");
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }
    public void suspendUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
    {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        UserModel user = new UserModel();
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                int id = Integer.parseInt(request.getParameter("user_id"));
                user.setSuspended(id);
                response.sendRedirect(request.getContextPath() + "/GetUsers");
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }
    public void unSuspendUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
    {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        UserModel user = new UserModel();
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                int id = Integer.parseInt(request.getParameter("user_id"));
                user.setUnSuspended(id);
                response.sendRedirect(request.getContextPath() + "/GetUsers");
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        UserModel user = new UserModel();
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                ArrayList<Integer> user_ids = UserModel.getAllNormalUsers();
                ArrayList<User> normal_users = new ArrayList<>();
                
                for(int i = 0;i < user_ids.size();i++)
                    normal_users.add(UserModel.getByID(user_ids.get(i)));
                
                request.setAttribute("Users", normal_users);
                request.getRequestDispatcher("Admin/normal_users.jsp").forward(request, response);
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }
}
