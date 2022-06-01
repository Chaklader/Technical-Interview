package com.sample;

import java.io.*;


/**
 * Created by Chaklader on 11/5/18.
 */
class ExternalizedCustomer implements Externalizable {


    /*
     * serialize the customer ID and name
     * */
    int customerId;
    String customerName;

    /*
     * the age and SSN are not serialized
     * */
    int customerAge;
    String customerSSN;

    // default public no-arg constructor
    public ExternalizedCustomer() {
        System.out.println("public no-arg constructor is must for "
                + "Externalizable, "
                + "while restoring object back from file storage");
    }

    // 4-arg parameterized constructor for Customer
    public ExternalizedCustomer(int customerId, String customerName,
                                int customerAge, String customerSSN) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerSSN = customerSSN;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        // saving to file storage
        out.writeInt(customerId);
        out.writeObject(customerName);
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {

        // restoring variables, as per order of serialization
        int tempCustId = in.readInt();
        String tempCustName = (String) in.readObject();

        // assigning restored values to member variables
        customerId = tempCustId;
        customerName = tempCustName;
    }

    // to print nicely - customer object
    @Override
    public String toString() {
        return "Customer [customerId=" + customerId
                + ", customerName=" + customerName
                + ", customerSSN=" + customerSSN
                + ", customerAge=" + customerAge
                + "]";
    }


    public static void main(String[] args) {

        // create an customer object using 4-arg constructor
        ExternalizedCustomer serializeCustomer =
                new ExternalizedCustomer(59, "Nelly", 19, "SSN-78087");

        // creating output stream variables
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating customer object reference
        // to hold values after de-serialization
        ExternalizedCustomer deSerializeCustomer = null;

        try {
            // for writing or saving binary data
            fos = new FileOutputStream("Customer.ser");

            // converting java-object to binary-format
            oos = new ObjectOutputStream(fos);

            // writing or saving customer object's value to stream
            oos.writeObject(serializeCustomer);
            oos.flush();
            oos.close();

            System.out.println("Externalization: "
                    + "Customer object saved to Customer.ser file\n");

            // reading binary data
            fis = new FileInputStream("Customer.ser");

            // converting binary-data to java-object
            ois = new ObjectInputStream(fis);

            // reading object's value and casting to Customer class
            deSerializeCustomer = (ExternalizedCustomer) ois.readObject();
            ois.close();

            System.out.println("Externalization: Customer object "
                    + "de-serialized from Customer.ser file\n");
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
