package library.files;

import library.interfaces.LibraryListeners;
import library.interfaces.Notifiable;

import java.io.*;
import java.util.*;

public class BaseWriter{
    public <K,V> void writeMap(HashMap<K,V> list, String filename){
        File file = new File("resources/data/"+filename);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/data/"+filename, false))){
            oos.writeObject(list);
        }catch (IOException e){
            System.err.println("Erro ao criar arquivo "+filename);
            e.printStackTrace();
        }
    }
}
