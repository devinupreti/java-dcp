/*
PROBLEM:
Given a linked list,
rearrange the node values in low->high->low->high ... form

EXAMPLE:
Input : 1->2->3->4->5
Output : 1->3->2->5->4

Solution : 
Swap nodes whenever property not satisfied
if a<b and b<c then a<c
Time : O(n) | Space : O(1)
*/
class Node{
    int value;
    Node next;

    public Node() {}

    public Node(int value){
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String sequence = "";
        Node head = this;
        while (head != null)
        {
            sequence = sequence + head.getValue() + " -> ";
            head = head.getNext();
        }
        sequence = sequence + "null";
        return sequence;
    }
}

public class ArrangeNode {

    public static Node swap(Node current, Node next)
    {
        current.setNext(next.getNext());
        next.setNext(current);
        return next;
    }

    public static Node makeLowHigh(Node current)
    {
        if (current == null || current.getNext() == null || current.getValue() < current.getNext().getValue())
        {
            return current;
        }

        return swap(current, current.getNext());

    }

    public static Node makeHighLow(Node current)
    {
        if (current == null || current.getNext() == null || current.getValue() > current.getNext().getValue())
        {
            return current;
        }

        return swap(current, current.getNext());
    }

    // Input : 1->2->3->4->5
    // Output : 1->3->2->5->4
   
    public static void arrangeNodes(Node linkedList)
    {
        Node head = makeLowHigh(linkedList);

        Node prev = head;
        int counter = 1;

        while (prev != null && prev.getNext() != null)
        {
            System.out.print(prev.value + " ");
            if (counter % 2 == 0) { prev.setNext(makeLowHigh(prev.getNext())); }
            else { prev.setNext(makeHighLow(prev.getNext())); }

            counter += 1;
            prev = prev.getNext();
        }
        System.out.println();
        System.out.println(head);
    }

    public static void test1()
    {
        Node test = new Node(1, new Node(2, null));
        System.out.println(test);
        arrangeNodes(test);
    }

    public static void test2()
    {
        Node test = new Node(1, new Node(2, null));
        test.getNext().setNext(new Node(3, new Node(4, new Node(5, null))));
        System.out.println(test);
        arrangeNodes(test);
    }

    public static void test3()
    {
        Node test = new Node(1, new Node(2, null));
        Node node3 = new Node(3, new Node(4, new Node(5, null)));
        test.getNext().setNext(node3);
        Node node6 = new Node(6, new Node(7, new Node(8, new Node(9, null))));
        node3.getNext().getNext().setNext(node6);
        System.out.println(test);
        arrangeNodes(test);
    }

    public static void test4()
    {
        Node test = null;
        arrangeNodes(test);
    }

    public static void test5()
    {
        Node test = new Node(9, new Node(8, new Node(7, new Node(6, new Node(5, new Node(4, new Node(11, null)))))));
        System.out.println(test);
        arrangeNodes(test);
    }
}
