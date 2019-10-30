import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Key {

    private final int x;
    private final int y;

    Key(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}

public class LongestCommonSequence3 {

    private static int LCM(int[] seq, int[] seq2) {
        Map<Key, Integer> matrix = new HashMap<>();
        for (int i = 0; i <= seq.length; i++) {
            matrix.put(new Key(i, 0), 0);
        }
        for (int j = 0; j <= seq2.length; j++) {
            matrix.put(new Key(0, j), 0);
        }
        for (int j = 1; j <= seq2.length; j++) {
            for (int i = 1; i <= seq.length; i++) {
                int insertion = matrix.get(new Key(i - 1, j));
                int deletion = matrix.get(new Key(i, j - 1));
                int match = matrix.get(new Key(i - 1, j - 1)) + 1;
                int mismatch = matrix.get(new Key(i - 1, j - 1));
                int temp = Math.max(insertion, deletion);
                if (seq[i - 1] == seq2[j - 1]) {
                    matrix.put(new Key(i, j), Math.max(temp, match));
                } else {
                    matrix.put(new Key(i, j), Math.max(temp, mismatch));
                }
            }
        }
        return matrix.get(new Key(seq.length, seq2.length));
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int seqLength = scanner.nextInt();
        int[] seq = new int[seqLength];
        for (int i = 0; i < seqLength; i++) {
            int number = scanner.nextInt();
            seq[i] = number;
        }
        int seqLength2 = scanner.nextInt();
        int[] seq2 = new int[seqLength2];
        for (int i = 0; i < seqLength2; i++) {
            int number = scanner.nextInt();
            seq2[i] = number;
        }
        int seqLength3 = scanner.nextInt();
        int[] seq3 = new int[seqLength3];
        for (int i = 0; i < seqLength3; i++) {
            int number = scanner.nextInt();
            seq3[i] = number;
        }
        int temp = Math.min(LCM(seq, seq2), LCM(seq2, seq3));
        int res = Math.min(temp, LCM(seq, seq3));
        System.out.println(res);
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}