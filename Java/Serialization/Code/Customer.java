package com.sample;

import java.io.*;


public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    // to always, return same instance
    private volatile static Customer CUSTOMER = new Customer();

    // private constructor
    private Customer() {
        // private constructor
    }

    // create static method to get same instance every time
    public static Customer getInstance() {
        return CUSTOMER;
    }


    /*
     * this method invoked automatically during serialization process
     * */
    private Object readResolve() throws ObjectStreamException {
        return CUSTOMER;
    }


    public static void main(String[] args) {


        // create an customer object
        Customer serializeCustomer = Customer.getInstance();

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

            System.out.println("Serialization: "
                    + "Customer object saved to Customer.ser file\n");

            // reading binary data
            fis = new FileInputStream("Customer.ser");

            // converting binary-data to java-object
            ois = new ObjectInputStream(fis);

            // reading object's value and casting to Customer class
            deSerializeCustomer = (Customer) ois.readObject();
            ois.close();

            System.out.println("De-Serialization: Customer object "
                    + "de-serialized from Customer.ser file\n");
        } catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }

        // printing hash code of serialize customer object
        System.out.println("Hash code of the serialized "
                + "Customer object is " + serializeCustomer.hashCode());

        // printing hash code of de-serialize customer object
        System.out.println("\nHash code of the de-serialized "
                + "Customer object is " + deSerializeCustomer.hashCode());
    }
}