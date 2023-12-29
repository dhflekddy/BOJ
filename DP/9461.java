import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Testing3{
    public static void solution(int n){
        if(n<=3){
            System.out.println(1);
            return;
        }
        else if(n<=5){
            System.out.println(2);
            return;
        }
        long[]dp=new long[n+1];
        Arrays.fill(dp, 1,4, 1);
        Arrays.fill(dp, 4,6,2);
        for(int i=6;i<=n;++i)
            dp[i]=dp[i-1]+dp[i-5];

        System.out.println(dp[n]);
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            int n=Integer.parseInt(br.readLine());
            solution(n);
        }
    }
}