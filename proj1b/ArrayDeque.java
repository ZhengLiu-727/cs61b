public class ArrayDeque<T> implements Deque<T>{
    private static int initial_capacity = 8;
    private static int Factor = 2;
    private static double min_ratio = 0.25;


    private T[] items;
    private int size;
    private int capacity;
    private int NextFirst;
    private int NextLast;


    public ArrayDeque() {
        capacity = initial_capacity;
        size = 0;
        items = (T[]) new Object[initial_capacity];
        NextFirst = capacity - 1;
        NextLast = 0;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }


    public int onePlus(int index) {
        if (index == capacity - 1) {
            return 0;
        }else {
            return index + 1;
        }
    }

    public int oneMinus(int index) {
        if (index == 0) {
            return capacity - 1;
        }else {
            return index - 1;
        }
    }

    public void resize(int new_capacity) {
        T[] new_items = (T[]) new Object[new_capacity];
        int head = onePlus(NextFirst);
        int tail = oneMinus(NextLast);

        if (head < tail) {
            int length = tail - head + 1;
            System.arraycopy(items, head, new_items, 0, length);
            NextFirst = new_capacity - 1;
            NextLast = length;
        } else {
            int lengthFirsts = capacity - head;
            int newhead = new_capacity - lengthFirsts;
            int lengthLasts = NextLast;
            System.arraycopy(items, head, new_items, newhead, lengthFirsts);
            System.arraycopy(items, 0, new_items, 0, lengthLasts);
            NextFirst = new_capacity - lengthFirsts - 1;
        }

        capacity = new_capacity;
        items = new_items;

    }


    private void expand() {
        if (size == capacity) {
            int newCapacity = capacity * Factor;
            resize(newCapacity);
        }
    }

    private void contract() {
        double ratio = (double) size / capacity;
        if (capacity >= 16 && ratio < min_ratio) {
            int newCapacity = capacity / 2;
            resize(newCapacity);
        }
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        items[NextFirst] = item;
        NextFirst = oneMinus(NextFirst);

        expand(); // Expand if array is full
    }

    @Override
    public void addLast(T item) {
        size += 1;
        items[NextLast] = item;
        NextLast = onePlus(NextLast);

        expand();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int head = onePlus(NextFirst);
        T removed = items[head];
        items[head] = null;
        NextFirst = head;
        size -= 1;

        contract(); // Contract array if it only uses less than 25% of memory

        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int tail = oneMinus(NextLast);
        T removed = items[tail];
        items[tail] = null;
        NextLast = tail;
        size -= 1;

        contract(); // Contract array if it only uses less than 25% of memory

        return removed;
    }


    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int indexFromFront = NextFirst + 1 + index;
        if (indexFromFront >= capacity) {
            indexFromFront -= capacity;
        }
        return items[indexFromFront];
    }

    @Override
    public void printDeque() {
        int currentIndex = onePlus(NextFirst);
        while (currentIndex != NextLast) {
            System.out.print(items[currentIndex] + " ");
            currentIndex = onePlus(currentIndex);
        }
        System.out.println();
    }


    public ArrayDeque(ArrayDeque other) {
        capacity = other.capacity;
        size = 0;
        NextFirst = capacity - 1;
        NextLast = 0;
        items = (T[]) new Object[capacity];

        System.arraycopy(other.items, 0, items, 0, capacity);


    }
}
