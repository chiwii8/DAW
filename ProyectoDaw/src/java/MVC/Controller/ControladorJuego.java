/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import MVC.Controller.Verificator.Verificador;
import MVC.Models.Constants.JSP_NAME_ATTRIBUTE;
import MVC.Models.Juego;
import MVC.Models.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "ControladorJuego", urlPatterns = {"/juego/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
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
                ///Pendiente
                case "/verjuegos/":
                    Usuario user = (Usuario) session.getAttribute(JSP_NAME_ATTRIBUTE.SESSION_USER);
                    List<Juego> myGames = recoverMyGames(user.getId());
                    request.setAttribute(JSP_NAME_ATTRIBUTE.GAME_ALL, myGames);

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

        Juego game;
        String id;
        String sageName;
        String name;
        String version;
        String description;
        String msg;
        Part image;

        if ("/verJuego/".equals(accion)) {
            ///cargamos los datos del juego a ver
            id = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_ID);
            game = loadGame(Long.parseLong(id));
            if (game != null) {
                request.setAttribute(JSP_NAME_ATTRIBUTE.GAME_ONE, game);
            } else {
                request.setAttribute(JSP_NAME_ATTRIBUTE.MESSAGE_ERROR, "No se ha podido cargar el juego seleccionado");
            }
            vista = "ViewGame.jsp";
        } else {
            HttpSession session = request.getSession(false);
            Usuario user;
            if (session != null && (user = (Usuario) session.getAttribute(JSP_NAME_ATTRIBUTE.SESSION_USER)) != null) {
                game = new Juego();
                try {
                    switch (accion) {
                        case "/formnuevojuego/":
                            ///Operacion crear Juego
                            vista = "CUDGames.jsp";

                            sageName = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_SAGA);
                            name = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_NAME);
                            version = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_VERSION);
                            description = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_DESCRIPTION);

                            try {
                                image = request.getPart(JSP_NAME_ATTRIBUTE.GAME_IMAGE);
                                System.out.println("Valor de imagen " + image);
                                if (image != null && image.getSize() > 0) {
                                    ///Obtiene el nombre del fichero

                                    String fileName = sageName + "_" + name + ".".concat(image.getSubmittedFileName().split("\\.")[1]);

                                    ///Path Real del Archivo
                                    String context = request.getServletContext().getRealPath(JSP_NAME_ATTRIBUTE.IMAGE_RESOURCE);

                                    ///Guarda el archivo en el sistema de archivos
                                    Path savePath = Paths.get(context, fileName);
                                    try ( InputStream fileContent = image.getInputStream()) {
                                        Files.copy(fileContent, savePath, StandardCopyOption.REPLACE_EXISTING);
                                    }

                                    game.setUrlImagen(JSP_NAME_ATTRIBUTE.PREFIX_IMAGE_URL + fileName);
                                }
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }

                            ///Verificar los datos insertados
                            if (!Verificador.validateData(sageName, name, version)) {
                                throw new Exception("Los datos que intentas insertar no son válidos");
                            }
                            ///Inicializamos el juego
                            game.setSaga(sageName);
                            game.setNombre(name);
                            game.setVersion(version);
                            game.setDescripcion(description);

                            ///Pasan las verificaciones
                            if (!checkGame(game)) {
                                throw new Exception("Juego ya existente");
                            }

                            ///Datos validados, por lo que persisten
                            ///Insertamos al usuario 
                            game.setUsuario(user);
                            try {
                                persist(game);
                            } catch (Exception e) {
                                System.out.println("Error: Imposible persistir Juego: ");
                            }
                            ///Vista destino
                            vista = "index.jsp";
                            break;

                        case "/editarjuego/":
                            id = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_ID);
                            request.setAttribute(JSP_NAME_ATTRIBUTE.GAME_ONE, loadGame(Long.parseLong(id)));
                            vista = "ModifyGame.jsp";
                            break;

                        case "/formeditarjuego/":
                            vista = "ModifyGame.jsp";

                            id = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_ID);
                            sageName = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_SAGA);
                            name = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_NAME);
                            version = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_VERSION);
                            description = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_DESCRIPTION);

                            game.setSaga(sageName);
                            game.setNombre(name);
                            game.setVersion(version);
                            game.setDescripcion(description);

                            updateGame(Long.parseLong(id), game, request);
                            vista = "index.jsp";
                            break;
                        case "/borrarjuego/":
                            id = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_ID);
                            removeGame(Long.parseLong(id));
                            vista = "index.jsp";
                            break;
                        default:
                            vista = "index.jsp";
                    }

                } catch (Exception ex) {
                    msg = ex.getMessage();
                    ex.printStackTrace();
                    System.out.println("Salta una excepcion aqui con el siguiente valor: " + msg);

                    request.setAttribute(JSP_NAME_ATTRIBUTE.MESSAGE_ERROR, msg);
                }
            }
        }
        if (vista.equals("index.jsp")) {
            TypedQuery<Juego> query = em.createNamedQuery("Juego.findAll", Juego.class);
            List<Juego> games;
            try {
                games = query.getResultList();
            } catch (Exception e) {
                games = null;
            }
            request.setAttribute(JSP_NAME_ATTRIBUTE.GAME_ALL, games);
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

    /**
     * Nos permite verificar si existe un juego con esa Saga y nombre
     * @param game Objeto juego a verificar
     * @return devuelve true si no existe algún juego, sino false o por defecto lanza una excepcion
     */
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

    private List<Juego> recoverMyGames(Long userId) {
        List<Juego> games;
        try {
            TypedQuery<Juego> query = em.createNamedQuery("Juego.findByUserId", Juego.class);
            query.setParameter("usuarioId", userId);
            games = query.getResultList();
        } catch (Exception ex) {
            games = null;
        }

        return games;
    }

    private void removeGame(long id) {
        try {
            utx.begin();
            Juego game = em.find(Juego.class, id);
            if (game != null) {
                ///Eliminamos la entidad
                em.remove(game);

                ///Confirmamos la transaccion
                utx.commit();
            }
        } catch (Exception e) {

        }
    }

    private Juego loadGame(long id) {
        Juego result = null;
        try {
            result = em.find(Juego.class, id);
            if (result != null) {
                System.out.println("Se ha obtenido el objeto buscado por id");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Se ha cargado el juego");
        return result;
    }

    
    @Transactional
    private void updateGame(long id, Juego newgame, HttpServletRequest request) {    ////TODO
        Juego oldgame;
        try {
            oldgame = em.find(Juego.class, id);

            ///Verificamos que existe el juego
            if (oldgame != null) {
                ///Almacenamos las ID necesarias
                newgame.setId(oldgame.getId());
                newgame.setUsuario(oldgame.getUsuario());

                if (!oldgame.getNombre().equals(newgame.getNombre()) || !oldgame.getSaga().equals(newgame.getSaga())) {
                    ///Verificamos si hay algún juego con esas especificaciones
                    if (!checkGame(newgame)) {
                        throw new Exception("Ya existe un juego con esas especificaciones de saga y nombre");
                    }

                    try {
                        Part image = request.getPart(JSP_NAME_ATTRIBUTE.GAME_IMAGE);
                        System.out.println("Valor de imagen " + image);
                        if (image.getSize() != 0) {
                            ///Obtiene el nombre del fichero
                            System.out.println("Nombre del fichero " + image.getSubmittedFileName());
                            String fileName = newgame.getSaga() + "_" + newgame.getNombre() + ".".concat(image.getSubmittedFileName().split("\\.")[1]);
                            System.out.println("El nombre del fichero es:" + fileName);

                            ///Path Real del Archivo
                            String context = request.getServletContext().getRealPath(JSP_NAME_ATTRIBUTE.IMAGE_RESOURCE);
                            System.out.println("El path de ImagenesCaratula es: " + context);
                            ///Guarda el archivo en el sistema de archivos

                            Path savePath = Paths.get(context, fileName);
                            try ( InputStream fileContent = image.getInputStream()) {
                                Files.copy(fileContent, savePath, StandardCopyOption.REPLACE_EXISTING);
                            }

                            newgame.setUrlImagen(JSP_NAME_ATTRIBUTE.PREFIX_IMAGE_URL + fileName);
                        } else {
                            newgame.setUrlImagen(oldgame.getUrlImagen());
                        }
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                        System.out.println("Salta una excepcion cuando intentamos guardar una imagen");
                        System.out.println(e.getMessage());
                    }

                } else {
                    try {
                        Part image = request.getPart(JSP_NAME_ATTRIBUTE.GAME_IMAGE);
                        System.out.println("Valor de imagen " + image);
                        if (image.getSize() != 0) {
                            ///Obtiene el nombre del fichero
                            System.out.println("Nombre del fichero " + image.getSubmittedFileName());
                            String fileName = newgame.getSaga() + "_" + newgame.getNombre() + ".".concat(image.getSubmittedFileName().split("\\.")[1]);
                            System.out.println("El nombre del fichero es:" + fileName);

                            ///Path Real del Archivo
                            String context = request.getServletContext().getRealPath(JSP_NAME_ATTRIBUTE.IMAGE_RESOURCE);
                            System.out.println("El path de ImagenesCaratula es: " + context);
                            ///Guarda el archivo en el sistema de archivos

                            Path savePath = Paths.get(context, fileName);
                            try ( InputStream fileContent = image.getInputStream()) {
                                Files.copy(fileContent, savePath, StandardCopyOption.REPLACE_EXISTING);
                            }

                            newgame.setUrlImagen(JSP_NAME_ATTRIBUTE.PREFIX_IMAGE_URL + fileName);
                        } else {
                            newgame.setUrlImagen(oldgame.getUrlImagen());
                        }
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                        System.out.println("Salta una excepcion cuando intentamos guardar una imagen");
                        System.out.println(e.getMessage());
                    }
                }

                ///Aplicamos los cambios
                utx.begin();
                em.merge(newgame);
                utx.commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se ha encontrado el juego");
        }

    }
}
