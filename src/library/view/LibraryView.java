package library.view;

import library.controller.DataManager;

import java.util.*;

public class LibraryView {
    public static final Scanner sc = new Scanner(System.in);
    private final LibraryForms lf;

    public LibraryView(){
        lf = new LibraryForms(sc);
    }

    public void startProgram(){
        while(true){
            printMenu();
            int input = lf.readNumber(1,5);
            switch(input){
                case 1:
                    printRegisterMenu();
                    register(lf.readNumber(1,2));
                    break;
                case 2:
                    borrow();
                    break;
                case 3:
                    brought();
                    break;
                case 4:
                    printStatsMenu();
                    stats(lf.readNumber(1,7));
                    break;
                case 5:
                    System.out.println("Obrigado por utilizar nossos serviços, tenha um bom dia! :)");
                    return;
            }
        }
    }

    private void printMenu() {
        System.out.println("Bem-vindo à Biblioteca Show, o que gostaria de fazer?");
        System.out.println("\t1 - Realizar um cadastro");
        System.out.println("\t2 - Realizar um empréstimo");
        System.out.println("\t3 - Realizar uma devolução");
        System.out.println("\t4 - Consultar uma estatística");
        System.out.println("\t5 - Finalizar o programa");
    }

    private void printRegisterMenu(){
        System.out.println("O que deseja cadastrar?");
        System.out.println("1 - Estudante");
        System.out.println("2 - Livro");
    }

    private void register(int input){
        switch (input){
            case 1:
                lf.registerStudent();
                break;
            case 2:
                lf.registerBook();
                break;
        }
    }

    private void borrow(){
        Boolean suc = lf.borrow();
        if(suc == null) System.out.println("Empréstimo cancelado");
        else if(suc) System.out.println("Empréstimo realizado com sucesso");
        else System.out.println("Erro ao realizar empréstimo");
    }

    private void brought(){
        Boolean suc = lf.brought();
        if(suc == null) System.out.println("Devolução cancelada");
        else if(suc) System.out.println("Devolução realizada com sucesso");
        else System.out.println("Erro ao realizar devolução");
    }

    private void printStatsMenu(){
        System.out.println("Qual estatística gostaria de consultar?");
        System.out.println("\t1 - Últimos empréstimos");
        System.out.println("\t2 - Empréstimos por duração");
        System.out.println("\t3 - Estudantes que mais pegam emprestado");
        System.out.println("\t4 - Livros mais emprestados");
        System.out.println("\t5 - Autores mais populares");
        System.out.println("\t6 - Estilos mais populares");
        System.out.println("\t7 - Voltar ao menu");
    }

    private void stats(int input){
        if(input == 7) return;
        DataManager dm = DataManager.getDataManager();
        if(input != 2) System.out.println("Quantos registros deseja visualizar?");
        else System.out.println("Quantos dias de empréstimo no mínimo?");
        int n = lf.readNumber(1);
        List<?> rank = null;
        switch (input){
            case 1:
                rank = dm.lastNBorrows(n);
                break;
            case 2:
                rank = dm.borrowsNDays(n);
                break;
            case 3:
                rank = dm.mostNBorrowers(n);
                break;
            case 4:
                rank = dm.mostNBorrowedBooks(n);
                break;
            case 5:
                rank = dm.mostNPopularAuthors(n);
                break;
            case 6:
                rank = dm.mostNPopularTypes(n);
                break;
        }
        int count = 1;
        for(Object entity : rank){
            System.out.println(count++ + ": "+entity);
        }
        System.out.println("");
    }

}
