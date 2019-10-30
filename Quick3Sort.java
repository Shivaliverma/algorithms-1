import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Quick3Sort {
    private static Random randomGen = new Random();

    private static void switchNumbers (int[] a, int firstIndex, int secondIndex) {
        int temp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = temp;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = randomGen.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int m = partition2(a, l, r);
        randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);
    }

    private static int[] partition3 (int[] a, int left, int right) {
        int x = a[left]; // pivot element
        int j = left; // all elements from a[j2...j] equal pivot element
        int j2 = -1;
        for (int i = left + 1; i <= right; i++) {
            if (a[i]  < x) {
                j++;
                switchNumbers(a, i, j);
                if (j2 != -1) {
                    switchNumbers(a, j, j2);
                    j2++;
                }
            }
            else if (a[i] == x) {
                j++;
                switchNumbers(a, i, j);
                if (j2 == -1) {
                    j2 = j;
                }
            }
        }
        if (j2 == -1) {
            j2 = j;
        } else {
            j2--;
        }
        switchNumbers(a, left, j2);
        return new int[] {j2, j};
    }

    private static void randomizedQuick3Sort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int random = randomGen.nextInt(right - left + 1) + left;
        switchNumbers(a, left, random);
        int [] indices = partition3(a, left, right);
        randomizedQuick3Sort(a, left, indices[0] - 1);
        randomizedQuick3Sort(a, indices[1] + 1, right);
    }

    public static void main(String[] args) {
        /*FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuick3Sort(a, 0, a.length - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }*/

        // STRESS testing
        int quantity = randomGen.nextInt(100000000) + 10000000;
        for (int i = 0; i < quantity; i++) {
            int [] array = new int[randomGen.nextInt(1000000)];
            for (int j = 0; j < array.length; j++) {
                array[j] = randomGen.nextInt(1000000);
            }
            int [] arrayCopy = array.clone();
            for (Integer value : array) {
                System.out.print(value + " ");
            }
            System.out.println();
            for (Integer value : arrayCopy) {
                System.out.print(value + " ");
            }
            randomizedQuickSort(array, 0, array.length - 1);
            randomizedQuick3Sort(arrayCopy, 0, arrayCopy.length - 1);
            assert (array == arrayCopy) : "error";
            System.out.println("OK");
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
