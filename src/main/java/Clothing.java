package main.java;

public class Clothing implements Comparable<Clothing> {
    public double price;
    //public String Description;’SER316 TASK 2 SPOTBUGS FIX

    public Clothing() {
        this(4.00, "Generic Offtrack Separate");

    }

    public Clothing(double price, String descr) {
        this.price = price;
        //this.Description = descr;’SER316 TASK 2 SPOTBUGS FIX
    }
    /**
     * Gone.
     */

    public int compareTo(Clothing clothes) {
        //if (clothes.price < this.price) {
        //    return 1;
        //} else if (clothes.price > this.price) {
        //    return -1;
        //} else {
        //    return 0;
        //}
        return new Double(this.price).compareTo(clothes.price); //’SER316 TASK 2 SPOTBUGS FIX
    }

    @Override
    public int hashCode() { //’SER316 TASK 2 SPOTBUGS FIX
        final int prime = 31;
        int result = 1;
        result = prime * result + 1237;
        result = prime * result + (int) price;
        return result;
    }

    @Override
    public boolean equals(Object obj) { //’SER316 TASK 2 SPOTBUGS FIX
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Clothing other = (Clothing) obj;
        if (price != other.price) {
            return false;
        }
        return true;
    }
}
