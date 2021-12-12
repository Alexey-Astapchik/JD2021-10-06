package by.it.ithoitan.jd02_02;


import by.it.ithoitan.jd02_02.entity.Manager;
import by.it.ithoitan.jd02_02.entity.Queue;
import by.it.ithoitan.jd02_02.entity.Store;

public class Runner {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Manager manager = new Manager(100);
            Queue queue = new Queue();
            Store store = new Store(manager, queue);
            StoreWorker storeWorker = new StoreWorker(store);
            storeWorker.start();
        }
    }
}
