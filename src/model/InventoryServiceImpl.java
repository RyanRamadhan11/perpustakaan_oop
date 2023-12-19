package model;

import util.FormatCode;

import java.util.*;

public class InventoryServiceImpl implements InventoryService {
    private static Map<String, Book> bookMap = new HashMap<>();
    private Scanner input = new Scanner(System.in);

    public InventoryServiceImpl() {
    }

    public void displayMenu() {
        try {
            while (true) {
                System.out.println("~~~~ Welcome to EnigmaPus ~~~~");
                System.out.println("1. Add Book");
                System.out.println("2. Search Book By Id");
                System.out.println("3. Search Book By Title");
                System.out.println("4. Delete Book");
                System.out.println("5. Get All Books");
                System.out.println("6. Exit");

                System.out.print("Enter Your Choice : ");
                int choice = input.nextInt();
                input.nextLine();

                InventoryService is = new InventoryServiceImpl();

                switch (choice) {
                    case 1:
                        getAddBook();
                        break;
                    case 2:
                        getSearchBookById(input);
                        break;
                    case 3:
                        getSearchBookByTitle(input, is);
                        break;
                    case 4:
                        getDeleteBook(input, is);
                        break;
                    case 5:
                        getAllBook(is);
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice, Please try again !!!");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred : " + e.getMessage());
        }
    }

    @Override
    public void addBook(Book book) {
        bookMap.put(book.code, book);
    }

    public void getAddBook() {
        try {
            InventoryService is = new InventoryServiceImpl();
            System.out.println("~~~ Form Add Book ~~~ ");
            System.out.print("input type book (novel or magazine) : ");
            String typeBook = input.nextLine().toLowerCase();

            System.out.print("input title book : ");
            String title = input.nextLine().toLowerCase();


            System.out.print("input publication Year book : ");
            Integer year = input.nextInt();
            input.nextLine();

            if (typeBook.equals("novel")) {
                System.out.print("input publisher book : ");
                String publisher = input.nextLine();

                System.out.print("Input author book : ");
                String author = input.nextLine();
                String novelCode = FormatCode.formatNovelCode(year);

                Novel novel = new Novel(novelCode, title, year, author, publisher);
                is.addBook(novel);
                System.out.println("novel succes add with code " + novelCode);

            } else if (typeBook.equals("magazine")) {
                System.out.print("Input period magazine : ");
                String period = input.nextLine();
                String magazineCode = FormatCode.formatMagazineCode(year);

                Magazine magazine = new Magazine(magazineCode, title, year, period);
                is.addBook(magazine);
                System.out.println("magazine success add with code " + magazineCode);
            } else {
                System.out.println("invalid type book");
            }
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    @Override
    public Book seacrhBookById(String code) {
        return bookMap.get(code);
    }

    public static void getSearchBookById(Scanner input) {
        try {
            System.out.print("Input Code Book to Seacrh (Format : year-type-counter) : ");
            String code = input.nextLine();

            InventoryServiceImpl is = new InventoryServiceImpl();
            Book foundCode = is.seacrhBookById(code);

            if (foundCode != null) {
                System.out.println("~~~ Book Found ~~~");
                displayBookDetails(foundCode);
            } else {
                System.out.println(" Book Not Found ");
            }
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Book> seacrhBookByTitle(String title) {
        List<Book> temp = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                temp.add(book);
            }
        }
        return temp;
    }

    public static void getSearchBookByTitle(Scanner input, InventoryService is) {
        try {
            System.out.print("input title Book To Search: ");
            String title = input.nextLine();
            List<Book> foundBook = is.seacrhBookByTitle(title);

            if (!foundBook.isEmpty()) {
                System.out.println("Book Found : ");
                for (Book book : foundBook) {
                    displayBookDetails(book);
                }
            } else {
                System.out.println("Book Not Found ");
            }
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteBook(String code) {
        bookMap.remove(code);
    }

    public static void getDeleteBook(Scanner input, InventoryService is) {
        try {
            System.out.print("input code Book To Delete: ");
            String foundCode = input.nextLine();
            Book deleteBook = is.seacrhBookById(foundCode);
            if (deleteBook != null) {
                is.deleteBook(foundCode);
                System.out.println("~~ Book delete success ~~");
            } else {
                System.out.println("Book Not Found ");
            }
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    public static void getAllBook(InventoryService is) {
        try {
            List<model.Book> allBook = is.getAllBooks();
            if (!allBook.isEmpty()) {
                System.out.println("~~~ All Books ~~~");
                for (int i = 0; i < allBook.size(); i++) {
                    System.out.println("Index: " + i);
                    displayBookDetails(allBook.get(i));

                    if (i < allBook.size() - 1) {
                        System.out.println();
                    }
                }
            } else {
                System.out.println("Books Not Found");
            }
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    public static void displayBookDetails(Book book) {
        try {
            System.out.println("~~~ Details Book : ~~~");
            System.out.println("Code  : " + book.code);
            System.out.println("Title : " + book.title);
            System.out.println("Year : " + book.year);

            if (book instanceof Novel) {
                System.out.println("Publisher : " + ((Novel) book).publisher);
                System.out.println("Author : " + ((Novel) book).author);
            } else if (book instanceof Magazine) {
                System.out.println("Period : " + ((Magazine) book).period); // Fix here
            }
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }
}