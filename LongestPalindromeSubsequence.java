/*
PROBLEM:
Given a string, return the length of the longest palindromic subsequence in the string.
A subsequence of a string does not have to be contiguous!

EXAMPLE:
Input - MAPTPTMTPA
Output - 7
since the longest palindromic subsequence in the string is APTMTPA

CONSTRAINTS:
Your algorithm should run in O(n^2) time and space.

SOLUTIONS:
1. Recursive -> T : O(2^n) , S : O(n)
2. Memoization/DP -> T : O(n^2), S : O(n^2)

*/

public class LongestPalindromeSubsequence
{
    public static void test()
    {
        int output = findLongestPalindromeSubsequence("MAPTPTMTPA");
        assert(output == 7);
    }
    
    // rather than thinking about filling the array top-down
    // think about filling it bottom up
    // time : O(n^2) | space : O(n^2)
    public static int findLongestPalindromeSubsequence(String s)
    {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
