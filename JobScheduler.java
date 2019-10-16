/*
PROBLEM:
Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.

SOLUTION:
to pass functions as parameters(kinda), we need to use a functional interface (1 abstract method)
then use java.util.Timer()
and java.util.TimerTask()
*/


public class JobScheduler
{
    interface Command
    {
        public void execute();
    }

    // to call : JobScheduler.myScheduler( () -> System.out.println("I am slow but get things done!"), 5000);;
    public static void myScheduler(Command command, int n)
    {
        new java.util.Timer().schedule(new java.util.TimerTask()
        {
            @Override
            public void run()
            {
                command.execute();
            }
        }, n);
    }
}
