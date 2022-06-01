import java.util.*; 
import java.util.stream.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map.Entry; 
import java.util.Map; 
import java.util.regex.*;
import java.lang.StringBuilder;


public class myRecur {

	/*Fibonacci*/

	/*solution-a*/
	public static int fibonacci(int i) {

		if (i == 0) {
			return 0;
		}
		if (i == 1) {
			return 1;
		}
		return fibonacci(i - 1) + fibonacci(i - 2);
	}
	
	/*solution-a*/

	/*solution-b*/
	public static int max = 100; // Make this as big as you want! (Though you'll exceed the bounds of a long around 46)
	public static int[] fib = new int[max];

	public static int fibonacci(int i) {
		if (i == 0) {
			return 0;
		}
		if (i == 1) {
			return 1;
		}
		if (fib[i] != 0) {
			return fib[i];
		}
		fib[i] = fibonacci(i - 1) + fibonacci(i - 2);
		return fib[i];
	}
	/*solution-b*/
	/*Fibonacci*/


	/*9-1*/
	public static int countWaysDP(int n, int[] map) {

		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (map[n] > -1) {
			return map[n];
		} else {
			map[n] = countWaysDP(n - 1, map) + 
					 countWaysDP(n - 2, map) + 
					 countWaysDP(n - 3, map);
			return map[n];
		}
	}
	
