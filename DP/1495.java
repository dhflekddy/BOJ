import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Testing3 {
    public static void solution(int n, int s, int m, int[] arr) {
        boolean[][]dp=new boolean[n+1][m+1];
        if(s+arr[1]<=m)dp[1][s+arr[1]]=true;
        if(s-arr[1]>=0)dp[1][s-arr[1]]=true;
        for(int i=2;i<=n;++i){
            for(int j=0;j<=m;++j){
                if(dp[i-1][j]){
                    if(j+arr[i]<=m)dp[i][j+arr[i]]=true;
                    if(j-arr[i]>=0)dp[i][j-arr[i]]=true;
                }
            }
        }
        for(int i=m;i>=0;--i){
            if(dp[n][i]){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        solution(n, s, m, arr);
    }
}
