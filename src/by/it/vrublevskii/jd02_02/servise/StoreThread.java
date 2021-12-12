package by.it.vrublevskii.jd02_02.servise;


import by.it.vrublevskii.jd02_02.entity.Cashier;
import by.it.vrublevskii.jd02_02.entity.Customer;
import by.it.vrublevskii.jd02_02.entity.PriceListRepo;
import by.it.vrublevskii.jd02_02.entity.Store;
import by.it.vrublevskii.jd02_02.helper.CustomerGenerator;
import by.it.vrublevskii.jd02_02.helper.RandomGenerator;
import by.it.vrublevskii.jd02_02.helper.Timeout;

import java.util.ArrayList;

public class StoreThread extends Thread {

    private final Store store;

    public StoreThread(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        PriceListRepo priceListRepo = new PriceListRepo();
        ArrayList<Thread> threads = new ArrayList<>();
        System.out.println("Store is opened");
        int customerCounter = 0;
        for (int numberCashier = 1; numberCashier <=2; numberCashier++) {
            Cashier cashier = new Cashier(numberCashier);
            CashierThread cashierThread = new CashierThread(store, cashier);
            Thread thread = new Thread(cashierThread);
            threads.add(thread);
            thread.start();
        }
        while (store.getManager().isStoreOpen()) {
            int customersInSecond = RandomGenerator.get(2);
            for (int i = 0; store.getManager().isStoreOpen() && i < customersInSecond; i++) {
                Customer customer = CustomerGenerator.get(++customerCounter);
                CustomerThread customerThread = new CustomerThread(store, customer, priceListRepo);
                threads.add(customerThread);
                customerThread.start();
            }
            Timeout.sleep(1000);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Store is closed");
    }
}
