package library.controller;

import library.interfaces.LibraryListeners;
import library.interfaces.Notifiable;

import java.util.*;

/**
 * Gerenciador de eventos para notificação
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class EventManager {
    private static HashMap<String, List<LibraryListeners>> listeners = new HashMap<>();

    /**
     * Inscreve uma instância de LibraryListeners a um evento específico
     * @param type Evento de interesse
     * @param listener Instância a receber notificações
     * @return true caso tenha sido inscrito, false caso contrário
     */
    public static boolean subscribe(String type, LibraryListeners listener){
        if(!listeners.containsKey(type)) listeners.put(type, new ArrayList<>());
        return listeners.get(type).add(listener);
    }

    /**
     * Desinscreve uma instância de LibraryListeners a um evento específico
     * @param type Evento a ser removido
     * @param listener Instância a parar de receber notificações
     * @return true caso tenha sido removido, false caso contrário
     */
    public static boolean unsubscribe(String type, LibraryListeners listener){
        return listeners.get(type).remove(listener);
    }

    /**
     * Notifica os inscritos de uma notificação
     * @param type Evento ocorrido
     * @param data Dado a ser notificado
     */
    public static void notify(String type, Notifiable data){
        if(!listeners.containsKey(type)) return;
        for(LibraryListeners listener : listeners.get(type)){
            listener.update(data);
        }
    }
}
