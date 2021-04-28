/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/
package fpij;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import static fpij.Folks.friends;

public class PickElements {
  public static void main(final String[] args) {
{
    final List<String> startsWithN = new ArrayList<String>();
    for(String name : friends) {
      if(name.startsWith("N")) {
        startsWithN.add(name);
      }
    }

    System.out.println(String.format("Found %d names", startsWithN.size()));
}

System.out.println("//" + "START:FILTER_OUTPUT");
    final List<String> startsWithN =
      friends.stream()
             .filter(name -> name.startsWith("N"))
             .collect(Collectors.toList());

System.out.println(String.format("Found %d names", startsWithN.size()));

System.out.println("//" + "END:FILTER_OUTPUT");
  }
}
