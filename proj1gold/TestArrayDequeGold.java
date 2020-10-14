import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    private String message = "";

    public void randomAdd(double random, Integer i, StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads) {
        if (random < 0.5) {
            sad.addFirst(i);
            ads.addFirst(i);
            message += "\naddFirst(" + i + ")";
        }else {
            sad.addLast(i);
            ads.addLast(i);
            message += "\naddLast(" + i + ")";
        }
    }

    public void randomRemove(double random, Integer i, StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads) {
        Integer expected;
        Integer actual;
        if (random < 0.5) {
            actual = sad.removeFirst();
            expected = ads.removeFirst();
            message += "\nremoveFirst()";
        }else {
            actual = sad.removeLast();
            expected = ads.removeLast();
            message += "\nremoveLast()";
        }
        assertEquals(message, expected, actual);
    }

    @Test
    public void testRandomized() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        for (Integer i = 0; i < 100; i += 1) {
            if (sad.isEmpty()) {
                double random = StdRandom.uniform();
                randomAdd(random, i, sad, ads);
            } else {
                double random1 = StdRandom.uniform();
                double random2 = StdRandom.uniform();
                if (random1 < 0.5) {
                    randomAdd(random2, i, sad, ads);
                } else {
                    randomRemove(random2, i, sad, ads);
                }
            }
        }

    }

}
