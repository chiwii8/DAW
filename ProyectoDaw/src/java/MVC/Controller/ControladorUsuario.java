/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import MVC.Controller.Verificator.Verificador;
import MVC.Models.Constants.JSP_NAME_ATTRIBUTE;
import MVC.Models.Constants.TABLE_VARIABLE_NAME;
import MVC.Models.Usuario;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
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
import javax.transaction.Transactional;

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
            case "/cerrarsesion/":
                vista = "logout.jsp";
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
        String vista = "index.jsp";

        Usuario user = new Usuario();
        String name;
        String password;
        String mail;
        String otherPassword;
        String salt;
        String hashpassword;
        String msg;

        try {
            switch (accion) {
                case "/forminiciasesion/":
                    vista = "SignIn.jsp";
                    name = request.getParameter(JSP_NAME_ATTRIBUTE.USER_NAME);
                    password = request.getParameter(JSP_NAME_ATTRIBUTE.USER_PASSWORD);

                    ///Verificamos los datos insertados
                    if (!Verificador.validateData(name, password)) {
                        throw new Exception("Los datos que intentas insertar no son válidos");
                    }

                    user = validateUserLoggin(name, password);

                    if (user != null) {
                        ///Se inicia sesión correctamente

                        createSession(user, request);
                        vista = "index.jsp";
                    } else {
                        throw new Exception("El usuario o contraseña son inválidos");
                    }
                    break;

                case "/formcrearcuenta/":
                    vista = "SignUp.jsp";

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

                    ///Verificamos que las contraseñas son iguales
                    if (!Verificador.sameObject(password, otherPassword)) {
                        throw new Exception("Las contraseñas no son iguales");
                    }

                    salt = generateSalt();
                    hashpassword = hashPassword(password, salt);
                    user.setSalt(salt);
                    user.setPassword(hashpassword);
                    if (!checkUser(user)) {
                        throw new Exception("No se ha podido crear la cuenta por datos existentes");
                    }

                    ///Datos validados, por lo que persisten
                    try {
                        System.out.println("Los datos del usuario son: " + user.getUserName() + " contra: " + user.getPassword() + " mail: " + user.getMail());
                        persist(user);
                    } catch (Exception e) {
                        System.out.println("Error: Imposible persistir Usuario: ");
                    }

                    ///Iniciar sesión
                    createSession(user, request);
                    ///Fin iniciar sesion
                    ///Vista destino
                    vista = "index.jsp";
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            msg = e.getMessage();
            System.out.println("Salta una excepcion aqui con el siguiente valor: " + msg);

            request.setAttribute(JSP_NAME_ATTRIBUTE.MESSAGE_ERROR, msg);
        }

        RequestDispatcher rq;
        
        if (vista.equals("index.jsp")) {
            System.out.println("Se redirecciona a la siguiente direccion");
            response.sendRedirect("/ProyectoDaw/");
        } else {
            rq = request.getRequestDispatcher("/WEB-INF/jsp/" + vista);
            rq.forward(request, response);
        }

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
        if (session != null) {
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
        ///Tomamos la session si hay
        HttpSession session = request.getSession(false);
        if (session != null) {
            ///En caso de que haya la invalidamos
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
    private Usuario validateUserLoggin(String userName, String password) throws Exception {
        boolean valid;
        Usuario user;
        Usuario result = null;
        try {
            ///Preparamos la query
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUserName", Usuario.class);
            query.setParameter(TABLE_VARIABLE_NAME.USER_NAME, userName);

            user = query.getSingleResult();

            valid = VerifyPassword(password, user.getPassword(), user.getSalt());
            if (valid) {
                result = user;
            }
        } catch (Exception e) {
            valid = false;
        }

        return result;
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
        String msg;
        try {
            valid = isUniqueUserName(user.getUserName());
            if (valid) {
                valid = isUniqueMail(user.getMail());
            } else {
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
    private boolean isUniqueUserName(String userName) throws PersistenceException, Exception {

        boolean valid = false;
        try {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUserName", Usuario.class);
            query.setParameter(TABLE_VARIABLE_NAME.USER_NAME, userName);
            query.getSingleResult();

            ///En caso de que exista lanzamos una excepcion
            throw new Exception("Ese nick no esta disponible");
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
    private boolean isUniqueMail(String mail) throws PersistenceException, Exception {
        boolean valid = false;
        try {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByMail", Usuario.class);
            query.setParameter(TABLE_VARIABLE_NAME.USER_MAIL, mail);
            query.getSingleResult();

            ///En caso de que exista lanzamos una excepcion
            throw new Exception("Ese mail no esta disponible");
        } catch (NoResultException ex) {
            valid = true;
        } catch (NonUniqueResultException ex) {
            throw new PersistenceException("Error al guardar un único mail ");
        }
        return valid;
    }

    /**
     * Generamos un salt de manera aleatoria
     * @return devuelve el Salt generado
     */
    private String generateSalt() {
        int saltLong = 16;
        // Utiliza SecureRandom para generar un salt aleatorio
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltLong];
        random.nextBytes(salt);

        // Codifica el salt en una cadena Base64 para almacenarlo
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Crea una contraseña hash para indexarlo en la base de datos
     * @param pass contraseña en texto plano
     * @param salt salt a aplicar sobre la contraseña
     * @return devuelve una contraseña condificada en SHA-256
     */
    private String hashPassword(String pass, String salt) {
        String result = null;
        try {
            // Concatenar la contraseña y el salt
            String passwordConSalt = pass + salt;

            // Crear una instancia del algoritmo de hash (por ejemplo, SHA-256)
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calcular el hash
            byte[] hash = digest.digest(passwordConSalt.getBytes());

            // Convertir el hash a una cadena Base64 para almacenarlo
            result = Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Verificamos si las contraseñas son identicas,
     * @param validatingPassword contraseña a validar
     * @param validpassword contraseña válida
     * @param storedSalt salt de la contraseña válida para aplicarlo sobre la contraseña en texto plano
     * @return devuelve si las contraseñas son idénticas
     */
    private boolean VerifyPassword(String validatingPassword, String validpassword, String storedSalt) {
        String hashPassword = hashPassword(validatingPassword, storedSalt);
        return validpassword.equals(hashPassword);
    }

    @Transactional
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
