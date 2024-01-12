import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Testing3 {
    public static void solution(int n, int[] arr) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i=1;i<n;++i){
            for(int j=0;j<i;++j){
                if(arr[i]>arr[j]){
                    dp[i]=Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int max=-1;
        for(int i=0;i<n;++i){
            max=Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        solution(n, arr);
    }
}