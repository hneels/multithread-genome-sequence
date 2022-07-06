/**
 * Genome sequence printer version 3:
 * prints 100 genome sequences, using ExecutorService to create and manage 5 threads,
 * each of which prints 20 genome sequences. Uses awaitTermination() to ensure main
 * method awaits all threads' completion.
 * @author Hope Neels
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Version3 {

    public static void main(String[] args) throws InterruptedException {

        // start timeclock
        long startTime = System.currentTimeMillis();

        // create new ExecutorService to manage the threads
        ExecutorService ex = Executors.newCachedThreadPool();

        // use ExecutorService to start 5 new GenomeTasks
        for (int i = 0; i < 5; i++) {
            ex.execute(new GenomeTask());
        }

        // stop ExecutorService from accepting more tasks
        ex.shutdown();

        // wait for all threads to finish
        ex.awaitTermination(10, TimeUnit.SECONDS);

        // print total elapsed time
        System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + " milliseconds");

    }
}
