/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Meme
 */
public class CSV {


    public static void GuardarTokens(List<Token> listaTokens) throws IOException {
        FileWriter archivoTokens = new FileWriter(new File("ListaTokens.csv"));
        try ( CSVWriter writer = new CSVWriter(archivoTokens)) {
            for (Token token : listaTokens) {
                writer.writeNext(new String[]{token.getNombre(), token.getAtributo()}, false);
            }
        }
    }

    public static List<Token> LeerTokens() throws IOException {
        List<Token> listaTokens = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("ListaTokens.csv"));
            String linea = br.readLine();
            while (null != linea) {
                String[] tokens = linea.split(",");
                //System.out.println(Arrays.toString(tokens));
                listaTokens.add(new Token(tokens[0], tokens[1]));
                linea = br.readLine();
            }
        } catch (IOException e) {

        } finally {
            if (null != br) {
                br.close();
            }
        }
        return listaTokens;
    }
}
