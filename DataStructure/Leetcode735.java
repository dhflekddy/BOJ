import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Testing3 {
    public static Deque<Integer> solution(int[] arr) {
        int size = arr.length;
        Deque<Integer> answerList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            int ele = arr[i];
            boolean flag = true;
            while (!answerList.isEmpty() && answerList.peekLast() > 0 && ele < 0) {
                int lastOne = answerList.peekLast() + ele;
                if (lastOne > 0) {
                    flag = false;
                    break;
                } else if (lastOne == 0) {
                    answerList.pollLast();
                    flag = false;
                    break;
                } else {
                    answerList.pollLast();
                }
            }
            if (flag)
                answerList.addLast(ele);
        }
        return answerList;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            br.readLine();
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Deque<Integer> answer = solution(arr);
            if (answer.size() == 0)
                System.out.println();
            else
                System.out.println(answer.stream().map(m -> m.toString()).collect(Collectors.joining(" ")));
        }
    }
}