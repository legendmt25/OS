package OS2.AUD5;

import java.util.concurrent.Semaphore;

public class SiO2 {
    public static final int  NUM_RUN = 50;
    static Semaphore si;
    static Semaphore o;
    static Semaphore SiHere;
    static Semaphore OHere;
    static Semaphore ready;

    public static void init() {
        si = new Semaphore(1);
        o = new Semaphore(2);
        SiHere = new Semaphore(0);
        OHere = new Semaphore(0);
        ready = new Semaphore(0);
    }

    static class Si extends Thread {

        public void bond() {
            System.out.println("Si bond()");
        }

        public void execute() throws InterruptedException {
            si.acquire();
            SiHere.release(2);
            OHere.acquire(2);
            ready.release(2);
            bond();
            si.release();
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

    static class O extends Thread {

        public void bond() {
            System.out.println("O bond()");
        }

        public void execute() throws InterruptedException {
            o.acquire();
            SiHere.acquire();
            OHere.release();
            ready.acquire();
            bond();
            o.release();
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
