// Question 1.1
// Classic Dining Philosophers Problem. 

import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{
    private Lock leftChopstick, rightChopstick;
    private int id;

    public Philosopher(int id, Lock leftChopstick, Lock rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run(){
        while (true) {
            // Philosopher can take four actions:
            think();
            pickUp();
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
