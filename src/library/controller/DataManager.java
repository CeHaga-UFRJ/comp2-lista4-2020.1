package library.controller;

import library.comparators.MostRecentBorrowComparator;
import library.entities.*;
import library.files.BaseReader;

import java.util.HashMap;
import java.util.List;

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
    private HashMap<Integer, List<Borrow>> borrows;

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
        borrows = br.readBorrow();
        for(List<Borrow> borrowsList : borrows.values()){
            borrowsList.sort(new MostRecentBorrowComparator());
            for(Borrow borrow : borrowsList){
                borrow.setBook(getBookById(borrow.getBookId()));
                borrow.setStudent(getStudentById(borrow.getStudentId()));
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

    public List<Borrow> lastNBorrows(int n){
        
    }
}
