/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/
package fpij;

import org.junit.Test;
import java.math.BigDecimal;
import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

public class CalculateNAVTest {
  @Test 
  public void computeStockWorth() {
    final CalculateNAV calculateNAV = 
      new CalculateNAV(ticker -> new BigDecimal("6.01"));
    BigDecimal expected = new BigDecimal("6010.00");  
    assertEquals(0, 
      calculateNAV.computeStockWorth("GOOG", 1000).compareTo(expected), 
      0.001);
  }
  
  public static void main(String[] args) {
    junit.textui.TestRunner.run(new JUnit4TestAdapter(CalculateNAVTest.class));
  }
  //...
}
