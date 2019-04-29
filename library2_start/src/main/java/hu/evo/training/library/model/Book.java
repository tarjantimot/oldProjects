/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.training.library.model;

/**
 *
 * @author pappmico
 */
public class Book implements DbTable{
    public static final String TABLE_NAME="BOOKS";
    public static final String FIELD_ID="ID";
    public static final String FIELD_ISBN="ISBN";
    public static final String FIELD_AUTHOR="AUTHOR";
    public static final String FIELD_YEAR="YEAR";
    public static final String FIELD_TITLE="TITLE";
    public static final String ATTR="Book.attr";
    
    private int id;
    private String isbn;
    private String author;
    private Integer year;
    private String title;

    public Book(int id, String isbn, String author, Integer year, String title) {
        this.id = id;
        this.isbn = isbn;
        this.author = author;
        this.year = year;
        this.title = title;
    }
    
    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", isbn=" + isbn + ", author=" + author + ", year=" + year + ", title=" + title + '}';
    }

    @Override
    public String getTableName() {
        return Book.TABLE_NAME;
    }

}
