/*
PROBLEM:
There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time.
Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.

EXAMPLE:
For example, if N is 4, then there are 5 unique ways:

1, 1, 1, 1
2, 1, 1
1, 2, 1
1, 1, 2
2, 2
What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive integers X?
For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.

LOGIC:
Recursion - O(2^n)
Dynamic programming - O(n) -> ways to reach ith step = ways(i - 1) + ways(i - 2)

if given set of steps
for each valid step : (i - step) >= 0
    - ways[i] += ways[i - step];
ways[0] = 1
 */

public class Stairs {

    public static void test()
    {
        System.out.println(findWays(4));
        System.out.println(findWays(5));
        System.out.println(findWaysSet(new int[] {1, 2} ,4));
        System.out.println(findWaysSet(new int[] {1, 2, 3} ,3));
    }

    public static int findWaysSet(int[] steps, int stairs)
    {
        int [] ways = new int[stairs + 1];
        for (int i = 0; i < ways.length; i++)
        { ways[i] = 0; }
        ways[0] = 1;

        for (int i = 1; i < ways.length; i++)
        {
            for (int step : steps)
            {
                if (i - step >= 0 )
                {
                    ways[i] += ways[i - step];
                }
            }
        }
        return ways[stairs];
    }

    public static int findWays(int stairs)
    {
        if (stairs < 1)  { return 0; }
        if (stairs == 1) { return 1; }
        if (stairs == 2) { return 2; }

        // baseCases
        int prev = 2;
        int second_prev = 1;
        int current  = 0;
        for(int i = 2; i < stairs; i++)
        {
            current = prev + second_prev;
            second_prev = prev;
            prev = current;
        }
        return current;
    }

}
