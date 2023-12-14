/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Models.Constants;

/**
 *
 * @author alejandro
 * En esta clase se crearan las consultas necesarias para realizar la parametrización necesaria, 
 * que nos ayudara un mantenimiento más fácil en caso de posibles cambios a lo largo del proyecto
 */
public class ENTITY_QUERIES {
    ///Consultas usuario
    public static final String USER_SEARCH_BY_NAME ="SELECT u FROM Usuario u WHERE u." + TABLE_VARIABLE_NAME.USER_NAME + " = :" + TABLE_VARIABLE_NAME.USER_NAME;
    public static final String USER_SEARCH_BY_MAIL = "SELECT u FROM Usuario u WHERE u." + TABLE_VARIABLE_NAME.USER_MAIL + " = :" + TABLE_VARIABLE_NAME.USER_MAIL;
    public static final String USER_SEARCH_BY_NAME_AND_PASSWORD = "SELECT u FROM Usuario u WHERE u." 
            + TABLE_VARIABLE_NAME.USER_NAME + " = :" + TABLE_VARIABLE_NAME.USER_NAME + " AND u."
            + TABLE_VARIABLE_NAME.USER_PASSWORD + " = :" + TABLE_VARIABLE_NAME.USER_PASSWORD;
    
    ///Consultas juego
    
    ///Consultas noticia
    
}
