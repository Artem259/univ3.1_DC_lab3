import java.util.concurrent.Semaphore;

public class Visitor implements Runnable {
    private final int n;
    private final Semaphore armchair;
    private final Semaphore work;
    private final Semaphore changing;

    public Visitor(int n, Semaphore armchair, Semaphore work, Semaphore changing) {
        this.n = n;
        this.armchair = armchair;
        this.work = work;
        this.changing = changing;
    }

    @Override
    public void run() {
        //---------------------------------------------------------//
        System.out.println("Visitor " + n + " takes a place in the queue.");
        try {
            armchair.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Visitor " + n + " takes a seat and falls asleep.");
        //---------------------------------------------------------//
        do {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (work.availablePermits() > 0);
        System.out.println("Visitor " + n + " wakes up and leaves an armchair.");
        work.release();
        //---------------------------------------------------------//
        try {
            changing.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Visitor " + n + " leaves.");
        //---------------------------------------------------------//
    }
}
