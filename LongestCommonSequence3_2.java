import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestCommonSequence3_2 {

    private static int LCM(int[] seq, int[] seq2, int[] seq3) {
        int[][][] matrix = new int[seq.length + 1][seq2.length + 1][seq3.length + 1];
        for (int i = 0; i <= seq.length; i++) {
            for (int j = 0; j <= seq2.length; j++) {
                for (int z = 0; z <= seq3.length; z++) {
                    matrix[i][j][z] = 0;
                }
            }
        }
        for (int z = 1; z <= seq3.length; z++) {
            for (int j = 1; j <= seq2.length; j++) {
                for (int i = 1; i <= seq.length; i++) {
                    int insertion = matrix[i - 1][j][z];
                    int deletion = matrix[i][j - 1][z];
                    int move = matrix[i][j][z - 1];
                    int match = matrix[i - 1][j - 1][z - 1] + 1;
                    int mismatch = matrix[i - 1][j - 1][z - 1];
                    int temp = Math.max(insertion, deletion);
                    int temp2 = Math.max(temp, move);
                    if (seq[i - 1] == seq2[j - 1] && seq[i - 1] == seq3[z - 1]) {
                        matrix[i][j][z] = Math.max(temp2, match);
                    } else {
                        matrix[i][j][z] = Math.max(temp2, mismatch);
                    }
                }
            }
        }
        return matrix[seq.length][seq2.length][seq3.length];
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
        System.out.println(LCM(seq, seq2, seq3));
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