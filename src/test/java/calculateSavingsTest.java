import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.lang.System;
import main.java.BearWorkshop;

import static org.junit.Assert.*;
//@RunWith(Parameterized.class)
public class calculateSavingsTest {
    //private Class<BearWorkshop> classUnderTest;

    @SuppressWarnings("unchecked")
    public calculateSavingsTest(/*Object classUnderTest*/) {
        //this.classUnderTest = (Class<BearWorkshop>) classUnderTest;
    }

    /*@Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = {
                {BearWorkshop1.class},
                {BearWorkshop2.class},
                {BearWorkshop3.class},
                {BearWorkshop4.class},
                {BearWorkshop5.class}

        };
        return Arrays.asList(classes);
    }

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        Constructor<BearWorkshop> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }
*/
    private BearWorkshop createBearWorkshop(String name) throws Exception{
        return new BearWorkshop(name);
    }

    BearWorkshop oneBear;
    Double oneBearExpected;

    BearWorkshop threeBears;
    Double threeBearsExpected;

    BearWorkshop twoBears;
    Double twoBearsExpected;

    @Before
    public void setUp() throws Exception {

        // One Bear base stuffing, no saving expected
        oneBear = createBearWorkshop("NY");
        oneBear.addBear(new Bear(Stuffing.stuffing.BASE));
        oneBearExpected = 0.00; // no savings
        
        // Three Bears expected to not pay for cheapest one
        threeBears = createBearWorkshop("AZ");
        threeBears.addBear(new Bear(Stuffing.stuffing.BASE));
        threeBears.addBear(new Bear(Stuffing.stuffing.DOWN));
        threeBears.addBear(new Bear(Stuffing.stuffing.FOAM));
        threeBearsExpected = 30.00;
        
        
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void oneBearNoSavings() {
        Double ans = oneBear.calculateSavings();
        assertEquals(oneBearExpected, ans);
    }


    // sample test
    @Test
    public void threeBearsSaveOnCheapest() {
        Double ans = threeBears.calculateSavings();
        assertEquals(threeBearsExpected, ans);
    }

    // sample test
    @Test
    public void zeroBearsExpectNoSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        
        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBearTest3clothingsExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        
        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test
    public void oneBear2clothingsNoSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));

        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear3clothingsDifferentPricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));

        Double expected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear5clothingsSamePricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));

        Double expected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear5clothingsDifferentPricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));//5
        customBear.clothing.add(new Clothing(6, "Shoes"));//6
        customBear.clothing.add(new Clothing(7, "Shirt"));//7
        customBear.clothing.add(new Clothing(8, "Jacket"));//8

        Double expected = 4.0;
        Double ans = bears.calculateSavings();
        //System.out.println("" + ans);
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear6clothingsDifferentPricesExpectMoreSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        customBear.clothing.add(new Clothing(7, "Shirt"));
        customBear.clothing.add(new Clothing(8, "Jacket"));
        customBear.clothing.add(new Clothing(10, "Cape"));

        Double expected = 9.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear6clothingsSamePricesExpectMoreSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Cape"));

        Double expected = 8.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear7clothingsSamePricesExpectMoreSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Cape"));
        customBear.clothing.add(new Clothing(4, "Jeans"));

        Double expected = 8.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear7clothingsDifferentPricesExpectMoreSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        customBear.clothing.add(new Clothing(7, "Shirt"));
        customBear.clothing.add(new Clothing(8, "Jacket"));
        customBear.clothing.add(new Clothing(9, "Cape"));
        customBear.clothing.add(new Clothing(10, "Jeans"));

        Double expected = 9.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear1noiseAccessoryNoSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear9noiseAccessoriesNoSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear10noiseAccessoriesSamePricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 13.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear10noiseAccessoriesDifferentPricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 0));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 1));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 2));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 3));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 11.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear11noiseAccessoriesDifferentPricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 0));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 1));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 2));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 3));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 12.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear11noiseAccessoriesSamePricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 14.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear20noiseAccessoriesDifferentPricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 0));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 1));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 2));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 3));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 21.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear20noiseAccessoriesSamePricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 23.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear21noiseAccessoriesSamePricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 24.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear21noiseAccessoriesDifferentPricesExpectSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 0));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 1));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 2));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 3));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 22.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear10clothesExpectNo10PercentSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));

        Double expected = 12.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear12clothesExpectNo10PercentSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));

        Double expected = 16.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear14clothesExpect10PercentSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));//
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));

        Double expected = 16.0 + 7.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void oneBear3clothes8accessoriesExpectClothesAndAccessoriesSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 4.0 + 11.8;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }

    @Test
    public void twoBearsExpectNoSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);

        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears2clothingsExpectNoSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));

        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears3clothingsExpectClothesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));

        Double expected = 8.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears5clothingsExpectClothesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);

        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));

        Double expected = 8.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears6clothingsExpectMoreClothesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));

        Double expected = 16.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears7clothingsExpectMoreClothesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));

        Double expected = 16.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears1accessoryExpectNoSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears9accessoriesExpectNoSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears10accessoriesExpectAccessoriesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 26.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears11accessoriesExpectAccessoriesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 28.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears20accessoriesExpectAccessoriesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 46.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears21accessoriesExpectAccessoriesSavingsOnly()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 48.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears10clothesExpectNo10PercentSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        customBear.clothing.add(new Clothing(4, "Jacket"));
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear);
        bears.addBear(customBear1);

        Double expected = 24.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears12clothesExpectNo10PercentSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear);
        bears.addBear(customBear1);

        Double expected = 32.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears14clothesExpect10PercentSavingsAndClothesSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));//
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear);
        bears.addBear(customBear1);

        Double expected = 32.0 + 14;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void twoBears3clothes8accessoriesExpectClothesAndAccessoriesSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        bears.addBear(customBear1);

        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));

        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 8 + 23.6;//was 8
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }

    @Test
    public void threeBearsTwoClothesBearSavingsExpected()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear2);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));

        Double expected = 30.0 + 8;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears3ClothesExpectClothingAndBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear2);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));

        Double expected = 30.0 + 12 + 8;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears5ClothesExpectClothingAndBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear2);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));

        Double expected = 30.0 + 12 + 16;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears6ClothesExpectMoreClothingAndSameBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear2);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));

        Double expected = 30.0 + 24 + 16;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears7ClothesExpectMoreClothingAndSameBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear1);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        bears.addBear(customBear2);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Hat"));

        Double expected = 30.0 + 24 + 20;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears1accessoryExpectBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear2);
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 40.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears9accessoriesExpectBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear2);
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 120.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears10accessoriesExpect10PercentAndBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear2);
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 156.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears11accessoriesExpect10PercentAndBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear2);
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 168.0;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears20accessoriesExpect10PercentAndBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear2);
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 230.0 + 46;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears21AccessoriesExpect10PercentAndBearSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear1);
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        bears.addBear(customBear2);
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 240.0 + 48;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears10clothesExpectNo10PercentSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        customBear.clothing.add(new Clothing(4, "Jacket"));
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Sunglasses"));
        customBear2.clothing.add(new Clothing(4, "Shoes"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Sunglasses"));
        customBear2.clothing.add(new Clothing(4, "Shoes"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);

        Double expected = 30.0 + 36 + 28;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears12clothesExpectNo10PercentSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Sunglasses"));
        customBear2.clothing.add(new Clothing(4, "Shoes"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Sunglasses"));
        customBear2.clothing.add(new Clothing(4, "Shoes"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);

        Double expected = 30.0 + 48 + 32;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears14clothesExpect10PercentSaving()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//
        customBear.clothing.add(new Clothing(4, "Shirt"));
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Hat"));//
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Shirt"));//
        customBear.clothing.add(new Clothing(4, "Jacket"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));//
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        customBear1.clothing.add(new Clothing(4, "Hat"));//
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));//
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        customBear1.clothing.add(new Clothing(4, "Jacket"));//
        customBear1.clothing.add(new Clothing(4, "Jacket"));
        customBear1.clothing.add(new Clothing(4, "Shirt"));
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Sunglasses"));
        customBear2.clothing.add(new Clothing(4, "Shoes"));//
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        customBear2.clothing.add(new Clothing(4, "Hat"));//
        customBear2.clothing.add(new Clothing(4, "Sunglasses"));
        customBear2.clothing.add(new Clothing(4, "Shoes"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));//
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        customBear2.clothing.add(new Clothing(4, "Jacket"));//
        customBear2.clothing.add(new Clothing(4, "Jacket"));
        customBear2.clothing.add(new Clothing(4, "Shirt"));
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);

        Double expected = 30.0 + 48 + 40 + 14;//14 dollars the result of the 10 percent discount
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
    @Test
    public void threeBears3clothes8accessoriesExpectClothesAndAccessoriesSavings()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));//

        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        bears.addBear(customBear1);

        customBear1.clothing.add(new Clothing(4, "Hat"));
        customBear1.clothing.add(new Clothing(4, "Sunglasses"));
        customBear1.clothing.add(new Clothing(4, "Shoes"));//

        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear1.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        bears.addBear(customBear2);

        customBear2.clothing.add(new Clothing(4, "Hat"));
        customBear2.clothing.add(new Clothing(4, "Sunglasses"));
        customBear2.clothing.add(new Clothing(4, "Shoes"));//

        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));
        customBear2.noisemakers.add(new NoiseMaker("Words of Encouragement", "You're the best!", 4));

        Double expected = 118.0 + 12 + 23.6;
        Double ans = bears.calculateSavings();
        assertEquals(expected, ans, 0.005);
    }
}