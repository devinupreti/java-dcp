
/*
PROBLEM:
Given a list of strictly positive integers, partition the list into 3 contiguous partitions which each sum up to the same value.
If not possible, return null.

EXAMPLE:
Input - [3, 5, 8, 0, 8]
Output -  [[3, 5], [8, 0], [8]] (Which each add up to 8)

LOGIC:
Try to find where first parition ends and last partition begins
check if (middleSum == currentSum) else -> first++; end--

Better:
sum1 + sum2 + sum3 = TotalSum
3s = totalSum
first index end -> s
second index end -> 2s
third index end -> end

SOLUTION:
1. Two pointer - worst case T : O(n^2) | average case T : O(n), S : O(1)
2. Mathematical Equation & Search -> T : O(n) | S : O(1)

*/

import java.util.Arrays;
import java.util.stream.IntStream;

public class PartitionEqualSum {

    public static void printSolution(int [] [] partitions)
    {
        if( partitions == null) { System.out.println("null"); return; }

        System.out.print("[ ");
        for (int [] p : partitions)
        {
            System.out.print("[ ");
            for (int i = 0; i < p.length; ++i) {
                System.out.print(p[i] + " ");
                if (i != p.length - 1) { System.out.print(", ");}
            }
            System.out.print("] ");
        }
        System.out.println("]");
    }

    public static void test()
    {
        int [] test1 = {3, 5, 8, 0, 8};
        int [][] output1 = findPartitionsWithEqualSum(test1);
        printSolution(output1);

        int [] test2 = {1, 2, 3, 4, 1, 2, 3, 4};
        int [][] output2 = findPartitionsWithEqualSum(test2);
        printSolution(output2);

        int [] test3 = {2,2,2,2,8,2,2,2,2};
        int [][] output3 = findPartitionsWithEqualSum(test3);
        printSolution(output3);
    }

    public static int [][] findPartitionsWithEqualSum( int [] numbers)
    {
        int [][] solution = new int[3][];

        int first = 0;
        int last = numbers.length - 1;

        int currentSumOne = numbers[first];
        int currentSumTwo = numbers[last];

        while (first < last - 1)
        {
            if (currentSumOne < currentSumTwo) {
                first += 1;
                currentSumOne += numbers[first];
            }
            else if (currentSumOne > currentSumTwo) {
                last -= 1;
                currentSumTwo += numbers[last];
            }
            else
            {
                int middleSum = getMiddleSum(numbers, first, last);
                if (currentSumOne == middleSum) {
                    solution[0] = Arrays.copyOfRange(numbers, 0, first+1);
                    solution[1] = Arrays.copyOfRange(numbers, first+1, last);
                    solution[2] = Arrays.copyOfRange(numbers, last, numbers.length);
                    return solution;
                }
                else
                {
                    first += 1;
                    currentSumOne += numbers[first];
                    last -= 1;
                    currentSumTwo += numbers[last];
                }
            }
        }
        return null;
    }

    public static int getMiddleSum(int [] numbers, int first, int last)
    {
        return IntStream.range(first+1,last).map(i -> numbers[i]).sum();
    }
}
