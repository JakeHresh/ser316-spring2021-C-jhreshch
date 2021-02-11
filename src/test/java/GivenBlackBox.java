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

//import main.java.BearWorkshop;

import static org.junit.Assert.*;

/***
 * This class provides a framework to implement black box tests for various
 * implementations of the BearWorkshop Class. The BearWorkshop is having a
 * blowout sale and is offering the following savings.
 *
 * Bears are Buy 2 bears, get 1 free. It is 10% off the cost of a bear when a
 * single bear has 10 or more accessories (Note that embroidery, stuffing, and
 * the material used for the bear casing is not considered an accessory).
 * Additionally, clothes are buy 2, get one free on each bear. Only non free clothes count 
 * towards the 10 accessories or more savings part. 
 */
@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<BearWorkshop> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
        this.classUnderTest = (Class<BearWorkshop>) classUnderTest;
    }

    @Parameters
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

    // sample test

    /**
     * Test examines a BearFactory with 1 simple bear in it. The bear costs $30
     * and there are no savings.
     */
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
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        customBear.clothing.add(new Clothing(7, "Shirt"));
        customBear.clothing.add(new Clothing(8, "Jacket"));

        Double expected = 4.0;
        Double ans = bears.calculateSavings();
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
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }
    @Test
    public void oneBear()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    }

    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }
    @Test
    public void twoBears()
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
    }

    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }
    @Test
    public void threeBears()
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
        bears.addBear(customBear1);
        bears.addBear(customBear2);
    }

    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }
    @Test
    public void fourBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
    }

    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }
    @Test
    public void fiveBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
    }

    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }
    @Test
    public void sixBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
    }

    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
    @Test
    public void sevenBears()
    {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear5 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear6 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);
        bears.addBear(customBear5);
        bears.addBear(customBear6);
    }
}
