import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Testing3 {
    public static int target;
    public static int[] arr;

    public static int solution(int count, int[] numbers, int start) {
        int answer = 0;
        if (count == 3) {
            int temp = 0;
            for (int i = 0; i < 3; ++i) temp += numbers[i];
            if (temp <= target) {
                answer = Math.max(answer, temp);
            }
            return answer;
        }
        for (int i = start; i < arr.length; ++i) {
            numbers[count] = arr[i];
            int result = solution(count + 1, numbers, i + 1);
            if (result > answer) answer = result;
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] elements = new int[3];
        System.out.println(solution(0, elements, 0));

    }
}