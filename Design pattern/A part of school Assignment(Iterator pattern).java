import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author 김상진
 * 배열 기반 비정렬 범용 리스트
 * @version 2023년도 2학기
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 */
public class UnsortedArrayList<T> implements Iterable<T> {
    private int capacity = 5;
    @SuppressWarnings("unchecked")
    private T[] items = (T[]) (new Object[capacity]);
    private int size = 0;

    private int memory;
    private boolean prevNextused = false;
    private boolean addRemoveUsed = false;
    private boolean onlynext = false;

    private class ArrayListIterator implements ListIterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }
        //next, previous호출에서 반환된 요소를 교체한다. 단, next, previous이후 add, remove가 호출되었다면 set은 사용할수 없고 IlleegaState예외를 발생해야 한다.

        @Override
        public T next() {
            if (cursor < size) {
                memory = cursor;
                prevNextused = true;
                addRemoveUsed = false;
                onlynext = true;
                return getClonedIfCloneable(items[cursor++]);
            } else throw new NoSuchElementException();

        }

        @Override
        public T previous() {
            if (cursor > 0 && cursor <= size) {
                memory = cursor - 1;
                prevNextused = true;
                addRemoveUsed = false;
                onlynext=false;
                return getClonedIfCloneable(items[--cursor]);
            } else throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public int nextIndex() {
            if (cursor == size) return size;
            else return cursor;
        }

        @Override
        public int previousIndex() {
            if (cursor - 1 < 0) return -1;
            else return cursor - 1;
        }

        @Override
        public void add(T e) {
            if (addRemoveUsed)
                throw new IllegalStateException();
            if (size + 1 >= capacity) increaseCapacity();
            for (int i = size; i > cursor; i--)
                items[i] = items[i - 1];
            items[cursor] = e;
            size++;
            cursor++;
            if (prevNextused) {
                addRemoveUsed = true;
                prevNextused = false;
            }
        }

        @Override
        public void remove() {
            if (addRemoveUsed)
                throw new IllegalStateException();

            if (prevNextused) {
                for (int j = memory + 1; j < size; j++)
                    items[j - 1] = items[j];
                --size;
                items[size] = null;
                addRemoveUsed = true;
                prevNextused = false;
                if (onlynext) {
                    cursor -= 1;
                    onlynext=false;
                }
            }
        }

        //next, previous호출에서 반환된 요소를 교체한다. 단, next, previous이후 add, remove가 호출되었다면 set은 사용할수 없고 IlleegaState예외를 발생해야 한다.
        @Override
        public void set(T e) {
            if (addRemoveUsed) throw new IllegalStateException();
            else
                items[memory] = e;
        }


    }

    @SuppressWarnings("unchecked")
    private T getCloned(T item) {
        T cloned = null;
        try {
            Class<?> C = item.getClass();
            Method cloneMethod = C.getMethod("clone");
            cloned = (T) cloneMethod.invoke(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloned;
    }

    private T getClonedIfCloneable(T item) {
        if (item instanceof Cloneable) return getCloned(item);
        return item;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peekBack() {
        if (isEmpty()) throw new IllegalStateException();
        return items[size - 1];
    }

    private void increaseCapacity() {
        capacity *= 2;
        items = Arrays.copyOf(items, capacity);
    }

    public void pushBack(T item) {
        if (size == capacity) increaseCapacity();
        items[size++] = item;
    }

    public T popBack() {
        if (isEmpty()) throw new IllegalStateException();
        return items[--size];
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("유효하지 않은 색인 사용");
        return items[index];
    }


    @SuppressWarnings("unchecked")
    public void pushBackAll(T... items) {
        for (var item : items) pushBack(item);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    public ListIterator<T> listIterator() {
        return new ArrayListIterator();
    }
}
