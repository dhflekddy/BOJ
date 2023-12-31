import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Testing3 {

    public static void solution(int n, int[][]dp){
        for(int i=2;i<=n;++i){
            for(int j=1;j<=i;++j){
                if(j==1)
                    dp[i][j]+=dp[i-1][j];
                else{
                    dp[i][j]=dp[i][j]+Math.max(dp[i-1][j-1], dp[i-1][j]);
                }
            }
        }
        int maxValue=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++)
            maxValue=Math.max(dp[n][i], maxValue);

        System.out.println(maxValue);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        int[][]arr=new int[n+1][n+1];
        for(int i=1;i<n+1;++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; ++j)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        solution(n, arr);
    }
}
