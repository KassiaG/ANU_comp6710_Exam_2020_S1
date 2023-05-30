package comp1110.exam;

/**
 * COMP1110 Exam, Question 1.2
 */
public class Q1Rain {

    static final int MAGIC = 999;

    /**
     * This function takes an array of integers and returns an integer
     * that is the average of the 'included' integers in the array,
     * or -1 if there are no 'included' integers in the array.
     * <p>
     * An array element is 'included' if:
     * a) it is greater than or equal to zero, and
     * b) it appears earlier in the array than the MAGIC number (999)
     * <p>
     * Because the return value is an integer, the average should be
     * calculated using integer division.
     * <p>
     * Some examples:
     * <p>
     * [ 1, 999] -> 1
     * [ 1, 3, 999] -> 2
     * [ 1, 6, 999, 4] -> 3
     * [ 1, -3, 5, 999] -> 3
     *
     * @param in An array of integers
     * @return The average of the 'included' values in the input array, or
     * -1 if there are no included values in the input array.
     */
    public static int avg(int[] in) {
        // FIXME complete this method
        // Initialize variables to keep track of sum and count
        int sum = 0;
        int count =0;
        //An array element is 'included' if:
        //      a) it is greater than or equal to zero, and
        //      b) it appears earlier in the array than the MAGIC number (999)
        // Iterate through the array
        for (int i = 0; i < in.length; i++) {
            // Check if the element meets the 'included' conditions
            if (in[i] >= 0 && in[i] != MAGIC) {
                // If it does, add it to the sum and increment the count
                sum += in[i];
                count++;
            } else if (in[i] == MAGIC) {
                // If we encounter the MAGIC number, break out of the loop
                break;
            }
        }// Check if there are included values in the array
            if(count > 0){
                // Calculate the average using integer division and return it
                return sum / count;
            } else {
                // If there are no included values, return -1
                return -1;
            }
        }
}
