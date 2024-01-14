import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Testing3{
    public static void solution(int n, int m){
        //nCr=n-1Cr-1+n-1Cr
        BigInteger[][]dp=new BigInteger[n+1][n+1];
        for(int i=1;i<n+1;++i){
            for(int j=0;j<i+1;++j){
                if(j==0 ||j==i){
                    dp[i][j]=BigInteger.ONE;
                    continue;
                }
                dp[i][j]=dp[i-1][j-1].add(dp[i-1][j]);
            }
        }
        System.out.println(dp[n][m]);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        solution(n,m);
    }
}