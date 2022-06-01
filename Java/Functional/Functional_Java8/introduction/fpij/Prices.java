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
import java.util.Arrays;

public class Prices {
  public static
  final List<BigDecimal> prices = Arrays.asList(
    new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"),
    new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
    new BigDecimal("45"), new BigDecimal("12"));
}
