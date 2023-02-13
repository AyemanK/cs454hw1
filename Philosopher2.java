// Question 1.1
// Amendement 1.1.2
// Classic Dining Philosophers Problem. 

import java.util.concurrent.locks.Lock;

public class Philosopher2 implements Runnable{
    private Lock leftChopstick, rightChopstick;
    private int id;

    public Philosopher2(int id, Lock leftChopstick, Lock rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run(){
        while (true) {
            // Philosopher can take four actions:
            think();

            // Each philosopher's pickUp is so that the first chopstick to be picked up is common with another philosopher (specific order)
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
        leftChopstick.lock();
        rightChopstick.lock();
    }

    private void pickUp2() {
        rightChopstick.lock();
        leftChopstick.lock();
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
