/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package MVC.Models.Enums;

/**
 *
 * @author alejandro
 * Este enum nos permite nombrar y utilizar cualquiera de estos eventos para la realización de noticias que nos aportaran información de un juego en específico
 * para simplificar su funcionamiento
 */
public enum TIPO_NOTICIA {
    REPORTE("REPORTE"),
    ACTUALIZACION("ACTUALIZACIÓN"),
    VERSION_ALFA("VERSION ALFA"),
    VERSION_BETA("VERSION BETA"),
    VERSION_COMPLETA("VERSION COMPLETA"),
    RESUMEN_JUEGO("RESUMEN JUEGO"),
    OPINION("OPINION");
    
    private final String nombre;
    private TIPO_NOTICIA(String nombreAsociado){
        nombre = nombreAsociado;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
