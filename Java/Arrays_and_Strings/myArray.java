
import java.util.*; 
import java.util.stream.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map.Entry; 
import java.util.Map; 
import java.util.regex.*;
import java.lang.StringBuilder;


/*
* Q1: https://hastebin.com/wecujizuho.http
*/




// CRACKING_THE_CODING_INTERVIEW 
// -----------------------------

// #19, CTCI_4TH_EDITION = #17, CTCI_5TH_EDITION =  #16, CTCI_6TH_EDITION // MODERATE
// #20, CTCI_4TH_EDITION = #18, CTCI_5TH_EDITION =  #17, CTCI_6TH_EDITION // HARD


// I NEED TO SRAT FROM 19-9

/*Question 17-13:*/
class BiNode {


    public BiNode node1;
    public BiNode node2;
    public int data; 
    
    public BiNode(int d) {
        data = d;
    }
}


class QuestionA {

    
    private static class NodePair {

        BiNode head;
        BiNode tail;

        public NodePair(BiNode head, BiNode tail)  {

            this.head = head;
            this.tail = tail;
        }
    }
    
    public static NodePair convert(BiNode root) {

        if (root == null) {
            return null;
        }
        
        NodePair part1 = convert(root.node1);
        NodePair part2 = convert(root.node2);
        
        if (part1 != null) {
            concat(part1.tail, root);
        }
        
        if (part2 != null) {
            concat(root, part2.head);
        }
        
        return new NodePair(part1 == null? root : part1.head, part2 == null ? root : part2.tail);
    }   
    
    public static void concat(BiNode x, BiNode y) {

        x.node2 = y;
        y.node1 = x;
    }

    public static void printLinkedListTree(BiNode root) {

        // for (BiNode node = root; node != null; node = node.node2) {

        //     if (node.node2 != null && node.node2.node1 != node) {
        //         System.out.print("inconsistent node: " + node);
        //     }

        //     System.out.print(node.data + "->");
        // }

        BiNode node = root;

        while(node != null){

            if (node.node2 != null && node.node2.node1 != node) {
                System.out.print("inconsistent node: " + node);
            }

            System.out.print(node.data + "->");

            node = node.node2
        }

        System.out.println();
    }


    public static BiNode createTree() {

        BiNode[] nodes = new BiNode[7];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new BiNode(i);
        }

        nodes[4].node1 = nodes[2];
        nodes[4].node2 = nodes[5];
        nodes[2].node1 = nodes[1];
        nodes[2].node2 = nodes[3];
        nodes[5].node2 = nodes[6];
        nodes[1].node1 = nodes[0];
        return nodes[4];
    }

    public static void printAsTree(BiNode root, String spaces) {

        if (root == null) {
            System.out.println(spaces + "- null");
            return;
        }

        System.out.println(spaces + "- " + root.data);
        printAsTree(root.node1, spaces + "   ");
        printAsTree(root.node2, spaces + "   ");
    }

    public static void main(String[] args) {
        BiNode root = createTree();
        printAsTree(root, "");
        NodePair n = convert(root);
        printLinkedListTree(n.head);
    }

}

class QuestionB {

    static int count = 0;
    
    public static BiNode convert(BiNode root) {
    
        if (root == null) {
            return null;
        }
        
        BiNode part1 = convert(root.node1);
        BiNode part2 = convert(root.node2);
        
        if (part1 != null) {
            concat(getTail(part1), root);
        }
        
        if (part2 != null) {
            concat(root, part2);
        }
        
        return part1 == null ? root : part1;
    }   
    
    public static BiNode getTail(BiNode node) {
        if (node == null) {
            return null;
        }
        while (node.node2 != null) {
            count++;
            node = node.node2;
        }
        return node;
    }
    
    public static void concat(BiNode x, BiNode y) {
        x.node2 = y;
        y.node1 = x;
    }

    public static void printLinkedListTree(BiNode root) {

        for (BiNode node = root; node != null; node = node.node2) {
            if (node.node2 != null && node.node2.node1 != node) {
                System.out.print("inconsistent node: " + node);
            }
            System.out.print(node.data + "->");
        }

        System.out.println();
    }

    public static BiNode createTree() {

        BiNode[] nodes = new BiNode[7];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new BiNode(i);
        }

        nodes[4].node1 = nodes[2];
        nodes[4].node2 = nodes[5];
        nodes[2].node1 = nodes[1];
        nodes[2].node2 = nodes[3];
        nodes[5].node2 = nodes[6];
        nodes[1].node1 = nodes[0];

        return nodes[4];
    }

    public static void printAsTree(BiNode root, String spaces) {

        if (root == null) {
            System.out.println(spaces + "- null");
            return;
        }

        System.out.println(spaces + "- " + root.data);
        printAsTree(root.node1, spaces + "   ");
        printAsTree(root.node2, spaces + "   ");
    }

    public static void main(String[] args) {

        BiNode root = createTree();
        printAsTree(root, "");
        BiNode n = convert(root);
        printLinkedListTree(n);
        System.out.println(count);
    }
}




class QuestionC {

    public static void printAsTree(BiNode root, String spaces) {

        if (root == null) {
            System.out.println(spaces + "- null");
            return;
        }

        System.out.println(spaces + "- " + root.data);
        printAsTree(root.node1, spaces + "   ");
        printAsTree(root.node2, spaces + "   ");
    }
    
    public static BiNode createTree() {

        BiNode[] nodes = new BiNode[7];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new BiNode(i);
        }

        nodes[4].node1 = nodes[2];
        nodes[4].node2 = nodes[5];
        nodes[2].node1 = nodes[1];
        nodes[2].node2 = nodes[3];
        nodes[5].node2 = nodes[6];
        nodes[1].node1 = nodes[0];
        return nodes[4];
    }
    
    public static void printLinkedListTree(BiNode root) {

        for (BiNode node = root; node != null; node = node.node2) {
            if (node.node2 != null && node.node2.node1 != node) {
                System.out.print("inconsistent node: " + node);
            }

            System.out.print(node.data + "->");
        }

        System.out.println();
    }
    
    public static BiNode convertToCircular(BiNode root) {

        if (root == null) {
            return null;
        }
        
        BiNode part1 = convertToCircular(root.node1);
        BiNode part3 = convertToCircular(root.node2);
                
        if (part1 == null && part3 == null) {
            root.node1 = root;
            root.node2 = root;
            return root;
        }

        BiNode tail3 = part3 == null ? null : part3.node1;
        
        /* join left to root */
        if (part1 == null) {
            concat(part3.node1, root);
        } else {
            concat(part1.node1, root);
        }
        
        /* join right to root */
        if (part3 == null) {
            concat(root, part1);
        } else {
            concat(root, part3);
        }
        
        /* join right to left */
        if (part1 != null && part3 != null) {
            concat(tail3, part1);
        }
        
        return part1 == null ? root : part1;
    }
    
    public static BiNode convert(BiNode root) {
        BiNode head = convertToCircular(root);
        head.node1.node2 = null;
        head.node1 = null;
        return head;
    }
    
    public static void concat(BiNode x, BiNode y) {
        x.node2 = y;
        y.node1 = x;
    }
    
    public static void main(String[] args) {

        BiNode root = createTree();
        printAsTree(root, "");
        BiNode r = convert(root);
        printLinkedListTree(r);
    }
}
/*END of solution 17-13*/





/*question 19-9: Since XML is very verbose, you are given a way of encoding it where 
each tag gets mapped to a pre-defined integer value. The language/grammar is as follows:

Element --> Element Attr* END Element END [aka, encode the element
tag, then its attributes, then tack on an END character, then encode 
its children, then another end tag]

Attr --> Tag Value [assume all values are strings]

END --> 01

Tag --> some predefined mapping to int

Value --> string value END


Write code to print the encoded version of an xml element (passed in as string). Is 
there anything else you could do to (in many cases) compress this even further?*/


class Attribute {


    public String tag;
    public String value;

    public Attribute(String t, String v) {
        tag = t;
        value = v;
    }
    
    public String getTagCode() {

        if (tag == "family") {
            return "1";
        } else if (tag == "person") {
            return "2";
        } else if (tag == "firstName") {
            return "3";
        } else if (tag == "lastName") {
            return "4";
        } else if (tag == "state") {
            return "5";
        } 
        return "--";
    }
}

class Element {


    public ArrayList<Attribute> attributes;
    public ArrayList<Element> children;
    public String name;
    public String value;
    
    public Element(String n) {

        name = n;
        attributes = new ArrayList<Attribute>();
        children = new ArrayList<Element>();
    }
    
    public Element(String n, String v) {
        name = n;
        value = v;
        attributes = new ArrayList<Attribute>();
        children = new ArrayList<Element>();
    }   
    
    public String getNameCode() {

        if (name == "family") {
            return "1";
        } 

        else if (name == "person") {
            return "2";
        } 

        else if (name == "firstName") {
            return "3";
        } 

        else if (name == "lastName") {
            return "4";
        } 

        else if (name == "state") {
            return "5";
        } 
        return "--";
    }
    
    public void insert(Attribute attribute) {
        attributes.add(attribute);
    }
    
    public void insert(Element child) {
        children.add(child);
    }
}

class QuestionString {


    private Map<String, Byte> tagMap;
    private static final Byte[] END = { 0, 1 };

    private ArrayList<String> tokens;
    private int currentTokenIndex;

    public QuestionString(Map<String, Byte> tagMap) {this.tagMap = tagMap;}

    public byte[] encode(char[] input) throws IOException {


        // tokenize
        tokenize(input);
        currentTokenIndex = 0;

        // parse
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        encodeTokens(outputStream);
        return outputStream.toByteArray();
    }

    private void encodeTokens(ByteArrayOutputStream output) throws IOException {

        nextToken("<");

        String tagName = nextToken();
        output.write(getTagCode(tagName));

        // read attributes
        while (!hasNextToken(">") && !hasNextTokens("/", ">")) {
            // read next attribute
            String key = nextToken();
            nextToken("=");
            String value = nextToken();

            output.write(getTagCode(key));
            for (char c : value.toCharArray()) {
                output.write(c);
            }

            output.write(END[0]);
            output.write(END[1]);
        }

        // end of attributes
        output.write(END[0]);
        output.write(END[1]);

        // finish this element
        if (hasNextTokens("/", ">")) {
            nextToken("/");
            nextToken(">");
        } else {
            nextToken(">");
            // while not the end tag
            while (!hasNextTokens("<", "/")) {
                // encode child
                encodeTokens(output);
            }
            // ending tag
            nextToken("<");
            nextToken("/");
            nextToken(tagName);
            nextToken(">");
        }

        output.write(END[0]);
        output.write(END[1]);
    }

    private String nextToken() throws IOException {

        if (currentTokenIndex >= tokens.size()) {
            throw new IOException("Unexpected end of input.");
        }

        String token = tokens.get(currentTokenIndex);
        currentTokenIndex++;
        return token;
    }

    private void nextToken(String expectedToken) throws IOException {

        if (currentTokenIndex >= tokens.size()) {
            throw new IOException("Unexpected end of input.");
        }

        String token = tokens.get(currentTokenIndex);
        if (token.equals(expectedToken)) {
            currentTokenIndex++;
        } else {
            throw new IOException("Unexpected input. Expected '"
                    + expectedToken + "'; found '" + token + "'.");
        }
    }

    private boolean hasNextToken(String expectedToken) {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex).equals(expectedToken);
        } else {
            return false;
        }
    }

    private boolean hasNextTokens(String... expectedTokens) {
        if (currentTokenIndex + expectedTokens.length > 
            tokens.size()) {
            return false;
        }

        for (int i = 0; i < expectedTokens.length; i++) {
            if (!tokens.get(currentTokenIndex + i)
                    .equals(expectedTokens[i])) return false;
        }
        return true;
    }

    private void tokenize(char[] input) {

        tokens = new ArrayList<String>();
        int i = 0;

        while (i < input.length) {
            i = setNextToken(input, i);
        }
    }

    private int setNextToken(char[] input, int inputIndex) {

        int i = inputIndex;

        while (i < input.length && input[i] == ' ') i++;

        if (i == input.length) return i;

        // get 1 char token
        char c = input[i];
        if (c == '<' || c == '>' || c == '=' || c == '/') {
            tokens.add(String.valueOf(c));
            return i + 1;
        }

        // get multiple char token
        StringBuilder string = new StringBuilder();

        do {
            string.append(c);
            i++;
            c = input[i];
            if (c == '<' || c == '>' || c == '=' || 
                c == '/' || c == ' ') {
                break;
            }
        } while (i < input.length);

        tokens.add(string.toString());
        return i;
    }

    private byte getTagCode(String tag) throws IOException {

        Byte tagCode = tagMap.get(tag);

        if (tagCode == null) {
            throw new IOException("Unknown tag: " + tag);
        }
        return tagCode;
    }

    public static void testMain(String args[]) {

        try {

            Map<String, Byte> tagMap = new HashMap<String, Byte>();

            tagMap.put("a", (byte) 10);
            tagMap.put("root", (byte) 11);
            tagMap.put("href", (byte) 20);
            tagMap.put("target", (byte) 21);
            tagMap.put("name", (byte) 50);
            tagMap.put("id", (byte) 51);

            QuestionString encoder = new QuestionString(tagMap);
            String input;
            byte[] output;

            input = "<root></root>";
            output = encoder.encode(input.toCharArray());
            print(output);

            input = "<root id=a />";
            output = encoder.encode(input.toCharArray());
            print(output);

            input = "<root><a href=abc id=xyz></a><a></a></root>";
            output = encoder.encode(input.toCharArray());
            print(output);
        } 

        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void print(byte[] output) {
        for (byte b : output) {
            System.out.print(b);
            System.out.print(" ");
        }

        System.out.println();
    }
}
/*END of solution 19-9: Since XML is very verbose, you are given a way of 
encoding it where each tag gets mapped to a pre-defined integer value. The 
language/grammar is as follows:

Element --> Element Attr* END Element END [aka, encode the element
tag, then its attributes, then tack on an END character, then encode 
its children, then another end tag]

Attr --> Tag Value [assume all values are strings]

END --> 01

Tag --> some predefined mapping to int

Value --> string value END

Write code to print the encoded version of an xml element (passed in as 
string). Is there anything else you could do to (in many cases) compress 
this even further?*/









/*Question 17-14*/
class Result {


    public int invalid = Integer.MAX_VALUE;
    public String parsed = "";

    public Result(int inv, String p) {
        invalid = inv;
        parsed = p;
    }
    
    public Result clone() {
        return new Result(this.invalid, this.parsed);
    }
    
    public static Result min(Result r1, Result r2) {
        if (r1 == null) {
            return r2;
        } else if (r2 == null) {
            return r1;
        } 
        
        return r2.invalid < r1.invalid ? r2 : r1;
    }   
}
/*END of solition 17-14:*/




/*question: wirte a program with 
proper syntaxes of enum in Java*/
enum Day {

    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY 
}


class EnumTest {

    Day day;
    
    public EnumTest(Day day) {
        this.day = day;
    }
    
    public void tellItLikeItIs() {

        switch (day) {

            case MONDAY:
                System.out.println("Mondays are bad.");
                break;
                    
            case FRIDAY:
                System.out.println("Fridays are better.");
                break;
                         
            case SATURDAY:

            case SUNDAY:
                System.out.println("Weekends are best.");
                break;
                        
            default:
                System.out.println("Midweek days are so-so.");
                break;
        }
    }
    
    public static void test ( ) {

        EnumTest firstDay = new EnumTest(Day.MONDAY);
        firstDay.tellItLikeItIs();

        EnumTest thirdDay = new EnumTest(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();

        EnumTest fifthDay = new EnumTest(Day.FRIDAY);
        fifthDay.tellItLikeItIs();

        EnumTest sixthDay = new EnumTest(Day.SATURDAY);
        sixthDay.tellItLikeItIs();

        EnumTest seventhDay = new EnumTest(Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }    
}
/*END of solution: wirte a program with 
proper syntaxes of enum in Java*/




// SPOTIBLE TEST FOR ON-SITE INTERVIEW 
/*Question: design a DS class with 2 methods namely 
add and get with the following definition:

a. public void add(String str); 
==============================
The add method will take a String, encode it as String by 
converting the Characters as their mirror images such as  
('A' -> 'Z', 'Y' -> 'B', 'W' -> 'D'), convert the String 
to phone number (as int value) and store the String 
against the phone number.

b. public String get(int index);

The get method will take the index (as int), search for 
all the Strings that matches with the index and return 
as concatenated comma separated String of all the 
matched Strings.*/

interface spotIn {

    public void add(String str);
    public String get(int index); 
}



class DS implements spotIn {


    private Map<String, Integer> storage;

    public DS() {
        storage = new LinkedHashMap<>();
    }

    public void add(String str){
        
        if(str == null || str.length() == 0)
            return;

        String encode = encodeString(str);

        if(encode == null || encode.length() == 0)
            return;
        
        int index = convertToNumber(encode);

        if(index == -1)
            return; 

        storage.put(str, index);
    }

    public String get(int index) {

        String temp = "";

        for (Map.Entry<String, Integer> entry : storage.entrySet()) {

            if (entry.getValue() == index) {
                temp += entry.getKey() + ",";
            }
        }

        temp = temp.replaceAll(",$", "");
        return temp;
    }

    public String encodeString(String str) {

        if(str == null || str.length() == 0){
            return null;
        }
        
        char[] ch = str.toUpperCase().toCharArray();
        char[] result = new char[ch.length];

        int count = 0;

        for (char c : ch) {

            int value = (int) c;
            
            if ( value < 65 || value > 90)
                return null;
            
            char mirror;
            int distanceFrom65 = value - 65, distanceTo90 = 90 - value;
            
            if (distanceFrom65 < distanceTo90)
                mirror = (char) (90 - distanceFrom65);
               
            else
                mirror = (char) (65 + distanceTo90);
            
            result[count++] = mirror;
        }

        return new String(result);
    }
    
    public int convertToNumber(String str) {

        if(str == null || str.length() == 0){
            return -1;
        }
        
        char[] ch = str.toCharArray();
        String test = "";
        
        for (char c : ch) {

            switch (c) {

                case 'A':
                case 'B':
                case 'C':
                    test += "2";
                    break;
    
                case 'D':
                case 'E':
                case 'F':
                    test += "3";
                    break;
    
                case 'G':
                case 'H':
                case 'I':
                    test += "4";
                    break;
    
                case 'J':
                case 'K':
                case 'L':
                    test += "5";
                    break;
    
                case 'M':
                case 'N':
                case 'O':
                    test += "6";
                    break;
    
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                    test += "7";
                    break;
    
                case 'T':
                case 'U':
                case 'V':
                    test += "8";
                    break;
    
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    test += "9";
                    break;
    
                default:
                    return -1;
            }
        }

        try {

            return Integer.parseInt(test);
        }

        catch (Exception ex) {

            ex.printStackTrace();
        }

        return -1;
    }
}
/*END of solution: Spotible test*/



/*
* deck of cards 
*/
public class Cards {

    protected int rank;
    protected int suit;
    
    protected String[] sNames = { "Hearts", "Clubs", "Spades", "Diamonds" };
    protected String[] rNames = { "Ace", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "Jack", "Queen", "King" };

    public Cards(int Rank, int Suit) {
        suit = Suit;
        rank = Rank;
    }

    public String toString() {
        return ("Your card is: " + rNames[rank - 1] + " of " + sNames[suit - 1]);
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

}


public class DeckOfCards {

    private Cards[] deck;
    private int cardHold;

    public DeckOfCards() {

        deck = new Cards[52];
        int n = 0;

        for (int i = 1; i <= 13; i++) {

            for (int j = 1; j <= 4; j++) {
                deck[n] = new Cards(i, j);
                n = n+1;
            }
        }

        cardHold = -1;
    }


    public void shuffle() {
        
        int i = 0;
        
        while (i < 52) {

            int rando = (int) (5.0*(Math.random()));
            Cards temp = deck[rando];
            deck[rando] = deck[i];
            deck[i] = temp;
            i++;
        }
    }

    public Cards deal() {
        return (hasMoreCards() ? deck[++cardHold] : null);
    }

    public boolean hasMoreCards() {
        return (cardHold != 0);
    }

    public static void main(String[] args) {
        DeckOfCards deck2 = new DeckOfCards();
        deck2.shuffle();
        for (int i = 0; i < 52; i++)
            System.out.println(deck2.deal());
    }
}

/*
* END of deck of cards
*/





public class myArray {


    // WalletHub 


    // Q) Remove html tags in a string
    // Sample Input: <h1>Hello World!</h1> <p>something</p>
    // Output: Hello World! something
    // My Solution:
    public static String stripHtmlTags(String html){

        html = html.replaceAll("<.*?>", " ");
        html = html.replaceAll(" +", " ");

        return html;
    }

    // Q) Given an integer input array A, you need to create an integer output array B of same size such that each entry at index i, is stated as
    // B[i] = A[0]*A[1]*....A[i-1]*A[i+1]*A[i+2]*....
    // So, it means we have to multiply all the numbers excluding the value at that index i.
    // Sample Input: {3,1,6,4}
    // Output: [24, 72, 12, 18]
    // B[0] = 1*6*4, B[1] = 3*6*4, B[2] = 3*1*4, B[3] = 3*1*6

    // /**
    // * Assumptions: zero is not present in the numbers.
    // * @param input int numbers
    // * @return output int array
    // */
    public static int[] product(int[] input){
        
        int prod = 1;
        int[] res = new int[input.length];

        for(int ele:input) {
            prod *= ele;
        }

        for(int ind=0; ind<res.length; ind++) {

            res[ind] = prod/input[ind];
        }

        return res;
    }



    /*question:*/
    public static List<long[]> removNb(long n) {

        long [] data = LongStream.range(1, n+1).toArray();
        long sum = LongStream.of(data).sum();
        
        List<Long[]> lis = new ArrayList<>();
        
        int i =  data.length/2;
        int limit = data.length -1;
        
        while(i < limit){

            for(int j = data.length -1 ; j > i; j--){
                
                long a = data[i];
                long b = data[j];

                if(sum ==  a+b+ a*b ){

                    lis.add(new long[]{a, b});
                    lis.add(new long[]{b, a});
                    return lis; 
                } 
            }
            
            i++;            
            if (i == data.length && lis.size() == 0){
            
              i = 0; 
              limit = data.length/2;
            }
        } 
        
        return lis;
    }
    /*END of solution*/




    /* ================= */
    public static class Statistics {

        double[] data;
        int size;   

        public Statistics(double[] data) {

            this.data = data;
            size = data.length;
        }   

        double getMean(){

            double sum = 0.0;
            
            for(double a : data){
                sum += a;
            }    
            
            return sum/size;
        }

        public double getVariance(){

            double mean = getMean();
            double temp = 0;
            
            for(double a :data)
                temp += (a-mean)*(a-mean);
            return temp/size;
        }

        public double getStdDev(){

            return Math.sqrt(getVariance());
        }

        public double median(){

           Arrays.sort(data);

           if (data.length % 2 == 0){

              return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
           } 
           return data[data.length / 2];
        }
    }


    public static Map<String, double[]> statisticsHlpr(String strng) {


        String lines[] = strng.split("\\r?\\n");        
        String cities[] = new String[lines.length];

        List<Double> rainfalls;

        Map<String, Double[]> map = new TreeMap<>();
        
        int count = 0;

        for(String line: lines){


            rainfalls = new ArrayList<Double>();
        
            int index = line.indexOf(":");
            String city = line.substring(0, index).trim();
            String[] values =  line.substring(index+1).split(",");

            Pattern p = Pattern.compile("\\d+\\.\\d+");
        
            for(String v: values){                   

                String s = v.replaceAll("[^0-9\\.]+", "");
                double d = Double.parseDouble(s);

                rainfalls.add(d);
            }

            map.put(city, rainfalls.stream().mapToDouble(d -> d).toArray());
          
            cities[count] = city;
            count++;
        }

        return map;
    }

    public static double mean(String town, String strng) {

        Map<String, Double[]> map = statisticsHlpr(strng);

        if(map.get(town) ==  null){
            return -1;
        }

        Statistics stat = new Statistics(map.get(town));  

        return stat.getMean();    
    }


    public static double variance(String town, String strng) {
     
        Map<String, Double[]> map = statisticsHlpr(strng);

        if(map.get(town) ==  null){
            return -1;
        }

        Statistics stat = new Statistics(map.get(town));  

        return stat.getVariance();
    }



    // ==============================
    // Hasso Plattner Institute (HPI)
    // ==============================


    // problem - l
    // -----------
    public static int countUneatenLeaves(int N, int[] A) {

        int total = 0;

        for (int i = 0; i < A.length; i++) {

            int multiplier = (int) Math.pow(-1, i);
            total += multiplier * combination(A, i + 1, N);
        }

        return N - total;
    }

    private static int calc(int[] combination, int[] elements, int num) {

        int eaten = 0;

        if (combination.length == 1) {
            eaten = (int) Math.floor(num / elements[combination[0]]);
        } 

        else {

            int lcm = lcm(elements[combination[0]], elements[combination[1]]);
            for (int i = 2; i < combination.length; i++) {
                lcm = lcm(lcm, elements[combination[i]]);
            }
            eaten = Math.abs((int) Math.floor(num / lcm));
        }
        return eaten;
    }

    private static int lcm(int a, int b) {
        return a * (b / findGCD(a, b));
    }

    private static int findGCD(int number1, int number2) {
        //base case
        if (number2 == 0) {
            return number1;
        }

        return findGCD(number2, number1 % number2);
    }

    public static int combination(int[] elements, int K, int num) {

        // get the length of the array
        // e.g. for {'A','B','C','D'} => N = 4 
        int N = elements.length;

        // get the combination by index 
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[K];

        // position of current index
        //  if (r = 1)              r*
        //  index ==>       0   |   1   |   2
        //  element ==>     A   |   B   |   C

        int r = 0;
        int index = 0;
        int total = 0;

        while (r >= 0) {


            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if (index <= (N + (r - K))) {
                combination[r] = index;

                // if we are at the last position print and increase the index
                if (r == K - 1) {

                    //do something with the combination e.g. add to list or print
                    total += calc(combination, elements, num);
                    index++;
                } else {
                    // select index for next position
                    index = combination[r] + 1;
                    r++;
                }
            } else {
                r--;
                if (r > 0) index = combination[r] + 1;
                else index = combination[0] + 1;
            }
        }
        return total;
    }
    /* END ofsolution - l*/
    

    

    // problem - ll
    // ------------

    static String mergeStrings(String a, String b) {

        boolean eql =  a.length() ==  b.length();
        boolean less =  a.length() < b.length();
        // boolean greater =  a.length() > b.length();

        int min = less ? a.length(): b.length();

        String rst = "";
        
        for (int i = 0; i < min; i++){            
            
            rst +=  "" + a.charAt(i) + b.charAt(i);
            // rst +=   new StringBuilder().append(a.charAt(i)).append(b.charAt(i)).toString();
            // rst += String.valueOf(a.charAt(i)) + String.valueOf(b.charAt(i));
        } 

        if(eql){
            return rst;
        }

        else if(less){
            return rst += b.substring(min);
        }

        return rst += a.substring(min);
    }
    /* END ofsolution -  ll*/
     




    // problem - lll
    // -------------

    /*
        Victor 
        Veronica 
        Ryan 
        Dave 
        Maria 
        Maria 
        Farah 
        Farah
        Ryan 
        Veronica 

        ================
        answer: Veronica
        ================ 
    */

    // String[] votes = { "Victor", "Veronica", "Ryan", "Dave", "Maria",
    //                      "Maria", "Farah", "Farah","Ryan",  "Veronica"};
    public static String electionWinner(String[] votes) {


        // TreeMap will put the values in the ascending order 
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote  : votes) {
            
            String key =  vote;
            Integer value = map.containsKey(key) ? map.get(key) +1 : 1;

            map.put(key, value);
        }

        List<String> keys = new ArrayList<String>();

        int max =  Collections.max(map.values());

        for(Map.Entry<String, Integer> entry : map.entrySet()){

            if(entry.getValue() == max){
                keys.add(entry.getKey());
            }
        }

        // the list is already sorded in the ascending order due 
        // to the use of TreeMap
        
        String rst = keys.get(keys.size() - 1);
        return rst;
    }


    public static String electionWinner1(String[] votes) {

        // TreeMap will put the values in the ascending order 
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote  : votes) {
            
            String key =  vote;
            Integer value = map.containsKey(key) ? map.get(key) +1 : 1;

            map.put(key, value);
        }

        int max =  Collections.max(map.values());

        Iterator it = map.entrySet().iterator();

        while(it.hasNext()) {

            Map.Entry entry = (Map.Entry)it.next();
            
            if(!(entry.getValue()).equals(max)){
                it.remove();
            }
        }

        // System.out.println(map.lastEntry());
        
        return map.lastKey();
    }

    public static void printMap(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }
    }
    /* END ofsolution -  lll*/

    
    public static void mySetTest(){

        Set<Integer> set = new TreeSet<Integer>();

        set.add(3);
        set.add((int)3.0);
        set.add(2);
        set.add(2);

        set.add(new Integer(2));
        set.add(Integer.parseInt("2"));

        System.out.println(set);
        // [2, 3]
    }    



