package by.it.vrublevskii.jd02_01.old.entity;

public class Good {

    private final String name;
    private final double price;

    public Good() {
        this("noName", 12);
    }

    public Good(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Good " + name + '\'' + ", price=" + price;
    }
}
