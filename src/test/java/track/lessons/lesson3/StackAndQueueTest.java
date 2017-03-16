package track.lessons.lesson3;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;


/**
 * Tests for Stack and Queue interfaces
 */
public class StackAndQueueTest {
    @Test(expected = NoSuchElementException.class)
    public void emptyStack() throws Exception {
        Stack stack = new MyLinkedList();
        int number = stack.pop();
    }

    @Test
    public void stackPushPop() throws Exception {
        Stack stack = new MyLinkedList();
        stack.push(5);
        stack.push(7);
        stack.push(10);
        Assert.assertEquals(10, stack.pop());
        Assert.assertEquals(7, stack.pop());
        Assert.assertEquals(5, stack.pop());
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyQueue() throws Exception {
        Queue queue = new MyLinkedList();
        int number = queue.dequeu();
    }

    @Test
    public void queueEnDe() throws Exception {
        Queue queue = new MyLinkedList();
        queue.enqueue(1);
        queue.enqueue(10);
        queue.enqueue(21);
        Assert.assertEquals(1, queue.dequeu());
        Assert.assertEquals(10, queue.dequeu());
        Assert.assertEquals(21, queue.dequeu());
    }

}
