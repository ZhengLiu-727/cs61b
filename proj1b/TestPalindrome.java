import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    //You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByN3 = new OffByN(3);
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s0 = "";
        String s1 = "a";
        assertTrue(palindrome.isPalindrome(s0));
        assertTrue(palindrome.isPalindrome(s1));

        String s2 = "racecar";
        String s3 = "horse";
        assertTrue(palindrome.isPalindrome(s2));
        assertFalse(palindrome.isPalindrome(s3));

        String s4 = "noon";
        String s5 = "rancor";
        assertTrue(palindrome.isPalindrome(s4));
        assertFalse(palindrome.isPalindrome(s5));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        String s0 = "";
        String s1 = "a";
        assertTrue(palindrome.isPalindrome(s0, offByOne));
        assertTrue(palindrome.isPalindrome(s1, offByOne));

        String s2 = "flake";
        String s3 = "frake";
        assertTrue(palindrome.isPalindrome(s2, offByOne));
        assertFalse(palindrome.isPalindrome(s3, offByOne));

        String s4 = "abcb";
        String s5 = "abbb";
        assertTrue(palindrome.isPalindrome(s4, offByOne));
        assertFalse(palindrome.isPalindrome(s5, offByOne));
    }

    @Test
    public void testIsPalindromeOffByN() {
        String s0 = "";
        String s1 = "a";
        assertTrue(palindrome.isPalindrome(s0, offByN3));
        assertTrue(palindrome.isPalindrome(s1, offByN3));

        String s2 = "abxed";
        String s3 = "abxdd";
        assertTrue(palindrome.isPalindrome(s2, offByN3));
        assertFalse(palindrome.isPalindrome(s3, offByN3));

        String s4 = "abed";
        String s5 = "abbb";
        assertTrue(palindrome.isPalindrome(s4, offByN3));
        assertFalse(palindrome.isPalindrome(s5, offByN3));
    }


}
