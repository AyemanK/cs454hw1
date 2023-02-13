// Classic philosopher problem
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
// Program has been amended to use n, the number of philosophers, as a parameter
// Philosopher3 should be able to handle any number of philosophers

public class Table {
    public static void main(String[] args) {
        int n = 5;
        Lock[] chopsticks = new ReentrantLock[n];
        for (int i = 0; i < n; i++) {
            // create a chopstick for each philosopher (1:1)
            chopsticks[i] = new ReentrantLock();
        }
        Philosopher[] philosophers = new Philosopher[n];
        for (int i = 0; i < n; i++) {
            // give each philosopher a left and right chopstick
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % n]);
            // start each philosopher
            new Thread(philosophers[i]).start();
        }
    }
}
