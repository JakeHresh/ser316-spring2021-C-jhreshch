package main.java;

import java.util.UUID;

/**
 * This a class for Customer in the Bear Workshop.
 */
public class Customer {
    int age;

    // customer has a name and a customer id
    Customer parent;
    //String customerId;’SER316 TASK 2 SPOTBUGS FIX

    // Customer lives in a state
    public String state;

    /**
     * Default ctor with state.
     */
    public Customer(String state) {
        this.state = state;
        this.age = 18;
    }

    /**
     * Parameterized ctor for Customers.
     * @param age int age of customer
     * @param custumer reference to guardian or null
     */
    public Customer(int age, String state, Customer custumer) {
        this.parent = custumer;
        this.age = age;
        //this.customerId = UUID.randomUUID().toString();’SER316 TASK 2 SPOTBUGS FIX

        this.state = state;
    }



}