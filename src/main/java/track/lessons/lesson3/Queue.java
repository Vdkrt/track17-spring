package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Queue interface
 */
public interface Queue {
    public void enqueue(int value);

    public int dequeu() throws NoSuchElementException;
}
