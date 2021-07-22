package OS2.AUD4;

import java.util.ArrayList;
import java.util.List;

public class MutexLibrary {
    List<String> books;
    int capacity;

    public MutexLibrary(int capacity) {
        this.capacity = capacity;
        books = new ArrayList<>();
    }

    public synchronized void returnBook(String book) throws InterruptedException {
        while(books.size() > capacity) {

        }
        books.add(book);

    }

    public synchronized String borrowBook() throws InterruptedException {
        String book = "";
        while(books.size() == 0) {

        }
        book = books.get(0);
        books.remove(0);

        return book;
    }
}
