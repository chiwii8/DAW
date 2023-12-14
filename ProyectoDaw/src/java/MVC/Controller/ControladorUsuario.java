/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import MVC.Models.Constants.JSP_NAME_ATTRIBUTE;
import MVC.Models.Constants.TABLE_VARIABLE_NAME;
import MVC.Models.Usuario;
import java.io.IOException;
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
                vista = "Sign-in.jsp";
                break;
            case "/crearcuenta/":
                vista = "Sign-up.jsp";
                break;
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
        TypedQuery<Usuario> query;
        String accion = request.getPathInfo();
        String vista = null;

        Usuario user = new Usuario();
        String name;
        String password;
        String mail;
        String otherPassword;

        switch (accion) {
            case "/forminiciasesion/":
                name = request.getParameter(JSP_NAME_ATTRIBUTE.USER_NAME);
                password = request.getParameter(JSP_NAME_ATTRIBUTE.USER_PASSWORD);
                
                ///Verificamos los datos insertados
                //Verificador.datosValidos(name,password);
                
                user.setUserName(name);
                user.setPassword(password);
                
                try {
                    query = em.createNamedQuery("Usuario.findByNameAndPassword", Usuario.class);
                    query.setParameter(TABLE_VARIABLE_NAME.USER_NAME, name);
                    query.setParameter(TABLE_VARIABLE_NAME.USER_PASSWORD, password);
                    Usuario result = query.getSingleResult();
                } catch (Exception e) {
                    System.out.println("No se ha encontrado un usuario");
                    ///Error si no se encuentra el usuario
                }
                
                ///Si se verifica correctamente, creamos una sesion y lo mandamos al inicio
                //Si se produce un fallo volvemos a llamar la vista pero procesamos el fallo y lo mostramos
                //vista = "/iniciarsesion/";
                break;
            case "/formcrearcuenta/":
                name = request.getParameter(JSP_NAME_ATTRIBUTE.USER_NAME);
                password = request.getParameter(JSP_NAME_ATTRIBUTE.USER_PASSWORD);
                otherPassword = request.getParameter(JSP_NAME_ATTRIBUTE.USER_OTHERPASSWORD);
                mail = request.getParameter(JSP_NAME_ATTRIBUTE.USER_MAIL);

                ///Verificar los datos insertados
                //Verificador.datosValidos(name,password,otherPassword,mail);
                //Verificamos si ya existe algun usuario
                ///Inicializamos el usuario
                user.setUserName(name);
                user.setMail(mail);
                user.setPassword(password);

                /*try {
                    query = em.createNamedQuery("Usuario.findByUserName", Usuario.class);
                    query.setParameter("userName", name);
                    Usuario result = query.getSingleResult();
                } catch (NoResultException e) {
                    System.out.println("No hay resultados por lo que se puede insertar");
                }

                if (user.equals(result)) { //Verificamos si coinciden las contraseñas
                    ///Resolvemos el problema
                    System.out.println("Se ha encontrado el mismo nombre de usuario");
                }

                ///Verificamos que las contraseñas son iguales
                if (!Verificador.sameObject(password, otherPassword)) {
                    ///Resolvemos el problema
                    otherPassword = null;
                }*/

                try {
                    System.out.println("Los datos del usuario son: " + user.getUserName() + " contra: " + user.getPassword() + " mail: " + user.getMail());
                    persist(user);
                } catch (Exception e) {
                    System.out.println("Error: Imposible persistir Usuario: ");
                }

                ///Tras todas las verificaciones, si correcto creamos un nuevo usuario
                vista = "index.jsp";   ///se crea correctamente, se inicia automáticamente la sesión, por lo que le lleva al inicio
            ///En caso contrario lanzamos un mensaje de error con el error obtenido
            // se produce un fallo volvemos a llamar la vista pero procesamos el fallo y lo mostramos
            //vista = "/jsp/Sign-in.jsp";
                break;
            default:
                throw new AssertionError();
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
        return "Este servlet se encarga de controlar los usuarios y realizar CRUD si es necesario";
    }// </editor-fold>

    
    /**
     * Crea una sesión con los parámetros pasados por parámetro
     *
     * @param name nombre del usuario
     * @param password contraseña del usuario
     */
    private void createSession(String name, String password) {

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
