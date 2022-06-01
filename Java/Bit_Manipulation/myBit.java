import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;



class Sample_Code {

	public static boolean getBit(int num, int i) {
		return ((num & (1 << i)) != 0);
	}
	
	public static int setBit(int num, int i) {
		return num | (1 << i);
	}
	
	public static int clearBit(int num, int i) {
		int mask = ~(1 << i);
		return num & mask;
	}
	
	public static int updateBit(int num, int i, int value) {
		int mask = ~(1 << i);
		return (num & mask) | (value << i);
	}
	
	public static int clearBitsMSBthroughI(int num, int i) {
		int mask = (1 << i) - 1;
		return num & mask;
	}
	
	public static int clearBitsIthrough0(int num, int i) {
		int mask = ~(-1 >>> (31 - i));
		return (num & mask);
	}
	
	public static void main(String[] args) {
		
		int number = 59;
		System.out.println("Testing with number: " + number);
		
		// Get Bit
		System.out.println("Get Bit");
		System.out.println(AssortedMethods.toFullBinaryString(number));
		for (int i = 31; i >= 0; i--) {
			int res = getBit(number, i) ? 1 : 0;
			System.out.print(res);
		}
		
		// Update Bit
		System.out.println("\n\nUpdate Bit");		
		int num1 = 1578; // arbitrary number
		for (int i = 31; i >= 0; i--) {
			int res = getBit(number, i) ? 1 : 0;
			num1 = updateBit(num1, i, res);
		}	
		System.out.println(num1);
		
		// Set and Clear Bit
		System.out.println("\nSet and Clear Bit");		
		int num2 = 1578; // arbitrary number
		for (int i = 31; i >= 0; i--) {
			if (getBit(number, i)) {
				num2 = setBit(num2, i);
			} else {
				num2 = clearBit(num2, i);
			}
		}	
		System.out.println(num2);
	
		// Clear Bits MSB through i
		number = 13242352;
		System.out.println("\nClear bits MSB through 4");	
		System.out.println(AssortedMethods.toFullBinaryString(number));
		int num3 = clearBitsMSBthroughI(number, 4);
		System.out.println(AssortedMethods.toFullBinaryString(num3));
		
		// Clear Bits i through 0
		System.out.println("\nClear bits 6 through 0");	
		number = -1;
		System.out.println(AssortedMethods.toFullBinaryString(number));
		int num4 = clearBitsIthrough0(number, 2);
		System.out.println(AssortedMethods.toFullBinaryString(num4));
	}
}







class BitInteger {


	public static int INTEGER_SIZE;
	private boolean[] bits;
	public BitInteger() {
		bits = new boolean[INTEGER_SIZE];
	}
	/* Creates a number equal to given value. Takes time proportional 
	 * to INTEGER_SIZE. */
	public BitInteger(int value){
		bits = new boolean[INTEGER_SIZE];
		for (int j = 0; j < INTEGER_SIZE; j++){
			if (((value >> j) & 1) == 1) bits[INTEGER_SIZE - 1 - j] = true;
			else bits[INTEGER_SIZE - 1 - j] = false;
		}
	}
	
	/** Returns k-th most-significant bit. */ 
	public int fetch(int k){
		if (bits[k]) return 1;
		else return 0;
	}
	
	/** Sets k-th most-significant bit. */
	public void set(int k, int bitValue){
		if (bitValue == 0 ) bits[k] = false;
		else bits[k] = true;
	}
	
	/** Sets k-th most-significant bit. */
	public void set(int k, char bitValue){
		if (bitValue == '0' ) bits[k] = false;
		else bits[k] = true;
	}
	
	/** Sets k-th most-significant bit. */
	public void set(int k, boolean bitValue){
		bits[k] = bitValue;
	}	
	
	public void swapValues(BitInteger number) {
		for (int i = 0; i < INTEGER_SIZE; i++) {
			int temp = number.fetch(i);
			number.set(i, this.fetch(i));
			this.set(i, temp);
		}
	}
		
