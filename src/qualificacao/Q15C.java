import java.util.ArrayList;
import java.util.Scanner;

public class Q15C {
    static ArrayList<Integer> digits = new ArrayList<Integer>();
    static int[][][][][] dp = new int[20][15][2][2][2];
    static boolean[][][][][] v = new boolean[20][15][2][2][2];

    public static int count_fogo_dp(int d, int prev, boolean up, boolean down, boolean tight) {
        if (d == digits.size())
            return up && down ? 1 : 0;
        if (!v[d][prev][i(up)][i(down)][i(tight)]) {
            dp[d][prev][i(up)][i(down)][i(tight)] = 0;
            for (int i = 0; i <= (tight ? digits.get(d) : 9); i++)
                dp[d][prev][i(up)][i(down)][i(tight)] += count_fogo_dp(d + 1, i, up || i > prev, down || i < prev, tight && i == digits.get(d));
            v[d][prev][i(up)][i(down)][i(tight)] = true;
        }
        return dp[d][prev][i(up)][i(down)][i(tight)];
    }

    public static int count_fogo(long x) {
        if (x <= 100)
            return 0;
        digits.clear();
        while (x > 0) {
            digits.add(0, (int) x % 10);
            x /= 10;
        }
        v = new boolean[20][15][2][2][2];
        int res = 0;

        for (int d = 0; d < digits.size() - 2; d++)
            for (int i = 1; i <= (d == 0 ? digits.get(0) : 9); i++)
                res += count_fogo_dp(d + 1, i, false, false, d == 0 && i == digits.get(0));
        return res;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();

        for (int i = 0; i < c; i++) {
            long x1 = sc.nextLong(), x2 = sc.nextLong();
            System.out.println(count_fogo(x2) - count_fogo(x1 - 1));
        }
    }

    public static int i(boolean b) {
        return b ? 1 : 0;
    }
}
