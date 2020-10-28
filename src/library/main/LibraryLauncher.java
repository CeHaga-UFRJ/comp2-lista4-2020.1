package library.main;

import library.view.LibraryView;

/**
 * Classe principal do programa
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class LibraryLauncher {
    public static void main(String[] args) {
        LibraryView lv = new LibraryView();
        lv.startProgram();
    }
}
