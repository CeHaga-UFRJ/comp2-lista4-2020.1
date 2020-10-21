package library.controller;

import library.entities.Author;
import library.entities.Book;
import library.entities.Student;
import library.entities.Type;
import library.input.BaseReader;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    private DataManager(){
        BaseReader br = new BaseReader();
        types = br.readType();
        authors = br.readAuthor();
        students = br.readStudent();
        books = br.readBook();
        for(Book book : books){
            try {
                book.setAuthor(getAuthorById(book.getAuthorId()));
                book.setType(getTypeById(book.getTypeId()));
            }catch (Exception e){
                System.err.println("Erro no livro "+book.getName());
                e.printStackTrace();
            }
        }
    }

    public static DataManager getDataManager() {
        if(dataManager == null) dataManager = new DataManager();
        return dataManager;
    }

    public Student getStudentById(int i) {
        try{
            return students.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public Book getBookById(int i) {
        try{
            return books.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public Author getAuthorById(int i) {
        try{
            return authors.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public Type getTypeById(int i) {
        try{
            return types.get(i-1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }
}