	public static int countWaysRecursive(int n) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return countWaysRecursive(n - 1) + countWaysRecursive(n - 2) + countWaysRecursive(n - 3);
		}
	}
	/*9-1*/









	/*9-2*/

	/*solution-a*/
	public static int size = 4;
	public static int[][] maze = new int[size][size];
	
	public static boolean isFree(int x, int y) {

		if (maze[x][y] == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean getPath(int x, int y, ArrayList<Point> path) {

		// If out of bounds or not available, return.
		if (y < 0 || x < 0 || !isFree(x, y)) {
			return false;
		}
		
		boolean isAtOrigin = (x == 0) && (y == 0);
		
		// If there's a path from the start to my current location, add my location.
		if (isAtOrigin || getPath(x, y - 1, path) || getPath(x - 1, y, path)) { 
			Point p = new Point(x, y);
			path.add(p);
			return true;
		}
		
		return false;
	}	

	public static boolean getPath(int x, int y, ArrayList<Point> path, Hashtable<Point, Boolean> cache) {
		/* If out of bounds or not available, return.*/
		if (y < 0 || x < 0 || !isFree(x, y)) {
			return false;
		}
		Point p = new Point(x, y);
		
		/* If we've already visited this cell, return. */
		if (cache.containsKey(p)) { 
			return cache.get(p);
		}	
		
		boolean isAtOrigin = (x == 0) && (y == 0);
		boolean success = false;
		
		/* If there's a path from the start to my current location, add my location.*/
		if (isAtOrigin || getPath(x, y - 1, path, cache) || getPath(x - 1, y, path, cache)) {
			path.add(p);
			success = true;
		}
		
		cache.put(p, success); // Cache result
		return success;
	}
	
	/*END of solution-a*/






	/*solution-b*/
	public static int[][] maze = new int[10][10];
	
	public static boolean isFree(int x, int y) {
		if (maze[x][y] == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean getPath(int x, int y, ArrayList<Point> path) {
		// If out of bounds or not available, return.
		if (y < 0 || x < 0 || !isFree(x, y)) {
			return false;
		}
		
		boolean isAtOrigin = (x == 0) && (y == 0);
		
		// If there's a path from the start to my current location, add my location.
		if (isAtOrigin || getPath(x, y - 1, path) || getPath(x - 1, y, path)) { 
			Point p = new Point(x, y);
			path.add(p);
			return true;
		}
		
		return false;
	}
	
	/*solution-b*/
	/*9-2*/










	/*9-3*/
	/*solution-a*/
	public static int magicSlow(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i) {
				return i;
			}
		}
		return -1;
	}
	
	public static int magicFast(int[] array, int start, int end) {
		if (end < start || start < 0 || end >= array.length) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (array[mid] == mid) {
			return mid;
		} else if (array[mid] > mid){
			return magicFast(array, start, mid - 1);
		} else {
			return magicFast(array, mid + 1, end);
		}
	}
	
	public static int magicFast(int[] array) {
		return magicFast(array, 0, array.length - 1);
	}
	
	/* Creates an array that is distinct and sorted */
	public static int[] getDistinctSortedArray(int size) {
		int[] array = AssortedMethods.randomArray(size, -1 * size, size);
		Arrays.sort(array);
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[i-1]) {
				array[i]++;
			} else if (array[i] < array[i - 1]) {
				array[i] = array[i-1] + 1;
			}
		}
		return array;
	}
	/*solution-a*/



	/*solution-b*/
	public static int magicSlow(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i) {
				return i;
			}
		}
		return -1;
	}
	
	public static int magicFast(int[] array, int start, int end) {
		if (end < start || start < 0 || end >= array.length) {
			return -1;
		}
		int midIndex = (start + end) / 2;
		int midValue = array[midIndex];
		if (midValue == midIndex) {
			return midIndex;
		}
		/* Search left */
		int leftIndex = Math.min(midIndex - 1, midValue);
		int left = magicFast(array, start, leftIndex);
		if (left >= 0) {
			return left;
		}
		
		/* Search right */
		int rightIndex = Math.max(midIndex + 1, midValue);
		int right = magicFast(array, rightIndex, end);
		
		return right;
	}
	
	public static int magicFast(int[] array) {
		return magicFast(array, 0, array.length - 1);
	}
	
	/* Creates an array that is sorted */
	public static int[] getSortedArray(int size) {
		int[] array = AssortedMethods.randomArray(size, -1 * size, size);
		Arrays.sort(array);
		return array;
	}
	/*solution-b*/
	/*9-3*/







	/*9-4*/
	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {

		ArrayList<ArrayList<Integer>> allsubsets;

		if (set.size() == index) { // Base case - add empty set
			allsubsets = new ArrayList<ArrayList<Integer>>();
			allsubsets.add(new ArrayList<Integer>()); 
		} 


		else {
		
			allsubsets = getSubsets(set, index + 1);
			int item = set.get(index);
			ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
		
			for (ArrayList<Integer> subset : allsubsets) {
		
				ArrayList<Integer> newsubset = new ArrayList<Integer>();
				newsubset.addAll(subset); //
				newsubset.add(item);
				moresubsets.add(newsubset);
			}
			
			allsubsets.addAll(moresubsets);
		}
		return allsubsets;
	}
	
	public static ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
		ArrayList<Integer> subset = new ArrayList<Integer>(); 
		int index = 0;
		for (int k = x; k > 0; k >>= 1) {
			if ((k & 1) == 1) {
				subset.add(set.get(index));
			}
			index++;
		}
		return subset;
	}
	
	public static ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
		int max = 1 << set.size(); /* Compute 2^n */ 
		for (int k = 0; k < max; k++) {
			ArrayList<Integer> subset = convertIntToSet(k, set);
			allsubsets.add(subset);
		}
		return allsubsets;
	}
	/*9-4*/






	/*9-5*/
	public static ArrayList<String> getPerms(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> permutations = new ArrayList<String>();
		if (str.length() == 0) { // base case
			permutations.add("");
			return permutations;
		}
	            
		char first = str.charAt(0); // get the first character
		String remainder = str.substring(1); // remove the first character
		ArrayList<String> words = getPerms(remainder);
		for (String word : words) {
			for (int j = 0; j <= word.length(); j++) {
				String s = insertCharAt(word, first, j);
				permutations.add(s);
			}
		}
		return permutations;
	}
	
	public static String insertCharAt(String word, char c, int i) {
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}
	/*9-5*/








	/*9-6*/

	/*solution-a*/
	public static String insertInside(String str, int leftIndex) {
		String left = str.substring(0, leftIndex + 1);
		String right = str.substring(leftIndex + 1, str.length());
		return left + "()" + right;
	}
	
	public static Set<String> generateParens(int remaining) {
		Set<String> set = new HashSet<String>();
		if (remaining == 0) {
			set.add("");
		} else {
			Set<String> prev = generateParens(remaining - 1); 
			for (String str : prev) {
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == '(') {
						String s = insertInside(str, i);
						/* Add s to set if it is not already in there. Note: 	
						 * HashSet automatically checks for duplicates before
						 * adding, so an explicit check is not necessary. */
						set.add(s);			
					}
				}
				set.add("()" + str);
			}
		}
		return set;
	}
	/*solution-a*/


	/*solution-b*/
	public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count) {
		if (leftRem < 0 || rightRem < leftRem) return; // invalid state
		
		if (leftRem == 0 && rightRem == 0) { /* all out of left and right parentheses */
			String s = String.copyValueOf(str);
			list.add(s);
		} else {
			if (leftRem > 0) { // try a left paren, if there are some available
				str[count] = '(';
				addParen(list, leftRem - 1, rightRem, str, count + 1);
			}
			if (rightRem > leftRem) { // try a right paren, if there’s a matching left
				str[count] = ')';
				addParen(list, leftRem, rightRem - 1, str, count + 1);
			}
		}
	}
	
	public static ArrayList<String> generateParens(int count) {
		char[] str = new char[count*2];
		ArrayList<String> list = new ArrayList<String>();
		addParen(list, count, count, str, 0);
		return list;
	}
	/*solution-b*/
	/*9-6*/





	/*9-7*/

	public enum Color {
		Black, White, Red, Yellow, Green
	}
	
	public static String PrintColor(Color c) {
		switch(c) {
		case Black:
			return "B";
		case White:
			return "W";
		case Red:
			return "R";
		case Yellow:
			return "Y";
		case Green:
			return "G";
		}
		return "X";
	}
	
	public static void PrintScreen(Color[][] screen) {
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[0].length; j++) {
				System.out.print(PrintColor(screen[i][j]));
			}
			System.out.println();
		}
	}
	
	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}
	
	public static boolean PaintFill(Color[][] screen, int x, int y, Color ocolor, Color ncolor) {
		if (x < 0 || x >= screen[0].length || y < 0 || y >= screen.length) {
			return false;
		}
		if (screen[y][x] == ocolor) {
			screen[y][x] = ncolor;
			PaintFill(screen, x - 1, y, ocolor, ncolor); // left
			PaintFill(screen, x + 1, y, ocolor, ncolor); // right
			PaintFill(screen, x, y - 1, ocolor, ncolor); // top
			PaintFill(screen, x, y + 1, ocolor, ncolor); // bottom
		}
		return true;
	}
	
	public static boolean PaintFill(Color[][] screen, int x, int y, Color ncolor) {
		if (screen[y][x] == ncolor) return false;
		return PaintFill(screen, x, y, screen[y][x], ncolor);
	}
	
	/*9-7*/








	/*9-8*/
	public static int makeChange(int amount, int[] denoms, int index) {
		if (index >= denoms.length - 1) return 1; // one denom remaining -> one way to do it
		int denomAmount = denoms[index];
		int ways = 0;
		for (int i = 0; i * denomAmount <= amount; i++) {
			int amountRemaining = amount - i * denomAmount;
			ways += makeChange(amountRemaining, denoms, index + 1); // go to next denom
		}
		return ways;
	}
	
	public static int makeChange1(int n) {
		int[] denoms = {25, 10, 5, 1};
		return makeChange(n, denoms, 0);
	}

	public static int makeChange2(int n) {
		int[] denoms = {25, 10, 5, 1};
		int[][] map = new int[n + 1][denoms.length];
		return makeChange2(n, denoms, 0, map);
	}
	
	public static int makeChange2(int amount, int[] denoms, int index, int[][] map) {
		if (map[amount][index] > 0) { // retrieve value
			return map[amount][index];
		}
		if (index >= denoms.length - 1) return 1; // one denom remaining -> one way to do it
		int denomAmount = denoms[index];
		int ways = 0;
		for (int i = 0; i * denomAmount <= amount; i++) {
			// go to next denom, assuming i coins of denomAmount
			int amountRemaining = amount - i * denomAmount;
			ways += makeChange2(amountRemaining, denoms, index + 1, map);
		}
		map[amount][index] = ways;
		return ways;
	}	
	
	public static int makeChange(int n) {
		int x = makeChange1(n);
		int y = makeChange2(n);
		if (x != y) {
			System.out.println("Error: " + x + " " + y);
		}
		return x;
	}	
	/*9-8*/





	/*9-9*/
	public static int GRIDSIZE = 8;
	
	/* Check if (row1, column1) is a valid spot for a queen by checking if there
	 * is a queen in the same column or diagonal. We don't need to check it for queens
	 * in the same row because the calling placeQueen only attempts to place one queen at
	 * a time. We know this row is empty. 
	 */
	public static boolean checkValid(Integer[] columns, int row1, int column1) {
		for (int row2 = 0; row2 < row1; row2++) {
			int column2 = columns[row2];
			/* Check if (row2, column2) invalidates (row1, column1) as a queen spot. */
			
			/* Check if rows have a queen in the same column */
			if (column1 == column2) { 
				return false;
			}
			
			/* Check diagonals: if the distance between the columns equals the distance
			 * between the rows, then they're in the same diagonal.
			 */
			int columnDistance = Math.abs(column2 - column1); 
			int rowDistance = row1 - row2; // row1 > row2, so no need to use absolute value
		    if (columnDistance == rowDistance) {
		    	return false;
		    }
		}
		return true;
	}
	
	public static void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results) {
		if (row == GRIDSIZE) { // Found valid placement
			results.add(columns.clone()); 
		} else {
			for (int col = 0; col < GRIDSIZE; col++) {			
				if (checkValid(columns, row, col)) {
					columns[row] = col;	// Place queen
					placeQueens(row + 1, columns, results);	
				}		
			}
		}
	}
	
	public static void clear(Integer[] columns) {
		for (int i = 0; i < GRIDSIZE; i++) {
			columns[i] = -1;
		}
	}
	
	public static void printBoard(Integer[] columns) {
        drawLine();
        for(int i = 0; i < GRIDSIZE; i++){
			System.out.print("|");
			for (int j = 0; j < GRIDSIZE; j++){
			    if (columns[i] == j) {
			    	System.out.print("Q|");
			    } else {
			    	System.out.print(" |");
			    }
			}
            System.out.print("\n");
            drawLine();
		}
		System.out.println("");
	}

    private static void drawLine() {
        StringBuilder line = new StringBuilder();
        for (int i=0;i<GRIDSIZE*2+1;i++)
            line.append('-');
        System.out.println(line.toString());
    }


	
	public static void printBoards(ArrayList<Integer[]> boards) {
		for (int i = 0; i < boards.size(); i++) {
			Integer[] board = boards.get(i);
			printBoard(board);
		}
	}
	   
	/*9-9*/




	/* START THE TESTING OF THE METHIDS*/
	// =====================================
	public static void main(String[] args) {
		

		System.out.println("Hello Recursion");



		/*question: Fibonacci*/

		/*solution-a*/
		int max = 35; // WARNING: If you make this above 40ish, your computer may serious slow down.
		int trials = 10; // Run code multiple times to compute average time.
		double[] times = new double[max]; // Store times
		
		
		for (int j = 0; j < trials; j++) { // Run this 10 times to compute
			for (int i = 0; i < max; i++) {
				long start = System.currentTimeMillis();
				fibonacci(i);
				long end = System.currentTimeMillis();
				long time = end - start;
				times[i] += time; 
			}
		}
		
		for (int j = 0; j < max; j++) {
			System.out.println(j + ": " + times[j] / trials + "ms");
		}
		/*solution-a*/





		/*solution-b*/
		int trials = 10; // Run code multiple times to compute average time.
		double[] times = new double[max]; // Store times
		
		for (int j = 0; j < trials; j++) { // Run this 10 times to compute
			for (int i = 0; i < max; i++) {
				fib = new int[max];
				long start = System.currentTimeMillis();
				fibonacci(i);
				long end = System.currentTimeMillis();
				long time = end - start;
				times[i] += time; 
			}
		}
		
		for (int j = 0; j < max; j++) {
			System.out.println(j + ": " + times[j] / trials + "ms");
		}
		/*solution-b*/
		/*solution: Fibonacci*/









		/*9-1*/
		for (int i = 0; i < 30; i++) {
			long t1 = System.currentTimeMillis();
			int[] map = new int[30 + 1];
			for (int j = 0; j < map.length; j++) {
				map[j] = -1;
			}
			int c1 = countWaysDP(i, map);
			long t2 = System.currentTimeMillis();
			long d1 = t2 - t1;
			
			long t3 = System.currentTimeMillis();
			int c2 = countWaysRecursive(i);
			long t4 = System.currentTimeMillis();
			long d2 = t4 - t3;			
			System.out.println(i + " " + c1 + " " + c2 + " " + d1 + " " + d2);
		}
		/*9-1*/







		/*9-2*/
		/*solution-a*/
		maze = AssortedMethods.randomMatrix(size, size, 0, 5);
		AssortedMethods.printMatrix(maze);
		ArrayList<Point> path = new ArrayList<Point>();
		Hashtable<Point, Boolean> cache = new Hashtable<Point, Boolean>();
		boolean success = getPath(size - 1, size - 1, path, cache);
		
		if (success) {
			String s = AssortedMethods.listOfPointsToString(path);
			System.out.println("Path: " + " " + s);			
		} else {
			System.out.println("No path exists.");
		}
		/*solution-a*/




		/*solution-b*/
		int size = 5;
		maze = AssortedMethods.randomMatrix(size, size, 0, 5);
		
		AssortedMethods.printMatrix(maze);
		
		ArrayList<Point> path = new ArrayList<Point>();
		boolean success = getPath(size - 1, size - 1, path);
		if (success) {
			String s = AssortedMethods.listOfPointsToString(path);
			System.out.println(s);
		} else {
			System.out.println("No path found.");
		}
		/*solution-b*/
		/*9-2*/




		/*9-3*/

		/*solution-a*/
		for (int i = 0; i < 1000; i++) {
			int size = AssortedMethods.randomIntInRange(5, 20);
			int[] array = getDistinctSortedArray(size);
			int v2 = magicFast(array);
			if (v2 == -1 && magicSlow(array) != -1) {
				int v1 = magicSlow(array);
				System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			} else if (v2 > -1 && array[v2] != v2) {
				System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			}
		}
		/*solution-a*/



		/*solution-b*/
		for (int i = 0; i < 1000; i++) {
			int size = AssortedMethods.randomIntInRange(5, 20);
			int[] array = getSortedArray(size);
			int v2 = magicFast(array);
			if (v2 == -1 && magicSlow(array) != -1) {
				int v1 = magicSlow(array);
				System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			} else if (v2 > -1 && array[v2] != v2) {
				System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			}
		}
		/*soluton-b*/
		/*9-3*/




		/*9-4*/
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		ArrayList<ArrayList<Integer>> subsets = getSubsets(list, 0);
		System.out.println(subsets.toString());
		
		ArrayList<ArrayList<Integer>> subsets2 = getSubsets2(list);
		System.out.println(subsets2.toString());	
		/*9-4*/






		/*9-5*/
		ArrayList<String> list = getPerms("abcde");
		System.out.println("There are " + list.size() + " permutations.");
		
		for (String s : list) {
		
			System.out.println(s);
		}
		/*9-5*/





		/*9-6*/
		/*solution-a*/
		Set<String> list = generateParens(4);
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println(list.size());
		/*solution-a*/



		/*solution-b*/
		ArrayList<String> list = generateParens(3);
		
		for (String s : list) {

			System.out.println(s);
		}

		System.out.println(list.size());
		/*solution-b*/
		/*9-6*/




		/*9-7*/
		int N = 10;
		Color[][] screen = new Color[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				screen[i][j] = Color.Black;
			}			
		}
		for (int i = 0; i < 100; i++) {
			screen[randomInt(N)][randomInt(N)] = Color.Green;
		}
		PrintScreen(screen);
		PaintFill(screen, 2, 2, Color.White);
		System.out.println();
		PrintScreen(screen);
		/*9-7*/





		/*9-8*/
		for (int i = 0; i <= 100; i++) {
			System.out.println("makeChange(" + i + ") = " + makeChange(i));
		}
		/*9-8*/




		/*9-9*/
		ArrayList<Integer[]> results = new ArrayList<Integer[]>();
		Integer[] columns = new Integer[GRIDSIZE];
		clear(columns);
		placeQueens(0, columns, results);
		printBoards(results);
		System.out.println(results.size());
		/*9-9*/
	}
}