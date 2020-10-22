package library.controller;

import library.comparators.*;
import library.entities.*;
import library.files.BaseReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<Borrow> borrows;

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
        for(Borrow borrow : borrows){
            borrow.setBook(getBookById(borrow.getBookId()));
            borrow.setStudent(getStudentById(borrow.getStudentId()));
            borrow.getStudent().addBorrowedBooks();
            borrow.getBook().addTimesBorrowed();
            borrow.getBook().getAuthor().addTimesBorrowed();
            borrow.getBook().getType().addTimesBorrowed();
        }

        types.sort(new MostPopularTypesComparator());
        authors.sort(new MostPopularAuthorsComparator());
        students.sort(new MostBorrowerStudentsComparator());
        books.sort(new MostBorrowedBooksComparator());
        borrows.sort(new MostRecentBorrowComparator());
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
        int min = n < borrows.size() ? n : borrows.size();
        List<Borrow> rank = new ArrayList<>(borrows.subList(0,min));
        rank.sort(new MostRecentBorrowComparator());
        return rank;
    }

    public List<Borrow> borrowsLastNDays(int n){
        List<Borrow> rank = new ArrayList<>();
        LocalDateTime day = LocalDateTime.now().minusDays(n);
        for(Borrow borrow : borrows){
            if(borrow.getTakenDate().isBefore(day)) break;
            rank.add(borrow);
        }
        return rank;
    }

    public List<Student> mostNBorrowers(int n){
        int min = n < students.size() ? n : students.size();
        List<Student> rank = new ArrayList<>(students.subList(0, min));
        rank.sort(new MostBorrowerStudentsComparator());
        return rank;
    }

    public List<Book> mostNBorrowedBooks(int n){
        int min = n < books.size() ? n : books.size();
        List<Book> rank = new ArrayList<>(books.subList(0, min));
        rank.sort(new MostBorrowedBooksComparator());
        return rank;
    }

    public List<Author> mostNPopularAuthors(int n){
        int min = n < authors.size() ? n : authors.size();
        List<Author> rank = new ArrayList<>(authors.subList(0, min));
        rank.sort(new MostPopularAuthorsComparator());
        return rank;
    }

    public List<Type> mostNPopularTypes(int n){
        int min = n < types.size() ? n : types.size();
        List<Type> rank = new ArrayList<>(types.subList(0, min));
        rank.sort(new MostPopularTypesComparator());
        return rank;
    }

}
