/*
PROBLEM:
Given an array of integers, find the first missing positive integer in linear time and constant space.
In other words, find the lowest positive integer that does not exist in the array.
The array can contain duplicates and negative numbers as well.

EXAMPLE:
Input : [3, 4, -1, 1]
Output : 2

Input: [1, 2, 0]
Output : 3

LOGIC:
We need to find from 0 to n, where n doesn't exist in the array
Assume the array only consists of positive integers (we can deal with negative by partitioning)
at each index i:
    let elem = nums[i]
    if (elem > length) -> ignore | since first missing can't be greater than length
    else:
        nums[elem - 1] =  - nums[elem - 1] | indicator that elem i+1 exists for i

on second traversal just find the first positive


Solutions:
1. Brute Force & find each positive number | Time : O(n^2) | Space : O(1)
2. HashSet | Time : O(n) | Space : O(n)
3. Sort and start comparing from 1 (binary search) | Time : O(nlogn) | Space : O(1)
4. Index mapping | Time : O(n) | Space : O(1)

*/

public class FirstMissingInt {

    public static void test() {
        int[] nums = {3, 4, -1, 1};
        int[] nums2 = {1,2,0};

        System.out.println(linearTime(nums));
    }

    // puts positive elements in the front and negative in the back
    private static int segregate(int[] nums)
    {
        int lastPositiveIndex = nums.length - 1;
        while (nums[lastPositiveIndex] <= 0)
        { lastPositiveIndex -= 1; }

        // go through array and swap any negative to end
        for (int i = 0; i < nums.length; i++)
        {
            if (i >= lastPositiveIndex) { break; }
            while (nums[i] <= 0) // elem is negative
            {
                int temp = nums[i];
                nums[i] = nums[lastPositiveIndex];
                nums[lastPositiveIndex] = temp;
                lastPositiveIndex -= 1;
            }
        }

        while (nums[lastPositiveIndex] <= 0)
        { lastPositiveIndex -= 1; }

        return lastPositiveIndex;
    }

    // main function : t : O(n) | s: O(1)
    public static int linearTime(int [] nums)
    {
        if (nums.length < 1) { return -1; } // case not applicable
        // segregate the positive and negative
        int lastPositiveIndex = segregate(nums);

        for(int i = 0; i <= lastPositiveIndex; i++)
        {
            int elem = Math.abs(nums[i]);
            if (elem <= lastPositiveIndex + 1)
            {
                nums[elem - 1] = - nums[elem - 1];
            }
        }

        for (int i = 0; i <= lastPositiveIndex; i++)
        {
            if (nums[i] > 0) { return i + 1; }
        }
        return lastPositiveIndex + 2;
    }



}


