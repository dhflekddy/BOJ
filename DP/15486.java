import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
어려운 문제다... dp의 점화식을 구하기 위해서는 무조건 규칙을 찾아 보기 위해 해보는 방법 밖에는 없다.
https://www.youtube.com/watch?v=-4wjSUr1_K0&t=1458s
 */
public class Main{
    public static void solution(int n, int[]duration, int[]profit){
        int[]dp=new int[n+2];
        for(int i=1;i<=n;i++){
            int next=i+duration[i];
            if(next<=n+1)
                dp[next]=Math.max(dp[i]+profit[i], dp[next]);

            dp[i+1]=Math.max(dp[i], dp[i+1]);

        }
        System.out.println(dp[n+1]);

    }

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[]duration=new int[n+1];
        int[]profit=new int[n+1];
        StringTokenizer st;
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            duration[i]=Integer.parseInt(st.nextToken());
            profit[i]=Integer.parseInt(st.nextToken());
        }
        solution(n, duration, profit);
    }
}
