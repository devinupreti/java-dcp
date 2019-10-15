/*
PROBLEM:
Given a list of integers, write a function that returns the largest sum of non-adjacent numbers.
Numbers can be 0 or negative.

EXAMPLE:
[2, 4, 6, 2, 5] should return 13
[5, 1, 1, 5] should return 10
[10, -1, -1, -1] should return 10

LOGIC:
At each step we can choose (n-2) or not
DP problem

SOLUTION:
1. DP - time : O(n) | space : O(1)
*/

import java.util.Arrays;
import java.util.Collections;

public class MaxSumNonAdjacent
{
    public static void test()
    {
        int [] nums = {2,4,6,2,5};
        int [] nums2 = {5,1,1,5};
        int [] nums3 = {10,-20,-1,20,12};
        int [] nums4 ={5, 5, 10, 100, 10, 5}; // 110

        System.out.println(maxSum(nums));
        System.out.println(maxSum(nums2));
        System.out.println(maxSum(nums3));
        System.out.println(maxSum(nums4));
    }

    // time : O(n) | space : O(1)
    public static int maxSum(int [] nums)
    {
        if (nums.length < 2) { return (nums.length == 0) ? 0 : nums[0]; }

        int secondLast = nums[0];
        int last = nums[1];
        // starting from  i = 2
        for(int i = 2; i < nums.length; i++)
        {
            int current = nums[i];
            if (secondLast > 0)
            {
                if(nums[i] > 0) {  current += secondLast; }
                else { current = secondLast; }
            }
            secondLast = last;
            last = current;
        }
        return Math.max(last,secondLast);
    }
}
