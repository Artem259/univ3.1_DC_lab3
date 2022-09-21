public class Application {
    private final int bees;
    private final int containerMaxCapacity;

    public Application(int bees, int containerMaxCapacity) {
        this.bees = bees;
        this.containerMaxCapacity = containerMaxCapacity;
    }

    public void start() {
        MyLock lock = new MyLock();
        Container container = new Container(containerMaxCapacity);
        Bear bear = new Bear(container, lock);

        new Thread(bear).start();
        for(int i=0; i<bees; i++) {
            new Thread(new Bee(i+1, container, bear, lock)).start();
        }
    }

    public static void main(String[] args) {
        new Application(5,30).start();
    }
}
