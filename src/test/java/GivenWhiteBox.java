import main.java.*;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import main.java.BearWorkshop;


public class GivenWhiteBox {
    BearWorkshop oneBear;

    @Before
    public void setUp() throws Exception {
    }

    /*
    The checkout method in the BearWorkshop class appears to calculate the cost of the single bear correctly, at least
    for one bear. The price of the bear, including discounts and tax, should be 32.1. I simply changed the assert
    statement to check for the answer 32.1.
    */
    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop(/*"AZ"*/);
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(32.1, ans, 0.005);
    }
    /*
    This test will test a getCost() sequence that completes the one existing Node Coverage Sequence and completes one Edge Coverage Sequence out of two.
    This sequence is {37, 38, 39, 40, 42, 43, 44, 45, 46, 49, 42, 43, 44, 46, 47, 48, 49, 42, 51, 52, 53, 51, 55, 56, 57, 59, 60, 62, 63}
    */
    @Test
    public void getCostSequence1(){
        oneBear = new BearWorkshop(/*"AZ"*/);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.ink = new Embroidery("LOVE");
        Double ans = oneBear.getCost(customBear);
        //System.out.println(ans);
        assertEquals(55, ans, 0.005);
    }
    /*
    This test will test the second Edge Coverage Sequence for getCost().
    This sequence is {37, 38, 39, 40, 42, 43, 44, 45, 46, 49, 42, 43, 44, 46, 47, 48, 49, 42, 51, 52, 53, 51, 55, 59, 60, 62, 63}
    */
    @Test
    public void getCostSequence2(){
        oneBear = new BearWorkshop(/*"AZ"*/);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        //customBear.ink = new Embroidery("LOVE");
        Double ans = oneBear.getCost(customBear);
        //System.out.println(ans);
        assertEquals(51, ans, 0.005);
    }
    /*
    This test will test the single Node Coverage Sequence for getRawCost(). This sequence also helps cover one of the
    Edge Coverage Sequences.
    */
    @Test
    public void getRawCostSequence1(){
        oneBear = new BearWorkshop(/*"AZ"*/);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.ink = new Embroidery("LOVE");
        Double ans = oneBear.getRawCost(customBear);
        //System.out.println(ans);
        assertEquals(59, ans, 0.005);
    }
    /*
    This test tests the other Edge Coverage Sequence of getRawCost().
    */
    @Test
    public void getRawCostSequence2(){
        oneBear = new BearWorkshop(/*"AZ"*/);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        //customBear.ink = new Embroidery("LOVE");
        Double ans = oneBear.getRawCost(customBear);
        //System.out.println(ans);
        assertEquals(55, ans, 0.005);
    }

    @Test
    public void addBearSequence1(){
        oneBear = new BearWorkshop(/*"AZ"*/);
        boolean added = oneBear.addBear(new Bear(Stuffing.StuffingE.BASE));
        assertTrue(added);
    }

    @Test
    public void removeBearSequence1(){
        oneBear = new BearWorkshop(/*"AZ"*/);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        boolean removed = oneBear.removeBear(customBear);
        assertTrue(removed);
    }

    @Test
    public void removeBearSequence2(){
        oneBear = new BearWorkshop(/*"AZ"*/);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        oneBear.removeBear(customBear);
        boolean removed = oneBear.removeBear(customBear);
        assertFalse(removed);
    }

    @Test
    public void checkoutSequence2(){
        oneBear = new BearWorkshop(13, 13, "AZ", 1.07);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        Double ans = oneBear.checkout();
        assertEquals(-1, ans, 0.005);
    }

    @Test
    public void checkoutSequence3(){
        oneBear = new BearWorkshop(13, 21, "AZ", 1.07);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        Bear customBear1 = new Bear(Stuffing.StuffingE.BASE);
        Bear customBear2 = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        oneBear.addBear(customBear1);
        oneBear.addBear(customBear2);
        Double ans = oneBear.checkout();
        //System.out.println(ans);
        assertEquals(64.2, ans, 0.005);
    }

    @Test
    public void checkoutSequence4(){
        oneBear = new BearWorkshop(18, 21, "AZ", 1.07);
        Bear customBear = new Bear(Stuffing.StuffingE.BASE);
        Bear customBear1 = new Bear(Stuffing.StuffingE.BASE);
        Bear customBear2 = new Bear(Stuffing.StuffingE.BASE);
        oneBear.addBear(customBear);
        oneBear.addBear(customBear1);
        oneBear.addBear(customBear2);
        Double ans = oneBear.checkout();
        //System.out.println(ans);
        assertEquals(64.2, ans, 0.005);
    }
}
