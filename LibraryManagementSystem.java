import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("\n\u001B[4m\u001B[32mLibrary Books:\u001B[0m");
        for (Book book : books) {
            System.out.println("-------------------------");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("-------------------------");
        }
    }

    public void removeBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                System.out.println("\n\u001B[32mBook removed successfully!\u001B[0m");
                return;
            }
        }
        System.out.println("Book not found in the library.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Adding sample books to the library
        Book book1 = new Book("Java Programming", "John Smith");
        Book book2 = new Book("Python Basics", "Jane Doe");
        library.addBook(book1);
        library.addBook(book2);

        System.out.println("\n\u001B[32m\u001B[4m---####---Welcome to the Library Management System!---####---\u001B[0m");

        while (true) {
            System.out.println("\nMenu");
            System.out.println("1. Admin Module");
            System.out.println("2. User Module");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminModule(library, scanner);
                    break;
                case 2:
                    userModule(library);
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\n\u001B[31mInvalid choice. Please try again. \u001B[0m");
            }
        }
    }

    private static void adminModule(Library library, Scanner scanner) {
        System.out.println("\n\u001B[45mAdmin Module\u001B[0m");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Display Books");
        System.out.println("4. Exit Admin Module");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter the title of the book: ");
                String title = scanner.nextLine();
                System.out.print("Enter the author of the book: ");
                String author = scanner.nextLine();
                Book newBook = new Book(title, author);
                library.addBook(newBook);
                System.out.println("Book added successfully!");
                break;
            case 2:
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter the title of the book to remove: ");
                String removeTitle = scanner.nextLine();
                library.removeBook(removeTitle);
                break;
            case 3:
                library.displayBooks();
                break;
            case 4:
                System.out.println("Exiting Admin Module...");
                break;
            default:
                System.out.println("\n\u001B[31mInvalid choice. Please try again.\u001B[0m]");
        }
    }

    private static void userModule(Library library) {
        System.out.println("\n\u001B[45mUser Module \u001B[0m");
        System.out.println("1. Display Books");
        System.out.println("2. Exit User Module");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
    
        switch (choice) {
            case 1:
                library.displayBooks();
                break;
            case 2:
                System.out.println("Exiting User Module...");
                break;
            default:
                System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
        }
    }
}
