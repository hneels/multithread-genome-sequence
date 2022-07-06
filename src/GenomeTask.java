/**
 * A Runnable-implementing task that can be passed to a Thread constructor or to ExecutorService's execute()
 * method. Prints 20 random genome sequences to the console by calling static Genome.makeSequence()
 * @author Hope Neels
 */

public class GenomeTask implements Runnable {

    /**
     * Prints 20 random genome sequences to the console, printing information about the thread name
     * and when the thread starts/finishes.
     */
    @Override
    public void run() {

        String name = Thread.currentThread().getName();

        // print thread start message
        System.out.println(name + " starting");

        // print 20 genome sequences, including which thread created it
        for (int i = 0; i < 20; i++) {
            System.out.println(Genome.makeSequence() + " from " + name);
        }

        // print thread finish message
        System.out.println(name + " finished");
    }
}
