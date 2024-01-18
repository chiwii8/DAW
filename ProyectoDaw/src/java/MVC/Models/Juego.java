/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Models;

import MVC.Models.Constants.TABLE_VARIABLE_NAME;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alejandro
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Juego.findAll",
            query = "SELECT j FROM Juego j"),
    @NamedQuery(name = "Juego.findById",
            query = "SELECT j FROM Juego j WHERE j.id =:id"),
    @NamedQuery(name = "Juego.findByNombre",
            query = "SELECT j FROM Juego j Where j.nombre = :nombre"),
    @NamedQuery(name = "Juego.findBySaga",
            query = "SELECT j FROM Juego j Where j.saga = :saga"),
    @NamedQuery(name = "Juego.findByNombreANDSAGA",
            query = "SELECT j FROM Juego j WHERE j.nombre=:nombre AND j.saga=:saga"),
    @NamedQuery(name = "Juego.findByUserId",
            query = "SELECT j FROM Juego j Where j.usuario.id=:usuarioId"),
    @NamedQuery(name = "Juego.deleteById",
            query = "DELETE FROM Juego WHERE id:=id")
})
@Table(name = TABLE_VARIABLE_NAME.TABLE_GAME)
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = TABLE_VARIABLE_NAME.GAME_SAGE, nullable = false)
    private String saga;

    @Column(name = TABLE_VARIABLE_NAME.GAME_NAME, nullable = false)
    private String nombre;

    @Column(name = TABLE_VARIABLE_NAME.GAME_VERSION, nullable = false)
    private String version;

    @Column(name = TABLE_VARIABLE_NAME.GAME_FRONT)
    private String UrlImagen;

    @Column(name = "descripcion")
    private String descripcion;

    ///TODO: Componetizar y mapear estas entidades
    //@Column(name =TABLE_VARIABLE_NAME.TABLE_FEATURE)
    //private List<String> Características;
    //@Column(name = TABLE_VARIABLE_NAME.TABLE_IMAGE_BOARD)
    //private List<String> urlImagenes;
    //@Column(name = TABLE_VARIABLE_NAME.TABLE_TEXT)
    //private List<String> textos;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @OneToMany
    private List<Noticia> noticias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaga() {
        return saga;
    }

    public void setSaga(String saga) {
        this.saga = saga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String UrlImagen) {
        this.UrlImagen = UrlImagen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    /*public List<String> getCaracterísticas() {
        return Características;
    }

    public void setCaracterísticas(List<String> Características) {
        this.Características = Características;
    }

    public List<String> getUrlImagenes() {
        return urlImagenes;
    }

    public void setUrlImagenes(List<String> urlImagenes) {
        this.urlImagenes = urlImagenes;
    }

    public List<String> getTextos() {
        return textos;
    }

    public void setTextos(List<String> textos) {
        this.textos = textos;
    }*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Identificamos si los juegos son iguales, unicamente teniendo en cuenta su
     * nombre que debe ser único
     *
     * @param object objeto a comparar
     * @return verdadero si son iguales, en caso contrario falso
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juego)) {
            return false;
        }
        Juego other = (Juego) object;

        return this.nombre.equals(other.nombre);
    }

    @Override
    public String toString() {
        return "MVC.Models.Juego[ id=" + id + " ]";
    }

}
