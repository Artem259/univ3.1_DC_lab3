import java.util.Random;

public class Bee implements Runnable{
    private final Integer beeNumber;
    private final Container container;
    private final Bear bear;
    private final MyLock lock;

    public Bee(Integer beeNumber, Container container, Bear bear, MyLock lock) {
        this.beeNumber = beeNumber;
        this.container = container;
        this.bear = bear;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock(); // --->
            if(!container.isFull()) {
                container.putOne();
                System.out.println("Bee " + beeNumber + " brings honey: " + container.getCapacity() + "/" + container.getMaxCapacity());
                if(container.isFull()) {
                    synchronized (bear) {
                        bear.notify();
                    }
                }
            }
            lock.unlock();  // <---

            try {
                Random rand = new Random();
                Thread.sleep(2000 + rand.nextInt(3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
