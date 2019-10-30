import java.util.*;
import java.io.*;

public class MajorityElement {
    private static boolean checkElement(int[] a, int test) {
        if (test == -1) {
            return false;
        }
        int count = 0;
        for (int value : a) {
            if (value == test) {
                count++;
            }
        }
        return count > a.length / 2;
    }

    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return a[left];
        }
        int middle = left + (right - left) / 2;
        int firstPossibleElement = getMajorityElement(a, left, middle);
        int secondPossibleElement = getMajorityElement(a, middle + 1, right);
        if (checkElement(a, firstPossibleElement)) return firstPossibleElement;
        if (checkElement(a, secondPossibleElement)) return secondPossibleElement;
        return -1;
    }

    private static int getMajorityElementHashMap(int[] a) {
        Map <Integer, Integer> map = new HashMap<>();
        for (int number : a) {
            map.merge(number, 1, Integer::sum);
        }
        int maxCount = 0;
        for (Map.Entry entry : map.entrySet()) {
            maxCount = Math.max(maxCount, (Integer)entry.getValue());
        }
        if (maxCount > a.length / 2) {
            for (Map.Entry entry : map.entrySet()) {
                if ((Integer)entry.getValue() == maxCount) {
                    return (Integer)entry.getKey();
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElementHashMap(a) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
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