
/*
PROBLEM:
A permutation can be specified by an array P, where P[i] represents the location of the element at i in the permutation.
For example, [2, 1, 0] represents the permutation where elements at the index 0 and 2 are swapped.
Given an array and a permutation, apply the permutation to the array.

EXAMPLE:
Given the array ["a", "b", "c"] and the permutation [2, 1, 0]
return ["c", "b", "a"]

LOGIC:
new array : O(n), O(n)
O(n),O(1) -> swap in circular fashion and mark swapped positions as Invalid (-1)
Logic -> everything we are swapping with is coming to its rightful position we just need to make sure that the first elem we had gets to its right position

SOLUTIONS:
1. Circular swap - T: O(n), S: O(1)
2. New array - T: O(n), S:O(n)
3. Sort using permutation index as comparison - T : O(nlogn), S:O(1)
 */

public class ApplyPermutation {

    public static void test()
    {
        int [] array = {000, 100, 200};
        int [] permutation = {1,2,0};

        int A[] = { 5, 6, 7, 8 };
        int P[] = { 3, 2, 1, 0 };

        int arr[] = new int[]{50, 40, 70, 60, 90};
        int index[] = new int[]{3,  0,  4,  1,  2}; // 60 50 90 40 70

        permute(arr, index);
    }

    static int[] swap(int []arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public static void permute(int [] array, int[] permutation)
    {
        for (int i = 0; i < array.length; ++i)
        {
            int currentIndex = i;

            // while currentIndex is not at position
            // and permutation is valid -> SWAP with correct position elem

            // Logic -> everything we are swapping with
            //          is coming to its rightful position
            //          we just need to make sure that the first elem we had gets to its right position
            while (i != permutation[currentIndex] && permutation[currentIndex] > 0)
            {
                swap(array, currentIndex, permutation[currentIndex]);


                int oldIndex = currentIndex;
                currentIndex = permutation[currentIndex];
                permutation[oldIndex] = -1;
            }
            permutation[currentIndex] = -1;
        }

        for (int x : array) { System.out.print(x + " ");}
    }

}
