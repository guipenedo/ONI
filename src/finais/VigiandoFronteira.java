//stack overflow error (apenas no mooshak). mas algoritmo teoricamente e para 100 - O(5000*t)
package finais;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class VigiandoFronteira {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int s[] = new int[5050], dp[][] = new int[5050][200], t, a, p;

    public static void main(String[] args) throws IOException {
        t = in();
        a = in();
        p = in();
        boolean pp[] = new boolean[5050];
        for (int i = 0; i < p; i++)
            pp[in()] = true;
        for (int i = 0; i < 5005; i++) {
            s[i] = (pp[i] ? 1 : 0) + (i != 0 ? s[i - 1] : 0);
            Arrays.fill(dp[i], -1);
        }
        out.println(dp(5000, t));
        out.flush();
    }

    static int dp(int x, int n) {
        if (n < 1 || x < 0) return 0;
        if (dp[x][n] == -1)
            dp[x][n] = Math.max(dp(x - 1, n), r(x) + dp(x - 2 * a - 1, n - 1));
        return dp[x][n];
    }

    static int r(int x) {
        return s[Math.min(x + a, 5000)] - (x - a - 1 < 0 ? 0 : s[x - a - 1]);
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
