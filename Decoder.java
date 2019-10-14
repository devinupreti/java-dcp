/*
PROBLEM:
Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
You can assume that the messages are decodable. For example, '001' is not allowed.

EXAMPLE:
the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

LOGIC:
a single digit number can be decoded in 1 way
a two digit number < 27 can be decoded in 2 ways

111 -> 1 + "11"
    -> 11 + "1"
traverse 1 and traverse 2 digits

Solution:
1. Recursion | t : O(2^n) | s : O(n)
2. Dynamic Programming | t : O(n) | s : O(n) / O(1)
*/


import java.util.ArrayList;
import java.util.HashMap;

public class Decoder {

    public static void test()
    {
        System.out.println(countDP("1234"));
    }

    // time: O(n) | space : O(n) - can be reduced to O(1)
    static int countDP(String message)
    {
        char [] digits = message.toCharArray();
        int n = message.length();

        int count[] = new int[n + 1];
        count[0] = 1;
        count[1] = 1;

        for (int i = 2; i <= n; i++)
        {
            count[i] = 0;

            // If the last digit is not 0, then last digit must add to the number of words
            if (digits[i - 1] > '0')
                count[i] = count[i - 1];

            // If second last digit is smaller than 2 and last digit is smaller than 7, then last two digits form a valid character
            if (digits[i - 2] == '1' || (digits[i - 2] == '2' && digits[i - 1] < '7'))
                count[i] += count[i - 2];
        }
        return count[n];
    }


    public static void helper(int start, String decoded ,String message, ArrayList<String> resultStrings, HashMap<String, String> mapping)
    {
        if (message.length() == start) { resultStrings.add(decoded); }

        if (start < message.length())
        {
            String check = String.valueOf(message.charAt(start));
            if (mapping.containsKey(check)) {
                helper(start + 1, decoded + mapping.get(check), message, resultStrings, mapping);
            }
        }

        if (start < message.length() - 1) {
            String check = message.substring(start, start + 2);
            if (mapping.containsKey(check)) {
                helper(start + 2, decoded + mapping.get(check), message, resultStrings, mapping);
            }
        }

    }

    // time : O(2^n) | space : O(n)
    // exponential time complexity
    public static int decode(String message)
    {
        HashMap<String, String> mapping = new HashMap<>();
        for (int i = 1; i < 27; i++)
        {
            int a = 64 + i;
            char c = (char)a;
            String m = String.valueOf(Character.toLowerCase(c));
            mapping.put(String.valueOf(i), m);
        }

        ArrayList<String> decoded = new ArrayList<String>();
        helper(0, "" , message , decoded, mapping);
        return decoded.size();
    }

}
