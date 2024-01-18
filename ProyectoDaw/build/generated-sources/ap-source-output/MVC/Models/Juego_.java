package MVC.Models;

import MVC.Models.Noticia;
import MVC.Models.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T22:45:29")
@StaticMetamodel(Juego.class)
public class Juego_ { 

    public static volatile SingularAttribute<Juego, String> descripcion;
    public static volatile ListAttribute<Juego, Noticia> noticias;
    public static volatile SingularAttribute<Juego, Usuario> usuario;
    public static volatile SingularAttribute<Juego, Long> id;
    public static volatile SingularAttribute<Juego, String> saga;
    public static volatile SingularAttribute<Juego, String> nombre;
    public static volatile SingularAttribute<Juego, String> version;
    public static volatile SingularAttribute<Juego, String> UrlImagen;

}