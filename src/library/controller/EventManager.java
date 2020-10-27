package library.controller;

import library.interfaces.LibraryListeners;
import library.interfaces.Notifiable;

import java.util.*;

public class EventManager {
    private static HashMap<String, List<LibraryListeners>> listeners = new HashMap<>();

    public static boolean subscribe(String type, LibraryListeners listener){
        if(!listeners.containsKey(type)) listeners.put(type, new ArrayList<>());
        return listeners.get(type).add(listener);
    }

    public static boolean unsubscribe(String type, LibraryListeners listener){
        return listeners.get(type).remove(listener);
    }

    public static void notify(String type, Notifiable data){
        if(!listeners.containsKey(type)) return;
        for(LibraryListeners listener : listeners.get(type)){
            listener.update(data);
        }
    }
}
