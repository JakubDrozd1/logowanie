package com.example.logowanie;

public class Quotation {
    private String content;
    private String author;

    public Quotation() {
    }

    public Quotation(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
