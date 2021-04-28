/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/
package fpij;

public class HolderThreadSafe {
  private Heavy heavy;
  
  public HolderThreadSafe() {
    System.out.println("Holder created");
  }

  public synchronized Heavy getHeavy() {
    if(heavy == null) {
      heavy = new Heavy();
    }
    
    return heavy;
  }

//...

  public static void main(final String[] args) {
    final HolderThreadSafe holder = new HolderThreadSafe();
    System.out.println("deferring heavy creation...");
    System.out.println(holder.getHeavy());
    System.out.println(holder.getHeavy());
  }
}
