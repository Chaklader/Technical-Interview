package com.codility.Graphs_Trees;

import java.util.*;

/**
 * Created by Chaklader on 7/22/18.
 */
public class Trie {

    private static class TrieNode {

        char c;
        boolean isLeaf;

        // can have multiple children
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

        public TrieNode() {

        }

        public TrieNode(char c) {
            this.c = c;
        }
    }


/*

                       ROOT
                    /   \    \
                    t   a     b
                    |   |     |
                    h   n     y
                    |   |  \  |
                    e   s  y  e
                 /  |   |
                 i  r   w
                 |  |   |
                 r  e   e
                        |
                        r

*/

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        Map<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);
            TrieNode t;

            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);

                // Says, the chidren of root is the new node "t"
                children.put(c, t);
            }

            /*
             * "t" is the new node and we update the children for "t"
             */
            children = t.children;

            if (i == word.length() - 1) {
                t.isLeaf = true;
            }
        }
    }


    // Returns if the word is in the trie
    public boolean search(String word) {

        TrieNode t = searchNode(word);

        if (t != null && t.isLeaf) {
            return true;
        } else {
            return false;
        }
    }


    public TrieNode searchNode(String str) {

        // one or many children
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            if (children.containsKey(c)) {

                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }

        return t;
    }

    /*
     * the trie contains the substring (prefix)
     */
    public boolean startsWith(String prefix) {

        if (searchNode(prefix) == null) {
            return false;
        } else {
            return true;
        }
    }
}
