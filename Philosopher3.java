// Question 1.1
// Amendement 1.1.3
// Classic Dining Philosophers Problem. 

import java.util.concurrent.locks.Lock;

public class Philosopher3 implements Runnable{
    private Lock leftChopstick, rightChopstick;
    private int id;

    public Philosopher3(int id, Lock leftChopstick, Lock rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run(){
        while (true) {
            // Philosopher can take four actions:
            think();

            // Each philosopher's pickUp is so that the first chopstick to be picked up is common with another philosopher
            // Thus, the deadlock is avoided
            if(id % 2 == 0)
                pickUp();
            else
                pickUp2();
            eat();
            putDown();
        }
    }

    private void think() {
        System.out.println("Philosopher Number " + id + " is thinking");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pickUp() {
        // first lets wait until the left chopstick is available
        while(!leftChopstick.tryLock()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // at this point we have the left chopstick, now lets wait until the right chopstick is available
        while(!rightChopstick.tryLock()) {
            // if we made it in here, the right chopstick is not available, so lets put down the left chopstick
            leftChopstick.unlock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // now lets try to get the left chopstick again
            while(!leftChopstick.tryLock()) {
            }
        }
        // at this point we have both chopsticks
        // This will prevent starvation as the philosopher will not hold onto the chopsticks while waiting
    }

    private void pickUp2() {
        // first lets wait until the right chopstick is available
        while(!rightChopstick.tryLock()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // at this point we have the right chopstick, now lets wait until the left chopstick is available
        while(!leftChopstick.tryLock()) {
            // if we made it in here, the left chopstick is not available, so lets put down the right chopstick
            rightChopstick.unlock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // now lets try to get the right chopstick again
            while(!rightChopstick.tryLock()) {
            }
        }
        // at this point we have both chopsticks
        // This will prevent starvation as the philosopher will not hold onto the chopsticks while waiting
    }

    private void eat() {
        System.out.println("Philosopher Number " + id + " is eating");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void putDown() {
        leftChopstick.unlock();
        rightChopstick.unlock();
    }
}
