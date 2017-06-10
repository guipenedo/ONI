//Problema: https://omegaup.com/arena/CIIC-2016/practice/#problems/Enredados

package CIIC2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Enredados {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = in(), a[] = new int[n / 2 + 1];
        long t = 0;
        SegmentTree tr = new SegmentTree(n / 2 + 1);
        for (int i = 1; i <= n / 2; i++)
            a[in()] = i;
        for (int i = 0; i < n / 2; i++) {
            int k = in();
            t += tr.query(a[k] - 1);
            tr.update(a[k]);
        }
        System.out.println(t);
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static class SegmentTree {
        int a[];

        public SegmentTree(int n) {
            a = new int[n];
        }

        public int query(int i) {
            if (i <= 0) return 0;
            int t = -i;
            for (; i > 0; i -= Integer.lowestOneBit(i))
                t += a[i];
            return -t;
        }

        public void update(int i) {
            for (; i < a.length; i += Integer.lowestOneBit(i))
                a[i] += 1;
        }
    }
}
