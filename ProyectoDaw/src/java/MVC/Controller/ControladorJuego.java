/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "ControladorJuego", urlPatterns = {"/juego/*"})
public class ControladorJuego extends HttpServlet {

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
        String accion = request.getPathInfo();
        String vista = null;

        switch (accion) {
            case "/nuevojuego/":
                vista = "";
                break;

            case "/borrarjuego/":
                vista = "";
                break;

            case "/editarjuego/":
                vista = "";
                break;
            default:
                throw new AssertionError();
        }

        RequestDispatcher rq = request.getRequestDispatcher("WEB-INF/jsp/" + vista);
        rq.forward(request, response);
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

        ///Tratamos los formularios de insertar y modificar
        String accion = request.getPathInfo();
        String vista = null;

        switch (accion) {
            case "/formnuevousuario/":
                
                break;

            case "/formeditarusuario/":
                vista = "";
                break;
            default:
                vista = "index.jsp";
        }

        RequestDispatcher rq = request.getRequestDispatcher("WEB-INF/jsp/" + vista);
        rq.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Este servlet se encarga de controlar los juegos y realizar CRUD si es necesario";
    }// </editor-fold>

}
