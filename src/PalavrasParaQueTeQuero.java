import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class PalavrasParaQueTeQuero {
    static final int MAXS = 1000000, MAXC = 26;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int g[][] = new int[MAXS][MAXC], ll[] = new int[MAXS], vv[] = new int[MAXS];
    static Comparator<Interval> comp = new Comparator<Interval>() {
        public int compare(int x, int y) {
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        }

        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.r == o2.r ? compare(o1.l, o2.l) : compare(o1.r, o2.r);
        }
    };

    public static void main(String[] args) throws IOException {
        int p = in(), state = 1;
        for (int i = 0; i < p; i++) {
            int ss = 0;
            char[] cc = next().toCharArray();
            for (char a : cc) {
                if (g[ss][i(a)] == 0)
                    g[ss][i(a)] = state++;
                ss = g[ss][i(a)];
            }
            ll[ss] = cc.length;
            vv[ss] = in();
        }

        int c = in();
        for (int i = 0; i < c; i++) {
            char[] cc = next().toCharArray();
            PriorityQueue<Interval> q = new PriorityQueue<Interval>(1, comp);
            for (int j = 0; j < cc.length; j++) {
                int s = 0, k = j;
                while (k < cc.length && (s = g[s][i(cc[k++])]) != -1)
                    if (ll[s] != 0)
                        q.add(new Interval(j, j + ll[s] - 1, vv[s]));
            }
            TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
            int r = 0, v = 0;
            while (!q.isEmpty()) {
                Interval iv = q.poll();
                if (iv.r != r) {
                    m.put(r, v);
                    r = iv.r;
                    v = 0;
                }
                v = Math.max(v, iv.v + (m.lowerEntry(iv.l) != null ? m.lowerEntry(iv.l).getValue() : 0));
            }
            m.put(r, v);
            out.println(m.lowerEntry(cc.length) != null ? m.lowerEntry(cc.length).getValue() : 0);
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

    static class Interval {
        int l, r, v;

        public Interval(int l, int r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }
    }
}
