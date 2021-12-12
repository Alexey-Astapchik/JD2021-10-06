package by.it.chumak.jd02_03.entity;

import by.it.chumak.jd02_03.service.PriceListRepo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class Store {

    private final Manager MANAGER;
    private final Queue QUEUE;
    private final BlockingQueue<Integer> CART_QUEUE;
    private final PriceListRepo STORE_PRICE_LIST;
    private final Semaphore SEMAPHORE;
    private final AtomicLong totalProfit;

    public Store(Manager manager, Queue queue, int storeCartsLimit, int maxCustomersInStoreHall) {
        this.MANAGER = manager;
        this.QUEUE = queue;
        this.CART_QUEUE = new ArrayBlockingQueue<>(storeCartsLimit);
        this.STORE_PRICE_LIST = new PriceListRepo();
        this.SEMAPHORE = new Semaphore(maxCustomersInStoreHall);
        this.totalProfit = new AtomicLong(0);
    }

    public double getTotalProfit() {
        return totalProfit.get();
    }

    public void addToTotalProfit(long totalProfit) {
        this.totalProfit.getAndAdd(totalProfit);
    }

    public Manager getManager() {
        return this.MANAGER;
    }

    public BlockingQueue<Integer> getCART_QUEUE() {
        return CART_QUEUE;
    }

    public Queue getQueue() {
        return this.QUEUE;
    }

    public PriceListRepo getStorePriceList() {
        return this.STORE_PRICE_LIST;
    }

    public Semaphore getSEMAPHORE() {
        return SEMAPHORE;
    }
}
