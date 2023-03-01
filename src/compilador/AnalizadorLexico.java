/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase nos va a proporcionar un analizador lexico
 *
 * @author Meme
 * @version 18/04/2022/A
 */
public class AnalizadorLexico {

    //Campos de la clase
    private List<String[]> listaErrores = new ArrayList<>();
    private int apuntador = 1;
    private String codigo;
    private boolean finalizo = false;
    private int numeroLinea = 1;
    private int n = 0;
    private char c;

    /**
     * Constructor que asigna los valores
     * @param codigo Cadena que incluye el codigo a analizar
     */
    public AnalizadorLexico(String codigo) {
        this.codigo = codigo;
        c = codigo.charAt(n);
    }

    /**
     * Metodo que verfica si el caracter es o no una letra
     *
     * @param caracter El caracter actual que se esta leyendo
     * @return Verdadero o falso dependiendo si el caracter es una letra
     */
    private boolean EsLetra(char caracter) {
        return caracter >= 65 && caracter <= 90 || caracter >= 97 && caracter <= 122;
    }//Cierre metodo

    /**
     * Metodo que verifica si el caracter es o no un simbolo imprimible
     *
     * @param caracter El caracter actual que se esta leyendo
     * @return Verdadero o falso dependiendo si el caracter es un simbolo
     * imprimible
     */
    private boolean EsImprimible(char caracter) {
        return caracter >= 32 && caracter <= 126 && caracter != 34;

    }//Cierre metodo

    /**
     * Metodo que devuelve los tokens de las palabras reservadas
     *
     * @param lexema El lexema contiene la palabra que coincide con los
     * patrones.
     */
    private Token EsPalabraReservada(String lexema) {
        switch (lexema) {
            case "entero":
                return new Token("pr_entero");
            case "real":
                return new Token("pr_real");
            case "cadena":
                return new Token("pr_cadena");
            case "Potencia":
                return new Token("pr_Potencia");
            case "Output":
                return new Token("pr_Output");
            case "Input":
                return new Token("pr_Input");
            case "SI":
                return new Token("pr_SI");
            case "ENTONCES":
                return new Token("pr_ENTONCES");
            case "SINO":
                return new Token("pr_SINO");
            case "END":
                return new Token("pr_END");
            case "MIENTRAS":
                return new Token("pr_MIENTRAS");
            case "HACER":
                return new Token("pr_HACER");
            case "void":
                return new Token("pr_void");
            case "Main":
                return new Token("pr_Main");
            case "Inicio":
                return new Token("pr_Inicio");
            case "Fin":
                return new Token("pr_Fin");
            case "Call":
                return new Token("pr_Call");
            default:
                return new Token("identificador");
        }
    }//Cierre metodo

