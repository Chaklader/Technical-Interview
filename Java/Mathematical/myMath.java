import java.util.*; 
import java.util.stream.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map.Entry; 
import java.util.Map; 
import java.util.regex.*;
import java.lang.StringBuilder;



/*question*/
class SwapMinMax {
	
	public static int getMinIndex(int[] array) {
	
		int minIndex = 0;

		for (int i = 1; i < array.length; i++) {

			if (array[i] < array[minIndex]) {
				minIndex = i;
			}
		}

		return minIndex;
	}
	
	public static int getMaxIndex(int[] array) {

		int maxIndex = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[maxIndex]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public static void swap(int[] array, int m, int n) {
		int temp = array[m];
		array[m] = array[n];
		array[n] = temp;
	}
	
	public static void swapMinMaxBetter(int[] array) {
		int minIndex = getMinIndex(array);
		int maxIndex = getMaxIndex(array);
		swap(array, minIndex, maxIndex);
	}

	public static void swapMinMax(int[] array) {
		int minIndex = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[minIndex]) {
				minIndex = i;
			}
		}
		
		int maxIndex = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[maxIndex]) {
				maxIndex = i;
			}
		}
		
		int temp = array[minIndex];
		array[minIndex] = array[maxIndex];
		array[maxIndex] = temp;
	}
	
	public static void allTestA() {
		int[] array = AssortedMethods.randomArray(10, -10, 10);
		System.out.println(AssortedMethods.arrayToString(array));
		swapMinMaxBetter(array);
		System.out.println(AssortedMethods.arrayToString(array));
	}

}

/*END of solution*/

/*question*/
class CompareBinaryToHex {

	public static int digitToValue(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else if (c >= 'A' && c <= 'F') {
			return 10 + c - 'A';
		} else if (c >= 'a' && c <= 'f') {
			return 10 + c - 'a';
		}
		return -1;
	}
	
	public static int convertToBase(String number, int base) {

		if (base < 2 || (base > 10 && base != 16)) return -1;
		int value = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			int digit = digitToValue(number.charAt(i));
			if (digit < 0 || digit >= base) {
				return -1;
			}
			int exp = number.length() - 1 - i;
			value += digit * Math.pow(base, exp);
		}
		return value;
	}
	

	public static boolean compareBinToHex(String binary, String hex) {

		int n1 = convertToBase(binary, 2);
		int n2 = convertToBase(hex, 16);
		if (n1 < 0 || n2 < 0) {
			return false;
		} else {
			return n1 == n2;
		}
	}
	
	// compary binary and the hexadecimal number 
	public static void allTest(){
		System.out.println(compareBinToHex("111001011", "1CB"));
	}
}
/*END of solution*/



class PrimeNumbers {

	public static boolean primeNaive(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean primeSlightlyBetter(int n) {
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}	
	
	public static void main(String[] args) {
		for (int i = 2; i < 100; i++) {
			if (primeSlightlyBetter(i)) {
				System.out.println(i);
			}
		}
	}

}








/*7-3*/
class Line {

	static double epsilon = 0.000001;
	public double slope;
	public double yintercept;
	
	public Line(double s, double y) {
		slope = s;
		yintercept = y;
	}
	
	public void print() {
		System.out.print("y = " + slope + "x + " + yintercept);
	}
	
	public boolean intersect(Line line2) {
		return Math.abs(slope - line2.slope) > epsilon ||
			   Math.abs(yintercept - line2.yintercept) < epsilon;
	}
};
/*7-3*/









/*7-5*/
class Line {

	public Point start;
	public Point end;
	
	public Line(Point start, Point end) {
	
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
	
		return "Line from " + start.toString() + " to " + end.toString();
	}
}

class Point {


