package library.main;

import library.controller.DataManager;
import library.entities.Author;

public class LibraryLauncher {
    public static void main(String[] args) {
        DataManager dm = DataManager.getDataManager();
        System.out.println(dm.getAuthorByIndex(0).getName());
    }
}
