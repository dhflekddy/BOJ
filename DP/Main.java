import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static int solution(int r, int n){
        int[][]dp=new int[n+1][r+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=r;j++){
                if(j==i ||j==0)
                    dp[i][j]=1;
                else
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
            }
        }
        return dp[n][r];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=0;t<T;t++){
            st=new StringTokenizer(br.readLine());
            int west=Integer.parseInt(st.nextToken());
            int east=Integer.parseInt(st.nextToken());
            System.out.println(solution(west, east));

        }

    }
}