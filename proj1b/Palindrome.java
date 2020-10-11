public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (first == last) {
                return isPalindromeHelper(deque);
            } else {
                return false;
            }
        }
    }
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);

        return isPalindromeHelper(deque);
    }


    private boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (cc.equalChars(first, last)) {
                return isPalindromeHelper(deque, cc);
            } else {
                return false;
            }
        }
    }

    /**
     * Returns true if the given word is a palindrome according to the character comparison
     *  test provided by the CharacterComparator passed in as argument `cc`
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }
}
