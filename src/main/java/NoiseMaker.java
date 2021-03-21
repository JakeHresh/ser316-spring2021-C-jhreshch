package main.java;

public class NoiseMaker {
    public double price;
    //String label;’SER316 TASK 2 SPOTBUGS FIX
    //String recording;’SER316 TASK 2 SPOTBUGS FIX

    Location spot;

    public NoiseMaker() {
        this("Default Noise", "I wuv you", Location.CENTERBODY);
    }
    /**
     * There once was a man from Peru.
     */

    public NoiseMaker(String label, String recording,
                      Location location) {
        //this.label = label;’SER316 TASK 2 SPOTBUGS FIX
        //this.recording = recording;’SER316 TASK 2 SPOTBUGS FIX
        this.spot = location;
        switch (location) {
            case CENTERBODY:
                this.price = 10;
                break;
            default:
                this.price = 5;
                break;
        }
    }
    /**
     * There once was a man from Peru.
     */

    public NoiseMaker(String label, String recording,
                      int location) {
        //this.label = label;’SER316 TASK 2 SPOTBUGS FIX
        //this.recording = recording;’SER316 TASK 2 SPOTBUGS FIX
        /*this.spot = location;
        switch (location) {
            case CENTERBODY:
                this.price = 10;
                break;
            default:
                this.price = 5;
                break;
        }*/
        if (location == 4) {
            this.spot = Location.CENTERBODY;
            this.price = 10;
        } else if (location == 3) {
            this.spot = Location.LEFT_FOOT;
            this.price = 5;
        } else if (location == 2) {
            this.spot = Location.RIGHT_FOOT;
            this.price = 5;
        } else if (location == 1) {
            this.spot = Location.LEFT_HAND;
            this.price = 5;
        } else {
            this.spot = Location.RIGHT_HAND;
            this.price = 5;
        }
    }


    public enum Location {
        RIGHT_HAND, LEFT_HAND, RIGHT_FOOT, LEFT_FOOT, CENTERBODY
    }
}


