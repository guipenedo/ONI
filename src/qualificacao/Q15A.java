package qualificacao;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q15A {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = in(), p = in();
        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            String nome = next();
            int pp = 0;
            for (int j = 0; j < p; j++)
                pp += in();
            a[i] = new Node(nome, pp);
        }
        Arrays.sort(a);
        for (int i = 0; i < n; i++)
            out.println(a[i].nome + " " + a[i].p);
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

    static class Node implements Comparable<Node> {
        String nome;
        int p;

        public Node(String nome, int p) {
            this.nome = nome;
            this.p = p;
        }

        @Override
        public int compareTo(Node o) {
            return o.p == p ? nome.compareTo(o.nome) : o.p - p;
        }
    }
}
