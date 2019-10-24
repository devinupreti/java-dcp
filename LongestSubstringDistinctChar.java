/*
PROBLEM:
Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.

EXAMPLE:
Given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".

Solutions:
1. brute force
O(n^2) - check all possibilities
       - when k = 2 | compare max


2. maintain a hashMap and sliding window
   O(n) | O(n)
*/

import java.util.HashMap;

public class LongestSubstringDistinctChar {
    public static void test()
    {
        System.out.println(findLongest(2, "abcba"));
        System.out.println(findLongest(3, "aabbcc"));
    }

    public static int findLongest(int k, String s)
    {
        if (k < 2) { return k; }
        if (s.length() < 2) { return 0; }

        // make Char hashSet
        HashMap<Character, Integer> set = new HashMap<Character, Integer>();

        // add 0 and 1st char
        int first = 0;
        int last = 2;

        set.put(s.charAt(0), 1);
        if (s.charAt(0) != s.charAt(1)) { set.put(s.charAt(1), 1); }
        else { set.put(s.charAt(1), 2); }

        int maxLength = (set.size() == k) ? 2 : 0;

        // sliding window
        while (first <= last && last < s.length())
        {
            if (set.containsKey(s.charAt(last)))
            { set.put(s.charAt(last), set.get(s.charAt(last)) + 1 ); }
            else { set.put(s.charAt(last), 1);}


            while (set.size() > k)
            {
                if (set.get(s.charAt(first)) == 1)
                { set.remove(s.charAt(first)); }
                else { set.put(s.charAt(first), set.get(s.charAt(first)) - 1 );}
                first += 1;
            }


            if(set.size() == k)
            {
                if (last - first + 1 > maxLength)
                { maxLength = last - first + 1; }
            }
            last +=1;
        }
        return maxLength;
    }

}
