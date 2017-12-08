/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.sun.javafx.css.SizeUnits.S;
import java.io.*;  
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.S;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import static javax.swing.text.html.HTML.Tag.S;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants.S;
import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.S;

/**
 *
 * @author osman
 */
@WebServlet(urlPatterns = {"/Calculate"})
public class Calculate extends HttpServlet {

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
            throws ServletException, IOException {
        
       
        
        
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
        processRequest(request, response);
         PrintWriter out = response.getWriter();  
        out.println("<h1>hello </h1>");

        String number = request.getParameter("the_calculation");
        request.setAttribute("number" , number);
        
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = number;
        try {
            double result = Double.parseDouble(engine.eval(foo).toString());
            request.setAttribute("result" , result);
            request.getRequestDispatcher("Display.jsp").forward(request, response);
        } catch (ScriptException ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //request.getRequestDispatcher("Display.jsp").forward(request, response);
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
        processRequest(request, response);
       

        
        
        
        
        
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
