/*
PROBLEM:
Given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.

EXAMPLE:
Given the query string "de" and the set of strings [dog, deer, deal], return [deer, deal]

LOGIC:
Prefix Tree -  if not { a : -> {}, b : -> {}, ... }
Entry - O(n) for word containing n chars | Total : m words -> O(nm)
Retrieval - O(n) for given string containing n chars
Entry is more costly than retrieval

Brute Force :
check every candidate if startsWith - "de"
1 check - O(m) | m - chars in checkString
n checks - O(mn) | n - words, m - chars in checkString

SOLUTION:
1. Brute Force | retrieval - t : O(mn) | s: O(1)
2. Trie | retrieval - t : O(n) | s: O(mn)
*/

import java.util.ArrayList;
import java.util.HashMap;

/*
functions
    - insert(String) - O(n)
    - contains(String) - O(n)
*/
class PrefixNode
{
    HashMap<Character, PrefixNode> chars;
    boolean isWord = false;

    PrefixNode() { chars = new HashMap<Character, PrefixNode>(); }

    public void insert(String word, int index)
    {
        if (index >= word.length())
        {
            this.isWord = true;
            return;
        }

        Character c = word.charAt(index);
        if (!this.chars.containsKey(c))
        { this.chars.put(c, new PrefixNode()); }

        PrefixNode next = this.chars.get(c);
        next.insert(word, index + 1);
    }

    public boolean contains(String word)
    {
        PrefixNode currentNode = this;
        for (int i = 0; i < word.length(); i++)
        {
            Character currentChar = word.charAt(i);
            if (currentNode.chars.containsKey(currentChar))
            {
                currentNode = currentNode.chars.get(currentChar);
                if (i == word.length() - 1) { return currentNode.isWord; }
            }
            else { return false; }
        }
        return false;
    }

    public void traverseAll(String word, PrefixNode node, ArrayList<String> havePrefix)
    {
        if (node.isWord)
        { havePrefix.add(word); }

        for (Character currentChar : node.chars.keySet())
        {
            PrefixNode currentNode = node.chars.get(currentChar);
            traverseAll(word + String.valueOf(currentChar), currentNode, havePrefix);
        }
    }

    public void addWithPrefix(String checkString, ArrayList<String> havePrefix)
    {
        PrefixNode lastNode = this;

        for (int i = 0; i < checkString.length(); i++)
        {
            Character currentChar = checkString.charAt(i);

            if (lastNode.chars.containsKey(currentChar))
            {
                lastNode = lastNode.chars.get(currentChar);
            }
            else { return; }
        }
        traverseAll(checkString, lastNode, havePrefix);
        return;
    }

}


public class AutoComplete
{
    public static void test()
    {
        System.out.println(bruteForce(new String [] { "dog", "deer", "deal"} , "de"));
        System.out.println(usingPrefixTree(new String [] { "dog", "deer", "deal"} , "de"));
    }

    public static ArrayList<String> usingPrefixTree(String [] words, String checkString)
    {
        PrefixNode root = new PrefixNode();
        for (String word : words)
        {
            root.insert(word, 0);
        }

        ArrayList<String> havePrefix = new ArrayList<String >();

        root.addWithPrefix(checkString, havePrefix);
        return havePrefix;
    }

    public static ArrayList<String> bruteForce(String [] words, String checkString)
    {
        ArrayList<String> havePrefix = new ArrayList<String >();
        for (int i = 0; i < words.length; i++)
        {
            if(words[i].startsWith(checkString)) { havePrefix.add(words[i]); }
        }
        return havePrefix;
    }

}
