package library.view;

import library.controller.DataManager;
import library.entities.*;

import java.util.*;

public class LibraryView {
    public static final Scanner sc = new Scanner(System.in);

    public void startProgram(){
        while(true){
            System.out.println("Bem-vindo à Biblioteca Show, o que gostaria de fazer?");
            System.out.println("\t1 - Realizar um Cadastro");
            System.out.println("\t2 - Realizar uma Edição");
            System.out.println("\t3 - Realizar uma Remoção");
            System.out.println("\t4 - Realizar um Empréstimo");
            System.out.println("\t5 - Realizar uma Devolução");
            System.out.println("\t6 - Consultar uma Estatística");
            System.out.println("\t7 - Finalizar o Programa");
            String input = sc.nextLine();
            int esc = 0;
            while(esc == 0){
                try{
                    esc = Integer.parseInt(sc.nextLine());
                    if(esc < 1 || esc > 7){
                        System.out.println("Entre com um número de 1 a 7");
                        esc = 0;
                    }
                }catch (NumberFormatException e){
                    System.out.println("Entre com um número de 1 a 7");
                }
            }
            switch(esc){
                case 1:
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
                    break;
                case 7:
                    System.out.println("Obrigado por utilizar nossos serviços, tenha um bom dia! :)");
                    return;
            }
        }
    }
}
