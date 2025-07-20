import java.util.Scanner;

class Book {
    String title;
    String code;
    double price;
    int quantity;

    public Book(String title, String code, double price, int quantity) {
        this.title = title;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }


    public String toString() {
        return "Book Name: " + title + " | Code: " + code + " | Price: " + price + " USD | Quantity: " + quantity;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return this.code.equals(other.code);
    }
}

class MyArray<T> {
    private Object[] data;
    private int size;

    public MyArray(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public void add(T item) {
        if (size < data.length) {
            data[size++] = item;
        }
    }

    public void set(int index, T item) {
        data[index] = item;
    }

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
    private Object[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T item) {
        if (size == capacity) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T item = (T) data[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        return (T) data[front];
    }

    public T get(int index) {
        return (T) data[(front + index) % capacity];
    }
}

class MyStack<T> {
    private Object[] data;
    private int top;

    public MyStack(int capacity) {
        data = new Object[capacity];
        top = -1;
    }

    public void push(T item) {
        if (top < data.length - 1) {
            data[++top] = item;
        } else {
            System.out.println("Stack is full");
        }
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return (T) data[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public T peek() {
        if (isEmpty()) return null;
        return (T) data[top];
    }

    public int size() {
        return top + 1;
    }
}

class Order {
    MyArray<Book> booksOrdered = new MyArray<>(100);

    public void addBook(Book book) {
        booksOrdered.add(book);
    }

    public MyArray<Book> getBooksOrdered() {
        return booksOrdered;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < booksOrdered.size(); i++) {
            sb.append(booksOrdered.get(i)).append("\n");
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


        bookCatalog.add(new Book("Algorithms Unlocked", "#B1001", 45.99, 8));
        bookCatalog.add(new Book("Data Structures in Java", "#B1002", 55.50, 6));
        bookCatalog.add(new Book("Machine Learning Crash Course", "#B1003", 75.00, 10));
        bookCatalog.add(new Book("Artificial Intelligence Basics", "#B1004", 80.25, 5));
        bookCatalog.add(new Book("Cybersecurity for Beginners", "#B1005", 42.00, 12));
        bookCatalog.add(new Book("Networking Essentials", "#B1006", 38.50, 9));
        bookCatalog.add(new Book("Clean Code", "#B1007", 49.99, 7));
        bookCatalog.add(new Book("Design Patterns Explained", "#B1008", 59.95, 4));
        bookCatalog.add(new Book("Introduction to Databases", "#B1009", 35.00, 11));
        bookCatalog.add(new Book("Web Development with HTML/CSS", "#B1010", 30.99, 15));
        bookCatalog.add(new Book("Java Programming Masterclass", "#B1011", 65.00, 6));
        bookCatalog.add(new Book("Python Programming for All", "#B1012", 33.75, 14));
        bookCatalog.add(new Book("C# Fundamentals", "#B1013", 50.00, 5));
        bookCatalog.add(new Book("C++ for Beginners", "#B1014", 40.00, 8));
        bookCatalog.add(new Book("Full Stack Web Development", "#B1015", 79.99, 3));
        bookCatalog.add(new Book("Operating System Concepts", "#B1016", 69.99, 6));
        bookCatalog.add(new Book("Cloud Computing Explained", "#B1017", 60.00, 7));
        bookCatalog.add(new Book("Linux Command Line Basics", "#B1018", 25.99, 10));
        bookCatalog.add(new Book("Agile Project Management", "#B1019", 44.90, 5));
        bookCatalog.add(new Book("Mobile App Development", "#B1020", 58.00, 4));

        int option;
        do {
            System.out.println("\n--- Bookstore Menu ---");
            System.out.println("1. View catalog");
            System.out.println("2. Place order");
            System.out.println("3. Process orders (choose sorting)");
            System.out.println("4. Search orders");
            System.out.println("5. View recent processed orders (Stack)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            option = Integer.parseInt(scanner.nextLine().trim());

            switch (option) {
                case 1 -> {
                    for (int i = 0; i < bookCatalog.size(); i++) {
                        System.out.println((i + 1) + ". " + bookCatalog.get(i));
                    }
                }
                case 2 -> {
                    Order newOrder = new Order();

                    System.out.println("Available Books:");
                    for (int i = 0; i < bookCatalog.size(); i++) {
                        System.out.println((i + 1) + ". " + bookCatalog.get(i));
                    }

                    String choice;
                    do {
                        System.out.print("Enter book number to add to order (or 'done' to finish): ");
                        choice = scanner.nextLine();

                        if (!choice.equalsIgnoreCase("done")) {
                            try {
                                int bookIndex = Integer.parseInt(choice) - 1;
                                if (bookIndex >= 0 && bookIndex < bookCatalog.size()) {
                                    Book selectedBook = bookCatalog.get(bookIndex);
                                    System.out.print("Enter quantity to order: ");
                                    int orderQty = Integer.parseInt(scanner.nextLine());

                                    if (orderQty <= 0) {
                                        System.out.println("Quantity must be positive.");
                                    } else if (orderQty > selectedBook.quantity) {
                                        System.out.println("Not enough stock. Available: " + selectedBook.quantity);
                                    } else {
                                        selectedBook.quantity -= orderQty;
                                        Book bookInOrder = new Book(selectedBook.title, selectedBook.code, selectedBook.price, orderQty);
                                        newOrder.addBook(bookInOrder);
                                        System.out.println("Added: " + selectedBook.title + " (Qty: " + orderQty + ")");
                                    }
                                } else {
                                    System.out.println("Invalid book number.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid number.");
                            }
                        }
                    } while (!choice.equalsIgnoreCase("done"));

                    if (newOrder.booksOrdered.size() > 0) {
                        orders.enqueue(newOrder);
                        System.out.println("Order placed successfully!");
                        System.out.println("Total orders in queue: " + orders.size());
                    } else {
                        System.out.println("No books added. Order was not saved.");
                    }
                }
                case 3 -> {
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        int sortOption;
                        try {
                            System.out.print("Choose sorting algorithm: 1) Insertion 2) Bubble 3) Selection: ");
                            sortOption = Integer.parseInt(scanner.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Defaulting to Insertion Sort.");
                            sortOption = 1;
                        }

                        while (!orders.isEmpty()) {
                            Order order = orders.dequeue();
                            switch (sortOption) {
                                case 1 -> insertionSort(order.getBooksOrdered());
                                case 2 -> bubbleSort(order.getBooksOrdered());
                                case 3 -> selectionSort(order.getBooksOrdered());
                                default -> System.out.println("Invalid sort option, using insertion sort.");
                            }
                            processedOrders.add(order);
                            recentProcessedStack.push(order);
                            System.out.println("--- Sorted Order ---");
                            System.out.println(order);
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Enter keyword to search in processed orders: ");
                    String keyword = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    for (int i = 0; i < processedOrders.size(); i++) {
                        Order order = processedOrders.get(i);
                        for (int j = 0; j < order.getBooksOrdered().size(); j++) {
                            Book book = order.getBooksOrdered().get(j);
                            if (book.title.toLowerCase().contains(keyword) || book.code.toLowerCase().contains(keyword)) {
                                System.out.println("Found in Order #" + (i + 1) + ": " + book);
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("No matching book found in any order.");
                    }
                }
                case 5 -> {
                    if (recentProcessedStack.isEmpty()) {
                        System.out.println("No recent processed orders in stack.");
                    } else {
                        System.out.println("--- Recently Processed Orders (Stack View) ---");
                        while (!recentProcessedStack.isEmpty()) {
                            Order order = recentProcessedStack.pop();
                            System.out.println(order);
                        }
                    }
                }
                case 6 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (option != 6);
    }

    public static void insertionSort(MyArray<Book> array) {
        for (int i = 1; i < array.size(); i++) {
            Book key = array.get(i);
            int j = i - 1;
            while (j >= 0 && array.get(j).title.compareToIgnoreCase(key.title) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }
            array.set(j + 1, key);
        }
    }

    public static void bubbleSort(MyArray<Book> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - 1 - i; j++) {
                if (array.get(j).title.compareToIgnoreCase(array.get(j + 1).title) > 0) {
                    Book temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
    }

    public static void selectionSort(MyArray<Book> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).title.compareToIgnoreCase(array.get(minIndex).title) < 0) {
                    minIndex = j;
                }
            }
            Book temp = array.get(minIndex);
            array.set(minIndex, array.get(i));
            array.set(i, temp);
        }
    }
}