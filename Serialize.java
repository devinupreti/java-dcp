/*
PROBLEM:
Given the root to a binary tree, implement serialize(root),
which serializes the tree into a string,
and deserialize(s), which deserializes the string back into the tree.

EXAMPLE:
node = Node('root', Node('left', Node('left.left')), Node('right'))
assert deserialize(serialize(node)).left.left.val == 'left.left

Solution:
Prefix Traversal for both Serialize and Deserialize
In deserialize you need to keep removing elements
Time : O(n) | Space : O(n + m) - recursive stack + space to store the string

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Tree
{
    public String val;
    public Tree left;
    public Tree right;

    public Tree(String val) { this.val = val; }

    public Tree(String val, Tree left, Tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Serialize
{
    public static void test()
    {
        Tree root = new Tree("root", new Tree("left", new Tree("left.left"), null), new Tree("right"));
        System.out.println(serialize(root));
        Tree get = deSerialize(serialize(root));
        System.out.println(serialize(get));
    }

    public static void test2()
    {
        Tree root = new Tree("root"); //, null , new Tree("right"));
        System.out.println(serialize(root));
        Tree get = deSerialize(serialize(root));
        System.out.println(serialize(get));
    }

    public static String serialize(Tree root)
    {
        if (root == null) { return "null"; }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    public static Tree makeTree(List<String> nodes)
    {
        String current = nodes.get(0);
        nodes.remove(current);

        if (current.equals("null")) { return null; }

        Tree root = new Tree(current);
        if (nodes.size() > 0)
            root.left = makeTree(nodes);

        if (nodes.size() > 0)
            root.right = makeTree(nodes);

        return root;
    }

    public static Tree deSerialize(String treeString)
    {
        ArrayList<String> nodes = new ArrayList<String> (Arrays.asList(treeString.split(",")) );
        Tree root = makeTree(nodes);
        return root;
    }
}
