package main.java;

public class Stuffing {
    public enum StuffingE {
        BASE,
        DOWN,
        FOAM
    }

    //stuffing polyStuffing;’SER316 TASK 2 SPOTBUGS FIX
    int price;

    /**
     * Here is the missing comment.
     */

    public Stuffing(StuffingE interiorStuffing) {

        switch (interiorStuffing) {
            case BASE:
                //this.polyStuffing = stuffing.BASE;’SER316 TASK 2 SPOTBUGS FIX
                this.price = 30;
                break;
            case DOWN:
                //this.polyStuffing = stuffing.DOWN;’SER316 TASK 2 SPOTBUGS FIX
                this.price = 40;
                break;
            case FOAM:
                //this.polyStuffing = stuffing.FOAM;’SER316 TASK 2 SPOTBUGS FIX
                this.price = 50;
                break;
            default:
                break;
        }
    }
}
