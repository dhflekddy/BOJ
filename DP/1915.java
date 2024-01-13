import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Testing3 {
    public static void solution(int[][] arr, int[][] dp, int n, int m) {
        int max = 0;
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 1; j < m + 1; ++j) {
                if (arr[i][j]==1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max * max);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; ++i) {
            String input = br.readLine();
            for (int j = 1; j < m + 1; ++j) {
                arr[i][j] = input.charAt(j - 1) - '0';
            }
        }
        for(int i=0;i<n+1;i++)
            System.out.println(Arrays.stream(arr[i]).mapToObj(String::valueOf).collect(Collectors.joining(" ")).toString());
        solution(arr, dp, n, m);
    }
}