import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Testing3{
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int[]arr= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        String result="";
        for(int i=0;i<6;++i){
            if(arr[i]==arr[i+1]-1)result="ascending";
            else if(arr[i]==arr[i+1]+1)result="descending";
            else{
                System.out.println("mixed");
                return;
            }
        }
        System.out.println(result);
    }
}