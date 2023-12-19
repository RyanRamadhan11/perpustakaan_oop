package model;

abstract class Book {
    protected String code;
    protected String title;
    protected Integer year;

    public Book(String code, String title, Integer year) {
        this.code = code;
        this.title = title;
        this.year = year;
    }

    public abstract String getTitle();

}
