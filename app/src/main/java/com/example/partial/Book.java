package com.example.partial;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private Integer year;

    Book(){

    }

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return title + "\n" + year;
    }
}
