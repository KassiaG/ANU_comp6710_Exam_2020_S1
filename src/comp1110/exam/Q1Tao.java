package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.1
 */
public class Q1Tao {
    /**
     * Print the Collatz sequence for the value n.
     * If the input value is less than one, do nothing.
     * Otherwise, print the input value n on a line by itself, and then:
     * - if the input value is even, call tao again, passing in (n/2);
     * - if the input value is odd and greater than one, call tao again, passing in (n*3+1);
     * - if the input value is one, do nothing.
     * Special rule (*not* part of the Collatz sequence):
     * If the input value is 13120, print the string "Tao!" on a line by
     * itself and then exit (without printing anything further).
     *
     * @param n the starting term of the Collatz sequence to print
     * @see https://en.wikipedia.org/wiki/Collatz_conjecture
     */
    static void tao(int n) {
        // FIXME complete this method
        // Handle edge cases first
        if (n == 13120) {
            System.out.println("Tao!");
            return; // exit the method
        } else if (n == 1) {
            System.out.println(n);
            return; // do nothing
        } else if (n > 1) {
            System.out.println(n);
            if (n % 2 == 0) {
                tao(n / 2);
            } else {
                tao(n*3+1);
            }
        } else{
            return; // do nothing
        }
    }
}
