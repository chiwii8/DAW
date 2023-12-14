/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Models.Complements.Containers;

import MVC.Models.Complements.Complementos;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alejandro
 * Esta clase es un contenedor que nos permite agrupar las distintas textos subidas como una lista de características
 */
@Entity
public class Caracteristicas implements Serializable, Complementos {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numSecuence;

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

    /**
     * Este método no esta soportado para esta clase
     * @param content 
     */
    @Override
    public void setContent(String content) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Este método no esta soportado para esta clase
     * @return 
     */
    @Override
    public String getContent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        if (!(object instanceof Caracteristicas)) {
            return false;
        }
        Caracteristicas other = (Caracteristicas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MVC.Models.Complements.Containers.Caracteristicas[ id=" + id + " ]";
    }

}
