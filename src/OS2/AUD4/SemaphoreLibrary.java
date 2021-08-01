package OS2.AUD4;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreLibrary {
    List<String> books;
    int capacity;

    Semaphore coordinator = new Semaphore(1);
    Semaphore rBook = new Semaphore(10);
    Semaphore bBook = new Semaphore(10);

    public SemaphoreLibrary(int capacity) {
        this.capacity = capacity;
        books = new ArrayList<>();
    }

    public void returnBook(String book) throws InterruptedException {
        rBook.acquire();

        coordinator.acquire();
        while (books.size() >= capacity) {
            coordinator.release();
            Thread.sleep(1000);
            coordinator.acquire();
        }

        books.add(book);
        coordinator.release();
        bBook.release();
    }

    public String borrowBook() throws InterruptedException {
        bBook.acquire();

        coordinator.acquire();
        String book = "";
        while (books.size() <= 0) {
            coordinator.release();
            Thread.sleep(1000);
            coordinator.acquire();
        }

        book = books.remove(0);
        coordinator.release();
        rBook.release();
        return book;
    }
}
