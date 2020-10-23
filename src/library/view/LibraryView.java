package library.view;

import library.controller.DataManager;
import library.entities.*;
import library.exceptions.NumberOutOfRangeException;

import java.util.*;

public class LibraryView {
    public static final Scanner sc = new Scanner(System.in);

    public void startProgram(){
        while(true){
            printMenu();
            int input = readNumber(1,7);
            switch(input){
                case 1:
                    printRegisterMenu();
                    register(readNumber(1,4));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    printStatsMenu();
                    stats(readNumber(1,7));
                    break;
                case 7:
                    System.out.println("Obrigado por utilizar nossos serviços, tenha um bom dia! :)");
                    return;
            }
        }
    }

    private int readNumber(int min, int max){
        if(min > max) throw new NumberOutOfRangeException("Mínimo maior que máximo");
        int input = min - 1;
        while(true){
            try{
                input = Integer.parseInt(sc.nextLine());
                if(input < min || input > max){
                    System.out.println("Entre com um número de "+min+" a "+max);
                    continue;
                }
            }catch (NumberFormatException e){
                System.out.println("Entre com um número de "+min+" a "+max);
                continue;
            }
            return input;
        }
    }

    private int readNumber(int min){
        int input = min - 1;
        while(true){
            try{
                input = Integer.parseInt(sc.nextLine());
                if(input < min){
                    System.out.println("Entre com um número maior que "+min);
                    continue;
                }
            }catch (NumberFormatException e){
                System.out.println("Entre com um número maior que "+min);
                continue;
            }
            return input;
        }
    }

    private void printMenu() {
        System.out.println("Bem-vindo à Biblioteca Show, o que gostaria de fazer?");
        System.out.println("\t1 - Realizar um cadastro");
        System.out.println("\t2 - Realizar uma edição");
        System.out.println("\t3 - Realizar uma remoção");
        System.out.println("\t4 - Realizar um empréstimo");
        System.out.println("\t5 - Realizar uma devolução");
        System.out.println("\t6 - Consultar uma estatística");
        System.out.println("\t7 - Finalizar o programa");
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
        int n = readNumber(1);
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
            case 7:
                return;
        }
        int count = 1;
        for(Object entity : rank){
            System.out.println(count++ + ": "+entity);
        }
        System.out.println("");
    }

    private void printRegisterMenu(){
        System.out.println("O que deseja cadastrar?");
        System.out.println("1 - Estudante");
        System.out.println("2 - Livro");
    }

    private void register(int esc){
        DataManager dm = DataManager.getDataManager();
        switch (esc){
            case 1:
                
        }
    }
}
