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
import javax.persistence.OneToMany;

/**
 *
 * @author alejandro
 */
@Entity
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = TABLE_VARIABLE_NAME.GAME_SAGE , nullable = false)
    private String saga;
    
    @Column(name = TABLE_VARIABLE_NAME.GAME_NAME , nullable = false, unique = true)
    private String nombre;
    
    ///TODO: Componetizar y mapear estas entidades
    private List<String> Características;
    private List<String> urlImagenes;
    private List<String> textos;
    
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

    public List<String> getCaracterísticas() {
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

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public List<String> getTextos() {
        return textos;
    }

    public void setTextos(List<String> textos) {
        this.textos = textos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Identificamos si los juegos son iguales, unicamente teniendo en cuenta su nombre
     * que debe ser único
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
