package OS2.AUD5;

import java.util.concurrent.Semaphore;

public class ProducerController {
    public static final int  NUM_RUN = 50;
    static Semaphore accessBuffer;
    static Semaphore lock;
    static Semaphore canCheck;

    public static void init() {
        accessBuffer = new Semaphore(1);
        lock = new Semaphore(1);
        canCheck = new Semaphore(10);
    }

    static class Buffer {
        public int numChecks = 0;

        public void produce() {
            System.out.println("Produce");
        }

        public void check() {
            System.out.println("Check");
        }
    }

    static class Producer extends Thread {
        private final Buffer buffer;

        Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        public void execute() throws InterruptedException {
            accessBuffer.acquire();
            buffer.produce();
            accessBuffer.release();
        }

        @Override
        public void run() {
            for(int i = 0; i < NUM_RUN; ++i) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Controller extends Thread {
        private final Buffer buffer;

        Controller(Buffer buffer) {
            this.buffer = buffer;
        }

        public void execute() throws InterruptedException {
            lock.acquire();
            if(buffer.numChecks == 0) {
                accessBuffer.acquire();
            }
            buffer.numChecks++;
            lock.release();

            canCheck.acquire();
            buffer.check();

            lock.acquire();
            buffer.numChecks--;
            canCheck.release();
            if(buffer.numChecks == 0) {
                accessBuffer.release();
            }
            lock.release();
        }

        @Override
        public void run() {
            for(int i = 0; i < NUM_RUN; ++i) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
