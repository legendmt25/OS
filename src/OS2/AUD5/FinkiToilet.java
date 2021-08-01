package OS2.AUD5;

import java.util.concurrent.Semaphore;

public class FinkiToilet {

    static class Toilet {
        public void vlezi() {
            System.out.println("vlezi()");
        }
        public void izlezi() {
            System.out.println("izlezi()");
        }
    }

    static Semaphore toiletAccess;

    public void init() {
        toiletAccess = new Semaphore(1);
    }

    static class Man extends Thread {
        static int num = 0;
        static Semaphore lock = new Semaphore(1);
        Toilet toilet;

        public Man(Toilet toilet) {
            this.toilet = toilet;
        }

        public void enter() throws InterruptedException {
            lock.acquire();
            if(num == 0) {
                toiletAccess.acquire();
            }
            num++;
            toilet.vlezi();
            lock.release();
        }


        public void exit() throws InterruptedException {
            lock.acquire();
            toilet.izlezi();
            num--;
            if(num == 0) {
                toiletAccess.release();
            }
            lock.release();
        }

        @Override
        public void run() {

        }
    }

    static class Woman extends Thread {
        static int num = 0;
        static Semaphore lock = new Semaphore(1);
        Toilet toilet;

        public Woman(Toilet toilet) {
            this.toilet = toilet;
        }

        public void enter() throws InterruptedException {
            lock.acquire();
            if(num == 0) {
                toiletAccess.acquire();
            }
            num++;
            toilet.vlezi();
            lock.release();
        }


        public void exit() throws InterruptedException {
            lock.acquire();
            toilet.izlezi();
            num--;
            if(num == 0) {
                toiletAccess.release();
            }
            lock.release();
        }

        @Override
        public void run() {

        }
    }

}
