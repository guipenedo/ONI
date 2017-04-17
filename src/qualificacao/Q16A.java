package qualificacao;

import java.io.*;
import java.util.StringTokenizer;

public class Q16A {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = in(), k = in(), r = 0, j = 0;
        boolean[] m = new boolean[n + k + 2];
        char[] c = next().toCharArray();
        for (int i = 0; i < n; i++)
            m[i] = c[i] == 'H';
        for (int i = n - 1; i >= 0; i--) {
            if (m[i + k + 1]) j--;
            if (m[i + 1]) j++;
            if (!m[i]) r += j;
        }
        out.println(r);
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
}
