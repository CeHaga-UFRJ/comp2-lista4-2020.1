package library.controller;

import library.comparators.*;
import library.entities.*;
import library.files.BaseReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Fachada para armazenar todos os dados do programa
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class DataManager {
    private static DataManager dataManager;
    private List<Student> students;
    private List<Book> books;
    private List<Author> authors;
    private List<Type> types;
    private List<Borrow> borrows;

    private DataManager(){

    }

    public static DataManager getDataManager() {
        if(dataManager == null) dataManager = new DataManager();
        return dataManager;
    }

    public List<Type> getTypes(){
        if(types != null) return new ArrayList<>(types);
        BaseReader br = BaseReader.getBaseReader();
        types = br.readType();
        return new ArrayList<>(types);
    }

    public List<Author> getAuthors(){
        if(authors != null) return new ArrayList<>(authors);
        BaseReader br = BaseReader.getBaseReader();
        authors = br.readAuthor();
        return new ArrayList<>(authors);
    }

    public List<Student> getStudents(){
        if(students != null) return new ArrayList<>(students);
        BaseReader br = BaseReader.getBaseReader();
        students = br.readStudent();
        return new ArrayList<>(students);
    }

    public List<Book> getBooks(){
        if(books != null) return new ArrayList<>(books);
        BaseReader br = BaseReader.getBaseReader();
        types = getTypes();
        authors = getAuthors();
        books = br.readBook();
        for(Book book : books){
            book.setAuthor(getAuthorById(book.getAuthorId()));
            book.setType(getTypeById(book.getTypeId()));
        }
        return new ArrayList<>(books);
    }

    public List<Borrow> getBorrows(){
        if(borrows != null) return borrows;
        BaseReader br = BaseReader.getBaseReader();
        students = getStudents();
        books = getBooks();
        borrows = br.readBorrow();
        for(Borrow borrow : borrows){
            borrow.setBook(getBookById(borrow.getBookId()));
            borrow.setStudent(getStudentById(borrow.getStudentId()));
            borrow.getStudent().addBorrowedBooks();
            borrow.getBook().addTimesBorrowed();
            borrow.getBook().getAuthor().addTimesBorrowed();
            borrow.getBook().getType().addTimesBorrowed();
        }
        return borrows;
    }

    public Student getStudentById(int i) {
        List<Student> students = getStudents();
        try{
            return students.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public Book getBookById(int i) {
        List<Book> books = getBooks();
        try{
            return books.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public Author getAuthorById(int i) {
        List<Author> authors = getAuthors();
        try{
            return authors.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public List<Author> getAuthorBySurname(String surname){
        List<Author> searchAuthors = new ArrayList<>();
        List<Author> authors = getAuthors();
        for(Author author : authors){
            if(author.getSurname().equals(surname)) searchAuthors.add(author);
        }
        return searchAuthors;
    }

    public Type getTypeById(int i) {
        List<Type> types = getTypes();
        try{
            return types.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public Type getTypeByName(String name){
        List<Type> types = getTypes();
        for(Type type : types){
            if(type.getName().equals(name)) return type;
        }
        return null;
    }

    public List<Borrow> lastNBorrows(int n){
        return StatsManager.getStatsManager().lastNBorrowsRank(n);
    }

    public List<Borrow> borrowsNDays(int n){
        return StatsManager.getStatsManager().borrowsNDaysRank(n);
    }

    public List<Student> mostNBorrowers(int n){
        return StatsManager.getStatsManager().mostNBorrowerStudentRank(n);
    }

    public List<Book> mostNBorrowedBooks(int n){
        return StatsManager.getStatsManager().mostNPopularBooksRank(n);
    }

    public List<Author> mostNPopularAuthors(int n){
        return StatsManager.getStatsManager().mostNPopularAuthorsRank(n);
    }

    public List<Type> mostNPopularTypes(int n){
        return StatsManager.getStatsManager().mostNPopularTypes(n);
    }

    public Student registerStudent(String name, String surname, LocalDate birthdate, char gender, String classCode, int points){
        students = getStudents();
        Student student = new Student(name, surname, birthdate, gender, classCode, points);
        students.add(student);
        return student;
    }

    public Book registerBook(String name, int pageCount, int point, Author author, Type type){
        books = getBooks();
        Book book = new Book(name, pageCount, point, author, type);
        books.add(book);
        return book;
    }

    public Author registerAuthor(String name, String surname){
        authors = getAuthors();
        Author author = new Author(name, surname);
        authors.add(author);
        return author;
    }

    public Type registerType(String name){
        types = getTypes();
        Type type = new Type(name);
        types.add(type);
        return type;
    }
}
