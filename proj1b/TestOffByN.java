import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator OffByN3 = new OffByN(3);
    static CharacterComparator OffByN5 = new OffByN(5);

    @Test
    public void testEqualsCharLower() {
        assertTrue(OffByN3.equalChars('a', 'd'));
        assertTrue(OffByN5.equalChars('a', 'f'));
        assertFalse(OffByN3.equalChars('a', 'h'));
        assertFalse(OffByN5.equalChars('a', 'k'));
    }

    @Test
    public void testEqualsCharUpper() {
        assertTrue(OffByN3.equalChars('A', 'D'));
        assertTrue(OffByN5.equalChars('A', 'F'));
        assertFalse(OffByN3.equalChars('A', 'H'));
        assertFalse(OffByN5.equalChars('A', 'K'));
    }

    @Test
    public void testEqualsCharNonAlphabet() {
        assertTrue(OffByN3.equalChars('#', '&'));
        assertTrue(OffByN5.equalChars(':', '?'));
        assertFalse(OffByN3.equalChars('*', '+'));
        assertFalse(OffByN5.equalChars('(', ')'));
    }
}
