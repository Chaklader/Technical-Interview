import java.util.*;
import java.util.regex.*;
import java.io.*; 
import java.text.*;





// 3 

/*^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$

^		 #start of the line
 #		 #  must constains a "#" symbols
 (		 #  start of group #1
  [A-Fa-f0-9]{6} #    any strings in the list, with length of 6
  |		 #    ..or
  [A-Fa-f0-9]{3} #    any strings in the list, with length of 3
 )		 #  end of group #1
$		 #end of the line*/


class HexValidator{

		private Pattern pattern;
		private Matcher matcher;

		private static final String HEXPATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

		public HexValidator(){

			pattern = Pattern.compile(HEXPATTERN);
		}


		/**
		* Validate hex with regular expression
		* @param hex hex for validation
		* @return true valid hex, false invalid hex
		*/
		public boolean validate(final String hex){

			matcher = pattern.matcher(hex);
			return matcher.matches();

		}
}





class HexValidatorTest {



		private HexValidator hexValidator;

		@BeforeClass
        public void initData(){
			
			hexValidator = new HexValidator();
        }


		@DataProvider
		public Object[][] ValidHexProvider() {

			return new Object[][]{
				   {new String[] {
					"#1f1f1F", "#AFAFAF","#1AFFa1","#222fff", "#F00"
				   }}
			};
		}


		@DataProvider
		public Object[][] InvalidHexProvider() {

			return new Object[][]{

				{new String[] {
					   "123456","#afafah","#123abce","aFaE3f",
					   "F00","#afaf", "#F0h"
			        }}
			};
		}


		@Test(dataProvider = "ValidHexProvider")
		public void ValidHexTest(String[] hex) {

		   for(String temp : hex){

			   boolean valid = hexValidator.validate(temp);
			   System.out.println("Hex is valid : " + temp + " , " + valid);
			   Assert.assertEquals(true, valid);
		   }

		}

		@Test(dataProvider = "InvalidHexProvider", dependsOnMethods="ValidHexTest")
		public void InValidHexTest(String[] hex) {

		   for(String temp : hex){

			   boolean valid = hexValidator.validate(temp);
			   System.out.println("Hex is valid : " + temp + " , " + valid);
			   Assert.assertEquals(false, valid);
		   }

		}
}




public class myRegex {


		// JAVA REGULAR EXPRESSION 
		// -----------------------


		// .      Matches any character</entry>
		//  *     Occurs zero or more times, is short for  {0,}
		//  +     Occurs one or more times   {1,}
		// ?	  Occurs no or one times, ? is short for {0,1}.


	    // \d will represent a digit (0-9) and \D will represent a non digit
	    //  *?    It tries to find the smallest match. This makes the regular expression stop at the first match.
	    // ^regex   Finds regex that must match at the beginning of the line.
	    

	    // [^abc]    When a caret appears as the first character inside square brackets, 
	    // it negates the pattern. This pattern matches any character except a or b or c.


	    //  [a-d1-7]   Ranges: matches a letter between a and d and figures from 1 to 7, but not d1.
	    // [abc][vz]		Set definition, can match a or b or c followed by either v or z.



	    // \d  Any digit, short for [0-9]
		// \D  A non-digit, short for [^0-9]
		// \s  A whitespace character, short for [ \t\n\x0b\r\f]
		// \S  A non-whitespace character, short for
		// \w  A word character, short for [a-zA-Z0-9]
		// \W  A non-word character [^\w]
		// \S+  Several non-whitespace characters</entry>
		// \b  Matches a word boundary where a word character is [a-zA-Z0-9]




