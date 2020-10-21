package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        HashMap<Integer, Integer> numbers= new HashMap<>();
        for(Oomage o: oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if(numbers.containsKey(bucketNum)) {
                int num = numbers.get(bucketNum);
                numbers.put(bucketNum, num + 1);
            } else {
                numbers.put(bucketNum, 1);
            }
        }

        int N = oomages.size();
        int max = (int) (N / 2.5);
        int min = N / 50;
        Set<Integer> numberKeys = numbers.keySet();
        for(int k: numberKeys) {
            int number = numbers.get(k);
            if(number > max || number < min) {
                return false;
            }
        }
        return true;
    }
}
