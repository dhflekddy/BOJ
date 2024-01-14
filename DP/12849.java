import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Testing3 {
    /*
        정보과학 0
        전산 1
        미래 2
        신양 3
        한경직 4
        진리5
        형남 6
        학생회 7
     */

    public static void solution(int d) {
        long[][] dp = new long[d+1][8];
        dp[0][0]=1;
        for (int i = 1; i <= d; ++i) {
            dp[i][0]=dp[i-1][1]+dp[i-1][2];
            dp[i][1]=dp[i-1][0]+dp[i-1][2]+dp[i-1][3];
            dp[i][2]=dp[i-1][0]+dp[i-1][1]+dp[i-1][3]+dp[i-1][4];
            dp[i][3]=dp[i-1][1]+dp[i-1][2]+dp[i-1][4]+dp[i-1][5];
            dp[i][4]=dp[i-1][2]+dp[i-1][3]+dp[i-1][5]+dp[i-1][6];
            dp[i][5]=dp[i-1][3]+dp[i-1][4]+dp[i-1][7];
            dp[i][6]=dp[i-1][4]+dp[i-1][7];
            dp[i][7]=dp[i-1][5]+dp[i-1][6];
            for(int j=0;j<8;++j)
                dp[i][j]%=1000000007;
        }
        System.out.println(dp[d][0]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());
        solution(d);
    }
}