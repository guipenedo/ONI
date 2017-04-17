package qualificacao;

import java.io.*;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Q11A {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        SortedSet<Bakugan> q = new TreeSet<Bakugan>();
        int a = in(), r = in();
        for (int i = 0; i < a + r; i++) {
            String l = next();
            if (l.equals("MIN")) {
                out.println(q.last().e);
                q.remove(q.last());
            } else if (l.equals("MAX")) {
                out.println(q.first().e);
                q.remove(q.first());
            } else
                q.add(new Bakugan(in()));
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

    static class Bakugan implements Comparable<Bakugan> {
        static int ii = 0;
        int e, i;

        public Bakugan(int e) {
            this.e = e;
            this.i = ii++;
        }

        @Override
        public int compareTo(Bakugan o) {
            return e == o.e ? i - o.i : o.e - e;
        }
    }
}
