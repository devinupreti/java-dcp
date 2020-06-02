
/*
PROBLEM:
A strobogrammatic number is a positive number that appears the same after being rotated 180 degrees.
Create a program that finds all strobogrammatic numbers with N digits.

EXAMPLE:
For example, 16891 is strobogrammatic.

LOGIC:
Valid numbers -> {0,1,6,8,9}
for each number, only 1 pair (same for 0,1,8 and opp for 6,9)

SOLUTION:
1. Recursion : T : O(5^(n/2)) | S : O(n/2)



 */

import java.util.ArrayList;
import java.util.List;

public class Strobogrammatic {

    public static void test() {
        List<Integer [] > output = findAllStrobogrammaticNumbers(3);

        System.out.println("total : "+ output.size());
        for (Integer [] arr : output)
        {
            for (int x : arr) { System.out.print(x + " "); }
            System.out.println();
        }
    }

    public static List<Integer [] > findAllStrobogrammaticNumbers(int length) {
        List<Integer [] > solution = new ArrayList<Integer[]>();
        Integer [] currentBuild = new Integer[length];

        if (length % 2 != 0)
        {
            // odd
            int middle = length / 2;
            for (int x : new int [] {0,1,8})
            {
                currentBuild[middle] = x;
                buildSolution(currentBuild, length, 0, solution);
            }
        }
        else { buildSolution(currentBuild, length, 0, solution); }
        return solution;
    }

    private static void buildSolution(Integer[] currentBuild, int length, int index, List<Integer [] > solution) {
        if (index == length / 2 )
        {
            Integer [] newBuild = currentBuild.clone();
            solution.add(newBuild);
            return;
        }

        for (int x : new int [] {0,1,8})
        {
            currentBuild[index] = x;
            currentBuild[(length - 1) - index] = x;
            buildSolution(currentBuild, length, index + 1, solution);
        }

        currentBuild[index] = 6;
        currentBuild[length - 1 - index] = 9;
        buildSolution(currentBuild, length, index + 1, solution);
        currentBuild[index] = 9;
        currentBuild[length - 1 - index] = 6;
        buildSolution(currentBuild, length, index + 1, solution);
    }
}
