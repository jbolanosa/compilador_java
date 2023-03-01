/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compilador;

import com.google.common.base.Charsets;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Meme
 */
public class Compilador {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

    }

    public static void Analizar(String ruta) throws Exception {
        //List<Token> listaTokens = new ArrayList<>();
        File archivo = new File(ruta);
        String codigo = com.google.common.io.Files.asCharSource(archivo, Charsets.UTF_8).read();
        AnalizadorSintactico sintactico = new AnalizadorSintactico(codigo + "\0");
        sintactico.IniciarAnalisis();

        
//        Token token = lexico.ObtenerToken();
//        while (!lexico.Finalizo()) {
//            listaTokens.add(token);
//            System.out.println(token.getNombre());
//            token = lexico.ObtenerToken();
//        }
        
//        CSV.GuardarTokens(listaTokens);
//
//        FileWriter archivoErrores = new FileWriter(new File("ListaErrores.csv"));
//        try ( CSVWriter writerErrores = new CSVWriter(archivoErrores)) {
//            List<String[]> listaErrores = lexico.getListaErrores();
//            writerErrores.writeNext(new String[]{"Linea", "Columna", "Tipo"});
//            writerErrores.writeAll(listaErrores);
//        }
    }

}
