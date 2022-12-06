package ReentrantLook;
//task-8

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomReentrantLock implements CustomLock{
    private static final Logger log = Logger.getLogger(CustomReentrantLock.class.getName());
    private int lockCount;
    private long currentHoldingThreadID;

    public CustomReentrantLock() {
        this.lockCount = 0;
    }

    public synchronized void lock() {
        if(lockCount == 0){
            lockCount++;
            currentHoldingThreadID = Thread.currentThread().getId();
        } else if(lockCount > 0 &&
                currentHoldingThreadID == Thread.currentThread().getId()){
            lockCount++;
        } else {
            while (currentHoldingThreadID!= Thread.currentThread().getId()){
                try {
                    this.wait();
                    lockCount++;
                    currentHoldingThreadID = Thread.currentThread().getId();
                } catch (InterruptedException e) {
                    log.log(Level.SEVERE, "Exception: ", e);
                }
            }
        }
    }

    public synchronized void unlock() {
        if(lockCount == 0){
            throw new IllegalMonitorStateException();
        }
        lockCount--;
        if(lockCount == 0){
            notify();
        }
    }

    public boolean tryLock() {
        if(lockCount == 0){
            lock();
            return true;
        }
        return false;
    }
}
