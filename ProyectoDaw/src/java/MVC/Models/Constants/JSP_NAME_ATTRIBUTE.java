/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Models.Constants;

/**
 *
 * @author alejandro
 * En esta clase se almacenarán todas las etiquetas que se emplean en el JSP
 * Por lo que en caso de cambio en el JSP solo es necesario cambiarlo aqui
 */
public class JSP_NAME_ATTRIBUTE {
    ///Usuario en session
    public static final String SESSION_USER = "user";
    
    ///Mensajes al Cliente 
    public static final String MESSAGE_ERROR = "errorMessage";
    
    /// usuario
    public static final String USER_NAME = "userName";
    public static final String USER_PASSWORD = "userPassword";
    public static final String USER_OTHERPASSWORD = "userOtherPassword";
    public static final String USER_MAIL = "userMail";
    
    ///noticia
    public static final String NEW_TITLE = "title";
    public static final String NEW_TYPE = "typeNotice";
    public static final String NEW_URL_IMAGE = "imagenReferencia";
    public static final String NEW_DESCRIPTION = "description";
    
    ///juego
    public static final String GAME_SAGA = "sagaName";
    public static final String GAME_NAME = "name";
    public static final String GAME_VERSION = "version";
    public static final String GAME_IMAGE = "GameFront";
    public static final String GAME_DESCRIPTION ="description";
    
    //public static final String GAME_FEATURE = "feature";
    //public static final String GAME_IMAGE = "urlImage";
    
    ///Tipos de seccion a añadir en los juegos
    public static final String SECTION_TEXT = "text";
    public static final String SECTION_DASHBOARD = "picture_wall";
    public static final String SECTION_FEATURES ="features";
    public static final String [] GAME_SECTIONS = {SECTION_TEXT,SECTION_DASHBOARD,SECTION_FEATURES};
}
