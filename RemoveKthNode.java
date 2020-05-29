/*
PROBLEM:
Given a linked list and an integer k, remove the k-th node from the end of the list and return the head of the list.
k is guaranteed to be smaller than the length of the list.
Do this in one pass.

LOGIC:
Let k = 2, a -> b -> null then a is kth from end
use two pointers : fast and slow

SOLUTION:
1. Two pointer : T: O(n), S: O(1)
2. Recursive : T: (n), S: O(n)


*/

public class RemoveKthNode {

    static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            Node head = this;
            StringBuilder str = new StringBuilder("");
            while(head != null)
            {
                str.append(head.value);
                str.append(" -> ");
                head = head.next;
            }
            str.append("null\n");
            return str.toString();
        }
    }

    public static void test()
    {
        // LL : 1 -> 2 -> 3 -> 4 -> null
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, null))));
        removeKthNodeFromEnd(head, 3); // should remove two

        System.out.println(head);
    }

    // time : O(n), space : O(1)
    public static void removeKthNodeFromEnd(Node head, int k)
    {
        if (head == null) { return; }

        Node first = head;
        Node second = head;

        int i = 0;
        while (i <= k)
        {   second = second.next;
            i++;
        }

        while (second != null)
        {
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;
    }
}
