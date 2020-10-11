
public class LinkedListDeque<T> implements Deque<T>{
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node x, T i, Node y){
            prev = x;
            item = i;
            next = y;
        }
    }

    private Node sentinel;  // circular sentinel
    private int size;


// create an empty linked list
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel && sentinel.prev == sentinel && size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }
    

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed;
    }

    @Override
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed;
    }

    @Override
    public void printDeque() {
        Node current = sentinel;
        while(current.next != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node current = sentinel;
        while (index >= 0) {
            current = current.next;
            index -= 1;
        }
        return current.item;
    }


    private T getRecursiveHelper(Node currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }



    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new Node(sentinel, null, sentinel);

        for (int i = 0; i < other.size; i += 1) {
            addLast((T) other.get(i));
        }
    }

}


