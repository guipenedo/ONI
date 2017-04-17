package finais;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrintaTres {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int a, c, z, dp[][][] = new int[11][10][2];
    static ArrayList<Integer> k = new ArrayList<Integer>();

    //d - digitos
    //n - contagem
    //t - tight
    static int dp(int d, int n, int t) {
        if (d == 1) return n == 0 ? 1 : 0;
        if (dp[d][n][t] == -1) {
            dp[d][n][t] = 0;
            for (int i = 0; i <= (t == 1 ? k.get(d - 2) : 9); i++)
                if (i != a || n > 0)
                    dp[d][n][t] += dp(d - 1, n - (i == a ? 1 : 0), t == 1 && i == k.get(d - 2) ? 1 : 0);
        }
        return dp[d][n][t];
    }

    public static void main(String[] args) throws IOException {
        a = in();
        c = in();
        int q = in();
        for (int i = 0; i < q; i++) {
            int x = in();
            out.println(x + ": " + bsearch(x));
        }
        out.flush();
    }

    static int bsearch(int m) {
        int l = 1, r = 2000000000;
        while (l < r) {
            int mid = ((r - l) >>> 1) + l;
            if (upper(mid) < m)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    static int upper(int m) {
        k.clear();
        while (m > 0) {
            k.add(m % 10);
            m /= 10;
        }
        z = k.size();
        for (int d = 0; d <= 10; d++)
            for (int n = 0; n <= 9; n++)
                Arrays.fill(dp[d][n], -1);
        int res = 0;
        //j=1-> de 1 algarismo, de 2, etc ate z algarismos
        for (int j = 1; j <= z; j++) {
            for (int i = 1; i <= (j == z ? k.get(z - 1) : 9); i++)
                res += dp(j, c - (i == a ? 1 : 0), j == z && i == k.get(z - 1) ? 1 : 0);
        }
        return res;
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
