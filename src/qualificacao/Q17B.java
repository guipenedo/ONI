package qualificacao;

import java.io.*;
import java.util.*;

public class Q17B {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = in(), q = in();
        ArrayList<V> a = new ArrayList<V>(2 * n);
        for (int i = 0; i < n; i++) {
            int x1 = in(), y1 = in(), x2 = in(), y2 = in();
            a.add(new V(x1, y2 - y1));
            a.add(new V(x2, y1 - y2));
        }
        Collections.sort(a, c);
        TreeMap<Integer, Long> m = new TreeMap<Integer, Long>();
        int x = 0;
        long v = 0;
        for (V i : a) {
            if (i.x != x) {
                m.put(x, v);
                x = i.x;
            }
            v += i.v;
        }
        m.put(x, v);
        for (int i = 0; i < q; i++)
            out.println(m.floorEntry(in()).getValue());
        out.flush();
    }

    static class V {
        static int ii = 0;
        int i, x, v;

        public V(int x, int v) {
            this.i = ii++;
            this.x = x;
            this.v = v;
        }
    }

    static Comparator<V> c = new Comparator<V>() {
        @Override
        public int compare(V o1, V o2) {
            if (o1.x == o2.x) return o1.i - o2.i;
            return Integer.compare(o1.x, o2.x);
        }
    };

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
