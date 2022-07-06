/**
 * Genome sequence printer version 1:
 * prints 100 genome sequences without concurrency
 * @author Hope Neels
 */
public class Version1 {

    public static void main(String[] args) {

        // start timeclock
        long startTime = System.currentTimeMillis();

        // print 100 random genome sequences
        for (int i = 0; i < 100; i++) {
            System.out.println(Genome.makeSequence());
        }

        // print total elapsed time
        System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }
}
