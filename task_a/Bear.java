public class Bear implements Runnable{
    private final Container container;
    private final MyLock lock;

    public Bear(Container container, MyLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while(!container.isFull())
                {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            lock.lock(); // --->
            System.out.println("Bear is eating...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            container.setEmpty();
            System.out.println("Bear finished eating: 0/0");
            lock.unlock();  // <---
        }
    }
}
