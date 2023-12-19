/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Models.Constants;

/**
 *
 * @author alejandro
 * En esta clase se van a situar todas las variables que deciden el nombre de la tabla,
 * así como de cada uno de los atributos.
 * 
 * Cada variable del atributo va seguido del supuesto nombre de la tabla a la que pertenece
 * 
 */
public class TABLE_VARIABLE_NAME {
    
    ///Tabla de usuario
    public static final String TABLE_USER= "usuario";
    public static final String USER_NAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_MAIL = "mail";

    ///Tabla de juego
    public static final String TABLE_GAME = "juego";
    public static final String GAME_SAGE = "sage";
    public static final String GAME_NAME = "name";
    public static final String GAME_FRONT ="URLimage";
    public static final String GAME_DESCRIPTION = "descripcion";
    public static final String GAME_VERSION = "version";
    
    ///Tabla de noticias
    public static final String TABLE_NEW ="noticia";
    public static final String NEW_TITLE = "titulo";
    public static final String NEW_TYPE = "tipo";
    public static final String NEW_URLIMAGE = "imagen";
    public static final String NEW_DESCRIPTION = "descripcion";
    
    ///Tablas de los componentes
    
    //Tabla del contenedor
    public static final String TABLE_FEATURE = "caracteristicas";
    public static final String TABLE_IMAGE_BOARD = "muralimagenes";
    
    ///Tabla del elemento
    public static final String TABLE_TEXT = "texto";
    public static final String TABLE_IMAGE = "imagen";
    
    ///Caracteristica del Elemento
    public static final String COMPLEMENT_NUMSECUENCE = "numsecuence";
    public static final String COMPLEMENT_CONTENT = "content";
}
