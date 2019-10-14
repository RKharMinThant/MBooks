package com.nobletecx.mbooks.model;

public class Book {
    public String author,title;
    public int page_no,image;

    public Book(String author, String title, int page_no,int image) {
        this.author = author;
        this.title = title;
        this.page_no = page_no;
        this.image = image;
    }
}
