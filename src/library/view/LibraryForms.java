package library.view;

import library.controller.DataManager;
import library.entities.*;
import library.exceptions.NumberOutOfRangeException;

import java.time.*;
import java.util.*;

public class LibraryForms {
    private final Scanner sc;

    public LibraryForms(Scanner sc){
        this.sc = sc;
    }


    public int readNumber(int min, int max){
        if(min > max) throw new NumberOutOfRangeException("Mínimo maior que máximo");
        int input;
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

    public int readNumber(int min){
        int input;
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

    public LocalDate readDate(){
        int birthDay;
        int birthMonth;
        int birthYear;
        System.out.println("Entre com o ano:");
        birthYear = readNumber(0);
        System.out.println("Entre com o mês:");
        birthMonth = readNumber(1, 12);
        System.out.println("Entre com o dia:");
        int[] maxDay = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if(birthYear % 400 == 0 || (birthYear % 4 == 0 && birthYear % 100 != 0)) maxDay[1]++;
        birthDay = readNumber(1, maxDay[birthMonth-1]);
        return LocalDate.of(birthYear, birthMonth, birthDay);
    }

    public LocalDateTime readDateTime(){
        LocalDate date = readDate();
        int hour;
        int minute;
        System.out.println("Entre com a hora:");
        hour = readNumber(0, 23);
        System.out.println("Entre com o minuto:");
        minute = readNumber(0, 59);
        return date.atTime(hour, minute);
    }

    public Author readAuthor(){
        DataManager dm = DataManager.getDataManager();
        String input;
        Author author;
        System.out.println("Entre com o id ou sobrenome do autor:");
        input = sc.nextLine();
        try{
            int id = Integer.parseInt(input);
            author = dm.getAuthorById(id);
        }catch (NumberFormatException e){
            List<Author> possibleAuthors = dm.getAuthorBySurname(input);
            System.out.println("Esses foram os autores encontrados com esse sobrenome. Digite seu id ou 0, caso não esteja na lista:");
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
        return author;
    }

    public Author readAuthorElseCreate(){
        Author author = readAuthor();
        if(author != null) return author;
        DataManager dm = DataManager.getDataManager();
        String authorName;
        String authorSurname;
        System.out.println("Entre com o nome do autor do livro:");
        authorName = sc.nextLine();
        System.out.println("Entre com o sobrenome do autor do livro:");
        authorSurname = sc.nextLine();
        return dm.registerAuthor(authorName, authorSurname);
    }

    public Type readType(){
        String input;
        DataManager dm = DataManager.getDataManager();
        Type type;
        System.out.println("Entre com o id ou nome do estilo:");
        input = sc.nextLine();
        try{
            int id = Integer.parseInt(input);
            type = dm.getTypeById(id);
        }catch (NumberFormatException e){
            type = dm.getTypeByName(input);
        }
        return type;
    }

    public Type readTypeElseCreate(){
        Type type = readType();
        if(type != null) return type;
        DataManager dm = DataManager.getDataManager();
        System.out.println("Estilo não encontrado, entre com o nome do estilo a ser criado:");
        String name = sc.nextLine();
        return dm.registerType(name);
    }

    public Student readStudent(){
        String input;
        DataManager dm = DataManager.getDataManager();
        Student student;
        System.out.println("Entre com o id ou sobrenome do estudante:");
        input = sc.nextLine();
        try{
            int id = Integer.parseInt(input);
            student = dm.getStudentById(id);
        }catch (NumberFormatException e){
            List<Student> possibleStudents = dm.getStudentBySurname(input);
            System.out.println("Esses foram os estudantes encontrados com esse sobrenome. Digite seu id ou 0, caso não esteja na lista:");
            List<Integer> possibleIds = new ArrayList<>();
            for(Student possibleStudent : possibleStudents){
                System.out.println(possibleStudent);
                possibleIds.add(possibleStudent.getStudentId());
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
            student = dm.getStudentById(id);
        }
        return student;
    }

    public Book readBook(){
        String input;
        DataManager dm = DataManager.getDataManager();
        Book book;
        System.out.println("Entre com o id ou nome do livro:");
        input = sc.nextLine();
        try{
            int id = Integer.parseInt(input);
            book = dm.getBookById(id);
        }catch (NumberFormatException e){
            book = dm.getBookByName(input);
        }
        return book;
    }

    public void registerStudent(){
        String name;
        String surname;
        LocalDate birthDate;
        char gender;
        String classCode;
        int points;
        String input;
        DataManager dm = DataManager.getDataManager();

        System.out.println("Entre com o nome do estudante:");
        name = sc.nextLine();
        System.out.println("Entre com o sobrenome do estudante:");
        surname = sc.nextLine();
        System.out.println("Entre com a data de nascimento do estudante.");
        birthDate = readDate();
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
        points = readNumber(0);
        dm.registerStudent(name, surname, birthDate, gender, classCode, points);
    }

    public void registerBook(){
        DataManager dm = DataManager.getDataManager();
        String name;
        int pageCount;
        int point;
        Author author;
        Type type;
        System.out.println("Entre com o nome do livro:");
        name = sc.nextLine();
        System.out.println("Entre com a quantidade de páginas:");
        pageCount = readNumber(0);
        System.out.println("Entre com a quantidade de pontos:");
        point = readNumber(0);
        author = readAuthorElseCreate();
        type = readTypeElseCreate();
        dm.registerBook(name, pageCount, point, author, type);
    }

    public Boolean borrow(){
        Student student = readStudent();
        if(student == null) return false;
        if(student.getActualBooks() == 2){
            System.out.println("O estudante já possui 2 livros");
            return false;
        }
        Book book = readBook();
        if(book == null) return false;
        if(book.isBorrowedBy(student)){
            System.out.println("Esse estudante está com uma cópia desse livro");
            return false;
        }
        if(book.getActualCopies() == 0){
            System.out.println("O livro não possui mais cópias disponíveis");
            return false;
        }
        if(student.getPoints() < book.getPoint()){
            System.out.println("O estudante não possui pontos o suficiente");
            return false;
        }
        DataManager dm = DataManager.getDataManager();
        System.out.println("Entre com a data de empréstimo:");
        LocalDateTime takenDate = readDateTime();
        System.out.println("Já houve a devolução do livro? (S ou N)");
        String choice = sc.nextLine();
        if(choice.toLowerCase().equals("s")){
            LocalDateTime broughtDate = readDateTime();
            dm.registerBorrow(student, book, takenDate, broughtDate);
        }else{
            dm.registerBorrow(student, book, takenDate, null);
        }
        return true;
    }

    public Boolean brought(){
        System.out.println("Entre com o id do empréstimo ou 0 para cancelar:");
        int id = readNumber(0);
        if(id == 0) return null;
        DataManager dm = DataManager.getDataManager();
        Borrow borrow = dm.getBorrowById(id);
        if(borrow == null) return false;
        if(borrow.getBroughtDate() != null){
            System.out.println("O livro já foi devolvido");
            return false;
        }
        System.out.println("Entre com a data que ocorreu a devolução.");
        LocalDateTime date = readDateTime();
        dm.setBorrowReturn(borrow, date);
        return true;
    }
}
