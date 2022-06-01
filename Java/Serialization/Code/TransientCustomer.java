package com.sample;

import java.io.*;

/**
 * Created by Chaklader on 11/5/18.
 */
public class TransientCustomer implements Serializable {


    // member variables
    final int customerId;
    transient final String customerName;
    transient final int customerAge = 10;
    transient int customerSSN;

    // 3-arg parameterized constructor
    public TransientCustomer(int customerId, String customerName,
                             int customerSSN) {
        this.customerId = customerId;
        this.customerName = customerName; // final transient
        this.customerSSN = customerSSN;
    }

    // overriding toString() method
    @Override
    public String toString() {
        return "Transient Customer [customerId=" + customerId
                + ", customerName=" + customerName
                + ", customerAge=" + customerAge
                + ", customerSSN=" + customerSSN + "]";
    }


    /*
    *
    *
    * These cases are bit tricky; when we serialize an Object

        all non-final instance variables will be serialized
        all non-final transient variable won’t be serialized
        all final non-transient variable will be serialized (directly as values)
        But all final transient variable won’t be serialized
    * */

    /*
    *
    * Printing customer values from de-serialized object...
        Customer [customerId=107, customerName=null, customerAge=10,customerSSN=0]
    * */
    public static void main(String[] args) {

        // create an customer instance using 3-arg constructor
        TransientCustomer serializeCustomer =
                new TransientCustomer(107, "Mike", 117896);

        // creating output stream variables
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating customer object reference
        // to hold values after de-serialization
        Customer deSerializeCustomer = null;

        try {
            // for writing or saving binary data
            fos = new FileOutputStream("Customer.ser");

            // converting java-object to binary-format
            oos = new ObjectOutputStream(fos);

            // writing or saving customer object's value to stream
            oos.writeObject(serializeCustomer);
            oos.flush();
            oos.close();

            System.out.println("Serialization success: Customer"
                    + " object saved to Customer.ser file\n");

            // reading binary data
            fis = new FileInputStream("Customer.ser");

            // converting binary-data to java-object
            ois = new ObjectInputStream(fis);

            // reading object's value and casting to Customer class
            deSerializeCustomer = (Customer) ois.readObject();
            ois.close();

            System.out.println("De-Serialization success: Customer"
                    + " object de-serialized from Customer.ser file\n");
        } catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }

        // printing customer object to console using toString() method
        System.out.println("Printing customer values from "
                + "de-serialized object... \n" + deSerializeCustomer);
    }
}
