import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int countSum(int target, int[] arr) {
        int answer = 0;
        if (target == 0)
            return 1;
        if (target < 0)
            return 0;
        for (int i = 0; i < arr.length; i++) {
            answer += countSum(target - arr[i], arr);
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();//아래 4줄의 코드를 한큐에 처리하는 스트림!!
            /*
            int[]arr=new int[n];
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++)
                arr[j]=Integer.parseInt(st.nextToken());
             */
            System.out.println(countSum(target, arr));
        }
    }
}