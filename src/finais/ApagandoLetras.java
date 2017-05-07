package finais;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ApagandoLetras {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static char[] a, b;
    static int be[][], dp[][];

    public static void main(String[] args) throws IOException {
        a = (" " + next()).toCharArray();
        b = (" " + next()).toCharArray();
        be = new int[a.length + 5][b.length + 5];
        dp = new int[a.length + 5][a.length + 5];
        for (int i = 0; i < a.length + 4; i++) {
            Arrays.fill(be[i], Integer.MIN_VALUE);
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        int r = 0;
        for (int i = 1; i < a.length; i++)
            if (be(i, b.length - 1) != -1)
                r = Math.max(be(i, b.length - 1) + dp(i, a.length), r);
        out.println(r);
        out.flush();
    }

    static int be(int i, int j) {
        if (a[i] != b[j]) return -1;
        if (j == 1) return dp(0, i);
        if (be[i][j] == Integer.MIN_VALUE) {
            be[i][j] = -1;
            for (int k = 1; k < i; k++)
                if (be(k, j - 1) != -1)
                    be[i][j] = Math.max(be(k, j - 1) + dp(k, i), be[i][j]);
        }
        return be[i][j];
    }

    static int dp(int i, int j) {
        if (Math.abs(j - i) <= 1) return 0;
        if (dp[i][j] == Integer.MIN_VALUE) {
            dp[i][j] = 0;
            for (int k = i + 1; k < j; k++)
                dp[i][j] = Math.max(dp[i][j], dp(i, k) + cost(i, k, j) + dp(k, j));
        }
        return dp[i][j];
    }

    static int cost(int i, int k, int j) {
        return (i > 0 ? Math.abs(a[i] - a[k]) : 0) + (j < a.length ? Math.abs(a[j] - a[k]) : 0);
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
