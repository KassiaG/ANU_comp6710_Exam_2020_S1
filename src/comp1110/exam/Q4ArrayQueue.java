package comp1110.exam;

import java.util.Arrays;

/**
 * COMP1110/6710 Exam, Question 4
 * <p>
 * This class represents a queue, in which elements are added and removed in a
 * first in, first out (FIFO) order. Duplicate elements are permitted.
 * When a queue is first created, it contains no elements.
 * The queue can grow to fit new elements as required.
 * Attempting to access an element of an empty queue throws an EmptyQueueException,
 * and does not result in any modification to the queue.
 * The Queue is implemented using an array data structure (a regular Java array),
 * and does not use any of the Java Collection classes.
 */
public class Q4ArrayQueue<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private int front;
    private int rear;
    public Q4ArrayQueue(){
        elements = new Object[DEFAULT_CAPACITY];
        size =0;
        front = 0;
        rear = 1;
    }
    /**
     * An exception that is thrown when trying to dequeue or peek at an
     * empty queue. Do not modify this class.
     */
    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() { }
    }

    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        // FIXME complete this method
        return size == 0 ;
    }

    /**
     * Add the given value to the back of the queue.
     *
     * @param value the value to add to the queue
     */
    public void enqueue(T value) {
        if(size == elements.length){
            expandCapacity();
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = value;
        size++;
        // FIXME complete this method
    }

    private void expandCapacity() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            int index = (front + i) % elements.length;
            newElements[i] = elements[index];
        }
        elements = newElements;
        front = 0;
        rear = size - 1;
    }

    /**
     * Remove the value from the front of the queue and return it.
     *
     * @return the value that was taken off of the queue
     * @throws EmptyQueueException if the queue is currently empty
     */
    public T dequeue() throws EmptyQueueException {
        // FIXME complete this method
        if (isEmpty()){
            throw new EmptyQueueException();
        }
        T value = (T) elements[front];
        elements[front] = null;
        front = (front +1 ) % elements.length;
        size--;
        return value;

    }

    /**
     * Get the value that is currently at the front of the queue,
     * but do not remove it from the queue.
     *
     * @return the value at the front of the queue
     * @throws EmptyQueueException if the queue is currently empty
     */
    public T first() throws EmptyQueueException {
        if (isEmpty()){
            throw new EmptyQueueException();
        }
        return (T) elements[front];
        // FIXME complete this method
    }

    /**
     * Check whether a given value is contained in this queue.
     * Specifically, returns true if value is not null and
     * an element e is contained in the queue such that e.equals(value).
     *
     * @param value the value to search for
     * @return true if the value is contained in this queue
     */
    public boolean contains(T value) {
        // FIXME complete this method
        for (int i = 0; i < size; i++) {
            int index = (front + i) % elements.length;
            if (value.equals(elements[index])){
                return true;
            }

        }
        return false;
    }

    /**
     * Create a String representation of this queue.
     * Elements on the queue are listed in order from front to back,
     * separated by commas (without spaces).
     * If the queue is empty, an empty string is returned.
     * For example, a queue containing the elements (from front to back)
     * "a", "b", and "c" would be represented as "a,b,c".
     *
     * @return a String representation of this queue
     */
    public String toString() {
        // FIXME complete this method
        if (isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = (front + i) % elements.length;
            sb.append(elements[index]);
            if (i<size-1){
                sb.append(",");
            }

        }
        return sb.toString();
    }
}
