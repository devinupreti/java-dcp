/*
PROBLEM:
Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.

EXAMPLE:
given [(30, 75), (0, 50), (60, 150)], you should return 2

SOLUTION:
1. Sort by end time and use heap/priorityQueue - T : O(nlogn) | S : O(n)

if start_time < heap.top end time -> add
else -> poll

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MeetingRooms
{
    public static void test()
    {
        ArrayList<Integer []> meetings = new ArrayList<Integer []>();
        Integer [] meetingOne = {30, 75};
        Integer [] meetingTwo = {0, 50};
        Integer [] meetingThree = {60, 150};
        meetings.add(meetingOne);
        meetings.add(meetingTwo);
        meetings.add(meetingThree);

        System.out.println(minMeetingRoomsRequired(meetings));

    }

    public static int minMeetingRoomsRequired(ArrayList<Integer []> meetings)
    {
        Collections.sort(meetings, (s1, s2) -> s1[1] - s2[1]);

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int numberOfMeetingRoomsRequired = 0;

        heap.add(meetings.get(0)[1]);
        int index = 1;
        while (index < meetings.size())
        {
            int nextStartTime = meetings.get(index)[0];
            while(nextStartTime > heap.peek())
            {
                heap.poll();
            }
            heap.add(meetings.get(index)[1]);

            numberOfMeetingRoomsRequired = (heap.size() > numberOfMeetingRoomsRequired) ? heap.size() : numberOfMeetingRoomsRequired;
            ++index;
        }

        return numberOfMeetingRoomsRequired;
    }

}
