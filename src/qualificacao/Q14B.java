package qualificacao;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Q14B {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static TreeSet[] m = new TreeSet[20001];

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        int n = in(), r = 0, a = Integer.MIN_VALUE;
        for (int i = 0; i <= 20000; i++) m[i] = new TreeSet<Integer>();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point(in(), in());
            m[p[i].x].add(p[i].y);
        }
        Arrays.sort(p);
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int x = p[j].x - p[i].x, y = p[j].y - p[i].y;
//                out.println("testing points: " + p[i] + " " + p[j] + ": [" + (p[i].x + y) + ";" + (p[i].y + x) + "] e [" + (p[i].x + y + x) + ";" + (p[i].y + y - x) + "]");
                if (m(p[i].x + y, p[i].y - x) && m(p[i].x + y + x, p[i].y + y - x)) {
                    r++;
                    a = Math.max(a, x * x + y * y);
                }
            }
        out.println(r / 2);
        if (r > 0)
            out.println(a);
        out.flush();
    }

    static boolean m(int x, int y) {
        return !(x < 1 || y < 1) && m[x].contains(y);
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "x=" + x + " y=" + y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? x - o.x : y - o.y;
        }
    }
}
