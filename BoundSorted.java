/*
PROBLEM:
Given an array of integers out of order, determine the bounds of the smallest window that must be sorted in order for the entire array to be sorted

Example:
Input - [3,7,5,6,9]
Output - [1,3] (indexes)

Logic:
[a, b, c, d, e]
1. for the array to be sorted all nums[j] > nums[i] where j > i & vice versa
2. the min and max from [unsorted] need to be greater and less than all the elems from left and right side of [unsorted]
   and also left and right side need to be sorted

Selection Sort : find min elem and swap at each iteration

Solutions:
1. Compare every every two pair to find range | Brute Force | Time : O(n^2) | Space : O(1)
2. Sort and Compare | Time : O(nlogn) | Space : O(n)
3. Stack of Indexes | Time : O(n) | Space : O(n)
4. Even better - https://leetcode.com/articles/shortest-unsorted-continous-subarray/
*/

import java.util.Arrays;
import java.util.Stack;

public class BoundSorted
{
    public static void test()
    {
        int [] nums = {3,7,5,6,9};
        int [] solution = useStack(nums);
        for (int x : solution) { System.out.print(x + " "); }
    }

    // t : O(n^2) | s : O(1)
    // finds all elements that are not in place
    public static int [] bruteForce (int [] nums)
    {
        int left = nums.length - 1;
        int right = 0;
        for (int i = 0; i < nums.length - 1; i++)
        {
            for  (int j = 0; j < nums.length; j++)
            {
                if (nums[i] > nums[j])
                {
                    // these two are not in sorted order
                    left = Math.min(i, left);
                    right = Math.max(j, right);
                }
            }
        }
        return right - left < 0 ? new int[] {0, 0} : new int[] {left, right};
    }

    // t : O(nlogn) | s: O(n)
    public static int[] sortAndCompare(int[] nums)
    {
        int [] sorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
        { sorted[i] = nums[i]; }
        Arrays.sort(sorted);

        int index = 0;
        int left = nums.length - 1;
        while(index < nums.length)
        {
            if (nums[index] != sorted[index])
            {
                left = index;
                break;
            }
            index += 1;
        }
        index = nums.length - 1;
        int right = 0;
        while(index >= 0)
        {
            if (nums[index] != sorted[index])
            {
                right = index;
                break;
            }
            index -= 1;
        }
        return right - left < 0 ? new int[] {0, 0} : new int[] {left, right};
    }

    // t : O(n) | s : O(n)
    public static int[] useStack(int [] nums)
    {
        int left = nums.length - 1;
        int right = 0;
        Stack<Integer> stack = new Stack<Integer> ();

        // finding left
        for(int i = 0; i < nums.length; i++)
        {
            // subsequent elements need to be larger if sorted
            while ( !stack.isEmpty() && nums[i] < nums[stack.peek()])
            {
                left = Math.min(left, stack.pop() );
            }
            stack.push(i);
        }

        stack.clear();
        // finding right
        for(int i = nums.length - 1; i >= 0; i--)
        {
            // subsequent elements need to be larger if sorted
            while ( !stack.isEmpty() && nums[i] > nums[stack.peek()])
            {
                right = Math.max(right, stack.pop() );
            }
            stack.push(i);
        }
        return right - left < 0 ? new int[] {0, 0} : new int[] {left, right};
    }

}
