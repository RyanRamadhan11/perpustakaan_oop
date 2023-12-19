package model;

public class Magazine extends Book {
    public String period;

    public Magazine(String code, String title, Integer year, String period) {
        super(code, title, year);
        this.period = period;
    }

    public String getTitle() {
        return title;
    }
}