	public int toInt() {
		int number = 0;
		for (int j = INTEGER_SIZE - 1; j >= 0; j--){
			number = number | fetch(j);
			if (j > 0) {
				number = number << 1;
			}
		}
		return number;
	}
}




public class myBit {


	/*question: write syntaxes 
    for bitwise operation*/
    public static void bitWiseOperation(){


    	/* Bitwise and Bit Shift Operators
		~       Unary bitwise complement
		<<      Signed left shift
		>>      Signed right shift
		>>>     Unsigned right shift
		&       Bitwise AND
		^       Bitwise exclusive OR
		|       Bitwise inclusive OR*/


	    /*1010 & 0101 == 0000
		1100 & 0110 == 0100

		1010 | 0101 == 1111
		1100 | 0110 == 1110

		~1111 == 0000
		~0011 == 1100

		// In the case of ^  Bitwise exclusive OR, (1,0) or (0,1) = 1, rest will be considered as 0
		1010 ^ 0101 == 1111
		1100 ^ 0110 == 1010*/

    	 int a = 60;	/* 60 = 0011 1100 */  
	     int b = 13;	/* 13 = 0000 1101 */
	     int c = 0;

	     c = a & b;       /* 12 = 0000 1100 */ 
	     System.out.println("a & b = " + c );

	     c = a | b;       /* 61 = 0011 1101 */
	     System.out.println("a | b = " + c );

	     c = a ^ b;       /* 49 = 0011 0001 */
	     System.out.println("a ^ b = " + c );

	     c = ~a;          /*-61 = 1100 0011 */
	     System.out.println("~a = " + c );

	     c = a << 2;     /* 240 = 1111 0000 */
	     System.out.println("a << 2 = " + c );

	     c = a >> 2;     /* 215 = 1111 */
	     System.out.println("a >> 2  = " + c );

	     c = a >>> 2;     /* 215 = 0000 1111 */
	     System.out.println("a >>> 2 = " + c );
    }
    /*END of solution: write syntaxes 
    for bitwise operation*/





	/*5-1*/
	public static int updateBits(int n, int m, int i, int j) {
		// Validation
		if (i >= 32 || j < i) {
			return 0;
		}
		
		/* Create a mask to clear bits i through j in n
		/* EXAMPLE: i = 2, j = 4. Result should be 11100011.
		 * (Using 8 bits for this example.  This is obviously not actually 8 bits.)
		 */
		int allOnes = ~0; // allOnes = 11111111
		
		int left = allOnes << (j + 1); // 1s through position j, then 0s. left = 11100000	
	  	int right = ((1 << i) - 1); // 1’s after position i.  right = 00000011
		int mask = left | right; // All 1s, except for 0s between i and j. mask = 11100011

		/* Clear i through j, then put m in there */
		int n_cleared = n & mask; // Clear bits j through i.
		int m_shifted = m << i; // Move m into correct position.
		
		/* OR them, and we're done! */
		return n_cleared | m_shifted; 
	}
	/*5-1*/    	




	/*5-2*/
	public static String printBinary(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}
	
