package model;

import java.util.List;

public interface InventoryService {

    void addBook(Book book);

    Book seacrhBookById(String code);

    List<Book> seacrhBookByTitle(String title);

    void deleteBook(String code);

    List<Book> getAllBooks();
}
