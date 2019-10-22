/*
PROBLEM:
You run an e-commerce website and want to record the last N order ids in a log.

Implement a data structure to accomplish this, with the following API:
- record(order_id): adds the order_id to the log
- get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.

LOGIC:
so we would only store N elements according to their order of arrival

breaking down each op
- record
    -> remove oldest id
    -> replace with newest id

- get_last(i)
    -> find id of (n - i)th element

Data structures:
1. Array - find : O(1) | replace : O(1)
2. LinkedList - find : O(n) | replace : O(1)

SOLUTION:
we can use an array with a pointer which points to the oldest available position
time for each op : O(1) | space : O(n)
*/

class TestRecord
{
    public static void test()
    {
        RecordLog log = new RecordLog(4);
        log.record(10);
        log.record(20);
        log.record(30);
        log.record(40);
        log.record(50);
        System.out.println(log.get_last(3));
    }
}

public class RecordLog
{
    private int [] data;
    private int pointer;

    RecordLog(int n)
    {
        this.data = new int [n];
        this.pointer = 0;
    }

    private void updatePointer()
    {
        this.pointer += 1;
        this.pointer = this.pointer % data.length;
    }

    public void record(int id)
    {
        // assuming the id added is the newest one
        data[pointer] = id;
        updatePointer();
    }

    public int get_last(int i)
    {
        int last = this.pointer - i + 1 - 1; // currently pointer points to next to last elem, hence -1 (redundant for explanation)
        if (last < 0) {last = data.length + last;}
        return this.data[last];
    }

}
