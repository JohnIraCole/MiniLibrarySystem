
import java.util.*;

/**
 * Main class for the Mini Library System.
 */
public class MiniLibrarySystem {

    private static final ArrayList<Book> books = new ArrayList<>();
    private static final Queue<String> borrowQueue = new LinkedList<>();
    private static final Stack<String> returnStack = new Stack<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        runMenu();
    }

    private static void runMenu() {
        while (true) {
            printMenu();
            System.out.print("Enter choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> borrowBook();
                case 3 -> returnBook();
                case 4 -> searchBook();
                case 5 -> sortBooks();
                case 6 -> displayBooks();
                case 7 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=================================");
        System.out.println("          MINI LIBRARY SYSTEM");
        System.out.println("=================================");
        System.out.println("[1] Add Book");
        System.out.println("[2] Borrow Book (Queue)");
        System.out.println("[3] Return Book (Stack)");
        System.out.println("[4] Search Book");
        System.out.println("[5] Sort Books (Title / Author)");
        System.out.println("[6] Display All Books");
        System.out.println("[7] Exit");
        System.out.println("=================================");
    }

    private static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    // ADD BOOK
    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    // BORROW BOOK
    private static void borrowBook() {
        System.out.print("Enter Borrower Name: ");
        String borrower = sc.nextLine();

        System.out.print("Enter Book ID to Borrow: ");
        String id = sc.nextLine();

        borrowQueue.add(borrower + " borrowed " + id);
        System.out.println(borrower + " added to borrowing queue.");
    }

    // RETURN BOOK
    private static void returnBook() {
        System.out.print("Enter Book ID to Return: ");
        String id = sc.nextLine();

        returnStack.push(id);
        System.out.println(id + " added to return stack.");
    }

    // SEARCH BOOK
    private static void searchBook() {
        System.out.print("Enter keyword (Title or Author): ");
        String key = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(key) ||
                b.getAuthor().toLowerCase().contains(key)) {
                System.out.println("FOUND: " + b);
                found = true;
            }
        }

        if (!found) System.out.println("No matching books found.");
    }

    // SORT BOOKS
    private static void sortBooks() {
        System.out.println("[1] Sort by Title");
        System.out.println("[2] Sort by Author");
        System.out.print("Enter choice: ");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> {
                books.sort(Comparator.comparing(b -> b.getTitle().toLowerCase()));
                System.out.println("Books sorted by Title.");
            }
            case 2 -> {
                books.sort(Comparator.comparing(b -> b.getAuthor().toLowerCase()));
                System.out.println("Books sorted by Author.");
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    // DISPLAY BOOKS
    private static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- BOOK LIST ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