		StringBuilder binary = new StringBuilder();
		binary.append(".");
		while (num > 0) {
			/* Setting a limit on length: 32 characters */
			if (binary.length() > 32) {
				return "ERROR";
			}
			double r = num * 2;
			if (r >= 1) {
				binary.append(1);
				num = r - 1;
			} else {
				binary.append(0);
				num = r;
			}
		}
		return binary.toString();
	}
	
	public static String printBinary2(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}
	
		StringBuilder binary = new StringBuilder();
		double frac = 0.5;
		binary.append(".");
		while (num > 0) {
			/* Setting a limit on length: 32 characters */
			if (binary.length() >= 32) {
				return "ERROR";
			}
			if (num >= frac) {
				binary.append(1);
				num -= frac;
			} else {
				binary.append(0);
			}
			frac /= 2;
		}
		return binary.toString();
	}	
	/*5-2*/






	/*5-3*/

	public static int countOnes(int i) {
		int count = 0;
		while (i > 0) {
			if ((i & 1) == 1) {
				count++;
			}
			i = i >> 1;
		}
		return count;
	}
	
	public static int countZeros(int i) {
		return 32 - countOnes(i);
	}	
	
	public static boolean hasValidNext(int i) {
		if (i == 0) {
			return false;
		}
		int count = 0;
		while ((i & 1) == 0) {
			i >>= 1;
			count++;
		}
		while ((i & 1) == 1) {
			i >>= 1;
			count++;
		}		
		if (count == 31) {
			return false;
		}
		return true;	
	}
	
	public static boolean hasValidPrev(int i) {
		while ((i & 1) == 1) {
			i >>= 1;
		}
		if (i == 0) {
			return false;
		}
		return true;		
	}

	public static int getNextSlow(int i) {
		if (!hasValidNext(i)) {
			return -1;
		}
		int num_ones = countOnes(i);
		i++;
		while (countOnes(i) != num_ones) {
			i++;
		}
		return i;
	}

	public static int getPrevSlow(int i) {
		if (!hasValidPrev(i)) {
			return -1;
		}		
		int num_ones = countOnes(i);
		i--;
		while (countOnes(i) != num_ones) {
			i--;
		}
		return i;
	}
	
	public static int getNext(int n) {
		int c = n;
		int c0 = 0;
		int c1 = 0;
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		
		/* If c is 0, then n is a sequence of 1s followed by a sequence of 0s. This is already the biggest
		 * number with c1 ones. Return error.
		 */
		if (c0 + c1 == 31 || c0 + c1 == 0) {
			return -1;
		}
		
		int pos = c0 + c1; // position of right-most non-trailing zero (where the right most bit is bit 0)
		
		/* Flip the right-most non-trailing zero (which will be at position pos) */
		n |= (1 << pos); // Flip right-most non-trailing zero
				
		/* Clear all bits to the right of pos.
		 * Example with pos = 5 
		 * (1) Shift 1 over by 5 to create 0..0100000           [ mask = 1 << pos ]
		 * (2) Subtract 1 to get 0..0011111                     [ mask = mask - 1 ]
		 * (3) Flip all the bits by using '~' to get 1..1100000 [ mask = ~mask    ]
		 * (4) AND with n
		 */
		n &= ~((1 << pos) - 1); // Clear all bits to the right of pos
		
		/* Put (ones-1) 1s on the right by doing the following:
		 * (1) Shift 1 over by (ones-1) spots. If ones = 3, this gets you 0..0100
		 * (2) Subtract one from that to get 0..0011
		 * (3) OR with n
		 */
		n |= (1 << (c1 - 1)) - 1;
		
		return n;
	}
	
	public static int getNextArith(int n) {
		int c = n;
		int c0 = 0;
		int c1 = 0;
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		
		/* If c is 0, then n is a sequence of 1s followed by a sequence of 0s. This is already the biggest
		 * number with c1 ones. Return error.
		 */
		if (c0 + c1 == 31 || c0 + c1 == 0) {
			return -1;
		}
		
		/* Arithmetically:
		 * 2^c0 = 1 << c0
		 * 2^(c1-1) = 1 << (c0 - 1)
		 * next = n + 2^c0 + 2^(c1-1) - 1;
		 */
		
		return n + (1 << c0) + (1 << (c1 - 1)) - 1;
	}	
	
	public static int getPrev(int n) {
		int temp = n;
		int c0 = 0;
		int c1 = 0;
		while ((temp & 1) == 1) {
			c1++;
			temp >>= 1;
		}
		
		/* If temp is 0, then the number is a sequence of 0s followed by a sequence of 1s. This is already
		 * the smallest number with c1 ones. Return -1 for an error.
		 */
		if (temp == 0) { 
			return -1;
		}
		
		while (((temp & 1) == 0) && (temp != 0)) {
			c0++;
			temp >>= 1;
		}

		int p = c0 + c1; // position of right-most non-trailing one (where the right most bit is bit 0)

		/* Flip right-most non-trailing one. 
		 * Example: n = 00011100011.
		 * c1 = 2
		 * c0 = 3
		 * pos = 5
		 * 
		 * Build up a mask as follows:
		 * (1) ~0 will be a sequence of 1s
		 * (2) shifting left by p + 1 will give you 11.111000000 (six 0s) 
		 * (3) ANDing with n will clear the last 6 bits
		 * n is now 00011000000
		 */
		n &= ((~0) << (p + 1)); // clears from bit p onwards (to the right)
		
		/* Create a sequence of (c1+1) 1s as follows
		 * (1) Shift 1 to the left (c1+1) times. If c1 is 2, this will give you 0..001000
		 * (2) Subtract one from that. This will give you 0..00111
		 */
		int mask = (1 << (c1 + 1)) - 1; // Sequence of (c1+1) ones
		
		/* Move the ones to be right up next to bit p 
		 * Since this is a sequence of (c1+1) ones, and p = c1 + c0, we just need to
		 * shift this over by (c0-1) spots.
		 * If c0 = 3 and c1 = 2, then this will look like 00...0011100
		 * 
		 * Then, finally, we OR this with n.
		 */
		n |= mask << (c0 - 1);  
		
		return n;		
	}
	
	public static int getPrevArith(int n) {
		int temp = n;
		int c0 = 0;
		int c1 = 0;
		while (((temp & 1) == 1) && (temp != 0)) {
			c1++;
			temp >>= 1;
		}
		
		/* If temp is 0, then the number is a sequence of 0s followed by a sequence of 1s. This is already
		 * the smallest number with c1 ones. Return -1 for an error.
		 */
		if (temp == 0) { 
			return -1;
		}
		
		while ((temp & 1) == 0 && (temp != 0)) {
			c0++;
			temp >>= 1;
		}

		/* Arithmetic:
		 * 2^c1 = 1 << c1
		 * 2^(c0 - 1) = 1 << (c0 - 1)
		 */
		return n - (1 << c1) - (1 << (c0 - 1)) + 1;		
	}	
	
	public static void binPrint(int i) {
		System.out.println(i + ": " + Integer.toBinaryString(i));		
	}
	/*5-3*/




	/*5-5*/

	public static int bitSwapRequired(int a, int b) {
		int count = 0;
		for (int c = a ^ b; c != 0; c = c >> 1) { 
			count += c & 1;
		}
		return count;
	}
	
	public static int bitSwapRequired2(int a, int b){
		int count = 0;
		for (int c = a ^ b; c != 0; c = c & (c-1)) {
			count++;
		}
		return count;
	}
	/*5-5*/






	/*5-6*/
	public static int swapOddEvenBits(int x) { 
		return ( ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1) ); 
	} 
	/*5-6*/








	/*5-7*/
	/* Create a random array of numbers from 0 to n, but skip 'missing' */
    public static ArrayList<BitInteger> initialize(int n, int missing) {

        BitInteger.INTEGER_SIZE = Integer.toBinaryString(n).length();
        ArrayList<BitInteger> array = new ArrayList<BitInteger>();
        
        for (int i = 1; i <= n; i++) {
        	array.add(new BitInteger(i == missing ? 0 : i));
        }

        // Shuffle the array once.
        for (int i = 0; i < n; i++){
            int rand = i + (int) (Math.random() * (n-i));
            array.get(i).swapValues(array.get(rand));
        }
        
        return array;
    }


    public static int findMissing(ArrayList<BitInteger> array) {
       return findMissing(array, BitInteger.INTEGER_SIZE - 1);
    }        

    private static int findMissing(ArrayList<BitInteger> input, int column) {
    	if (column < 0) { // Base case and error condition
    		return 0;
    	}
    	ArrayList<BitInteger> oneBits = new ArrayList<BitInteger>(input.size()/2);
    	ArrayList<BitInteger> zeroBits = new ArrayList<BitInteger>(input.size()/2);
        for (BitInteger t : input) {
            if (t.fetch(column) == 0) {
                zeroBits.add(t);
            } else {
                oneBits.add(t);
            }
        }
        if (zeroBits.size() <= oneBits.size()) {
        	int v = findMissing(zeroBits, column - 1);
        	System.out.print("0");
            return (v << 1) | 0;
        } else {
        	int v = findMissing(oneBits, column - 1);
        	System.out.print("1");
            return (v << 1) | 1;
        }
    }
	/*5-7*/







	/*5-8*/

	public static int computeByteNum(int width, int x, int y) {
		return (width * y + x) / 8;
	}
	
	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8;
		if (start_offset != 0) {
			first_full_byte++;
		}
		
		int end_offset = x2 % 8;
		int last_full_byte = x2 / 8;
		if (end_offset != 7) {
			last_full_byte--;
		}
		
		// Set full bytes
		for (int b = first_full_byte; b <= last_full_byte; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}
		
		byte start_mask = (byte) (0xFF >> start_offset);
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));
		
		// Set start and end of line
		if ((x1 / 8) == (x2 / 8)) { // If x1 and x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (start_offset != 0) {
				int byte_number = (width / 8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if (end_offset != 7) {
				int byte_number = (width / 8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			} 
		}
	}
	
	public static void printByte(byte b) {
		for (int i = 7; i >= 0; i--) {
			System.out.print((b >> i) & 1);
		}
	}
	
	public static void printScreen(byte[] screen, int width) {
		int height = screen.length * 8 / width;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c+=8) {
				byte b = screen[computeByteNum(width, c, r)];
				printByte(b);
			}
			System.out.println("");
		}
	}
	/*5-8*/


	public static void main(String[] args) {


		/*5-1*/
		/*int a = 103217;
		System.out.println(AssortedMethods.toFullBinaryString(a));
		int b = 13;
		System.out.println(AssortedMethods.toFullBinaryString(b));		
		int c = updateBits(a, b, 4, 12);
		System.out.println(AssortedMethods.toFullBinaryString(c));*/
		/*5-1*/	




		/*5-2*/
		/*String bs = printBinary(.125);
		System.out.println(bs);
		
		for (int i = 0; i < 1000; i++) {
			double num = i / 1000.0;
			String binary = printBinary(num);
			String binary2 = printBinary2(num);
			if (!binary.equals("ERROR") || !binary2.equals("ERROR")) {
				System.out.println(num + " : " + binary + " " + binary2);
			}
		}*/
		/*5-2*/






		/*5-3*/
		/*for (int i = 0; i < 200; i++) {
			int p1 = getPrevSlow(i);
			int p2 = getPrev(i);
			int p3 = getPrevArith(i);
			
			int n1 = getNextSlow(i);
			int n2 = getNext(i);
			int n3 = getNextArith(i);
			
			if (p1 != p2 || p2 != p3 || n1 != n2 || n2 != n3) {
				binPrint(i);
				binPrint(p1);
				binPrint(p2);
				binPrint(p3);
				binPrint(n1);
				binPrint(n2);
				binPrint(n3);
				System.out.println("");
			}			
		}*/
		/*5-3*/	




		/*5-5*/
		/*int a = 23432;
		int b = 512132;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
		int nbits = bitSwapRequired(a, b);
		int nbits2 = bitSwapRequired2(a, b);
		System.out.println("Required number of bits: " + nbits + " " + nbits2);*/
		/*5-5*/






		/*5-6*/
		/*int a = 103217;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		int b = swapOddEvenBits(a);
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));*/
		/*5-6*/





		/*5-7*/
		/*Random rand = new Random(); 
        int n = rand.nextInt(300000) + 1;
        int missing = rand.nextInt(n+1);
        ArrayList<BitInteger> array = initialize(n, missing);
        System.out.println("The array contains all numbers but one from 0 to " + n + ", except for " + missing);
        
        int result = findMissing(array);
        if (result != missing) {
            System.out.println("Error in the algorithm!\n" + "The missing number is " + missing + ", but the algorithm reported " + result);
        } else {
            System.out.println("The missing number is " + result);
        }*/
		/*5-7*/






		/*5-8*/
		/*int width = 8 * 4;
		int height = 15;
		byte[] screen = new byte[width * height / 8];
		//screen[1] = 13;
		
		drawLine(screen, width, 8, 10, 2);

		printScreen(screen, width);*/
		/*5-8*/
	}
}