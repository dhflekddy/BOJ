import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Testing3 {
    public static int knapsack(int n, int k, int[] w, int[] v) {
        int[][]dp=new int[n+1][k+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=k;j++){
                if(i== 0 || j==0)
                    continue;
                else if(w[i]<=j)
                    dp[i][j]=Integer.max(v[i]+dp[i-1][j-w[i]], dp[i-1][j]);
                else
                    dp[i][j]=dp[i-1][j];
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(knapsack(n, k, w, v));
    }
}