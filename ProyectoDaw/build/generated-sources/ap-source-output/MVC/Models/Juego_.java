package MVC.Models;

import MVC.Models.Noticia;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-17T17:21:52")
@StaticMetamodel(Juego.class)
public class Juego_ { 

    public static volatile ListAttribute<Juego, Noticia> noticias;
    public static volatile SingularAttribute<Juego, List> urlImagenes;
    public static volatile SingularAttribute<Juego, List> textos;
    public static volatile SingularAttribute<Juego, Long> id;
    public static volatile SingularAttribute<Juego, String> saga;
    public static volatile SingularAttribute<Juego, String> nombre;
    public static volatile SingularAttribute<Juego, List> Características;

}