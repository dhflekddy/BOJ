import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    // public static void solution(int num){
    //     int[]dp=new int[num+1];
    //     dp[0]=1;
    //     int[]arr={1,2,3};
    //     for(int i=0;i<num;i++){
    //         for(int j=0;j<arr.length;j++){
    //             if(i+arr[j]<=num)
    //                 dp[i+arr[j]]+=dp[i];
    //         }
    //     }
    //     System.out.println(dp[num]);
    // }
    public static void solution(int target){
        int[]arr={1,2,3};
        int[]dp=new int[target+1];
        dp[0]=1;
        for(int i=0;i<target;i++){
                for (var j : arr) {
                    if(i+j<=target)dp[i+j]+=dp[i];
                }
            }
        System.out.println(dp[target]);
        }

    public static void main(String[] arags)throws  IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            int num=Integer.parseInt(br.readLine());
            solution(num);
        }
    }
}
