import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class ArrayDequeTest {


    @Test
    public void testEmpty() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
        assertEquals(null, q.get(0));
        assertEquals(null, q.removeFirst());
        assertEquals(null, q.removeLast());

        System.out.println("Empty ArrayDeque function works!");
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            q.addFirst(i);
        }
        assertEquals(10, q.size());
        int actualFirst = q.get(0);
        int actualLast = q.get(9);
        assertEquals(9, actualFirst);
        assertEquals(0, actualLast);

        for (int i = 10; i < 100; i++) {
            q.addLast(i);
        }
        actualLast = q.get(99);
        assertEquals(100, q.size());
        assertEquals(128, q.capacity());
        assertEquals(99, actualLast);

        System.out.println("Resize function works!");
        System.out.println("Get function works!");
        System.out.println("AddFirst and AddLast function works!");

    }


    
}
