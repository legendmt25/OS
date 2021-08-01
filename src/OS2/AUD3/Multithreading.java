package OS2.AUD3;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Multithreading {
    public static void main(String[] args) throws InterruptedException {
        Incrementor inc = new Incrementor();
//        Incrementor inc2 = new Incrementor();
        Thread t1 = new ThreadClass("thread 1", inc);
        Thread t2 = new ThreadClass("thread 2", inc);
        //Thread t3 = new ThreadClass("thread 3", inc);
        t1.start();
        t2.start();
        //t3.start();

        t1.join(5);
        t2.join(5);
        //t3.join();
        System.out.println(inc);
//        if(t1.isAlive() && t2.isAlive()) {
//            System.out.println("Alive");
//            t1.interrupt();
//            t2.interrupt();
//        }
//        if(t1.isInterrupted() && t2.isInterrupted()) {
//            System.out.println("Interrupted");
//        }
        //System.exit(0);
    }
}

class ThreadClass extends Thread {
    String name;
    Incrementor inc;

    public ThreadClass(String name, Incrementor inc) {
        this.name = name;
        this.inc = inc;
    }

    @Override
    public void run() {
        for(int i = 0; i < 20; ++i) {
            try {
                inc.semaphoreIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ": " + inc);
        }
    }

}

class Incrementor {
    private static int count = 0;
    private static Lock mutex = new ReentrantLock();
    private static Semaphore semaphore = new Semaphore(2);

    void semaphoreIncrement() throws InterruptedException {
        semaphore.acquire();
        ++count;
        semaphore.release();
    }

    void increment() {
        mutex.lock();
        ++count;
        mutex.unlock();
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }
}