		/*
		^	Matches beginning of line.

		$	Matches end of line.

		.	Matches any single character except newline. Using m option allows it to match newline as well.
		
		[...]	Matches any single character in brackets.
		
		[^...]	Matches any single character not in brackets
		
		\A	Beginning of entire string
		
		\z	End of entire string
		\Z	End of entire string except allowable final line terminator.
		re*	Matches 0 or more occurrences of preceding expression.

		re+	Matches 1 or more of the previous thing

		re?	Matches 0 or 1 occurrence of preceding expression.
		re{ n}	Matches exactly n number of occurrences of preceding expression.
		re{ n,}	Matches n or more occurrences of preceding expression.
		re{ n, m}	Matches at least n and at most m occurrences of preceding expression.
		a| b	Matches either a or b.

		(re)	Groups regular expressions and remembers matched text.

		(?: re)	Groups regular expressions without remembering matched text.

		(?> re)	Matches independent pattern without backtracking.

		\w	Matches word characters.

		\W	Matches nonword characters.

		\s	Matches whitespace. Equivalent to [\t\n\r\f].

		\S	Matches nonwhitespace.

		\d	Matches digits. Equivalent to [0-9].

		\D	Matches nondigits.
		\A	Matches beginning of string.
		\Z	Matches end of string. If a newline exists, it matches just before newline.
		\z	Matches end of string.
		\G	Matches point where last match finished.
		\n	Back-reference to capture group number "n"
		
		\b	Matches word boundaries when outside brackets. Matches backspace (0x08) when inside brackets.
		
		\B	Matches nonword boundaries.
		
		\n, \t, etc.	Matches newlines, carriage returns, tabs, etc.
		

		\Q	Escape (quote) all characters up to \E

		\E	Ends quoting begun with \Q
		*/





	    // .{3}\.     

	    /*Match	cat.	Success
		Match	896.	Success
		Match	?=+.*/




		// [cmf]an    can , man , fan 


		// [^b]og ,   [hd]og 

		// [A-C][n-p][a-c] 

		// waz{3,5}up      	wazzzzzup 




		//  aa+b*c+
		// a{2,4}b{0,4}c{1,2}

		// ab?c will match either the strings "abc" or "ac" 
		// \d+ files? found\?



		//  \d\.\s+abc
		// 1.   abc     2.      abc      3.  		 abc




		// ^Mission: successful$



		// ^(IMG\d+\.png)$ to capture and extract the full filename, 
		// filename without the extension, pattern ^(IMG\d+)\.png$ which only captures the part before the period.


		// ^(file.+)\.pdf$
		//  (\w+ (\d+))    				Jan 1987    Mar 2015



		// (\d+)x(\d+)    1235x1456








	   public static void checkRegularExpression( ) {
	      
	        Pattern pattern = Pattern.compile(".*[^0-9].*");


	        String [] inputs = {"123", "-123" , "123.12", "abcd123"};
	 
			for ( String input: inputs){

					System.out.println( "does " + input + " is number : "
			                    + !pattern.matcher(input).matches() );
			}

			String [] numbers = {"123", "1234" , "123.12", "abcd123", "123456"};
			Pattern digitPattern = Pattern.compile("\\d{6}");       


			for ( String number: numbers ){

			 System.out.println( "does " + number + " is 6 digit number : "
			                   + digitPattern.matcher(number).matches());
			}

	    }






	    public static void brackets(){


			String str = "obj[attr1=val1 attr2=val2 attr3=val3]";
			String regex = "(?:\\b)(\\w+?)=(\\w+?)(?:\\b)";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);

