package by.it.vrublevskii.jd02_02.old.entity;

public class Store extends Thread {

    private final Manager manager;
    private final Queue queue;

    public Store(Manager manager, Queue queue) {
        this.manager = manager;
        this.queue = queue;
    }

    public Manager getManager() {
        return manager;
    }

    public Queue getQueue() {
        return queue;
    }
}
