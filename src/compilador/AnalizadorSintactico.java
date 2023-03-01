/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

/**
 *
 * @author Meme
 */
public class AnalizadorSintactico {

    private AnalizadorLexico lexico;
    private Token token;

    public AnalizadorSintactico(String codigo) {
        lexico = new AnalizadorLexico(codigo);
        token = lexico.ObtenerToken();
    }
    
    public void IniciarAnalisis(){
        S();
    }

    private void S() {
        if (token.getNombre().equals("pr_void")) {
            AvanzarToken();
            METODO();
        } else {
            //error;
        }
    }

    private void METODO() {
        if (token.getNombre().equals("identificador")) {
            AvanzarToken();
            if (token.getNombre().equals("parentesis_abierto")) {
                AvanzarToken();
                PARAMETRO();
                if (token.getNombre().equals("parentesis_cerrado")) {
                    AvanzarToken();
                    if (token.getNombre().equals("pr_Inicio")) {
                        AvanzarToken();
                        SENTENCIA();
                        if (token.getNombre().equals("pr_Fin")) {
                            AvanzarToken();
                            S();
                        } else {
                            //error
                        }
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else if (token.getNombre().equals("pr_Main")) {
            AvanzarToken();
            if (token.getNombre().equals("parentesis_abierto")) {
                AvanzarToken();
                PARAMETRO();
                if (token.getNombre().equals("parentesis_cerrado")) {
                    AvanzarToken();
                    if (token.getNombre().equals("pr_Inicio")) {
                        AvanzarToken();
                        SENTENCIA();
                        if (token.getNombre().equals("pr_Fin")) {
                            //Aqui finaliza el analisis
                        } else {
                            //error
                        }
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void PARAMETRO() {
        if (token.getNombre().equals("identificador")) {
            AvanzarToken();
            if (token.getNombre().equals("dos_puntos")) {
                AvanzarToken();
                TIPO();
            } else {
                //error
            }
        } else if (token.getNombre().equals("parentesis_cerrado")) {
            //no se hace nada
        } else {
            //error
        }
    }

    private void SENTENCIA() {
        if (token.getNombre().equals("identificador") || token.getNombre().equals("pr_Output") || token.getNombre().equals("pr_Input") || token.getNombre().equals("pr_Output") || token.getNombre().equals("pr_Call")) {
            SENTENCIA2();
            SENTENCIA();
        } else if (token.getNombre().equals("pr_SI") || token.getNombre().equals("pr_MIENTRAS")) {
            SENTENCIA3();
            SENTENCIA();
        } else if (token.getNombre().equals("pr_Fin")) {
            //No se hace nada
        } else {
            //error
        }
    }

    private void SENTENCIA2() {
        if (token.getNombre().equals("identificador")) {
            AvanzarToken();
            DECLARASINGACION();
        } else if (token.getNombre().equals("pr_Output")) {
            ESCRITURA();
        } else if (token.getNombre().equals("pr_Input")) {
            LECTURA();
        } else if (token.getNombre().equals("pr_Call")) {
            LLAMADA();
        } else {
            //error
        }
    }

    private void SENTENCIA3() {
        if (token.getNombre().equals("pr_SI")) {
            CONDICIONAL();
        } else if (token.getNombre().equals("pr_MIENTRAS")) {
            CICLO();
        } else {
            //error
        }
    }

    private void DECLARASINGACION() {
        if(token.getNombre().equals("dos_puntos")){
            AvanzarToken();
            TIPO();
            VALOR();
            if(token.getNombre().equals("fin_sentencia")){
                AvanzarToken();
            } else {
                //error
            }
        } else if(token.getNombre().equals("igual")){
            AvanzarToken();
            DATO();
            if(token.getNombre().equals("fin_sentencia")){
                AvanzarToken();
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void TIPO() {
        if(token.getNombre().equals("pr_entero") || token.getNombre().equals("pr_real") || token.getNombre().equals("pr_cadena")){
            AvanzarToken();
        } else {
            //error
        }
    }

    private void VALOR() {
        if(token.getNombre().equals("igual")){
            AvanzarToken();
            VALOR3();
        } else if(token.getNombre().equals("fin_sentencia")){
            //No se hace nada
        } else {
            //error
        }
    }

    private void VALOR3() {
        if(token.getNombre().equals("entero") || token.getNombre().equals("real")){
            NUMERO();
        } else if(token.getNombre().equals("cadena")){
            AvanzarToken();
        } else {
            //error
        }
    }

    private void DATO() {
        if(token.getNombre().equals("entero") || token.getNombre().equals("real")){
            OPERACION();
        } else if(token.getNombre().equals("cadena")){
            OPERACION2();
        } else if(token.getNombre().equals("pr_Potencia")){
            OPERACION3();
        } else {
            //error
        }
    }

    private void OPERACION() {
        if(token.getNombre().equals("entero") || token.getNombre().equals("real")){
            NUMERO();
            OPERADOR();
        } else {
            //error
        }
    }

    private void NUMERO() {
        if(token.getNombre().equals("entero") || token.getNombre().equals("real")){
            AvanzarToken();
        } else {
            //error
        }
    }

    private void OPERADOR() {
        if(token.getNombre().equals("suma") || token.getNombre().equals("resta") || token.getNombre().equals("multiplicacion") || token.getNombre().equals("division")){
            AvanzarToken();
            OPERACION();
        } else if(token.getNombre().equals("fin_sentencia")){
            //no se hace nada
        } else {
            //error
        }
    }

    private void OPERACION2() {
        if(token.getNombre().equals("cadena")){
            AvanzarToken();
            OPERADOR2();
        } else {
            //error
        }
    }

    private void OPERADOR2() {
        if(token.getNombre().equals("suma")){
            AvanzarToken();
            OPERACION2();
        } else if(token.getNombre().equals("fin_sentencia")){
            //no se hace nada
        } else {
            //error
        }
    }

    private void OPERACION3() {
        if(token.getNombre().equals("pr_Potencia")){
            AvanzarToken();
            if(token.getNombre().equals("parentesis_abierto")){
                AvanzarToken();
                NUMERO();
                if(token.getNombre().equals("coma")){
                    AvanzarToken();
                    NUMERO();
                    if(token.getNombre().equals("parentesis_cerrado")){
                        AvanzarToken();
                        if(token.getNombre().equals("fin_sentencia")){
                            AvanzarToken();
                        } else {
                            //error
                        }
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void CONDICIONAL() { //No puede ir vacia
        if(token.getNombre().equals("pr_SI")){
            AvanzarToken();
            if(token.getNombre().equals("parentesis_abierto")){
                AvanzarToken();
                CONDICION();
                if(token.getNombre().equals("parentesis_cerrado")){
                    AvanzarToken();
                    if(token.getNombre().equals("pr_ENTONCES")){
                        AvanzarToken();
                        SENTENCIA2();
                        if(token.getNombre().equals("pr_SINO")){
                            AvanzarToken();
                            SENTENCIA2();
                            if(token.getNombre().equals("pr_END")){
                                AvanzarToken();
                            } else {
                                //error
                            }
                        } else {
                            //error
                        }
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void CONDICION() {
        if(token.getNombre().equals("identificador") || token.getNombre().equals("entero") || token.getNombre().equals("real") || token.getNombre().equals("cadena")){
            VALOR2();
            OPERADOR3();
            VALOR2();
        } else {
            //error
        }
    }

    private void OPERADOR3() {
        if(token.getNombre().equals("igual") || token.getNombre().equals("mayor") || token.getNombre().equals("menor") || token.getNombre().equals("mayorigual") || token.getNombre().equals("menorigual") || token.getNombre().equals("diferente")){
            AvanzarToken();
        } else {
            //error
        }
    }

    private void VALOR2() {
        if(token.getNombre().equals("identificador")){
            AvanzarToken();   
        } else if(token.getNombre().equals("entero") || token.getNombre().equals("real")){
            NUMERO();
        } else if(token.getNombre().equals("cadena")){
            AvanzarToken();
        } else {
            //error
        }
    }

    private void CICLO() {
        if(token.getNombre().equals("pr_MIENTRAS")){
            AvanzarToken();
            if(token.getNombre().equals("parentesis_abierto")){
                AvanzarToken();
                CONDICION();
                if(token.getNombre().equals("parentesis_cerrado")){
                    AvanzarToken();
                    if(token.getNombre().equals("pr_HACER")){
                        AvanzarToken();
                        if(token.getNombre().equals("pr_END")){
                            AvanzarToken();
                        } else {
                            //error
                        }
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void ESCRITURA() {
        if(token.getNombre().equals("pr_Output")){
            AvanzarToken();
            if(token.getNombre().equals("parentesis_abierto")){
                AvanzarToken();
                DATO2();
                if(token.getNombre().equals("parentesis_cerrado")){
                    AvanzarToken();
                    if(token.getNombre().equals("fin_sentencia")){
                        AvanzarToken();
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void DATO2() {
        if(token.getNombre().equals("identificador") || token.getNombre().equals("entero") || token.getNombre().equals("real") || token.getNombre().equals("cadena")){
            VALOR2();
            OTRO();
        } else {
            //error
        }
    }

    private void OTRO() {
        if(token.getNombre().equals("coma")){
            AvanzarToken();
            VALOR2();
            OTRO();
        } else if(token.getNombre().equals("parentesis_cerrado")){
            //no se hace nada
        } else{
            //error
        }
    }

    private void LECTURA() {
        if(token.getNombre().equals("pr_Input")){
            AvanzarToken();
            if(token.getNombre().equals("parentesis_abierto")){
                AvanzarToken();
                if(token.getNombre().equals("identificador")){
                    AvanzarToken();
                    if(token.getNombre().equals("parentesis_cerrado")){
                        AvanzarToken();
                        if(token.getNombre().equals("fin_sentencia")){
                            AvanzarToken();
                        } else {
                            //error
                        }
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void LLAMADA() {
        if(token.getNombre().equals("pr_Call")){
            AvanzarToken();
            if(token.getNombre().equals("parentesis_abierto")){
                AvanzarToken();
                if(token.getNombre().equals("identificador")){
                    AvanzarToken();
                    if(token.getNombre().equals("parentesis_cerrado")) {
                        AvanzarToken();
                        if(token.getNombre().equals("fin_sentencia")){
                            AvanzarToken();
                        } else {
                            //error
                        }
                    } else {
                        //error
                    }
                } else {
                    //error
                }
            } else {
                //error
            }
        } else {
            //error
        }
    }

    private void AvanzarToken() {
        token = lexico.ObtenerToken();
    }

}
