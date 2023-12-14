/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Models.Complements.Elements;

import MVC.Models.Complements.Complementos;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alejandro
 * Este elemento se puede considerar ponerlo dentro de un contenedor MuralImagenes si es un elemento complementario
 * o simplemente ponerlo de manera independiente como un componente.
 * 
 * Actualmente no se considera ponerlo de manera independiente para facilitar el desarrollo,
 * en versiones futuras se considera ponerlo de manera independiente
 * 
 */
@Entity
public class Imagen implements Serializable,Complementos {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numSecuence;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
        @Override
    public void setNumberSecuence(int numSecuence) {
        this.numSecuence = numSecuence;
    }

    @Override
    public int getNumberSecuence() {
        return this.numSecuence;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
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
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MVC.Models.Complements.Elements.Imagen[ id=" + id + " ]";
    }
    
}
