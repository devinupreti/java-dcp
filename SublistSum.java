/*
PROBLEM:
Given a list of numbers L
implement a method sum(i, j) which returns the sum from the sublist L[i:j] (including i, excluding j).
You can assume that you can do some pre-processing.
sum() should be optimized over the pre-processing step.

EXAMPLE:
given L = [1, 2, 3, 4, 5], sum(1, 3) should return sum([2, 3]), which is 5.

LOGIC:
Iterative : O(n), O(1)

Using mathematical equation to optimize pre-processing
a + b + c = totalSum
sum(i,j) = totalSum - sum[0 + ... + i - 1] - sum[j + ... + len - 1]

SumTillIndex[array]
sum(i,j) = totalSum - SumTillIndex[i - 1] - (totalSum - sumTill[j-1] )

[ [a] , [b], [c] ]
b (contains i) = t - a - c
a = sumTill[i - 1]
c (contains j) = totalSum - sumTill[j-1]

SOLUTION:
1. Preprocessing : O(n), O(n)
   Sum : O(1), O(1)

2. Iterative : O(n), O(1)
 */

public class SublistSum {

    public static void test()
    {
        int [] input = {1, 2, 3, 4, 5};
        int output = sum(input, preprocessing(input), 1, 3);
        assert(output == 5);
    }

    public static int[] preprocessing(int numbers[])
    {
        int[] sums = new int[numbers.length];
        if (numbers.length < 1) { return sums; }

        int currentSum = 0;
        for (int i = 0; i < numbers.length; i++)
        {
            currentSum += numbers[i];
            sums[i] = currentSum;
        }
        return sums;
    }

    public static int sum(int [] numbers, int [] sums, int i, int j)
    {
        return sums[sums.length - 1] - sums[i - 1] - (sums[sums.length - 1] - sums[j-1]);
    }

}
