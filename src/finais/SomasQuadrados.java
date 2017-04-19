//Solucao do @gangsterveggies para 100
package finais;

import java.io.*;
import java.util.StringTokenizer;

public class SomasQuadrados {
    static final int MAX = 2000000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int sq[] = new int[MAX + 5], sq2[] = new int[MAX + 5], m[];

    public static void main(String[] args) throws IOException {
//        long now = System.currentTimeMillis();
        for (int i = 1; i * i <= MAX; i++) {
            for (int j = i; i * i + j * j <= MAX; j++)
                sq2[i * i + j * j]++;
            sq[i * i] = i;
        }
        int c = in();
        for (int w = 0; w < c; w++) {
            int n = in(), k = 0, d = 0;
            if (sq[n] > 0) {
                k = 1;
                d = 1;
            } else if (sq2[n] > 0) {
                k = 2;
                d += sq2[n];
            } else {
                //3
                for (int i = 1; i * i <= n; i++)
                    for (int j = i; j * j + i * i <= n; j++)
                        if (sq[n - i * i - j * j] >= j) {
                            k = 3;
                            d++;
                        }
                //4
                if (k == 0) {
                    k = 4;
                    m = new int[MAX + 5];
                    for (int i = 1; i * i <= n; i++) {
                        for (int j = 1; j <= i && i * i + j * j <= n; j++)
                            m[i * i + j * j]++;
                        for (int j = i; i * i + j * j <= n; j++)
                            d += m[n - i * i - j * j];
                    }
                }
            }
            out.println(n + ": " + k + " " + d);
        }
//        out.println("time: " + (System.currentTimeMillis() - now));
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