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

public class Transform {
  public static void main(final String[] args) {
  {
    final List<String> uppercaseNames = new ArrayList<String>();
    
    for(String name : friends) {
      uppercaseNames.add(name.toUpperCase());
    }

    System.out.println(uppercaseNames);
  }
  {
    final List<String> uppercaseNames = new ArrayList<String>();
    friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
    System.out.println(uppercaseNames);
  }

/*
      friends.stream()
             .map(name -> name.toUpperCase());
*/

System.out.println("//" + "START:TRANSFORM_OUTPUT");

    friends.stream()
           .map(name -> name.toUpperCase())
           .forEach(name -> System.out.print(name + " "));     
    System.out.println();

System.out.println("//" + "END:TRANSFORM_OUTPUT");

System.out.println("//" + "START:NUMBER_OUTPUT");

    friends.stream()
           .map(name -> name.length())
           .forEach(count -> System.out.print(count + " "));

System.out.println();
System.out.println("//" + "END:NUMBER_OUTPUT");

/*
    friends.stream()
           .map(String::toUpperCase);
*/

    friends.stream()
           .map(String::toUpperCase)
           .forEach(name -> System.out.println(name));
  }
}
