import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class EditDistance {

    public static class Key {

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

    private static int EditDistance(char[] first, char[] second) {
        int firstLength = first.length;
        int secondLength = second.length;
        Map<Key, Integer> matrix = new HashMap<>();
        for (int i = 0; i <= firstLength; i++) {
            matrix.put(new Key(i, 0), i);
        }
        for (int j = 0; j <= secondLength; j++) {
            matrix.put(new Key(0, j), j);
        }
        for (int j = 1; j <= secondLength; j++) {
            for (int i = 1; i <= firstLength; i++) {
                int insertion = matrix.get(new Key(i, j - 1)) + 1;
                int deletion = matrix.get(new Key(i - 1, j)) + 1;
                int match = matrix.get(new Key(i - 1, j - 1));
                int mismatch = matrix.get(new Key(i - 1, j - 1)) + 1;
                int temp = Math.min(insertion, deletion);
                if (first[i - 1] == second[j - 1]) {
                    matrix.put(new Key(i, j), Math.min(temp, match));
                } else {
                    matrix.put(new Key(i, j), Math.min(temp, mismatch));
                }
            }
        }
        return matrix.get(new Key(firstLength, secondLength));
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        char[] first = scanner.next().toCharArray();
        char[] second = scanner.next().toCharArray();
        System.out.println(EditDistance(first, second));
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