			while (matcher.find()) {

			    System.out.println(matcher.group(1) + " : " + matcher.group(2));
			}
		}





		public static pipesSeperators( ) {


	        // Define regex to find the word 'quick' or 'lazy' or 'dog'
	        String regex = "(quick)|(lazy)|(dog)";
	        String text = "the quick brown fox jumps over the lazy dog";

	        // Obtain the required matcher
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(text);

	        int groupCount = matcher.groupCount();
	        System.out.println("Number of group = " + groupCount);

	        // Find every match and print it
	        while (matcher.find()) {
	            for (int i = 0; i <= groupCount; i++) {
	                // Group i substring
	                System.out.println("Group " + i + ": " + matcher.group(i));
	            }
	        }
	    }






		public static void splitString( ){


			String sampleString = "Cat    Dog \t Elephant \n Fox \r\n Goat";
			String[] animals = sampleString.split("\\s+");
			int animalIndex = 1;


			for (String animal : animals) {

				System.out.println(animalIndex + ". " + animal);
				animalIndex++;
			}
	    }





		public static void replaceWord(){

			String input = 
	                  "User clientId=23421. Some more text clientId=33432. This clientNum=100";

	        /*
		        (clientId=) 	group everything within the parenthesis as group 1
				clientId=	match the text ‘clientId=’
				(\\d+)		group everything within the parenthesis as group 2
				\\d+		match one or more digits
			*/

			Pattern p = Pattern.compile("(clientId=)(\\d+)");
			Matcher m = p.matcher(input);

			StringBuffer result = new StringBuffer();

			while (m.find()) {

				System.out.println("Masking: " + m.group(2));
				m.appendReplacement(result, m.group(1) + "***masked***");
			}

			m.appendTail(result);
			System.out.println(result);
		}





		public static void checkAnimal(){


			String input = "I have a cat, but I like my dog better.";

			/*
				()		group everything within the parenthesis as group 1
				mouse		match the text ‘mouse’
				|		alternation: match any one of the sections of this group
				cat		match the text ‘cat’
			*/

			Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
			Matcher m = p.matcher(input);

			List<String> animals = new ArrayList<String>();
			
			while (m.find()) {

				System.out.println("Found a " + m.group() + ".");
				animals.add(m.group());
			}

		}







		public static void checkSocialSecurity(){


			List<String> input = new ArrayList<String>();
			input.add("123-45-6789");
			input.add("9876-5-4321");
			input.add("987-65-4321 (attack)");
			input.add("987-65-4321 ");
			input.add("192-83-7465");


			/*
				^		match the beginning of the line
				() 		group everything within the parenthesis as group 1
				\d{n}		match n digits, where n is a number equal to or greater than zero
				-?		optionally match a dash
				$		match the end of the line
			*/

			for (String ssn : input) {

				if (ssn.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")) {

					System.out.println("Found good SSN: " + ssn);
				}
			}
		}









		// 1 

		public static final String EXAMPLETEST = "This is my small example "
	                        + "string which I'm going to " + "use for pattern matching.";

	    public static void seperateWords( ) {

	            System.out.println("Matching info : "+ EXAMPLETEST.matches("\\w.*"));
	            String[] splitString = (EXAMPLETEST.split("\\s+"));
	            System.out.println(splitString.length);// should be 14

	            for (String string : splitString) {

	                    System.out.println(string);
	            }

	            // replace all whitespace with tabs
	            System.out.println(EXAMPLETEST.replaceAll("\\s+", "\t"));
	    }



	    //  2 
	    public static void multipleWordMatches(){


	    	String s = "humbapumpa jim";
	        System.out.println(s.matches(".*(jim|joe).*"));
	    }





	    public static void test100(){

	    	String line = "This order was placed for QT3000! OK?";
			Pattern pattern = Pattern.compile("(.*?)(\\d+)(.*)");

			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
			    System.out.println("group 1: " + matcher.group(1));
			    System.out.println("group 2: " + matcher.group(2));
			    System.out.println("group 3: " + matcher.group(3));
			}

			/*group 1: This order was placed for QT
			group 1: This order was placed for QT
			group 2: 3000
			group 3: ! OK?*/
	    }



		/*question: write the method to find whether a String is 
		postion integer and formed of 6-digits using regex*/

    	// \d will represent a digit (0-9)
    	// \D will represent a non digit 
    	// "\\s+" to  for secaping one or more whietspaces 
	   
		public static void checkRegularExpression( ) {


			Pattern pattern = Pattern.compile(".*[^0-9].*");


			String [] inputs = {"123", "-123" , "123.12", "abcd123"};


			for ( String input: inputs){

				System.out.println( "Does " + input + " is position integer ? "
				+ !pattern.matcher(input).matches() );
			}


			// If String is 6 digit number or not
			String [] numbers = {"123", "1234" , "123.12", "abcd123", "123456"};
			Pattern digitPattern = Pattern.compile("\\d{6}");   
			//Pattern digitPattern = Pattern.compile("\\d\\d\\d\\d\\d\\d");    


			for ( String number: numbers ){

				System.out.println( "does " + number + " is 6 digit number : "
				+ digitPattern.matcher(number).matches());
			}
		}
	    /*END of solution: write the method to find whether a String is 
		postion integer and formed of 6-digits using regex*/
	   






		/*question: write the method to split the 
		words from the String using regex*/
		public static void splitString( ){


			String sampleString = "Cat    Dog \t Elephant \n Fox \r\n Goat";

			String[] animals = sampleString.split("\\s+");

			int animalIndex = 1;

			for (String animal : animals) {

				System.out.println(animalIndex + ". " + animal);
				animalIndex++;
			}
	    }
	    /*END of solution: write the method to split the 
		words from the String using regex*/






		/*question: write the method using regex*/
		public static void replaceWord(){


			String input = "User clientId=23421. Some more text clientId=33432. This clientNum=100";


	        /*
		        (clientId=) 	group everything within the parenthesis as group 1
				clientId=		match the text ‘clientId=’
				(\\d+)			group everything within the parenthesis as group 2
				\\d+			match one or more digits
			*/


			Pattern p = Pattern.compile("(clientId=)(\\d+)");
			Matcher m = p.matcher(input);

			StringBuffer result = new StringBuffer();

			while (m.find()) {

				System.out.println("Masking: " + m.group(2));
				m.appendReplacement(result, m.group(1) + "***masked***");
			}

			m.appendTail(result);
			System.out.println(result);

		}
		/*END of solution: write the method using regex*/






		public static void checkAnimal(){


			String input = "I have a cat, but I like my dog better.";

			/*
				()		group everything within the parenthesis as group 1
				mouse		match the text ‘mouse’
				|		alternation: match any one of the sections of this group
				cat		match the text ‘cat’
			*/

			Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
			Matcher m = p.matcher(input);

			List<String> animals = new ArrayList<String>();

			while (m.find()) {
			
				System.out.println("Found a " + m.group() + ".");
				animals.add(m.group());
			}
		}





		public static void checkSocialSecurity(){

			List<String> input = new ArrayList<String>();
			input.add("123-45-6789");
			input.add("9876-5-4321");
			input.add("987-65-4321 (attack)");
			input.add("987-65-4321 ");
			input.add("192-83-7465");


			/*
				^		match the beginning of the line
				() 		group everything within the parenthesis as group 1
				\d{n}		match n digits, where n is a number equal to or greater than zero
				-?		optionally match a dash
				$		match the end of the line
			*/

			for (String ssn : input) {

				if (ssn.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")) {

					System.out.println("Found good SSN: " + ssn);
				}
			}
		}






		// 1 
		/*^[a-z0-9-]{3,15}$

		^                    # Start of the line
		  [a-z0-9-]	     # Match characters and symbols in the list, a-z, 0-9 , underscore , hyphen
		             {3,15}  # Length at least 3 characters and maximum length of 15
		$                    # End of the line*/






		// 2 
		/*((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})

		(			# Start of group
		  (?=.*\d)		#   must contains one digit from 0-9
		  (?=.*[a-z])		#   must contains one lowercase characters
		  (?=.*[A-Z])		#   must contains one uppercase characters
		  (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
		              .		#     match anything with previous condition checking
		                {6,20}	#        length at least 6 characters and maximum of 20
		)			# End of group*/





		// 3 

		/*^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$

		^		 #start of the line
		 #		 #  must constains a "#" symbols
		 (		 #  start of group #1
		  [A-Fa-f0-9]{6} #    any strings in the list, with length of 6
		  |		 #    ..or
		  [A-Fa-f0-9]{3} #    any strings in the list, with length of 3
		 )		 #  end of group #1
		$		 #end of the line*/






		// 4

		/*^[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+
		(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$


		^			#start of the line
		  [A-Za-z0-9-]+	#  must start with string in the bracket [ ], must contains one or more (+)
		  (			#  start of group #1
		    \\.[A-Za-z0-9-]+	#     follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		  )*			#  end of group #1, this group is optional (*)
		    @			#     must contains a "@" symbol
		     [A-Za-z0-9]+       #        follow by string in the bracket [ ], must contains one or more (+)
		      (			#	   start of group #2 - first level TLD checking
		       \\.[A-Za-z0-9]+  #	     follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		      )*		#	   end of group #2, this group is optional (*)
		      (			#	   start of group #3 - second level TLD checking
		       \\.[A-Za-z]{2,}  #	     follow by a dot "." and string in the bracket [ ], with minimum length of 2
		      )			#	   end of group #3
		$			#end of the line*/







		// 5


		/*([^\s]+(\.(?i)(jpg|png|gif|bmp))$)
		
		(			#Start of the group #1
		 [^\s]+			#  must contains one or more anything (except white space)
		       (		#    start of the group #2
		         \.		#	follow by a dot "."
		         (?i)		#	ignore the case sensitive checking
		             (		#	  start of the group #3
		              jpg	#	    contains characters "jpg"
		              |		#	    ..or
		              png	#	    contains characters "png"
		              |		#	    ..or
		              gif	#	    contains characters "gif"
		              |		#	    ..or
		              bmp	#	    contains characters "bmp"
		             )		#	  end of the group #3
		       )		#     end of the group #2
		  $			#  end of the string
		)			#end of the group #1 */







		//  6

		/*^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.
		([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$
		^		#start of the line
		 (		#  start of group #1
		   [01]?\\d\\d? #    Can be one or two digits. If three digits appear, it must start either 0 or 1
				#    e.g ([0-9], [0-9][0-9],[0-1][0-9][0-9])
		    |		#    ...or
		   2[0-4]\\d	#    start with 2, follow by 0-4 and end with any digit (2[0-4][0-9])
		    |           #    ...or
		   25[0-5]      #    start with 2, follow by 5 and end with 0-5 (25[0-5])
		 )		#  end of group #2
		  \.            #  follow by a dot "."
		....            # repeat with 3 time (3x)
		$		#end of the line*/






		// 7

		/*(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)
		(				#start of group #1
		 1[012]				#  start with 10, 11, 12
		 |				#  or
		 [1-9]				#  start with 1,2,...9
		)				#end of group #1
		:				#    follow by a semi colon (:)
		[0-5][0-9]			#   follow by 0..5 and 0..9, which means 00 to 59
		        (\\s)?		#        follow by a white space (optional)
		              (?i)		#          next checking is case insensitive
		                  (am|pm)	#            follow by am or pm*/





    	//  8
		// ===


		/*(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)

		(			#start of group #1

		0?[1-9]		#  01-09 or 1-9
		
		|                  	#  ..or
		
		[12][0-9]		#  10-19 or 20-29
		
		|			#  ..or
		
		3[01]			#  30, 31
		
		) 			#end of group #1
		
		/			#  follow by a "/"
		
		(			#    start of group #2
		
		0?[1-9]		#	01-09 or 1-9
		
		|			#	..or
		
		1[012]		#	10,11,12
		
		)			#    end of group #2
		
		/			#	follow by a "/"
		
		(			#	  start of group #3
		
		(19|20)\\d\\d	#	    19[0-9][0-9] or 20[0-9][0-9]
		
		)		#	  end of group #3*/







		// 9

		/*<("[^"]*"|'[^']*'|[^'">])*>
		<	  	#start with opening tag "<"
		(		#   start of group #1
		"[^"]*"	#	only two double quotes are allow - "string"
		|		#	..or
		'[^']*'	#	only two single quotes are allow - 'string'
		|		#	..or
		[^'">]	#	cant contains one single quotes, double quotes and ">"
		)		#   end of group #1
		*		# 0 or more
		>		#end with closing tag ">"*/






		// 10

		/*(?i)<a([^>]+)>(.+?)</a>
		(		#start of group #1
		?i		#  all checking are case insensive
		)		#end of group #1
		<a              #start with "<a"
		(		#  start of group #2
		[^>]+	#     anything except (">"), at least one character
		)		#  end of group #2
		>		#     follow by ">"
		(.+?)	#	match anything
		</a>	#	  end with "</a>*/




        //  11

        /*\s*(?i)href\s*=\s*(\"([^"]*\")|'[^']*'|([^'">\s]+));

		\s*			   #can start with whitespace
		(?i)			   # all checking are case insensive
		href		   #  follow by "href" word
		\s*=\s*		   #   allows spaces on either side of the equal sign,
		(		   #    start of group #1
		"([^"]*")   #      only two double quotes are allow - "string"
		|	   #	  ..or
		'[^']*'	   #      only two single quotes are allow - 'string'
		|           #	  ..or
		([^'">]+)   #     cant contains one single / double quotes and ">"
		)		   #    end of group #1*/


		public static void main(String[] args) {


			Locale currentLocale = new Locale ("en","US");

			BreakIterator wordIterator =
			BreakIterator.getWordInstance(currentLocale);

			String someText = "She 1010. " +
			"She said, \"Hello there,\" and then went " +
			"on.";

			String test = "FLORENCE - FIRE/TOMORROW";

			BreakIteratorDemo bid = new BreakIteratorDemo();
			bid.extractWords(someText, wordIterator);

		}

}