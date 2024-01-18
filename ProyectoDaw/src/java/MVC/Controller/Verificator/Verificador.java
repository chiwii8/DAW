/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Controller.Verificator;

/**
 *
 * @author alejandro
 */
public class Verificador {
    
    /**
     * Nos permite validar los datos que queremos introducir como cadena de texto
     * si se cumple que tengan alguna metaEtiqueta o si hace referencia a table
     * 
     * @param data Array de String que vamos a evaluar si tienen etiquetas
     * @return devueve true si son válidos los datos
     */
    public static boolean validateData(String ...data){
        String patternScript="^[a-zA-Z0-9_@.]+$";
        int i = 0;
        boolean validos = true;
        while(i < data.length && validos){
            if(!data[i].matches(patternScript)){
                validos = false;
            }else
                i++;
        }
            return validos;
    }
    
    /**
     * Nos permite comparar dos objetos sin saber el tipo del objeto
     * Su función es ser genérico y nos permita evaluar para todas las entidades
     * 
     * @param obj1 Objeto 1 a comparar
     * @param obj2 Objeto 2 a comparar
     * @return verdadero si el son identicos los objetos, en caso contrario falso
     */
    public static boolean sameObject(Object obj1, Object obj2){
        return obj1.equals(obj2);
    }
    
}
