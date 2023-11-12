import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
어려운 문제다... dp의 점화식을 구하기 위해서는 무조건 규칙을 찾아 보기 위해 해보는 방법 밖에는 없다.
https://www.youtube.com/watch?v=-4wjSUr1_K0&t=1458s

비슷비슷한 문제일지라도 문제의 조건과 무엇을 구하는지에 따라 설계기법이 달라진다. 
마감시간과 작업일수가 있는 스캐줄링 에서 최소시간외 근무일수(27112, 시간외 근무 멈춰). 단 안되면 그냥 -1출력 =>Greedy

마감시간이 있는 스케줄링에서 최대이익(작업일수 없음, 마감시간과 이득만 있음) => Greedy 

(퇴사2문제)작업일수가 있는 스케줄링에서 최대이익 (작업일수, 이득만 있음) =Dynamic

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
