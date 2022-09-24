import java.util.concurrent.Semaphore;

public class Application {
    private final int visitorN;

    public Application(int visitorN) {
        this.visitorN = visitorN;
    }

    public void start() {
        Semaphore armchair = new Semaphore(1);
        Semaphore work = new Semaphore(1);
        Semaphore changing = new Semaphore(1);

        new Thread(new Barber(visitorN, armchair, work, changing)).start();
        for(int i = 0; i < visitorN; i++) {
            new Thread(new Visitor(i+1, armchair, work, changing)).start();
        }
    }

    public static void main(String[] args) {
        new Application(3).start();
    }
}
