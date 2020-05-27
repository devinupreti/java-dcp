/*
PROBLEM:
You are given a list of jobs to be done, where each job is represented by a start time and end time.
Two jobs are compatible if they don't overlap. there is no guarantee that jobs will be sorted.
Find the largest subset of compatible jobs.

EXAMPLE:
Input - [(0, 6), (1, 4), (3, 5), (3, 8), (4, 7), (5, 9), (6, 10), (8, 11)]
Output - [(1, 4), (4, 7), (8, 11)]

Follow up:
Weighted -> DP (1D array)

LOGIC:
Sort by end times
if a ends before b, anything that doesn't overlap with b will not overlap with a but not vice versa

SOLUTION:
1. Sort and greedy - T : O(nlogn), S : O(1)

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CompatibleJobs {

    public static void test()
    {
        Integer[][] jobs = {{0, 6}, {1, 4}, {3, 5}, {3, 8}, {4, 7}, {5, 9}, {6, 10}, {8, 11}};
        ArrayList<Integer[]> output = findCompatibleJobs(jobs);

        // [(1, 4), (4, 7), (8, 11)]
        for (Integer[] j : output)
        { System.out.println(Arrays.toString(j)); }
    }

    public static ArrayList<Integer []> findCompatibleJobs(Integer [][] jobs)
    {
        ArrayList<Integer [] > solution = new ArrayList<Integer [] >();
        if (jobs.length == 0) { return solution; }

        Arrays.sort(jobs, Comparator.comparingInt(s -> s[1]));

        int i = 0;
        solution.add(jobs[i]);

        for (int j = 1; j < jobs.length; j++)
        {
            if(jobs[j][0] >= jobs[i][1]) {
                // not overlapping
                i = j;
                solution.add(jobs[i]);
            }
        }
        return solution;
    }
}
