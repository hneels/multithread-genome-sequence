/**
 * This class contains one method, static makeSequence() which returns one
 * String of length 10 that contains random letters from A,T,G, and C. For example:
 * TGGGAAAGCA, GCGCTATCGT, or ACTATATCTG. The static method is used to print genome
 * sequences in all 3 versions of the program.
 * @author Hope Neels
 */

import java.util.Random;

public class Genome {

    /**
     * Creates and returns a String of 10 characters randomly chosen from A,T,G,C, representing
     * a genome sequence
     * @return a String of length 10 with letters A,T,G,C randomly chosen in random order
     */
    public static String makeSequence() {

        // possible letter choices
        char[] letters = {'A', 'T', 'G', 'C'};

        // a Random generator for choosing a random index from the array
        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {

            // get a random letter from the letters array
            char letter = letters[random.nextInt(4)];

            // add it to the end of stringBuilder
            stringBuilder.append(letter);
        }

        return stringBuilder.toString();
    }
}
