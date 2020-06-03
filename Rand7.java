
/*
PROBLEM:
Using a function rand5() that returns an integer from 1 to 5 (inclusive) with uniform probability,
implement a function rand7() that returns an integer from 1 to 7 (inclusive).

LOGIC:
Let x be any multiple of 7 -> 7, 14, 21
then x % 7 will have equal probability for (0 to 6)
therefore, (x % 7) + 1 -> (1 to 7) uniform probability

Now, to generate 25 numbers with equal prob
we can use x = 5 * [rand5()-1] + rand5() // bijection - one to one mapping hence equal probability
if (x < 22) -> OK
else (x > 21) -> DISCARD [re-generate x]

 */

import java.util.stream.IntStream;

public class Rand7 {

    public static void test()
    {
        for (int i : IntStream.range(0,10).toArray())
        {
            System.out.print(rand7() + " ");
        }
    }
    
    public static int rand5()
    {
        // assume given
        int randomInt = (int) (5.0 * Math.random()) + 1;
        return randomInt;
    }

    // avg - O(1) | worst - O(n)
    public static int rand7()
    {
        int i;
        i = 5*rand5() + rand5() - 5; // 1 to 25
        if (i < 22)
            return i%7 + 1;
        return rand7();
    }

}
