/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/
package fpij;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import static fpij.Prices.prices;

public class DiscountImperative {
  public static void main(final String[] args) {
    BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;

    for(BigDecimal price : prices) {
      if(price.compareTo(BigDecimal.valueOf(20)) > 0) 
        totalOfDiscountedPrices = 
          totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
    }
    System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
  }
}
