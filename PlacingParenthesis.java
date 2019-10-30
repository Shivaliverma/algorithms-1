import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class PlacingParenthesis {
    private static long[][] minVal;
    private static long[][] maxVal;
    private static char[] ops;

    private static long[] MinAndMax(int i, int j) {
        long min = 999999999999999999L;
        long max = -999999999999999999L;
        for (int k = i; k < j; k++) {
            //System.out.println(minVal[i][k] + " " + minVal[k + 1][j]);
            //System.out.println(maxVal[i][k] + " " + maxVal[k + 1][j]);
            long a = eval(maxVal[i][k], maxVal[k + 1][j], ops[k]);
            long b = eval(maxVal[i][k], minVal[k + 1][j], ops[k]);
            long c = eval(minVal[i][k], maxVal[k + 1][j], ops[k]);
            long d = eval(minVal[i][k], minVal[k + 1][j], ops[k]);
            //System.out.println("a = " + a + " b = " + b + " c = " + c + " d = " + d);
            long temp1 = Math.min(a, b);
            long temp2 = Math.min(c, d);
            long temp3 = Math.min(temp1, temp2);
            min = Math.min(min, temp3);
            temp1 = Math.max(a, b);
            temp2 = Math.max(c, d);
            temp3 = Math.max(temp1, temp2);
            max = Math.max(max, temp3);
        }
        long[] res = new long[2];
        res[0] = min;
        res[1] = max;
        //System.out.println(min);
        //System.out.println(max);
        //System.out.println();
        return res;
    }

    private static long getMaximValue(String exp) {
        int n = exp.length() / 2 + 1;
        minVal = new long[n][n];
        maxVal = new long[n][n];
        char[] expression = exp.toCharArray();

        long[] digits = new long[n];
        int index = 0;
        for (int i = 0; i < digits.length; i++) {
            digits[i] = Long.parseLong(String.valueOf(expression[index]));
            index += 2;
        }

        ops = new char[n - 1];
        index = 1;
        for (int i = 0; i < ops.length; i++) {
            ops[i] = expression[index];
            index += 2;
        }

        // actual position = index + 1
        for (int i = 0; i < n; i++) {
            minVal[i][i] = digits[i];
            maxVal[i][i] = digits[i];
        }
        for (int s = 0; s <= n - 1; s++) {
            for (int i = 0; i < n - s; i++) {
                int j = i + s;
                if (i != j) {
                    long[] values = MinAndMax(i, j);
                    minVal[i][j] = values[0];
                    maxVal[i][j] = values[1];
                }
            }
        }
        return maxVal[0][n - 1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
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