	public double x;
	public double y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isEqual(Point p) {
		return (p.x == x && p.y == y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}



class Square {


	public double left;
	public double top;
	public double bottom;
	public double right;
	public double size;
	
	public Square(double left, double top, double size) {
	
		this.left = left;
		this.top = top;
		this.bottom = top + size;
		this.right = left + size;
		this.size = size;
	}
	
	public Point middle() {
	
		return new Point((this.left + this.right)/2.0, (this.top + this.bottom)/2.0);
	}
	
	public boolean contains(Square other) {
	
		if (this.left <= other.left && this.right >= other.right && this.top <= other.top && this.bottom >= other.bottom) {
			return true;
		}
	
		return false;
	}
	
	/* Return the point where the line segment connecting mid1 and
	 * mid2 intercepts the edge of square 1. That is, draw a line 
	 * from mid2 to mid1, and continue it out until the edge of
	 * the square. */
	public Point extend(Point mid1, Point mid2, double size) {
		/* Find what direction the line mid2 -> mid1 goes */
		double xdir = mid1.x < mid2.x ? -1 : 1;
		double ydir = mid1.y < mid2.y ? -1 : 1;
		
		/* If mid1 and mid2 have the same x value, then the slope
		 * calculation will throw a divide by 0 exception. So, we
		 * compute this specially. */
		if (mid1.x == mid2.x) {
			return new Point(mid1.x, mid1.y + ydir * size / 2.0);
		}
		double slope = (mid1.y - mid2.y) / (mid1.x - mid2.x);
		double x1 = 0;
		double y1 = 0;
		
		/* Calculate slope using the equation (y1 - y2) / (x1 - x2).
		 * Note: if the slope is “steep” (>1) then the end of the
		 * line segment will hit size / 2 units away from the middle
		 * on the y axis. If the slope is “shallow” (<1) the end of
		 * the line segment will hit size / 2 units away from the
		 * middle on the x axis. */
		if (Math.abs(slope) == 1) {
			x1 = mid1.x + xdir * size / 2.0;
			y1 = mid1.y + ydir * size / 2.0;
		} else if (Math.abs(slope) < 1) {
			x1 = mid1.x + xdir * size / 2.0;
			y1 = slope * (x1 - mid1.x) + mid1.y; 
		} else {
			y1 = mid1.y + ydir * size / 2.0;
			x1 = (y1 - mid1.y) / slope + mid1.x;
		}
		return new Point(x1, y1);
	}
	
	public Line cut(Square other) {
		/* Calculate where a line between each middle would collide with the edges of the squares */
		Point p1 = extend(this.middle(), other.middle(), this.size);
		Point p2 = extend(this.middle(), other.middle(), -1 * this.size);
		Point p3 = extend(other.middle(), this.middle(), other.size);
		Point p4 = extend(other.middle(), this.middle(), -1 * other.size);
	
		/* Of above points, find start and end of lines. Start is farthest left (with top most as a tie breaker)
		 * and end is farthest right (with bottom most as a tie breaker */
		Point start = p1;
		Point end = p1;		
		Point[] points = {p2, p3, p4};
		for (int i = 0; i < points.length; i++) {
			if (points[i].x < start.x || (points[i].x == start.x && points[i].y < start.y)) {
				start = points[i];
			} else if (points[i].x > end.x || (points[i].x == end.x && points[i].y > end.y)) {
				end = points[i];
			}
		}
			
		return new Line(start, end);
	}
	
	public String toString() {
		return "(" + left + ", " + top + ")|(" + right + "," + bottom + ")";
	}
}

/*7-5*/








/*7-6*/
class GraphPoint {
	public double x;
	public double y;
	public GraphPoint(double x1, double y1) {
		x = x1;
		y = y1;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}



class Line {


	public static double epsilon = .0001;
	public double slope;
	public double intercept;
	
	private boolean infiniteslope = false;
	
	public Line(GraphPoint p, GraphPoint q) {
		if (Math.abs(p.x - q.x) > epsilon) { // if x’s are different
			slope = (p.y - q.y) / (p.x - q.x); // compute slope
			intercept = p.y - slope * p.x; // y intercept from y=mx+b
		} else {
			infiniteslope = true;
			intercept = p.x; // x-intercept, since slope is infinite
		}
	}
	
	public boolean isEquivalent(double a, double b) {
		return (Math.abs(a - b) < epsilon);
	}
	
	public void Print() {
		System.out.println("y = " + slope + "x + " + intercept);
	}
		
	public static double floorToNearestEpsilon(double d) {
		int r = (int) (d / epsilon);
		return ((double) r) * epsilon;
	}
    
	public boolean isEquivalent(Object o) {  
		Line l = (Line) o;
    	if (isEquivalent(l.slope, slope) && isEquivalent(l.intercept, intercept) && (infiniteslope == l.infiniteslope)) {
    		return true;
    	}
        return false;
    }      
}

/*7-6*/











public class myMath {

	public static int randomInt(int n) {
	
		return (int) (Math.random() * n);
	}

	/*7-4*/

	/* Flip a positive sign to negative, or a negative sign to pos */
	public static int negate(int a) {
		int neg = 0;
		int d = a < 0 ? 1 : -1;
		while (a != 0) {
			neg += d;
			a += d;
		}
	    return neg;
	}

	/* Subtract two numbers by negating b and adding them */
	public static int minus(int a, int b) {
		return a + negate(b);
	}

	/* Return absolute value */
	public static int abs(int a) {

		if (a < 0) {
			return negate(a);
		}

		else return a;
	}

	/* Multiply a by b by adding a to itself b times */
	public static int multiply(int a, int b) {

		if (a < b) {
			return multiply(b, a); // algo is faster if b < a
		}
		int sum = 0;
		for (int i = abs(b); i > 0; i--) {
			sum += a;
		}
		if (b < 0) {
			sum = negate(sum);
		}
		return sum;
	}




	/* Divide a by b by literally counting how many times b can go into
	 * a. That is, count how many times you can add b to itself until you reach a. */
	public static int divide(int a, int b) throws java.lang.ArithmeticException {


		if (b == 0) {
			throw new java.lang.ArithmeticException("ERROR: Divide by zero.");
		}
		int absa = abs(a);
		int absb = abs(b);
		
		int product = 0;
		int x = 0;
		while (product + absb <= absa) { /* don't go past a */
			product += absb;
			x++;
		}
		
		if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
			return x;
		} else {
			return negate(x);
		}
	}
	
	/*7-4*/





	/*7-5*/
	public static void printLine(Line l) {
		System.out.println(l.start.x + "\t" + l.start.y);
		System.out.println(l.end.x + "\t" + l.end.y);
	}
	
	public static void printSquare(Square s) {
		System.out.println(s.left + "\t" + s.top + "\t" + s.size);
	}	
	
	public static boolean isApproxEqual(double d1, double d2) {
		double epsilon = .001;	
		if (Math.abs(d1 - d2) < epsilon) {
			return true;
		}
		return false;
	}
	
	public static boolean isApproxEqual(Point p1, Point p2) {
		return isApproxEqual(p1.x, p2.x) && isApproxEqual(p1.y, p2.y); 
	}
	
	public static boolean doTest(Square s1, Square s2, Point start, Point end) {
		Line line = s1.cut(s2);
		boolean r = (isApproxEqual(line.start, start) && isApproxEqual(line.end, end)) || (isApproxEqual(line.start, end) && isApproxEqual(line.end, start));
		if (!r) {
			printSquare(s1);
			printSquare(s2);
			printLine(line);
			System.out.println(start.toString());
			System.out.println(end.toString());
			System.out.println();
			return r;
		}
		return r;
	}
	
	public static boolean doTestFull(Square s1, Square s2, Point start, Point end) {
		return doTest(s1, s2, start, end) && doTest(s2, s1, start, end);
	}
	
	public static void doTests() {
		// Equal
		doTestFull(new Square(1, 1, 5), new Square(1, 1, 5), new Point(3.5, 1), new Point(3.5, 6));	
		
		// Concentric
		doTestFull(new Square(1, 1, 5), new Square(2, 2, 3), new Point(3.5, 1), new Point(3.5, 6));
		
		// Partially overlapping -- side by side
		doTestFull(new Square(10, 10, 10), new Square(8, 10, 10), new Point(8, 15), new Point(20, 15));
		
		// Partially overlapping -- corners
		doTestFull(new Square(10, 10, 10), new Square(8, 7, 7), new Point(8.777777, 7), new Point(18.8888888, 20));		
		
		// Partially overlapping -- on top of each other
		doTestFull(new Square(10, 10, 10), new Square(8, 7, 15), new Point(8, 22), new Point(23, 7));		
		
		// Not overlapping -- side by side
		doTestFull(new Square(10, 10, 10), new Square(19, 25, 4), new Point(12.5, 10), new Point(22, 29));				
		
		// Not overlapping -- on top of each other
		doTestFull(new Square(10, 10, 10), new Square(4, 4, 3), new Point(4, 4), new Point(20, 20));		
		
		// Contained
		doTestFull(new Square(10, 10, 10), new Square(12, 14, 3), new Point(10, 16.66666), new Point(20, 13.333));			
	}
	/*7-5*/





	/*7-6*/
	/* Count lines within an array of lines which are "equivalent" (slope and y-intercept are within an epsilon value) to a given line */
	public static int countEquivalentLines(ArrayList<Line> lines, Line line) {
		if (lines == null) {
			return 0;
		}
		
		int count = 0;
		for (Line parallelLine : lines) {
			if (parallelLine.isEquivalent(line)) {
				count++;
			}
		}
		return count;		
	}
	
	/* Check hashmap for lines that are equivalent. Note that we need to check one epsilon above and below the actual slope
	 * since we're defining two lines as equivalent if they're within an epsilon of each other.
	 */
	public static int countEquivalentLines(HashMap<Double, ArrayList<Line>> linesBySlope, Line line) {
		double key = Line.floorToNearestEpsilon(line.slope);
		int count = countEquivalentLines(linesBySlope.get(key), line);
		count += countEquivalentLines(linesBySlope.get(key - Line.epsilon), line);
		count += countEquivalentLines(linesBySlope.get(key + Line.epsilon), line);
		return count;
	}	
	
	/* insert line into hashmap */
	public static void insertLine(HashMap<Double, ArrayList<Line>> linesBySlope, Line line) {
		ArrayList<Line> lines = null;
		double key = Line.floorToNearestEpsilon(line.slope);
		if (!linesBySlope.containsKey(key)) {
			lines = new ArrayList<Line>();
			linesBySlope.put(key, lines);
		} else {
			lines = linesBySlope.get(key);
		}
		lines.add(line);
	}
	
	
	public static Line findBestLine(GraphPoint[] points) {
		Line bestLine = null;
		int bestCount = 0;
		HashMap<Double, ArrayList<Line>> linesBySlope = new HashMap<Double, ArrayList<Line>>();
		
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				Line line = new Line(points[i], points[j]);
				insertLine(linesBySlope, line);
				
				/* count lines that are equivalent to current line */
				int count = countEquivalentLines(linesBySlope, line);
				
				/* if better than current line, replace it */
				if (count > bestCount) {
					bestLine = line;
					bestCount = count;
				}
			}
		} 
		
		return bestLine;
	}	
	
