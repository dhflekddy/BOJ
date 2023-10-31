/*
이전에 java로 bestSum을 내용이 있지만 아래코드가 가장 간결하고 재구현하기에 가장 적합한 코드같다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> bestSum(int target, int[] arr) {
        List<Integer>answer=null;
        if(target<0)
            return null;
        if(target==0)
            return new LinkedList<>();
        for(int i=0;i<arr.length;i++){
            List<Integer>updating=bestSum(target-arr[i], arr);
            if(updating!=null) {
                updating.add(arr[i]);
                if (answer == null || answer.size() > updating.size())
                    answer = updating;
            }
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
            List<Integer> answer=bestSum(target, arr);
            if(answer==null)
                System.out.println(-1);
            else{
                System.out.print(answer.size()+" ");
                System.out.println(answer.stream().map(s->s.toString()).collect(Collectors.joining(" ")));
            }
        }
    }
}