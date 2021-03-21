package main.java;

public class Casing {
    double priceModifier;

    //String description;’SER316 TASK 2 SPOTBUGS FIX

    public Casing() {
        this(1.00, "Default outer shell");
    }

    public Casing(double price, String descr) {
        this.priceModifier = price;
        //this.description = descr;’SER316 TASK 2 SPOTBUGS FIX
    }
}
