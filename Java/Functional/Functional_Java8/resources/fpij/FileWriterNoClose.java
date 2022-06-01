/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/
package fpij;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterNoClose {
  private FileWriter writer;
  
  public FileWriterNoClose(String fileName) throws IOException {
    writer = new FileWriter(fileName);
  }
  
  public void writeStuff(String message) throws IOException {
    writer.write(message);
  }
  
  public void finalize() throws IOException {
    writer.close();
  }

  public static void main(String[] args) {
    try {
      FileWriterNoClose writerExample = new FileWriterNoClose("noclose.txt");
      writerExample.writeStuff("peek-a-boo");      
    } catch(IOException ex) {
      System.out.println(ex);
    }
  }
}
