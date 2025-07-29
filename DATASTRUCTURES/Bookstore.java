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

    public String toString() {
        return "Book Name: " + title
                + " | Code: " + code
                + " | Author: " + author
                + " | Price: " + price + " USD"
                + " | Quantity: " + quantity;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        return this.code.equals(other.code);
    }
}

class Node<E> {
    E data;
    Node<E> next;
    public Node(E data) { this.data = data; this.next = null; }
}

class Array<E> {
    private Node<E> head;
    private int size = 0;

    public Array(int capacity) {
        head = null;
        size = 0;
    }

    public void add(E item) {
        Node<E> node = new Node<>(item);
        if (head == null) head = node;
        else {
            Node<E> current = head;
            while (current.next != null) current = current.next;
            current.next = node;
        }
        size++;
    }

    public void set(int index, E item) {
        if (index < 0 || index >= size) return;
        Node<E> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        current.data = item;
    }

    public E get(int index) {
        if (index < 0 || index >= size) return null;
        Node<E> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }
}

class Queue<E> {
    private Node<E> front, rear;
    private int size = 0;

    public Queue(int capacity) {
        front = rear = null;
        size = 0;
    }

    public void add(E item) {
        Node<E> node = new Node<>(item);
        if (rear == null) front = rear = node;
        else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public E remove() {
        if (front == null) return null;
        E data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public E peek() {
        return front != null ? front.data : null;
    }
}

class Stack<E> {
    private Node<E> top;
    private int size = 0;

    public Stack(int capacity) {
        top = null;
        size = 0;
    }

    public void push(E item) {
        Node<E> node = new Node<>(item);
        node.next = top;
        top = node;
        size++;
    }

    public E pop() {
        if (top == null) return null;
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}

class Order {
    String customerName;
    String customerAddress;
    String status;
    Array<Book> booksOrdered = new Array<>(0);

    public Order(String customerName, String customerAddress) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.status = "Pending";
    }

    public void addBook(Book book) {
        booksOrdered.add(book);
    }

    public Array<Book> getBooksOrdered() {
        return booksOrdered;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Name: ").append(customerName).append("\n");
        sb.append("Customer Address: ").append(customerAddress).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Books:\n");
        for (int i = 0; i < booksOrdered.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(booksOrdered.get(i)).append("\n");
        }
        return sb.toString();
    }
}

public class Bookstore {
    static Array<Book> bookCatalog = new Array<>(0);
    static Queue<Order> orders = new Queue<>(0);
    static Array<Order> processedOrders = new Array<>(0);
    static Stack<Order> recentProcessedStack = new Stack<>(0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        int option = 0;
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

            try {
                option = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1:
                    for (int i = 0; i < bookCatalog.size(); i++) {
                        System.out.println((i + 1) + ". " + bookCatalog.get(i));
                    }
                    break;
                case 2:
                    placeOrder(scanner);
                    break;
                case 3:
                    processOrders(scanner);
                    break;
                case 4:
                    searchProcessedOrders(scanner);
                    break;
                case 5:
                    viewRecentProcessed();
                    break;
                case 6:
                    viewPendingOrders();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 7);
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        Order newOrder = new Order(name, address);
        for (int i = 0; i < bookCatalog.size(); i++) {
            System.out.println((i + 1) + ". " + bookCatalog.get(i));
        }
        String choice;
        do {
            System.out.print("Enter book number to add (or 'done'): ");
            choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("done")) {
                int idx;
                try {
                    idx = Integer.parseInt(choice) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                    continue;
                }
                if (idx >= 0 && idx < bookCatalog.size()) {
                    Book b = bookCatalog.get(idx);
                    System.out.print("Enter quantity: ");
                    int qty;
                    try {
                        qty = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity.");
                        continue;
                    }
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
        if (newOrder.getBooksOrdered().size() > 0) { orders.add(newOrder); System.out.println("Order placed."); }
    }

    private static void processOrders(Scanner scanner) {
        if (orders.isEmpty()) { System.out.println("No orders to process."); return; }
        System.out.print("Choose sort: 1) Insertion 2) Bubble 3) Selection: ");
        int sortChoice = Integer.parseInt(scanner.nextLine().trim());
        while (!orders.isEmpty()) {
            Order order = orders.remove();
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
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < processedOrders.size(); i++) {
            Order o = processedOrders.get(i);
            for (int j = 0; j < o.getBooksOrdered().size(); j++) {
                Book bk = o.getBooksOrdered().get(j);
                if (bk.title.toLowerCase().contains(keyword)
                        || bk.author.toLowerCase().contains(keyword)
                        || bk.code.toLowerCase().contains(keyword)) {
                    System.out.println("Match in Order #" + (i + 1) + ":\n" + o);
                    found = true;
                    break;
                }
            }
        }
        if (!found) System.out.println("No match found.");
    }

    private static void viewRecentProcessed() {
        while (!recentProcessedStack.isEmpty()) {
            System.out.println(recentProcessedStack.pop());
        }
    }

    private static void viewPendingOrders() {
        if (orders.isEmpty()) { System.out.println("No pending orders."); return; }
        Queue<Order> temp = new Queue<>(0);
        while (!orders.isEmpty()) {
            Order o = orders.remove();
            System.out.println(o);
            temp.add(o);
        }
        while (!temp.isEmpty()) orders.add(temp.remove());
    }

    public static void insertionSort(Array<Book> arr) {
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

    public static void bubbleSort(Array<Book> arr) {
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

    public static void selectionSort(Array<Book> arr) {
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
