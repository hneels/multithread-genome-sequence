/**
 * Genome sequence printer version 2:
 * prints 100 genome sequences, using concurrency to create 5 Threads,
 * each of which prints 20 genome sequences. Uses join() to ensure
 * main method awaits all threads' completion.
 * @author Hope Neels
 */
public class Version2 {

    public static void main(String[] args) throws InterruptedException {

        // start timeclock
        long startTime = System.currentTimeMillis();

        // array of 5 Threads stores threads to join them after all have started
        Thread[] threads = new Thread[5];

        // start 5 threads, each of which prints 20 genome sequences
        for (int i = 0; i < threads.length; i++) {
            Thread thread = new Thread(new GenomeTask());
            thread.start();

            // add thread to threads array so they can be joined AFTER all have started
            threads[i] = thread;
        }

        // join each thread to main() so the timeclock waits until all threads finish
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        // print total elapsed time
        System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + " milliseconds");

    }
}