	public static GraphPoint[] createPoints() {
	
		int npoints = 100;
		System.out.println("Points on Graph\n***************");
		GraphPoint[] points = new GraphPoint[npoints - 1];
	
		for (int i = 0; i < npoints / 2; i++) {
	
			GraphPoint p = new GraphPoint(i, 2.3 * ((double)i) + 20.0);
			points[i] = p;
			System.out.println(p.toString());
		}
	
		for (int i = 0; i < npoints / 2 - 1; i++) {
	
			GraphPoint p = new GraphPoint(i, 3.0 * ((double)i) + 1.0);
			points[npoints / 2 + i] = p;
			System.out.println(p.toString());
		}
		System.out.println("****************\n");
		return points;
	}
	/*7-6*/






	/*7-7*/


	/*solution-a*/
	public static int removeMin(Queue<Integer> q) {
		int min = q.peek();
		for (Integer v : q) {
			if (min > v) {
				min = v;
			}
		}
		while (q.contains(min)) {
			q.remove(min);
		}
		return min;
	}
	
	public static void addProducts(Queue<Integer> q, int v) {
		q.add(v * 3);
		q.add(v * 5);
		q.add(v * 7);
	}
	
	public static int getKthMagicNumber(int k) {
		if (k < 0) {
			return 0;
		}
		int val = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		addProducts(q, 1);
		for (int i = 0; i < k; i++) { // Start at 1 since we've already done one iteration
			val = removeMin(q);
			addProducts(q, val);
		}
		return val;
	}
	/*END of solution-a*/





