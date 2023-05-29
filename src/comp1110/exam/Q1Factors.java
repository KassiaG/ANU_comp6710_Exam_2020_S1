package comp1110.exam;

import java.util.ArrayList;
import java.util.List;

/**
 * COMP1110 Exam, Question 1.1
 */
public class Q1Factors {
    /**
     * This function takes a positive integer, n, and returns an array
     * of ints containing all prime factors of that integer in ascending order.
     * For example:
     * factors(6) returns {2, 3}
     * factors{7} returns {7}
     * factors{24} returns {2, 2, 2, 3}
     * <p>
     * If n is less than 2, an empty array is returned.
     *
     * @param n the number to factor
     * @return an array containing all prime factors of the number
     */
    public static int[] factors(int n) {
        if(n<2){// Return an empty array if n is less than 2
            return new int[0];
        }
        List<Integer> factorsList = new ArrayList<>();
        // Find prime factors by iterating through numbers from 2 to sqrt(n)
        for (int i = 2; i <= Math.sqrt(n); i++) {
            while(n%i ==0){ //as long as it is a factor
                factorsList.add(i); // i is a prime factor
                n/= i;// Reduce n by dividing it by i repeatedly
            }
        }
        // If n is still greater than 1, it means n itself is a prime factor
        if(n>1){
            factorsList.add(n);
        }
        // Convert the list of factors to an array
        int[] factorsArray = new int[factorsList.size()];
        for (int i = 0; i < factorsList.size(); i++) {
            factorsArray[i] = factorsList.get(i);
        }
        // FIXME complete this method
        return factorsArray;
    }
}
