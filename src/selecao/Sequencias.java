import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sequencias {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    static int n, p, k[];

    public static void main(String[] args) throws IOException {
        n = in();
        char[] v = next().toCharArray();
        k = new int[n + 1];
        for (int i = 1; i <= n; i++)
            k[i] = k[i - 1] + (v[i - 1] - '0');
        p = in();
        for (int i = 0; i < p; i++)
            out.println(bits(inl(), inl()));
        out.flush();
    }

    static long bits(long x, long y) {
        return bits(y) - bits(x - 1);
    }

    static long bits(long x) {
        if (x <= 0) return 0;
        int i = 50;
        long l = n * 1125899906842624L, s = 0;
        boolean o = true;
        while (i > 1) {
            i--;
            l /= 2;
            if (x > l) {
                s += l / 2;
                x -= l;
                o = !o;
            }
        }
        l /=2;
        if (x > l){
            s += o ? k[n] : n - k[n];
            o = !o;
            x -= l;
        }
        s += o ? k[(int) x] : x - k[(int) x];
        return s;
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static long inl() throws IOException {
        return Long.parseLong(next());
    }
}
