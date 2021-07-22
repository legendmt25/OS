package OS2.AUD4;

import java.util.ArrayList;
import java.util.List;

public class SyncLibrary {
    List<String> books;
    int capacity;

    public SyncLibrary(int capacity) {
        this.capacity = capacity;
        books = new ArrayList<>();
    }

    public synchronized void returnBook(String book) throws InterruptedException {
        while(books.size() > capacity) {
            wait();
        }
        books.add(book);
        notifyAll();
    }

    public synchronized String borrowBook() throws InterruptedException {
        String book = "";
        while(books.size() == 0) {
            wait();
        }
        book = books.get(0);
        books.remove(0);
        notify();
        return book;
    }

}
