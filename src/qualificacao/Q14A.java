package qualificacao;

import java.io.*;
import java.util.StringTokenizer;

public class Q14A {
    static final int k = 200000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stok;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int st[] = new int[4 * k + 50], c = 0;

    public static void main(String[] args) throws IOException {
        int n = in();
        for (int i = 0; i < n; i++) {
            String ss = next();
            int x = in();
            if (ss.equals("INS")) {
                change(x, 1);
                c++;
            } else if (ss.equals("REM")) {
                change(x, -1);
                c--;
            } else {
                out.println(query(c - x + 1));
            }
        }
        out.flush();
    }

    static void change(int v, int x) {
        change(1, 1, k, v, x);
    }

    static void change(int i, int l, int r, int v, int x) {
        if (l <= v && v <= r)
            st[i] += x;
        if (l == r)
            return;
        int mid = (l + r) >>> 1;
        if (v <= mid)
            change(i * 2, l, mid, v, x);
        else
            change(i * 2 + 1, mid + 1, r, v, x);
    }

    static int query(int p) {
        int l = 1, r = k;
        while (l < r) {
            int mid = (l + r) >>> 1;
//            out.println("query: [1," + mid + "] = " + query(1, mid));
            if (query(1, mid) < p)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    static int query(int ql, int qr) {
        return query(1, 1, k, ql, qr);
    }

    static int query(int i, int l, int r, int ql, int qr) {
        if (ql <= l && qr >= r)
            return st[i];
        if (qr < l || ql > r)
            return 0;
        int mid = (l + r) >>> 1;
        return query(i * 2, l, mid, ql, qr) + query(i * 2 + 1, mid + 1, r, ql, qr);
    }

    static String next() throws IOException {
        if (stok == null || !stok.hasMoreTokens())
            stok = new StringTokenizer(br.readLine());
        return stok.nextToken();
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }
}
