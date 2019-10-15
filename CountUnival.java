/*
PROBLEM:
A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
Given the root to a binary tree, count the number of unival subtrees.

EXAMPLE:
   0
  / \
 1   1
    / \
   1   0
  / \
 1   1
has 5 unival trees

LOGIC:
if both child null -> YES
if one child null -> YES if that child is unival
if both child not null
-> NO if left.val != right.val
-> YES if left.val == right.val && left is unival && right is unival && left.val == left.child.val (or null) && right.val == right.child.val (or null)


SOLUTION:
1. Recursion | time : O(n) | space : O(n) { actual time is O(2n) that's why its confusing }

*/

public class CountUnival
{
    public static void test() {
        Tree node1 = new Tree("1");
        Tree node2 = new Tree("1");
        Tree root = new Tree("0", new Tree("1", null, null), new Tree("1", new Tree("1", node1, node2), new Tree("0")));
        System.out.println(count_unival(root));
    }

    public static boolean unival_helper(Tree node, String val)
    {
        if (node == null) { return true; }
        if (node.val.equals(val) ) { return unival_helper(node.left, val) && unival_helper(node.right, val); }
        return false;
    }

    public static boolean isUnival(Tree node){
        return unival_helper(node, node.val);
    }

    public static int count_unival(Tree node)
    {
        if (node == null) { return 0; }
        int left = count_unival(node.left);
        int right = count_unival(node.right);

        return (isUnival(node)) ? 1 + left + right : left + right;
    }

}
