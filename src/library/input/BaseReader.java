package library.input;

import library.entities.Author;
import library.entities.Book;
import library.entities.Student;
import library.entities.Type;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BaseReader {
    public List<Type> readType(){
        List<Type> types = new ArrayList<Type>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/types.csv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String formattedLine = line.substring(1,line.length()-1);
                String[] data = formattedLine.split("\",\"");
                int typeId = Integer.parseInt(data[0]);
                String name = data[1];
                types.add(new Type(typeId, name));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo types.csv");
            e.printStackTrace();
            return null;
        }
        return types;
    }

    public List<Author> readAuthor(){
        List<Author> authors = new ArrayList<Author>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/authorsFull.csv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String formattedLine = line.substring(1,line.length()-1);
                String[] data = formattedLine.split("\",\"");
                int authorId = Integer.parseInt(data[0]);
                String name = data[1];
                String surname = data[2];
                authors.add(new Author(authorId, name, surname));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo authorsFull.csv");
            e.printStackTrace();
            return null;
        }
        return authors;
    }

    public List<Student> readStudent(){
        List<Student> students = new ArrayList<Student>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/students.csv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String formattedLine = line.substring(1,line.length()-1);
                String[] data = formattedLine.split("\",\"");
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
            System.err.println("Erro ao abrir arquivo students.csv");
            e.printStackTrace();
            return null;
        }
        return students;
    }

    public List<Book> readBook(){
        List<Book> books = new ArrayList<Book>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/base/books.csv")))){
            br.readLine(); //Cabecalho
            String line;
            while((line = br.readLine()) != null){
                String formattedLine = line.substring(1,line.length()-1);
                String[] data = formattedLine.split("\",\"");
                int bookId = Integer.parseInt(data[0]);
                String name = data[1];
                int pageCount = Integer.parseInt(data[2]);
                int points = Integer.parseInt(data[3]);
                int authorId = Integer.parseInt(data[4]);
                int typeId = Integer.parseInt(data[5]);
                books.add(new Book(bookId, name, pageCount, points, authorId, typeId));
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir arquivo books.csv");
            e.printStackTrace();
            return null;
        }
        return books;
    }
}
