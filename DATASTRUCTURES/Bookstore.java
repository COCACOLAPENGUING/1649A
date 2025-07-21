import java.util.Scanner;

class Book {
    String title;
    String code;
    String author;
    double price;
    int quantity;

    public Book(String title, String code, String author, double price, int quantity) {
        this.title = title;
        this.code = code;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book Name: " + title +
                " | Code: " + code +
                " | Author: " + author +
                " | Price: " + price + " USD" +
                " | Quantity: " + quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        return this.code.equals(other.code);
    }
}

class MyArray<T> {
    private final Object[] data;
    private int size = 0;

    public MyArray(int capacity) {
        data = new Object[capacity];
    }

    public void add(T item) {
        if (size < data.length) {
            data[size++] = item;
        }
    }

    public void set(int index, T item) {
        data[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) data[index];
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }
}

class MyQueue<T> {
    private final Object[] data;
    private int front = 0, rear = -1, size = 0;

    public MyQueue(int capacity) {
        data = new Object[capacity];
    }

    public void enqueue(T item) {
        if (size == data.length) return;
        rear = (rear + 1) % data.length;
        data[rear] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) return null;
        T item = (T) data[front];
        front = (front + 1) % data.length;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return isEmpty() ? null : (T) data[front];
    }
}

class MyStack<T> {
    private final Object[] data;
    private int top = -1;

    public MyStack(int capacity) {
        data = new Object[capacity];
    }

