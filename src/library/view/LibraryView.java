package library.view;

import library.controller.DataManager;
import library.entities.*;
import library.exceptions.NumberOutOfRangeException;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.*;

public class LibraryView {
    public static final Scanner sc = new Scanner(System.in);

    public void startProgram(){
        while(true){
            DataManager dm = DataManager.getDataManager();
            Student student = dm.getStudentById(506);
            if(student != null) System.out.println(student);
            Author author = dm.getAuthorById(235);
            if(author != null) System.out.println(author);
            Book book = dm.getBookById(169);
            if(book != null) System.out.println(book);
            Type type = dm.getTypeById(19);
            if(type != null) System.out.println(type);
            printMenu();
            int input = readNumber(1,7);
            switch(input){
                case 1:
                    printRegisterMenu();
                    register(readNumber(1,2));
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
        switch (esc){
            case 1:
                registerStudent();
                break;
            case 2:
                registerBook();
                break;
        }
    }

    private void registerStudent(){
        String name;
        String surname;
        int birthDay;
        int birthMonth;
        int birthYear;
        char gender;
        String classCode;
        int points;
        String input;
        DataManager dm = DataManager.getDataManager();

        System.out.println("Entre com o nome do estudante:");
        name = sc.nextLine();
        System.out.println("Entre com o sobrenome do estudante:");
        surname = sc.nextLine();
        System.out.println("Entre com o ano de nascimento do estudante:");
        while (true){
            try{
                input = sc.nextLine();
                birthYear = Integer.parseInt(input);
                if(birthYear <= 0){
                    System.out.println("Entre com um número positivo");
                    continue;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Entre com um número");
            }
        }
        System.out.println("Entre com o mês de nascimento do estudante:");
        while (true){
            try{
                input = sc.nextLine();
                birthMonth = Integer.parseInt(input);
                if(birthMonth < 1 || birthMonth > 12){
                    System.out.println("Entre com um número entre 1 e 12");
                    continue;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Entre com um número");
            }
        }
        System.out.println("Entre com o dia de nascimento do estudante:");
        int minDay = 1;
        int maxDay[] = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if(birthYear % 400 == 0 || (birthYear % 4 == 0 && birthYear % 100 != 0)) maxDay[1]++;
        while (true){
            try{
                input = sc.nextLine();
                birthDay = Integer.parseInt(input);
                if(birthDay < minDay || birthDay > maxDay[birthMonth-1]){
                    System.out.println("Entre com um dia entre "+minDay+" e "+maxDay[birthMonth-1]);
                    continue;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Entre com um número");
            }
        }
        System.out.println("Entre com o gênero do estudante (M ou F):");
        while (true){
            input = sc.nextLine();
            if(!input.equals("M") && !input.equals("F")){
                System.out.println("Entre com M ou F");
                continue;
            }
            break;
        }
        gender = input.charAt(0);
        System.out.println("Entre com o código de classe:");
        classCode = sc.nextLine();
        System.out.println("Entre com a quantidade de pontos do estudante:");
        while (true){
            try{
                input = sc.nextLine();
                points = Integer.parseInt(input);
                break;
            }catch (NumberFormatException e){
                System.out.println("Entre com um número");
            }
        }
        dm.registerStudent(name, surname, LocalDate.of(birthYear, birthMonth, birthDay), gender, classCode, points);
    }

    private void registerBook(){
        DataManager dm = DataManager.getDataManager();
        String name;
        int pageCount;
        int point;
        Author author = null;
        Type type = null;
        String input;
        System.out.println("Entre com o nome do livro:");
        name = sc.nextLine();
        System.out.println("Entre com a quantidade de páginas:");
        while (true){
            try{
                input = sc.nextLine();
                pageCount = Integer.parseInt(input);
                if(pageCount > 0) break;
                System.out.println("Entre com um número positivo");
            }catch (NumberFormatException e){
                System.out.println("Entre com um número");
            }
        }
        System.out.println("Entre com a quantidade de pontos:");
        while (true){
            try{
                input = sc.nextLine();
                point = Integer.parseInt(input);
                break;
            }catch (NumberFormatException e){
                System.out.println("Entre com um número");
            }
        }
        System.out.println("Entre com o id do autor, ou seu sobrenome:");
        input = sc.nextLine();
        try{
            int id = Integer.parseInt(input);
            author = dm.getAuthorById(id);
        }catch (NumberFormatException e){
            List<Author> possibleAuthors = dm.getAuthorBySurname(input);
            System.out.println("Esses foram os autores encontrados com esse sobrenome. Digite seu id ou 0, caso queira cadastrar um novo:");
            List<Integer> possibleIds = new ArrayList<>();
            for(Author possibleAuthor : possibleAuthors){
                System.out.println(possibleAuthor);
                possibleIds.add(possibleAuthor.getAuthorId());
            }
            int id;
            while (true){
                try{
                    input = sc.nextLine();
                    id = Integer.parseInt(input);
                    if(id == 0 || possibleIds.contains(id)) break;
                    System.out.println("Entre com um id da lista ou 0:");
                }catch (NumberFormatException err){
                    System.out.println("Entre com um número");
                }
            }
            author = dm.getAuthorById(id);
        }
        if(author == null){
            String authorName;
            String authorSurname;
            System.out.println("Entre com o nome do autor do livro:");
            authorName = sc.nextLine();
            System.out.println("Entre com o sobrenome do autor do livro:");
            authorSurname = sc.nextLine();
            author = dm.registerAuthor(authorName, authorSurname);
        }
        System.out.println("Entre com o id do estilo, ou seu nome:");
        input = sc.nextLine();
        try{
            int id = Integer.parseInt(input);
            type = dm.getTypeById(id);
            if(type == null){
                System.out.println("Id nâo encontrado. Entre com o nome do estilo a ser criado:");
                String typeName = sc.nextLine();
                type = dm.registerType(typeName);
            }
        }catch (NumberFormatException e){
            while (true) {
                type = dm.getTypeByName(input);
                if (type == null) {
                    System.out.println("Estilo não encontrado. Gostaria de criar um novo com o nome inserido (S ou N)?");
                    String choice = sc.nextLine();
                    if(!choice.toLowerCase().equals("s")){
                        System.out.println("Entre com o nome a ser procurado novamente:");
                        input = sc.nextLine();
                        continue;
                    }
                    type = dm.registerType(input);
                }
                break;
            }
        }
        dm.registerBook(name, pageCount, point, author, type);
    }
}
