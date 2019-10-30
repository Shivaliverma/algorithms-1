import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiscreteKnapsackWithoutRepetitions {

    private static int MaxWeight(int capacity, int[] barsWeight) {
        int [][] maxWeights = new int[capacity + 1][barsWeight.length + 1];
        for (int i = 0; i <= capacity; i++) {
            maxWeights[i][0] = 0;
        }
        for (int i = 0; i <= barsWeight.length; i++) {
            maxWeights[0][i] = 0;
        }
        for (int bar = 1; bar <= barsWeight.length; bar++) {
            for (int currCup = 1; currCup <= capacity; currCup++) {
                int value = maxWeights[currCup][bar] = maxWeights[currCup][bar - 1];
                if (barsWeight[bar - 1] <= currCup) {
                    maxWeights[currCup][bar] = Math.max(value,
                            maxWeights[currCup - barsWeight[bar - 1]][bar - 1] + barsWeight[bar - 1]);
                }
            }
        }
        return maxWeights[capacity][barsWeight.length];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int capacity = scanner.nextInt();
        int n = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            int w = scanner.nextInt();
            weights[i] = w;
        }
        System.out.println(MaxWeight(capacity, weights));
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