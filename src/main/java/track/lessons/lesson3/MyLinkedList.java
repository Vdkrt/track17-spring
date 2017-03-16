package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements track.lessons.lesson3.Stack, track.lessons.lesson3.Queue{

    public MyLinkedList() {
        size = 0;
    }

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    private Node head, end;

    @Override
    public void add(int item) {
        if (size > 0) {
            Node current = new Node(end, null, item);
            end.next = current;
            end = current;
            size++;
        } else {
            head = new Node(null, null, item);
            end = head;
            size++;
        }
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        Node current = head;
        if (idx < 0 || idx >= size) {
            throw new NoSuchElementException();
        } else if (idx == 0) {
            if (size != 1) {
                head = head.next;
                head.prev = null;
                size--;
            } else {
                head = end = null;
                size--;
            }
        } else {
            for (int number = 0; number <= idx - 1; number++) {
                current = current.next;
            }
            if (idx == size - 1) {
                current.prev.next = current.next;
                end = current.prev;
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            size--;
        }
        return 0;
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        Node current = head;
        if (idx < 0 || idx >= size || size == 0) {
            throw new NoSuchElementException();
        } else if (idx != 0) {
            for (int number = 0; number <= idx - 1; number++) {
                current = current.next;
            }
        }
        return current.val;
    }


    public void push(int value) {
        this.add(value);
    }

    public int pop() throws NoSuchElementException {
        int curVal;
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            curVal = this.get(size - 1);
            this.remove(size - 1);
        }
        return curVal;
    }

    public void enqueue(int value) {
        this.add(value);
    }

    public int dequeu() throws NoSuchElementException {
        int curVal;
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            curVal = this.get(0);
            this.remove(0);
        }
        return curVal;
    }
}
