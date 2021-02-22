package main.java;

import java.util.*;

// This class provides functionality for a BearWorkshop class.
public class BearWorkshop implements BearWorkshopInterface{
    // Workshop has a collection of bears
    // Workshop has a customer
    Customer customer;
    List<Bear> BearCart;

    /**
     * Default constructor for the Bear Workshop
     */
    public BearWorkshop() {
        this("AZ");
    }

    /**
     * This is a parameterized ctor for a BearWorkshop
     * @param state customer is in
     */
    public BearWorkshop(String state) {
        BearCart = new LinkedList<>();
        customer = new Customer(state);
    }
    /*
    This constructor is included to test different branches in the checkout method.
    */
    public BearWorkshop(int age1, int age2, String state){
        BearCart = new LinkedList<>();
        customer = new Customer(age1, state, new Customer(age2, state, new Customer(state)));
    }

    /**
     * This is a convenience method to calculate the cost of bears in the
     * shopping cart for a customer in the BearFactory. This methods calculates
     * the overall price of ONE bear, with all its add-ons and should include the discounts. 
     * @param bear to get cost of
     * @return double representation of bear cost
     * TODO: test me and fix me in assignment 3
     */
    @Override
    public double getCost(Bear bear) {
        Collections.sort(bear.clothing);
        int numFree = bear.clothing.size() / 3;
        ArrayList<Clothing> freeClothes = new ArrayList<>();

        for (int i = 0; i < bear.clothing.size(); i++) {
            Clothing clothes = bear.clothing.get(i);
            if (i < numFree) {
                freeClothes.add(clothes);
            } else {
                bear.price += clothes.price;
            }
        }

        for (NoiseMaker noise: bear.noisemakers) {
            bear.price += noise.price;
        }

        if (bear.ink != null) {
            bear.price += bear.ink.price;
        }

        bear.price += bear.stuff.price;
        bear.price *= bear.casing.priceModifier;

        return bear.price;
    }

    // Function to get the raw cost of a bear without any discounts
   // TODO: test me and fix me in assignment 3
    public double getRawCost(Bear bear) {
        for (int i = 0; i < bear.clothing.size(); i++) {
            Clothing clothes = bear.clothing.get(i);
            bear.price += clothes.price;

        }

        for (NoiseMaker noise: bear.noisemakers) {
            bear.price += noise.price;
        }

        if (bear.ink != null) {
            bear.price += bear.ink.price;
        }

        bear.price += bear.stuff.price;
        bear.price *= bear.casing.priceModifier;

        double bearPrice = bear.price;
        bear.price = 0;
        return bearPrice;
    }

    /**
     * Utility method to calculate tax for purchases by customers in different
     * states.
     * You can assume these taxes are what we want, so they are not wrong.
     * The taxes are meant to be used in this way: costInShoppingCart * tax
     * @return
     */
    public double calculateTax() {
        double tax;
        switch (customer.state) {
            case "AZ":
                tax = 1.07;
                break;
            case "NY":
                tax = 1.09;
                break;
            case "VA":
                tax = 1.05;
                break;
            case "DC":
                tax = 1.105;
                break;
            case "CA":
                tax = 1.1;
                break;
            default:
                tax = 1.05;
                break;
        }
        return tax;
    }

    /**
     * Utility method to add a bear to the BearFactory. So to the shopping cart.
     * @param bear to add
     * @return true if successful, false otherwise
     * TODO: test me and fix me in assignment 3
     */
    /*
    Given that adding an element to the LinkedList will return either true or will throw an exception
    and will never return false, the else branch is impossible to reach and, therefore, unnecessary.
    */
    @Override
    public boolean addBear(Bear bear)       {
         this.BearCart.add(bear);       
            return true;
                                            
    }

    // WE ARE REMOVING A BEAR FROM THE SHOPPING CART
    @Override
    public boolean removeBear(Bear bear)    {
        if (this.BearCart.remove(bear))     {
            return true;
                                            }
        else                                {
            return false;
                                            }
    }

