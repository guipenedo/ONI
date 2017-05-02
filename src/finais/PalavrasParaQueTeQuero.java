package finais;//utiliza o seguinte algoritmo: http://cr.yp.to/bib/1975/aho.pdf

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class PalavrasParaQueTeQuero {
    static final int MAXS = 1000000, MAXC = 26;
    static final int FAIL = -1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int g[][] = new int[MAXS][MAXC], f[] = new int[MAXS];
    static ArrayList<Integer>[] ll = new ArrayList[MAXS], vv = new ArrayList[MAXS];

    public static void main(String[] args) throws IOException {
        int p = in(), ss = 1;
        for (int i = 0; i < MAXS; i++) Arrays.fill(g[i], FAIL);
        ll[0] = new ArrayList<Integer>();
        vv[0] = new ArrayList<Integer>();
        for (int i = 0; i < p; i++) {
            int s = 0;
            char[] cc = next().toCharArray();
            for (char a : cc) {
                if (g[s][i(a)] == FAIL) {
                    g[s][i(a)] = ss++;
                    ll[g[s][i(a)]] = new ArrayList<Integer>();
                    vv[g[s][i(a)]] = new ArrayList<Integer>();
                }
                s = g[s][i(a)];
            }
            ll[s].add(cc.length);
            vv[s].add(in());
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < MAXC; i++)
            if (g[0][i] == FAIL)
                g[0][i] = 0;
            else
                q.add(g[0][i]);
        while (!q.isEmpty()) {
            int r = q.poll();
            for (int a = 0; a < MAXC; a++) {
                if (g[r][a] == FAIL) continue;
                q.add(g[r][a]);
                int s = f[r];
                while (g[s][a] == FAIL) s = f[s];
                f[g[r][a]] = g[s][a];
                ll[g[r][a]].addAll(ll[g[s][a]]);
                vv[g[r][a]].addAll(vv[g[s][a]]);
            }
        }
        int c = in();
        for (int j = 0; j < c; j++) {
            char[] a = next().toCharArray();
            int s = 0, dp[] = new int[a.length + 5];
            for (int i = 0; i < a.length; i++) {
                dp[i + 1] = dp[i];
                while (g[s][i(a[i])] == FAIL) s = f[s];
                s = g[s][i(a[i])];
                for (int l = 0; l < ll[s].size(); l++)
                    dp[i + 1] = Math.max(dp[i + 1], vv[s].get(l) + dp[i + 1 - ll[s].get(l)]);
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
