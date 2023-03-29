import java.util.concurrent.Semaphore;


public class Foo {
    private final Semaphore s12 = new Semaphore(0);
    private final Semaphore s23 = new Semaphore(0);


    public void first(Runnable r) {
        System.out.print("first");
        s12.release();
    }

    public void second(Runnable r) {
        try {
            s12.acquire();
            System.out.print("second");
            s23.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void third(Runnable r) {
        try {
            s23.acquire();
            System.out.print("third");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}