    /**
     * This is a function to allow customers to checkout their shopping cart
     * This method is supposed to calculate the overall costs of the bears in the cart
     * and apply all discounts
     * TODO: Test me and fix me in assignment 3
     * @return
     */
    /*
    One edit to this function is that the lines printing that the guardian is too young 
    and to return -1 are not grouped together, meaning that the guardian might be old enough
    to purchase a bear for their child, but will still be rejected. Therefore, these instructions
    ought to be grouped together.

    Additionally, the function was capable of being simplified by subtracting the savings amount
    by the raw cost of the purchase, meaning this function didn't need to compute the savings since
    it's done elsewhere.
    */
    @Override
    public double checkout() {
        if (this.customer.age <= 13) {
            if (this.customer.parent.age < 18){
                System.out.println("Guardian is too young");
                return -1;
            }
        }
        /*double temp = 0;
        Double Cost = Double.valueOf(0.00);
        for (Bear bear: BearCart) {
            Cost = Cost + getRawCost(bear);
        }
        for (Bear bear: this.BearCart) {
            temp += getCost(bear);
        }


        double savings = 0;
        // calculate total cost
        double rawCost = 0;
        for (Bear bear: BearCart) {
            rawCost += this.getRawCost(bear);
        }

        // calculate adjusted cost
        double cost = 0;
        for (Bear bear: this.BearCart) {
            cost += this.getCost(bear);
        }
        savings += rawCost - cost; // calc delta between raw and prorated cost

        List<Bear> nonFreeBears = new LinkedList<>();
        int counter = 0;
        int numberOfFreeBearsInBearCart = BearCart.size() / 3;
        double discountedCost = 0;
        Bear freeBear = null;

        for (int count = 0; count <= numberOfFreeBearsInBearCart; ++count) {
            for (Bear bear : BearCart) {
                if (freeBear != null && bear.price < freeBear.price)
                    freeBear = bear;
                    temp += temp - temp * 2 + bear.price;

            }
        }
        cost = temp;
        System.out.println("" + (cost * calculateTax()));
        return cost * calculateTax();*/
        double rawCost = 0;
        for(int i = 0; i < BearCart.size(); i++)
        {
            rawCost += getRawCost(BearCart.get(i));
        }
        double savings = calculateSavings();
        return (rawCost - savings) * calculateTax();
    }


    /**
     * This method returns the savings for advertised bundle savings.
     * Test me in Assignment 2
     * You do NOT have the implemented methods for this but just 5 implemented versions in the .class files in the cls folder. So you need
     * to Blackbox test this method based on the description you get here. 
     * What this method does: 
     * - Bear costs are calculated based on all the accessories, nose makers, material and so forth
     * - Bears are Buy 2 bears, get a third one free. It is always the cheapest bear that is free. If I buy 3 then I pay only for the 2 most expensive ones, if I buy 6 I pay for the 4 most expensive ones. The cheapest is determined after all the individiual discounts are applied to a bear. So the total bear cost is always the bear with all accessories and all bear discounts.   
     * - It is 10% off the total cost of a bear when a single bear has 10 or more accessories (clothes and noisemakers and embroidery) that the customer pays for (so if clothes are free these do not count). 
     * - Clothes are buy 2, get one free on each bear. Always the cheapest clothes are free. Basically same principle as for the Bears buy 2 get one free. 
     *  TIP: the implemented savings method in the BearWorkshop1-5 do not use the getCost method implemented in this base class. They implement their own savings calculation
     *  		 All of them do however use the getRawCost method implemented in this base class. You should test all the constraints that you can come up with, for noise makers, clothers etc. 
     * @return the savings if the customer would check out as double
     */
    public double calculateSavings() {
        double savings = 0.0;
        double priceArr[] = new double[BearCart.size()];
        for(int i = 0; i < BearCart.size(); i++)
        {
            priceArr[i] = 0.0;
            int accessoryCount = 0;
            Collections.sort(BearCart.get(i).clothing);
            int freeCount = BearCart.get(i).clothing.size() / 3;
            for (int j = 0; j < BearCart.get(i).clothing.size(); j++) 
            {
                 Clothing clothes = BearCart.get(i).clothing.get(j);
                 if (j < freeCount) 
                 {
                    savings += clothes.price;
                 } 
                 else 
                 {
                    priceArr[i] += clothes.price;
                    accessoryCount++;
                 }
             }
             for(int k = 0; k < BearCart.get(i).noisemakers.size(); k++)
             {
                priceArr[i] += BearCart.get(i).noisemakers.get(k).price;
                accessoryCount++;
             }
             if(BearCart.get(i).ink != null)
             {
                priceArr[i] += BearCart.get(i).ink.price;
             }
             priceArr[i] += BearCart.get(i).stuff.price;
             priceArr[i] *= BearCart.get(i).casing.priceModifier;
             if(accessoryCount >= 10)
             {
                double percent = priceArr[i]/10;
                savings += percent;
                priceArr[i] -= percent;
             }
        }
        Arrays.sort(priceArr);//bad sort
        int freeBears = BearCart.size() / 3;
        for(int l = 0; l < freeBears; l++)
        {
            savings += priceArr[l];
            //System.out.println("" + priceArr[l]);
        }
        //System.out.println("---");
        return savings;
    }
}