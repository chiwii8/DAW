/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import MVC.Controller.Verificator.Verificador;
import MVC.Models.Constants.ENTITY_QUERIES;
import MVC.Models.Constants.JSP_NAME_ATTRIBUTE;
import MVC.Models.Constants.TABLE_VARIABLE_NAME;
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

/**
 *
 * @author alejandro
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/usuario/*"})
public class ControladorUsuario extends HttpServlet {

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
        String vista;

        switch (accion) {
            case "/iniciarsesion/":
                vista = "SignIn.jsp";
                break;
            case "/crearcuenta/":
                vista = "SignUp.jsp";
                break;
            case "/logout.jsp":
                logoutSession(request);

            ///La vista tras finalizar session sera index.jsp
            default:
                vista = "index.jsp";

        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/" + vista);
        rd.forward(request, response);

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
        ///En principio procesa el inicio de sesion y crear la cuenta al realizar los formularios 
        String accion = request.getPathInfo();
        String vista = null;

        Usuario user = new Usuario();
        String name;
        String password;
        String mail;
        String otherPassword;
        String msg;

        try {
            switch (accion) {
                case "/forminiciasesion/":
                    vista = "Sign-in.jsp";
                    name = request.getParameter(JSP_NAME_ATTRIBUTE.USER_NAME);
                    password = request.getParameter(JSP_NAME_ATTRIBUTE.USER_PASSWORD);
                    
                    ///Verificamos los datos insertados
                    if (!Verificador.validateData(name, password)) {
                        throw new Exception("Los datos que intentas insertar no son válidos");
                    }

                    user.setUserName(name);
                    user.setPassword(password);

                    validateUserLoggin(name, password);

                    ///Se inicia sesión correctamente
                    createSession(user, request);

                    vista = "index.jsp";
                    break;

                case "/formcrearcuenta/":
                    vista = "Sign-up.jsp";

                    name = request.getParameter(JSP_NAME_ATTRIBUTE.USER_NAME);
                    password = request.getParameter(JSP_NAME_ATTRIBUTE.USER_PASSWORD);
                    otherPassword = request.getParameter(JSP_NAME_ATTRIBUTE.USER_OTHERPASSWORD);
                    mail = request.getParameter(JSP_NAME_ATTRIBUTE.USER_MAIL);

                    ///Verificar los datos insertados
                    if (!Verificador.validateData(name, password, otherPassword, mail)) {
                        throw new Exception("Los datos que intentas insertar no son válidos");
                    }

                    ///Inicializamos el usuario
                    user.setUserName(name);
                    user.setMail(mail);
                    user.setPassword(password);
                    if (!checkUser(user)) {
                        throw new Exception("No se ha podido crear la cuenta por datos existentes");
                    }

                    ///Verificamos que las contraseñas son iguales
                    if (!Verificador.sameObject(password, otherPassword)) {
                        throw new Exception("Las contraseñas no son identicas");
                    }

                    ///Datos validados, por lo que persisten
                    try {
                        System.out.println("Los datos del usuario son: " + user.getUserName() + " contra: " + user.getPassword() + " mail: " + user.getMail());
                        persist(user);
                    } catch (Exception e) {
                        System.out.println("Error: Imposible persistir Usuario: ");
                    }

                    ///Iniciar sesión
                    ///Fin iniciar sesion
                    ///Vista destino
                    vista = "index.jsp";
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }

        RequestDispatcher rq = request.getRequestDispatcher("/jsp/" + vista);
        rq.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Este servlet se encarga de controlar los usuarios y realizar CRUD si es necesario";
    }// </editor-fold>

    /**
     * Crea una sesión con los parámetros pasados por parámetro
     *
     * @param name nombre del usuario
     * @param password contraseña del usuario
     */
    private void createSession(Usuario user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        
        ///Si hay una sesión ya abierta, la invalidamos
        if(session!=null){
            session.invalidate();
        }
        ///Abrimos una nueva sesión 
        session = request.getSession(true);
        session.setAttribute(JSP_NAME_ATTRIBUTE.SESSION_USER, user);
    }

    /**
     * Finaliza la session actual
     *
     * @param request respuesta del Httpservlet
     */
    private void logoutSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * Verificamos que el usuario que intenta iniciar sesión tenga una cuenta
     *
     * @param userName nombre de usuario
     * @param password contraseña esperada
     * @return true si inicia sesión correctamente, false en caso contrario
     * @throws Exception Errores surgidos mientras inicia sesión
     */
    private boolean validateUserLoggin(String userName, String password) throws Exception {
        boolean valid = false;
        try {
            ///Preparamos la query
            TypedQuery<Usuario> query = em.createNamedQuery(ENTITY_QUERIES.USER_SEARCH_BY_NAME_AND_PASSWORD, Usuario.class);
            query.setParameter(TABLE_VARIABLE_NAME.USER_NAME, userName);
            query.setParameter(TABLE_VARIABLE_NAME.USER_PASSWORD, password);

            ///Obtenemos el primer resultado
            query.getSingleResult();
            valid = true;
        } catch (NoResultException e) {
            throw new Exception("El usuario o contraseña son incorrectos");
        }

        return valid;
    }

    /**
     * Nos permite saber si el usuario a crear es válido, si no dispone de
     * ningun parámetro ya insertado en BD
     *
     * @param user datos del usuario a crear
     * @return true si es válido el usuario, false en caso contrario
     */
    private boolean checkUser(Usuario user) throws Exception {
        boolean valid = false;

        try {
            valid = isUniqueUserName(user.getUserName());
            if (valid) {
                valid = isUniqueMail(user.getMail());
            }
        } catch (PersistenceException e) {
            throw new Exception(e.getMessage());
        }
        return valid;
    }

    /**
     * Nos permite verificar que el usuario es único
     *
     * @param userName nombre de usuario
     * @param request respuesta http recibida
     * @return true si es único el usuario, en caso contrario false
     * @throws PersistenceException En caso de excepcion referente a la
     * persistencia de objetos se propaga
     */
    private boolean isUniqueUserName(String userName) throws PersistenceException {

        boolean valid = false;
        try {
            TypedQuery<Usuario> query = em.createNamedQuery(ENTITY_QUERIES.USER_SEARCH_BY_NAME, Usuario.class);
            query.setParameter(TABLE_VARIABLE_NAME.USER_NAME, userName);
            query.getSingleResult();
        } catch (NoResultException ex) {
            valid = true;
        } catch (NonUniqueResultException ex) {
            throw new PersistenceException("Error al guardar un único nick");
        }

        return valid;
    }

    /**
     * Nos permite verificar que el correo del usuario sea único
     *
     * @param mail correo del usuario
     * @return true si no existe otro correo igual, false en caso contrario
     * @throws PersistenceException En caso de excepcion referente a la
     * persistencia de objetos se propaga
     */
    private boolean isUniqueMail(String mail) throws PersistenceException {
        boolean valid = false;
        try {
            TypedQuery<Usuario> query = em.createNamedQuery(ENTITY_QUERIES.USER_SEARCH_BY_MAIL, Usuario.class);
            query.setParameter(TABLE_VARIABLE_NAME.USER_MAIL, mail);
            query.getSingleResult();
        } catch (NoResultException ex) {
            valid = true;
        } catch (NonUniqueResultException ex) {
            throw new PersistenceException("Error al guardar un único mail ");
        }
        return valid;
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
