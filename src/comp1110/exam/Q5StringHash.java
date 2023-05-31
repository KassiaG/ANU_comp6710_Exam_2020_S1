package comp1110.exam;

/**
 * COMP1110 Exam, Question 5
 */
public class Q5StringHash {
    /**
     * Return a hash of the given string as an integer in the range 0 ... buckets - 1.
     *
     * @param value   The string to be hashed
     * @param buckets The number of buckets into which the hash should be made (defining its range)
     * @return An integer hash of value in the range 0 ... buckets - 1.
     */
    public static int hash(String value, int buckets) {
        long hash = 5381;
        // FIXME complete this method, without using Java's built-in hashCode() method
            for (int i = 0; i < value.length(); i++) {
                hash = ((hash << 5) + hash) + value.charAt(i);
            }
            return (int) (hash % buckets);
    }
}
