package qualificacao;

import java.io.*;
import java.util.StringTokenizer;

public class Q16C {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int stm[], stl[], str[];
    static boolean b[];

    public static void main(String[] args) throws IOException {
        int n = in(), q = in();
        b = new boolean[n + 1];
        stm = new int[4 * n + 50];
        stl = new int[4 * n + 50];
        str = new int[4 * n + 50];
        char[] cc = next().toCharArray();
        for (int i = 0; i < n; i++)
            b[i + 1] = cc[i] == '1';
        build_all(1, 1, n);
        for (int i = 0; i < q; i++) {
            String ss = next();
            if (ss.equals("P"))
                change(1, 1, n, in(), 0);
            else if (ss.equals("A"))
                change(1, 1, n, in(), 1);
            else
                out.println(Math.max(Math.max(stm[1], stl[1]), str[1]));
        }
        out.flush();
    }

    static void change(int i, int l, int r, int x, int d) {
        if (x < l || x > r)
            return;
        if (l == r) {
            stm[i] = str[i] = stl[i] = d;
            return;
        }
        int mid = (l + r) >>> 1;
        change(i * 2, l, mid, x, d);
        change(i * 2 + 1, mid + 1, r, x, d);
        build(i, l, r, mid);
    }

    static void build(int i, int l, int r, int mid) {
        stm[i] = Math.max(Math.max(stm[i * 2], stm[i * 2 + 1]), str[i * 2] + stl[i * 2 + 1]);
        str[i] = str[i * 2 + 1] + (str[i * 2 + 1] == r - mid ? str[i * 2] : 0);
        stl[i] = stl[i * 2] + (stl[i * 2] == mid - l + 1 ? stl[i * 2 + 1] : 0);
    }

    private static void build_all(int i, int l, int r) {
        if (l == r) {
            stm[i] = str[i] = stl[i] = !b[l] ? 1 : 0;
            return;
        }
        int mid = (l + r) >>> 1;
        build_all(i * 2, l, mid);
        build_all(i * 2 + 1, mid + 1, r);
        build(i, l, r, mid);
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
