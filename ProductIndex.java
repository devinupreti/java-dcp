/*
PROBLEM:
Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
You can't use division.

Example:
Input - [1,2,3,4,5]
Output - [120, 60, 40, 30, 24]

Input - [3, 2, 1]
Output - [2, 3, 6]

Logic:
[a, b, c] -> [(a*b*c) /a , (a*b*c) / b, (a*b*c) / c]
          -> [(b*c), (a*c), (a*b)]

Solution:
1. Array with Left Pass & Right Pass | Time : O(2n) | Space : O(n)
*/

public class ProductIndex
{
    public static void test()
    {
        int[] nums = new int[] {1,2,3,4,5};
        int[] sol = getProducts(nums);
        for(int x : sol) { System.out.print(x + " ") ;}
    }

    public static int[] getProducts(int[] nums)
    {
        int [] products = new int[nums.length];
        for (int i = 0; i < products.length; i++)
        { products[i] = 1; }

        //left pass
        for(int i = 1; i < nums.length; i++)
        {
            products[i] = nums[i - 1] * products[i - 1];
        }
        //right pass
        int current = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i--)
        {
            products[i] *= current;
            current *= nums[i];
        }
        return products;
    }
}
