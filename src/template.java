/*
    Template de ficheiro para as minhas soluções
    Permite leitura de input/escrita de output
    muito mais rápida do que a do Scanner
    Recomendo para submissões de standard input/output de concursos de programação
 */


import java.io.*;
import java.util.StringTokenizer;

public class template {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
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