    /*question 19-1: Write a function to swap a number 
    in place without temporary variables.*/
    public static void swap(int a, int b) {

        // Example for a = 9, b = 4
        a = a - b; // a = 9 - 4 = 5
        b = a + b; // b = 5 + 4 = 9
        a = b - a; // a = 9 - 5
        
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
    
    public static void swap_opt(int a, int b) {

        a = a^b; 
        b = a^b; 
        a = a^b; 
        
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
    /*END of solution:Write a function to swap a 
    number in place without temporary variables.*/



    // CTCI_EDT_6_CODE 
    /*question 19-2: Design an algorithm to figure out if 
    someone has won in a game of tic-tac-toe.*/

    enum Piece { 
        Empty, Red, Blue 
    };

    class Position {

        public int row, column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    class PositionIterator implements Iterator<Position> {

        private int rowIncrement, colIncrement, size;
        private Position current;
        
        public PositionIterator(Position p, int rowIncrement, int colIncrement, int size) {
            this.rowIncrement = rowIncrement;
            this.colIncrement = colIncrement;
            this.size = size;
            current = new Position(p.row - rowIncrement, p.column - colIncrement);
        }
        
        @Override
        public boolean hasNext() {
            return current.row + rowIncrement < size && current.column + colIncrement < size;
        }

        @Override
        public Position next() {
            current = new Position(current.row + rowIncrement, current.column + colIncrement);
            return current;
        }
    }

    /*solution-a*/
    public static int convertBoardToInt(Piece[][] board) {

        int sum = 0;

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {
                int value = board[i][j].ordinal();
                sum = sum * 3 + value;
            }
        }

        return sum;
    }
    
    public static void testMain_a(String[] args) {

        Piece[][] board = { 
                {Piece.Empty, Piece.Empty, Piece.Empty},
                {Piece.Empty, Piece.Empty, Piece.Empty},
                {Piece.Blue, Piece.Blue, Piece.Blue}};
        

        int v = convertBoardToInt(board);
        System.out.println(v);
    }
    /*END of solution-a*/



    /*solution-b*/
    public static Piece hasWon(Piece[][] board, int row, int column) {

        if (board.length != board[0].length) return Piece.Empty;
        
        Piece piece = board[row][column];
        
        if (piece == Piece.Empty) return Piece.Empty;

        if (hasWonRow(board, row) || hasWonColumn(board, column)) {
            return piece;
        }
        
        if (row == column && hasWonDiagonal(board, 1)) {
            return piece;
        }
        
        if (row == (board.length - column - 1) && hasWonDiagonal(board, -1)) {
            return piece;
        }
        
        return Piece.Empty;
    }   
    
    public static boolean hasWonRow(Piece[][] board, int row) {

        for (int c = 1; c < board[row].length; c++) {
            if (board[row][c] != board[row][0]) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean hasWonColumn(Piece[][] board, int column) {

        for (int r = 1; r < board.length; r++) {

            if (board[r][column] != board[0][column]) {
                return false;
            }
        }
        return true;
    }

    
    public static boolean hasWonDiagonal(Piece[][] board, int direction) {

        int row = 0;
        int column = direction == 1 ? 0 : board.length - 1;
        Piece first = board[0][column];

        for (int i = 0; i < board.length; i++) {

            if (board[row][column] != first) {
                return false;
            }
            row += 1;
            column += direction;
        }
        return true;
    }       
    
    public static void m_Test(String[] args) {

        int N = 3;
        int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);

        board_t[1][1] = board_t[0][2];
        board_t[2][0] = board_t[0][2];
        
        Piece[][] board = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = board_t[i][j];
                board[i][j] = Tester.convertIntToPiece(x);
            }
        }

        Piece p1 = hasWon(board, 0, 2);
        
        System.out.println(p1);
        AssortedMethods.printMatrix(board_t);
    }
    /*END of solution-b*/



    /*solution-c*/
    public static boolean hasWinner(Piece p1, Piece p2, Piece p3) {

        if (p1 == Piece.Empty) {
            return false;
        }
        return p1 == p2 && p2 == p3;
    }

    
    public static Piece hasWon(Piece[][] board) {

        for (int i = 0; i < board.length; i++) {

            // Check Rows 
            if (hasWinner(board[i][0], board[i][1], board[i][2])) {
                return board[i][0];
            }

            // Check Columns
            if (hasWinner(board[0][i], board[1][i], board[2][i])) {
                return board[0][i];
            }
        }

        // Check Diagonal
        if (hasWinner(board[0][0], board[1][1], board[2][2])) {
            return board[0][0];
        }
        
        if (hasWinner(board[0][2], board[1][1], board[2][0])) {
            return board[0][2];
        }
        
        return Piece.Empty;
    }
    
    public static void m_test(String[] args) {

        int N = 3;

        int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);
        Piece[][] board = new Piece[N][N];
        
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                int x = board_t[i][j];
                board[i][j] = Tester.convertIntToPiece(x);
            }
        }

        Piece p1 = hasWon(board);

        System.out.println(p1);
        AssortedMethods.printMatrix(board_t);
    }
    /*END of solution-c*/


    /*solution-d*/
    public static boolean hasWinner(Piece p1, Piece p2, Piece p3) {

        if (p1 == Piece.Empty) {
            return false;
        }

        return p1 == p2 && p2 == p3;
    }
    
    public static Piece hasWon(Piece[][] board) {

        if (board[0][0] != Piece.Empty &&

            (hasWinner(board[0][0], board[0][1], board[0][2]) ||
             hasWinner(board[0][0], board[1][0], board[2][0]))) {
            return board[0][0];
        }
        
        if (board[2][2] != Piece.Empty &&
            (hasWinner(board[2][0], board[2][1], board[2][2]) ||
             hasWinner(board[0][2], board[1][2], board[2][2]))) {
            return board[2][2];
        }
        
        if (board[1][1] != Piece.Empty &&
            (hasWinner(board[0][0], board[1][1], board[2][2]) ||
             hasWinner(board[0][2], board[1][1], board[2][0]) ||
             hasWinner(board[1][0], board[1][1], board[1][2]) ||
             hasWinner(board[0][1], board[1][1], board[2][1]))) {
            return board[1][1];
        }
        
        return Piece.Empty;
    }
    
    public static void m_test(String[] args) {
        int N = 3;
        int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);
        Piece[][] board = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = board_t[i][j];
                board[i][j] = Tester.convertIntToPiece(x);
            }
        }

        Piece p1 = hasWon(board);
        
        System.out.println(p1);
        AssortedMethods.printMatrix(board_t);
    }
    /*END of solution-d*/


    /*solution-e*/
    public static Piece hasWon(Piece[][] board) {
        
        int size = board.length;

        if (board[0].length != size) return Piece.Empty;
        Piece first;
        
        // Check rows
        for (int i = 0; i < size; i++) {

            first = board[i][0];

            if (first == Piece.Empty) continue;

            for (int j = 1; j < size; j++) {
                if (board[i][j] != first) {
                    break;
                } else if (j == size - 1) {
                    return first;
                }
            }
        }
        
        // Check columns
        for (int i = 0; i < size; i++) {

            first = board[0][i];

            if (first == Piece.Empty) continue;
            for (int j = 1; j < size; j++) {
                if (board[j][i] != first) {
                    break;
                } else if (j == size - 1) {
                    return first;
                }
            }
        }
        
        // Check diagonals
        first = board[0][0];
        if (first != Piece.Empty) {
            for (int i = 1; i < size; i++) {
                if (board[i][i] != first) {
                    break;
                } else if (i == size - 1) {
                    return first;
                }
            }
        }
        
        first = board[0][size - 1];

        if (first != Piece.Empty) {
        
            for (int i = 1; i < size; i++) {
                if (board[i][size - i - 1] != first) {
                    break;
                } else if (i == size - 1) {
                    return first;
                }
            }
        }
        
        return Piece.Empty;
    }
    

    public static void main_test(String[] args) {
     
        int N = 3;
        int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);

        Piece[][] board = new Piece[N][N];

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                int x = board_t[i][j];
                board[i][j] = Tester.convertIntToPiece(x);
            }
        }

        Piece p1 = hasWon(board);
        
        System.out.println(p1);
        AssortedMethods.printMatrix(board_t);
    }
    /*END of solution-e*/


    /*solution-f*/
    public static Piece hasWon(Piece[][] board) {

        Piece winner = Piece.Empty;
        
        /* Check rows. */
        for (int i = 0; i < board.length; i++) {

            winner = hasWon(board, i, 0, 0, 1);
            if (winner != Piece.Empty) {
                return winner;
            }
        }
        
        /* Check columns. */
        for (int i = 0; i < board[0].length; i++) {
            winner = hasWon(board, 0, i, 1, 0);
            if (winner != Piece.Empty) {
                return winner;
            }
        }
        
        /* Check top/left -> bottom/right diagonal. */
        winner = hasWon(board, 0, 0, 1, 1);
        if (winner != Piece.Empty) {
            return winner;
        }
        
        /* Check top/right -> bottom/left diagonal. */
        return hasWon(board, 0, board[0].length - 1, 1, -1);
    }
    
    public static Piece hasWon(Piece[][] board, int row, int col, int incrementRow, int incrementCol) {
        Piece first = board[row][col];
        while (row < board.length && col < board[row].length) {
            if (board[row][col] != first) {
                return Piece.Empty;
            }
            row += incrementRow;
            col += incrementCol;
        }
        return first;
    }
    
    public static void test1(String[] args) {
        int N = 3;
        int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);
        Piece[][] board = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = board_t[i][j];
                board[i][j] = Tester.convertIntToPiece(x);
            }
        }

        Piece p1 = hasWon(board);
        
        System.out.println(p1);
        AssortedMethods.printMatrix(board_t);
    }
    /*END of solution-f*/


    /*solution-g*/
    public static class Check {

        public int row, column;
        private int rowIncrement, columnIncrement;
        
        public Check(int row, int column, int rowI, int colI) {

            this.row = row;
            this.column = column;
            this.rowIncrement = rowI;
            this.columnIncrement = colI;
        }
        
        public void increment() {
            row += rowIncrement;
            column += columnIncrement;
        }
        
        public boolean inBounds(int size) {
            return row >= 0 && column >= 0 &&
                    row < size && column < size;
        }
    }
    
    public static Piece hasWon(Piece[][] board) {

        if (board.length != board[0].length) return Piece.Empty;
        int size = board.length;
        
        /* Create list of things to check. */
        ArrayList<Check> instructions = new ArrayList<Check>();
        for (int i = 0; i < board.length; i++) {
            instructions.add(new Check(0, i, 1, 0));
            instructions.add(new Check(i, 0, 0, 1));
        }

        instructions.add(new Check(0, 0, 1, 1));
        instructions.add(new Check(0, size - 1, 1, -1));
        
        /* Check them. */
        for (Check instr : instructions) {
        
            Piece winner = hasWon(board, instr);
        
            if (winner != Piece.Empty) {
                return winner;
            }
        }

        return Piece.Empty;
    }
    
    public static Piece hasWon(Piece[][] board, Check instr) {

        Piece first = board[instr.row][instr.column];
        while (instr.inBounds(board.length)) {
            if (board[instr.row][instr.column] != first) {
                return Piece.Empty;
            }
            instr.increment();
        }
        return first;
    }   
    
    public static void t_main(String[] args) {
        int N = 3;
        int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);
        Piece[][] board = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = board_t[i][j];
                board[i][j] = Tester.convertIntToPiece(x);
            }
        }

        Piece p1 = hasWon(board);
        
        System.out.println(p1);
        AssortedMethods.printMatrix(board_t);
    }
    /*END of solution-g*/


    /*solution-h*/
    public static Piece hasWon(Piece[][] board) {

        if (board.length != board[0].length) return Piece.Empty;
        int size = board.length;
        
        ArrayList<PositionIterator> instructions = new ArrayList<PositionIterator>();

        for (int i = 0; i < board.length; i++) {
            instructions.add(new PositionIterator(new Position(0, i), 1, 0, size));
            instructions.add(new PositionIterator(new Position(i, 0), 0, 1, size));
        }

        instructions.add(new PositionIterator(new Position(0, 0), 1, 1, size));
        instructions.add(new PositionIterator(new Position(0, size - 1), 1, -1, size));
        
        for (PositionIterator iterator : instructions) {
            Piece winner = hasWon(board, iterator);
            if (winner != Piece.Empty) {
                return winner;
            }
        }
        return Piece.Empty;
    }
    
    public static Piece hasWon(Piece[][] board, PositionIterator iterator) {

        Position firstPosition = iterator.next();
        Piece first = board[firstPosition.row][firstPosition.column];
        
        while (iterator.hasNext()) {
        
            Position position = iterator.next();
        
            if (board[position.row][position.column] != first) {
                return Piece.Empty;
            }
        }
        
        return first;
    }   
    
    public static void m_test(String[] args) {

        int N = 3;
        int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);
        Piece[][] board = new Piece[N][N];

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                int x = board_t[i][j];
                board[i][j] = Tester.convertIntToPiece(x);
            }
        }

        Piece p1 = hasWon(board);
        
        System.out.println(p1);
        AssortedMethods.printMatrix(board_t);
    }
    /*END of solution-h*/





    private class Tester {  

        public static Piece convertIntToPiece(int i) {
        
            if (i == 1) {
                return Piece.Blue;
            } else if (i == 2) {
                return Piece.Red;
            } else {
                return Piece.Empty;
            }
        }
        
        public static Piece hasWonB(Piece[][] board) {

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    Piece winner =QuestionB.hasWon(board, i, j);
                    if (winner != Piece.Empty) {
                        return winner;
                    }
                }
            }

            return Piece.Empty;
        }
        
        public static void m_Test(String[] args) {

            for (int k = 0; k < 100; k++) {

                int N = 3;
                int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);
                Piece[][] board = new Piece[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int x = board_t[i][j];
                        board[i][j] = convertIntToPiece(x);
                    }
                }
                //AssortedMethods.printMatrix(board_t);
                Piece p1 = hasWonB(board);
                Piece p2 = QuestionC.hasWon(board);
                Piece p3 = QuestionD.hasWon(board);
                Piece p4 = QuestionE.hasWon(board);
                Piece p5 = QuestionF.hasWon(board);
                Piece p6 = QuestionG.hasWon(board);
                Piece p7 = QuestionH.hasWon(board);
                
                //System.out.println(p + " " + p2);
                if (p1 != p2 || p2 != p3 || p3 != p4 || p4 != p5 || p5 != p6 || p6 != p7) {
                    System.out.println(p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7);
                    AssortedMethods.printMatrix(board_t);
                }
            }
            
            for (int k = 0; k < 100; k++) {

                int N = 4;
                int[][] board_t = AssortedMethods.randomMatrix(N, N, 0, 2);
                Piece[][] board = new Piece[N][N];
                
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int x = board_t[i][j];
                        board[i][j] = convertIntToPiece(x);
                    }
                }

                //AssortedMethods.printMatrix(board_t);
                Piece p3 = hasWonB(board);
                Piece p4 = QuestionE.hasWon(board);
                Piece p5 = QuestionF.hasWon(board);
                Piece p6 = QuestionG.hasWon(board);
                Piece p7 = QuestionH.hasWon(board);
                //System.out.println(p + " " + p2);



                if (p3 != p4 || p4 != p5 || p5 != p6 || p6 != p7) {
                    System.out.println(p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7);
                    AssortedMethods.printMatrix(board_t);
                }
            }       
        }

    }
    /*END of solution 19-2: Design an algorithm to figure 
    out if someone has won in a game of tic-tac-toe.*/



    /*Question 19-3: Write an algorithm which computes 
    the number of trailing zeros in n factorial.*/

    /*Solution-a*/
    public static int factorsOf5(int i) {            


        int count = 0;

        while (i % 5 == 0) {

            count++;
            i /= 5;
        }    
        return count;
    }

    
    public static int countFactZeros(int num) {

        int count = 0;      

        for (int i = 2; i <= num; i++) {
            count += factorsOf5(i);
        }
        return count;
    }
    
    public static int factorial(int num) {

        if (num == 1) {
            return 1;
        } 

        else if (num > 1) {
            return num * factorial(num - 1);
        } 

        else {
            return -1; // Error
        }
    }
    /*END of solution-a*/


    /*Solution-b*/
    public static int countFactZeros(int num) {

        int count = 0;

        if (num < 0) {
            System.out.println("Factorial is not defined for negative numbers");
            return 0;
        }

        for (int i = 5; num / i > 0; i *= 5) {
            count += num / i;
        }

        return count;
    }
    
    public static int factorial(int num) {
     
        if (num == 1) {
            return 1;
        } else if (num > 1) {
            return num * factorial(num - 1);
        } else {
            return -1; // Error
        }
    }
    /*END of solution-b*/
    
    /*END of solution 19-3: Write an algorithm which computes 
    the number of trailing zeros in n factorial*/




    /*Question 19-4: Write a method which finds the maximum 
    of two numbers. You should not use ifelse or any 
    other comparison operator. For example, Input: 5, 10
    Output: 10*/

    /* Flips a 1 to a 0 and a 0 to a 1 */
    public static int flip(int bit) {
        return 1 ^ bit;
    }   
    
    /* Returns 1 if a is positive, and 0 if a is negative */
    public static int sign(int a) {
        return flip((a >> 31) & 0x1);
    }
    
    public static int getMaxNaive(int a, int b) {

        int k = sign(a - b);
        int q = flip(k);

        return a * k + b * q;
    }
    
    public static int getMax(int a, int b) {

        int c = a - b;
        
        int sa = sign(a); // if a >= 0, then 1 else 0
        int sb = sign(b); // if b >= 0, then 1 else 0
        int sc = sign(c); // depends on whether or not a - b overflows
        
        /* We want to define a value k which is 1 if a > b and 0 if a < b. 
        (if a = b, it doesn't matter what value k is) */
        
        int use_sign_of_a = sa ^ sb; // If a and b have different signs, then k = sign(a)
        int use_sign_of_c = flip(sa ^ sb); // If a and b have the same sign, then k = sign(a - b)
        
        /* We can't use a comparison operator, but we can multiply values by 1 or 0 */
        int k = use_sign_of_a * sa + use_sign_of_c * sc;
        int q = flip(k); // opposite of k
        
        return a * k + b * q;
    }
    /*END of solution 19-4: Write a method which finds the 
    maximum of two numbers. You should not use ifelse 
    or any other comparison operator. For example, 
    Input: 5, 10; Output: 10*/





    /*question 19-5: The Game of Master Mind is played as follows: The computer has four 
    slots containing balls that are red (R), yellow (Y), green (G) or blue (B). For 
    example, the computer might have RGGB (e.g., Slot #1 is red, Slots #2 and #3 are 
    green, Slot #4 is blue). You, the user, are trying to guess the solution. You might, 
    for example, guess YRGB. When you guess the correct color for the correct slot, you 
    get a “hit”. If you guess a color that exists but is in the wrong slot, you get a 
    “pseudo-hit”. For example, the guess YRGB has 2 hits and one pseudo hit. For each 
    guess, you are told the number of hits and pseudo-hits. Write a method that, given 
    a guess and a solution, returns the number of hits and pseudo hits.*/

    public static class Result {

        public int hits;
        public int pseudoHits;
        
        public Result(int h, int p) {
            hits = h;
            pseudoHits = p;
        }
        
        public Result() {

        }
        
        public String toString() {
            return "(" + hits + ", " + pseudoHits + ")";
        }
    }
    
    public static int code(char c) {

        switch (c) {

            case 'B':
                return 0;
            case 'G':
                return 1;
            case 'R':
                return 2;
            case 'Y':
                return 3;
            default:
                return -1;
        }
    }
    
    public static int MAX_COLORS = 4;
    
    public static Result estimate(String guess, String solution) {

        if (guess.length() != solution.length()) {
            return null;
        }

        Result res = new Result();
        int[] frequencies = new int[MAX_COLORS];
            
        /* Compute hits and built frequency table */
        for (int i = 0; i < guess.length(); i++) {

            if (guess.charAt(i) == solution.charAt(i)) {
                res.hits++;
            } 

            else {
                /* Only increment the frequency table (which will be used for pseudo-hits) if
                 * it's not a hit. If it's a hit, the slot has already been "used." */
                int code = code(solution.charAt(i));
                if (code >= 0) {
                    frequencies[code]++;
                }
            }
        }
        
        /* Compute pseudo-hits */
        for (int i = 0; i < guess.length(); i++) {
            int code = code(guess.charAt(i));
            if (code >= 0 && frequencies[code] > 0 && guess.charAt(i) != solution.charAt(i)) {
                res.pseudoHits++;
                frequencies[code]--;
            }
        }
        return res;
    }

    /************************** TEST CODE **********************************/
    
    public static char letterFromCode(int k) {
        
        switch (k) {
        
            case 0:
                return 'B';
            case 1:
                return 'G';
            case 2:
                return 'R';
            case 3:
                return 'Y';
            default:
                return '0';
        }       
    }

    public static Result estimateBad(String g, String s) {

        char[] guess = g.toCharArray();
        char[] solution = s.toCharArray();

        int hits = 0;
        
        for (int i = 0; i < guess.length; i++) {

            if (guess[i] == solution[i]) {
                hits++;
                solution[i] = '0';
                guess[i] = '0';
            }
        }
        
        int pseudohits = 0;
        
        for (int i = 0; i < guess.length; i++) {

            if (guess[i] != '0') {
            
                for (int j = 0; j < solution.length; j++) {
            
                    if (solution[j] != '0') {
            
                        if (solution[j] == guess[i]) {
                            pseudohits++;
                            solution[j] = '0';
                            break;
                        }
                    }
                }
            }
        }        
        return new Result(hits, pseudohits);
    }
    
    public static String randomString() {

        int length = 4;
        char[] str = new char[length];
        Random generator = new Random();
        
        for (int i = 0; i < length; i++) {
            int v = generator.nextInt(4);
            char c = letterFromCode(v);
            str[i] = c;
        }
        
        return String.valueOf(str);
    }
    
    public static boolean test(String guess, String solution) {

        Result res1 = estimate(guess, solution);
        Result res2 = estimateBad(guess, solution);

        if (res1.hits == res2.hits && res1.pseudoHits == res2.pseudoHits) {
            return true;
        } 

        else {
            System.out.println("FAIL: (" + guess + ", " + solution + "): " + res1.toString() + " | " + res2.toString());
            return false;
        }
    }
    
    public static boolean testRandom() {
        String guess = randomString();
        String solution = randomString();
        return test(guess, solution);
    }
    
    // run this method to test say, test(1000)
    public static boolean test(int count) {
        
        for (int i = 0; i < count; i++) {

            if (!testRandom()) {
                return true;
            }
        }

        return false;
    }

    public static void t_main() {
        test(1000);
    }
    /*END of solution 19-5: The Game of Master Mind is played as follows: The computer 
    has four slots containing balls that are red (R), yellow (Y), green (G) or blue (B). 
    For example, the computer might have RGGB (e.g., Slot #1 is red, Slots #2 and #3 are 
    green, Slot #4 is blue). You, the user, are trying to guess the solution. You might, 
    for example, guess YRGB. When you guess the correct color for the correct slot, you 
    get a “hit”. If you guess a color that exists but is in the wrong slot, you get a 
    “pseudo-hit”. For example, the guess YRGB has 2 hits and one pseudo hit. For each 
    guess, you are told the number of hits and pseudo-hits. Write a method that, given 
    a guess and a solution, returns the number of hits and pseudo hits.*/




    // MODERATE_INCL_6EDT
    // NOT_INCLUDED_4_EDT
    /*Question 16-16: Given two arrays of integers, compute the 
    pair of values (one value in each array) with the smallest 
    (non-negative) difference. Return the difference. EXAMPLE,
    
    Input: {l, 3, 15, 11, 2}, {23, 127, 235, 19, 8} 
    Output: 3. That is, the pair (11, 8)*/
    public static int findEndOfLeftSubsequence(int[] array) {

        for (int i = 1; i < array.length; i++) {

            if (array[i] < array[i - 1]) {
                return i - 1;
            }
        }

        return array.length - 1;
    }
    
    public static int findStartOfRightSubsequence(int[] array) {
        
        for (int i = array.length - 2; i >= 0; i--) {
        
            if (array[i] > array[i + 1]) {
                return i + 1;
            }
        }
        
        return 0;
    }       
    
    public static int shrinkLeft(int[] array, int min_index, int start) {

        int comp = array[min_index];

        for (int i = start - 1; i >= 0; i--) {
            if (array[i] <= comp) {
                return i + 1;
            }
        }
        return 0;
    }
    
    public static int shrinkRight(int[] array, int max_index, int start) {
        int comp = array[max_index];
        for (int i = start; i < array.length; i++) {
            if (array[i] >= comp) {
                return i - 1;
            }
        }
        return array.length - 1;
    }   
    

    // run this method to test
    // int[] array = {1, 2, 4, 7, 10, 11, 8, 12, 5, 7, 16, 18, 19};
    // findUnsortedSequence(array);
    public static void findUnsortedSequence(int[] array) {

        // find left subsequence
        int end_left = findEndOfLeftSubsequence(array); 
        
        if (end_left >= array.length - 1) {
            //System.out.println("The array is already sorted.");
            return; // Already sorted
        }
        
        // find right subsequence
        int start_right = findStartOfRightSubsequence(array);   
        
        int max_index = end_left; // max of left side
        int min_index = start_right; // min of right side

        for (int i = end_left + 1; i < start_right; i++) {

            if (array[i] < array[min_index]) {
                min_index = i;
            }
            if (array[i] > array[max_index]) {
                max_index = i;
            }
        }       
        
        // slide left until less than array[min_index]
        int left_index = shrinkLeft(array, min_index, end_left);

        // slide right until greater than array[max_index]
        int right_index = shrinkRight(array, max_index, start_right);
        
        if (validate(array, left_index, right_index)) {
            System.out.println("TRUE: " + left_index + " " + right_index);
        } else {
            System.out.println("FALSE: " + left_index + " " + right_index);
        }
    }
    
    /* Validate that sorting between these indices will sort the array. Note that this is not a complete
     * validation, as it does not check if these are the best possible indices.
     */
    public static boolean validate(int[] array, int left_index, int right_index) {

        int[] middle = new int[right_index - left_index + 1];

        for (int i = left_index; i <= right_index; i++) {
            middle[i - left_index] = array[i];
        }

        Arrays.sort(middle);
        for (int i = left_index; i <= right_index; i++) {
            array[i] = middle[i - left_index];
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i-1] > array[i]) {
                return false;
            }       
        }

        return true;
    }
    /*END of solution: Given two arrays of integers, compute the 
    pair of values (one value in each array) with the smallest 
    (non-negative) difference. Return the difference. EXAMPLE,
    
    Input: {l, 3, 15, 11, 2}, {23, 127, 235, 19, 8} 
    Output: 3. That is, the pair (11, 8)*/






    /*Question 19-6: Given an integer between 0 and 999,999, 
    print an English phrase that describes the integer 
    (eg, “One Thousand, Two Hundred and Thirty Four”).*/

    public static String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    public static String[] teens = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public static String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public static String[] bigs = {"", "Thousand", "Million", "Billion"};
    
    public static String numToString(int number) {

        if (number == 0) {
            return "Zero";
        }
        
        if (number < 0) {
            return "Negative " + numToString(-1 * number);
        }

        int count = 0;
        String str = "";
        
        while (number > 0) {

            if (number % 1000 != 0) {
                str = numToString100(number % 1000) + bigs[count] + " " + str;
            }

            number /= 1000;
            count++;
        }
        
        return str;
    }
    

    public static String numToString100(int number) {   
    
        String str = "";
        
        /* Convert hundreds place */
        if (number >= 100) {
            str += digits[number / 100 - 1] + " Hundred ";
            number %= 100;
        }
        
        /* Convert tens place */
        if (number >= 11 && number <= 19) {
            return str + teens[number - 11] + " ";
        } 

        else if (number == 10 || number >= 20) {
            str += tens[number / 10 - 1] + " ";
            number %= 10;
        }
        
        /* Convert ones place */
        if (number >= 1 && number <= 9) {
            str += digits[number - 1] + " ";
        }
        
        return str;
    }
    /*END of solution 19-6: Given an integer between 
    0 and 999,999, print an English phrase that describes 
    the integer*/





    /*Question 19-7: You are given an array of integers (both positive 
    and negative). Find the continuous sequence with the largest sum. 
    Return the sum. EXAMPLE,
    
    Input: {2, -8, 3, -2, 4, -10}
    Output: 5 (i.e., {3, -2, 4} )*/
    public static int getMaxSum(int[] a) {

        int maxSum = 0;
        int runningSum = 0;

        for (int i = 0; i < a.length; i++) {
            runningSum += a[i];
            if (maxSum < runningSum) {
                maxSum = runningSum;
            } else if (runningSum < 0) {
                runningSum = 0;
            }
        }
        return maxSum;
    }
    /*END of solution 19-7: You are given an array of 
    integers (both positive and negative). Find the 
    continuous sequence with the largest sum. Return 
    the sum. EXAMPLE,
    
    Input: {2, -8, 3, -2, 4, -10}
    Output: 5 (i.e., {3, -2, 4} )*/





    /*Question 19-8: Design a method to find the 
    frequency of occurrences of any given word in 
    a book.*/

    /*solution-a*/
    public static int getFrequency(String[] book, String word) {

        word = word.trim().toLowerCase();

        int count = 0;

        for (String w : book) {
            if (w.trim().toLowerCase().equals(word)) {
                count++;
            }
        }
        return count;
    }
    /*END of solution-a*/

    /*solution-b*/
    public static Hashtable<String, Integer> setupDictionary(String[] book) {

        Hashtable<String, Integer> table = new Hashtable<String, Integer>();

        for (String word : book) {
        
            word = word.toLowerCase();
        
            if (word.trim() != "") {

                if (!table.containsKey(word)) {
                    table.put(word, 0);
                }

                table.put(word, table.get(word) + 1);
            }
        }

        return table;
    }
    
    public static int getFrequency(Hashtable<String, Integer> table, String word) {

        if (table == null || word == null) {
            return -1;
        }

        word = word.toLowerCase();

        if (table.containsKey(word)) {
            return table.get(word);
        }

        return 0;
    }
    /*ENd of solution-b*/

    /*END of solution 19-8:Design a method to  find 
    the frequency of occurrences of any given word in 
    a book.*/




    /*17-10*/
    public static void encode(String v, StringBuffer sb) {

        v = v.replace("0", "\\0");

        sb.append(v);
        sb.append(" ");
    }
    
    public static void encodeEnd(StringBuffer sb) {
        sb.append("0");
        sb.append(" ");
    }
    
    public static void encode(Attribute attr, StringBuffer sb) {
        encode(attr.getTagCode(), sb);
        encode(attr.value, sb);
    }
    
    public static void encode(Element root, StringBuffer sb) {

        encode(root.getNameCode(), sb);

        for (Attribute a : root.attributes) {
            encode(a, sb);
        }

        encodeEnd(sb);

        if (root.value != null && root.value != "") {
            encode(root.value, sb);
        } 

        else {
            for (Element e : root.children) {
                encode(e, sb);
            }
        }

        encodeEnd(sb);
    }
    
    public static String encodeToString(Element root) {
        StringBuffer sb = new StringBuffer();
        encode(root, sb);
        return sb.toString();
    }
    /*17-10*/




    /*Question: Write a method to generate a random number 
    between 1 and 7, given a method that generates a random 
    number between 1 and 5 (i.e., implement rand7() using
    rand5())*/   

    /*Solution-a*/
    public static int rand7() {

        while (true) {

            int num = 5 * rand5() + rand5();
            if (num < 21) {
                return num % 7;
            }
        }
    }

    public static int rand5() {
        return (int) (Math.random() * 100) % 5;
    }
    /*END of solution-a*/


    /*Solution-b*/
    public static int rand7() {

        while (true) {

            int r1 = 2 * rand5(); /* evens between 0 and 9 */
            int r2 = rand5(); /* will be later used to generate a 0 or 1 */

            if (r2 != 4) { /* r2 has an extra even number, so discard the extra */

                int rand1 = r2 % 2; /* Generate 0 or 1 */
                int num = r1 + rand1; /* will be in the range 0 through 9 */

                if (num < 7) {
                    return num;
                }
            }
        }
    }

    public static int rand5() {
        return (int) (Math.random() * 100) % 5;
    }
    /*END of solution-b:*/

    /*END of solution: Write a method to generate a 
    random number between 1 and 7, given a method that 
    generates a random number between 1 and 5 (i.e., 
    implement rand7() using rand5())*/





    /*Question 17-12: */
    public static void printPairSums(int[] array, int sum) {

        Arrays.sort(array);

        int first = 0;
        int last = array.length - 1;
        
        while (first < last) {
        
            int s = array[first] + array[last];
            
            if (s == sum) {
                System.out.println(array[first] + " " + array[last]);
                ++first;
                --last;
            } 

            else {
                
                if (s < sum) {
                    ++first;
                } 

                else {
                    --last;
                }
            }
        }
    }
    /*17-12*/



    /*17-14*/
    public static String sentence;
    public static Trie dictionary;
    
    /* incomplete code */
    public static Result parse(int wordStart, int wordEnd, Hashtable<Integer, Result> cache) {


        if (wordEnd >= sentence.length()) {
            return new Result(wordEnd - wordStart, sentence.substring(wordStart).toUpperCase());
        }

        if (cache.containsKey(wordStart)) {
            return cache.get(wordStart).clone();
        }

        String currentWord = sentence.substring(wordStart, wordEnd + 1);

        boolean validPartial = dictionary.contains(currentWord, false);
        boolean validExact = validPartial && dictionary.contains(currentWord, true);
        
        /* break current word */
        Result bestExact = parse(wordEnd + 1, wordEnd + 1, cache);

        if (validExact) {
            bestExact.parsed = currentWord + " " + bestExact.parsed;
        } 

        else {
            bestExact.invalid += currentWord.length();
            bestExact.parsed = currentWord.toUpperCase() + " " + bestExact.parsed;
        } 
        
        /* extend current word */
        Result bestExtend = null;
        if (validPartial) {
            bestExtend = parse(wordStart, wordEnd + 1, cache);
        }
        
        /* find best */
        Result best = Result.min(bestExact, bestExtend);
        cache.put(wordStart, best.clone());
        return best;
    }   

    
    public static int parseOptimized(int wordStart, int wordEnd, Hashtable<Integer, Integer> cache) {

        if (wordEnd >= sentence.length()) {
            return wordEnd - wordStart;
        }

        if (cache.containsKey(wordStart)) {
            return cache.get(wordStart);
        }       
        
        String currentWord = sentence.substring(wordStart, wordEnd + 1);
        boolean validPartial = dictionary.contains(currentWord, false);
        
        /* break current word */
        int bestExact = parseOptimized(wordEnd + 1, wordEnd + 1, cache);

        if (!validPartial || !dictionary.contains(currentWord, true)) {
            bestExact += currentWord.length();
        }
        
        /* extend current word */
        int bestExtend = Integer.MAX_VALUE;

        if (validPartial) {
            bestExtend = parseOptimized(wordStart, wordEnd + 1, cache);
        }
        
        /* find best */
        int min = Math.min(bestExact, bestExtend);
        cache.put(wordStart, min);
        return min;
    }
    

    public static int parseSimple(int wordStart, int wordEnd) {

        if (wordEnd >= sentence.length()) {
            return wordEnd - wordStart;
        }
        
        String word = sentence.substring(wordStart, wordEnd + 1);
        
        /* break current word */
        int bestExact = parseSimple(wordEnd + 1, wordEnd + 1);

        if (!dictionary.contains(word, true)) {
            bestExact += word.length();
        }
        
        /* extend current word */
        int bestExtend = parseSimple(wordStart, wordEnd + 1);
        
        /* find best */
        return Math.min(bestExact, bestExtend);
    }   
    
    public static String clean(String str) {
     
        char[] punctuation = {',', '"', '!', '.', '\'', '?', ','};
        for (char c : punctuation) {
            str = str.replace(c, ' ');
        }
        return str.replace(" ", "").toLowerCase();
    }
    /*17-14*/



    /*18-1*/
    public static int add(int a, int b) {

        if (b == 0) return a;

        int sum = a ^ b; // add without carrying
        int carry = (a & b) << 1; // carry, but don’t add

        return add(sum, carry); // recurse
    }
    
    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }
    /*18-1*/


    /*18-2*/
    /* Random number between lower and higher, inclusive */
    public static int rand(int lower, int higher) { 
        return lower + (int)(Math.random() * (higher - lower + 1));
    }   
    
    public static int[] shuffleArrayRecursively(int[] cards, int i) {
        if (i == 0) {
            return cards;
        }
        
        /* shuffle elements 0 through index - 1 */
        shuffleArrayRecursively(cards, i - 1);
        int k = rand(0, i);     
        
        /* Swap element k and index */
        int temp = cards[k];
        cards[k] = cards[i];
        cards[i] = temp;
        
        /* Return shuffled array */
        return cards;
    }
    
    public static void shuffleArrayInteratively(int[] cards) { 
        for (int i = 0; i < cards.length; i++) { 
            int k = rand(0, i);
            int temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
        } 
    }

    public static void testA3(){

        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(AssortedMethods.arrayToString(cards));

        shuffleArrayInteratively(cards);
        System.out.println(AssortedMethods.arrayToString(cards));
    }
    /*18-2*/



    /*18-3*/

    /*A*/
    /* Random number between lower and higher, inclusive */
    public static int rand(int lower, int higher) { 
        return lower + (int)(Math.random() * (higher - lower + 1));
    }

    /* pick M elements from original array.  Clone original array so that
     * we don’t destroy the input. */
    public static int[] pickMRandomly(int[] original, int m) {
        for (int i = 0; i < original.length; i++) { 
            int k = rand(0, i);
            int temp = original[k];
            original[k] = original[i];
            original[i] = temp;
        } 
        return original;
    }
    
    public static void testA4(String[] args) {
        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(AssortedMethods.arrayToString(cards));
        int[] set = pickMRandomly(cards, 4);
        System.out.println(AssortedMethods.arrayToString(set));
    }
    /*A*/

    /*B*/
    /* Random number between lower and higher, inclusive */
    public static int rand(int lower, int higher) { 
        return lower + (int)(Math.random() * (higher - lower + 1));
    }
    
    /* pick M elements from original array, using only elements 0 through i (inclusive).*/
    public static int[] pickMRecursively(int[] original, int m, int i) {
        if (i + 1 < m) { // Not enough elements
            return null; 
        } else if (i + 1 == m) { // Base case -- copy first m elements into array
            int[] set = new int[m];
            for (int k = 0; k < m; k++) {
                set[k] = original[k];
            }
            return set;
        } else {
            int[] set = pickMRecursively(original, m, i - 1);
            int k = rand(0, i);
            if (k < m) {
                set[k] = original[i];
            }
            return set;
        }
    }   

    /* pick M elements from original array.*/
    public static int[] pickMIteratively(int[] original, int m) {
        int[] subset = new int[m];
        
        /* Fill in subset array with first part of original array */
        for (int i = 0; i < m ; i++) {
            subset[i] = original[i];
        }
        
        /* Go through rest of original array. */
        for (int i = m; i < original.length; i++) {
            int k = rand(0, i);
            if (k < m) {
                subset[k] = original[i];
            }
        }
        
        return subset;
    }
    
    public static void testB2(){
        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // comes from the library 
        System.out.println(AssortedMethods.arrayToString(cards));
        int[] set = pickMIteratively(cards, 4);
        System.out.println(AssortedMethods.arrayToString(set));
    }   
    /*B*/
    /*18-3*/



    /*18-4*/
    /*A*/
    public static int count2sInRangeAtDigit(int number, int d) {

        int powerOf10 = (int) Math.pow(10, d);
        int nextPowerOf10 = powerOf10 * 10;
        int right = number % powerOf10;
        
        int roundDown = number - number % nextPowerOf10;
        int roundUp = roundDown + nextPowerOf10;
        
        int digit = (number / powerOf10) % 10; 
        if (digit < 2) { // if the digit in spot digit is 
            return roundDown / 10;
        } 

        else if (digit == 2) {
            return roundDown / 10 + right + 1;
        } 

        else {
            return roundUp / 10;
        }
    }
    

    public static int count2sInRange(int number) {
        int count = 0;
        int len = String.valueOf(number).length();
        for (int digit = 0; digit < len; digit++) {
            count += count2sInRangeAtDigit(number, digit);
        }
        return count;
    }
    
    public static int count2sR(int n) { 
        /* Alternate, messier, solution */
        
        // Example: n = 513
        
        // Base case
        if (n == 0) {
            return 0;
        }
        
        // Split apart 513 into 5 * 100 + 13.
        // [Power = 100; First = 5; Remainder = 13]
        int power = 1;
        while (10 * power < n) {
            power *= 10;
        }
        int first = n / power;
        int remainder = n % power;
        
        // Counts 2s from first digit
        int nTwosFirst = 0;
        if (first > 2) {
            nTwosFirst += power; 
        } else if (first == 2) {
            nTwosFirst += remainder + 1;
        }
        
        // Count 2s from all other digits
        int nTwosOther = first * count2sR(power - 1) + count2sR(remainder);
        
        return nTwosFirst + nTwosOther;
    }
    
    public static void testA5() {
        for (int i = 0; i < 10000; i++) {
            int v1 = count2sR(i);
            int v2 = count2sInRange(i); 
            System.out.println("Between 0 and " + i + ": " + v1 + " " + v2);
        }
    }
    /*A*/


    /*B*/
    public static int numberOf2s(int n) {

        int count = 0;

        while (n > 0) {

            if (n % 10 == 2) {
                count++;
            }

            n = n / 10;
        }
        return count;
    }
    
    public static int numberOf2sInRange(int n) {

        int count = 0;
        
        for (int i = 2; i <= n; i++) { // Might as well start at 2
            count += numberOf2s(i);
        }

        return count;
    }
    
    public static void testB3(){
        for (int i = 0; i < 1000; i++) {
            int v = numberOf2sInRange(i);
            System.out.println("Between 0 and " + i + ": " + v);
        }       
    }
    /*B*/
    /*18-4*/



    /*18-5*/
    public static int shortest(String[] words, String word1, String word2) {

        int min = Integer.MAX_VALUE;

        int lastPosWord1 = -1;
        int lastPosWord2 = -1;

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.equals(word1)) {
                lastPosWord1 = i;
                // Comment following 3 lines if word order matters
                int distance = lastPosWord1 - lastPosWord2;
                if (lastPosWord2 >= 0 && min > distance) {
                    min = distance;
                }
            } else if (currentWord.equals(word2)) {
                lastPosWord2 = i;
                int distance = lastPosWord2 - lastPosWord1;
                if (lastPosWord1 >= 0 && min > distance) {
                    min = distance;
                }
            }
        }
        return min;
    }
    
    public static String wordAtLocation(String[] words, int loc) {
        if (loc < 0 || loc >= words.length) {
            return null;
        }
        return words[loc];
    }
    
    // Method to confirm other result
    public static boolean searchConfirm(String[] words, String word1, String word2, int distance) {

        boolean found_at_distance = false;

        for (int i = 0; i < words.length; i++) {

            if (words[i].equals(word1)) {

                for (int j = 1; j < distance; j++) {
                    String loc2a = wordAtLocation(words, i - j);
                    String loc2b = wordAtLocation(words, i + j);
                    if (word2.equals(loc2a) || word2.equals(loc2b)) {
                        return false;
                    }
                }
                
                String loc2a = wordAtLocation(words, i - distance);
                String loc2b = wordAtLocation(words, i + distance);
                if (word2.equals(loc2a) || word2.equals(loc2b)) {
                    found_at_distance = true;
                }
            }
        }
        return found_at_distance;
    }
    
    // run this method to test 
    public static void testA6() {
        String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
        System.out.println(AssortedMethods.stringArrayToString(wordlist));
        
        String[][] pairs = {{"Lara", "the"}, {"river", "life"}, {"path", "their"}, {"life", "a"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            int distance = shortest(wordlist, word1, word2);
            boolean confirm = searchConfirm(wordlist, word1, word2, distance);
            System.out.println("Distance between <" + word1 + "> and <" + word2 + ">: " + distance + " (" + confirm + ")");
        }
    }
    /*END of solution 18-5*/




    /*Question 18-6*/
    class Parts {
    
        public int left;
        public int right;

        public Parts(int l, int r) {
            left = l;
            right = r;
        }
    }

    public static int rankB(int[] array, int rank) {

        int[] cloned = array.clone();
        Arrays.sort(cloned);
        return cloned[rank];
    }
    
    public static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    
    public static boolean validate(int[] array, int left, int right, int pivot, int endLeft) {

        for (int i = left; i <= endLeft; i++) {
            if (array[i] > pivot) {
                return false;
            }
        }

        for (int i = endLeft + 1; i <= right; i++) {

            if (array[i] <= pivot) {
                return false;
            }
        }

        return true;
    }


    public static boolean validateFull(int[] array) {

        for (int i = 0; i < array.length; i++) {

            for (int j = i; j < array.length; j++) {
            
                for (int k = i; k <= j; k++) {
            
                    int[] cloned = array.clone();
                    int pivot = array[k];
                    int p = partition(cloned, i, j, pivot);
            
                    if (!validate(cloned, i, j, pivot, p)) {
                        AssortedMethods.printIntArray(cloned);
                        String val = p >= 0 && p < cloned.length ? String.valueOf(array[i]) : "?"; 
                        System.out.println("pivot: " + pivot + " | " + p + " | " + val);
                        return false;
                    }
                }
            }
        }       

        return true;
    }

    
    public static boolean isUnique(int[] array) {

        int[] cloned = array.clone();
        Arrays.sort(cloned);

        for (int i = 1; i < cloned.length; i++) {
            if (cloned[i] == cloned[i - 1]) {
                return false;
            }
        }

        return true;
    }
    
    public static int max(int[] array, int left, int right) {
        int max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            max = Math.max(array[i], max);
        }
        return max;
    }
    
    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }
    
    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }
    
    public static int partition(int[] array, int left, int right, int pivot) {
    
        while (true) {
    
            while (left <= right && array[left] <= pivot) {
                left++;
            }
            
            while (left <= right && array[right] > pivot) {
                right--;
            }
            
            if (left > right) {
                return left - 1;
            } 
            swap(array, left, right);
        }
    }   

    
    public static int rank(int[] array, int left, int right, int rank) {

        int pivot = array[randomIntInRange(left, right)];

        int leftEnd = partition(array, left, right, pivot); // returns end of left partition
        int leftSize = leftEnd - left + 1;
        
        if (leftSize == rank + 1) {
            return max(array, left, leftEnd);
        } 

        else if (rank < leftSize) {
            return rank(array, left, leftEnd, rank);
        } 

        else {
            return rank(array, leftEnd + 1, right, rank - leftSize);
        }
    }
    

    public static void testA7( ){

        int numberOfTests = 1000;
        int count = 0;
        
        while (count < numberOfTests) {
        
            int[] array = AssortedMethods.randomArray(10, -1000, 1000);
            if (isUnique(array)) {
                int n = AssortedMethods.randomIntInRange(0, array.length - 1);
                int rank1 = rank(array.clone(), 0, array.length - 1, n);
                int rank2 = rankB(array.clone(), n);
                
                if (rank1 != rank2) {
                    System.out.println("ERROR: " + rank1 + " " + rank2);
                    AssortedMethods.printIntArray(array);
                }
                count++;
            }
        }

        System.out.println("Completed " + count + " runs.");
    }
    /*END of solution 18-6:*/





    /*Question 18-7*/
    class LengthComparator implements Comparator<String> {

        public int compare(String o1, String o2) {

            if (o1.length() < o2.length()) return 1;
            if (o1.length() > o2.length()) return -1;

            return 0;
        }
    }

    public static String printLongestWord(String arr[]) {
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        for (String str : arr) {
            map.put(str, true);
        }
        Arrays.sort(arr, new LengthComparator()); // Sort by length
        for (String s : arr) {
            if (canBuildWord(s, true, map)) {
                System.out.println(s);
                return s;
            }
        }
         return "";
    }
    

    public static boolean canBuildWord(String str, boolean isOriginalWord, HashMap<String, Boolean> map) {

        if (map.containsKey(str) && !isOriginalWord) {
            return map.get(str);
        }

        for (int i = 1; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i);
            if (map.containsKey(left) && map.get(left) == true && 
                canBuildWord(right, false, map)) {
                return true;
            }
        }

        map.put(str, false);
        return false;
    }

    public static String[] createGiantArray() {

        String arr[] = {"a", 

                "able", 
                "about", 
                "account", 
                "acid", 
                "across", 
                "act", 
                "addition", 
                "adjustment", 
                "advertisement", 
                "after", 
                "again", 
                "against", 
                "agreement", 
                "air", 
                "all", 
                "almost", 
                "among", 
                "amount", 
                "amusement", 
                "and", 
                "angle", 
                "angry", 
                "animal", 
                "answer", 
                "ant", 
                "any", 
                "apparatus", 
                "apple", 
                "approval", 
                "arch", 
                "argument", 
                "arm", 
                "army", 
                "art", 
                "as", 
                "at", 
                "attack", 
                "attempt", 
                "attention", 
                "attraction", 
                "authority", 
                "automatic", 
                "awake", 
                "baby", 
                "back", 
                "bad", 
                "bag", 
                "balance", 
                "ball", 
                "band", 
                "base", 
                "basin", 
                "basket", 
                "bath", 
                "be", 
                "beautiful", 
                "because", 
                "bed", 
                "bee", 
                "before", 
                "behaviour", 
                "belief", 
                "bell", 
                "bent", 
                "berry", 
                "between", 
                "bird", 
                "birth", 
                "bit", 
                "bite", 
                "bitter", 
                "black", 
                "blade", 
                "blood", 
                "blow", 
                "blue", 
                "board", 
                "boat", 
                "body", 
                "boiling", 
                "bone", 
                "book", 
                "boot", 
                "bottle", 
                "box", 
                "boy", 
                "brain", 
                "brake", 
                "branch", 
                "brass", 
                "bread", 
                "breath", 
                "brick", 
                "bridge", 
                "bright", 
                "broken", 
                "brother", 
                "brown", 
                "brush", 
                "bucket", 
                "building", 
                "bulb", 
                "burn", 
                "burst", 
                "business", 
                "but", 
                "butter", 
                "button", 
                "by", 
                "cake", 
                "camera", 
                "canvas", 
                "card", 
                "care", 
                "carriage", 
                "cart", 
                "cat", 
                "cause", 
                "certain", 
                "chain", 
                "chalk", 
                "chance", 
                "change", 
                "cheap", 
                "cheese", 
                "chemical", 
                "chest", 
                "chief", 
                "chin", 
                "church", 
                "circle", 
                "clean", 
                "clear", 
                "clock", 
                "cloth", 
                "cloud", 
                "coal", 
                "coat", 
                "cold", 
                "collar", 
                "colour", 
                "comb", 
                "come", 
                "comfort", 
                "committee", 
                "common", 
                "company", 
                "comparison", 
                "competition", 
                "complete", 
                "complex", 
                "condition", 
                "connection", 
                "conscious", 
                "control", 
                "cook", 
                "copper", 
                "copy", 
                "cord", 
                "cork", 
                "cotton", 
                "cough", 
                "country", 
                "cover", 
                "cow", 
                "crack", 
                "credit", 
                "crime", 
                "cruel", 
                "crush", 
                "cry", 
                "cup", 
                "cup", 
                "current", 
                "curtain", 
                "curve", 
                "cushion", 
                "damage", 
                "danger", 
                "dark", 
                "daughter", 
                "day", 
                "dead", 
                "dear", 
                "death", 
                "debt", 
                "decision", 
                "deep", 
                "degree", 
                "delicate", 
                "dependent", 
                "design", 
                "desire", 
                "destruction", 
                "detail", 
                "development", 
                "different", 
                "digestion", 
                "direction", 
                "dirty", 
                "discovery", 
                "discussion", 
                "disease", 
                "disgust", 
                "distance", 
                "distribution", 
                "division", 
                "do", 
                "dog", 
                "door", 
                "doubt", 
                "down", 
                "drain", 
                "drawer", 
                "dress", 
                "drink", 
                "driving", 
                "drop", 
                "dry", 
                "dust", 
                "ear", 
                "early", 
                "earth", 
                "east", 
                "edge", 
                "education", 
                "effect", 
                "egg", 
                "elastic", 
                "electric", 
                "end", 
                "engine", 
                "enough", 
                "equal", 
                "error", 
                "even", 
                "event", 
                "ever", 
                "every", 
                "example", 
                "exchange", 
                "existence", 
                "expansion", 
                "experience", 
                "expert", 
                "eye", 
                "face", 
                "fact", 
                "fall", 
                "FALSE", 
                "family", 
                "far", 
                "farm", 
                "fat", 
                "father", 
                "fear", 
                "feather", 
                "feeble", 
                "feeling", 
                "female", 
                "fertile", 
                "fiction", 
                "field", 
                "fight", 
                "finger", 
                "fire", 
                "first", 
                "fish", 
                "fixed", 
                "flag", 
                "flame", 
                "flat", 
                "flight", 
                "floor", 
                "flower", 
                "fly", 
                "fold", 
                "food", 
                "foolish", 
                "foot", 
                "for", 
                "force", 
                "fork", 
                "form", 
                "forward", 
                "fowl", 
                "frame", 
                "free", 
                "frequent", 
                "friend", 
                "from", 
                "front", 
                "fruit", 
                "full", 
                "future", 
                "garden", 
                "general", 
                "get", 
                "girl", 
                "give", 
                "glass", 
                "glove", 
                "go", 
                "goat", 
                "gold", 
                "good", 
                "government", 
                "grain", 
                "grass", 
                "great", 
                "green", 
                "grey", 
                "grip", 
                "group", 
                "growth", 
                "guide", 
                "gun", 
                "hair", 
                "hammer", 
                "hand", 
                "hanging", 
                "happy", 
                "harbour", 
                "hard", 
                "harmony", 
                "hat", 
                "hate", 
                "have", 
                "he", 
                "head", 
                "healthy", 
                "hear", 
                "hearing", 
                "heart", 
                "heat", 
                "help", 
                "high", 
                "history", 
                "hole", 
                "hollow", 
                "hook", 
                "hope", 
                "horn", 
                "horse", 
                "hospital", 
                "hour", 
                "house", 
                "how", 
                "humour", 
                "I", 
                "ice", 
                "idea", 
                "if", 
                "ill", 
                "important", 
                "impulse", 
                "in", 
                "increase", 
                "industry", 
                "ink", 
                "insect", 
                "instrument", 
                "insurance", 
                "interest", 
                "invention", 
                "iron", 
                "island", 
                "jelly", 
                "jewel", 
                "join", 
                "journey", 
                "judge", 
                "jump", 
                "keep", 
                "kettle", 
                "key", 
                "kick", 
                "kind", 
                "kiss", 
                "knee", 
                "knife", 
                "knot", 
                "knowledge", 
                "land", 
                "language", 
                "last", 
                "late", 
                "laugh", 
                "law", 
                "lead", 
                "leaf", 
                "learning", 
                "leather", 
                "left", 
                "leg", 
                "let", 
                "letter", 
                "level", 
                "library", 
                "lift", 
                "light", 
                "like", 
                "limit", 
                "line", 
                "linen", 
                "lip", 
                "liquid", 
                "list", 
                "little", 
                "living", 
                "lock", 
                "long", 
                "look", 
                "loose", 
                "loss", 
                "loud", 
                "love", 
                "low", 
                "machine", 
                "make", 
                "male", 
                "man", 
                "manager", 
                "map", 
                "mark", 
                "market", 
                "married", 
                "mass", 
                "match", 
                "material", 
                "may", 
                "meal", 
                "measure", 
                "meat", 
                "medical", 
                "meeting", 
                "memory", 
                "metal", 
                "middle", 
                "military", 
                "milk", 
                "mind", 
                "mine", 
                "minute", 
                "mist", 
                "mixed", 
                "money", 
                "monkey", 
                "month", 
                "moon", 
                "morning", 
                "mother", 
                "motion", 
                "mountain", 
                "mouth", 
                "move", 
                "much", 
                "muscle", 
                "music", 
                "nail", 
                "name", 
                "narrow", 
                "nation", 
                "natural", 
                "near", 
                "necessary", 
                "neck", 
                "need", 
                "needle", 
                "nerve", 
                "net", 
                "new", 
                "news", 
                "night", 
                "no", 
                "noise", 
                "normal", 
                "north", 
                "nose", 
                "not", 
                "note", 
                "now", 
                "number", 
                "nut", 
                "observation", 
                "of", 
                "off", 
                "offer", 
                "office", 
                "oil", 
                "old", 
                "on", 
                "only", 
                "open", 
                "operation", 
                "opinion", 
                "opposite", 
                "or", 
                "orange", 
                "order", 
                "organization", 
                "ornament", 
                "other", 
                "out", 
                "oven", 
                "over", 
                "owner", 
                "page", 
                "pain", 
                "paint", 
                "paper", 
                "parallel", 
                "parcel", 
                "part", 
                "past", 
                "paste", 
                "payment", 
                "peace", 
                "pen", 
                "pencil", 
                "person", 
                "physical", 
                "picture", 
                "pig", 
                "pin", 
                "pipe", 
                "place", 
                "plane", 
                "plant", 
                "plate", 
                "play", 
                "please", 
                "pleasure", 
                "plough", 
                "pocket", 
                "point", 
                "poison", 
                "polish", 
                "political", 
                "poor", 
                "porter", 
                "position", 
                "possible", 
                "pot", 
                "potato", 
                "powder", 
                "power", 
                "present", 
                "price", 
                "print", 
                "prison", 
                "private", 
                "probable", 
                "process", 
                "produce", 
                "profit", 
                "property", 
                "prose", 
                "protest", 
                "public", 
                "pull", 
                "pump", 
                "punishment", 
                "purpose", 
                "push", 
                "put", 
                "quality", 
                "question", 
                "quick", 
                "quiet", 
                "quite", 
                "rail", 
                "rain", 
                "range", 
                "rat", 
                "rate", 
                "ray", 
                "reaction", 
                "reading", 
                "ready", 
                "reason", 
                "receipt", 
                "record", 
                "red", 
                "regret", 
                "regular", 
                "relation", 
                "religion", 
                "representative", 
                "request", 
                "respect", 
                "responsible", 
                "rest", 
                "reward", 
                "rhythm", 
                "rice", 
                "right", 
                "ring", 
                "river", 
                "road", 
                "rod", 
                "roll", 
                "roof", 
                "room", 
                "root", 
                "rough", 
                "round", 
                "rub", 
                "rule", 
                "run", 
                "sad", 
                "safe", 
                "sail", 
                "salt", 
                "same", 
                "sand", 
                "say", 
                "scale", 
                "school", 
                "science", 
                "scissors", 
                "screw", 
                "sea", 
                "seat", 
                "second", 
                "secret", 
                "secretary", 
                "see", 
                "seed", 
                "seem", 
                "selection", 
                "self", 
                "send", 
                "sense", 
                "separate", 
                "serious", 
                "servant", 
                "sex", 
                "shade", 
                "shake", 
                "shame", 
                "sharp", 
                "sheep", 
                "shelf", 
                "ship", 
                "shirt", 
                "shock", 
                "shoe", 
                "short", 
                "shut", 
                "side", 
                "sign", 
                "silk", 
                "silver", 
                "simple", 
                "sister", 
                "size", 
                "skin", 
                "skirt", 
                "sky", 
                "sleep", 
                "slip", 
                "slope", 
                "slow", 
                "small", 
                "smash", 
                "smell", 
                "smile", 
                "smoke", 
                "smooth", 
                "snake", 
                "sneeze", 
                "snow", 
                "so", 
                "soap", 
                "society", 
                "sock", 
                "soft", 
                "solid", 
                "some", 
                "", 
                "son", 
                "song", 
                "sort", 
                "sound", 
                "soup", 
                "south", 
                "space", 
                "spade", 
                "special", 
                "sponge", 
                "spoon", 
                "spring", 
                "square", 
                "stage", 
                "stamp", 
                "star", 
                "start", 
                "statement", 
                "station", 
                "steam", 
                "steel", 
                "stem", 
                "step", 
                "stick", 
                "sticky", 
                "stiff", 
                "still", 
                "stitch", 
                "stocking", 
                "stomach", 
                "stone", 
                "stop", 
                "store", 
                "story", 
                "straight", 
                "strange", 
                "street", 
                "stretch", 
                "strong", 
                "structure", 
                "substance", 
                "such", 
                "sudden", 
                "sugar", 
                "suggestion", 
                "summer", 
                "sun", 
                "support", 
                "surprise", 
                "sweet", 
                "swim", 
                "system", 
                "table", 
                "tail", 
                "take", 
                "talk", 
                "tall", 
                "taste", 
                "tax", 
                "teaching", 
                "tendency", 
                "test", 
                "than", 
                "that", 
                "the", 
                "then", 
                "theory", 
                "there", 
                "thick", 
                "thin", 
                "thing", 
                "this", 
                "thought", 
                "thread", 
                "throat", 
                "through", 
                "through", 
                "thumb", 
                "thunder", 
                "ticket", 
                "tight", 
                "till", 
                "time", 
                "tin", 
                "tired", 
                "to", 
                "toe", 
                "together", 
                "tomorrow", 
                "tongue", 
                "tooth", 
                "top", 
                "touch", 
                "town", 
                "trade", 
                "train", 
                "transport", 
                "tray", 
                "tree", 
                "trick", 
                "trouble", 
                "trousers", 
                "TRUE", 
                "turn", 
                "twist", 
                "umbrella", 
                "under", 
                "unit", 
                "up", 
                "use", 
                "value", 
                "verse", 
                "very", 
                "vessel", 
                "view", 
                "violent", 
                "voice", 
                "waiting", 
                "walk", 
                "wall", 
                "war", 
                "warm", 
                "wash", 
                "waste", 
                "watch", 
                "water", 
                "wave", 
                "wax", 
                "way", 
                "weather", 
                "week", 
                "weight", 
                "well", 
                "west", 
                "wet", 
                "wheel", 
                "when", 
                "where", 
                "while", 
                "whip", 
                "whistle", 
                "white", 
                "who", 
                "why", 
                "wide", 
                "will", 
                "wind", 
                "window", 
                "wine", 
                "wing", 
                "winter", 
                "wire", 
                "wise", 
                "with", 
                "woman", 
                "wood", 
                "wool", 
                "word", 
                "work", 
                "worm", 
                "wound", 
                "writing", 
                "wrong", 
                "year", 
                "yellow", 
                "yes", 
                "yesterday", 
                "you", 
                "young"};
        
        return arr;
        
        /* To see performance on a larger array, comment / delete the above "return arr;" line and uncomment the below code.
         * This will create a giant array by concatenating words from the above list.*/

        /*ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            int n = AssortedMethods.randomIntInRange(0, 1000);
            String s = arr[i];
            for (int j = 0; j < n; j++) {
                int index = AssortedMethods.randomIntInRange(0, i);
                s += arr[index];
            }
            list.add(s);
            list.add(arr[i]);
        }
        String[] ar = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ar[i] = list.get(i);
        }       
        return ar;
        */
    }   

    public static void testA8(){

        String[] arr = createGiantArray();  
        printLongestWord(arr);
    }
    /*END of solution 18-7*/






    /*Question 18-10:*/
    public static LinkedList<String> transform(String startWord, String stopWord, Set<String> dictionary) {


        startWord = startWord.toUpperCase();
        stopWord = stopWord.toUpperCase();

        Queue<String> actionQueue = new LinkedList<String>();
        Set<String> visitedSet = new HashSet<String>();
        Map<String, String> backtrackMap = new TreeMap<String, String>();

        actionQueue.add(startWord);
        visitedSet.add(startWord);

        while (!actionQueue.isEmpty()) {

            String w = actionQueue.poll();
            
            // For each possible word v from w with one edit operation
            for (String v : getOneEditWords(w)) {
            
                if (v.equals(stopWord)) {
                    // Found our word!  Now, back track.
                    LinkedList<String> list = new LinkedList<String>();
                    // Append v to list
                    list.add(v);
                    while (w != null) {
                        list.add(0, w);
                        w = backtrackMap.get(w);
                    }
                    return list;
                }

                // If v is a dictionary word
                if (dictionary.contains(v)) {
                    if (!visitedSet.contains(v)) {
                        actionQueue.add(v);
                        visitedSet.add(v); // mark visited
                        backtrackMap.put(v, w);
                    }
                }
            }
        }
        return null;
    }

    private static Set<String> getOneEditWords(String word) {

        Set<String> words = new TreeSet<String>();

        // for every letter
        for (int i = 0; i < word.length(); i++) {

            char[] wordArray = word.toCharArray();
            // change that letter to something else
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != word.charAt(i)) {
                    wordArray[i] = c;
                    words.add(new String(wordArray));
                }
            }
        }

        return words;
    }
    
    public static HashSet<String> setupDictionary(String[] words) {

        HashSet<String> hash = new HashSet<String>();

        for (String word : words) {
            hash.add(word.toUpperCase());
        }

        return hash;
    }
    /*END of solution 18-10:*/





    /*Question 18-11:*/
    static class SquareCell {

        public int zerosRight = 0;
        public int zerosBelow = 0;

        public SquareCell(int right, int below) {
            zerosRight = right;
            zerosBelow = below;
        }
        
        public void setZerosRight(int right) {
            zerosRight = right;
        }
        
        public void setZerosBelow(int below) {
            zerosBelow = below;
        }
    }

    static class Subsquare {

        public int row, column, size;

        public Subsquare(int r, int c, int sz) {
            row = r;
            column = c;
            size = sz;
        }
        
        public void print() {
            System.out.println("(" + row + ", " + column + ", " + size + ")");
        }
    }

    /*A*/
    public static Subsquare findSquareWithSize(SquareCell[][] processed, int square_size) {
        
        // On an edge of length N, there are (N - sz + 1) squares of length sz.
        int count = processed.length - square_size + 1; 
        
        // Iterate through all squares with side length square_size.
        for (int row = 0; row < count; row++) {

            for (int col = 0; col < count; col++) {

                if (isSquare(processed, row, col, square_size)) {
                    return new Subsquare(row, col, square_size);
                }
            }
        }
        return null;
    }
    
    public static Subsquare findSquare(int[][] matrix){

        assert(matrix.length > 0);
        for (int row = 0; row < matrix.length; row++){
            assert(matrix[row].length == matrix.length);
        }
        
        SquareCell[][] processed = processSquare(matrix);
        
        int N = matrix.length;
        
        for (int i = N; i >= 1; i--) {
            Subsquare square = findSquareWithSize(processed, i);
            if (square != null) {
                return square;
            }
        }
        return null;
    }   

    private static boolean isSquare(SquareCell[][] matrix, int row, int col, int size) {

        SquareCell topLeft = matrix[row][col];
        SquareCell topRight = matrix[row][col + size - 1];
        SquareCell bottomRight = matrix[row + size - 1][col];

        if (topLeft.zerosRight < size) { // Check top edge
            return false;
        }
        if (topLeft.zerosBelow < size) { // Check left edge
            return false;
        }
        if (topRight.zerosBelow < size) { // Check right edge
            return false;
        }
        if (bottomRight.zerosRight < size) { // Check bottom edge
            return false;
        }
        return true;
    }
    
    public static SquareCell[][] processSquare(int[][] matrix) {
        
        SquareCell[][] processed = new SquareCell[matrix.length][matrix.length];
        
        for (int r = matrix.length - 1; r >= 0; r--) {

            for (int c = matrix.length - 1; c >= 0; c--) {

                int rightZeros = 0;
                int belowZeros = 0;

                if (matrix[r][c] == 0) { // only need to process if it's a black cell
                    rightZeros++;
                    belowZeros++;
                    if (c + 1 < matrix.length) { // next column over is on same row
                        SquareCell previous = processed[r][c + 1];
                        rightZeros += previous.zerosRight;
                    }
                    if (r + 1 < matrix.length) {
                        SquareCell previous = processed[r + 1][c];
                        belowZeros += previous.zerosBelow;
                    }
                }
                processed[r][c] = new SquareCell(rightZeros, belowZeros);
            }
        }   
        return processed;
    }
    
    public static void testA(){

        int[][] matrix = AssortedMethods.randomMatrix(7, 7, 0, 1);
        AssortedMethods.printMatrix(matrix);

        Subsquare square = findSquare(matrix);
        square.print();
    }
    /*END of solution A:*/




    /*Question - B*/
    public static Subsquare findSquareWithSize(int[][] matrix, int squareSize) {
        // On an edge of length N, there are (N - sz + 1) squares of length sz.
        int count = matrix.length - squareSize + 1; 
        
        // Iterate through all squares with side length square_size.
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isSquare(matrix, row, col, squareSize)) {
                    return new Subsquare(row, col, squareSize);
                }
            }
        }
        return null;
    }
    
    public static Subsquare findSquare(int[][] matrix){
        assert(matrix.length > 0);
        for (int row = 0; row < matrix.length; row++){
            assert(matrix[row].length == matrix.length);
        }
        
        int N = matrix.length;
        
        for (int i = N; i >= 1; i--) {
            Subsquare square = findSquareWithSize(matrix, i);
            if (square != null) {
                return square;
            }
        }
        return null;
    }   

    private static boolean isSquare(int[][] matrix, int row, int col, int size) {

        // Check top and bottom border.
        for (int j = 0; j < size; j++){
            if (matrix[row][col+j] == 1) {
                return false;
            }
            if (matrix[row+size-1][col+j] == 1) {
                return false;
            }
        }
        
        // Check left and right border.
        for (int i = 1; i < size - 1; i++) {
            if (matrix[row+i][col] == 1){
                return false;
            }
            if (matrix[row+i][col+size-1] == 1) {
                return false;
            }
        }
        return true;
    }
    
    public static void testB( ){
        int[][] matrix = AssortedMethods.randomMatrix(7, 7, 0, 1);
        AssortedMethods.printMatrix(matrix);
        Subsquare square = findSquare(matrix);
        square.print();
    }
    /*END of solution - B*/
    /*END of question 18-11:*/






    /*Question 18-12*/

    /*Solution - A*/
    public static int getMaxMatrix(int[][] original) {

        int maxArea = Integer.MIN_VALUE; // Important! Max could be < 0
        int rowCount = original.length;
        int columnCount = original[0].length;
        int[][] matrix = precomputeMatrix(original);
    
        for (int row1 = 0; row1 < rowCount; row1++) {
    
            for (int row2 = row1; row2 < rowCount; row2++) {

                for (int col1 = 0; col1 < columnCount; col1++) {
                
                    for (int col2 = col1; col2 < columnCount; col2++) {
                
                        int sum = computeSum(matrix, row1, row2, col1, col2);
                        if (sum > maxArea) {
                            System.out.println("New Max of " + sum + ": (rows " + row1 + " to " + row2 + ") and (columns " + col1 + " to " + col2 + ")");
                            maxArea = sum;
                        }                        
                    }
                }
            }
        }

        return maxArea;
    }

        
    private static int[][] precomputeMatrix(int[][] matrix) {

        int[][] sumMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) { // first cell
                    sumMatrix[i][j] = matrix[i][j];
                } else if (j == 0) { // cell in first column
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + matrix[i][j];
                } else if (i == 0) { // cell in first row
                    sumMatrix[i][j] = sumMatrix[i][j - 1] + matrix[i][j];
                } else {
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + 
                      sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1] +
                      matrix[i][j];
                }
            }
        }
        return sumMatrix;
    }

    
    private static int computeSum(int[][] sumMatrix, int i1, int i2, 
                                  int j1, int j2) {
        if (i1 == 0 && j1 == 0) { // starts at row 0, column 0
            return sumMatrix[i2][j2];
        } else if (i1 == 0) { // starts at row 0
            return sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1];
        } else if (j1 == 0) { // starts at column 0
            return sumMatrix[i2][j2] - sumMatrix[i1 - 1][j2];
        } else {
            return sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1] - sumMatrix[i1 - 1][j2] + sumMatrix[i1 - 1][j1 - 1];
        }
    }
    
    public static void testA1() {
        int[][] matrix = AssortedMethods.randomMatrix(10, 10, -5, 5);
        AssortedMethods.printMatrix(matrix);
        System.out.println(getMaxMatrix(matrix));
    }
    /*A*/


    /*B*/
    public static void clearArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }
    
    public static int maxSubMatrix(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
    
        int[] partialSum = new int[colCount]; 
        int maxSum = 0; // Max sum is an empty matrix
    
        for (int rowStart = 0; rowStart < rowCount; rowStart++) {
            clearArray(partialSum);
        
            for (int rowEnd = rowStart; rowEnd < rowCount; rowEnd++) {
                for (int i = 0; i < colCount; i++) {
                    partialSum[i] += matrix[rowEnd][i];
                }
        
                int tempMaxSum = maxSubArray(partialSum, colCount);
                
                // if you want to track the coordinates, add code here to do that
                maxSum = Math.max(maxSum, tempMaxSum);
            }
        }
        return maxSum;
    }

    public static int maxSubArray(int array[], int N) {

        int maxSum = 0;
        int runningSum = 0;

        for (int i = 0; i < N; i++) {
            runningSum += array[i];
            maxSum = Math.max(maxSum, runningSum);
            
            /* If running_sum is < 0 no point in trying to continue the 
             * series. Reset. */
            if (runningSum < 0) {
                runningSum = 0;
            }
        }

        return maxSum;
    }
    
    public static void testB1( ){
        int[][] matrix = AssortedMethods.randomMatrix(5, 7, -100, 100);
        int sum = maxSubMatrix(matrix);
        AssortedMethods.printMatrix(matrix);
        System.out.println(sum);
    }
    /*END of solution B:*/
    /*END of solution18-12:*/


    


    /*Question 18-13:*/
    class Rectangle {
        
        // Rectangle data.
        public int height;
        public int length; 
        public char [][] matrix;

        public Rectangle(int len) {
            this.length = len;
        }

        /* Construct a rectangular array of letters of the specified length
         * and height, and backed by the specified matrix of letters. (It is
         * assumed that the length and height specified as arguments are 
         * consistent with the array argument's dimensions.)
         */
        public Rectangle(int length, int height, char[][] letters) {
            this.height = letters.length;
            this.length = letters[0].length;
            matrix = letters;
        }

        /* Return the letter present at the specified location in the array.
         */
        public char getLetter (int i, int j) {
            return matrix[i][j];
        }
        
        public String getColumn(int i) {
            char[] column = new char[height];
            for (int j = 0; j < height; j++) {
                column[j] = getLetter(j, i);
            }
            return new String(column);
        }
        
        public boolean isComplete(int l, int h, WordGroup groupList) {
            // Check if we have formed a complete rectangle.
            if (height == h) {
                // Check if each column is a word in the dictionary.
                for (int i = 0; i < l; i++) {
                    String col = getColumn(i);
                    if (!groupList.containsWord(col)) {
                        return false; // Invalid rectangle.
                    }
                }
                return true; // Valid Rectangle!
            }
            return false;
        }
        
        public boolean isPartialOK(int l, Trie trie) {

            if (height == 0) {
                return true;
            }

            for (int i = 0; i < l ; i++ ) {
                String col = getColumn(i);
                if (!trie.contains(col)) {
                    return false; // Invalid rectangle.
                }
            }

            return true;
        }


        /* If the length of the argument s is consistent with that of this
         * Rectangle object, then return a Rectangle whose matrix is constructed by
         * appending s to the underlying matrix. Otherwise, return null. The
         * underlying matrix of this Rectangle object is /not/ modified.
         */
        public Rectangle append(String s) {

            if (s.length() == length) {

                char temp[][] = new char[height + 1][length];
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < length; j++) {
                        temp[i][j] = matrix[i][j];
                    }
                }
                s.getChars(0, length, temp[height], 0);

                return new Rectangle(length, height + 1, temp);
            }
            return null;
        }

        /* Print the rectangle out, row by row. */
        public void print() {
            for (int i = 0; i < height ; i++) {
                for (int j = 0; j < length; j++) {
                    System.out.print(matrix[i][j]);
                }
                System.out.println(" ");
            }
        }
    }



    class WordGroup {

        private Hashtable<String, Boolean> lookup = new Hashtable<String, Boolean>();
        private ArrayList<String> group = new ArrayList<String>();
        
        public WordGroup() {

        }
        
        public boolean containsWord(String s) {
            return lookup.containsKey(s);
        }
        
        public void addWord (String s) {
            group.add(s);
            lookup.put(s, true);
        }
        
        public int length() {
            return group.size();
        }
        
        public String getWord(int i) {
            return group.get(i);
        }
        
        public ArrayList<String> getWords(){ 
            return group;
        }
        
        public static WordGroup[] createWordGroups(String[] list) {
            WordGroup[] groupList;
            int maxWordLength = 0;
            // Find out the length of the longest word
            for (int i = 0; i < list.length; i++) {
                if (list[i].length() > maxWordLength) {
                    maxWordLength = list[i].length();
                }
            }

            /* Group the words in the dictionary into lists of words of 
             * same length.groupList[i] will contain a list of words, each 
             * of length (i+1). */
            groupList = new WordGroup[maxWordLength];

            for (int i = 0; i < list.length; i++) {
                /* We do wordLength - 1 instead of just wordLength since this is used as
                 * an index and no words are of length 0 */
                int wordLength = list[i].length() - 1; 
                if (groupList[wordLength] == null) {
                    groupList[wordLength] = new WordGroup();
                }
                groupList[wordLength].addWord(list[i]);
            }

            return groupList;
        }
    }


    private int maxWordLength;
    private WordGroup[] groupList ;
    private Trie trieList[];

    public Question(String[] list) {

        groupList = WordGroup.createWordGroups(list);
        maxWordLength = groupList.length;
        // Initialize trieList to store trie of groupList[i] at ith position.
        trieList = new Trie[maxWordLength];
    }

    /* This function finds a rectangle of letters of the largest 
     * possible area (length x breadth) such that every row forms a 
     * word (reading left to right) from the list and every column 
     * forms a word (reading top to bottom) from the list. 
     */
    public Rectangle maxRectangle() {

        // The dimensions of the largest possible rectangle.
        int maxSize = maxWordLength * maxWordLength; 

        for (int z = maxSize; z > 0; z--) {
            // Find out all pairs i,j less than maxWordLength 
            // such that i * j = z
            for (int i = 1; i <= maxWordLength; i ++ ) {
                if (z % i == 0) {
                    int j = z / i;
                    if (j <= maxWordLength) {
                        // Check if a Rectangle of length i and height 
                        // j can be created. 
                        Rectangle rectangle = makeRectangle(i,j);
                        if (rectangle != null) {
                            return rectangle;
                        }
                    }
                }
            }
        }
        return null;
    }

    /* This function takes the length and height of a rectangle as
     * arguments. It tries to form a rectangle of the given length and 
     * height using words of the specified length as its rows, in which 
     * words whose length is the specified height form the columns. It 
     * returns the rectangle so formed, and null if such a rectangle 
     * cannot be formed.
     */
    private Rectangle makeRectangle(int length, int height) {
        if (groupList[length - 1] == null || groupList[height - 1] == null) {
            return null;
        }
        if (trieList[height - 1] == null) {
            ArrayList<String> words = groupList[height - 1].getWords();
            trieList[height - 1] = new Trie(words); 
        }
        return makePartialRectangle(length, height, new Rectangle(length));
    }


    /* This function recursively tries to form a rectangle with words
     * of length l from the dictionary as rows and words of length h
     * from the dictionary as columns. To do so, we start with an empty
     * rectangle and add in a word with length l as the first row. We
     * then check the trie of words of length h to see if each partial
     * column is a prefix of a word with length h. If so we branch
     * recursively and check the next word till we've formed a complete
     * rectangle. When we have a complete rectangle check if every
     * column is a word in the dictionary.
     */
    private Rectangle makePartialRectangle(int l, int h, Rectangle rectangle) {

        // Check if we have formed a complete rectangle by seeing if each column
        // is in the dictionary
        if (rectangle.height == h) {

            if (rectangle.isComplete(l, h, groupList[h - 1])) {
                return rectangle;
            } else {
                return null;
            }
        }

        // If the rectangle is not empty, validate that each column is a
        // substring of a word of length h in the dictionary using the
        // trie of words of length h.
        if (!rectangle.isPartialOK(l, trieList[h - 1])) {
            return null;
        }
        
        // For each word of length l, try to make a new rectangle by adding
        // the word to the existing rectangle.
        for (int i = 0; i < groupList[l-1].length(); i++) {

            Rectangle orgPlus = rectangle.append(groupList[l-1].getWord(i));
            Rectangle rect = makePartialRectangle(l, h, orgPlus);
            if (rect != null) {
                return rect;
            }
        }
        return null;
    }

    // Test harness.
    public static void testA2(){

        Question dict = new Question(AssortedMethods.getListOfWords());
        Rectangle rect = dict.maxRectangle();

        if (rect != null) {
            rect.print();
        } else {
            System.out.println ("No rectangle exists");
        }
    }
    /*END of solution 18-13*/





    /*question: design an algorithm to get top 3 
    maximum occurances of String from a String list*/

    /*END of solution: design an algorithm 
    to get top 3 maximum occurances of String 
    from a String list*/







    /*question: desing a program 
    to synchronize a HashMap*/

    /*
    1 Brad
    2 Anil
    4 Sachin
    88 XYZ
    44 Ajit */

    public static void synchronizeHashMap() {

        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        hmap.put(2, "Anil");
        hmap.put(44, "Ajit");
        hmap.put(1, "Brad");
        hmap.put(4, "Sachin");
        hmap.put(88, "XYZ");

        /*
        * The HashMap is not thread-safe. We ned to make it thread-safe 
        */
        Map map = Collections.synchronizedMap(hmap);
        Set set = map.entrySet();

        // now starts the synchronized block of code 
        synchronized(map){

            Iterator i = set.iterator();
            
            while(i.hasNext()) {

                Map.Entry me = (Map.Entry)i.next();
                System.out.print(me.getKey() + " " + me.getValue());
            }
        }
    }
    /*END of solution: desing a program 
    to synchronize a HashMap*/







    /*question: design a program to delete 
    multiple white spaces from the Sting*/
    public static void delMultiWhSpaces() {


        String str = "String    With Multiple      Spaces";

        StringTokenizer st = new StringTokenizer(str, " ");
        StringBuilder builder = new StringBuilder();

        while(st.hasMoreElements()){
            builder.append(st.nextElement()).append(" ");
        }

        System.out.println(builder.toString().trim());
        // "String With Multiple Spaces"
    }
    /*END of solution: design a program to 
    delete multiple white spaces from the 
    Sting*/








    /*question: an array for which the ith element is the 
    price of a given stock on day i. Design an algorithm to 
    find the maximum profit. You may complete as many transactions 
    as you like (ie, buy one and sell one share of the stock multiple 
    times) with the following restrictions: You may not engage in 
    multiple transactions at the same time (ie, you must sell the 
    stock before you buy again). After you sell your stock, you 
    cannot buy stock on next day. (ie, cooldown 1 day)*/

    public int maxProfit(int[] prices) {
        
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int[] sells = new int[prices.length];
        int[] buys = new int[prices.length];

        sells[0] = 0;
        sells[1] = Math.max(0, prices[1] - prices[0]);

        buys[0] = -prices[0];
        buys[1] = Math.max(-prices[0], -prices[1]);

        for (int i = 2; i < prices.length; i++) {

            sells[i] = Math.max(sells[i - 1], buys[i - 1] + prices[i]);
            buys[i] = Math.max(buys[i - 1], sells[i - 2] - prices[i]);
        }
        
        return sells[sells.length - 1];
    }
    /*END of solution: maximum profit 
    for stock buy and sell*/








    /*Question: create sub-map from the tree map*/
    public static void myTreeMap1( ) {
     
        // Create a TreeMap
        Map<String, String> treemap = new TreeMap<String, String>();

        // Put elements to the map
        treemap.put("Key1", "Jack");
        treemap.put("Key2", "Rick");
        treemap.put("Key3", "Kate");
        treemap.put("Key4", "Tom");
        treemap.put("Key5", "Steve");
        treemap.put("Key6", "Ram");
     
        System.out.println("TreeMap Contains : " + treemap);
          
        // subMap is not found 
        SortedMap<String, String> sortedMap = treemap.subMap("Key2","Key5");
        System.out.println("SortedMap Contains : " + sortedMap);

        sortedMap.remove("Key4");

        System.out.println("TreeMap Contains : " + treemap);
    }
    /*END of the question*/




    /*Question: create sub-map from the tree map*/
    public static void myTreeMap2( ) {


        List<Integer> keys = new ArrayList<>();

        keys.add(2);
        keys.add(3);
        keys.add(42); // this key is not in the map

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "foo");
        map.put(2, "bar");
        map.put(3, "fizz");
        map.put(4, "buz");

        Map<Integer, String> res = keys.stream()
            .filter(map::containsKey)
            .collect(Collectors.toMap(Function.identity(), map::get));

        System.out.println(res.toString());  
    }




    /*question: write a program  
    with syntaxes of TreeMap*/
    public static void myTreeMap(){

        TreeMap<String, String> treemap = new TreeMap<String, String>();

        // Put elements to the map
        treemap.put("Key1", "Jack");
        treemap.put("Key2", "Rick");
        treemap.put("Key3", "Kate");
        treemap.put("Key4", "Tom");
        treemap.put("Key5", "Steve");
     
        // Calling the method sortByvalues
        Map sortedMap = sortByValues(treemap);
        Set set = sortedMap.entrySet();
     
        // Get an iterator
        Iterator i = set.iterator();
     
        // Display elements
        while(i.hasNext()) {

            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + " "+ me.getValue());
        }
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {

        Comparator<K> valueComparator =  new Comparator<K>(){

            public int compare(K k1, K k2){

                int compare = map.get(k1).compareTo(map.get(k2));

                if (compare == 0){
                    return 1;
                }                    

                else {
                    return compare;
                }
            }

        };

        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);

        return sortedByValues;
    }
    /*END of solution: write a program  
    with syntaxes of TreeMap*/






    /*question: Finding duplicate entry in an integer 
    array. Next there were variations of this where 
    I had to find duplicate entry only if it was 
    within some index range, and next if the values 
    were not actually duplicate but within some range 
    of values.*/ 

        // some code 
    /*END OF SOLUTION*/





    /*question: Given an array as input find the output 
    array that has median of each sub array whose 
    index starts from 0 to i(i = 1,2...array.length-*/

        /*http://stackoverflow.com/questions/29187412/
        given-an-array-as-input-find-the-output-array-
        that-has-median-of-each-sub-array*/

    /*END OF SOLUTION*/





    /*question: write a string compress function: 
    give you "RRGB" and return "R2G1B1"*/
        // some code
    /*END of solution*/





    /*question:How would you find duplicates 
    between two arrays?*/
        // Sort both arrays, then traverse them together. 
        //O(NlogN), where N is the size of the larger array.
    /*END of solution*/






    /*qustion: Explain the significance of 2^32*/
        // some code
    /*END of solution*/






    /*question: Given an unsorted list of integers, return true 
    if the list contains any fuzzy duplicates within k indices 
    of each element. A fuzzy duplicate is another integer within 
    d of the original integer. Example: If d=4, then 6 is a 
    fuzzy duplicate of 3 but 8 is not. Do it faster than O(n^2). */


    /*Similar to the anwer to the previous question, but keep a 
    TreeMap instead of HashMap and for each x in the array check 
    if map.ceilingKey(x) - x &lt;= d || x - map.floorKey(x) &lt;= d.*/
    /*END of solution*/








    /*question: Given an unsorted list of integers, return true if the 
    list contains any duplicates within k indices of each element. Do 
    it faster than O(n^2).  */
    // some code
    /*END of solution*/




    /*question:  Given a stream of integers, all of which come in pairs   
    except for one, find the integer without a duplicate.*/
    // XOR all the numbers
    /*It's worth noting that a hashmap will also give O(n) 
    performance in the average case, but uses O(n) space. 
    Using an XOR hash is O(n) running time and O(1) space 
    with certainty.*/
     /*END of solution*/






     /*question:Sort an array where each item is at most k 
     indices from its position in the sorted array. What's 
     the run time?  */
     
         /*1.Scan the array using a heap with k elements, popping 
         the least element in the heap, and heaping the next 
         element in the array. This will run in O(n log k) time.

         2. Insertion sort does O(kn) in nearly sorted arrays. 
         Also, you can sort the elements with indices 1-2*k, 
         k-3*k, 2*k-4*k.. That will be in O(n log k)*/

    /*END of solution*/







    /*question: If there are two lists, and each have an identical 
    number   in them except for one unique number, how do you 
    find the unique number? */

        /*1. Hash all the numbers of one array into a hashmap. 
        Iterate through the other array and see if the hashmap 
        contains the current element.
        2.if I understand problem correctly you have 1: 5 8 9 1 2: 5 9 8 and 
        the answer 1. Iterate all numbers and do xor numbers. The rule is 
        a xor a = 0, 0 xor x = x, x xor 0 = x. So you'll get unique number.*/

    /*END OF SOLUTION*/




    /*question A*/
    /*
    There is a queue for the self-checkout tills at the supermarket. 
    Your task is write a function to calculate the total time required for all the customers to check out!

    The function has two input variables:

    customers: an array (list in python) of positive integers representing the queue. 
    Each integer represents a customer, and its value is the amount of time they require to check out.
    n: a positive integer, the number of checkout tills.
    The function should return an integer, the total time required.

    Assume that the front person in the queue (i.e. the first element in the array/list) 
    proceeds to a till as soon as it becomes free. So, for example:

    N.B. You should assume that all the test input will be valid, as specified above.

    P.S. The situation in this kata can be likened to the more-computer-science-related 
    idea of a thread pool, with relation to running multiple processes at the same time: https://en.wikipedia.org/wiki/Thread_pool
    */

    public static int solveSuperMarketQueue(int[] customers, int n){

        return 0;
    }
    /*END OF SOLUTION*/





    /*question:You have a set of (time,value) pairs. How can you 
    find the first and last values in the time interval [a,b]. 
    Answer: You do binary search.*/

        // some code 
    /*END OF SOLUTION*/






    /*question: desing a program to implemenet  
    the traveling salesman problem.*/

        // some code 
    /*END OF SOLUTION*/




    /*question: design an algorithm to sort 
    alphabetically an array of Strings*/

    /*solution-a*/
    public static List<String> sortStrings(List<String> names){

        // List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        System.out.println(names);

        Collections.sort(names, new Comparator<String>() {

            @Override
            public int compare(String a, String b){            
                return a.compareTo(b);
            }
        });

        System.out.println(names);
        return names; 
    }
    /*END of solution-a*/




    /*solution-b*/
    public static List<String> sortStrings1( List<String> names){

        // List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        System.out.println(names);

        Collections.sort(names, (String a, String b)->{ 
            return a.compareTo(b); 
        });

        // Collections.sort(names, (String a, String b) -> a.compareTo(b)); 
        // Collections.sort(names, (a, b) -> a.compareTo(b));
        // Collections.sort(names); 

        System.out.println(names);
        return names; 
    }
    /*END of solution-b*/  
    /*END of solution: design an algorithm to sort 
    alphabetically an array of Strings*/




    /*question: write an algorithm for 
    printing upward sequencing of numbers*/
    public static void countUp(int start, int end) {    

        System.out.println(start);

        if( start < end){
            countUp(start+1, end);            
        }
    }
    /*END of solution: write an algorithm for 
    printing upward sequencing of numbers*/





    /*question: write an algorithm for printing 
    upward and downward sequencing of numbers*/

    /*solution-a*/
    public static void countUpDown(int start, int end) {    

        System.out.println(start);

        if( start < end){

            countUp(start+1, end);
            System.out.println(start);
        }
    }
    /*END of solution-a*/




    /*solution-b*/
    public static void countUpDown1(int start, int end) {    

        System.out.println(start);

        if( start < end){
            countUp(start+1, end);           
        }

        if( start != end){
            System.out.println(start);
        }
    }
    /*END of solution-b*/
    /*END of solution: write an algorithm for printing 
    upward and downward sequencing of numbers*/



    /*question: provides the examples for the 
    synchronized colections*/
    public static void synchronizedDataStorages( ){
 
        // ********************** synchronizedList ************************
        ArrayList<String> al = new ArrayList<String>();
 
        // populate the crunchifyArrayList
        al.add("eBay");
        al.add("Paypal");
        al.add("Google");
        al.add("Yahoo");

 
        // Returns a synchronized (thread-safe) list backed by the specified
        // list. In order to guarantee serial access, it is critical that all
        // access to the backing list is accomplished through the returned list.
        List<String> synchronizedList = Collections.synchronizedList(al);
 
        System.out.println("synchronizedList conatins : " + synchronizedList);
 
        // ********************** synchronizedMap ************************
        Map<String, String> map = new HashMap<String, String>();
 
        // populate the crunchifyMap
        map.put("1", "eBay");
        map.put("2", "Paypal");
        map.put("3", "Google");
        map.put("4", "Yahoo");
 
        // create a synchronized map
        Map<String, String> synchronizedMap = Collections.synchronizedMap(map);
 
        System.out.println("synchronizedMap contains : " + synchronizedMap);
    }
    /*END of solutions: provides the examples for the 
    synchronized colections*/




    /*question: a robot is in the (0,0) co-ordinate of the 
    XY grid system and beign navigated by direction.It can 
    have 3 directions : 'F' for forward, 'R' for 90 degrees 
    right turn and 'L' for 90 degrees left turn. Desing an 
    algorithm to find the final position provided with 
    direciton String*/

    // need to solve with the "State" design pattern

    /*solution-a*/
    public static void robotPosition(String str ){

        if( str == null || str.length() == 0){
            return;
        }

        int [] initial = {0,0};
        
        boolean xPos = false, xNeg = false, yPos = true, yNeg = false; 
        char[] ch = str.toCharArray();

        for( char c: ch){

            // the initial postion of the robot is towards the positive Y axis 

            if(c == 'L'){

                if(xPos){
                    xPos = false;
                    yPos = true;
                }

                else if ( xNeg){
                    xNeg = false;
                    yNeg = true;
                }

                else if(yPos){
                    xNeg = true;
                    yPos = false;             
                }

                else if (yNeg){
                    yNeg = false;
                    xPos = true;
                }
            }

            else if ( c == 'R'){

                if(xPos){
                    xPos = false;
                    yNeg = true;
                }

                else if(xNeg){
                    yPos = true;
                    xNeg = false;
                }

                else if(yPos){
                    yPos = false;
                    xPos = true;
                }

                else if(yNeg){
                    yNeg = false;
                    xNeg = true;
                }                
            }

            else if (c == 'F'){

                if(xNeg){
                    initial[0]  -= 1;
                }

                else if (xPos){
                    initial[0] += 1;
                }

                else if (yNeg){
                    initial[1] -=1;
                }

                else if( yPos){
                    initial[1] += 1;
                }
                
            }
        }

        System.out.println(str + "\t" + Arrays.toString(initial));
    }
    /*END of solution-a*/




    /*solution-b*/
    private static enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }

    public static void robotPosition1(String str){

        if(str == null || str.length() == 0) {
            return;
        }

        int[] initial = {0,0};

        Direction dir = Direction.NORTH;
        char[] ch = str.toCharArray();

        for(char c: ch) {

            if(c == 'L') {
                dir = direction(dir, c);
            }

            else if (c == 'R'){

                dir = direction(dir, c);
            }

            else if (c == 'F') {

                int[] test = hashing(dir);

                int index = test[0];
                int change = test[1];

                initial[index] += change;
            }

            else 
                System.out.println("Illegal input");
        }

        System.out.println(Arrays.toString(initial));
    }


    public static Direction direction(Direction dir, char ch ){

        if(dir == Direction.NORTH && ch == 'L') 
            return Direction.WEST;

        else if(dir == Direction.NORTH && ch == 'R') 
            return Direction.EAST;

        else if(dir == Direction.SOUTH && ch == 'L') 
            return Direction.EAST;

        else if(dir == Direction.SOUTH && ch == 'R') 
            return Direction.WEST;

        else if(dir == Direction.WEST && ch == 'L') 
            return Direction.SOUTH;

        else if(dir == Direction.WEST && ch == 'R') 
            return Direction.NORTH;

        else if(dir == Direction.EAST && ch == 'L') 
            return Direction.NORTH;

        else if(dir == Direction.EAST && ch == 'R') r
            eturn Direction.SOUTH;

        else return null;
    }

    public static int[] hashing( Direction dir ){

        if( dir == null) return null; 

        Map<Direction, Integer[]> map = new HashMap<Direction, Integer[]>();
        
        // {index, change}
        map.put(Direction.NORTH, new int[]{1,1} );
        map.put(Direction.SOUTH, new int[] {1,-1});
        map.put(Direction.EAST, new int[] {0, 1});
        map.put(Direction.WEST, new int[] {0, -1});

        return map.get(dir);
    }
    /*END of solution-b*/




    /*solution-c*/
    public static void robotNavigator2(String str) {

        if (str == null || str.length() == 0) 
            return;

        int[] initial = {0, 0};
        int angle = 90;
        char[] ch = str.toCharArray();

        for (char c : ch) {

            // anti-up positive 
            if (c == 'L') {            
                angle = (angle + 90) % 360;
            } 

            // clock down negative 
            else if (c == 'R') {           
                angle = (angle - 90) % 360;
            } 

            else if (c == 'F') {          
                initial[0] += (int) Math.cos(Math.toRadians(angle));
                initial[1] += (int)  Math.sin(Math.toRadians(angle));
            }
        }

        System.out.println(Arrays.toString(initial));
    }
    /*END of solution-c*/




    /*
    @Test
    public void testRobotNavigator() {      

        assertEquals("[0, 2]", Robot.robotNavigator("FF"));
        assertEquals("[-3, 0]", Robot.robotNavigator("LFFFRFFFRRFFF")); 
        assertEquals("[5, -3]", Robot.robotNavigator("RFFFLFFLLFFFFFRLLFF"));
        assertNotEquals("[6, -3]", "RFFFLFFLLFFFFFRLLFF");
                
        assertNull(Robot.robotNavigator(null));
        assertNull("No direction is provided", Robot.robotNavigator(""));   
        assertNotNull(Robot.robotNavigator("FF"));
    }
    */




    /*solution-d*/
    public static void robotNavigator3(String str) {

        if (str == null || str.isEmpty())
            return;

        int x = 0, y = 0, dir = 0;

        for (char c : str.toCharArray()){

            if (c == 'R')
                dir = (dir + 1) % 4; // dir++: 0 -> 1 -> 2 -> 3 -> 0

            else if (c == 'L')
                dir = (dir + 3) % 4; // dir--: 0 -> 3 -> 2 -> 1 -> 0

            else if (c == 'F'){

                if (dir == 0)
                    y++; // 0: Up

                else if (dir == 1)
                    x++; // 1: Right

                else if (dir == 2)
                    y--; // 2: Down

                else
                    x--; // 3: Left          
            }
        }    

        System.out.println(x + " " + y);
    }
    /*END of solution-d*/


    /*END of solution: a robot is in the (0,0) co-ordinate 
    of the XY grid system and beign navigated by direction.
    It can have 3 directions : 'F' for forward, 'R' for 90 
    degrees right turn and 'L' for 90 degrees left turn. 
    Desing an algorithm to find the final position provided 
    with direciton String*/







    /*question: desing an algorithm to m in n 
    closest parts of array where the elements 
    of the array will be closest to each other 
    and in decreasing order. Say, with m = 121 
    and n = 4, the solution will be [30,30,30,31]*/
    public static int[] divideMoney(int m, int n ){

        // some code 
    }
    /*END of solution: desing an algorithm to m 
    in n closest parts of array where the elements 
    of the array will be closest to each other 
    and in decreasing order. Say, with m = 121 
    and n = 4, the solution will be [30,30,30,31]*/








    /*question: write an algorithm to separate 
    the numbers in an array such that negative 
    numbers are at the beginning and positive 
    numbers at the end without changing the order. 
    The algorithm shouldn't use sorting and perform 
    it in O(n) time and O(1) space, both with and 
    without additional data-structure

    int[] arr = {9, 2, -3, 1, 0, 0, -7, -6, 3, -5, 2}; // test data 
    int[] result = {-3, -7, -6, -5, 9, 2, 1, 0, 0, 3, 2};*/

    public static int[] seperateNums(int[] arr){

        int val = 0, index = 0, negIndex = 0;
     
        for ( int i = 0; i < arr.length; i++) {

            // we get the index and the value of the array element 
            val = arr[i];
            index = i;

            /*
             * put the first negative num in the 1st 
             * position, 2nd in the 2nd position and 
             * so on
            */ 
            while(index > negIndex && val < 0){

                arr[index] = arr[index-1];
                index--;

                if(negIndex == index ){

                    // this will be index we will fill in the next 
                    negIndex++;                    
                }
            }

            arr[index] = val; 
        }

        return arr; 
    }
    /*END of solution*/







    /*question: find the min index 
    value of an arraylist*/
    public static int findMinIndex(List<Integer> list) {

        int min = Collections.min(list);
        int minIndex = list.indexOf(min);

        return minIndex;
    }

    public static <T extends Comparable<T>> int findMinIndex1(final List<T> xs){

        int minIndex;

        if (xs.isEmpty()){
            minIndex = -1;
        }            
    
        else {

            // xs.iterator() also works 
            final ListIterator<T> itr = xs.listIterator();

            T min = itr.next(); // first element as the current minimum
            minIndex = itr.previousIndex();

            while (itr.hasNext()) {
            
                final T curr = itr.next();
            
                if (curr.compareTo(min) < 0) {
            
                    min = curr;
                    minIndex = itr.previousIndex();
                }
            }
        }

        return minIndex;
    }
    // we can get the min and also, the max values using the index
    /*END of solution: find the min 
    index value of an arraylist*/





    /*
        You are given a list of trips that together form a journey from one place to another. A trip has the form [starting place of the trip, destination place of the trip]. You are given the trips in random order. Write a function to find out what is the starting place of the journey. You may assume you are given the trips of the journey in a suitable data structure of your choice. You may assume a place is represented by a string.

        Example 1:
        exampleTrips := [[Cologne,Berlin],[Munich,Cologne],[YourPlace,Munich]]
        The starting place of the journey given by exampleTrips is "YourPlace".

        Example 2:
        exampleTrips := [ [A,B], [B,C], [C,D] ]
        The trip in this example started at "A".

        Example 3:
        exampleTrips := [ [D,E], [F,D], [E,X] ] 
    */
    /*
     * function to find the starting city of a
     * given journey from a list of trips
     *
     * time complexity for the solution is O(n)
     * */
    private static String findStartCity(Map<String, String> trips) {

        Map<String, String> reverseTrips = new HashMap<String, String>();


        /*
         * create a reverse map for the trips where
         * start and destination inter-changed
         * */
        for (Map.Entry<String, String> entry : trips.entrySet()) {
            reverseTrips.put(entry.getValue(), entry.getKey());
        }

        String startCity = null;

        /*
         * if a start city for a trip is not present as the destination
         * of any other trip, the city of the starting point for the whole
         * journey
         * */
        for (Map.Entry<String, String> entry : trips.entrySet()) {

            if (!reverseTrips.containsKey(entry.getKey())) {
                startCity = entry.getKey();
                break;
            }
        }

        // the inputs are not valid
        if (startCity == null) {
            return null;
        }

        return startCity;
    }


    /*
    * solution - b
    */    
    private static class Pair<A, B> {

        private final A start;
        private final B destination;

        public Pair(A start, B destination) {
            super();
            this.start = start;
            this.destination = destination;
        }

        public A getStart() {
            return start;
        }

        public B getDestination() {
            return destination;
        }

        public int hashCode() {

            int hashFirst = start != null ? start.hashCode() : 0;
            int hashSecond = destination != null ? destination.hashCode() : 0;

            return (hashFirst + hashSecond) * hashSecond + hashFirst;
        }

        public boolean equals(Object other) {

            if (other instanceof Pair) {
                Pair otherPair = (Pair) other;
                return
                        ((this.start == otherPair.start ||
                                (this.start != null && otherPair.start != null &&
                                        this.start.equals(otherPair.start))) &&
                                (this.destination == otherPair.destination ||
                                        (this.destination != null && otherPair.destination != null &&
                                                this.destination.equals(otherPair.destination))));
            }

            return false;
        }

        public String toString() {
            return "(" + start + ", " + destination + ")";
        }

    }


    private static String findStartCity1(List<Pair<String, String>> trips) {

        List<String> cities = new ArrayList<>();

        trips.forEach(trip -> {

            String start = trip.getStart();
            String destination = trip.getDestination();

            if (cities.contains(start)) {
                cities.remove(start);
            } else {
                cities.add(0, start);
            }

            if (cities.contains(destination)) {
                cities.remove(destination);
            } else {
                cities.add(destination);
            }

        });

        return cities.get(0);
    }




    /*question: design an algorithm to find the 
    maximum 3 occurances from a Array of Strings*/

    /*
    * String[] lis = {"Dhaka", "Munich", "Berlin", "Miami", "Seattle", 
    * "Munich", "Munich", "Seattle", "Dhaka", "Dhaka", "Dhaka", "Dhaka"};
    */
    public static void maxStringFrequencies(String[] arr){

        if (arr == null || arr.length == 0){
            return;
        }

        Map<String, Integer> map = new HashMap<>();

        for(String a : arr){
            map.put(a, map.containsKey(a) ? map.get(a) + 1 : 1);
        }

        Map<Integer, List<String>> treeMap = new TreeMap<>();

        for(Map.Entry<String, Integer> entry: map.entrySet()){

            String key = entry.getKey();
            Integer value = entry.getValue();

            if(!treeMap.containsKey(value)){

                //1 
                
                // List<String> lis = new ArrayList<>();
                // lis.add(key);
                // treeMap.put(value, lis);


                //2 
                // treeMap.put(value, new ArrayList<String>(){{add(key);}} );
                
                //3
                treeMap.put(value, new ArrayList<String>(Arrays.asList(key)));
            }

            else {

                treeMap.get(value).add(key);
            }
        }

        List<Integer> lis = new ArrayList<>(treeMap.keySet());
        int count =0;

        out:
            for (int i = lis.size()-1; i >=0; i--){

                // System.out.println( "key = " lis.get(i)+  " , value = "+ treeMap.get(lis.get(i)));

                for(String st : treeMap.get(lis.get(i))){

                    count++;
                    System.out.println" Number of occurance "+(lis.get(i)+ " : "+ st);

                    if (count == 3){
                        break out; 
                    }
                }
            }
    } 
    /*END of solution: design an algorithm to find the 
    maximum tree occurances from a Array of Strings*/






    /*question : write an example 
    to learn about ArrayList*/
    public static void learnArrayList(){

        List<String> list = new ArrayList<String>();

        list.add("Miami");
        list.add("Seattle");
        list.add("Berlin");
        list.add("New York");

        // this will squeeze in the index 2,       
        list.add( 2,"Boston");

        // list.toString() will also be same, print as String 
        System.out.println("The arraylist contains the following elements: "
                + list);

        // Checking index of the first instance of "Berlin"
        int pos = list.indexOf("Berlin");
        System.out.println("The index of Item2 is: " + pos);

        boolean check = list.isEmpty();
        System.out.println("Checking if the arraylist is empty: " + check);

        int size = list.size();
        System.out.println("The size of the list is: " + size);

        boolean element = list.contains("Boston");
        System.out.println("Checking if the arraylist contains the object Item5: "
                        + element);

        String item = list.get(0);
        System.out.println("The item is the index 0 is: " + item);

        System.out.println("Retrieving items with loop using index and size list");

        for ( int i = 0; i < list.size(); i++ ){
            System.out.println("Index: " + i + " - Item: " + list.get(i));
        }

        System.out.println("Retrieving items using foreach loop");

        for (String str : list){
            System.out.println("Item is: " + str);
        }

        System.out.println("Retrieving items using iterator");

        // list.listIterator()
        for (Iterator<String> it = list.iterator();  it.hasNext();){
            System.out.println( "Item is: " + it.next() );
        }

        // set replaces the element in the index of 1 
        list.set(1, "NewItem");
        System.out.println("The arraylist after the replacement is: " + list);

        /*list.remove() returns object*/
        list.remove(0);

        // removing the first occurrence of item "Seattle"
        list.remove("Seattle");
        System.out.println("The final contents of the arraylist are: " + list);

        // array conversion works fine with the Class types, say 
        // with String,but, doesn't work with primitives 
        String [] simpleArray = list.toArray(new String[list.size()]);

        System.out.println( "The array created after the conversion of our arraylist is: "
                        + Arrays.toString( simpleArray) );
    }
    /*END of solution: write an example 
    to learn about ArrayList*/







    /*question: design an algorithm to reverse a List*/
    /*solution-a*/
    public static ArrayList<Integer> reverseList(ArrayList<Integer> list) {

        int N = list.size() - 1;

        for (int i = 0; i < N; i++){
            list.add(i, list.remove(N));
        }

        return list;
    }
    /*END of solution-a*/




    /*solution-b*/
    public static <T>ArrayList<T> reverseList1(ArrayList<T> list) {

        if(list.size() > 1){  

            // if the List<Integer> lis = new ArrayList<Integer>(); 
            // lis.add(12);
            // int a = lis.remove(0); // is correct
            // Integer b = lis.remove(0); // is also correct  
            // int a = 1234;
            // lis.add(a);

            T value = list.remove(0);

            reverseList1(list);
            list.add(value);
        }

        return list;
    }
    /*END of solution-b*/



    /*solution-c*/
    public static <T> ArrayList<T> reverseList2(ArrayList<T> list) {

        int N = list.size();
        ArrayList<T> result = new ArrayList<T>(N);

        for (int i = N - 1; i >= 0; i--) {
            result.add(list.get(i));
        }

        return result;
    }
    /*END of solution-c*/



    /*solution-d*/
    public static <T>List<T> reverseList( List<T> lis){

        int index = 0, len = lis.size();

        while(index < len-1){
            lis.add(index++, lis.remove(len));
        }

        return lis; 
    }
    /*END of solutuion-d*/

    /*END of solution:design an algorithm 
    to reverse a List*/



    /*
    * `2017-03-18T22:42:21.3763342Z` => what's the unix ms since epoch timestamp
    * for this date string and what's the Java code to do that transformation?
    * */
    public static long getUnixMiliSeconds(String s) {

        Instant instant = Instant.parse(s);

        long previous = instant.toEpochMilli();
        long now = Instant.now().toEpochMilli();

        long diff = now - previous;

        return diff;
    }



    /*question: write a program to 
    learn about HashSet*/
    public static void learnHashSet() {

        // set.forEach(System.out::println);
        Set<String> hash = new HashSet<>();

        hash.add("castle");
        hash.add("bridge");
        hash.add("castle"); // Duplicate element.
        hash.add("moat");

        // Display size.
        System.out.println(hash.size());

        // See if these three elements exist.
        System.out.println(hash.contains("castle"));
        System.out.println(hash.contains("bridge"));
        System.out.println(hash.contains("moat"));

        HashSet<Integer> hash1 = new HashSet<>();

        // Zero elements.
        System.out.println(hash1.size());
        System.out.println(hash1.isEmpty());
        System.out.println();

        // Add one element.
        hash1.add(10);
        System.out.println(hash1.size());
        System.out.println(hash1.isEmpty());

        /* takes everything on the list, but, 
        escapes any duplicates*/
        ArrayList<String> list = new ArrayList<>();

        list.add("socrates");
        list.add("plato");
        list.add("cebes");

        // Add all elements to HashSet.
        HashSet<String> hash2 = new HashSet<>();

        hash2.add("kierkegaard");
        // will print all the entries including the hash set values 
        // [socrates, plato, cebes, kierkagaard]
        hash2.addAll(list);
        
        // Use contains.
        boolean a = hash.contains("cebes");
        System.out.println(a);

        // containsAll checks whether hash 
        // contains all the elements of the list
        boolean b = hash2.containsAll(list);
        System.out.println(b);

        /* retainAll takes the intersecting 
        elemenst or the Union of the list and set*/
        ArrayList<Integer> list5 = new ArrayList<>();

        list5.add(1);
        list5.add(2);
        list5.add(3);

        // This HashSet has 2, 3 and 4.
        Set<Integer> set5 = new HashSet<>();

        set5.add(2);
        set5.add(3);
        set5.add(4);

        // only retain the common elements (i.e. 2,3)
        set5.retainAll(list5);

        for (Integer v : set5){
            System.out.println(v);
        }

        // it will call the toString() method of the hash 
        System.out.println(set5);
    }
    /*END OF SOLUTION: learn about 
    HashSet (DOT NET PERLS)*/









    /*question: design a program to learn 
    about hash table data-structure*/
    public static void learnHashtable(){

        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        // Adding Key and Value pairs to Hashtable
        hashtable.put("Key1","Chaitanya");
        hashtable.put("Key2","Ajeet");
        hashtable.put("Key3","Peter");
        hashtable.put("Key4","Ricky");
        hashtable.put("Key5","Mona");

        Enumeration names = hashtable.keys();

        while(names.hasMoreElements()) {

            String key = (String) names.nextElement();
            System.out.println("Key = " + key + " ,Value = "+ hashtable.get(key));
        }

        Hashtable<Integer, String> ht = new Hashtable<Integer, String>();

        ht.put(10, "Chaitanya");
        ht.put(1, "Ajeet");
        ht.put(11, "Test");
        ht.put(9, "Demo");
        ht.put(3, "Anuj");

        // Get a set of the entries
        Set set = ht.entrySet();

        // Get an iterator
        Iterator i = set.iterator();

        // Display elements
        while(i.hasNext()) {

            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + " "+ me.getValue() );
        }

        System.out.println("\n\n");

        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<Integer, String>();

        lhm.put(10, "Chaitanya");
        lhm.put(1, "Ajeet");
        lhm.put(11, "Test");
        lhm.put(9, "Demo");
        lhm.put(3, "Anuj");


        // Get a set of the entries
        Set set1 = lhm.entrySet();

        // Get an iterator
        Iterator i1 = set1.iterator();

        // Display elements
        while(i1.hasNext()){

            Map.Entry me = (Map.Entry)i1.next();
            System.out.print( me.getKey() + " " + me.getValue());
        }


        System.out.println("\n\n");
        // using of TreeMap
        // TreeMap does the sorting based on the keys 

        Map<Integer, String> tm = new TreeMap<Integer, String>();

        tm.put(10, "Chaitanya");
        tm.put(1, "Ajeet");
        tm.put(11, "Test");
        tm.put(9, "Demo");
        tm.put(3, "Anuj");


        // Get a set of the entries
        Set set2 = tm.entrySet();

        // Get an iterator
        Iterator i2 = set2.iterator();

        while(i2.hasNext()){

            Map.Entry me = (Map.Entry)i2.next();
            System.out.print( me.getKey() + " "+ me.getValue() );
        }

        // Creating a Hashtable instance
        Hashtable<String, String> htb = new Hashtable<String, String>();

        // Adding key-value pairs to Hashtable
        htb.put("A", "Apple");
        htb.put("B", "Orange");
        htb.put("C", "Mango");
        htb.put("D", "Banana");
        htb.put("E", "Grapes");

        /*
            Checking Key existence using containsKey() method.
            boolean containsKey(Object key) method returns true if the 
            Hashtable contains the key, otherwise it returns false.
        */

        boolean keyFlag1 = htb.containsKey("A");
        System.out.println("Key A exists in Hashtable?: " + keyFlag1);

        Object ob = htb.remove("D");

        // the value "Banana" will be removed 
        System.out.println("The removed value is = "+ ob);

        // calling the toString() method of the hash table 
        System.out.println(htb);
        System.out.println("size of the hash table = " + htb.size() );

        // remove all the elements 
        htb.clear();
        System.out.println(htb);    
    }
    /*END OF SOLUTION: design a program to
    learn about hash table data-structure*/









    /*question: learn about the
    queue data-structure*/
    public static void learnQueue(){


        /*1. queue.poll() removes the element from the queue, 
        if there is no element it returns NULL 

        2. queue.remove() do the same but throws NoSuchElementException 
        exception in case there is no element

        3. queue.peek() only copy the value and if there is no elelemnt, provides NULL

        4. queue.element() only copy the value and if there is no elelemnt, 
        throws NoSuchElementException exception  

        5. queue.offer() - returns a special value (either null or 
        false, depending on the operation)

        6. queue.add() - throws an exception if the operation fails */

        Queue<Integer> queue = new LinkedList<Integer>();

        int [] arr = {12, 2, 34, 9, 56, 11};

        for ( int j = 0; j < arr.length; j++){
            queue.add(arr[j]);
        }

        System.out.println("Is Queue empty : " + queue.isEmpty());

        // calls the toString() method of the ll 
        System.out.println("Elements of queue = " + queue);
        System.out.println("Size of Queue : " + queue.size());

        Object obj = queue.element();

        System.out.println("Element at head position = " + obj);
        Iterator<Integer> it = queue.iterator();
        
        while ( it.hasNext() ) {            
            System.out.println( it.next() );
        }        
    }
    /*END of solution: learn about 
    the queue data-structure*/





    /*question: design an algorithm to get 
    3 Strings of maximum frequencies

    =====================================
    List<String> lis = new ArrayList<>();

    lis.add("a");
    lis.add("f");
    lis.add("e");
    lis.add("c");
    lis.add("c");
    lis.add("b");
    lis.add("b");
    lis.add("x");
    lis.add("u");
    lis.add("v");
    lis.add("u");
    lis.add("u");
    lis.add("e");
    lis.add("b");
    lis.add("e");
    lis.add("f");
    lis.add("v");
    lis.add("u");
    lis.add("f");

    lis.add("a");
    lis.add("a");
    lis.add("a");
    lis.add("t");
    lis.add("t");

    lis.add("g");
    lis.add("g");
    lis.add("g");
    lis.add("h");
    lis.add("h");

    List<String> list = maxThreeFrequencies(lis);
    ===================================== */

    public static List<String> maxThreeFrequencies(List<String> lis) {
        
        if(lis ==  null || lis.size() == 0) {
            return null;
        }
            
        Map<String, Integer> map = new HashMap<>();

        List<Integer> ls = new ArrayList<>();
        List<String> result = new ArrayList<>();

        // Map<Integer, String> mas = new HashMap<>();
        // List<Integer> les = new ArrayList<>(mas.keySet());

        for (String li: lis){
            map.put(li, map.containsKey(li) ? map.get(li) + 1 : 1);
        }

        for(Map.Entry<String, Integer> entry: map.entrySet()){
        
            if(!ls.contains(entry.getValue())){
                ls.add(entry.getValue());
            }                
        }

        Collections.sort(ls);
        
        for(int j = ls.size()-1; j >=0; j-- ) {

            System.out.print( "\t" + ls.get(j));

            for(Map.Entry<String, Integer> entry : map.entrySet()){

                if(entry.getValue() == ls.get(j)){

                    System.out.print(" " + entry.getKey());

                    if(result.size() < 3){
                        result.add(entry.getKey());
                    }                        

                    if(result.size() == 3){

                        System.out.println();
                        return result;
                    }
                }
            }

            System.out.println();
        }
        
        return result; 
    }
    /*END of solution: design an algorithm to get 
    3 Strings of maximum frequencies*/








    /*question: design an algorithm to 
    swap the keys and values of Map*/

    // if the keys have sames values, after swaping the 
    // values which will be converted to keys will be overlapped 

    /*solution-a*/
    public static <K,V> HashMap<V,K> reverseMap(Map<K,V> map) {

        Map<V,K> rev = new HashMap<V, K>();

        for(Map.Entry<K,V> entry : map.entrySet()) {
            rev.put(entry.getValue(), entry.getKey());
        }

        return rev;
    }
    /*END of solution-a*/



    /*solutuon-b*/
    public static <K,V> HashMap<V,K> reverseMap(Map<K,V> map) {

        Map<Integer, String> rev = map.entrySet()
                                      .stream()
                                      .collect(Collectors.toMap(Map.Entry::getValue, 
                                                                Map.Entry::getKey));
        return rev; 
    }
    /*END of solution-b*/
    
    /*END of solution: design an algorithm to 
    swap the keys and values of Map*/




	/*question: show 2 different ways 
    of array initialization*/
	public static void initializeStringArr(){

        String[] strArray; 
        strArray = new String[]{ "Under the bridge", 
                                "You will find me", 
                                "Or someone else"
                                };

        String[] arr = {   "Under the bridge", 
                            "You will find me", 
                            "Or someone else"
                        };
    }
    /*END of solution: show 2 different 
    ways of array initialization*/

             
    
	






	/*question : design an algorithm to 
    find the K-th smallest element of an 
    unsorted array of n elements*/

	/*solution - a:,using bubble sort*/
	public static int kTheSmallestElement(int[] arr, int k ){

		int N = arr.length; 
		int kTh = -1;

		if ( k > N || k < 0 ){
			return kTh;
		}
			
		out:
		for (int i = 0; i < N - 1 ; i++ ){

			for (int j = i+1; j < N; j++ ){

                // bring the i-th smallest element in the i-index 
				if ( arr[i] > arr[j]){
					
					arr[i] = arr[i]+ arr[j];
					arr[j] = arr[i] - arr[j];
					arr[i] = arr[i] - arr[j];					
				}
			}

			if (i == k-1 ){

				kTh = arr[i];
				break out; 
			}
		}

		System.out.println( Arrays.toString(arr) );
		return kTh; 
	}
	/*END of solution - a*/






	/*solution-b : using quick sort, assumption: 
    all array elements are distinct*/ 
    public static int kthSmallest(int arr[], int l, int r, int k){

        // If k is smaller than number of elements in array
        if (k > 0 && k <= (r - l + 1) ){

            int pos = partition(arr, l, r);
     
            if (pos-l == k-1){
                return arr[pos];
            }

            if (pos-l > k-1) {
                return kthSmallest(arr, l, pos-1, k);
            }     
     
            // the number of elements from l to pos = pos-l+1
            // so, k-th element will be = k - (pos-l+1) = (k -pos +l-1)
            return kthSmallest(arr, pos+1, r, (k-pos+l-1));
        }
     
        // If k is more than number of elements in array
        return Integer.MAX_VALUE;
    }



    // i-th element is in the i-th postion 
    // and smaller elements are left of i-th elem (but, not sorted out)
    // and bigger elements are right of i-th elem (but, not sorted out)
    public static int partition(int[] arr, int l, int r) {

        int x = arr[r];
        int i = l;

        for (int j = l; j < r ; j++){

            if (arr[j] <= x){
                arr[i] = swap(arr[j], arr[j] = arr[i]);
                i++;
            }
        }

        // swap arr[i] and arr[r]
        arr[i] = swap(arr[r], arr[r] = arr[i]);
        return i;
    }

    public static int swap(int itself, int dummy){
        return itself;
    }


    // another way of doing partition 
    public static int myPartition(int arr[], int left, int right){

        int i = left, j = right;
        int tmp;

        Random rand = new Random();

        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = arr[pivotIndex]; 

        /*
        * the nextInt(int n) method is used to get a pseudorandom, uniformly 
        * distributed int value between 0 (inclusive) and the specified value 
        * (exclusive)
        */
        while(i <= j){

            while ( arr[i] < pivot ) {
                i++;
            }

            while (arr[j] > pivot){
                 j--;
            }

            /*
            * array can be all sorted and the pivot 
            * is the last element of the array
            */
            if (i <= j) {

                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;

                  i++;
                  j--;
            }
        }

        return i;
    }    
	/*END of solution -b*/


    /*solution-c*/
    /*provide the solution using min heap,
    Geeks for geeks link: <http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/>*/
    /*END of solution-c*/



    /*solution-d*/
    /*provide the solution using max heap,
    Geeks for geeks link: <http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/>*/
    /*END OF SOLUTION-d*/


	/*END OF SOLUTION: design an algorithm 
    to find the K-th smallest element of 
    an unsorted array of n elements*/



    /*
    * Fisher–Yates shuffle
    * --------------------
    * The nextInt(int n) method is used to get a pseudorandom, uniformly 
    * distributed int value between 0 (inclusive) and the specified value 
    * (exclusive), drawn from this random number generator's sequence.
    */
    public static void shuffleArray(int[] arr){

        Random random = new Random();

        int N = arr.length;

        for (int i = N - 1; i > 0; i--){

            // take an int in the range of [0, 1, 2,...., i]
            int index = random.nextInt(i + 1);

            int a = arr[index];
            arr[index] = ar[i];
            arr[i] = a;
        }
    }








    /*question: convert an int[](primitive 
    array) to Integer[] (class array)*/
    public static void intToInteger(int[] arr) {


        Integer[] newArray = new Integer[arr.length];
        int i = 0;

        for (int value : arr){

            // newArray[i++] = value; is also fine
            // primitive "int" is automatically autoboxed to Integer 
            // during the time of assigning  
            newArray[i++] = Integer.valueOf(value);
        }

        int[] data = {1,2,3,4,5,6,7,8,9,10};


        // convert primitive array to Class array 
        Integer[] what = Arrays.stream(data).boxed().toArray( Integer[]::new );
        Integer[] ever = IntStream.of(data).boxed().toArray( Integer[]::new );

        // get an array of random elements // limit =  number of elements 
        // int[] array = new Random().ints(limit, low, high).toArray();

        // int[] to List<Integer>
        List<Integer> you  = Arrays.stream(data).boxed().collect(Collectors.toList());
        List<Integer> like = IntStream.of( data ).boxed().collect( Collectors.toList());

        // convert List<Integer> to int[] unboxing
        int[] unboxed = you.stream().mapToInt(i->i).toArray();

        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        // List<String> myList = Arrays.asList(new String[]{"a1", "a2", "b1", "c2", "c1"});

        myList.stream().filter(s -> s.startsWith("c"))
                                .map(String::toUpperCase)
                                .sorted()
                                .forEach(System.out::println);                                                    
    }   
    /*END of solution: convert an int[]
    (primitive array) to Integer[] (class
    array)*/







    /*question: convert an array to set*/
    public static void arrayToSet(int[] arr){

        // using Class arr Integer[]
        Integer[] newArray = new Integer[arr.length];
        int i = 0;

        for (int value : arr){

            newArray[i++] = Integer.valueOf(value);
            // newArray[i++] = value; // alos, correct
        }

        // Arrays.asList() requires class/obj refenreces, 
        // it doesn't work for the primitive array
        // 1. 
        Set<Integer> set = new HashSet<>(Arrays.asList(newArray));


        // using Collections
        // 2. 
        Set<Integer> set2 = new HashSet<Integer>();

        // also, only work with the class objects
        Collections.addAll(set2, newArray);

        // we can use primitve array with set by iteration 
        // 3. 
        Set<Integer> set1 = new HashSet<>();

        // this works for both primitives and class objects
        for (int v : arr) {
            set1.add(v);
        }
    }
    /*END of question: convert array to set*/






	/*question : design an algorithm to find the 
    number of tuples inside an array whose sum 
    is euqal or less than certain value*/

    /* int [] arr = { 3, 3, -1, 4, 2,5, 5, 1};
    int k = 8;
    15 tuples whose sum is euqual or less than 8, 
    {{-1,1},  {-1,2} ,  {3,-1}, {-1,4 }, {-1,5}, 
    {2,1},{3,1 },{4,1}, { 5,1},
    {3,2 },{4,2}, {2,5 }, 
    {3,4 } ,  {3,5 },   
    {3,3} }

    1 tuples whose sum is euqual to 8, 
    { {3,5 } }*/ 


    /* solution-a, better version*/ 
    public static int numberOfPairs(int[] arr, int sum) {


        /*1. check not to use the same values of i in the array
         2. check not to use  the same valus of j in the array for the same i*/
        List<Integer> li = new ArrayList<Integer>();
        List<Integer> lj;

        int count = 0; 

        for ( int i = 0; i < arr.length -1 ; i++){

            if (li.contains(arr[i]) ){
                continue; 
            }

            lj =  new ArrayList<Integer>();

            for (int j = i+1; j < arr.length; j++){

                if (lj.contains(arr[j])){
                    continue;
                }

                // if ( arr[i]+ arr[j] == sum){
                if ( arr[i]+ arr[j] <= sum){

                    count++;
                    lj.add(arr[j]);
                }
            }

            li.add(arr[i]);
        }

        return count;
    }
    /*END of solution-a*/







    /*solution-b*/
    public static int numberOfPairs(int[] a, int k ) {

        int len = a.length;
        int counter = 0;

        boolean isCheckedLeft = false;
        boolean isCheckedRight = false;

        int i, j, h;

        // I think we won't need sorting here 
        Arrays.sort(a);

        // 1. important for loop
        for (i = 0; i < len; i++){

            // 1st part for the dups (iteration of i) 
            isCheckedLeft = false;

            // check every elements before i for the dups 
            // this ensure that same i is not being used  
            for (j = i - 1; j >= 0; j--){

                if (a[i] == a[j]) {
                    isCheckedLeft = true;
                    break;
                }
            }

            // find the same element in the lower index,
            // so, continue 
            if (isCheckedLeft) {
                continue;
            }

            // 2. important for loop 
            for (j = i+1; j < len; j++){

                // 2nd part for the dups (iteration of j)
                isCheckedRight = false;

                // search between i and j ( i< h < j)
                // this ensure same j won't be used for certain i 
                for (h = j - 1; h > i; h--){

                    if (a[j] == a[h]) {

                        isCheckedRight = true;
                        break;
                    }
                }

                if (isCheckedRight) {
                    continue;
                }
                //  END of 2nd part for the dups (iteration of j)


                // System.out.print("("+a[i]+", "+a[j]+") ");
                if (a[i] + a[j] <= k) {

                    counter++;
                }
            }


        }

        return counter;
    }
    /*END of solution-b*/







    /*solution - c*/
    public static int numberOfPairs2(int[] arr, int sum ){


        // new HashSet<>( needs list here )
        Integer[] arr1 = Arrays.stream(arr).boxed().toArray(Integer[]:: new)));

        List<Integer> lis = new ArrayList<Integer>(new HashSet<>(Arrays.asList( arr1));

        int N = lis.size();
        int count = 0;

        // this solution is not correct as if the dups 
        // themselves will satisfy the condition
        for (int i = 0; i < N - 1; i++ ){

            for (int j = i+1; j < N; ) {

                if ( lis.get(i) + lis.get(j++) <= sum )
                    count++;
            }
        }

        /*now, we will need to find how many repeated 
        numbers are there, if they are satisfying the 
        relation themselves*/

        System.out.println(count);
        return count;
    }
    /*END of solution-c*/






    /*solution-d*/
    public static int numberOfPairs3(int[] array, int sum){

    	Set<Integer> set = new HashSet<Integer>();

	    // this set will keep track of the unique pairs.
	    Set<String> uniquePairs = new HashSet<String>(); 

        for (int i : array) {
            set.add(i);
        }

        // for (int i : array) {

        //     int x = sum - i;

        //     // for <= condition, we will need to iterate 
        //     // over the set for the elements those are equal 
        //     // or less than to satisfy the condition

        //     if(set.contains(x)){

        //         int[] y = new int[]{x,i};
        //         Arrays.sort(y);
        //         uniquePairs.add( Arrays.toString(y) );
        //     }
        // }


        for (int i : array) {

            for(int x : set){

                if(x + i =< sum){

                    int[] y = new int[]{x,i};

                    Arrays.sort(y);
                    uniquePairs.add( Arrays.toString(y) );
                }                
            }
        }

        /*for taking further this solution for 
        equal or less than a given number,
        1. we need to know if the set contains 
        an element whose value is equal or less 
        than certain value.
        2. we will also need to find the count 
        of repeated numbers and if they satisfy 
        the relation individually*/

        System.out.println(uniquePairs.size());     	
    }
    /*END of solution-d*/


    


    /*solution-e*/
    // doesn't work 
    public static int numberOfPairs1(int[] a, int k ){

        int N = a.length;
        
        if (N == 0) {
            return -1;
        }
        
        Arrays.sort(a);
        
        int count  = 0;

        int left = 0; 
        int right = N -1; 


        while(left < right){

            if (a[left] + a[right] <= k){

                // System.out.println(a[left] + "\t" + a[right]);
                count++; 

                /*may be the same numbers will also satisfy the relation*/
                if ( a[left] + a[left+1] <= k) {

                    count ++;
                    // System.out.println(a[left] + "\t"+a[left+1]);
                }

                /*now, use iteration to traverse the same elements*/
                while (a[left] == a[left+1] && left < len-1 ){
                   left++;
                }

                /*may be the same numbers will also satisfy the relation*/
                // System.out.println(a[right] +"\t"+ a[right-1]);
                if (a[right] + a[right-1] <= k) {
                    count ++;
                }

                /*now, use iteration to traverse 
                the same elements*/
                while (a[right] == a[right-1] && right >1){
                  right-- ; 
                }
            }

            // traverse through the array
            left++;
            right--;
        }

        return count; 
    }
    /*END of solution-e*/





    /*END of solution-f*/
    // imports are: java.utils.stream.Stream  
    //              java.utils.stream.IntStream 
    public static int numberOfPairs1(int[] values, int maxVal) {

        int N = values.length;

        return IntStream.range(0, N)
            .flatMap(i1 -> IntStream.range(i1 + 1, N)
            .map(i2 -> Arrays.asList(values[i1], values[i2])))
            .distinct()
            .filter(l -> l.stream().sum() <= maxVal)
            .count();
    }
    /*END of solution-f*/


	/*END of solution: design an algorithm 
    to find the number of tuples inside an 
    array whose sum is euqal or less than 
    certain value*/




    /*
    * find the maximum repeated number for the square root for 
    * the numbers between (incl.) provided range 
    */

    final int[] table = {

            0, 16, 22, 27, 32, 35, 39, 42, 45, 48, 50, 53, 55, 57,
            59, 61, 64, 65, 67, 69, 71, 73, 75, 76, 78, 80, 81, 83,
            84, 86, 87, 89, 90, 91, 93, 94, 96, 97, 98, 99, 101, 102,
            103, 104, 106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 117, 118,
            119, 120, 121, 122, 123, 124, 125, 126, 128, 128, 129, 130, 131, 132,
            133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 144, 145,
            146, 147, 148, 149, 150, 150, 151, 152, 153, 154, 155, 155, 156, 157,
            158, 159, 160, 160, 161, 162, 163, 163, 164, 165, 166, 167, 167, 168,
            169, 170, 170, 171, 172, 173, 173, 174, 175, 176, 176, 177, 178, 178,
            179, 180, 181, 181, 182, 183, 183, 184, 185, 185, 186, 187, 187, 188,
            189, 189, 190, 191, 192, 192, 193, 193, 194, 195, 195, 196, 197, 197,
            198, 199, 199, 200, 201, 201, 202, 203, 203, 204, 204, 205, 206, 206,
            207, 208, 208, 209, 209, 210, 211, 211, 212, 212, 213, 214, 214, 215,
            215, 216, 217, 217, 218, 218, 219, 219, 220, 221, 221, 222, 222, 223,
            224, 224, 225, 225, 226, 226, 227, 227, 228, 229, 229, 230, 230, 231,
            231, 232, 232, 233, 234, 234, 235, 235, 236, 236, 237, 237, 238, 238,
            239, 240, 240, 241, 241, 242, 242, 243, 243, 244, 244, 245, 245, 246,
            246, 247, 247, 248, 248, 249, 249, 250, 250, 251, 251, 252, 252, 253,
            253, 254, 254, 255
    };


    public int sqrt(int x) {

        int xn;

        if (x >= 0x10000) {
            if (x >= 0x1000000) {
                if (x >= 0x10000000) {
                    if (x >= 0x40000000) {
                        xn = table[x >> 24] << 8;
                    } else {
                        xn = table[x >> 22] << 7;
                    }
                } else {
                    if (x >= 0x4000000) {
                        xn = table[x >> 20] << 6;
                    } else {
                        xn = table[x >> 18] << 5;
                    }
                }

                xn = (xn + 1 + (x / xn)) >> 1;
                xn = (xn + 1 + (x / xn)) >> 1;
                return ((xn * xn) > x) ? --xn : xn;
            } else {
                if (x >= 0x100000) {
                    if (x >= 0x400000) {
                        xn = table[x >> 16] << 4;
                    } else {
                        xn = table[x >> 14] << 3;
                    }
                } else {
                    if (x >= 0x40000) {
                        xn = table[x >> 12] << 2;
                    } else {
                        xn = table[x >> 10] << 1;
                    }
                }

                xn = (xn + 1 + (x / xn)) >> 1;

                return ((xn * xn) > x) ? --xn : xn;
            }
        } else {
            if (x >= 0x100) {
                if (x >= 0x1000) {
                    if (x >= 0x4000) {
                        xn = (table[x >> 8]) + 1;
                    } else {
                        xn = (table[x >> 6] >> 1) + 1;
                    }
                } else {
                    if (x >= 0x400) {
                        xn = (table[x >> 4] >> 2) + 1;
                    } else {
                        xn = (table[x >> 2] >> 3) + 1;
                    }
                }

                return ((xn * xn) > x) ? --xn : xn;
            } else {
                if (x >= 0) {
                    return table[x] >> 4;
                }
            }
        }

        return -1;
    }


    long goodMask; // 0xC840C04048404040 computed below

    {
        for (int i = 0; i < 64; ++i) goodMask |= Long.MIN_VALUE >>> (i * i);
    }

    public boolean isSquare(long x) {

        /*
        *
        * This tests if the 6 least significant bits are right. Moving the to
        * be tested bit to the highest position saves us masking.
        * */
        if (goodMask << x >= 0) return false;
        final int numberOfTrailingZeros = Long.numberOfTrailingZeros(x);


        /*
        * Each square ends with an even number of zeros.
        * */
        if ((numberOfTrailingZeros & 1) != 0) return false;
        x >>= numberOfTrailingZeros;

        /*
        * Now x is either 0 or odd. In binary each odd square ends with 001.
        * Postpone the sign maxRepeatedNumber until now; handle zero in the branch.
        * */
        if ((x & 7) != 1 | x <= 0) return x == 0;


        /*
        *
        * Do it in the classical way. The correctness is not trivial as the conversion
        * from long to double is lossy!
        * */
        final long tst = (long) Math.sqrt(x);
        return tst * tst == x;
    }




    public int maxRepeatedNumber(int N, int count) {

        if (!isSquare(N)) {
            return count;
        }

        // the int has the sqrt, so, find the value
        else {

            count += 1;

            int root = sqrt(N);

            return maxRepeatedNumber(root, count);
        }
    }


    public int solution(int A, int B) {

        int count = 0;
        int temp = -1;

        for (int i = A; i <= B; i++) {

            if (!isSquare(i)) {
                continue;
            }

            temp = maxRepeatedNumber(i, 0);

            if (temp > count) {
                count = temp;
            }
        }

        return count;
    }
    /*
    * END of question: find the maximum repeated number for the square 
    root for the numbers between (incl.) provided range 
    */


    /*question: design a program to find the 
    second to last number of a digit*/
    public static int secondDigit( int number ){

        if( (number/10) == 0 ) return -1;

        // number%100 is 2 -digit number 
        return ( (number % 100)/10 );
    }
    /*END of solution: design a program to find 
    the second to last number of a digit*/









    /*question: desing an algorithm to find 
    the nth fibonacci number given n*/

    // print the fibonacci series till nth value
    /*solution -a, using recursion*/
    // time complexity O(n)
    public static int fibonacci(int n ){
        
        if ( n == 0) {
            return 0;
        }

        else if (n == 1) {
            return 1;            
        }

        else {
            return fibonacci(n-1) + fibonacci(n-2); 
        }        
    }
    /*END of solution a*/






    /*solution-b, using iteration*/
    public static int fibonacci1(int n ){

		int first = 0;
        int second  = 1;
		
        if (n < 0 ) {
            return -1; 
        }

		for (int j = 0; j < n; j++){

            second =  first + second;
            first = second - first;
		}

		return first; 
	}
    /*END of soluton-b*/





    /*solution-c*/
    // store values of already computed f(n) values inside an 
    // array and use those values for avoiding unnecsssary computing.
    public static int fibonacci2(int n) {

        if(n <=  0) {
            return 0;
        }

        if(n == 1) {
            return 1;
        }

        int[] arr = new int[n+1];

        // this is faster than using Array
        // List<Integer> lis = new ArrayList<>(Collections.nCopies(n+1, 0));

        // arr[0] = 0;
        arr[1] = 1; 

        return fiboHelper(n, arr);
    }



    public static int fiboHelper(int n, int[] arr){

        if(n <= 0) {
            return arr[0];
        }

        else if(n == 1) {
            return arr[1];
        }
            
        else {

            if( arr[n-1] != 0 && (arr[n-2] != 0 || (arr[n-2] == 0 && n-2 == 0))){
                return arr[n] = arr[n-1] + arr[n-2]; 
            }
                            
            else if (arr[n-1] == 0 && arr[n-2] != 0 ){
                return arr[n] = fiboHelper(n-1, arr) + arr[n-2]; 
            }
                
            else {
                return  arr[n] = fiboHelper(n-2, arr) + fiboHelper(n-1, arr );            
            }
        }             
    }
    /*END of solution-c*/

    /*END of solution: desing an algorithm 
    to find the nth fibonacci number given n*/





    /*question: write syntaxes 
    for bitwise operation*/
    public static void bitWiseOperation(){

    	/* 

            Bitwise and Bit Shift Operators
            -------------------------------

    		~       Unary bitwise complement

    		<<      Signed left shift
            <<<     Unsigned left shift

    		>>      Signed right shift
    		>>>     Unsigned right shift
            		
            &       Bitwise AND
    		^       Bitwise exclusive OR namely, XOR
    		|       Bitwise inclusive OR

        */


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






    /*question: desing an algorithm 
    to swap swap 2 numbers*/

    /*solution-*/
    public static void swapTwoNumbers(int a , int b ){

        a = a^b;
        b = a^b;
        a = a^b;
    }
    /*END of solution-a*/


    /*solution-b*/
     public static int swapTwoNumbers1(int m, int n ){
        return m;
    }
    //  make the call as below 
    // a = swapTwoNumbers1(b, b=a )
    /*END of solution-b*/


    /* END of solution: swap 2 numbers 
    using bitwise operation */






	/*questions: write the syntaxes for 
    conversion between list and array*/
    /*solution-a*/
	public static void listToArray_1(){

		List<Integer> lis = new ArrayList<Integer>();
	    int [] a = {12,4,56,78};

	    for (int j = 0; j< a.length; j++ ) {
	       lis.add(a[j]);
	    }

        // 1. Arrays.asList(ar) 2. lis.toArray() only for the class objects 

        // int[] b = lis.toArray( new int[ lis.size() ]); DOESN'T WORK 
        // Integer [] b = lis.toArray(new Integer[4] );   DOES WORK as Integer is the Class objects  

	    int[] b = lis.stream().mapToInt(i->i).toArray();
        // int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        // int[] arr = list.stream().filter(i -> i != null).mapToInt(i -> i).toArray();
        //  Integer [] ar = lis.toArray(new Integer[lis.size()]);

	    System.out.println(Arrays.toString(b));

        Integer[] mrr = {12,34,5,6,7};

        // boxing 
        int m =121;

        // auto-conversion for int to Integer
        Integer n = m;

        System.out.println(Arrays.stream(mrr).anyMatch(n::equals));

        // un-boxing, auto-conversion 
        Integer p = 123;
        int q = p;
	}
    /*END of solution-a*/



    /*solution-b*/
    public static int[] listToArray_2(List<Integer> lis){

        int[] A = new int[lis.size()];

        for (int i = 0; i < A.length; i++){

            A[i] = lis.get(i).intValue();
            // A[i] = lis.get(i); // also works 
        }

        return A;
    }
    /*END of solution-b*/



    /*solution-c*/
    public static int[] listToArray_3(List<Integer> lis){

        int[] ret = new int[lis.size()];
        Iterator<Integer> it = lis.iterator();
        
        for (int i = 0; i < ret.length; i++){
            ret[i] = it.next().intValue();
        }

        return ret;
    }
    /*END of solution-c*/




    /*solution-d*/
    private int[] listToArray_4(List<Integer> integers) {

        int i = 0;
        int[] ints = new int[integers.size()];

        for (int n : integers) {
            ints[i++] = n;
        }

        return ints;
    }
    /*END of solution-d*/

    /*END of solution: write the syntaxes for 
    conversion between list and array*/






	/*question: design an algorithm to check if 
    the brace Characters are in correct sequence 
    in the  string*/
    public static String[] braces(String[] values){

        int index = 0;
        String[] results = new String[values.length];

        for (String value: values) {
            
            results[index] = checkBraces(value) ? "YES": "NO";
            index++;
        }

        return results;
    }

    
	public static boolean checkBraces(String str){

	    char ch; 
	    Stack<Character> stack = new Stack<Character>();

	    // String str = "({({()})})";

	    Character[] open = {'(', '[', '{'};
	    Character [] close = {')', ']', '}'};

	    List<Character> o_lis = Arrays.asList(open);
	    List<Character> c_lis = Arrays.asList(close);

	    if (str ==  null) {
            return false; 
        }

	    if (str.length() == 0) {
            return true; 
        }


     	for (int j = 0; j < str.length(); j++){

	        ch = str.charAt(j);
	        // System.out.print(ch+ " ");

	        // 1. 
	        if ( o_lis.contains(ch) ){
	             stack.push(ch);
	        }

	        // 2. 
	        else if ( c_lis.contains(ch) ){

	          if (!stack.isEmpty()){

	              char test = stack.pop(); 

	              if ( ch == ')' && test != '(' ){
	                 return false; 
	              }

	              else if ( ch == '}' && test != '{' ){ 
	                  return false;
	              }

	              else if ( ch == ']' && test != '[' ){	               
                    return false;
	              }

                  // else{
                  //   return true;
                  // }  
	          }
	          
	          // if the String starts with the closing brace, return false 
	          else {
                return false;
              }
                 
	        }

	        else 
                return false; 
	        
	      }// end of for 


	    return stack.isEmpty();
	}
	/*END of solution:design an algorithm 
    to check if the brace Characters are 
    in correct sequence in the  string*/



    /*
    * solution - b
    */
    public static boolean checkBraces1(String str) {

        if (str == null) {
            return false;
        }

        if (str.isEmpty() == true) {
            return true;
        }

        int count = 0;
        int index = 0;

        int N = str.length();

        char[] ch = str.toCharArray();

        String start = str.substring(0, 1);

        // ({({()})})
        while (count >= 0 && index <= N - 1) {

            String s = str.substring(index, index + 1);
            index++;

            if (s.equals("(")) {
                count = count + 1;
            } else if (s.equals("{")) {
                count = count + 2;
            } else if (s.equals("[")) {
                count = count + 3;
            } else if (s.equals(")")) {
                count = count - 1;
            } else if (s.equals("}")) {
                count = count - 2;
            } else if (s.equals("]")) {
                count = count - 3;
            } else {

                // we get a diff. animal
                return false;
            }
        }

        return count == 0;
    }
    /*
    * END of solution - b
    */




    /*question: find the position of matching 
    parentheses or braces in a given piece of 
    text*/

    /*
    * solution-a
    */

    // String str = "({({()})})";
    public static int findIndexOfClosingBrace(String s, int openPos) {


        char[] text = s.toCharArray();

        int index = openPos;
        int counter = 1;

        // "[123[abc]]an[125[bc]]"
        while (counter > 0) {

            // check if the index is not exceed the String 

            char c = text[++index];
            
            if (c == '[') {
                counter++;
            } 

            else if (c == ']') {
                counter--;
            }
        }

        return closePos;
    }
    // END of solution




    /*
    * solution-b
    */
    public static int findIndexOfClosingBrace(String s, int openPos) {


        // "[123[abc]]an[125[bc]]"
        char[] text = s.toCharArray();

        int closePos = openPos + 1;
        int counter = 1;

        //"[123[abc]]an[125[bc]]"
        while (counter > 0) {

            char c = text[closePos];

            // opening brace
            if (c == '[') {
                counter++;
            }

            // closing brace
            else if (c == ']') {
                counter--;
            }

            if(counter > 0){
                closePos++;
            }
        }

        return closePos;
    }



    /*
    * Solution - c
    */
    public static int findIndexOfClosingBrace(String str, int openIndex) {


        // "[123[abc]]an[125[bc]]"
        Stack<Character> stack = new Stack<Character>();

        if (str == null || str.length() == 0) {
            return -1;
        }

        Character[] open = {'(', '[', '{'};
        Character[] close = {')', ']', '}'};

        List<Character> openingBracesList = Arrays.asList(open);
        List<Character> closingBracesList = Arrays.asList(close);

        char providedOpeningBrace = str.charAt(openIndex);

        if (!openingBracesList.contains(providedOpeningBrace)) {
            return -1;
        }

        for (int i = openIndex + 1; i < str.length(); i++) {

            char current = str.charAt(i);

            if (openingBracesList.contains(current)) {
                stack.push(current);
            } 

            else if (closingBracesList.contains(current)) {

                if (!stack.isEmpty()) {

                    char opBrace = stack.peek();

                    if (braceMatching(opBrace, current)) {
                        stack.pop();
                    }
                } 

                else if (braceMatching(providedOpeningBrace, current)) {
                    return i;
                }
            }
        }

        return -1;
    }
   

    public static boolean braceMatching(char c1, char c2) {
        
        if (c1 == '(' && c2 == ')' || c1 == '{' && c2 == '}'
                || c1 == '[' && c2 == ']') {
            return true;
        }
        return false;
    }
    // END of solution 

    


	/*question : design an algorithm to check 
    if a String is valid shuffle of two String*/

	/* given 3 strings: first,  second, and  third.  third String is said to be a 
	shuffle of first and second if it can be formed by interleaving the characters 
	of first and second String in a way that maintains the left to right ordering 
	of the characters from each string. 

	For example, given first = "abc" and second = "def",  third = "dabecf"  is a 
	valid shuffle since it preserves the character ordering of the two strings.*/

	/*END solution : design an algorithm to check 
    if a String is valid shuffle of two String*/











	/*question : design an algorithm to remove 
    a given characters from String*/

	/*You need to provide both iterative and recursive solution of this method 
	and also has to write JUnit tests to cover cases like null and empty String,
	input which only contains letter to be removed, String which doesn't contain 
	given character etc.*/

	/* END of solution: design an algorithm to 
    remove a given characters from String*/







	/*question : design an algorithm to find 
    longest palindrome in a string array*/

	/*1. Make a HashMap with all the palindrome with their length
	2. Get the longest length of palindrome.
	3. Check if there is any other palindrome with the same length
	4. If there is, then return all.
	5. If not, then return the longest palindrome.*/

	/* END solution : find longest palindrome in a string */







	/*question : design an algorithm to sort 
    strings on their length from a given array*/
		/*1. make HashMap with string and their length as integer. 
		2.Sort the HashMap based on the length int values.*/
	/* END : sort String on their length */






	/*question: design an algorithm to find 
    highest occurred character in a String*/

	/*solution-a*/
    public static void findMaxChar(String str){

        int N = str.length();
        
        int count = 1;
        int maxCount = 1;

        char maxChar = '\0';

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        /* it's also possible to do this solution using
        Set<Character> set = new LnkedHashSet<Character>();*/

        for(int i = 0; i < N-1; i++ ){

            char c = str.charAt(i);
            count = 1;

            for (int j = N - 1 ; j > i; j--){

                if ( c == str.charAt(j)) {
                    count++;
                }
            }

            /*count >= maxCount requires if there are more 
            than one high frequency Character exits*/
            if ( count > 1){

                // more than one maximum character 
                // of the current iteration 
                if( count ==  maxCount){
                    map.put(str.charAt(i), count);
                }

                else if ( count > maxCount){

                    maxCount = count;

                    // clear the previous entry 
                    map.clear();
                    map.put(str.charAt(i), count);
                }
            }
        }

        int index =0;

        for ( Map.Entry<Character, Integer> entry : map.entrySet()){
            System.out.println("index :" + (++index) 
                            +"\t"+entry.getKey()+ "\t"+ entry.getValue() );
        }
    }
    /*END of solution-a*/



    /* Bairesdev*/
    public static int MostPopularNumber(int[] arr, int N) {


        int count = 1;
        int maxCount = 1;

        Arrays.sort(arr);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();


        for (int i = 1; i < N; i++) {

            if (arr[i] == arr[i - 1]) {
                count++;
            } 

            else {

                if (count > 1) {

                    if (count == maxCount) {
                        map.put(arr[i], count);
                    } 

                    else if (count > maxCount) {

                        maxCount = count;
                        map.clear();
                        map.put(arr[i], count);
                    }

                    count = 1;
                }
            }
        }

        if (map.size() == 1) {
            return map.get(0);
        }

        int min = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            min = min > entry.getKey() ? entry.getKey() : min;
        }

        return min;
    }




	/*solution - b*/
	public static void findMaxChar3 (String str){

        char[] array = str.toLowerCase().toCharArray();

        int count = 1;
        int maxCount = 1;

        char maxChar = '0';

        Set<Character> set = new LinkedHashSet<Character>();

        for ( int i = 0; i < str.length() - 1; i++ ){  

            /*Character has the toString() methd, 
            char doesn't have the toString() method 

            // this works 
            Character ch = 'c';
            System.out.println( ch.toString());

            // DOESN'T work for the primitives 
            char ch = 'c'; 
            System.out.println( ch.toString());

            'c' is char or Character
            "c" is Stirng*/

            count = str.length() - str.replace(String.valueOf(array[i]) , "");

            if (count >1){

                if( count ==  maxCount){                    
                    set.add(str.charAt(i));
                }
                
                else if ( count > maxCount){

                    maxCount = count;

                    // clear the Set, we have a new maximum 
                    set.clear();
                    set.add(str.charAt(i));
                }
            }
        }

        for( Character c: set){
            System.out.println(c);
        }
    }
    /*END of solution-b*/


	/*END of solution: design an algorithm to 
    find  the highest occurance character*/






	/*question : design an algorithm to 
    count the vowels of a string*/
	public static void getVowels(){


		System.out.println("Please enter some text"); 
		Scanner reader = new Scanner(System.in); 


		String input = reader.nextLine(); 
		char[] letters = input.toCharArray(); 

		int count = 0; 

		for ( char c : letters ) { 
			
			switch (c) { 

				case 'a': 
				case 'e': 
				case 'i': 
				case 'o': 
				case 'u': 

					count++; 
				    break; 

				default: 
			} 
		} 

		System.out.println("Number of vowels in String [" + input + "] is : " + count);
	}
    /*END of solution: design an algorithm 
    to count the vowels of a string*/







	/* question: design an algorithm to check an input 
    * string is substring of another string. The solution 
    * is case sensitive*/
	public static boolean isSubstring(String fr, String sn, int f_len, int s_len ){

        // small string for testing substring
        if ( f_len == 0) {
            return true; 
        }

        // big string 
        if (s_len == 0) {
            return false; 
        }

        if ( fr.charAt( f_len -1 ) == sn.charAt(s_len -1 ) )
            return isSubstring(fr, sn, f_len -1, s_len -1 );


        return isSubstring(fr, sn, f_len, s_len -1 );
    }
    /*END of solution*/






    /*question: design an algorithm to find the 
    starting index of an input string which is 
    substring of another string. If not found, 
    return -1*/
    public static int beginningIndex(String fr, String sn){


        // fr is the small string here 
        // sn is the big string here 
        return indexHelper(fr, sn, fr.length(), sn.length());
    }

    public static int indexHelper(String fr, String sn, int f_len, int s_len ){

        // "ami" and "miami"
        if (f_len == 0){
            return s_len; 
        }

        if (s_len == 0){
            return -1; 
        }

        if ( fr.charAt( f_len -1 ) == sn.charAt(s_len -1 ) ){
            return indexHelper(fr, sn, f_len -1, s_len -1 );
        }

        return indexHelper(fr, sn, f_len, s_len -1 );
    }
    /*END of solution*/






    /*question: design an algorithm to 
    count the vowels of a string*/
	public static void  countVowelConstants() throws IOException {


		String str;

		int vowels = 0; 
        int digits = 0; 

        int blanks = 0;

		char ch;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter a String : ");
		str = br.readLine();

        // Using Scanner 
        // Scanner reader = new Scanner( System.in ); 
        // String input = reader.nextLine(); 

		for( int i = 0; i < str.length(); i++ ) {

			ch = str.charAt(i);

			if ( ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || 
			    ch == 'I' || ch == 'o' || ch == 'O' || ch == 'u' || ch == 'U'){
			
            	vowels ++;
            }

			else if ( Character.isDigit(ch) ){
				digits ++;
            }

			else if( Character.isWhitespace(ch) ){
				blanks ++;
            }
		}

		System.out.println("Vowels : " + vowels);
		System.out.println("Digits : " + digits);
		System.out.println("Blanks : " + blanks);
	}
	/*END of solution: design an algorithm to 
    count the vowels of a string*/






	/*question : design an algorithm to reverse 
    a string using recursion and iteration*/

    /*solution-a*/
	public static String reverse( String str){

        StringBuilder strBuilder = new StringBuilder();

        char[] strChars = str.toCharArray();

        for ( int i = (strChars.length - 1) ; i >= 0; i--){
            strBuilder.append(strChars[i]);
        }

        // reverse using StringBuilder
        // new StringBuilder("Miami").reverse()
        return strBuilder.toString();
    }
    /*END of solution-a*/



    /*solution-b*/
    public static String reverse1(String str){

        if (str.length() <= 1) {
            return str;
        }

        return reverse1(str.substring(1) ) + str.charAt(0);
    }
    /*END of solution-a*/

	/* END of solution: design an algorithm to reverse 
    a string using recursion and iteration*/










	/*question : design an algorithm to 
    find permutation of a string*/

	/*solution-a*/
	public static ArrayList<String> permutation( String str ) { 

		ArrayList<String> lis = new ArrayList<String>() ; 

    	permutation("", str, lis ); 
    	return lis; 
	}

	private static void permutation( String prefix, String str, ArrayList<String> lis){

	    int N = str.length();

	    if (N == 0) {

	    	lis.add(prefix);
	    	System.out.println(prefix);
	    }
	    	
	    else {

	        for (int i = 0; i < N; i++) {
	        	permutation(prefix + str.charAt(i),  str.substring(0, i) + str.substring(i+1),  lis);
	        }	        		            
	    }

	}
	/*END of solution-a*/
	 



	/*solution - b*/
    // using merge sort
	public static ArrayList<String> permutation1(String s){

	    ArrayList<String> res = new ArrayList<String>();

	    if (s.length() == 1){
            res.add(s);
        }
	    
	    else if (s.length() > 1){

	        int lastIndex = s.length() - 1;

	        // split the string in two parts 
	        String last = s.substring(lastIndex);
	        String rest = s.substring( 0, lastIndex );

	        if (rest != null && last != null){
	        	res = merge( permutation1(rest), last );
	        }	        	
	    }

	    return res;
    }
	

	public static ArrayList<String> merge(ArrayList<String> list, String c){

	    ArrayList<String> res = new ArrayList<String>();
	    // Loop through all the string in the list

	    for (String s : list) {

	        // For each string, insert the last character to all possible postions
	        // and add them to the new list	        
	        for (int i = 0; i <= s.length(); ++i) {

	        	// insert c in the index of i of String s 
	            String ps = new StringBuffer(s).insert(i, c).toString();
	            res.add(ps);
	        }
	    }

	    return res;
	}
	/*END of solution-b*/


    /*nPr = [ (n!)/( n - r)! ]  
      nCr = [ (n!)/{ r! * ( n - r)! } ] 
      where, n = total number of the elements
      r = how many we choose from them */



    /*solution-c*/
    // String str = "york"
    // new StringBuffer(str) and index = str.length() for all the permutations 
    // index < str.length() will also compile, but, will print lesser number of 
    // permutations 
	private static void doPerm( StringBuffer str, int index ){

	    if ( index <= 0 ){
	    	System.out.println(str);    
	    }
	        
	    else { 

	        doPerm( str, index - 1 );
	        int currPos = str.length() - index;

	        for ( int i = currPos + 1 ; i < str.length(); i++ ) {

	            swap( str , currPos , i );
	            doPerm( str, index - 1 );
	            swap( str, i , currPos );//restore back my string buffer	        	        
	        }	    
	    }
	}

	private  static void swap( StringBuffer str, int pos1, int pos2 ){

	    char t1 = str.charAt(pos1);

	    str.setCharAt( pos1, str.charAt(pos2) );
	    str.setCharAt(pos2, t1);
	}
	/*END of solution-c*/

	/* End of solution: design an algorithm 
    to find permutation of a string*/






    /*question: design an array to find a missing 
    number from an array of consecutive numbers*/
    public static int missingNumber( int [] arr ){

        /*1. substract the sum of array from 
        n(n+1)/2, the solution will be O(n) 
        2. solve it part by part similar to 
        random sort algorithm , we will need 
        O(log n) time complexity */

        return -1;
    }
    /*END of solution: design an array to find a missing 
    number from an array of consecutive numbers*/








    /*question: design an algorithm to find 
    the minimum distance of the elements
    of an array from 0*/
    public static int closeToZero(int[] arr){

        if (arr.length == 0 ){
            return Integer.MIN_VALUE;
        }

        int closeToZero = Integer.MAX_VALUE;
        int index = 0 ;

        for (int j = 0; j< arr.length; j++){

            if ( closeToZero > Math.abs(arr[j]) ){

                closeToZero = Math.abs(arr[j]); 
                index = j; 
            }
        }

        /* // 1st way:
        !Arrays.toList(ar).contains(closeToZero); 
          now, this method only works for Class type 
          not for primitives, hence, we will need to
          convert int[] to Integer[] as follwoing,

        Integer[] ar = Arrays.stream( arr ).boxed()
                        .toArray( Integer[]::new );

        // 2nd way 
        Integer close = closeToZero;
        !Arrays.stream(ar).anyMatch(close::equals)
        */

        Integer[] ar = Arrays.stream(arr).boxed().toArray(Integer[]:: new );

        if( arr[index] < 0 && !Arrays.asList(ar).contains(closeToZero) ){
            return -closeToZero;
        }

        /*
        Integer close = closeToZero;
        if( arr[index] < 0 &&  !Arrays.stream(ar).anyMatch(close::equals) ){

            return -closeToZero;
        }
        */
        
        return closeToZero;
    }
    /*END of solution: design an algorithm 
    to find the minimum distance of the 
    elements of an array from 0*/






	/*question: present the different 
    ways to use iterator in Java*/
	public static void iteratorTestMethod ( ) {

	    List<String> flavours = new ArrayList<>();

	    flavours.add("chocolate");
	    flavours.add("strawberry");
	    flavours.add("vanilla");

	    useWhileLoop(flavours);
	    useForLoop(flavours);
	}

	private static void useWhileLoop( Collection<String> aFlavours ) {

	    Iterator<String> flavoursIter = aFlavours.iterator();
        // Iterator<String> flavoursIter = aFlavours.listIterator();

	    while (flavoursIter.hasNext()){

	       System.out.println(flavoursIter.next());
	    }
	}

	private static void useForLoop(Collection<String> aFlavours) {

	    for (Iterator<String> flavoursIter = aFlavours.iterator(); flavoursIter.hasNext();	){

	       System.out.println( flavoursIter.next() );
	    }
	}
	/* END of solution: present the different 
    ways to use iterator in Java*/







	/*question : desing an algorithm to 
    reverse the words of the sentence*/
	/*solution - a*/
	public static String sentenceReverse5(String input){


        // split based on one or multiple white-spaces 
		Scanner sc = new Scanner(input);
		Stack<String> stack = new Stack<String>();

		while(sc.hasNext()) {
		    stack.push( sc.next() );
		}

		StringBuilder sb = new StringBuilder();

		while(!stack.isEmpty()) { 
		    sb.append(stack.pop()).append(" ");
		}

		return sb.toString().trim(); 
	}
    /*END of solution-a*/





    /*solution-b */
	public static String sentenceReverse4(String sen){


		/* "\\s" is for one white spaces 
		"\\s+" is for one or more than one whitespaces*/

		List<String> list = Arrays.asList(sen.split("\\s+")) ;
		
		Collections.reverse(list);
		StringBuffer b = new StringBuffer();

		for( String s : list){
		    b.append(s).append(" ");
		}

		return b.toString().trim();
	}
    /* END of solution-b */




    /*solution-c*/
	public static String sentenceReverse2 (String sen){

		String[] words =  sen.split("\\s+");

        /*1. "\\s" does split based on single white-spaces 
        2. "\\s+" does split based on one or more white-spaces 
        that means this only takes words 
        3. "\\b" does split based on everything (including white spaces), 
        that mean white-spaces will also be considered and included in 
        the generated array*/

		String rev = "";

		for (int i = words.length - 1; i >= 0 ; i--){
			rev += words[i] + " ";
		}

		// do the same using StringBuilder 
		StringBuilder sb = new StringBuilder();

		for( int i = words.length - 1 ; i >= 0 ; i-- ){
		   sb.append(words[i]).append(" ");
		}

		return sb.toString().trim(); 
	}
    /*END of solution-c*/



    /*solution-d, using recursion*/
	public static String sentenceReverse3(String s) {

	   int k = s.indexOf(" ");
	   return k == -1 ? s : sentenceReverse3(s.substring(k + 1)) + " " + s.substring(0, k);
	}
    /*END of solution-d*/
	/*END of solution: desing an algorithm to 
    reverse the words of the sentence*/








	/*question: desing an algorithm to remove 
    the duplicate elements from an int array*/

    /*solution-a*/
    public int[] removeArrDups(int [] arr ){

        int len = arr.length;
        
        if(len ==  0) {
            return null;
        }

        // Set<Integer> set = new HashSet<>(Arrays.asList(newArray));
        List<Integer> lis = new ArrayList<Integer>();

        for ( int j = 0;  j < len ; j++){

            if ( !lis.contains(arr[j])){
                lis.add(arr[j]);
            }
        }

        /*only be converted to Class array, it's 
        not possible to convert to primitive array*/ 
        Integer[] boxed_int = lis.toArray(new Integer[lis.size()]);
        // int[] arr = lis.stream().mapToInt(i->i).toArray();

        /*
        int[] myArr = new int[ boxed_int.length ];
        int count =0;

        for (Integer i: boxed_int){    
            myArr[count++] =i;
        }

        return myarr;
        */

        /* // this conversion is also correct
        int[] arr = { 10,10,1,2,34,56,3,34};

        Integer[] myArr = new Integer[arr.length];
        int count = 0;

        for (int i: arr){
            myArr[count++] = i;
        }

        System.out.println( Arrays.toString(myArr));
        */

        /* perform unboxing using Java 8*/
        int [] unboxing = Stream.of(boxed_int).mapToInt(Integer::intValue).toArray();
        // int [] unboxing = Stream.of(boxed_int).mapToInt(i -> i).toArray();

        return unboxing;
    }
    /*END of solution-a*/


    /* END solution: desing an algorithm to remove 
    the duplicate elements from an int array*/







	/*question: conversion between number 
    and string using various syntax*/
	public static void numberToString(  ){


        char[] ch = "Seattle".toCharArray();


        // convert character array to Stirng 
        System.out.println(String.valueOf(ch));
        System.out.println(new String(ch));


		System.out.println("here we go");

		int i = Integer.parseInt("123");
 		System.out.println("i: " + i);

        /* boxing */
  		Integer x =Integer.valueOf(9);
	    Double c = Double.valueOf(5);
	    Float a = Float.valueOf("80");               

        /* convert hexa-decimal number */
	    Integer b = Integer.valueOf("444",16);

	    System.out.println(x); 
	    System.out.println(c);
	    System.out.println(a);
	    System.out.println("the value Of b = "+ b );

      	Integer i1 = new Integer(30);
   
		String str = "5";


		// returns a Integer instance representing the specified string
		System.out.println( "Value = " + i1.valueOf(str));

	    int i2 = Integer.parseInt("000000081");
		System.out.println("i: " + i2 );

		String price = "" + 123;
		// new StringBuilder().append( "" ).append( 10 ).toString();

		String price1 = String.valueOf(123);
		String price2 = String.format ("%d", 123);
	}	
	/*END of solution : conversion 
    between string and number*/










	/* question: desing an algorithm to find 
    all duplicate characters in a String and 
    print each of them */	

    /*solution-a*/
	public static void printDuplicateCharacters(String word) { 


		Map<Character, Integer> charMap = 	makeMap(word);
		System.out.println("The word is = " + word); 

		for (Map.Entry<Character, Integer> entry : charMap.entrySet()){

			if ( entry.getValue() > 1 ){
		    	System.out.println( entry.getKey() + " = " + entry.getValue() );
            }
		}

		/* HashSet<Character> hs = charMap.entrySet();
		for (Character ch : hs){

			System.out.println(ch + " "+ charMap.get(ch) );
		}*/ 
	} 
    /*END of solution-a*/
  



    /*solution-b*/
	public static void printMap(String word ) {


		Map<Character, Integer> map = makeMap(word);
	    Iterator it = map.entrySet().iterator();

	    while (it.hasNext()){

	        Map.Entry pair = (Map.Entry)it.next();

	        if ( (int)pair.getValue()  > 1 ){	        	
	        	System.out.println( pair.getKey() + " = " + pair.getValue() );
	        }

	        it.remove();
	    }
	}	
    /*END of solution-b*/





    /*solution-a,b*/
    public static Map<Character, Integer> makeMap (String word) {

        char[] characters = word.toCharArray(); 
        Map<Character, Integer> charMap = new HashMap<Character, Integer>(); 

        for (Character ch : characters) { 
            charMap.put(ch, charMap.containsKey(ch) ? charMap.get(ch) + 1 : 1);
        }

        /* // this is also corrct
        for ( char ch : characters) { 

            charMap.put( ch, charMap.containsKey(ch) ? charMap.get(ch) + 1 : 1 );
        }
        */

        return charMap; 
    }
    /*ENd of solution-a,b*/
	/* END of solution: desing an algorithm to 
    find all duplicate characters in a String 
    and print each of them */   








	/*question: design an algoruthm to find the 
    first non-repitative character of a string*/
    /*solution-a*/
	public static char getFirstNonRepeatedChar( String str){ 

        /*
        * LinkedHashMap/LinkedHashSet keeps the order, 
        * but, not the HashMap/HashSet, note: LinkedHashSet 
        * has a method clear() that helps for removing 
        * everything from the Set
        */

		Map<Character,Integer> counts = new LinkedHashMap<Character,Integer>(str.length()); 

		for ( char c : str.toCharArray()) { 
			counts.put(c , counts.containsKey(c) ? counts.get(c) + 1 : 1); 
		} 

        // for ( Map.Entry<Character,Integer> entry : counts.entrySet() ) {}
		for (Entry<Character,Integer> entry : counts.entrySet()){ 

			if (entry.getValue() == 1) { 
				return entry.getKey(); 
			} 
		} 

		throw new RuntimeException("didn't find any non repeated Character"); 
	}
    /*END of solution-a*/





    /*solution-b*/
	public static char firstNonRepeatingChar(String word) { 


		Set<Character> repeating = new HashSet<Character>(); 

        // need to use List as Set doesn't keep the order
		List<Character> nonRepeating = new ArrayList<Character>(); 

		for (int i = 0; i < word.length(); i++) { 

			char letter = word.charAt(i); 

			if (repeating.contains(letter)) {
                continue; 
            }
			
			if (nonRepeating.contains(letter)) { 

                // casting from primitive to Wrapper class 
				nonRepeating.remove((Character) letter); 
				repeating.add(letter); 
			} 

			else { 
				nonRepeating.add(letter); 
			} 
		} 

		return nonRepeating.get(0); 
	}
    /* END of solution-b*/







    /*solution-c*/
	public static char firstNonRepeatedCharacter( String word ) { 

        // better to use the LinkedHashMap to keep sequence 
		Map<Character,Integer> scoreboard = new HashMap<Character,Integer>(); 

		for ( int i = 0; i < word.length(); i++ ) { 

			char c = word.charAt(i); 
			scoreboard.put(c, scoreboard.containsKey(c) ? scoreboard.get(c) + 1 : 1 );
		} 

        /*we need to iterate again over the word as 
        HashMap doesn't keep track of order */

		for (int i = 0; i < word.length(); i++) { 

			char c = word.charAt(i); 

			if ( scoreboard.get(c) == 1 ) { 

				return c; 
			} 
		} 

		throw new RuntimeException("Undefined behaviour");
	}
    /* END of solution-c*/
	/* END:  first non-repitative character of a string */






	/*question: design a algorithm to find out whether a 
    word is palandrom, PALINDROME: means reading forward 
    and backward of the same word keeps it same*/

    /*solution-a*/
	public static boolean isPalindrome3 (String str) {

	    return str.equals(new StringBuilder(str).reverse().toString());
	}
    /*END of solution-a*/


    /*solution-b*/
	public static boolean isPalindrome2(String str) {    

	    int n = str.length();

	    for ( int i = 0; i < n/2; i++ ){

	    	if (str.charAt(i) != str.charAt(n - i - 1)){ 
	        	return false;
            }    
	    }
	        
	    return true;    
	}
    /*END of solution-b*/



    /*solution-c*/
	public static boolean istPalindrom1 ( String word){

		char[] word = wor.toCharArray(); 

	    int i1 = 0;
	    int i2 = word.length - 1;

	    while ( i2 > i1 ) {

	        if ( word[i1++] != word[i2--]) {  
                return false;
            }
	    }

	    return true;
	}
    /*END of solution-c*/


    /* END of solution:design a algorithm to find out whether a 
    word is palandrom, PALINDROME : means reading forward 
    and backward of the same word keeps it same*/










	/*question 1-1 : design an algorithm to determine 
	if a string has all the unique characters*/	


    /*
    * the StringBuffer is synchronized while the StringBuilder is asynchronous
    */ 
    /*solution-a*/
	public static boolean isUniqueChars ( String str) {


		if (str.length() > 128) {
            return false;
        }

		/*
            ASCII character set 
    		'a' = 97
    		'z' = 122
    		'A' = 65
    		'Z' =  90
        */

        /*
            Let's represent cominations of N-element set as N-bit numbers, where jth bit is 1 
            if jth item is included in the combintation, and 0 otherwise. This way you can 
            represent all possible combinations as numbers in range [0, 2N).

            The outer loop iterates over these numbers (1 << N == 2N).

            The inner loop iterates over items of the set and if condition checks whether j-th 
            item is included into the current combination. In other words, it checks if jth bit 
            of i is 1.

            1<<j gives you a number where only jth bit is 1, i & (1 << j) resets all bits of i 
            other than that bit, and > checks that result is not 0.

            Note that this code (with ints) only works for N < 31.
        */

        int checker = 0;

		for ( int i = 0; i < str.length() ; i++) {

			int val = str.charAt(i) - 'a';

			if (checker & (1 << val) > 0) {
				return false;
            }

			checker |= (1 << val);
		}

		return true;
	}
    /* END of solution-a*/






    /*
    ASCII character set 
    'a' = 97
    'z' = 122
    'A' = 65
    'Z' =  90
    */

    /* solution-b*/
	public static boolean isUniqueChars1(String str) {

		if (str.length() > 128) {
            return false;
        }
		
        // will be initiated with false 
		boolean [] char_set = new boolean[128];
        // boolean [] char_set = new boolean[256];

		for ( int i = 0; i < str.length(); i++) {

			int val = str.charAt(i);

			if (char_set[val]){
				return false;
            }

			char_set[val] = true;
		}

		return true;
	}
    /* END of solution-b*/



    /* solution-c*/
    public static boolean isUniqueChars2(String in) {

        if (in == null) {
            return false;
        }
        
        Set<Character> chars = new HashSet<Character>();

        for (int i = 0; i < in.length(); i++) {

            Character c = in.charAt(i);

            if (chars.contains(c)){
                return false;
            }
            
            chars.add(c);
        }

        return true;
    }
    /*END of solution-c*/

	/*END of solution 1-1: design an algorithm to determine 
    if a string has all the unique characters*/









	/* question 1-3 remove the duplicate 
    characters in a string */

    /* solution-a */
	public static String removeDups1( String str1) {


		ArrayList<Character> lis = new ArrayList<Character>();
	    char[] str = str1.toCharArray();

	    for(int i = 0 ; i < str.length; i++){

	        if(!lis.contains( str[i])){
	            lis.add(str[i]);
	        }
	    }

        // StringBuilder constructors 
        // --------------------------

        // 1. StringBuilder ()
        // 2. StringBuilder(int capacity)
        // 3. StringBuilder(Stirng str)
        // 4. StringBuilder(CharSequence seq) where, CharSequence is an interface  


        // StringBuilder -> charAt(i) returns the Charcater
        // StringBuilder -> deleteCharAt(i) returns rest of the String 
	    StringBuilder builder = new StringBuilder(lis.size());

        /*for( Character e: set){ 
        } also correct*/

	    for(char c : lis){
	        builder.append(c);
	    }

	    return builder.toString();
        // StringBuilder also has a trim() method
        // return builder.toString().trim(); is correct
	}
    /*END of solution-a*/





    /*solution-b*/
	public static String removeDups2 ( String s ) {

	    StringBuilder noDupes = new StringBuilder();

	    for (int i = 0; i < s.length(); i++) {

            // i-the Character is converted to String 
	        String si = s.substring(i, i + 1);

	        if (noDupes.indexOf(si) == -1){
	            noDupes.append(si);	        
	        }
	    }
	    
	    return noDupes.toString();
	}
    /*END of solution-b*/


    /*solution-c*/
	public static String removeDups3(String input) {

	    return new StringBuilder(
	           new StringBuilder(input)
                    .reverse()
                    .toString()
                    .replaceAll("(.)(?=.*\\1)", ""))
                    .reverse().toString();
	}
    /*END of solution-c*/




    /*solution-d*/
	public static String removeDups4(String str1){

	    if (str1 == null) {
	        return null;
        }

	    char[] str = str1.toCharArray();
	    int N = str.length;

	    if (N < 2) {
	        return str1;
        }

	    char temp = str[0];

	    for (int i = 1; i < N; i++){

	    	if (temp != '\0'){

	    		for (int j = i; j < N; j++){

	                if ( temp == str[j] ){
	                    str[j] = '\0';
                    }
                }
	        }

	        temp = str[i];
	    }

	    int i = 0;
	    char[] str2 = new char[N];

	    for ( char c : str ){

            if ( c != '\0' ){
                str2[i++] = c;
            }
        }
	       
	    return new String(str2).trim();
	}
    /*End of solution-d*/





    /*solution-e*/
	public static String removeDups5 (String str){


		StringBuffer buf = new StringBuffer(str);
		int len = str.length();

		for (int i = 0; i < len; i++){

		     char c = buf.charAt(i);

		     for (int j = len - 1; j > i; j--) {

		        if ( buf.charAt(j) == c ) {

		           buf.deleteCharAt(j);
		           len--;
		        }
		     }
		}

		return buf.toString(); 
	}
    /*END of solution-e*/




    /*solution-f*/
    public static String removeDups6( String str ){


        // StringBuffer constructors 
        // 1. StringBuffer ()
        // 2. StringBuffer(int capacity)
        // 3. StringBuffer(Stirng str)
        // 4. StringBuffer(CharSequence seq) where, CharSequence is an interface  

        // StringBuffer i) charAt(j) ii) deleteCharAt(j)
        StringBuffer sb = new StringBuffer(str);
        int len = str.length();

        for (int i = 0; i < len ; i++){

            int j = (len-1);

            while(j > i){

                if ( sb.charAt(i) == sb.charAt(j) ){

                    sb.deleteCharAt(j);
                    len--;
                }

                j--;
            }
        }

        return sb.toString();
    }
    /*END of solution-f*/




    /*solution-g*/
	public static String removeDups7(String str1){

		char[] chars = str1.toCharArray();

        // LinkedHashSet will keeps the sequence 
		Set<Character> charSet = new LinkedHashSet<Character>();

		for (char c : chars){
		    charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();

		for (Character character : charSet){
		    sb.append(character);
		}

		return sb.toString();
	}
    /*END of solution-g*/






    /*solution-h*/
	public static String removeDups8 (String str1){

	    StringBuilder sb = new StringBuilder(str1);

	    for (int i = 0; i < sb.length(); i++){

	        String st = String.valueOf(sb.charAt(i));

            // first instance after the i-th index 
	        int dupIndex = sb.indexOf(st, i + 1);

            // StringBuilder i) indexOf(String str) ii) i) indexOf(String str, int fromIndex )
	        // Keep looping until all duplicates of the current letter are deleted
            // delete additional dups if exist 
	        while ( dupIndex != -1 ){

	            sb.deleteCharAt(dupIndex);
	            dupIndex = sb.indexOf( st, i + 1);
	        }
	    }

	    return sb.toString();
	}
    /*END of solution-h*/





    /*solution-i*/
	public static String removeDups10( String str1) {
		
        if (str1 == null) {
            return null;
        }

        if (str1.length() < 2) {
            return str1;
        }

		char[] str = str1.toCharArray();		
		int len = str.length;


		for ( int i = 0 ; i < len-1 ; i++){

			if( str[i] != '\0'){

                for( int j= i+1 ; j < len; j++){

                    if ( str[i] == str[j]  && str[j] != '\0'){

                        str[j] = '\0'; 
                    }                    
                }
            }
		}

		StringBuilder sb = new StringBuilder(); 

		for( char e: str ){

			if ( e != '\0'){
	        	sb.append(e);
            }
	    }

		return sb.toString();  
	}
    /*END of solution-i*/




	/*solution-j*/
    public static String removeDups11 ( String string ){


        if ( string == null) {
            return null;
        }

        if ( string.length() < 2) {
            return string;
        }
            
        char[] str = string.toCharArray();
        int len = str.length;

        boolean[] hit = new boolean[256];

        hit[ str[0] ] = true; 
        int tail = 1;

        for ( int i = 1; i < len; i++ ){

            // str[i] is char, when inserted inside the 
            // boolean [] hit, it automatically converted to it's ASCII value 
            // and being used as index 
            if (!hit[ str[i]]){

                hit[ str[i] ] = true;   
                str[tail++] = str[i];
            }
        }

        // put the end of the array as null Character 
        while(tail < len) {
            str[tail++] = '\0';
        }

        return new String(str).trim();
    }
    /*END of solution-j*/


	/* END of solution 1-3 remove the 
    duplicate characters in a string*/






	/*question 1-4: design an algorithm to 
    check whether two strings are anagram*/
    // ANAGRAM: creating a new word by rearranging the 
    // same letters of the previous word exactly once 

	/*solution - a*/
	public static boolean isAnagram( String word, String anagram ){

		if (word.length() != anagram.length()) {
			return false;
        }
		
		char[] chars = word.toCharArray();

		// looping through the 'word'
		for (char c : chars) {

            // Stirng.indexOf( works for both String and Character)
			int index = anagram.indexOf(c);

			if (index != -1) {

                // char in the index is deleted 
				anagram = anagram.substring(0, index) + anagram.substring(index + 1);
			} 

			else 
                return false;			
		}

		return anagram.isEmpty();

        /* 1. Stirng indexOf() takes of Character and Stirng
        // String takes both primitive char and wrapper class Character 
        but, StringBuilder indexOf() takes only String 

        2.String has isEmpty() method
        but, StringBuilder doesn't have any isEmpty() method
        3. both share the length() method*/
	}
    /* END of solution-a*/







	/*solution-b: another way to check if two Strings are anagram or not, 
	This method assumes that both word and anagram are not null and 
	lowercase * @return true, if both Strings are anagram.*/

	public static boolean iAnagram( String word, String anagram ) {

		char[] charFromWord = word.toCharArray();
		char[] charFromAnagram = anagram.toCharArray();

		Arrays.sort(charFromWord);
		Arrays.sort(charFromAnagram);

		return Arrays.equals(charFromWord, charFromAnagram);
	}


	public static boolean checkAnagram (String first, String second) {


		char[] characters = first.toCharArray();
		StringBuilder sbSecond = new StringBuilder(second);

		for (char ch : characters) {

            // StringBuilder indexOf only works String 
			int index = sbSecond.indexOf( "" + ch );

			if (index != -1) {
				sbSecond.deleteCharAt(index);
			} 

			else {
				return false;			
            }
		}

        // StringBuilder doesn't have any isEmpty() method 
		return sbSecond.length() == 0;
	}


	public static boolean permutation1(String s, String t) {
		return sort(s).equals(sort(t));
	}


	/* the first solution of anagram */
	public static boolean anagram2 (String s, String t) { 

		return sort(s) == sort(t);
        // return sort(s).compareTo(sort(t)) == 0;
	}

	public static String sort(String s) {

		char[] content = s.toCharArray();

		Arrays.sort(content);
		return new String(content);		
		// return String.valueOf(content)
	}



    /*
    ALGORITHM
    ---------
    i.   count the unique characters and all characters of the 1st string

    ii.  decrease the unique characters and all characters of the 2nd string

    iii. if the count of all characters maintain true for 2nd string and 
         the number of unique character matches in both of the strings, return 
         true
    */
	
	/*second solution of anagram*/
	public static boolean anagram( String s, String t ) {


		if (s.length() != t.length()){
			return false;
        }

		/* ASCII character set 		
		NUL = 0 
		'a' = 97
		'z' = 122

		'A' = 65
		'Z' =  90
		'DEL' = 127*/

        /*now, every cell is filled with zeros*/
		int[] letters = new int[128];

		int num_unique_chars = 0;
		int num_completed_t = 0;

		char[] s_array = s.toCharArray();

		for (char c : s_array) { // count number of each char in s.

			if (letters[c] == 0){
                ++num_unique_chars;
            }
				
			++letters[c];
		}

		for (int i = 0; i < t.length(); ++i) {

            // casting is not necessary 
			int c = (int) t.charAt(i);

			if ( letters[c] == 0) { 
                return false;
            }
			
			// it means letter[c] exit, now decrease one character 
			--letters[c];

			if (letters[c] == 0){

				++num_completed_t;

				if (num_completed_t == num_unique_chars){

					// it's a match if t has been processed completely
					return true;
					//return i == t.length() - 1;
				}
			}
		}

		return false;
	}	





	/* method for checking anagram */
	public static boolean permutation2(String s, String t){

		if ( s.length() != t.length() ) {
			return false;
		}
		
		int[] letters = new int[128];		 
		char[] s_array = s.toCharArray();

		for (char c : s_array) { 
			letters[c]++;  
		}
		  
		for (int i = 0; i < t.length(); i++) {

			int c = (int) t.charAt(i);

		    if (--letters[c] < 0) {

		    	return false;
		    }
		}
		  
		return true;
	}
	/* END solution 1-4 design an algorithm to check 
	whether two words are anagram to each other */








	/*question 1-5 : design an algorithm to replace 
    all spaces in a string with ‘%20’*/
	public static String replaceSpaces(String str){


        char[] ch = str.toCharArray();
        int count = 0;

        for ( char c: ch){

            if (c == ' '){
                count++;
            }
        }

        int len = ch.length + 2 * count;
        char[] modified = new char[len];

        for(int j = ch.length -1; j >= 0 && len > 0; j--){

            char c = str.charAt(j);

            // not the same as '\0'
            if (c == ' '){

                modified[len-1] = '0';
                modified[len-2] = '2';
                modified[len-3] = '%';

                len -= 3;
            }

            else {                
                modified[ len - 1] = c;
                len--;
            }
        }

        return new String(modified).trim();
    }


    public static String replaceSpaces1(String in) {

        if (in == null){
            return null;
        }
        
        /*
        * there are no difference between the java.lang.String methods 
        * replace() and replaceAll(), other than that the later uses 
        * regex
        */

        return in.replace(" ", "%20");
        return in.replaceAll(" ", "%20");
    }
    /* END of solution: design an algorithm to replace 
    all spaces in a string with ‘%20’*/






	/*Question : An unlabeled break statement terminates the innermost switch, for, while, 
	or do-while  statement, but a labeled break terminates an outer statement.*/
	public static void breakTest(){

        int[][] arrayOfInts = { 

            { 32, 87, 3, 589 },
            { 12, 1076, 2000, 8 },
            { 622, 127, 77, 955 }
        };

        int searchfor = 12;

        int i, j;
        boolean foundIt = false;

	    search:
            for ( i = 0; i < arrayOfInts.length; i++ ) {

                for ( j = 0; j < arrayOfInts[i].length; j++ ) {

                    if (arrayOfInts[i][j] == searchfor) {

                        foundIt = true;
                        break search;
                    }
                }
            }

        if (foundIt){
            System.out.println("The number is found");
        }
        
        else {
            System.out.println("Not found");    
        }
    }
	/*END of question 1-5 */








	/*question 1-6 : design an algorithm  to rotate an 
    image represented in N x N  matrix by 90 degrees*/

    /*solution-a*/
    public static int[][] rotateMatrix(int[][] in) {
        
        if (in == null) {
            return null;
        }

        // 1,1   1,2   1,3    
        // 1,3   2,3   3,3

        // 2,1   2,2   2,3 
        // 1,2   2,2   3,2

        // 3,1   3,2   3,3 
        // 1,1   2,1   3,1

                
        final int N = in.length; // NxN matrix
        int[][] out = new int[N][N];
        
        for (int x = 0; x < N; x++) {

            for (int y = 0; y < N; y++) {

                out[y][N-1-x] = in[x][y];
            }
        }

        return out;
    }
    /*END of solution-a*/


	public static void rotate ( int[][] matrix, int n ) {

		for (int layer = 0; layer < n/2; ++layer) {

			int first = layer;
			int last = n - 1 - layer;
			
			for ( int i = first; i < last; ++i ) {


				int offset = i - first;
				int top = matrix[first][i]; // save top


				// left -> top
				matrix[first][i] = matrix[last-offset][first]; 			

				// bottom -> left
				matrix[last-offset][first] = matrix[last][last - offset]; 

				// right -> bottom
				matrix[last][last - offset] = matrix[i][last]; 

				// top -> right
				matrix[i][last] = top; // right <- saved top
			}
		}
	}


	public static int[][] randomMatrix( int M, int N, int min, int max) {

		int[][] matrix = new int[M][N];

		for (int i = 0; i < M; i++) {

			for (int j = 0; j < N; j++) {
 
				matrix[i][j] = randomWithRange(min, max);
			}
		}

		return matrix;
	}



	/* Math.random() returns a number from 0.0 to 1.0.
      Math.random()*n returns value 0 to n 

    (int) Math.floor( Math.random() * 101 ) return 0 to 100
    (int)(Math.random() * 101); returns 0 to 100  
    // floor is not necessary  

    (int) Math.ceil( Math.random() * 100) returns 1 to 100*/    
	public static int randomWithRange(int min, int max){

	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}


	public static void printMatrix2 ( int[][] matrix ) {

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[i].length; j++) {

				System.out.print( matrix[i][j] + " ");
			}

			System.out.println();		
		}
	}
	/*END solution 1-6*/








	/*question 1-7 design an algorithm such 
    that if an element in an MxN matrix is 0, 
    its entire row and column is zero*/

	public static void setZeros(int[][] matrix) {

		boolean[] row = new boolean[matrix.length];	
		boolean[] column = new boolean[matrix[0].length];

		// Store the row and column index with value 0
        // rows 
		for (int i = 0; i < matrix.length; i++){

            // columns 
			for (int j = 0; j < matrix[0].length; j++) {

				if (matrix[i][j] == 0) {

					row[i] = true; 
					column[j] = true;
		 		}
			}
		}


		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[0].length;j++) {

				if ( row[i] || column[j] ){

					matrix[i][j] = 0;
				}
			}
		}

		printMatrix(matrix);
	}

	public static int[][] cloneMatrix( int[][] matrix ) {

		int[][] c = new int[ matrix.length ][ matrix[0].length ];

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[0].length; j++){
				c[i][j] = matrix[i][j];
			}
		}

		return c;
	}	
	/*END of solution 1-7 design an algorithm 
    such that if an element in an MxN matrix 
    is 0, its entire row and column is zero*/








	/*question 1-8 : design an algorithm to check for 
	two strings, s1 and s2 if s2 is a rotation of s1*/
	/*'waterbottle'  is the rotation of 'erbottlewat'*/

    /*solution - a*/
    public static boolean isRotation(String s1, String s2) {

        int len = s1.length();

        if ( len == s2.length() && len > 0 ) { 

            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }

        return false;   
    }

    public static boolean isSubstring( String big, String small ) {

		if (big.indexOf(small) >= 0){
            return true;		
        }
		
        else {
            return false;			
        }
	}
    /*END of solution - a*/
	

	/*solution -b*/
    public static boolean isRotation(String s1, String s2) {

        if (s1 == null || s2 == null){
            return false;
        }

        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }
    /*END of solution - b*/

	/* END solution 1-8 design an algorithm to check for 
    two strings, s1 and s2 if s2 is a rotation of s1 */




    /*
    * Sort a map based on the values and keep the top n elements 
    */
    private static void SortMapBasedOnValues(Map<Person, Duration>  map, int n){

        Map<Person, Duration> sortedDecreasingly = map.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(n)
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }




	public static void main ( String[] args  ){


        /*question 19-1: Write a function to swap a number 
        in place without temporary variables.*/
        int a = 1672;
        int b = 9332;
        
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        
        swap(a, b);
        swap_opt(a, b);
        /*END of solution: Write a function to swap a 
        number in place without temporary variables.*/



        /*Question 19-3: Write an algorithm which 
        computes the number of trailing zeros in n 
        factorial.*/

        /*solution-a*/
        for (int i = 1; i < 12; i++) {
            System.out.println(i + "! (or " + factorial(i) + ") has " + countFactZeros(i) + " zeros");
        }
        /*END of solution-a*/

        /*solution-b*/
        for (int i = 1; i < 12; i++) {
            System.out.println(i + "! (or " + factorial(i) + ") has " + countFactZeros(i) + " zeros");
        }
        /*END of solution-b*/

        /*END of solution 19-3: Write an algorithm 
        which computes the number of trailing zeros 
        in n factorial.*/




        /*Question 19-4: Write a method which finds the maximum 
        of two numbers. You should not use ifelse or any 
        other comparison operator. For example, Input: 5, 10
        Output: 10*/
        int a = 26;
        int b = -15;
        
        System.out.println("max_naive(" + a + ", " + b + ") = " + getMaxNaive(a, b));
        System.out.println("max(" + a + ", " + b + ") = " + getMax(a, b));      
        
        a = -15;
        b = 2147483647;
        
        System.out.println("max_naive(" + a + ", " + b + ") = " + getMaxNaive(a, b));
        System.out.println("max(" + a + ", " + b + ") = " + getMax(a, b));
        
        /*END of solution 19-4: Write a method which finds the 
        maximum of two numbers. You should not use ifelse or 
        any other comparison operator. For example, Input: 5, 
        10,  Output: 10*/





        /*question 19-5: The Game of Master Mind is played as follows: The computer has four 
        slots containing balls that are red (R), yellow (Y), green (G) or blue (B). For 
        example, the computer might have RGGB (e.g., Slot #1 is red, Slots #2 and #3 are 
        green, Slot #4 is blue). You, the user, are trying to guess the solution. You might, 
        for example, guess YRGB. When you guess the correct color for the correct slot, you 
        get a “hit”. If you guess a color that exists but is in the wrong slot, you get a 
        “pseudo-hit”. For example, the guess YRGB has 2 hits and one pseudo hit. For each 
        guess, you are told the number of hits and pseudo-hits. Write a method that, given 
        a guess and a solution, returns the number of hits and pseudo hits.*/


        /*ENd of solution 19-6:The Game of Master Mind is played as follows: The computer has four 
        slots containing balls that are red (R), yellow (Y), green (G) or blue (B). For 
        example, the computer might have RGGB (e.g., Slot #1 is red, Slots #2 and #3 are 
        green, Slot #4 is blue). You, the user, are trying to guess the solution. You might, 
        for example, guess YRGB. When you guess the correct color for the correct slot, you 
        get a “hit”. If you guess a color that exists but is in the wrong slot, you get a 
        “pseudo-hit”. For example, the guess YRGB has 2 hits and one pseudo hit. For each 
        guess, you are told the number of hits and pseudo-hits. Write a method that, given 
        a guess and a solution, returns the number of hits and pseudo hits.*/





        /*Question 19-6: Given an integer between 0 and 999,999, 
        print an English phrase that describes the integer 
        (eg, “One Thousand, Two Hundred and Thirty Four”).*/

        /* numbers between 100000 and 1000000 */
        for (int i = 0; i < 8; i++) {
            int value = (int) Math.pow(10, i);
            String s = numToString(-1 * value);
            System.out.println(value + ": " + s);
        }           
        
        /* numbers between 0 and 100 */
        for (int i = 0; i < 10; i++) {
            int value = AssortedMethods.randomIntInRange(0, 100);
            String s = numToString(value);
            System.out.println(value + ": " + s);
        }   
        
        /* numbers between 100 and 1000 */
        for (int i = 0; i < 10; i++) {
            int value = AssortedMethods.randomIntInRange(100, 1000);
            String s = numToString(value);
            System.out.println(value + ": " + s);
        }
        
        /* numbers between 1000 and 100000 */
        for (int i = 0; i < 10; i++) {
            int value = AssortedMethods.randomIntInRange(1000, 100000);
            String s = numToString(value);
            System.out.println(value + ": " + s);
        }       
        
        
        // numbers between 100000 and 100000000 
        for (int i = 0; i < 10; i++) {
            int value = AssortedMethods.randomIntInRange(100000, 100000000);
            String s = numToString(value);
            System.out.println(value + ": " + s);
        }   
        
        // numbers between 100000000 and 1000000000 
        for (int i = 0; i < 10; i++) {
            int value = AssortedMethods.randomIntInRange(100000000, 1000000000);
            String s = numToString(value);
            System.out.println(value + ": " + s);
        }           
                
        // numbers between 1000000000 and Integer.MAX_VALUE
        for (int i = 0; i < 10; i++) {
            int value = AssortedMethods.randomIntInRange(1000000000, Integer.MAX_VALUE);
            String s = numToString(value);
            System.out.println(value + ": " + s);
        }
        /*ENd of solution 19-6: Given an integer between 0 and 
        999,999, print an English phrase that describes the 
        integer*/






        /*Question 19-7: You are given an array of integers 
        (both positive and negative). Find the continuous 
        sequence with the largest sum. Return the sum. EXAMPLE,
        
        Input: {2, -8, 3, -2, 4, -10}
        Output: 5 (i.e., {3, -2, 4} )*/
        
        int[] a = {2, -8, 3, -2, 4, -10};
        System.out.println(getMaxSum(a));

        /*END of solution 19-7: You are given an array of integers 
        (both positive and negative). Find the continuous sequence 
        with the largest sum. Return the sum. EXAMPLE,
        
        Input: {2, -8, 3, -2, 4, -10}
        Output: 5 (i.e., {3, -2, 4} )*/






        /*Question 19-8: Design a method to  find 
        the frequency of occurrences of any given 
        word in a book.*/
        /*solution-a*/
        String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
        
        String[] words = {"the", "Lara", "and", "outcropping", "career", "it"};
        for (String word : words) {
            System.out.println(word + ": " + getFrequency(wordlist, word));
        }
        /*END of solution-a*/
        

        /*solution-b*/
        String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
        Hashtable<String, Integer> dictionary = setupDictionary(wordlist);
        
        String[] words = {"the", "Lara", "and", "outcropping", "career", "it"};
        for (String word : words) {
            System.out.println(word + ": " + getFrequency(dictionary, word));
        }
        /*END of solution-b*/

        /*END of solution 19-8: Design a method to  
        find the frequency of occurrences of any 
        given word in a book.*/




        /*question: Since XML is very verbose, you are given a way of encoding it where 
        each tag gets mapped to a pre-defined integer value. The language/grammar is as 
        follows:

        Element --> Element Attr* END Element END [aka, encode the element
        tag, then its attributes, then tack on an END character, then encode 
        its children, then another end tag]

        Attr --> Tag Value [assume all values are strings]

        END --> 01

        Tag --> some predefined mapping to int

        Value --> string value END

        Write code to print the encoded version of an xml element (passed in as string). Is 
        there anything else you could do to (in many cases) compress this even further?*/
        Element root = new Element("family");
        Attribute a1 = new Attribute("lastName", "0");
        Attribute a2 = new Attribute("state", "CA");
        root.insert(a1);
        root.insert(a2);
        
        Element child = new Element("person", "Some Message");
        Attribute a3 = new Attribute("firstName", "Gayle");
        child.insert(a3);
        
        root.insert(child);
        
        String s = encodeToString(root);
        System.out.println(s);
        /*END of solutin: XML extraction*/





        /*17-11*/

        /*A*/
        /* Test: call rand7 many times and inspect the results. */
        int[] arr = new int[7];
        int test_size = 1000000;
        for(int k = 0; k < test_size; k++){
            arr[rand7()]++;
        }

        for (int i = 0; i < 7; i++) {
            double percent = 100.0 * arr[i] / test_size;
            System.out.println(i + " appeared " + percent + "% of the time.");
        }
        /*A*/

        /*B*/
        /* Test: call rand7 many times and inspect the results. */
        int[] arr = new int[7];
        int test_size = 1000000;
        for(int k = 0; k < test_size; k++){
            arr[rand7()]++;
        }
        
        for (int i = 0; i < 7; i++) {
            double percent = 100.0 * arr[i] / test_size;
            System.out.println(i + " appeared " + percent + "% of the time.");
        }
        /*B*/
        /*17-11*/



        /*17-12*/
        int[] test = {9, 3, 6, 5, 7, -1, 13, 14, -2, 12, 0};
        printPairSums(test, 12);
        /*17-12*/



        /*17-14*/
        dictionary = AssortedMethods.getTrieDictionary();
        sentence = "As one of the top companies in the world, Google will surely attract the attention of computer gurus. This does not, however, mean the company is for everyone.";
        sentence = clean(sentence);
        System.out.println(sentence);
        //Result v = parse(0, 0, new Hashtable<Integer, Result>());
        //System.out.println(v.parsed);
        int v = parseOptimized(0, 0, new Hashtable<Integer, Integer>());
        System.out.println(v);
        /*17-14*/



		/* question: implement hash-table data-structure */

		/*int tableSize =  10; 
        HashTable ht = new HashTable( tableSize );

        for ( int j = 0; j < tableLimit; j ++ ){

        	ht.insert( j + 3);
        }

        ht.printTable();  */
		/* END : implement hash - table data-structure */







		/* question: design an algorithm to get permutation of a string */
		/*System.out.println();
		permutation("Are");*/
		/* END : design an algorithm to get permutation of a string */







		/* question 1-3 */
		/* remove the duplicate characters in a string */
				
		/*String str= "miami"; 
		System.out.println("\nOriginal word "+ str + " , after delete dups " + removeDuplicates(str) + "\n");
		System.out.println("\nOriginal word "+ str + " , after delete dups " + removeDuplicates2(str) + "\n");

		System.out.println("\nOriginal word "+ str + " , after delete dups " + removeDups(str) + "\n");
		System.out.println("\nOriginal word "+ str + " , after delete dups " + sortString(str) + "\n");
		*/






		/* question 1-4 */
		/* design an algorithm to check whether two strings are anagram */

		/*
		String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
		System.out.println();

		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram = anagram (word1, word2);

			if (anagram){

				System.out.println(word1 + ", " + word2 + " are anagram. ");
			}

			else {

				System.out.println(word1 + ", " + word2 + " aren't  anagram. ");
			}	
		}
		System.out.println();
		*/






		/* question 1-5 design an algorithm to replace all spaces in a string with ‘%20’.*/
		
		/*
		String str = "abc d e f";
		char[] arr = new char[str.length() + 3 * 2 + 1];
		for (int i = 0; i < str.length(); i++) {
			arr[i] = str.charAt(i);
		}
		
		replaceSpaces(arr, str.length());	
		System.out.println("\"" + charArrayToString( arr ) + "\"");
		*/






		/* question 1-6 design an algorithm  to rotate an image represented in N x N  matrix by 90 degrees */

		/*
		System.out.println();
		int[][] matrix = randomMatrix(10, 10, 0, 7);
		printMatrix(matrix);
		rotate(matrix, 10);
		System.out.println("\n\n\n");
		printMatrix(matrix);
		*/
		/* END solution test 1-6*/






		/* question 1-7 design an algorithm such that if an element 
		in an MxN matrix is 0, its entire row and column is zero */

		/*
		int nrows = 5;
		int ncols = 5;
		int[][] matrix1 = randomMatrix(nrows, ncols, 0, 8);		
		int[][] matrix2 = cloneMatrix(matrix1);

		System.out.println("\n\n");
		printMatrix(matrix1);
		
		System.out.println("\n\n");
		setZeros(matrix1);
		System.out.println();
		*/
		/* END solution test 1-7*/






		/* question 1-8 : design an algorithm to check for 
		two strings, s1 and s2 if s2 is a rotation of s1 */
		/*
		System.out.println("\n\n");
		String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};

		for (String[] pair : pairs) {

			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = isRotation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}
		
		System.out.println("\n\n");
		*/
		/* END solution test 1 - 8*/

		learnArrayList();  


	}
}