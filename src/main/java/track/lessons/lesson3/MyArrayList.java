package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * <p>
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    private int[] array;

    public MyArrayList() {
        size = 0;
        array = new int[10];
    }

    public MyArrayList(int capacity) {
        this();
        if (capacity > 0) {
            array = new int[capacity];
            size = capacity;
        }
    }

    @Override
    public void add(int item) {
        if (size + 1 > array.length) {
            int[] buffer = array;
            array = new int[2 * size];
            System.arraycopy(buffer, 0, array, 0, size);
        }
        size++;
        array[size - 1] = item;
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        if (idx >= 0 && idx < size) {
            System.arraycopy(array, idx + 1, array, idx, size - idx);
            size--;
            array[size] = 0;
        } else {
            throw new NoSuchElementException();
        }
        return 0;
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        if (idx >= 0 && idx < size) {
            return array[idx];
        } else {
            throw new NoSuchElementException();
        }
    }
}
