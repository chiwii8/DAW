package MVC.Models;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-17T17:21:52")
@StaticMetamodel(Noticia.class)
public class Noticia_ { 

    public static volatile SingularAttribute<Noticia, List> texto;
    public static volatile SingularAttribute<Noticia, String> descripción;
    public static volatile SingularAttribute<Noticia, String> tipoNoticia;
    public static volatile SingularAttribute<Noticia, List> urlImagenes;
    public static volatile SingularAttribute<Noticia, String> titulo;
    public static volatile SingularAttribute<Noticia, String> urlImagenReferencia;
    public static volatile SingularAttribute<Noticia, Long> id;
    public static volatile SingularAttribute<Noticia, List> Características;

}