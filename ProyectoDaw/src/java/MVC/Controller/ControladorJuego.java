/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import MVC.Models.Constants.JSP_NAME_ATTRIBUTE;
import MVC.Models.Juego;
import MVC.Models.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "ControladorJuego", urlPatterns = {"/juego/*"})
public class ControladorJuego extends HttpServlet {

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
        String vista = "index.jsp";

        ///Verificamos si hay una sesion activa
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute(JSP_NAME_ATTRIBUTE.SESSION_USER) != null) {
            ///si esta activa, proseguimos con la peticion
            switch (accion) {
                case "/nuevojuego/":
                    vista = "CUDGames.jsp";
                    break;
                case "/editarjuego/":
                    vista = "";
                    break;
                case "/borrarjuego/":
                    vista = "";
                    break;
                case "/verjuegos/":
                    ///Mostramos sus propios juegos juegos
                    vista = "myGames.jsp";
                    break;

                default:
                    throw new AssertionError();
            }
        }

        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/jsp/" + vista);
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
        String vista = "index.jsp";

        Juego game = new Juego();
        String sageName;
        String name;
        String version;
        String description;
        Part image;

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(JSP_NAME_ATTRIBUTE.SESSION_USER) != null) {
            try {
                switch (accion) {
                    case "/formnuevojuego/":
                        ///Operacion crear Juego
                        vista = "CUDGames.jsp";

                        sageName = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_SAGA);
                        name = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_NAME);
                        version = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_VERSION);
                        description = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_DESCRIPTION);

                        ///Verificar los datos insertados
                        //if (!Verificador.validateData(sageName, name, version, description)) {
                        //    throw new Exception("Los datos que intentas insertar no son válidos");
                        //}
                        ///Inicializamos el usuario
                        game.setSaga(sageName);
                        game.setNombre(name);
                        game.setVersion(version);
                        game.setDescripcion(description);

                        ///Pasan las verificaciones
                        //if (!checkGame(game)) {
                        //    throw new Exception("No se ha podido crear el juego por datos existentes en la base de datos");
                        //}
                        ///Verificamos que las contraseñas son iguales
                        //if (!Verificador.sameObject(password, otherPassword)) {
                        //    throw new Exception("Las contraseñas no son identicas");
                        //}
                        ///Datos validados, por lo que persisten
                        ///Insertamos al usuario 
                        game.setUsuario((Usuario) session.getAttribute(JSP_NAME_ATTRIBUTE.SESSION_USER));
                        try {
                            System.out.println("Los datos del usuario son: " + game.getNombre() + " version: " + game.getVersion());
                            persist(game);
                        } catch (Exception e) {
                            System.out.println("Error: Imposible persistir Usuario: ");
                        }

                        ///Vista destino
                        vista = "index.jsp";
                        break;

                    case "/formeditarjuego/":
                        vista = "";
                        break;
                    default:
                        vista = "index.jsp";
                }

            } catch (Exception ex) {

            }
        }

        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/jsp/" + vista);
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

    private boolean checkGame(Juego game) {
        boolean valid = false;
        try {
            TypedQuery<Juego> query = em.createNamedQuery("Juego.findByNombreANDSAGA", Juego.class);
            query.setParameter("nombre", game.getNombre());
            query.setParameter("saga", game.getSaga());
            query.getSingleResult();
        } catch (NoResultException ex) {
            valid = true;
        } catch (NonUniqueResultException ex) {
            throw new PersistenceException("Error al guardar un único nick");
        }

        return valid;

    }

}
