package finais;//solucao para 90 pontos (WA)
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Escolas {
    static final int INF = 5000000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int l, c, ii[][], dpx[][][][] = new int[550][550][5][2], dpy[][][][] = new int[550][550][5][2], dir[][], w[][] = new int[2][2], lx = 0, ly = 0;
    static char[][] m;

    public static void main(String[] args) throws IOException {
//        long now = System.currentTimeMillis();
        l = in();
        c = in();
        ArrayList<Point> es = new ArrayList<Point>();
        dir = new int[][]{{1, 1, l, c, -1, -1}, {1, c, l, 1, -1, +1}, {l, 1, 1, c, 1, -1}, {l, c, 1, 1, 1, 1}};
        m = new char[l + 1][c + 1];
        ii = new int[l + 1][c + 1];
        int a = 0;
        for (int y = 1; y <= l; y++) {
            char[] k = next().toCharArray();
            for (int x = 1; x <= c; x++) {
                m[y][x] = k[x - 1];
                if (m[y][x] == 'E') {
                    es.add(new Point(x, y));
                    ii[y][x] = a++;
                }
            }
        }
        for (int y = 0; y <= 530; y++)
            for (int x = 0; x <= 530; x++)
                for (int i = 0; i < 4; i++) {
                    dpx[y][x][i][0] = INF;
                    dpy[y][x][i][0] = INF;
                    dpx[y][x][i][1] = INF;
                    dpy[y][x][i][1] = INF;
                }
        int b = Integer.MAX_VALUE;
        fillDp();
        int[] ll = new int[a];
        for (int y = 1; y <= l; y++) {
            for (int x = 1; x <= c; x++)
                if (m[y][x] == 'R') {
                    int r = Integer.MAX_VALUE;
                    Point p = new Point(0, 0);
                    rr(y, x);
                    for (int i = 0; i < 4; i++) {
                        r(dpy[y][x][i][0], dpx[y][x][i][0]);
                        r(dpy[y][x][i][1], dpx[y][x][i][1]);
                        if (dpy[y][x][i][0] != INF && d(dpy[y][x][i][0], dpx[y][x][i][0]) < r) {
                            r = d(dpy[y][x][i][0], dpx[y][x][i][0]);
                            dpy[y][x][4][0] = dpy[y][x][i][0];
                            dpx[y][x][4][0] = dpx[y][x][i][0];
                            p = new Point(dpx[y][x][i][0], dpy[y][x][i][0]);
                        }
                    }
                    ll[ii[p.y][p.x]] = Math.max(ll[ii[p.y][p.x]], d(w[1][0], w[1][1]));
                }
        }
        Point bp = new Point(0, 0);
        for (Point p : es) {
            if (ll[ii[p.y][p.x]] != 0 && ll[ii[p.y][p.x]] < b) {
                bp = p;
                b = ll[ii[p.y][p.x]];
            }
        }
        for (int y = 1; y <= l; y++)
            for (int x = 1; x <= c; x++)
                if ((dpx[y][x][4][0] != bp.x || dpy[y][x][4][0] != bp.y) && m[y][x] == 'R')
                    b = Math.max(b, Math.abs(y - dpy[y][x][4][0]) + Math.abs(x - dpx[y][x][4][0]));
//        for (int i = 0; i < 5; i++) {
//            System.out.println("best " + i);
//            for (int y = 1; y <= l; y++) {
//                for (int x = 1; x <= c; x++)
//                    System.out.print("(" + dpx[y][x][i][0] + "," + dpy[y][x][i][0] + ") ");
//                System.out.println();
//            }
//            System.out.println();
//        }
        out.println(bp.y + " " + bp.x);
        out.println(b);
        out.flush();
//        System.out.println("time: " + (System.currentTimeMillis() - now));
    }

    static void r(int y, int x) {
        int d = d(y, x);
//        System.out.println("adding distance to ("+y+","+x+")=" + d);
        if (d < d(w[0][0], w[0][1])) {
            w[1][0] = w[0][0];
            w[1][1] = w[0][1];
            w[0][0] = y;
            w[0][1] = x;
        } else if (d < d(w[1][0], w[1][1]) && (y != w[0][0] || x != w[0][1])) {
            w[1][0] = y;
            w[1][1] = x;
        }
    }

    static int d(int y, int x) {
        return Math.abs(y - ly) + Math.abs(x - lx);
    }

    static void rr(int y, int x) {
        Arrays.fill(w[0], INF);
        Arrays.fill(w[1], INF);
        ly = y;
        lx = x;
    }

    static void fillDp() {
        for (int i = 0; i < 4; i++) {
            int[] k = dir[i];
            for (int y = k[0]; (k[2] > k[0] ? y <= k[2] : y >= k[2]); y += (k[2] > k[0] ? 1 : -1))
                for (int x = k[1]; (k[3] > k[1] ? x <= k[3] : x >= k[3]); x += (k[3] > k[1] ? 1 : -1)) {
                    rr(y, x);
                    if (m[y][x] == 'E')
                        r(y, x);
                    r(dpy[y + k[4]][x][i][0], dpx[y + k[4]][x][i][0]);
                    r(dpy[y][x + k[5]][i][0], dpx[y][x + k[5]][i][0]);
                    r(dpy[y + k[4]][x][i][1], dpx[y + k[4]][x][i][1]);
                    r(dpy[y][x + k[5]][i][1], dpx[y][x + k[5]][i][1]);
                    dpy[y][x][i][0] = w[0][0];
                    dpx[y][x][i][0] = w[0][1];
                    dpy[y][x][i][1] = w[1][0];
                    dpx[y][x][i][1] = w[1][1];
                }
        }
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
