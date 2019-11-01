/*
PROBLEM:
Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.
Assume nodes with the same value are the exact same node objects.
Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.

EXAMPLE:
For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.

SOLUTION:
1. Brute Force
for each node in LL1, compare every elem in LL2
t:  O(mn) | s : O(1)

2. HashSet
t : O(m + n) | s : O(m)

3. Add visited info to node
- if any node is visited on second list then it is intersection
t: O(m + n) | s : O(1)

*/

import java.util.HashSet;

public class LLIntersection {

    public static void test()
    {
        // Linked List 1
        Node test = new Node(1, new Node(2, null));
        Node node3 = new Node(3, new Node(4, new Node(5, null)));
        test.getNext().setNext(node3);
        Node node6 = new Node(6, new Node(7, new Node(8, new Node(9, null))));
        node3.getNext().getNext().setNext(node6);

        // Linked List 2
        Node test2 = new Node(10, node6);

        System.out.println(getIntersection(test, test2));
    }

    public static Node getIntersection(Node list1, Node list2)
    {
        Node head = list1;
        HashSet<Integer> elements = new HashSet<Integer> ();

        while (head != null)
        {
            elements.add(head.value);
            head = head.next;
        }

        head = list2;
        while (head != null)
        {
            if (elements.contains(head.value))
            {
                return head;
            }
            head = head.next;
        }
        return head;
    }

}
