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
import javax.persistence.Table;

/**
 *
 * @author alejandro
 */
@Entity
@Table(name = TABLE_VARIABLE_NAME.TABLE_NEW)
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = TABLE_VARIABLE_NAME.NEW_TITLE, nullable = false)
    private String titulo;
    
    @Column(name = TABLE_VARIABLE_NAME.NEW_TYPE, nullable = false)
    private String tipoNoticia;
    
    @Column(name = TABLE_VARIABLE_NAME.NEW_URLIMAGE)
    private String urlImagenReferencia;     ///TODO: Revisar si es canónica o simplemente tomar una foto de las que se han subido a la noticia
    
    @Column(name = TABLE_VARIABLE_NAME.NEW_DESCRIPTION, nullable = false)
    private String descripción;             ///TODO: Revisar si es canónica o simplemente tomar un fragmento de texto independiente que se haya añadido

    ///TODO: Componetizar y mapear estas entidades
    //private List<String> Características;
    //private List<String> urlImagenes;
    //private List<String> texto;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlImagenReferencia() {
        return urlImagenReferencia;
    }

    public void setUrlImagenReferencia(String urlImagenReferencia) {
        this.urlImagenReferencia = urlImagenReferencia;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MVC.Models.Noticia[ id=" + id + " ]";
    }

}
