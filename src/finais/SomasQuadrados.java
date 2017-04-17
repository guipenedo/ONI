//So da 76 pontos ;(

package finais;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SomasQuadrados {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static HashMap<Triple, Integer> dp = new HashMap<Triple, Integer>();

    static class Triple {
        int i, j, k;

        public Triple(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triple triple = (Triple) o;

            return i == triple.i && j == triple.j && k == triple.k;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            result = 31 * result + k;
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
//        long now = System.currentTimeMillis();
        int c = in();
        for (int i = 0; i < c; i++) {
            int n = in(), k = (int) Math.sqrt(n);
            for (int j = 1; j <= 4; j++) {
                int r = dp(n, k, j);
                if (r != 0) {
                    out.println(n + ": " + j + " " + r);
                    break;
                }
            }
        }
//        out.println("time: " + (System.currentTimeMillis() - now));
        out.flush();
    }

    static int dp(int v, int k, int c) {
//        System.out.println("dp: " + v + " " + k + " " + c);
        k = Math.min(k, (int) Math.sqrt(v-c+1));
        if (v == 0 && c == 0)
            return 1;
        if (v <= 0 || k <= 0 || c == 0 || k * k * c < v)
            return 0;
        Triple t = new Triple(v, k, c);
        if (c == 1)
            return Math.pow(Math.sqrt(v), 2) == v ? 1 : 0;
        if (!dp.containsKey(t)) {
            int r = 0;
            for (int i = 0; i <= c; i++)
                r += dp(v - k * k * i, k - 1, c - i);
            dp.put(t, r);
        }
        return dp.get(t);
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