    public void push(T item) {
        if (top < data.length - 1) {
            data[++top] = item;
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        return isEmpty() ? null : (T) data[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

class Order {
    String customerName;
    String customerAddress;
    String status;
    MyArray<Book> booksOrdered = new MyArray<>(100);

    public Order(String customerName, String customerAddress) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.status = "Pending";
    }

    public void addBook(Book book) {
        booksOrdered.add(book);
    }

    public MyArray<Book> getBooksOrdered() {
        return booksOrdered;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Name: ").append(customerName).append("\n");
        sb.append("Customer Address: ").append(customerAddress).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Books:\n");
        for (int i = 0; i < booksOrdered.size(); i++) {
            sb.append("  ").append(i + 1).append(". ")
                    .append(booksOrdered.get(i)).append("\n");
        }
        return sb.toString();
    }
}

public class Bookstore {
    static MyArray<Book> bookCatalog = new MyArray<>(100);
    static MyQueue<Order> orders = new MyQueue<>(100);
    static MyArray<Order> processedOrders = new MyArray<>(100);
    static MyStack<Order> recentProcessedStack = new MyStack<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize catalog
        bookCatalog.add(new Book("Algorithms Unlocked", "#B1001", "Thomas Cormen", 45.99, 8));
        bookCatalog.add(new Book("Data Structures in Java", "#B1002", "Robert Lafore", 55.50, 6));
        bookCatalog.add(new Book("Machine Learning Crash Course", "#B1003", "Google AI", 75.00, 10));
        bookCatalog.add(new Book("Artificial Intelligence Basics", "#B1004", "John Smith", 80.25, 5));
        bookCatalog.add(new Book("Cybersecurity for Beginners", "#B1005", "Jane Doe", 42.00, 12));
        bookCatalog.add(new Book("Networking Essentials", "#B1006", "Andrew Tanenbaum", 38.50, 9));
        bookCatalog.add(new Book("Clean Code", "#B1007", "Robert C. Martin", 49.99, 7));
        bookCatalog.add(new Book("Design Patterns Explained", "#B1008", "Alan Shalloway", 59.95, 4));
        bookCatalog.add(new Book("Introduction to Databases", "#B1009", "Jennifer Widom", 35.00, 11));
        bookCatalog.add(new Book("Web Development with HTML/CSS", "#B1010", "Jon Duckett", 30.99, 15));
        bookCatalog.add(new Book("Java Programming Masterclass", "#B1011", "Tim Buchalka", 65.00, 6));
        bookCatalog.add(new Book("Python Programming for All", "#B1012", "Charles Severance", 33.75, 14));
        bookCatalog.add(new Book("C# Fundamentals", "#B1013", "Mark J. Price", 50.00, 5));
        bookCatalog.add(new Book("C++ for Beginners", "#B1014", "Bjarne Stroustrup", 40.00, 8));
        bookCatalog.add(new Book("Full Stack Web Development", "#B1015", "David Flanagan", 79.99, 3));
        bookCatalog.add(new Book("Operating System Concepts", "#B1016", "Abraham Silberschatz", 69.99, 6));
        bookCatalog.add(new Book("Cloud Computing Explained", "#B1017", "John Rhoton", 60.00, 7));
        bookCatalog.add(new Book("Linux Command Line Basics", "#B1018", "William Shotts", 25.99, 10));
        bookCatalog.add(new Book("Agile Project Management", "#B1019", "Jim Highsmith", 44.90, 5));
        bookCatalog.add(new Book("Mobile App Development", "#B1020", "Chris Griffith", 58.00, 4));

        int option;
        do {
            System.out.println("\n--- Bookstore Menu ---");
            System.out.println("1. View catalog");
            System.out.println("2. Place order");
            System.out.println("3. Process orders");
            System.out.println("4. Search orders");
            System.out.println("5. View recent processed orders");
            System.out.println("6. View pending orders");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            option = Integer.parseInt(scanner.nextLine().trim());

            switch (option) {
                case 1 -> viewCatalog();
                case 2 -> placeOrder(scanner);
                case 3 -> processOrders(scanner);
                case 4 -> searchProcessedOrders(scanner);
                case 5 -> viewRecentProcessed();
                case 6 -> viewPendingOrders();
                case 7 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 7);
    }

    private static void viewCatalog() {
        for (int i = 0; i < bookCatalog.size(); i++) {
            System.out.println((i + 1) + ". " + bookCatalog.get(i));
        }
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        Order newOrder = new Order(name, address);
        viewCatalog();

        String choice;
        do {
            System.out.print("Enter book number to add (or 'done'): ");
            choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("done")) {
                int idx = Integer.parseInt(choice) - 1;
                if (idx >= 0 && idx < bookCatalog.size()) {
                    Book b = bookCatalog.get(idx);
                    System.out.print("Enter quantity: ");
                    int qty = Integer.parseInt(scanner.nextLine());
                    if (qty > 0 && qty <= b.quantity) {
                        b.quantity -= qty;
                        newOrder.addBook(new Book(b.title, b.code, b.author, b.price, qty));
                        System.out.println("Book added to order.");
                    } else {
                        System.out.println("Invalid quantity.");
                    }
                } else {
                    System.out.println("Invalid book number.");
                }
            }
        } while (!choice.equalsIgnoreCase("done"));

        if (newOrder.getBooksOrdered().size() > 0) {
            orders.enqueue(newOrder);
            System.out.println("Order placed. (Status: Pending)");
        }
    }

    private static void processOrders(Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }
        System.out.print("Choose sort: 1) Insertion 2) Bubble 3) Selection: ");
        int sortChoice = Integer.parseInt(scanner.nextLine().trim());

        while (!orders.isEmpty()) {
            Order order = orders.dequeue();
            order.setStatus("Processed");
            switch (sortChoice) {
                case 1 -> insertionSort(order.getBooksOrdered());
                case 2 -> bubbleSort(order.getBooksOrdered());
                case 3 -> selectionSort(order.getBooksOrdered());
            }
            processedOrders.add(order);
            recentProcessedStack.push(order);
            System.out.println("Processed Order:\n" + order);
        }
    }

    private static void searchProcessedOrders(Scanner scanner) {
        System.out.print("Enter keyword to search by title, author, or code: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (int i = 0; i < processedOrders.size(); i++) {
            Order order = processedOrders.get(i);
            for (int j = 0; j < order.getBooksOrdered().size(); j++) {
                Book book = order.getBooksOrdered().get(j);
                if (book.title.toLowerCase().contains(keyword) ||
                        book.code.toLowerCase().contains(keyword) ||
                        book.author.toLowerCase().contains(keyword)) {

                    System.out.println("Found in Order #" + (i + 1) + " (Status: " + order.status + "):");
                    System.out.println(order);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("No match found.");
        }
    }

    private static void viewRecentProcessed() {
        if (recentProcessedStack.isEmpty()) {
            System.out.println("No recent orders.");
            return;
        }
        System.out.println("Recent Processed Orders:");
        while (!recentProcessedStack.isEmpty()) {
            System.out.println(recentProcessedStack.pop());
        }
    }

    private static void viewPendingOrders() {
        if (orders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }
        System.out.println("=== Pending Orders ===");
        // Temporarily dequeue to display, then restore
        MyQueue<Order> temp = new MyQueue<>(100);
        int count = 1;
        while (!orders.isEmpty()) {
            Order o = orders.dequeue();
            System.out.println("Order #" + count++ + " (Status: " + o.status + "):");
            System.out.println(o);
            temp.enqueue(o);
        }
        // Restore original queue
        while (!temp.isEmpty()) {
            orders.enqueue(temp.dequeue());
        }
    }

    /* Sorting algorithms */
    public static void insertionSort(MyArray<Book> arr) {
        for (int i = 1; i < arr.size(); i++) {
            Book key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j).title.compareToIgnoreCase(key.title) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
    }

    public static void bubbleSort(MyArray<Book> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (arr.get(j).title.compareToIgnoreCase(arr.get(j + 1).title) > 0) {
                    Book tmp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, tmp);
                }
            }
        }
    }

    public static void selectionSort(MyArray<Book> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j).title.compareToIgnoreCase(arr.get(min).title) < 0) {
                    min = j;
                }
            }
            Book tmp = arr.get(min);
            arr.set(min, arr.get(i));
            arr.set(i, tmp);
        }
    }
}
