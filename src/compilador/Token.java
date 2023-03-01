/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

/**
 *
 * @author Meme
 */
public class Token {

    private String nombre;
    private String atributo;

    public Token(String nombre) {
        this.nombre = nombre;
        this.atributo = "N\\A";
    }

    public Token(String nombre, String atributo) {
        this.nombre = nombre;
        this.atributo = atributo;
    }


    public String getNombre() {
        return nombre;
    }

    public String getAtributo() {
        return atributo;
    }
}
