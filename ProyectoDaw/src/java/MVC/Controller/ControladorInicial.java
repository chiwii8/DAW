/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import MVC.Models.Constants.JSP_NAME_ATTRIBUTE;
import MVC.Models.Juego;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
@WebServlet(name = "ControladorInicial", urlPatterns = {"","/inicio/*","/filtro"})
public class ControladorInicial extends HttpServlet {

    @PersistenceContext(unitName = "ProyectoDawPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

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
        String id;
        Juego game;
        String vista = null;
        System.out.println("Entra en el processRequest de inicio");
        switch (accion) {
            case "":
            case "/":
                request.setAttribute(JSP_NAME_ATTRIBUTE.GAME_ALL, recoverGames());
                vista = "index.jsp";   ///No está añadida actualmente
                break;
            default:
                vista = "index.jsp";
        }
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/jsp/".concat(vista));
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

        String accion = request.getServletPath();
        String id;
        Juego game;

        String name;
        String saga;

        System.out.println("La acción que se está ejecutando" + accion);
        switch (accion) {
            case "/filtro":
                ///Aqui solo procesamos POST para los filtros por lo que no hay problema
                String where = "";
                boolean bn = false;
                
                ///Recuperamos los valores del filtro
                name = request.getParameter("nameFilter");
                saga = request.getParameter("sageFilter");

                if (!"".equals(name)) {
                    where = "WHERE j.nombre LIKE '" + name + "%'";
                    bn = true;
                }
                if (!"".equals(saga)) {
                    if (bn) {
                        where += " AND j.saga LIKE '" + saga + "%'";
                    } else {
                        where = "WHERE j.saga LIKE '" + saga + "%'";
                    }
                }

                String query = "SELECT j FROM Juego j " + (!where.isEmpty() ? where : "");

                TypedQuery<Juego> q1 = em.createQuery(query, Juego.class);
                List<Juego> games = (List<Juego>) q1.getResultList();
                request.setAttribute(JSP_NAME_ATTRIBUTE.GAME_ALL, games);

                RequestDispatcher rq = request.getRequestDispatcher("WEB-INF/jsp/filteredGames.jsp");
                rq.forward(request, response);
                break;
            default:
                ///No hace nada
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

    /**
     * Recupera todos los juegos 
     * @return devuelve una lista de todos los juegos
     */
    private List<Juego> recoverGames() {
        List<Juego> games;

        try {
            TypedQuery<Juego> query = em.createNamedQuery("Juego.findAll", Juego.class
            );
            games = query.getResultList();
        } catch (Exception ex) {
            games = null;
        }

        return games;
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
