package com.codility.Graphs_Trees;

import java.io.Serializable;
import java.util.*;

/**
 * A radix tree. Radix trees are String -> Object mappings which allow quick
 * lookups on the strings. Radix trees also make it easy to grab the objects
 * with a common prefix.
 * <p>
 * <p>
 * radix tree (also radix trie or compact prefix tree) is a data structure that
 * represents a space-optimized trie in which each node that is the only child
 * is merged with its parent. The result is that the number of children of every
 * internal node is at most the radix r of the radix tree, where r is a positive
 * integer and a power x of 2, having x ≥ 1. Unlike in regular tries, edges can
 * be labeled with sequences of elements as well as single elements. This makes
 * radix trees much more efficient for small sets (especially if the strings are
 * long) and for sets of strings that share long prefixes.
 *
 * @param <V> the type of values stored in the tree
 * @see <a href="http://en.wikipedia.org/wiki/Radix_tree">Wikipedia</a>
 */
public class RadixTree<V extends Serializable> implements Map<String, V>, Serializable {


    public interface RadixTreeVisitor<V, R> {
        /**
         * Visits a node in a radix tree.
         *
         * @param key   the key of the node being visited
         * @param value the value of the node being visited
         */
        public abstract void visit(String key, V value);

        /**
         * An overall result from the traversal of the radix tree.
         *
         * @return the result
         */
        public abstract R getResult();
    }


    /**
     * A node in a radix tree.
     */
    static class RadixTreeNode<V extends Serializable> implements Iterable<RadixTreeNode<V>>, Comparable<RadixTreeNode<V>>, Serializable {

        /**
         * The prefix at this node
         */
        private String prefix;

        /**
         * The value stored at this node
         */
        private V value;

        /**
         * Whether or not this node stores a value. This value is mainly used by
         * {@link RadixTreeVisitor} to figure out whether or not this node should
         * be visited.
         */
        private boolean hasValue;

        /**
         * The children for this node. Note, because we use {@link TreeSet} here,
         * traversal of {@link RadixTree} will be in lexicographical order.
         */
        private Collection<RadixTreeNode<V>> children;

        /**
         * Constructs a node from the given prefix.
         *
         * @param prefix the prefix
         */
        RadixTreeNode(String prefix) {
            this(prefix, null);
            this.hasValue = false;
        }

        /**
         * Constructs a node from the given prefix and value.
         *
         * @param prefix the prefix
         * @param value  the value
         */
        RadixTreeNode(String prefix, V value) {
            this.prefix = prefix;
            this.value = value;
            this.hasValue = true;
        }


        /**
         * Gets the value attached to this node.
         *
         * @return the value, or <code>null</code> if an internal node
         */
        V getValue() {
            return value;
        }

        /**
         * Sets the value attached to this node.
         *
         * @param value the value, or <code>null</code> if an internal node
         */
        void setValue(V value) {
            this.value = value;
        }

        /**
         * Gets the prefix associated with this node.
         *
         * @return the prefix
         */
        String getPrefix() {
            return prefix;
        }

        /**
         * Sets the prefix associated with this node.
         *
         * @param prefix the prefix
         */
        void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        /**
         * Gets the children of this node.
         *
         * @return the list of children
         */
        Collection<RadixTreeNode<V>> getChildren() {
            // Delayed creation of children to reduce memory cost
            if (children == null)
                children = new TreeSet<RadixTreeNode<V>>();
            return children;
        }

        /**
         * Whether or not this node has a value attached to it.
         *
         * @return whether or not this node has a value
         */
        boolean hasValue() {
            return hasValue;
        }

        /**
         * Sets whether or not this node has a value attached to it.
         *
         * @param hasValue <code>true</code> if this node will have a value,
         *                 <code>false</code> otherwise. If <code>false</code>,
         *                 {@link #getValue()} will return <code>null</code>
         *                 after this call.
         */
        void setHasValue(boolean hasValue) {
            this.hasValue = hasValue;
            if (!hasValue)
                this.value = null;
        }

