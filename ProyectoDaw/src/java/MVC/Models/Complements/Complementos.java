/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MVC.Models.Complements;

/**
 *
 * @author alejandro
 * Está interfaz va a definir los métodos get y set para las clases que
 * aportan una funcionalidad complementaria para las entidades que queremos
 * modelar
 */
public interface Complementos {
    
    /**
     * Nos permite modificar el orden en el que se ha establecido el componente
     * respecto del documento
     * @param numSecuence número representativo del orden
     */
    public void setNumberSecuence(int numSecuence);
    
    /**
     * Te permite obtener el orden en el que se ha establecido el componente
     * @return devuelve el orden del componente en el documento
     */
    public int getNumberSecuence();
    
    /**
     * Nos permite modificar el contenido del componente
     * @param content contenido del componente
     */
    public void setContent(String content);
    
    /**
     * Nos permite recuperar el contenido del componente
     * @return contenido del componente
     */
    public String getContent();
}
