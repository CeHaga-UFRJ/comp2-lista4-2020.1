package library.controller;

import library.entities.*;
import library.files.BaseReader;
import library.files.BaseWriter;
import library.interfaces.Notifiable;

import java.time.*;
import java.util.*;

/**
 * Fachada para armazenar todos os dados do programa
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class DataManager {
    private static DataManager dataManager;
    private HashMap<Integer, Student> students;
    private HashMap<Integer, Book> books;
    private HashMap<Integer, Author> authors;
    private HashMap<Integer, Type> types;
    private HashMap<Integer, Borrow> borrows;
    private BaseWriter bw;

    private DataManager(){
        StatsManager sm = StatsManager.getStatsManager();
        EventManager.subscribe("book",sm);
        EventManager.subscribe("borrow",sm);
        EventManager.subscribe("type",sm);
        EventManager.subscribe("student",sm);
        EventManager.subscribe("author",sm);
        bw = new BaseWriter();
    }

    /**
     * Cria caso não exista e retorna a instância de DataManager
     * @return Instância ativa de DataManager
     */
    public static DataManager getDataManager() {
        if(dataManager == null) dataManager = new DataManager();
        return dataManager;
    }

    private void readAll(){
        types = getTypes();
        authors = getAuthors();
        students = getStudents();
        books = getBooks();
        borrows = getBorrows();
    }

    /**
     * Retorna o mapa de estilos. Caso não exista, lê do arquivo
     * @return Mapa de estilos chaveados pelo id
     */
    public HashMap<Integer, Type> getTypes(){
        if(types != null) return new HashMap<>(types);
        BaseReader br = BaseReader.getBaseReader();
        types = br.readType();
        readAll(); //Para definir o número de empréstimos
        return new HashMap<>(types);
    }

    /**
     * Retorna o mapa de autores. Caso não exista, lê do arquivo
     * @return Mapa de autores chaveados pelo id
     */
    public HashMap<Integer, Author> getAuthors(){
        if(authors != null) return new HashMap<>(authors);
        BaseReader br = BaseReader.getBaseReader();
        authors = br.readAuthor();
        readAll(); //Para definir o número de empréstimos
        return new HashMap<>(authors);
    }

    /**
     * Retorna o mapa de estudantes. Caso não exista, lê do arquivo
     * @return Mapa de estudantes chaveados pelo id
     */
    public HashMap<Integer, Student> getStudents(){
        if(students != null) return new HashMap<>(students);
        BaseReader br = BaseReader.getBaseReader();
        students = br.readStudent();
        readAll(); //Para definir o número de empréstimos
        return new HashMap<>(students);
    }

    /**
     * Retorna o mapa de livros. Caso não exista, lê do arquivo
     * @return Mapa de livros chaveados pelo id
     */
    public HashMap<Integer, Book> getBooks(){
        if(books != null) return new HashMap<>(books);
        BaseReader br = BaseReader.getBaseReader();
        books = br.readBook();
        types = getTypes();
        authors = getAuthors();
        for(Book book : books.values()){
            book.setAuthor(getAuthorById(book.getAuthorId()));
            book.setType(getTypeById(book.getTypeId()));
        }
        readAll(); //Para definir o número de empréstimos
        return new HashMap<>(books);
    }

    /**
     * Retorna o mapa de empréstimos. Caso não exista, lê do arquivo
     * @return Mapa de empréstimos chaveados pelo id
     */
    public HashMap<Integer, Borrow> getBorrows(){
        if(borrows != null) return borrows;
        BaseReader br = BaseReader.getBaseReader();
        borrows = br.readBorrow();
        books = getBooks();
        students = getStudents();
        for(Borrow borrow : borrows.values()){
            borrow.setBook(getBookById(borrow.getBookId()));
            borrow.setStudent(getStudentById(borrow.getStudentId()));
            borrow.getStudent().addBorrowedBooks();
            borrow.getBook().addTimesBorrowed();
            borrow.getBook().getAuthor().addTimesBorrowed();
            borrow.getBook().getType().addTimesBorrowed();
            if(borrow.getBroughtDate() == null){
                borrow.getStudent().addActualBooks();
            }
        }
        readAll();
        return borrows;
    }

    /**
     * Procura um empréstimo pelo id
     * @param i id do empréstimo
     * @return Empréstimo com o id fornecido
     */
    public Borrow getBorrowById(int i){
        HashMap<Integer, Borrow> borrows = getBorrows();
        try{
            return borrows.get(i);
        }catch (IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
    }

    /**
     * Procura um estudante pelo id
     * @param i id do estudante
     * @return Estudante com o id fornecido
     */
    public Student getStudentById(int i) {
        HashMap<Integer, Student> students = getStudents();
        try{
            return students.get(i);
        }catch (IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
    }

    /**
     * Procura estudantes que possuem o sobrenome fornecido
     * @param surname Sobrenome do estudante
     * @return Lista de estudantes com esse sobrenome
     */
    public List<Student> getStudentBySurname(String surname){
        List<Student> searchStudents = new ArrayList<>();
        Collection<Student> students = getStudents().values();
        for(Student student : students){
            if(student.getSurname().equals(surname)) searchStudents.add(student);
        }
        return searchStudents;
    }

    /**
     * Procura um livro pelo id
     * @param i id do livro
     * @return Livro com o id fornecido
     */
    public Book getBookById(int i) {
        HashMap<Integer, Book> books = getBooks();
        try{
            return books.get(i);
        }catch (IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
    }

    /**
     * Procura um livro com o nome fornecido
     * @param name Nome do livro
     * @return Instância do livro com esse nome
     */
    public Book getBookByName(String name){
        Collection<Book> books = getBooks().values();
        for(Book book : books){
            if(book.getName().equals(name)) return book;
        }
        return null;
    }

    /**
     * Procura um autor pelo id
     * @param i id do autor
     * @return Autor com o id fornecido
     */
    public Author getAuthorById(int i) {
        HashMap<Integer, Author> authors = getAuthors();
        try{
            return authors.get(i);
        }catch (IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
    }

    /**
     * Procura autores que possuem o sobrenome fornecido
     * @param surname Sobrenome do autor
     * @return Lista de autores com esse sobrenome
     */
    public List<Author> getAuthorBySurname(String surname){
        List<Author> searchAuthors = new ArrayList<>();
        Collection<Author> authors = getAuthors().values();
        for(Author author : authors){
            if(author.getSurname().equals(surname)) searchAuthors.add(author);
        }
        return searchAuthors;
    }

    /**
     * Procura um estilo pelo id
     * @param i id do estilo
     * @return Estilo com o id fornecido
     */
    public Type getTypeById(int i) {
        HashMap<Integer, Type> types = getTypes();
        try{
            return types.get(i);
        }catch (IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
    }

    /**
     * Procura um estilo com o nome fornecido
     * @param name Nome do estilo
     * @return Instância do estilo com esse nome
     */
    public Type getTypeByName(String name){
        Collection<Type> types = getTypes().values();
        for(Type type : types){
            if(type.getName().equals(name)) return type;
        }
        return null;
    }

    /**
     * Calcula o ranking dos últimos N empréstimos
     * @param n Número de registros
     * @return Lista dos N empréstimos ordenados
     */
    public List<Borrow> lastNBorrows(int n){
        return StatsManager.getStatsManager().lastNBorrowsRank(n);
    }

    /**
     * Calcula o ranking dos empréstimo que duraram pelo menos N dias
     * @param n Número de dias
     * @return Lista dos empréstimos ordenados
     */
    public List<Borrow> borrowsNDays(int n){
        return StatsManager.getStatsManager().borrowsNDaysRank(n);
    }

    /**
     * Calcula o ranking dos N estudantes que mais pegaram livros
     * @param n Número de registros
     * @return Lista dos N estudantes ordenados
     */
    public List<Student> mostNBorrowers(int n){
        return StatsManager.getStatsManager().mostNBorrowerStudentRank(n);
    }

    /**
     * Calcula o ranking dos N livros mais populares
     * @param n Número de registros
     * @return Lista dos N livros ordenados
     */
    public List<Book> mostNBorrowedBooks(int n){
        return StatsManager.getStatsManager().mostNPopularBooksRank(n);
    }

    /**
     * Calcula o ranking dos N autores mais populares
     * @param n Número de registros
     * @return Lista dos N autores ordenados
     */
    public List<Author> mostNPopularAuthors(int n){
        return StatsManager.getStatsManager().mostNPopularAuthorsRank(n);
    }

    /**
     * Calcula o ranking dos N estilos mais populares
     * @param n Número de registros
     * @return Lista dos N estilos ordenados
     */
    public List<Type> mostNPopularTypes(int n){
        return StatsManager.getStatsManager().mostNPopularTypes(n);
    }

    /**
     * Cadastra um novo estudante
     * @param name Nome
     * @param surname Sobrenome
     * @param birthdate Data de nascimento
     * @param gender Gênero
     * @param classCode Código de classe
     * @param points Quantidade de pontos
     * @return Instância criada do estudante
     */
    public Student registerStudent(String name, String surname, LocalDate birthdate, char gender, String classCode, int points){
        students = getStudents();
        Student student = new Student(name, surname, birthdate, gender, classCode, points);
        students.put(student.getStudentId(), student);
        notify("student",student);
        bw.writeMap(students, "students.ser");
        return student;
    }

    /**
     * Cadastra um novo livro
     * @param name Nome
     * @param pageCount Número de páginas
     * @param point Quantidade de pontos
     * @param author Autor
     * @param type Estilo
     * @return Instância criada do livro
     */
    public Book registerBook(String name, int pageCount, int point, Author author, Type type){
        books = getBooks();
        Book book = new Book(name, pageCount, point, author, type);
        books.put(book.getBookId(), book);
        notify("book",book);
        bw.writeMap(books, "books.ser");
        return book;
    }

    /**
     * Cadastra um novo autor
     * @param name Nome
     * @param surname Sobrenome
     * @return Instância criada do autor
     */
    public Author registerAuthor(String name, String surname){
        authors = getAuthors();
        Author author = new Author(name, surname);
        authors.put(author.getAuthorId(), author);
        notify("author",author);
        bw.writeMap(authors, "authorsFull.ser");
        return author;
    }

    /**
     * Registra um novo estilo
     * @param name Nome
     * @return Instância criada do estilo
     */
    public Type registerType(String name){
        types = getTypes();
        Type type = new Type(name);
        types.put(type.getTypeId(), type);
        notify("type",type);
        bw.writeMap(types, "types.ser");
        return type;
    }

    /**
     * Registra um novo empréstimo
     * @param student Estudante
     * @param book Livro
     * @param takenDate Data de retirada
     * @param broughtDate Data de devolução
     * @return Instância criada do empréstimo
     */
    public Borrow registerBorrow(Student student, Book book, LocalDateTime takenDate, LocalDateTime broughtDate){
        borrows = getBorrows();
        Borrow borrow = new Borrow(student, book, takenDate, broughtDate);
        borrows.put(borrow.getBorrowId(), borrow);
        if(broughtDate == null){
            student.addPoints(-book.getPoint());
            student.addActualBooks();
            book.addBorrowedCopy(student);
        }else{
            student.addPoints(book.getPoint());
        }
        notify("borrow",borrow);
        bw.writeMap(borrows, "borrows.ser");
        return borrow;
    }

    /**
     * Define a data de devolução de um empréstimo
     * @param borrow Empréstimo a ser alterado
     * @param broughtDate Data de devolução
     */
    public void setBorrowReturn(Borrow borrow, LocalDateTime broughtDate){
        borrow.setBroughtDate(broughtDate);
        Student s = borrow.getStudent();
        Book b = borrow.getBook();
        s.addPoints(2*borrow.getBook().getPoint());
        s.removeActualBooks();
        b.removeBorrowedCopy(s);
        bw.writeMap(borrows, "borrows.ser");
    }

    private void notify(String type, Notifiable data){
        EventManager.notify(type, data);
    }
}
