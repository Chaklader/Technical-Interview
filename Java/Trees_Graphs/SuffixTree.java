package com.codility.Graphs_Trees;

import java.util.*;

/*
Suffix tree is a compressed trie containing all the suffixes of the 
given text as their keys and positions in the text as their values. 
Suffix trees allow particularly fast implementations of many important 
string operations.

The construction of such a tree for the string S takes time and space 
linear in the length of S. Once constructed, several  operations can be 
performed quickly, for instance locating a substring in S, locating a 
substring if a certain number of mistakes are allowed, locating matches 
for a regular expression pattern etc.
*/
class SuffixTree {


    private static class Node {

        char value;

        HashMap<Character, Node> children = new HashMap<Character, Node>();

        ArrayList<Integer> indexes = new ArrayList<Integer>();

        public Node() {

        }


        public void insertString(String s, int index) {

            indexes.add(index);

            if (s != null && s.length() > 0) {

                value = s.charAt(0);
                Node child = null;

                if (children.containsKey(value)) {
                    child = children.get(value);
                } else {
                    child = new Node();
                    children.put(value, child);
                }

                String remainder = s.substring(1);
                child.insertString(remainder, index);
            }
        }


        public ArrayList<Integer> search(String s) {

            if (s == null || s.length() == 0) {
                return indexes;
            } else {

                char first = s.charAt(0);

                if (children.containsKey(first)) {
                    String remainder = s.substring(1);
                    return children.get(first).search(remainder);
                }
            }

            return null;
        }

    }

    Node root = new Node();

    public SuffixTree(String s) {

        for (int i = 0; i < s.length(); i++) {

            String suffix = s.substring(i);
            root.insertString(suffix, i);
        }
    }


    public ArrayList<Integer> search(String s) {

        return root.search(s);
    }
}