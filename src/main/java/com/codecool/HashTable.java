package com.codecool;

/**
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 * Implement your hash table here.  You are required to use the separate
 * chaining strategy that we discussed in lecture, meaning that collisions
 * are resolved by having each cell in the table be a linked list of all of
 * the strings that hashed to that cell.
 */

public class HashTable
{
    private final StringHasher hasher;
    private int[] hashes;
    private int index = 0;
    /**
     * The constructor is given a table size (i.e. how big to make the array)
     * and a StringHasher, which is used to hash the strings.
     *
     * @param tableSize number of elements in the hash array
     *        hasher    Object that creates the hash code for a string
     * @see StringHasher
     */
    public HashTable(int tableSize, StringHasher hasher)
    {
        this.hashes = new int[tableSize];
        this.hasher = hasher;
    }


    /**
     * Takes a string and adds it to the hash table, if it's not already
     * in the hash table.  If it is, this method has no effect.
     *
     * @param word String to add
     */
    public void add(String word)
    {
        if (!lookup(word)) {
            hashes[index++] = hasher.hash(word);
        }
    }


    /**
     * Takes a string and returns true if that string appears in the
     * hash table, false otherwise.
     *
     * @param word String to look up
     */
    public boolean lookup(String word)
    {
        int wordHashed = hasher.hash(word);
        for (int hash : hashes) {
            if (hash == wordHashed) {
                return true;
            }
        }
        return false;
    }


    /**
     * Takes a string and removes it from the hash table, if it
     * appears in the hash table.  If it doesn't, this method has no effect.
     *
     * @param word String to remove
     */
    public void remove(String word)
    {
        // [10, 20, 30, 40, 50]
        // table[1] = 50
        // [10, 50, 30, 40, 0]
        int wordHashed = hasher.hash(word);
        for (int i = 0; i < hashes.length; i++) {
            if (hashes[i] == wordHashed) {
                hashes[i] = hashes[index--];
            }
        }
    }
}