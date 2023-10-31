import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main{
    public static List<Integer> howSum(int target, int[]arr){
        List<Integer>answer=null;
        if(target<0)
            return null;
        if(target==0)
            return new LinkedList<>();
        for(int i=0;i<arr.length;i++){
            answer=howSum(target-arr[i], arr);
            if(answer!=null){
                answer.add(arr[i]);
                return answer;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0;i<t;i++){
            st=new StringTokenizer(br.readLine());
            int target=Integer.parseInt(st.nextToken());
            int n=Integer.parseInt(st.nextToken());
            int[] arr=new int[n];
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++)
                arr[j]=Integer.parseInt(st.nextToken());
            List<Integer> answer=howSum(target, arr);
            if(answer==null)
                System.out.println(-1);
            else{
                System.out.print(answer.size()+" ");
                System.out.println(answer.stream().map(x->x.toString()).collect(Collectors.joining(" ")));
            }
        }
    }
}