    /**
     * Metodo que analiza el codigo y retorna tokens
     * @return Un token
     */
    public Token ObtenerToken() {
        String lexema = "";
        int EstadoActual = 0;
        int token = 0;

        boolean seguirWhile = true;

        while (true && seguirWhile) {

            switch (EstadoActual) {
                case 0:
                    if (Character.isDigit(c)) {
                        EstadoActual = 14;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else if (EsLetra(c) || c == '_' || c == '$') {
                        EstadoActual = 15;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        switch (c) {
                            case '<':
                                EstadoActual = 1;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '>':
                                EstadoActual = 2;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '=':
                                EstadoActual = 3;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case ',':
                                EstadoActual = 4;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '(':
                                EstadoActual = 5;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case ')':
                                EstadoActual = 6;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '/':
                                EstadoActual = 7;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '*':
                                EstadoActual = 8;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '-':
                                EstadoActual = 9;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '+':
                                EstadoActual = 10;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case ':':
                                EstadoActual = 11;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '&':
                                EstadoActual = 12;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case '"':
                                EstadoActual = 13;
                                lexema += c;
                                c = SiguienteCaracter();
                                break;
                            case ' ':
                                c = SiguienteCaracter();
                                break;
                            case '%':
                                EstadoActual = 22;
                                c = SiguienteCaracter();
                                break;
                            case '\n':
                                numeroLinea++;
                                c = SiguienteCaracter();
                                break;
                            case '\0':
                                seguirWhile = false;
                                finalizo = true;
                                break;
                            default:
                                c = SiguienteCaracter();
                                EstadoActual = 0;
                                lexema = "";
                                listaErrores.add(new String[]{String.valueOf(numeroLinea), String.valueOf(n), "Lexico"});

                                break;
                        }
                    }
                    break;
                case 1:
                    if (c == '>') {
                        EstadoActual = 16;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else if (c == '=') {
                        EstadoActual = 17;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        token = 16;
                        return new Token("menor");
                    }
                    break;
                case 2:
                    if (c == '=') {
                        EstadoActual = 18;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        token = 15;
                        return new Token("mayor");
                    }
                    break;
                case 3:
                    token = 14;
                    return new Token("igual");
                case 4:
                    token = 13;
                    return new Token("coma");
                case 5:
                    token = 11;
                    return new Token("parentesis_abierto");
                case 6:
                    token = 12;
                    return new Token("parentesis_cerrado");
                case 7:
                    token = 10;
                    return new Token("division");
                case 8:
                    token = 9;
                    return new Token("multiplicacion");
                case 9:
                    token = 8;
                    return new Token("resta");
                case 10:
                    token = 7;
                    return new Token("suma");
                case 11:
                    token = 6;
                    return new Token("dos_puntos");
                case 12:
                    token = 5;
                    return new Token("fin_sentencia");
                case 13:
                    if (EsImprimible(c)) {
                        EstadoActual = 13;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else if (c == '"') {
                        EstadoActual = 19;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        EstadoActual = 0;
                        lexema = "";
                        listaErrores.add(new String[]{String.valueOf(numeroLinea), String.valueOf(n), "Lexico"});
                    }
                    break;
                case 14:
                    if (Character.isDigit(c)) {
                        EstadoActual = 14;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else if (c == '.') {
                        EstadoActual = 20;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        token = 2;
                        return new Token("entero");
                    }
                    break;
                case 15:
                    if (EsLetra(c) || Character.isDigit(c) || c == '_' || c == '$') {
                        EstadoActual = 15;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        token = 1;
                        return EsPalabraReservada(lexema);
                    }
                    break;
                case 16:
                    token = 19;
                    return new Token("diferente");
                case 17:
                    token = 18;
                    return new Token("menorigual");
                case 18:
                    token = 17;
                    return new Token("mayorigual");
                case 19:
                    token = 4;
                    return new Token("cadena");
                case 20:
                    if (Character.isDigit(c)) {
                        EstadoActual = 21;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        EstadoActual = 0;
                        lexema = "";
                        listaErrores.add(new String[]{String.valueOf(numeroLinea), String.valueOf(n), "Lexico"});
                    }
                    break;
                case 21:
                    if (Character.isDigit(c)) {
                        EstadoActual = 21;
                        lexema += c;
                        c = SiguienteCaracter();
                    } else {
                        token = 3;
                        return new Token("real");
                    }
                    break;
                case 22:
                    if (c == '\n' || c == '\0') {
                        EstadoActual = 0;
                    } else {
                        c = SiguienteCaracter();
                    }
                    break;

            }

        }
        return new Token("null");
    }//Cierre metodo

    /**
     * Metodo que devuelve la lista de errores
     * @return Listado de errores
     */
    public List<String[]> getListaErrores() {
        return listaErrores;
    }//Cierre metodo

    /**
     * Metodo que indica si finalizo el analisis del codigo
     * @return Verdadero o falso dependiendo si termino de analizar
     */
    public boolean Finalizo() {
        return finalizo;
    }//Cierre metodo

    /**
     * Metodo que entrega el siguiente caracter de la cadena
     * @return Siguiente caracter
     */
    private char SiguienteCaracter() {
        n++;
        return codigo.charAt(n);
    }//Cierre metodo
    

}//Cierre de la clase y del analizador lexico