        @Override
        public Iterator<RadixTreeNode<V>> iterator() {
            if (children == null) {
                return new Iterator<RadixTreeNode<V>>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public RadixTreeNode<V> next() {
                        return null;
                    }

                    @Override
                    public void remove() {
                        // Unimplemented
                    }
                };
            }

            return children.iterator();
        }

        @Override
        public int compareTo(RadixTreeNode<V> node) {
            return prefix.toString().compareTo(node.getPrefix().toString());
        }
    }


    /**
     * Finds the length of the largest prefix for two character sequences.
     *
     * @param a character sequence
     * @param b character sequence
     * @return the length of largest prefix of <code>a</code> and <code>b</code>
     * @throws IllegalArgumentException if either <code>a</code> or <code>b</code>
     *                                  is <code>null</code>
     */
    public static int largestPrefixLength(CharSequence a, CharSequence b) {
        int len = 0;
        for (int i = 0; i < Math.min(a.length(), b.length()); ++i) {
            if (a.charAt(i) != b.charAt(i))
                break;
            ++len;
        }
        return len;
    }


    /**
     * Prints a radix tree to <code>System.out</code>.
     *
     * @param tree the tree
     */
    public static <V extends Serializable> void dumpTree(RadixTree<V> tree) {
        dumpTree(tree.root, "");
    }

    /**
     * Prints a subtree to <code>System.out</code>.
     *
     * @param node         the subtree
     * @param outputPrefix prefix to be printed to output
     */
    static <V extends Serializable> void dumpTree(RadixTreeNode<V> node, String outputPrefix) {
        if (node.hasValue())
            System.out.format("%s{%s : %s}%n", outputPrefix, node.getPrefix(), node.getValue());
        else
            System.out.format("%s{%s}%n", outputPrefix, node.getPrefix(), node.getValue());

        for (RadixTreeNode<V> child : node)
            dumpTree(child, outputPrefix + "\t");
    }


    public static final String KEY_CANNOT_BE_NULL = "key cannot be null";
    public static final String KEYS_MUST_BE_STRING_INSTANCES = "keys must be String instances";

    /**
     * The root node in this tree
     */
    RadixTreeNode<V> root;

    /**
     * Default constructor.
     */
    public RadixTree() {
        this.root = new RadixTreeNode<V>("");
    }

    /**
     * Traverses this radix tree using the given visitor. Note that the tree
     * will be traversed in lexicographical order.
     *
     * @param visitor the visitor
     */
    public void visit(RadixTreeVisitor<V, ?> visitor) {
        visit(root, "", "", visitor);
    }

    /**
     * Traverses this radix tree using the given visitor. Only values with
     * the given prefix will be visited. Note that the tree will be traversed
     * in lexicographical order.
     *
     * @param visitor the visitor
     * @param prefix  the prefix used to restrict visitation
     */
    public void visit(RadixTreeVisitor<V, ?> visitor, String prefix) {
        visit(root, prefix, "", visitor);
    }

    /**
     * Visits the given node of this tree with the given prefix and visitor. Also,
     * recursively visits the left/right subtrees of this node.
     *
     * @param node    the node
     * @param prefix  the prefix
     * @param visitor the visitor
     */
    private void visit(RadixTreeNode<V> node, String prefixAllowed, String prefix, RadixTreeVisitor<V, ?> visitor) {
        if (node.hasValue() && prefix.startsWith(prefixAllowed))
            visitor.visit(prefix, node.getValue());

        for (RadixTreeNode<V> child : node) {
            final int prefixLen = prefix.length();
            final String newPrefix = prefix + child.getPrefix();
            if (prefixAllowed.length() <= prefixLen
                    || newPrefix.length() <= prefixLen
                    || newPrefix.charAt(prefixLen) == prefixAllowed.charAt(prefixLen)) {
                visit(child, prefixAllowed, newPrefix, visitor);
            }
        }
    }

    @Override
    public void clear() {
        root.getChildren().clear();
    }

    @Override
    public boolean containsKey(final Object keyToCheck) {
        if (keyToCheck == null)
            throw new NullPointerException(KEY_CANNOT_BE_NULL);

        if (!(keyToCheck instanceof String))
            throw new ClassCastException(KEYS_MUST_BE_STRING_INSTANCES);

        RadixTreeVisitor<V, Boolean> visitor = new RadixTreeVisitor<V, Boolean>() {
            boolean found = false;

            @Override
            public void visit(String key, V value) {
                if (key.equals(keyToCheck))
                    found = true;
            }

            @Override
            public Boolean getResult() {
                return found;
            }
        };
        visit(visitor, (String) keyToCheck);
        return visitor.getResult();
    }

    @Override
    public boolean containsValue(final Object val) {
        RadixTreeVisitor<V, Boolean> visitor = new RadixTreeVisitor<V, Boolean>() {
            boolean found = false;

            @Override
            public void visit(String key, V value) {
                if (val == value || (value != null && value.equals(val)))
                    found = true;
            }

            @Override
            public Boolean getResult() {
                return found;
            }
        };
        visit(visitor);
        return visitor.getResult();
    }

    @Override
    public V get(final Object keyToCheck) {
        if (keyToCheck == null)
            throw new NullPointerException(KEY_CANNOT_BE_NULL);

        if (!(keyToCheck instanceof String))
            throw new ClassCastException(KEYS_MUST_BE_STRING_INSTANCES);

        RadixTreeVisitor<V, V> visitor = new RadixTreeVisitor<V, V>() {
            V result = null;

            @Override
            public void visit(String key, V value) {
                if (key.equals(keyToCheck))
                    result = value;
            }

            @Override
            public V getResult() {
                return result;
            }
        };
        visit(visitor, (String) keyToCheck);
        return visitor.getResult();
    }

    /**
     * Gets a list of entries whose associated keys have the given prefix.
     *
     * @param prefix the prefix to look for
     * @return the list of values
     * @throws NullPointerException if prefix is <code>null</code>
     */
    public List<Map.Entry<String, V>> getEntriesWithPrefix(String prefix) {
        RadixTreeVisitor<V, List<Map.Entry<String, V>>> visitor = new RadixTreeVisitor<V, List<Map.Entry<String, V>>>() {
            List<Map.Entry<String, V>> result = new ArrayList<Map.Entry<String, V>>();

            @Override
            public void visit(String key, V value) {
                result.add(new AbstractMap.SimpleEntry<String, V>(key, value));
            }

            @Override
            public List<Map.Entry<String, V>> getResult() {
                return result;
            }
        };
        visit(visitor, prefix);
        return visitor.getResult();
    }

    /**
     * Gets a list of values whose associated keys have the given prefix.
     *
     * @param prefix the prefix to look for
     * @return the list of values
     * @throws NullPointerException if prefix is <code>null</code>
     */
    public List<V> getValuesWithPrefix(String prefix) {
        if (prefix == null)
            throw new NullPointerException("prefix cannot be null");

        RadixTreeVisitor<V, List<V>> visitor = new RadixTreeVisitor<V, List<V>>() {
            List<V> result = new ArrayList<V>();

            @Override
            public void visit(String key, V value) {
                result.add(value);
            }

            @Override
            public List<V> getResult() {
                return result;
            }
        };
        visit(visitor, prefix);
        return visitor.getResult();
    }

    /**
     * Gets a list of keys with the given prefix.
     *
     * @param prefix the prefix to look for
     * @return the list of prefixes
     * @throws NullPointerException if prefix is <code>null</code>
     */
    public List<String> getKeysWithPrefix(String prefix) {
        if (prefix == null)
            throw new NullPointerException("prefix cannot be null");

        RadixTreeVisitor<V, List<String>> visitor = new RadixTreeVisitor<V, List<String>>() {
            List<String> result = new ArrayList<String>();

            @Override
            public void visit(String key, V value) {
                result.add(key);
            }

            @Override
            public List<String> getResult() {
                return result;
            }
        };
        visit(visitor, prefix);
        return visitor.getResult();
    }

    @Override
    public boolean isEmpty() {
        return root.getChildren().isEmpty();
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> map) {
        for (Map.Entry<? extends String, ? extends V> entry : map.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public int size() {
        RadixTreeVisitor<V, Integer> visitor = new RadixTreeVisitor<V, Integer>() {
            int count = 0;

            @Override
            public void visit(String key, V value) {
                ++count;
            }

            @Override
            public Integer getResult() {
                return count;
            }
        };
        visit(visitor);
        return visitor.getResult();
    }

    @Override
    public Set<Map.Entry<String, V>> entrySet() {
        // TODO documentation Of Map.entrySet() specifies that this is a view of
        //      the entries, and modifications to this collection should be
        //      reflected in the parent structure
        //
        RadixTreeVisitor<V, Set<Map.Entry<String, V>>> visitor = new RadixTreeVisitor<V, Set<Map.Entry<String, V>>>() {
            Set<Map.Entry<String, V>> result = new HashSet<Map.Entry<String, V>>();

            @Override
            public void visit(String key, V value) {
                result.add(new AbstractMap.SimpleEntry<String, V>(key, value));
            }

            @Override
            public Set<Map.Entry<String, V>> getResult() {
                return result;
            }
        };
        visit(visitor);
        return visitor.getResult();
    }

    @Override
    public Set<String> keySet() {
        // TODO documentation Of Map.keySet() specifies that this is a view of
        //      the keys, and modifications to this collection should be
        //      reflected in the parent structure
        //
        RadixTreeVisitor<V, Set<String>> visitor = new RadixTreeVisitor<V, Set<String>>() {
            Set<String> result = new TreeSet<String>();

            @Override
            public void visit(String key, V value) {
                result.add(key);
            }

            @Override
            public Set<String> getResult() {
                return result;
            }
        };
        visit(visitor);
        return visitor.getResult();
    }

    @Override
    public Collection<V> values() {
        // TODO documentation Of Map.values() specifies that this is a view of
        //      the values, and modifications to this collection should be
        //      reflected in the parent structure
        //
        RadixTreeVisitor<V, Collection<V>> visitor = new RadixTreeVisitor<V, Collection<V>>() {
            Collection<V> result = new ArrayList<V>();

            @Override
            public void visit(String key, V value) {
                result.add(value);
            }

            @Override
            public Collection<V> getResult() {
                return result;
            }
        };
        visit(visitor);
        return visitor.getResult();
    }

    @Override
    public V put(String key, V value) {
        if (key == null)
            throw new NullPointerException(KEY_CANNOT_BE_NULL);

        return put(key, value, root);
    }

    /**
     * Remove the value with the given key from the subtree rooted at the
     * given node.
     *
     * @param key  the key
     * @param node the node to start searching from
     * @return the old value associated with the given key, or <code>null</code>
     * if there was no mapping for <code>key</code>
     */
    private V put(String key, V value, RadixTreeNode<V> node) {
        V ret = null;

        final int largestPrefix = largestPrefixLength(key, node.getPrefix());
        if (largestPrefix == node.getPrefix().length() && largestPrefix == key.length()) {
            // Found a node with an exact match
            ret = node.getValue();
            node.setValue(value);
            node.setHasValue(true);
        } else if (largestPrefix == 0
                || (largestPrefix < key.length() && largestPrefix >= node.getPrefix().length())) {
            // Key is bigger than the prefix located at this node, so we need to see if
            // there's a child that can possibly share a prefix, and if not, we just add
            // a new node to this node
            final String leftoverKey = key.substring(largestPrefix);

            boolean found = false;
            for (RadixTreeNode<V> child : node) {
                if (child.getPrefix().charAt(0) == leftoverKey.charAt(0)) {
                    found = true;
                    ret = put(leftoverKey, value, child);
                    break;
                }
            }

            if (!found) {
                // No child exists with any prefix of the given key, so add a new one
                RadixTreeNode<V> n = new RadixTreeNode<V>(leftoverKey, value);
                node.getChildren().add(n);
            }
        } else if (largestPrefix < node.getPrefix().length()) {
            // Key and node.getPrefix() share a prefix, so split node
            final String leftoverPrefix = node.getPrefix().substring(largestPrefix);
            final RadixTreeNode<V> n = new RadixTreeNode<V>(leftoverPrefix, node.getValue());
            n.setHasValue(node.hasValue());
            n.getChildren().addAll(node.getChildren());

            node.setPrefix(node.getPrefix().substring(0, largestPrefix));
            node.getChildren().clear();
            node.getChildren().add(n);

            if (largestPrefix == key.length()) {
                // The largest prefix is equal to the key, so set this node's value
                ret = node.getValue();
                node.setValue(value);
                node.setHasValue(true);
            } else {
                // There's a leftover suffix on the key, so add another child 
                final String leftoverKey = key.substring(largestPrefix);
                final RadixTreeNode<V> keyNode = new RadixTreeNode<V>(leftoverKey, value);
                node.getChildren().add(keyNode);
                node.setHasValue(false);
            }
        } else {
            // node.getPrefix() is a prefix of key, so add as child
            final String leftoverKey = key.substring(largestPrefix);
            final RadixTreeNode<V> n = new RadixTreeNode<V>(leftoverKey, value);
            node.getChildren().add(n);
        }

        return ret;
    }

    @Override
    public V remove(Object key) {
        if (key == null)
            throw new NullPointerException(KEY_CANNOT_BE_NULL);

        if (!(key instanceof String))
            throw new ClassCastException(KEYS_MUST_BE_STRING_INSTANCES);

        // Special case for removing empty string (root node)
        final String sKey = (String) key;
        if (sKey.equals("")) {
            final V value = root.getValue();
            root.setHasValue(false);
            return value;
        }

        return remove(sKey, root);
    }

    /**
     * Remove the value with the given key from the subtree rooted at the
     * given node.
     *
     * @param key  the key
     * @param node the node to start searching from
     * @return the value associated with the given key, or <code>null</code>
     * if there was no mapping for <code>key</code>
     */
    private V remove(String key, RadixTreeNode<V> node) {
        V ret = null;
        final Iterator<RadixTreeNode<V>> iter = node.getChildren().iterator();
        while (iter.hasNext()) {
            final RadixTreeNode<V> child = iter.next();
            final int largestPrefix = largestPrefixLength(key, child.getPrefix());
            if (largestPrefix == child.getPrefix().length() && largestPrefix == key.length()) {
                // Found our match, remove the value from this node
                if (child.getChildren().isEmpty()) {
                    // Leaf node, simply remove from parent
                    ret = child.getValue();
                    iter.remove();
                    break;
                } else if (child.hasValue()) {
                    // Internal node
                    ret = child.getValue();
                    child.setHasValue(false);

                    if (child.getChildren().size() == 1) {
                        // The subchild's prefix can be reused, with a little modification
                        final RadixTreeNode<V> subchild = child.getChildren().iterator().next();
                        final String newPrefix = child.getPrefix() + subchild.getPrefix();

                        // Merge child node with its single child
                        child.setValue(subchild.getValue());
                        child.setHasValue(subchild.hasValue());
                        child.setPrefix(newPrefix);
                        child.getChildren().clear();
                    }

                    break;
                }
            } else if (largestPrefix > 0 && largestPrefix < key.length()) {

                /*
                 * Continue down subtree of child
                 */
                final String leftoverKey = key.substring(largestPrefix);
                ret = remove(leftoverKey, child);

                break;
            }
        }

        return ret;
    }
}
