/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVC.Controller;

import MVC.Models.Constants.JSP_NAME_ATTRIBUTE;
import MVC.Models.Juego;
import com.sun.xml.bind.api.impl.NameConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "ControladorDescargarJuego", urlPatterns = {"/descargarJuego/"})
public class ControladorDescargarJuego extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
       
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
        
        ///Descargamos la imagen correspondiente
        String accion = request.getPathInfo();
        String imageName = request.getParameter(JSP_NAME_ATTRIBUTE.GAME_IMAGE);
        System.out.println("Nombre de la imagen" + imageName);
        
        String contextoUrl = request.getRequestURL().toString();
        System.out.println("contexto Url");
        String contextoBase = contextoUrl.substring(0, contextoUrl.length() - request.getRequestURI().length());
        
        URL url = new URL(contextoBase + imageName);
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try (InputStream inputStream = connection.getInputStream()) {
                // Configura las cabeceras HTTP para indicar que es un archivo para descargar
                response.setHeader("Content-Disposition", "attachment; filename=" + imageName);

                // Copia el contenido del flujo de entrada al flujo de salida de la respuesta
                try (OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        
        ///Cierra la conexion
        connection.disconnect();
        
        
        
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
