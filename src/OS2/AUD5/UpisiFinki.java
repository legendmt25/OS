package OS2.AUD5;

import java.util.concurrent.Semaphore;

public class UpisiFinki {

    public static Semaphore slobodnoUpisnoMesto;
    public static Semaphore studentEnter;
    public static Semaphore studentHere;
    public static Semaphore studentDone;

    public void init() {
        slobodnoUpisnoMesto = new Semaphore(4);
        studentEnter = new Semaphore(0);
        studentHere = new Semaphore(0);
        studentDone = new Semaphore(0);
    }

    static class Clen extends Thread {

        public void execute() throws InterruptedException {
            slobodnoUpisnoMesto.acquire();

            final int num = 10;
            for(int i = 0; i < num; ++i) {
                studentEnter.release();
                studentHere.acquire();
                zapisi();
                studentDone.release();
            }

            slobodnoUpisnoMesto.release();
        }

        private void zapisi() {
            System.out.println("zapisi()");
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Student extends Thread {

        public void execute() throws InterruptedException {
            studentEnter.acquire();
            ostaviDokumenti();
            studentHere.release();
            studentDone.acquire();
        }

        private void ostaviDokumenti() {
            System.out.println("ostaviDokumenti()");
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
