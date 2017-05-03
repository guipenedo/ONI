package finais;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class AliceNoPaisDosPrimos {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        boolean pd[] = new boolean[1900000];
        StringBuilder sb = new StringBuilder();
        TreeSet<Integer> m = new TreeSet<Integer>();
        m.add(0);
        int p = in(), c = in();
        for (int i = 2; i < 1900000; i++) {
            if (!pd[i] && i >= p) {
                sb.append(i);
                m.add(m.last() + sb.length());
            }
            for (int j = 1; i * j < 1900000; j++)
                pd[i * j] = true;
        }
        for (int i = 0; i < c; i++) {
            int j = in();
            out.println(sb.charAt(j - m.lower(j) - 1));
        }
        out.flush();
    }

    static int in() throws IOException {
        return Integer.parseInt(next());
    }

    static String next() throws IOException {
        if (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}