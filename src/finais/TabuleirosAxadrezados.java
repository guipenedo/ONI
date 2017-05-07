package finais;//agradecimento: @gangsterveggies - ajudou-me a descobrir o meu erro

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class TabuleirosAxadrezados {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int l, c, dp[][], q[], M = 0;

    public static void main(String[] args) throws IOException {
        l = in();
        c = in();
        dp = new int[l][c];
        q = new int[l * c + 50];
        char[][] m = new char[l][c];
        for (int y = 0; y < l; y++) {
            m[y] = next().toCharArray();
            for (int x = 0; x < c; x++)
                dp[y][x] = (y == 0 || m[y][x] == m[y - 1][x]) ? 1 : dp[y - 1][x] + 1;
        }
        for (int y = 0; y < l; y++) {
            Stack<Integer> s = new Stack<Integer>();
            for (int x = 0; x < c; x++) {
                int f = x;
                while (!s.isEmpty() && (m[y][x] == m[y][x - 1] || dp[y][s.peek()] > dp[y][x])) {
                    f = m[y][x] == m[y][x - 1] ? f : Math.min(f, s.peek());
                    w(y, s.pop(), x);
                    dp[y][f] = dp[y][x];
                }
                s.push(f);
            }
            while (!s.isEmpty())
                w(y, s.pop(), c);
        }
        out.println(M + " " + q[M]);
        out.flush();
    }

    static void w(int y, int xi, int x) {
        M = Math.max(dp[y][xi] * (x - xi), M);
        q[dp[y][xi] * (x - xi)]++;
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
