/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

/**
 *
 * @author Meme
 */
public class Error {
    private int numero;
    private int linea;
    private int columna;
    private String tipo;
    private String descripcion;
    
    public Error(int numero, int linea, int columna, String tipo, String descripcion){
        this.numero = numero;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public int getLinea(){
        return linea;
    }
    public int getColumna(){
        return columna;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String getDescripcion(){
        return tipo;
    }
}
