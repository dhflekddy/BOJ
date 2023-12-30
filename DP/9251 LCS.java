import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Testing3 {
    public static void solution(char[]arr1, char[]arr2){
        int len1=arr1.length;
        int len2=arr2.length;
        int[][]dp=new int[len1+1][len2+1];
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(arr1[i-1]==arr2[j-1])
                    dp[i][j]=dp[i-1][j-1]+1;
                else
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        System.out.println(dp[len1][len2]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        solution(arr1, arr2);
    }
}
