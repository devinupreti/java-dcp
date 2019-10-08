/*
Given a list of numbers and a number k, return any two numbers from the list add up to k.

Input : [10, 15, 3, 7] and k of 17
Output : [7, 10] (sorted order)

Logic:
a + b = t
a = t - b

Solution:
1. Check every num for every num | Time : O(n^2) | Space: O(1)
2. Sort and Binary search for every num | Time : O(n logn) | Space : O(1)
3. Hash Set | Time  : O(n) | Space : O(n)
4. Sort and Two Pointer | Time : O(nlogn) | Space : (1)
*/

import java.util.Arrays;
import java.util.HashSet;

public class TwoSum {

    public static void test1()
    {
        int [] nums = {10, 15, 3, 7};
        int [] solution = useSet(nums, 17);

        for (int a : solution) { System.out.print(a + " "); }
    }

    public static int[] bruteForce(int [] nums, int targetSum)
    {
        for (int i = 0; i < nums.length - 1; i++)
        {
            for (int j = i + 1; j < nums.length; j++)
            {
                if (nums[i] + nums[j] == targetSum)
                {
                    if (nums[i] < nums[j]) { return new int [] {nums[i], nums[j]}; }
                    else { return new int[] {nums[j], nums[i]}; }
                }
            }
        }
        return new int[] {};
    }

    public static int[] binarySearch(int [] nums, int targetSum)
    {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++)
        {
            int find = targetSum - nums[i];
            // binary search
            int left = i + 1;
            int right = nums.length - 1;
            while(left <= right) {
                int middle = (left + right) / 2;
                if (nums[middle] < find) {
                    left = middle + 1;
                } else if (nums[middle] > find) {
                    right = middle - 1;
                } else {
                    return new int[]{nums[i], nums[middle]};
                }
            }
        }
        return new int[] {};
    }

    public static int[] twoPointer(int [] nums, int targetSum)
    {
        Arrays.sort(nums);
        // Two pointer
        int first = 0;
        int last = nums.length - 1;
        while (first < last)
        {
            int currentSum = nums[first] + nums[last];
            if (currentSum == targetSum)
            { return new int[] {nums[first], nums[last]}; }
            else if (currentSum < targetSum)
            { first += 1; }
            else
            { last -=  1; }
        }
        return new int[] {};
    }

    public static int[] useSet(int [] nums, int targetSum)
    {
        HashSet<Integer> numberSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
        {
            int find = targetSum - nums[i];
            if (numberSet.contains(find))
            {
                if (nums[i] < find) { return new int [] {nums[i], find}; }
                else { return new int[] {find, nums[i]}; }
            }
            numberSet.add(nums[i]);
        }
        return new int[] {};
    } 
}
