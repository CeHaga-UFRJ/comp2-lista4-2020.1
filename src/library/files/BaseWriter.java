package library.files;

import java.io.*;
import java.util.*;

/**
 * Utilidades para escrita de arquivos
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class BaseWriter{
    /**
     * Salva um mapa em um arquivo
     * @param map Mapa a ser salvo
     * @param filename Nome do arquivo
     */
    public <K,V> void writeMap(HashMap<K,V> map, String filename){
        File file = new File("resources/data/"+filename);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/data/"+filename, false))){
            oos.writeObject(map);
        }catch (IOException e){
            System.err.println("Erro ao criar arquivo "+filename);
            e.printStackTrace();
        }
    }
}
