import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MoneyChangeDynamic {
    private static class ChangeData {
        // money -> amount of coins for change
        private Map<Integer, Integer> map = new HashMap<>(Collections.singletonMap(0, 0));
        private int getMinNumCoins(int money) {
            return map.getOrDefault(money, -1);
        }
        private void addOrReplaceNumCoins(int money, int numCoins) {
            map.put(money, numCoins);
        }
    }

    private static int DPChange(int money, int[] coins) {
        if (money == 0) {
            return 0;
        }
        ChangeData changeData = new ChangeData();
        for (int m = 1; m <= money; m++) {
            for (int coin : coins) {
                if (m >= coin) {
                    int numCoinsPossible = changeData.getMinNumCoins(m - coin) + 1;
                    int numCoinsActual = changeData.getMinNumCoins(m);
                    if (numCoinsActual == -1 || numCoinsActual > numCoinsPossible) {
                        changeData.addOrReplaceNumCoins(m, numCoinsPossible);
                    }
                }
            }
        }
        return changeData.getMinNumCoins(money);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int money = scanner.nextInt();
        int[] coins = {1, 3, 4};
        System.out.println(DPChange(money, coins));
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