package main.java;

import java.lang.Math;

import java.util.LinkedList;

import main.java.Stuffing.StuffingE;

public class Bear implements Comparable<Bear> {
    public Casing casing;
    public Stuffing stuff;
    public Embroidery ink; 
    public LinkedList<NoiseMaker> noisemakers; // accessory
    public LinkedList<Clothing> clothing; // accessory
    double price;
    // bear has a shell (requ)
    // bear has stuffing (req)
    // bear has a tattoo/emroider or not (opt)
    // bear has a noisemaker (opt)

    /**
     * Here I am.
     */

    public Bear() {
        this.casing = new Casing();
        this.stuff = new Stuffing(StuffingE.BASE);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }

    /**
     * There I am.
     */

    public Bear(StuffingE stuff) {
        this.casing = new Casing();
        this.stuff = new Stuffing(stuff);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }

    /**
     * There I go.
     */

    public void setPrice(double incomingPrice) {
        this.price = incomingPrice;
    }

    /**
     * There I was.
     */

    public boolean addNoise(NoiseMaker noise) {
        if (this.noisemakers.size() >= 5) {
            return false;
        } else {
            for (NoiseMaker noisemaker: noisemakers) {
                if (noise.spot == noisemaker.spot) {
                    return false;
                }
            }
            noisemakers.add(noise);
            return true;
        }
    }

    /**
     * Here I am.
     */

    @Override
    public int compareTo(Bear bear) { //’SER316 TASK 2 SPOTBUGS FIX
        return new Double(this.price).compareTo(bear.price);
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
        Bear other = (Bear) obj;
        if (Math.abs(price - other.price) < .0000001) {
            return false;
        }

        return true;
    }
}