	/*solution-b*/
	public static void printQueue(Queue<Integer> q, int x) {
		System.out.print(x + ": ");
		for (Integer a : q) {
			System.out.print(a / x + ", ");
		}
		System.out.println("");
	}
	
	public static int getKthMagicNumber(int k) {
		if (k < 0) {
			return 0;
		}
		int val = 0;
		Queue<Integer> queue3 = new LinkedList<Integer>();
		Queue<Integer> queue5 = new LinkedList<Integer>();
		Queue<Integer> queue7 = new LinkedList<Integer>();
		queue3.add(1);
		for (int i = 0; i <= k; i++) { // Include 0th iteration through kth iteration
			int v3 = queue3.size() > 0 ? queue3.peek() : Integer.MAXVALUE; 
			int v5 = queue5.size() > 0 ? queue5.peek() : Integer.MAXVALUE;
			int v7 = queue7.size() > 0 ? queue7.peek() : Integer.MAXVALUE;
			val = Math.min(v3, Math.min(v5, v7));
			if (val == v3) {
				queue3.remove();
				queue3.add(3 * val);
				queue5.add(5 * val);
			} else if (val == v5) {
				queue5.remove();
				queue5.add(5 * val);
			} else if (val == v7) {
				queue7.remove();
			}
			queue7.add(7 * val);
		}
		return val;
	}
	
