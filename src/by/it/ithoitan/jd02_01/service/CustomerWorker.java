package by.it.ithoitan.jd02_01.service;

import by.it.ithoitan.jd02_01.entity.Customer;
import by.it.ithoitan.jd02_01.entity.Good;
import by.it.ithoitan.jd02_01.helper.RandomGenerator;
import by.it.ithoitan.jd02_01.helper.TimeOut;

public class CustomerWorker extends Thread implements CustomerAction {
    private final Customer customer;

    @Override
    public void run() {
        enteredStore();
        Good good = chooseGood();
        System.out.println(customer + " choose " + good);
        goOut();
    }

    public CustomerWorker(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void enteredStore() {
        System.out.println(customer + " entered to Shop");

    }

    @Override
    public Good chooseGood() {
        System.out.println(customer + " started to choose good");
        int timeout = RandomGenerator.get(500,2000);
        TimeOut.sleep(timeout);
        Good good = new Good();
        System.out.println(customer + " finished to choose good");
        return good;
    }

    @Override
    public void goOut() {
        System.out.println(customer + " leaved the Shop");

    }
}
