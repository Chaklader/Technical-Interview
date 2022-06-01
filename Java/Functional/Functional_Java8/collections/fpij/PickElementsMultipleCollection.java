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
import java.util.function.Predicate;
import static fpij.Folks.friends;
import static fpij.Folks.comrades;
import static fpij.Folks.editors;


public class PickElementsMultipleCollection {
  public static void main(final String[] args) {
{
    final long countFriendsStartN = 
      friends.stream()
             .filter(name -> name.startsWith("N")).count();

    final long countEditorsStartN = 
      editors.stream()
             .filter(name -> name.startsWith("N")).count();

    final long countComradesStartN = 
      comrades.stream()
              .filter(name -> name.startsWith("N")).count();

    System.out.println(countFriendsStartN);
    System.out.println(countComradesStartN);
    System.out.println(countEditorsStartN);
}

{
    final Predicate<String> startsWithN = name -> name.startsWith("N");

    final long countFriendsStartN = 
      friends.stream()
             .filter(startsWithN)
             .count();
    final long countEditorsStartN = 
      editors.stream()
             .filter(startsWithN)
             .count();
    final long countComradesStartN = 
      comrades.stream()
              .filter(startsWithN)
              .count();

    System.out.println(countFriendsStartN);
    System.out.println(countComradesStartN);
    System.out.println(countEditorsStartN);
}
  }
}
