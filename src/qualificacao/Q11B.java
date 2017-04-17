package qualificacao;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Q11B {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static TreeSet<String> s = new TreeSet<String>(), o = new TreeSet<String>(), b = new TreeSet<String>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> mat = new ArrayList<String>();
        int n = in();
        for (int i = 0; i < n; i++) {
            String c = next();
            o.add(c);
            c = " " + c + " ";
            for (int j = 1; j < c.length() - 1; j++) {
                String l = c.substring(1, j), r = c.substring(j + 1, c.length() - 1);
                s.add(l + r);
                if (s.contains(l + "#" + r))
                    b.add(l + "#" + r);
                s.add(l + "#" + r);
                s.add(l + "#" + c.charAt(j) + r);
            }
            s.add(c.substring(1, c.length() - 1) + "#");
        }
        int L = in();
        for (int i = 0; i < L; i++) {
            String c = next();
            if (matches(c))
                mat.add(c);
        }
        out.println(mat.size());
        for (String c : mat)
            out.println(c);
        out.flush();
    }

    static boolean matches(String c) {
        if (s.contains(c)) return true;
        String d = " " + c + " ";
        for (int j = 1; j < d.length() - 1; j++) {
            String l = d.substring(1, j), r = d.substring(j + 1, d.length() - 1);
            if (b.contains(l + "#" + r) || (s.contains(l + "#" + r) && !o.contains(c))) return true;
        }
        return false;
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
