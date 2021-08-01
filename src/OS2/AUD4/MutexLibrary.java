package OS2.AUD4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexLibrary {
    List<String> books;
    int capacity;

    static Lock lock = new ReentrantLock();

    public MutexLibrary(int capacity) {
        this.capacity = capacity;
        books = new ArrayList<>();
    }

    public void returnBook(String book) throws InterruptedException {
        lock.lock();
        if (books.size() <= capacity) {
            books.add(book);
            lock.unlock();
        } else {
            lock.unlock();
        }

    }

    public String borrowBook() throws InterruptedException {
        String book = "";
        while(true) {
            lock.lock();
            if (books.size() > 0) {
                book = books.get(0);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
        books.remove(0);
        return book;
    }
}
