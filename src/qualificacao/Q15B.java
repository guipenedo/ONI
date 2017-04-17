package qualificacao;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Q15B {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int c = in();
        for (int k = 0; k < c; k++) {
            int n = in(), d = in();
            TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
            int r = 0;
            for (int i = 0; i < n; i++) {
                int p = in();
                while (!m.isEmpty() && m.firstKey() < p - d)
                    m.pollFirstEntry();
                while (!m.isEmpty() && m.lastKey() > p + d)
                    m.pollLastEntry();
                int l = m.containsKey(p) ? m.get(p) : 0;
                r += l;
                m.put(p, l + 1);
            }
            out.println(r);
        }
        out.flush();
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }
}
