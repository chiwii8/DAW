/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Models;

import MVC.Models.Constants.ENTITY_QUERIES;
import MVC.Models.Constants.TABLE_VARIABLE_NAME;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Usuario.findByUserName",
            query = ENTITY_QUERIES.USER_SEARCH_BY_NAME),
    @NamedQuery(name = "Usuario.findByMail",
            query = ENTITY_QUERIES.USER_SEARCH_BY_MAIL),
    @NamedQuery(name = "Usuario.findByNameAndPassword",
            query = ENTITY_QUERIES.USER_SEARCH_BY_NAME_AND_PASSWORD)
})
@Table(name = TABLE_VARIABLE_NAME.TABLE_USER)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = TABLE_VARIABLE_NAME.USER_NAME, nullable = false, unique = true)
    private String username;

    @Column(name = TABLE_VARIABLE_NAME.USER_MAIL, nullable = false, unique = true)
    private String mail;

    @Column(name = TABLE_VARIABLE_NAME.USER_PASSWORD, nullable = false)
    private String password;
    //private String urlImg; si sobra tiempo añadir

    @OneToMany
    private List<Juego> juegos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return this.username.equals(other.username) && this.mail.equals(other.mail) && this.password.equals(other.password);
    }

    @Override
    public String toString() {
        return "MVC.Models.Usuario[ id=" + id + " ]";
    }

}
