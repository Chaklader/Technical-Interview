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
import static fpij.Folks.friends;

public class PickAnElement {
  public static void pickName(
    final List<String> names, final String startingLetter) {
    String foundName = null;
    for(String name : names) {
      if(name.startsWith(startingLetter)) {
        foundName = name;
        break;
      }
    }
    System.out.print(String.format("A name starting with %s: ", startingLetter));
      
    if(foundName != null) {
      System.out.println(foundName);
    } else {
      System.out.println("No name found");
    }
  }

  public static void main(final String[] args) {
    pickName(friends, "N");
    pickName(friends, "Z");
  }
}
