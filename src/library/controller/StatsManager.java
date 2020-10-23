package library.controller;

import library.comparators.*;
import library.entities.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

class StatsManager {
    private static StatsManager statsManager;

    private TreeSet<Author> mostPopularAuthor;
    private TreeSet<Type> mostPopularType;
    private TreeSet<Student> mostBorrowerStudent;
    private TreeSet<Book> mostPopularBook;
    private TreeSet<Borrow> lastNBorrows;
    private TreeSet<Borrow> borrowsNDays;

    private StatsManager(){
        mostPopularAuthor = new TreeSet<Author>(new MostPopularAuthorsComparator());
        mostPopularType = new TreeSet<Type>(new MostPopularTypesComparator());
        mostPopularBook = new TreeSet<Book>(new MostBorrowedBooksComparator());
        mostBorrowerStudent = new TreeSet<Student>(new MostBorrowerStudentsComparator());
        lastNBorrows = new TreeSet<Borrow>(new MostRecentBorrowComparator());
        borrowsNDays = new TreeSet<Borrow>(new MostDaysBorrowComparator());
    }

    public static StatsManager getStatsManager() {
        if(statsManager == null) statsManager = new StatsManager();
        return statsManager;
    }

    public List<Author> getMostPopularAuthor() {
        if(!mostPopularAuthor.isEmpty()) return new ArrayList<>(mostPopularAuthor);
        DataManager dm = DataManager.getDataManager();
        mostPopularAuthor.addAll(dm.getAuthors());
        return new ArrayList<>(mostPopularAuthor);
    }

    public List<Type> getMostPopularType() {
        if(!mostPopularType.isEmpty()) return new ArrayList<>(mostPopularType);
        DataManager dm = DataManager.getDataManager();
        mostPopularType.addAll(dm.getTypes());
        return new ArrayList<>(mostPopularType);
    }

    public List<Student> getMostBorrowerStudent() {
        if(!mostBorrowerStudent.isEmpty()) return new ArrayList<>(mostBorrowerStudent);
        DataManager dm = DataManager.getDataManager();
        mostBorrowerStudent.addAll(dm.getStudents());
        return new ArrayList<>(mostBorrowerStudent);
    }

    public List<Book> getMostPopularBook() {
        if(!mostPopularBook.isEmpty()) return new ArrayList<>(mostPopularBook);
        DataManager dm = DataManager.getDataManager();
        mostPopularBook.addAll(dm.getBooks());
        return new ArrayList<>(mostPopularBook);
    }

    public List<Borrow> getLastNBorrows() {
        if(!lastNBorrows.isEmpty()) return new ArrayList<>(lastNBorrows);
        DataManager dm = DataManager.getDataManager();
        lastNBorrows.addAll(dm.getBorrows());
        return new ArrayList<>(lastNBorrows);
    }

    public List<Borrow> getBorrowsNDays() {
        if(!borrowsNDays.isEmpty()) return new ArrayList<>(borrowsNDays);
        DataManager dm = DataManager.getDataManager();
        borrowsNDays.addAll(dm.getBorrows());
        return new ArrayList<>(borrowsNDays);
    }

    public List<Borrow> lastNBorrowsRank(int n){
        List<Borrow> borrows = getLastNBorrows();
        int min = n < borrows.size() ? n : borrows.size();
        List<Borrow> rank = new ArrayList<>(borrows.subList(0,min));
        return rank;
    }

    public List<Borrow> borrowsNDaysRank(int n){
        List<Borrow> borrows = getBorrowsNDays();
        List<Borrow> rank = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for(Borrow borrow : borrows){
            LocalDateTime last = borrow.getBroughtDate();
            if(last == null) last = now;
            if(Duration.between(borrow.getTakenDate(),last).toDays() < n) break;
            rank.add(borrow);
        }
        return rank;
    }

    public List<Student> mostNBorrowerStudentRank(int n){
        List<Student> students = getMostBorrowerStudent();
        int min = n < students.size() ? n : students.size();
        List<Student> rank = new ArrayList<>(students.subList(0, min));
        return rank;
    }

    public List<Book> mostNPopularBooksRank(int n){
        List<Book> books = getMostPopularBook();
        int min = n < books.size() ? n : books.size();
        List<Book> rank = new ArrayList<>(books.subList(0, min));
        return rank;
    }

    public List<Author> mostNPopularAuthorsRank(int n){
        List<Author> authors = getMostPopularAuthor();
        int min = n < authors.size() ? n : authors.size();
        List<Author> rank = new ArrayList<>(authors.subList(0, min));
        return rank;
    }

    public List<Type> mostNPopularTypes(int n){
        List<Type> types = getMostPopularType();
        int min = n < types.size() ? n : types.size();
        List<Type> rank = new ArrayList<>(types.subList(0, min));
        return rank;
    }
}
