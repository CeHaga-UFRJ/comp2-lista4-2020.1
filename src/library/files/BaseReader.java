package library.files;

import library.entities.*;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BaseReader {
    private static BaseReader baseReader;

    private BaseReader(){

    }

    public static BaseReader getBaseReader(){
        if(baseReader == null) baseReader = new BaseReader();
        return baseReader;
    }

    public List<Type> readType() {
        List<Type> types = new ArrayList<>();
        File file = new File("resources/data/types.ser");
        if(file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/data/types.ser"))) {
                types = (List<Type>) ois.readObject();
                return types;
            }catch (ClassNotFoundException e){
                System.err.println("Classe não encontrada");
                e.printStackTrace();
            }catch (IOException e){
                System.err.println("Erro ao abrir arquivo types.ser");
                e.printStackTrace();
            }
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/types.tsv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split("\t");
                int typeId = Integer.parseInt(data[0]);
                String name = data[1];
                types.add(new Type(typeId, name));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo types.tsv");
            e.printStackTrace();
            return null;
        }
        return types;
    }

    public List<Author> readAuthor(){
        List<Author> authors = new ArrayList<>();
        File file = new File("resources/data/authors.ser");
        if(file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/data/authors.ser"))) {
                authors = (List<Author>) ois.readObject();
                return authors;
            }catch (ClassNotFoundException e){
                System.err.println("Classe não encontrada");
                e.printStackTrace();
            }catch (IOException e){
                System.err.println("Erro ao abrir arquivo authors.ser");
                e.printStackTrace();
            }
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/authorsFull.tsv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split("\t");
                int authorId = Integer.parseInt(data[0]);
                String name = data[1];
                String surname = data[2];
                authors.add(new Author(authorId, name, surname));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo authorsFull.tsv");
            e.printStackTrace();
            return null;
        }
        return authors;
    }

    public List<Student> readStudent(){
        List<Student> students = new ArrayList<>();
        File file = new File("resources/data/students.ser");
        if(file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/data/students.ser"))) {
                students = (List<Student>) ois.readObject();
                return students;
            }catch (ClassNotFoundException e){
                System.err.println("Classe não encontrada");
                e.printStackTrace();
            }catch (IOException e){
                System.err.println("Erro ao abrir arquivo students.ser");
                e.printStackTrace();
            }
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/students.tsv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split("\t");
                int studentId = Integer.parseInt(data[0]);
                String name = data[1];
                String surname = data[2];
                LocalDate birthdate = LocalDate.parse(data[3]);
                char gender = data[4].charAt(0);
                String classCode = data[5];
                int points = Integer.parseInt(data[6]);
                students.add(new Student(studentId, name, surname, birthdate, gender, classCode, points));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo students.tsv");
            e.printStackTrace();
            return null;
        }
        return students;
    }

    public List<Book> readBook(){
        List<Book> books = new ArrayList<>();
        File file = new File("resources/data/books.ser");
        if(file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/data/books.ser"))) {
                books = (List<Book>) ois.readObject();
                return books;
            }catch (ClassNotFoundException e){
                System.err.println("Classe não encontrada");
                e.printStackTrace();
            }catch (IOException e){
                System.err.println("Erro ao abrir arquivo books.ser");
                e.printStackTrace();
            }
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/books.tsv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split("\t");
                int bookId = Integer.parseInt(data[0]);
                String name = data[1];
                int pageCount = Integer.parseInt(data[2]);
                int points = Integer.parseInt(data[3]);
                int authorId = Integer.parseInt(data[4]);
                int typeId = Integer.parseInt(data[5]);
                books.add(new Book(bookId, name, pageCount, points, authorId, typeId));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo books.tsv");
            e.printStackTrace();
            return null;
        }
        return books;
    }

    public List<Borrow> readBorrow(){
        List<Borrow> borrows = new ArrayList<>();
        File file = new File("resources/data/borrows.ser");
        if(file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/data/borrows.ser"))) {
                borrows = (List<Borrow>) ois.readObject();
                return borrows;
            }catch (ClassNotFoundException e){
                System.err.println("Classe não encontrada");
                e.printStackTrace();
            }catch (IOException e){
                System.err.println("Erro ao abrir arquivo borrows.ser");
                e.printStackTrace();
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/borrows.tsv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split("\t");
                int borrowId = Integer.parseInt(data[0]);
                int studentId = Integer.parseInt(data[1]);
                int bookId = Integer.parseInt(data[2]);
                LocalDateTime takenDate = LocalDateTime.parse(data[3],formatter);
                LocalDateTime broughtDate = LocalDateTime.parse(data[4],formatter);
                borrows.add(new Borrow(borrowId, studentId, bookId, takenDate, broughtDate));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo borrows.tsv");
            e.printStackTrace();
            return null;
        }
        return borrows;
    }
}
