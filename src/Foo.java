import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    static Lock lock = new ReentrantLock();


    public boolean finished1 = false;
    public boolean finished2 = false;
    public boolean finished3 = false;


    public void first(Runnable r) {
        try {
            lock.lock();
            while (!finished1) {
                synchronized (Foo.class) {
                    System.out.print("first");
                    finished1 = true;
                    second(r);
                }
            }
        } finally {
            lock.unlock();

        }
    }

    public void second(Runnable r) {
        try {

            lock.lock();
            while (!finished2 && finished1) {
                synchronized (Foo.class) {
                    System.out.print("second");
                    finished2 = true;
                    third(r);
                    lock.unlock();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable r) {
        try {

            lock.lock();
            while (!finished3 && finished2 && finished1) {
                synchronized (Foo.class) {
                    System.out.print("third");
                    finished3 = true;
                }
            }
        } finally {
            lock.unlock();
        }
    }

}





