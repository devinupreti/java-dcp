/*
PROBLEM:
Given an array of integers and a number k, where 1 <= k <= length of the array, compute the maximum values of each subarray of length k.
Do this in O(n) time and O(k) space. You can modify the input array in-place and don't need to store the results (can just print them)

EXAMPLE:
For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:
10 = max(10, 5, 2)
7 = max(5, 2, 7)
8 = max(2, 7, 8)
8 = max(7, 8, 7)

SOLUTION:

1. Brute force
go through the array and keep computing max(subarray)
O(nk) | O(1) - print

2. Max heap of k elements
insert & pop - O(log k)
remove specific element - O(k)
O(n * k) | O(k)

3. Dequeue
maintain indexes [largest_elem_index ...  smallest_elem_index] in deque of size k
peek & peekLast - O(1)
O(n) | O(k)

*/


import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaxInSubarray
{
    public static void test()
    {
        //findMaxValuesHeap(new int[] {10, 5, 2, 7, 8, 7}, 3);
        findMaxValuesDeque(new int[] {10, 5, 2, 7, 8, 7}, 3);
    }

    // time : O(n) | space : O(k)
    // source - GeeksForGeeks
    static void findMaxValuesDeque(int arr[], int k)
    {
        int n = arr.length;

        // front/left -> max elem index
        // rear/right -> min elem index
        Deque<Integer> Qi = new LinkedList<Integer>();

        int i;
        for (i = 0; i < k; ++i) {
            while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast(); // Remove from rear

            // Add new element at rear of queue
            Qi.addLast(i);
        }

        System.out.println(Qi);
        for (; i < n; ++i) {
            // The element at the front of the queue is the largest element of
            // previous window, so print it
            System.out.print(arr[Qi.peek()] + " ");

            // Remove the elements which are out of this window
            while ((!Qi.isEmpty()) && Qi.peek() <= i - k)
                Qi.removeFirst();

            // Remove all elements smaller than the currently
            // being added element (remove useless elements)
            while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();

            // Add current element at the rear of Qi
            Qi.addLast(i);
        }

        // Print the maximum element of last window
        System.out.print(arr[Qi.peek()]);
    }


    public static void findMaxValuesHeap(int [] nums, int k)
    {
        if (k > nums.length) { return; }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i = 0; i < k; i++)
        { maxHeap.add(nums[i]); }

        int first =  0;
        int last = k;

        System.out.print(maxHeap.peek() + " | ");
        while (last < nums.length)
        {
            maxHeap.remove(nums[first]); // O(k) - bad
            maxHeap.add(nums[last]);

            System.out.print(maxHeap.peek() + " | ");

            first += 1;
            last += 1;
        }
    }
}
