/*
    File: BearWorkshop.java
    Author: Dr. Mehlhase
    Date: 2/20/2021

    Description:
    This file stores the BearWorkshop class, which is used to keep track of customers purchasing bears and
    to calculate purchases.
*/

package main.java;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Collections;

import java.util.LinkedList;

import java.util.List;

// This class provides functionality for a BearWorkshop class.
public class BearWorkshop implements BearWorkshopInterface {
    // Workshop has a collection of bears
    // Workshop has a customer
    Customer customer;
    List<Bear> bearCart;

    /**
     * Default constructor for the Bear Workshop.
     */
    public BearWorkshop() {
        this("AZ");
    }

    /**
     * This is a parameterized ctor for a BearWorkshop.
     * @param state customer is in
     */
    public BearWorkshop(String state) {
        bearCart = new LinkedList<>();
        customer = new Customer(state);
    }
    /*
    This constructor is included to test different branches in the checkout method.
    */
    public BearWorkshop(int age1, int age2, String state) {
        bearCart = new LinkedList<>();
        customer = new Customer(age1, state, new Customer(age2, state, new Customer(state)));
    }

    
    @Override
    public double getCost(Bear bear) {
        Collections.sort(bear.clothing);
        int numFree = bear.clothing.size() / 3;
        //ArrayList<Clothing> freeClothes = new ArrayList<>();’SER316 TASK 2 SPOTBUGS FIX

        for (int i = 0; i < bear.clothing.size(); i++) {
            Clothing clothes = bear.clothing.get(i);
            if (i < numFree) {
                //freeClothes.add(clothes);’SER316 TASK 2 SPOTBUGS FIX
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

    /**
     * Enjoy the javadoc.
     * @param bear Here's a description.
     */
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
     * Utility method to calculate tax for purchases by 
     * customers in different
     * states.
     * You can assume these taxes are what we want, 
     * so they are not wrong.
     * The taxes are meant to be used in this way: 
     * costInShoppingCart * tax
     * @return 
     */
    public double calculateTax() {
        double tax;
        if(customer.state.equals("AZ"))
        {
            tax = 1.07;
        }
        else if(customer.state.equals("NY"))
        {
            tax = 1.09;
        }
        else if(customer.state.equals("VA"))
        {
            tax = 1.05;
        }
        else if(customer.state.equals("DC"))
        {
            tax = 1.105;
        }
        else if(customer.state.equals("CA"))
        {
            tax = 1.1;
        }
        else
        {
            tax = 1.05;
        }
        return tax;
    }

    /**
     * Utility method to add a bear to the BearFactory. So to the shopping cart.
     * @param bear to add
     * return true if successful, false otherwise
     */
    
    //Given that adding an element to the LinkedList 
    //will return either true or will throw an exception
    //and will never return false, the else branch is 
    //impossible to reach and, therefore, unnecessary.
    
    @Override
    public boolean addBear(Bear bear) {
        this.bearCart.add(bear);       
        return true;      
    }

    // WE ARE REMOVING A BEAR FROM THE SHOPPING CART
    @Override
    public boolean removeBear(Bear bear) {
        if (this.bearCart.remove(bear)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is a function to allow customers to checkout their shopping cart
     * This method is supposed to calculate the overall costs of the bears in the cart
     * and apply all discounts.
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
            if (this.customer.parent.age < 18) {
                System.out.println("Guardian is too young");
                return -1;
            }
        }
        /*double temp = 0;
        Double Cost = Double.valueOf(0.00);
        for (Bear bear: bearCart) {
            Cost = Cost + getRawCost(bear);
        }
        for (Bear bear: this.bearCart) {
            temp += getCost(bear);
        }


        double savings = 0;
        // calculate total cost
        double rawCost = 0;
        for (Bear bear: bearCart) {
            rawCost += this.getRawCost(bear);
        }

        // calculate adjusted cost
        double cost = 0;
        for (Bear bear: this.bearCart) {
            cost += this.getCost(bear);
        }
        savings += rawCost - cost; // calc delta between raw and prorated cost

        List<Bear> nonFreeBears = new LinkedList<>();
        int counter = 0;
        int numberOfFreeBearsInBearCart = bearCart.size() / 3;
        double discountedCost = 0;
        Bear freeBear = null;

        for (int count = 0; count <= numberOfFreeBearsInBearCart; ++count) {
            for (Bear bear : bearCart) {
                if (freeBear != null && bear.price < freeBear.price)
                    freeBear = bear;
                    temp += temp - temp * 2 + bear.price;

            }
        }
        cost = temp;
        System.out.println("" + (cost * calculateTax()));
        return cost * calculateTax();*/
        double rawCost = 0;
        for (int i = 0; i < bearCart.size(); i++) {
            rawCost += getRawCost(bearCart.get(i));
        }
        double savings = calculateSavings();
        return (rawCost - savings) * calculateTax();
    }


    /**
     * A summary I have for you.
     * @return the savings if the customer would check out as double.
     */
    public double calculateSavings() {
        double savings = 0.0;
        double priceArr[] = new double [bearCart.size()];
        for (int i = 0; i < bearCart.size(); i++) {
            priceArr[i] = 0.0;
            int accessoryCount = 0;
            Collections.sort(bearCart.get(i).clothing);
            int freeCount = bearCart.get(i).clothing.size() / 3;
            for (int j = 0; j < bearCart.get(i).clothing.size(); j++) {
                 Clothing clothes = bearCart.get(i).clothing.get(j);
                 if (j < freeCount) {
                    savings += clothes.price;
                 } else {
                    priceArr[i] += clothes.price;
                    accessoryCount++;
                 }
             }
             for (int k = 0; k < bearCart.get(i).noisemakers.size(); k++) {
                priceArr[i] += bearCart.get(i).noisemakers.get(k).price;
                accessoryCount++;
             } if (bearCart.get(i).ink != null) {
                priceArr[i] += bearCart.get(i).ink.price;
             }
             priceArr[i] += bearCart.get(i).stuff.price;
             priceArr[i] *= bearCart.get(i).casing.priceModifier;
             if (accessoryCount >= 10) {
                double percent = priceArr[i]/10;
                savings += percent;
                priceArr[i] -= percent;
             }
        }
        Arrays.sort(priceArr);//bad sort
        int freeBears = bearCart.size() / 3;
        for (int l = 0; l < freeBears; l++) {
            savings += priceArr[l];
            //System.out.println("" + priceArr[l]);
        }
        //System.out.println("---");
        return savings;
    }
}
