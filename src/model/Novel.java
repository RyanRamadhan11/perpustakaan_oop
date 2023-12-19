package model;

public class Novel extends Book {
    public String author;
    public String publisher;

    public Novel(String code, String title, Integer year, String author, String publisher) {
        super(code, title, year);
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }
}
