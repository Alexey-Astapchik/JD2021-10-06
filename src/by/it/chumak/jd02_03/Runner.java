package by.it.chumak.jd02_03;

import by.it.chumak.jd02_03.entity.Manager;
import by.it.chumak.jd02_03.entity.Queue;
import by.it.chumak.jd02_03.entity.Store;
import by.it.chumak.jd02_03.service.StoreReportPrinter;
import by.it.chumak.jd02_03.service.StoreWorker;

public class Runner {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Manager manager = new Manager(100, 5);
            Queue queue = new Queue(30);
            Store store = new Store(manager, queue, 50, 20);
            StoreWorker storeWorker = new StoreWorker(store);
            StoreReportPrinter storeReportPrinter = new StoreReportPrinter();
            storeReportPrinter.printHeadTable();
            storeWorker.start();
        }
    }
}
