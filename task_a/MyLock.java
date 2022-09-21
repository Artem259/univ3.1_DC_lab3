import jdk.jshell.spi.ExecutionControl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {
    private final AtomicBoolean isFree;

    public MyLock() {
        isFree = new AtomicBoolean(true); // free by default
    }

    public void lock() {
        synchronized (this) {
            while (!isFree.get()) {
                Thread.onSpinWait();
            }
            isFree.set(false);
        }
    }

    @Override
    public void unlock() {
        isFree.set(true);
    }

    @Override
    public void lockInterruptibly() {
        try {
            throw new ExecutionControl.NotImplementedException("MyLock.lockInterruptibly()");
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryLock() {
        try {
            throw new ExecutionControl.NotImplementedException("MyLock.tryLock()");
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        try {
            throw new ExecutionControl.NotImplementedException("MyLock.tryLock(long time, TimeUnit unit)");
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Condition newCondition() {
        try {
            throw new ExecutionControl.NotImplementedException("MyLock.newCondition()");
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
