package finais;

import java.io.*;
import java.util.*;

public class TrafegoAereo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static Aviao[] a;
    static int n;

    public static void main(String[] args) throws IOException {
        n = in();
        a = new Aviao[n];
        for (int i = 0; i < n; i++)
            a[i] = new Aviao(next(), ind(), ind(), i);
        Arrays.sort(a, cx);
        out.println(closest(0, n - 1));
        out.flush();
    }

    static Pair compare(Pair p, Aviao a, Aviao b) {
        return p.dsq > a.distsq(b) ? new Pair(a, b) : p;
    }

    private static Pair closest(int l, int r) {
        int c = r - l + 1;
        if (c < 2) return new Pair();
        if (c <= 6) {
            Pair b = new Pair();
            for (int i = l; i <= r; i++)
                for (int j = i + 1; j <= r; j++)
                    b = compare(b, a[i], a[j]);
            return b;
        }
        int mid = ((r - l) >>> 1) + l;
        Pair lp = closest(l, mid), rp = closest(mid + 1, r), b = lp.dsq < rp.dsq ? lp : rp;
        ArrayList<Aviao> d = new ArrayList<Aviao>();
        double k = (a[mid].x + a[mid + 1].x) / 2;
        for (int i = l; i <= r; i++)
            if (Math.abs(k - a[i].x) * Math.abs(k - a[i].x) <= b.dsq)
                d.add(a[i]);
        Collections.sort(d, cy);
        for (int i = 0; i < d.size(); i++)
            for (int j = 1; j < 6 && j+i < d.size(); j++)
                b = compare(b, d.get(i), d.get(j + i));
        return b;
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static double ind() throws IOException {
        return Double.parseDouble(next());
    }

    static Comparator<Aviao> cx = new Comparator<Aviao>() {
        @Override
        public int compare(Aviao o1, Aviao o2) {
            return o1.x == o2.x ? Double.compare(o1.y, o2.y) : Double.compare(o1.x, o2.x);
        }
    }, cy = new Comparator<Aviao>() {
        @Override
        public int compare(Aviao o1, Aviao o2) {
            return o1.y == o2.y ? Double.compare(o1.x, o2.x) : Double.compare(o1.y, o2.y);
        }
    };

    static class Pair {
        Aviao a, b;
        double dsq;

        public Pair() {
            dsq = Double.MAX_VALUE;
        }

        public Pair(Aviao a, Aviao b) {
            if (a.i < b.i) {
                this.a = a;
                this.b = b;
            } else {
                this.a = b;
                this.b = a;
            }
            dsq = a.distsq(b);
        }

        public String toString() {
            return a.codigo + " " + b.codigo;
        }
    }

    static class Aviao {
        String codigo;
        int i;
        double x, y;

        public Aviao(String codigo, double x, double y, int i) {
            this.codigo = codigo;
            this.x = x;
            this.y = y;
            this.i = i;
        }

        public double distsq(Aviao a) {
            return (x - a.x) * (x - a.x) + (y - a.y) * (y - a.y);
        }
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