	/*END of solution-b*/
	/*7-7*/










	/*question: SieveOfEratosthenes*/

	public static void crossOff(boolean[] flags, int prime) {
		/* Cross off remaining multiples of prime. We can start with
		 * (prime*prime), because if we have a k * prime, where k < prime,
		 * this value would have already been crossed off in a prior
		 * iteration. */		
		for (int i = prime * prime; i < flags.length; i += prime) {
			flags[i] = false;
		}
	}
	
	public static int getNextPrime(boolean[] flags, int prime) {
		int next = prime + 1;
		while (next < flags.length && !flags[next]) {
			next++;
		}
		return next;	
	}
	
	public static void init(boolean[] flags) {
		flags[0] = false;
		flags[1] = false;
		for (int i = 2; i < flags.length; i++) {
			flags[i] = true;
		}
	}
	
	public static int[] prune(boolean[] flags, int count) {
		int[] primes = new int[count];
		int index = 0;
		for (int i = 0; i < flags.length; i++) {
			if (flags[i]) {
				primes[index] = i;
				index++;
			}
		}
		return primes;
	}
	
	public static boolean[] sieveOfEratosthenes(int max) {
        boolean[] flags = new boolean[max + 1];
        int count = 0;
        
		init(flags);
        int prime = 2;
        
        while (prime <= Math.sqrt(max)) {
        	count++;        	
        	crossOff(flags, prime);
        	prime = getNextPrime(flags, prime);
        	if (prime >= flags.length) {
        		break;
        	}
        }
        
        return flags; //prune(flags, count);
	}
	/*END of solution: SieveOfEratosthenes*/







	/* main method for the testing*/
	public static void main(String[] args) {
	

		System.out.println("Hello Math");	

		/*7-3*/
		for (int i = 0; i < 10; i++) {

			Line line1 = new Line(randomInt(5), randomInt(1));
			Line line2 = new Line(randomInt(5), randomInt(2));
			line1.print();
			System.out.print(", ");
			line2.print();

			if (line1.intersect(line2)) {
				System.out.println("  YES");
			}
			 
			else {
				System.out.println("  NO");
			}
		}
		/*7-3*/	

		/*7-4*/
		int q = multiply(-5, -10);
		System.out.println(q);
		
		for (int i = 0; i < 100; i++) {


			int a = randomInt(10);
			int b = randomInt(10);
			int ans = minus(a, b);
			if (ans != a - b) {
				System.out.println("ERROR");
			}
			System.out.println(a + " - " + b + " = " + ans);
		}

		for (int i = 0; i < 100; i++) {
		
			int a = randomInt(10);
			int b = randomInt(10);
			int ans = multiply(a, b);
			if (ans != a * b) {
				System.out.println("ERROR");
			}
		
			System.out.println(a + " * " + b + " = " + ans);
		}

		for (int i = 0; i < 100; i++) {
		
			int a = randomInt(10) + 1;
			int b = randomInt(10) + 1;
			System.out.print(a + " / " + b + " = ");
			int ans = divide(a, b);

			if (ans != a / b) {
			
				System.out.println("ERROR");
			}

			System.out.println(ans);
		}
		/*7-4*/




		/*7-5*/
		/* For an easy way to test these, open up Square Cut Tester.xlsx
		 * in the Chapter 7, Problem 5 folder. Copy and paste the exact 
		 * output from below into the file (including all tabs).
		 */
		doTests();
		/*7-5*/




		/*7-6*/
		GraphPoint[] points = createPoints();
		Line line = findBestLine(points);
		line.Print();
		/*7-6*/





		/*7-7*/

		/*solution-a*/
		for (int i = 0; i < 14; i++) {
			System.out.println(i + " : " + getKthMagicNumber(i));
		}
		/*END of solution-a*/



		/*solution-b*/
		for (int i = 0; i < 14; i++) {
		
			System.out.println(i + " : " + getKthMagicNumber(i));
		}
		/*END of solution-b*/
		/*7-7*/





		/*Solution: SieveOfEratosthenes*/
		boolean[] primes = sieveOfEratosthenes(100);

		for (int i = 0; i < primes.length; i++) {
		
			if (primes[i]) {
		
				System.out.println(i);
			}
		}
		/*Solution: SieveOfEratosthenes*/
	}	
}