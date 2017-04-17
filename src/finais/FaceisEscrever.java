package finais;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class FaceisEscrever {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int[][] t = {{0, 8}, {1, 2, 4}, {1, 2, 3, 5}, {2, 3, 6}, {1, 4, 5, 7}, {2, 4, 5, 6, 8}, {3, 5, 6, 9}, {4, 7, 8}, {0, 5, 7, 8, 9}, {6, 8, 9}};

    static ArrayList<Integer> a = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        a.add(0);
        for (int i = 1; i <= 9; i++)
            gen(i, i);
        Collections.sort(a);
        int c = in();
        for (int i = 0; i < c; i++) {
            int x = in();
            out.println(x + ": " + a.get(x - 1));
        }
        out.flush();
    }

    static void gen(int i, int d) {
//        System.out.println("i=" + i + " d=" + d + " c=" + c)
        //Para chegar a este valor: gerar uma serie deles e ver o q corresponde à posicao maxima possivel (dos limites do enunciado)
        if (i > 98788523)
            return;
        a.add(i);
        for (int j = 0; j < t[d].length; j++)
            gen(i * 10 + t[d][j], t[d][j]);
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
