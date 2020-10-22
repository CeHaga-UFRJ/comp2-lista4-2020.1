package library.files;

import java.io.*;
import java.util.*;

public class BaseWriter {
    public <T> void writeList(List<T> list, String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/data/"+filename))){
            oos.writeObject(list);
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo "+filename);
        }
    }
}
