import java.util.Objects;

public class Container {
    private int capacity;
    private final int maxCapacity;

    public Container(Integer maxCapacity) {
        capacity = 0;
        this.maxCapacity = maxCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Boolean isFull() {
        return Objects.equals(capacity, maxCapacity);
    }

    public void putOne() throws RuntimeException{
        if(capacity == maxCapacity)
            throw new RuntimeException();
        capacity++;
    }

    public void setEmpty() {
        capacity = 0;
    }
}
