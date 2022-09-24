import java.util.concurrent.Semaphore;

public class Barber implements Runnable {
    private final int visitorN;
    private final Semaphore armchair;
    private final Semaphore work;
    private final Semaphore changing;

    public Barber(int visitorN, Semaphore armchair, Semaphore work, Semaphore changing) {
        this.visitorN = visitorN;
        this.armchair = armchair;
        this.work = work;
        this.changing = changing;
    }

    @Override
    public void run() {
        for(int i = 0; i < visitorN; i++) {
            //---------------------------------------------------------//
            System.out.println("Barber is waiting for a next visitor.");
            do {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while (armchair.availablePermits() > 0);
            //---------------------------------------------------------//
            System.out.println("Barber begins to work...");
            try {
                work.acquire();
                System.out.println("Barber finished working");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //---------------------------------------------------------//
            do {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while (changing.availablePermits() > 0);
            changing.release();
            armchair.release();
            //---------------------------------------------------------//
        }
    }
}
