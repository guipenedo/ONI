package finais;
//versao significativamente mais simples O(n*max_depth^2) tambem para 100 pontos com uma trie simplesßßß

import java.io.*;
import java.util.StringTokenizer;

public class PalavrasParaQueTeQuero_simples {
    static final int MAXS = 1000000, MAXC = 26;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int g[][] = new int[MAXS][MAXC], l[] = new int[MAXS], v[] = new int[MAXS];

    public static void main(String[] args) throws IOException {
        int p = in(), ss = 0, Mlen = 0;
        for (int k = 0; k < p; k++) {
            char[] a = next().toCharArray();
            int s = 0;
            for (int i = 0; i < a.length; i++) {
                if (g[s][i(a[i])] == 0)
                    g[s][i(a[i])] = ++ss;
                s = g[s][i(a[i])];
            }
            Mlen = Math.max(Mlen, a.length);
            l[s] = a.length;
            v[s] = in();
        }
        int c = in();
        for (int k = 0; k < c; k++) {
            char[] a = next().toCharArray();
            int dp[] = new int[a.length + 5];
            for (int i = 0; i < a.length; i++) {
                dp[i + 1] = dp[i];
                l:
                for (int x = Math.max(0, i - Mlen); x <= i; x++) {
                    int s = 0;
                    for (int j = x; j <= i; j++)
                        if ((s = g[s][i(a[j])]) == 0)
                            continue l;
                    dp[i + 1] = Math.max(dp[i + 1], v[s] + dp[i + 1 - l[s]]);
                }
            }
            out.println(dp[a.length]);
        }
        out.flush();
    }

    static int i(char c) {
        return c - 'a';
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
