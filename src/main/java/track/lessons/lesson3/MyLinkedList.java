package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List {

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
    void add(int item) {
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
    int remove(int idx) throws NoSuchElementException {
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
    int get(int idx) throws NoSuchElementException {
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
}
